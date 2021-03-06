package uk.ac.ucl.jsh.Utilities;

import uk.ac.ucl.jsh.Parser.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Visitor class that implements the TreeVisitor interface and evaluates the Parse tree generated by the Parser.
 */
public class EvalVisitor implements TreeVisitor<Void> {
    /**
     * Function that implements the logic behind Input redirection 
     * 
     * @param tokens         The tokens returned by the Parser class, containing the application name and its arguments
     * @param inputStream    The current input stream 
     * @return               The input stream after solving Input redirection, which might be the same as the inputStream argument
     * @throws JshException  Exception thrown if multiple files are used for Input redirection or if the File trying to be used does not exist
     */
    private InputStream getInputStream(ArrayList<String> tokens, InputStream inputStream) throws JshException {
        if (Collections.frequency(tokens, "<") > 1) {
            throw new JshException("Too many files for input redirection");
        }

        try {
            int inIndex = tokens.indexOf("<");
            if (inIndex != -1 && inIndex + 1 < tokens.size()) {
                inputStream = new FileInputStream(FileSystem.getInstance().getFile(tokens.get(inIndex+1)));
                tokens.subList(inIndex, inIndex + 2).clear();
            }
        } catch (FileNotFoundException e) {
            throw new JshException(e.getMessage());
        }

        return inputStream;
    }

    /**
     * Function that implements the logic behind Output redirection
     * 
     * @param tokens         The tokens returned by the Parser class, containing the application name and its arguments
     * @param outputStream   The current output stream 
     * @return               The output stream after solving Output redirection, which might be the same as the outputStream argument
     * @throws JshException  Exception thrown if multiple files are used for Output redirection or if the output file cannot be created
     */
    private OutputStream getOutputStream(ArrayList<String> tokens, OutputStream outputStream) throws JshException {
        if (Collections.frequency(tokens, ">") > 1) {
            throw new JshException("Too many files for output redirection");
        }

        try {
            int outIndex = tokens.indexOf(">");
            if (outIndex != -1 && outIndex + 1 < tokens.size()) {
                outputStream = new FileOutputStream(FileSystem.getInstance().getFile(tokens.get(outIndex+1)));
                tokens.subList(outIndex, outIndex + 2).clear();
            }
        } catch (FileNotFoundException e) {
            throw new JshException(e.getMessage());
        }

        return outputStream;
    }

     /**
     * Function that visits a seqNode in the Parse tree and evaluates it.
     * 
     * @param seqNode       The sequence Node that will be visited
     * @param inputStream   The stream that will be used by seqNodes' two children as input stream  if there is no I/O Redirection
     * @param outputStream  The stream that will be used by seqNodes' two children as output stream if there is no I/O Redirection
     * @return              Void, since the EvalVisitor evaluates the Parse tree and does not return anything
     * @throws JshException The exception that may be thrown if the visitor runs an Applications
     */
    public Void visit(SeqNode seqNode, InputStream inputStream, OutputStream outputStream) throws JshException {
        seqNode.getLeft().accept(this, inputStream, outputStream);
        seqNode.getRight().accept(this, inputStream,  outputStream);

        return null;
    }

     /**
     * Function that visits a pipeNode in the Parse tree and evaluates it.
     * 
     * @param pipeNode      The pipe Node that will be visited
     * @param inputStream   The stream that the left child of pipeNode should use as an input stream if there is no I/O Redirection
     * @param outputStream  The stream that the right child of pipeNode should use as an output stream if there is no I/O Redirection
     * @return              Void, since the EvalVisitor evaluates the Parse tree and does not return anything
     * @throws JshException The exception that may be thrown if the visitor runs an Applications
     */
    public Void visit(PipeNode pipeNode, InputStream inputStream, OutputStream outputStream) throws JshException {
        ByteArrayOutputStream newStream = new ByteArrayOutputStream();
        pipeNode.getLeft().accept(this, inputStream, newStream);
        ByteArrayInputStream newInputStream = new ByteArrayInputStream(newStream.toByteArray());
        pipeNode.getRight().accept(this, newInputStream, outputStream);

        return null;
    }

     /**
     * Function that visits a callNode in the Parse tree and evaluates the call command through the ApplicationManager, based on 
     * the tokens generated by the Parser
     * 
     * @param callNode      The call Node that will be visited
     * @param inputStream   The stream that the Application in the callNode should use as an input stream if there is no input redirection
     * @param outputStream  The stream that the Application in the callNode should use as an output stream if there is no output redirection
     * @return              Void, since the EvalVisitor evaluates the Parse tree and does not return anything
     * @throws JshException The exception that may be thrown if the Application in the callNode throws a JshException
     */
    public Void visit(CallNode callNode, InputStream inputStream, OutputStream outputStream) throws JshException {
        ArrayList<String> tokens = Parser.parseCallCommand(callNode.getApplicationString());
        
        inputStream = getInputStream(tokens, inputStream);
        outputStream = getOutputStream(tokens, outputStream);
        ApplicationManager.getInstance().executeApplication(tokens, inputStream, outputStream);
        
        return null;
    }
}