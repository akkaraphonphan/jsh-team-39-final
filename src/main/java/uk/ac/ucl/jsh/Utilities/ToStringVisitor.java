package uk.ac.ucl.jsh.Utilities;

import java.io.InputStream;
import java.io.OutputStream;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Parser.CallNode;
import uk.ac.ucl.jsh.Parser.PipeNode;
import uk.ac.ucl.jsh.Parser.SeqNode;

/**
 * Visitor class that implements the TreeVistor interface.
 * Used to get a String representation of the Parse tree in order to test for correct Parsing of input
 */
public class ToStringVisitor implements TreeVisitor<String> {
    /**
     * Utility StringBuilder to hold tab characters required to 'Pretty Print' the tree (i.e. make the string representation more readable)
     */
    private StringBuilder shiftSB = new StringBuilder("");

   /**
     * Function that visits a seqNode in the Parse tree and gets its String representation
     * 
     * @param seqNode       The sequence Node that will be visited
     * @param inputStream   The stream that will be used by seqNodes' two children as input stream
     * @param outputStream  The stream that will be used by seqNodes' two children as output stream
     * @return              String representation of the current Node
     * @throws JshException The exception that may be thrown if the visitor runs an Applications
     */
    public String visit(SeqNode seqNode, InputStream inputStream, OutputStream outputStream) throws JshException {
        StringBuilder result = new StringBuilder();
        result.append(shiftSB.toString() + "Seq Node" + Jsh.lineSeparator);
        shiftSB.append("\t");
        result.append(seqNode.getLeft().accept(this, inputStream, outputStream));
        result.append(seqNode.getRight().accept(this, inputStream, outputStream));
        shiftSB.setLength(shiftSB.length()-1);

        return result.toString();
    }

    /**
     * Function that visits a pipeNode in the Parse tree and gets its String representation
     * 
     * @param pipeNode      The pipe Node that will be visited
     * @param inputStream   The stream that the left child of pipeNode should use as an input stream
     * @param outputStream  The stream that the right child of pipeNode should use as an output stream
     * @return              String representation of the current Node
     * @throws JshException The exception that may be thrown if the visitor runs an Applications
     */
    public String visit(PipeNode pipeNode, InputStream inputStream, OutputStream outputStream) throws JshException {
        StringBuilder result = new StringBuilder();
        result.append(shiftSB.toString() + "Pipe Node" + Jsh.lineSeparator);
        shiftSB.append("\t");
        result.append(pipeNode.getLeft().accept(this, inputStream, outputStream));
        result.append(pipeNode.getRight().accept(this, inputStream, outputStream));
        shiftSB.setLength(shiftSB.length()-1);

        return result.toString();
    }

    /**
     * Function that visits a callNode in the Parse tree and gets its String representation
     * 
     * @param callNode      The call Node that will be visited
     * @param inputStream   The stream that the callNode should use as an input stream
     * @param outputStream  The stream that the callNode should use as an output stream
     * @return              String representation of the current Node
     * @throws JshException The exception that may be thrown if the visitor runs an Applications
     */
    public String visit(CallNode callNode, InputStream inputStream, OutputStream outputStream) throws JshException {
        return shiftSB.toString() + "Call node: " + callNode.getApplicationString() + Jsh.lineSeparator;
    }
}
