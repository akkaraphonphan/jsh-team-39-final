package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.regex.Matcher;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Grep application that implements the Application interface
 */
public class Grep implements Application {
    /**
     * The function that reads the input, tries to match the current read line to the pattern given as argument and writes the lines that match
     * the given pattern to the output stream
     * 
     * @param scanner       The scanner object used to read input from file or from inputstream
     * @param writer        The writer object used to write to the outputstream
     * @param pattern       The pattern given as argument to the Grep Application
     * @throws JshException The exception thrown if the writer fails to write to the outputstream
     */
    private void readAndMatch(Scanner scanner, OutputStreamWriter writer, Pattern pattern) throws JshException {
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    writer.write(line + Jsh.lineSeparator);
                    writer.flush();
                }
            }
            scanner.close();
        } catch (IOException e) {
            scanner.close();
            throw new JshException("grep: " + e.getMessage());
        }
    }

    /**
     * The function that checks the arguments passed to the Grep application, throwing an exception if there is no pattern given
     * or if there is no input 
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that will be used as input if the applicationArguments does not contain a file
     * @throws JshException        The exception thrown if there is no pattern given as argument or if there is no input
     */
    private void checkArguments(ArrayList<String> applicationArguments, InputStream inputStream) throws JshException {
        if (applicationArguments.isEmpty()) {
            throw new JshException("grep: missing arguments");
        }
        if (applicationArguments.size() == 1 && inputStream == null) {
            throw new JshException("grep: missing input");
        }
    }

    @Override
    /**
     * Executes the Grep application with the given arguments. Grep searches for lines containing a match to the specified pattern. The output 
     * of the application is the list of the lines. Each line is printed followed by a newline.
     * Exception thrown if the arguments are invalid or if the writer fails to write to the outputstream.
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that some Applications will use as input if the applicationArguments does not contain a file
     * @param outpustream          The stream to which the Application will write to
     * @throws JshException        The custom Exception that all Applications throw if an error occurs
     */
    public void execute(ArrayList<String> applicationArguments, InputStream inputStream, OutputStream outputStream) throws JshException {
        applicationArguments = Application.globArguments(applicationArguments, 0);
        checkArguments(applicationArguments, inputStream);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        Pattern grepPattern;
        try {
            grepPattern = Pattern.compile(applicationArguments.get(0));
        } catch (PatternSyntaxException e) {
            throw new JshException("grep: " + e.getMessage());
        } 

        if (applicationArguments.size() > 1) {
            for (int i = 1; i < applicationArguments.size(); ++i) {
                String filePath = applicationArguments.get(i);
                Scanner scanner;
                try {
                    scanner = new Scanner(FileSystem.getInstance().getFile(filePath));
                } catch (FileNotFoundException e) {
                    throw new JshException("grep: " + e.getMessage());
                }
                readAndMatch(scanner, writer, grepPattern);
            }
        }
        else {
            readAndMatch(new Scanner(inputStream), writer, grepPattern);
        }
    }

}