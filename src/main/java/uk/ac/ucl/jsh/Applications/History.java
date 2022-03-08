package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.Utilities.JshException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * The History application that implements the Application interface
 */
public class History implements Application {
    /**
     * The function that creates a line of the history command
     * 
     * @param index          The index in the Jsh.history array
     * @param historyElement The element at that index
     * @return               A string representing a line of the history output
     */
    private String indexHistoryElement(int index, String historyElement) {
        return Integer.toString(index) + ". " + historyElement;
    }

      /**
     * The function that checks the arguments passed to the History application
     * 
     * @param applicationArguments The arguments of the Application
     * @throws JshException        The exception thrown if there is more than one argument given
     */
    private void checkArguments(ArrayList<String> applicationArguments) throws JshException {
        if (applicationArguments.size() > 1) {
            throw new JshException("history: too many arguments");
        }
    } 

    @Override
    /**
     * Executes the History application with the given arguments. History prints the last numberOfElementsToPrint commands, where numberOfElementsToPrint
     * is an argument given to the application. If no argument is given, then it prints up to the last 500 commands.
     * Exception thrown if the arguments are invalid or if the writer fails to write to the outputstream.
     * 
     * @param applicationArguments The arguments of the Application
     * @param inputStream          The stream that some Applications will use as input if the applicationArguments does not contain a file
     * @param outpustream          The stream to which the Application will write to
     * @throws JshException        The custom Exception that all Applications throw if an error occurs
     */
    public void execute(ArrayList<String> applicationArguments, InputStream inputStream, OutputStream outputStream) throws JshException {

        checkArguments(applicationArguments);
        ArrayList<String> historyToPrint = new ArrayList<>();
        ArrayList<String> history = Jsh.getHistory();
        int numberOfElementsToPrint;

        if (applicationArguments.isEmpty()) {
            numberOfElementsToPrint = history.size();
        }
        else {
            numberOfElementsToPrint = Integer.parseInt(applicationArguments.get(0));
            if (numberOfElementsToPrint > history.size()) {
                numberOfElementsToPrint = history.size();
            }
        }

        if (numberOfElementsToPrint > 500) {
            numberOfElementsToPrint = 500;
        }

        if (numberOfElementsToPrint < 0) {
            throw new JshException("history: invalid option");
        }

        for (int index = history.size() - numberOfElementsToPrint; index < history.size(); ++index) {
            historyToPrint.add(indexHistoryElement(index + 1, history.get(index)));
        }
        
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        try {
            for (String historyElement: historyToPrint) {
                writer.write(historyElement + Jsh.lineSeparator);
                writer.flush();
            }
        } catch (IOException e) {
            throw new JshException("history: cannot write output");
        }
    }
}