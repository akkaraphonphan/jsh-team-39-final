package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.OutputStreamWriter;

/**
 * The Tail application that implements the Application interface
 */
public class Tail implements Application {
     /**
     * The number of lines that Tail will print
     */
    private int tailLines;

     /**
     * The function that reads the input and writes to the outputstream the first tailLines number of lines.
     * 
     * @param scanner       The object that reads the input either from a file or from the inputstream
     * @param writer        The object used to write to the outputstream
     * @throws JshException The exception thrown if the writer failes to write to the ouptustream
     */
    private void readAndWrite(Scanner scanner, OutputStreamWriter writer) throws JshException {
        ArrayList<String> storage = new ArrayList<>();
        try {
            while (scanner.hasNextLine()) {
                storage.add(scanner.nextLine());
            }

            int index = 0;
            if (tailLines > storage.size()) {
                index = 0;
            } else {
                index = storage.size() - tailLines;
            }
            for (int i = index; i < storage.size(); i++) {
                writer.write(storage.get(i) + Jsh.lineSeparator);
                writer.flush();
            }     
            scanner.close();
        } catch (IOException e) {
            scanner.close();
            throw new JshException("tail: " + e.getMessage());
        }       
    }
    
     /**
     * The function that checks the arguments passed to the Tail application
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that will be used as input if the applicationArguments does not contain a file
     * @throws JshException        The exception thrown if the given arguments are invalid
     */
    private void checkArguments(ArrayList<String> applicationArguments, InputStream inputStream) throws JshException {
        if (applicationArguments.isEmpty() && inputStream == null)  {
            throw new JshException("tail: missing input");
        }
        if (applicationArguments.size() > 3) {
            throw new JshException("tail: too many arguments");
        }
        if (applicationArguments.size() > 1 && !applicationArguments.get(0).equals("-n")) {
            throw new JshException("tail: wrong argument " + applicationArguments.get(0));
        }
        if (applicationArguments.size() == 2 && inputStream == null) {
            throw new JshException("tail: missing input");
        }
    }

    @Override
    /**
     * Executes the Tail application with the given arguments. Tail prints the last tailLines lines of the file (or input stream), where tailLines is an
     * argument received by the Tail application. The default value for tailLines is 10. If there are less than tailLines lines then the application prints
     * all the lines without raising an exception.
     * Exception thrown if the arguments are invalid or if the writer fails to write to the outputstream.
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

        tailLines = 10;
        if (applicationArguments.size() > 1) {
            try {
                tailLines = Integer.parseInt(applicationArguments.get(1));
                if (tailLines <= 0) {
                    throw new JshException("tail: illegal line count -- " + tailLines);
                }
            } catch (NumberFormatException e) {
                throw new JshException("tail: " + e.getMessage());
            }
        }

        if(applicationArguments.size() == 1 || applicationArguments.size() == 3) {
            String filePath = applicationArguments.get(applicationArguments.size() - 1);
                
            Scanner scanner;
            try {
                scanner = new Scanner(FileSystem.getInstance().getFile(filePath));
            } catch (FileNotFoundException e) {
                throw new JshException("tail: " + e.getMessage());
            }

            readAndWrite(scanner, writer);
        }
        else {
            readAndWrite(new Scanner(inputStream), writer);
        }
    }
    
}