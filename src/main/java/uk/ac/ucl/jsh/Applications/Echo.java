package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Utilities.JshException;

import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * The Echo application that implements the Application interface
 */
public class Echo implements Application {
    @Override
    /**
     * Executes the Echo application with the given arguments. Echo writes the given arguments to the outputStream.
     * Exception thrown if the writer failes to write to the outputstream.
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that some Applications will use as input if the applicationArguments does not contain a file
     * @param outpustream          The stream to which the Application will write to
     * @throws JshException        The custom Exception that all Applications throw if an error occurs
     */
    public void execute(ArrayList<String> applicationArguments, InputStream inputStream, OutputStream outputStream) throws JshException{
        applicationArguments = Application.globArguments(applicationArguments, -1);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        int index = 0;
        try {
            for (String arg : applicationArguments) {
                writer.write(arg);
                if(index != applicationArguments.size() - 1) {
                    writer.write(" ");
                }
                writer.flush();
                index += 1;
            }
            
            writer.write(Jsh.lineSeparator);
            writer.flush();
        } catch (IOException e) {
            throw new JshException("echo: " + e.getMessage());
        }
    }

}