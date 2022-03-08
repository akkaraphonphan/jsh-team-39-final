package uk.ac.ucl.jsh.Utilities;

/**
 * Custom Exception Class that all Applications throw
 */
public class JshException extends Exception {
    private static final long serialVersionUID = -1517441780643449892L;

    /**
     * Constructs an instance of a JshException and takes in a String
     * 
     * @param errorMessage A string representing the error message that the exception will contain
     */
    public JshException(String errorMessage) {
        super(errorMessage);
    }
}