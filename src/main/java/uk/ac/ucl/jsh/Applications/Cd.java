package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
 
/**
 * The Cd application that implements the Application interface
 */
public class Cd implements Application {
    /**
    * The function that checks the arguments passed to the Cd application
    * 
    * @param applicationArguments The arguments of the Application
    * @throws JshException        The exception thrown if the Cd application receives no arguments or more than one argument
    */
    private void checkArguments(ArrayList<String> applicationArguments) throws JshException {
        if (applicationArguments.isEmpty()) {
            throw new JshException("cd: missing argument");
        } else if (applicationArguments.size() > 1) {
            throw new JshException("cd: too many arguments");
        }
    }

    @Override
    /**
     * Executes the Cd application with the given arguments. Cd changes the current directory to the path given as input.
     * Exception thrown if the given path is invalid (i.e. the path is to a file, or to a non-existant directory).
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that some Applications will use as input if the applicationArguments does not contain a file
     * @param outpustream          The stream to which the Application will write to
     * @throws JshException        The custom Exception that all Applications throw if an error occurs
     */
    public void execute(ArrayList<String> applicationArguments, InputStream inputStream, OutputStream outputStream) throws JshException {
        applicationArguments = Application.globArguments(applicationArguments, -1);
        checkArguments(applicationArguments);
        
        String dirString = applicationArguments.get(0);
        File dir = FileSystem.getInstance().getFile(dirString);
        if (!dir.isDirectory()) {
            throw new JshException("cd: " + dirString + " is not an existing directory");
        }
        
        String currentDirectoryPath;
        try {
            currentDirectoryPath = dir.getCanonicalPath();
        } catch (IOException e) {
            throw new JshException("cd: could not get path");
        }

        FileSystem.getInstance().setWorkingDirectory(currentDirectoryPath);
    }

}