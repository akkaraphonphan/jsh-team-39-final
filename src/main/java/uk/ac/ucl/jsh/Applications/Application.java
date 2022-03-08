package uk.ac.ucl.jsh.Applications;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import uk.ac.ucl.jsh.Utilities.Globbing;
import uk.ac.ucl.jsh.Utilities.JshException;

/**
 * The Application interface that all Applications implement
 */
public interface Application {
    /**
     * Executes the Application, providing support for IO redirection and piping through the inputStream and outputStream parameters.
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that some Applications will use as input if the applicationArguments does not contain a file
     * @param outpustream          The stream to which the Application will write to
     * @throws JshException        The custom Exception that all Applications throw if an error occurs
     */
    void execute(ArrayList<String> applicationArguments, InputStream inputStream, OutputStream outpustream) throws JshException;

    /**
     * Provides access for all the Applications to the Utilities class Globbing, which performs argument globbing.
     * 
     * @param applicationArguments The list of arguments before perfomming globbing
     * @param ignoreIndex          The index of the argument in the applicationArguments that will not be globbed and will be added as it is
     * @return                     An ArrayList of Strings containing the globbing result
     */
    static ArrayList<String> globArguments(ArrayList<String> applicationArguments, int ignoreIndex) {
        return Globbing.globArguments(applicationArguments, ignoreIndex);
    }
}