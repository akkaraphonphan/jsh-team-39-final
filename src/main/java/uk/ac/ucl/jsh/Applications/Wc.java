package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Jsh;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import uk.ac.ucl.jsh.Utilities.JshException;

/**
 * The Wc application that implements the Application interface
 */
public class Wc implements Application {
    /**
     * Total char count
     */
    private int charCount;
    /**
     * Total word count
     */
    private int wordCount;
    /**
     * Total line count
     */
    private int lineCount;

    /**
     * Function that checks wheter an argument is a valid flag or not
     * 
     * @param argument The argument that is being checked
     * @return         True if the flag is valid, false otherwise
     */
    private boolean isValidFlag(String argument) {
        if(!argument.startsWith("-")) {
            return false;
        }
        for(int i = 1; i < argument.length(); ++ i) {
            if(argument.charAt(i) != 'm' && argument.charAt(i) != 'w' && argument.charAt(i) != 'l') {
                return false;
            }
        }
      
        return true;
    }

    /**
     * Function used to update the current flags
     * 
     * @param argument A valid flag
     * @param flags    The array representing the current flags
     */
    private void updateFlags(String argument, int[] flags) {
        if(argument.contains("m")) {
            flags[0] = 1;
        }
        if(argument.contains("w")) {
            flags[1] = 1;
        }
        if(argument.contains("l")) {
            flags[2] = 1;
        }
    }

    /**
     * The function that checks the arguments passed to the Wc application and generated the flags and the fileNames arrays
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that will be used as input if the applicationArguments does not contain a file
     * @param flags                The array representing which flags are currently being used
     * @param fileName             The array holding the name of the files given as paramaters
     * @throws JshException        The exception thrown if the given arguments are invalid
     */
    private void checkArguments(ArrayList<String> applicationArguments, InputStream inputStream, int[] flags, ArrayList<String> fileNames) throws JshException {
        for(String argument: applicationArguments) {
            if(isValidFlag(argument)) {
                updateFlags(argument, flags);
            }
            else {
                fileNames.add(argument);
            }
        }
        if(fileNames.size() == 0 && inputStream == null) {
            throw new JshException("wc: missing input");
        }
    }

   /**
    * Function that reads a file or an input stream and gets its word count, line count and char count 
    *
    * @param reader         The object used to read the file or the input stream
    * @throws JshException  Exception thrown if the reader cannot read the input
    */
    private void solveForInput(BufferedReader reader) throws JshException {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            int currentInt = reader.read();
            while (currentInt != -1) {
                char currentChar = (char)currentInt;
                stringBuilder.append(currentChar);
                currentInt = reader.read();
            }

            String str = stringBuilder.toString();
            charCount += str.length();
            String str_aux = str;
            lineCount += str.length() - str_aux.replaceAll(Jsh.lineSeparator, "").length();
            wordCount += str.split("\\s+").length;
        } catch (IOException e) {
            throw new JshException("wc: cannot read input");
        }
    }

    /**
     * Utlity function used to reset the total count after each execution of Wc
     * 
     */
    private void resetCount() {
        charCount = lineCount = wordCount = 0;
    }

    @Override
    /**
     * Executes the Wc application with the given arguments. Wc by default prints the number of bytes, words, and lines in given file or input stream. 
     * If no flags are used the default behaviour is expected. If any flags are used then only values corresponding to the used flags will be printed. 
     * The behaviours for flags are as follows: -m: character count, -l: line count, -w: word count
     * Exception thrown if the arguments are invalid or if the writer fails to write to the outputstream.
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that some Applications will use as input if the applicationArguments does not contain a file
     * @param outpustream          The stream to which the Application will write to
     * @throws JshException        The custom Exception that all Applications throw if an error occurs
     */
    public void execute(ArrayList<String> applicationArguments, InputStream inputStream, OutputStream outputStream) throws JshException {
        resetCount();
        int[] flags = new int[] {0,0,0};  //  indexes correspond to {m, w, l}
        ArrayList<String> fileNames = new ArrayList<>();
        applicationArguments = Application.globArguments(applicationArguments, -1);
        checkArguments(applicationArguments, inputStream, flags, fileNames);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        if(fileNames.size() == 0) {
            solveForInput(new BufferedReader(new InputStreamReader(inputStream)));
        }
        else {
            for(String fileName: fileNames) {
                File currFile = FileSystem.getInstance().getFile(fileName);
                if (currFile.exists()) {
                    if(currFile.isFile()) { 
                        try (BufferedReader reader = Files.newBufferedReader(Paths.get(currFile.getPath()), StandardCharsets.UTF_8)) {
                            solveForInput(reader);
                        } 
                        catch (IOException e) {
                            throw new JshException("wc: cannot open " + fileName);
                        }
                    } 
                    else {
                        throw new JshException("wc: " + fileName + " is a directory");
                    }
                } 
                else {
                    throw new JshException("wc: file does not exist");
                }
            }
        }
        try {
            if(flags[0] == 0 && flags[1] == 0 && flags[2] == 0) {
                writer.write(lineCount + " " + wordCount + " " + charCount + " ");
            }
            else {
                if(flags[2] == 1) {
                    writer.write(lineCount + " ");
                } 
                if(flags[1] == 1) {
                    writer.write(wordCount + " ");
                }
                if(flags[0] == 1) {
                    writer.write(charCount + " ");
                } 
            } 
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        } catch(IOException e) {
            throw new JshException("wc: cannot write output");
        }
    }
}