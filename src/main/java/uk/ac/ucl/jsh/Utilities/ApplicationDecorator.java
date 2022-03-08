package uk.ac.ucl.jsh.Utilities;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import uk.ac.ucl.jsh.Applications.Application;

/**
 * The abstract ApplicationDecorator class that implements Application
 */
public abstract class ApplicationDecorator implements Application {
    /**
     * The encapsulated Application 
     */
    protected Application application;

    /**
     * Construct an ApplicationDecorator, taking in an Application
     * 
     * @param application The Application that the Decorator will encapsulate
     */
    public ApplicationDecorator(Application application) {
        this.application = application;
    }

    @Override
    /**
     * The function that will be extended by concrete decorators
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that some Applications will use as input if the applicationArguments does not contain a file
     * @param outpustream          The stream to which the Application will write to
     * @throws JshException        The custom Exception that all Applications throw if an error occurs
     */
    public void execute(ArrayList<String> applicationArguments, InputStream inputStream, OutputStream outpustream) throws JshException {
        this.application.execute(applicationArguments, inputStream, outpustream);
    }

}