package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Cat application that implements the Application interface
 */
public class Cat implements Application{
    /**
     * The function that reads the inputStream or the File and writes it to the outputStream
     * 
     * @param scanner       The scanner object used to read the input
     * @param writer        The writer object used to write to the class' outputStream
     * @throws JshException The exception thrown if the writer cannot write to the outputstream
     */
    private void readAndWrite(Scanner scanner, OutputStreamWriter writer) throws JshException {
        try {
            while (scanner.hasNextLine()) {
                writer.write(scanner.nextLine());
                writer.write(Jsh.lineSeparator);
                writer.flush();
            }
            scanner.close();
        } catch (IOException e) {
            scanner.close();
            throw new JshException("cat: " + e.getMessage());
        }
    }

    /**
     * The function that checks the arguments passed to the Cat application
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that will be used as input if the applicationArguments does not contain a file
     * @throws JshException        The exception thrown if the Cat application does not receive any form of input
     */
    private void checkArguments(ArrayList<String> applicationArguments, InputStream inputStream) throws JshException {
        if(applicationArguments.isEmpty() && inputStream == null) {
            throw new JshException("cat: missing input");
        }
    }

    @Override
    /**
     * Executes the cat application with the given arguments. Cat prints the contents of files or of the input stream. 
     * Exception thrown if a file path is invalid, a file does not exist or cat does not receive any input.
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that some Applications will use as input if the applicationArguments does not contain a file
     * @param outpustream          The stream to which the Application will write to
     * @throws JshException        The custom Exception that all Applications throw if an error occurs
     */
    public void execute(ArrayList<String> applicationArguments, InputStream inputStream, OutputStream outputStream) throws JshException {
        applicationArguments = Application.globArguments(applicationArguments, -1);
        checkArguments(applicationArguments, inputStream);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        
        if(applicationArguments.size() == 0) {
            readAndWrite(new Scanner(inputStream), writer);
        }
        else {
            for (String filePath : applicationArguments) {
                Scanner scanner;
                try {
                    scanner = new Scanner(FileSystem.getInstance().getFile(filePath));
                } catch (FileNotFoundException e) {
                    throw new JshException("cat: " + e.getMessage());
                }

                readAndWrite(scanner, writer);
            }
        }
    }
   
}