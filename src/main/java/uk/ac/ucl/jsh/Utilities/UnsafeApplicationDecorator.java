package uk.ac.ucl.jsh.Utilities;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import uk.ac.ucl.jsh.Applications.Application;

/**
 * The concrete decorator UnsafeApplicationDecorator that extends the ApplicationDecorator class
 */
public class UnsafeApplicationDecorator extends ApplicationDecorator {
    /**
     * Constructing  an UnsafeApplicationDecorator, taking in an Application
     * 
     * @param application The Application that the Decorator will encapsulate
     */
    public UnsafeApplicationDecorator(Application application) {
        super(application);
    }

    @Override
    /**
     * The function that performs the main functionality of the UnsafeApplicationDecorator. It runs the execute method of the encapsulated Application,
     * catching the JshExpcetion it may throw and printing the error message to the System.err stream. This represents the enriched functionality that the 
     * unsafe version of Applications must provide.
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that some Applications will use as input if the applicationArguments does not contain a file
     * @param outpustream          The stream to which the Application will write to
     * @throws JshException        The custom Exception that all Applications throw if an error occurs
     */
    public void execute(ArrayList<String> applicationArguments, InputStream inputStream, OutputStream outpustream) throws JshException {
        try {
            this.application.execute(applicationArguments, inputStream, outpustream);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}   