package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * The Ls application that implements the Application interface
 */
public class Ls implements Application {
    /**
    * The function that checks the arguments passed to the Ls application
    * 
    * @param applicationArguments The arguments of the Application
    * @throws JshException        The exception thrown if the Ls application receives no arguments or more than one argument
    */
    private void checkArguments(ArrayList<String> applicationArguments) throws JshException {
        if(applicationArguments.size() > 1) {
            throw new JshException("ls: too many arguments");
        }
    }

    @Override
    /**
     * Executes the Ls application with the given arguments. Ls prints Prints a list of files and directories in a given directory, separated by 
     * tabs and followed by a newline. Ignores files and directories whose names start with “.”.
     * Exception thrown if the given argument is invalid (i.e. is path is to a file, or to a non-existant directory) or if the writer 
     * fails to write to the outputstream.
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that some Applications will use as input if the applicationArguments does not contain a file
     * @param outpustream          The stream to which the Application will write to
     * @throws JshException        The custom Exception that all Applications throw if an error occurs
     */
    public void execute(ArrayList<String> applicationArguments, InputStream inputStream, OutputStream outputStream) throws JshException {
        applicationArguments = Application.globArguments(applicationArguments, -1);
        checkArguments(applicationArguments);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        File currDir;
        if (applicationArguments.isEmpty()) {
            currDir = FileSystem.getInstance().getFile(FileSystem.getInstance().getWorkingDirectoryPath());
        } 
        else {
            currDir = FileSystem.getInstance().getFile(applicationArguments.get(0));
        }

        try {
            File[] listOfFiles = currDir.listFiles();
            boolean atLeastOnePrinted = false;
            try {
                int index = 0;
                for (File file : listOfFiles) {
                    if (!file.getName().startsWith(".")) {
                        writer.write(file.getName());
                        if(index + 1 < listOfFiles.length) {
                            writer.write("\t");
                        }
                        writer.flush();
                        atLeastOnePrinted = true;
                    }

                    index += 1;
                }    

                if (atLeastOnePrinted) {
                    writer.write(Jsh.lineSeparator);
                    writer.flush();
                } 
            } catch (IOException e) {
                throw new JshException("ls: " + e.getMessage());
            }
        } catch (NullPointerException e) {
            throw new JshException("ls: " + e.getMessage());
        }
    }

}