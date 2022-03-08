package uk.ac.ucl.jsh.Applications;

import java.io.OutputStreamWriter;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The Sed application that implements the Application interface
 */
public class Sed implements Application {
    /**
     * The regex String given as argument
     */
    private String regex;
    /**
     * The replacement String given as argument
     */
    private String replacement;

   /**
    * The function that checks if the first argument of Sed is valid and splits it into its components(i.e. regex and replacement)
    *
    * @param argument The first argument of the Sed Application
    * @return         A boolean representing if true if the first argument is valid and false otherwise
    */
    private boolean isValid(String argument) { 
        if (argument.length() < 5) {
            return false;
        }

        if (argument.charAt(0) != 's') {
            return false;
        }

        char delimiter = argument.charAt(1);
        int delimiterFrequency = (int)argument.substring(1).codePoints().filter(ch -> ch == delimiter).count();

        if (delimiterFrequency == 4 && delimiter != 'g') {
            return false;
        }
        else if (delimiterFrequency != 3 && delimiterFrequency != 4) {
            return false;
        }

        if (argument.charAt(argument.length()-1) != delimiter && argument.charAt(argument.length()-1) != 'g') {
            return false;
        }

        int firstDelimiterIndex = 1;
        int secondDelimiterIndex = argument.indexOf(delimiter, firstDelimiterIndex + 1);
        int thirdDelimiterIndex = argument.indexOf(delimiter, secondDelimiterIndex + 1);

        if (thirdDelimiterIndex != argument.length()-1 && thirdDelimiterIndex != argument.length()-2) {
            return false;
        }

        regex = argument.substring(firstDelimiterIndex + 1, secondDelimiterIndex);
        replacement = argument.substring(secondDelimiterIndex + 1, thirdDelimiterIndex);

        if(regex.compareTo("") == 0) {
            return false;
        }

        return true;
    }

    /**
     * The function that checks the arguments passed to the Sed application
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that will be used as input if the applicationArguments does not contain a file
     * @throws JshException        The exception thrown if the given arguments are invalid
     */
    private void checkArguments(ArrayList<String> applicationArguments, InputStream inputStream) throws JshException {
        int numberOfArguments = applicationArguments.size();
        if (numberOfArguments <= 0) {
            throw new JshException("sed: missing arguments");
        }   

        if (numberOfArguments > 2){
            throw new JshException("sed: too many arguments");
        }

        if (numberOfArguments == 1 && inputStream == null) {
            throw new JshException("sed: missing input");
        }

        if(isValid(applicationArguments.get(0)) == false) {
            throw new JshException("sed: invalid first argument");
        } 
    }

    @Override
    /**
     * Executes the Sed application with the given arguments. Sed copies the content of a given file or inputstream and writes it to the outputstream 
     * after performing string replacement of a sequence that matches the regex given as argument with the replacement argument. By default it only does 
     * so once per line, but if the 'g' character is used, it replaces all the sequences that match the given regex.
     * Exception thrown if the given arguments are invalid  or if the writer fails to write to the outputstream.
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

        boolean replaceAll = false;
        if(applicationArguments.get(0).endsWith("g")) {
            replaceAll = true;
        }

        Scanner scanner;
        if(applicationArguments.size() == 2){
            String filePath = applicationArguments.get(1);
            try {
                scanner = new Scanner(FileSystem.getInstance().getFile(filePath));
            } catch (FileNotFoundException e) {
                throw new JshException("sed: " + e.getMessage());
            }
        }
        else {
            scanner = new Scanner(inputStream);
        }

        try {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(replaceAll == true) {
                    writer.write(line.replaceAll(regex, replacement) + Jsh.lineSeparator);
                }
                else {
                    writer.write(line.replaceFirst(regex, replacement) + Jsh.lineSeparator);
                }
                writer.flush();
            }

            scanner.close();
        } catch (IOException e) {
            scanner.close();
            throw new JshException("sed: " + e.getMessage());
        }
    }

}