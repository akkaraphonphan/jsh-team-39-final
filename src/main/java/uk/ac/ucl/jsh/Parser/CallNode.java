package uk.ac.ucl.jsh.Parser;

import java.io.InputStream;
import java.io.OutputStream;

import uk.ac.ucl.jsh.Utilities.JshException;
import uk.ac.ucl.jsh.Utilities.TreeVisitor;

/**
 * CallNode is a concrete type of Node, extending the Node abstract class.
 * It contains one application name along with its arguments.
 */
public class CallNode extends Node {
    /**
     * The string representing the application name and its arguments
     */
    private String applicationString;

    /**
     * Constructs an instance of a CallNode, encapsulating a String 
     * 
     * @param applicationString  String representing the application name and its arguments
     */
    public CallNode(String applicationString) {
        this.applicationString = applicationString;
    }

    /**
     * Getter function for the applicationString
     * 
     * @return  The applicationString
     */
    public String getApplicationString() {
        return applicationString;
    }

    /**
     * Implementation of the accept function used for the Visitor Pattern
     * 
     */
    public <T> T accept(TreeVisitor<T> treeVisitor, InputStream inputStream, OutputStream outputStream) throws JshException {
        return treeVisitor.visit(this, inputStream, outputStream);
    }
}