package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The Find application that implements the Application interface
 */
public class Find implements Application {
    /**
     * Utility string that ensures that the paths created by the find function are not system-dependent
     */
    private String fileSeparator = Jsh.fileSeparator;
    /**
     * Utility string that ensures that the output of the find function is not system-dependent
     */
    private String lineSeparator = Jsh.lineSeparator;
    /**
     * The object that will contain the pattern given as argument and will try to match the filenames to it.
     */
    private PathMatcher matcher;
    /**
     * The object that will deal with writing to the outputstream
     */
    private OutputStreamWriter writer;

    /**
     * The find method that recursively searches from a given directory for files that match the given pattern
     *  
     * @param currentDirectoryPath The current directory in which the search function is at the moment which is an absolute path
     * @param currentResolvedPath  The resolved path of the current directory which is relative to the given directory
     * @throws IOException         Expcetion thrown if the writer cannot write to the output stream
     */
    private void find(String currentDirectoryPath, String currentResolvedPath) throws IOException {
        File currentFile = new File(currentDirectoryPath);
        File[] fileArray = currentFile.listFiles();
        for(File file: fileArray) {
            String currentFilePath = currentDirectoryPath + fileSeparator + file.getName();
            if(file.isFile() && matcher.matches(Paths.get(file.getName()))) {
                writer.write(currentResolvedPath + fileSeparator + file.getName() + lineSeparator);
                writer.flush();
            }
            if(file.isDirectory()) {
                find(currentFilePath, currentResolvedPath + fileSeparator + file.getName());
            }
        }
    }

    /**
     * The function that checks the arguments passed to the Find application
     * 
     * @param applicationArguments The arguments of the Application
     * @throws JshException        The exception thrown if the given arguments are invalid
     */
    private void checkArguments(ArrayList<String> applicationArguments) throws JshException {
        if(applicationArguments.size() < 2) {
            throw new JshException("find: missing arguments");
        }
        if(applicationArguments.size() == 2 && applicationArguments.get(0).compareTo("-name") != 0) {
            throw new JshException("find: wrong argument");
        }
        if(applicationArguments.size() == 3) {
            File rootSearchDirectory = FileSystem.getInstance().getFile(applicationArguments.get(0));
        
            if(!rootSearchDirectory.isDirectory()) {
                throw new JshException("find: could not open " + applicationArguments.get(0));
            }
            if(applicationArguments.get(1).compareTo("-name") != 0) {
                throw new JshException("find: invalid argument " + applicationArguments.get(1));
            }
        }
        if(applicationArguments.size() > 3) {
            throw new JshException("find: too many arguments");
        }
    }

    @Override
     /**
     * Executes the Find application with the given arguments. Find recursively searches for files that match a pattern given as argument, starting 
     * from a directory given as an argument, or, if no such directory is given, starting at the current working directory.
     * Exception thrown if the arguments are invalid or if the writer fails to write to the outputstream.
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that some Applications will use as input if the applicationArguments does not contain a file
     * @param outpustream          The stream to which the Application will write to
     * @throws JshException        The custom Exception that all Applications throw if an error occurs
     */
    public void execute(ArrayList<String> applicationArguments, InputStream inputStream, OutputStream outputStream) throws JshException {
        applicationArguments = Application.globArguments(applicationArguments, applicationArguments.size() - 1);
        checkArguments(applicationArguments);
        writer = new OutputStreamWriter(outputStream);
        
        String searchRootDirectory;
        String resolvedPath;
        if(applicationArguments.size() == 2) {
            searchRootDirectory = FileSystem.getInstance().getWorkingDirectoryPath();
            resolvedPath = ".";
        }
        else {
            searchRootDirectory = FileSystem.getInstance().getFilePath(applicationArguments.get(0));
            resolvedPath = applicationArguments.get(0);
        }

        matcher = FileSystems.getDefault().getPathMatcher("glob:" + applicationArguments.get(applicationArguments.size() - 1));
        try {
            find(searchRootDirectory, resolvedPath);
        } catch (IOException e) {
            throw new JshException("find: " + e.getMessage());
        }
    }
}