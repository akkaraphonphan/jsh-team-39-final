package uk.ac.ucl.jsh.Utilities;

import uk.ac.ucl.jsh.Applications.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The ApplicationManager class that follows the Singleton pattern and deals with the logic of calling 
 * the right application based on the tokens returned by the Parser class after parsing a command line
 */
public final class ApplicationManager {
    /**
     * The reference to the single ApplicationManager instance
     */
    private static final ApplicationManager INSTANCE = new ApplicationManager();
    /**
     * The HashMap that stores pairs of the form <name, corresponding_application_instance>
     */
    private HashMap<String, Application> applicationMap;

    /**
     * Constructs the Single instance of ApplicationManager
     * 
     */
    private ApplicationManager() {
        applicationMap = new HashMap<>();
        createApplications();
    }
    
    /**
     * The function that populates the applicationMap by creating only one instance for every safe verion of an application
     * 
     */
    private  void createApplications() {
        applicationMap.put("pwd",     new Pwd());
        applicationMap.put("cd",      new Cd());
        applicationMap.put("ls",      new Ls());
        applicationMap.put("cat",     new Cat());
        applicationMap.put("echo",    new Echo());
        applicationMap.put("head",    new Head());
        applicationMap.put("tail",    new Tail());
        applicationMap.put("grep",    new Grep());
        applicationMap.put("sed",     new Sed());
        applicationMap.put("find",    new Find());
        applicationMap.put("history", new History());
        applicationMap.put("wc",      new Wc());
    }

    /**
     * Getter function for the ApplicationManager instance
     * 
     * @return The reference to the only instance of the ApplicationManager
     */
    public static ApplicationManager getInstance() {
        return INSTANCE;
    }

    /**
     * The function that decides what application to execute based on the tokens returned by the Parser class. It uses only one instance of every command for
     * all safe versions of applications and handles the unsafe applications by creating an instance of an UnsafeApplicationDecorator when needed.
     * 
     * @param tokens               The tokens returned by the Parser class, containing the application name and its arguments
     * @param inputStream          The stream that some Applications will use as input if the applicationArguments does not contain a file
     * @param outpustream          The stream to which the Application will write to
     * @throws JshException        The custom Exception that all Applications throw if an error occurs
     */
    public void executeApplication(ArrayList<String> tokens, InputStream inputStream, OutputStream outputStream) throws JshException {
        String applicationName = tokens.get(0).toLowerCase();
        boolean unsafeVersion = false;
        ArrayList<String> applicationArguments = new ArrayList<String>(tokens.subList(1, tokens.size()));
        
        if(applicationName.startsWith("_")) {
            applicationName = applicationName.subSequence(1, applicationName.length()).toString();
            unsafeVersion = true;
        }
        if(applicationMap.containsKey(applicationName)) {
            Application currentApplication = applicationMap.get(applicationName);
            if(unsafeVersion) {
                UnsafeApplicationDecorator unsafeApplication = new UnsafeApplicationDecorator(currentApplication);
                unsafeApplication.execute(applicationArguments, inputStream, outputStream);
            }
            else {
                currentApplication.execute(applicationArguments, inputStream, outputStream);
            }
        } 
        else {
            throw new JshException(applicationName + ": unknown application");
        }
    }
    
}