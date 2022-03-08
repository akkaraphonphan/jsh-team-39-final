package uk.ac.ucl.jsh.Parser;

import java.io.InputStream;
import java.io.OutputStream;

import uk.ac.ucl.jsh.Utilities.JshException;
import uk.ac.ucl.jsh.Utilities.TreeVisitor;

/**
 * SeqNode is a concrete type of Node, extending the Node abstract class.
 * It contains two children, parts of a Sequence operation.
 */
public class SeqNode extends Node {
    /**
     * The left children, representing the left part of the Pipe operation
     */
    private Node left;
    /**
     * The right children, representing the right part of the Pipe operation
     */
    private Node right; 

     /**
     * Constructs a new instance of a SeqNode, taking two components, the left child, and the right child
     * 
     * @param left  The left child
     * @param right The right child
     */
    public SeqNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Getter function for the left child
     * 
     * @return The left child
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Getter function for the right child
     * 
     * @return The right child
     */
    public Node getRight() {
        return right;
    }
    
    /**
     * Implementation of the accept function used for the Visitor Pattern
     * 
     */
    public <T> T accept(TreeVisitor<T> testVisitor, InputStream inputStream, OutputStream outputStream) throws JshException {
        return testVisitor.visit(this, inputStream, outputStream);
    }
}