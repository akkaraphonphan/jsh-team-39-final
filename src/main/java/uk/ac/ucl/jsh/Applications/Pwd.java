package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * The Pwd application that implements the Application interface
 */
public class Pwd implements Application {
    /**
    * The function that checks the arguments passed to the Pwd application
    * 
    * @param applicationArguments The arguments of the Application
    * @throws JshException        The exception thrown if the Pwd application receives any argument
    */
    private void checkArguments(ArrayList<String> applicationArguments) throws JshException {
        if(!applicationArguments.isEmpty()) {
            throw new JshException("pwd: too many arguments");
        }
    }

    @Override
    /**
     * Executes the Pwd application with the given arguments. Pwd outputs the current working directory followed by a newline
     * Exception thrown if the application receives any argguments or if the writer fails to write to the outputstream.
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
       try {
            writer.write(FileSystem.getInstance().getWorkingDirectoryPath() + Jsh.lineSeparator);
            writer.flush();
       } catch (IOException e) {
           throw new JshException("pwd: cannot write output");
       }
    }

}