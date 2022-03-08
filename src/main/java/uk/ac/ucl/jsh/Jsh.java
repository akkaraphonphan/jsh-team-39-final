package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.Parser.Node;
import uk.ac.ucl.jsh.Parser.Parser;
import uk.ac.ucl.jsh.Utilities.*;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Class that encapsulates the Jsh Terminal
 */
public class Jsh {
    /**
     * Utility string that ensures that the paths created by the find function are not system-dependent
     */
    public final static String fileSeparator = System.getProperty("file.separator");
    /**
     * Utility string that ensures that the output of applications is not system-dependent
     */
    public final static String lineSeparator = System.getProperty("line.separator");
    /**
     * ArrayList holding the history of commands, used by the History Application
     */
    private static ArrayList<String> history = new ArrayList<>();

    /**
     * Getter function for the history ArrayList
     * 
     * @return  The Arraylist representing the history of commands
     */
    public static ArrayList<String> getHistory() {
        return history;
    }

    /**
     * Utility function used to clear the commands history
     */
    public static void clearHistory() {
        history.clear();
    }

    /**
     * Function that evaluates the command line given as argument and writes it to the output stream
     * 
     * @param cmdline       String representing the command line
     * @param outputStream  Output stream used to write to
     */
    public static void eval(String cmdline, OutputStream outputStream) {
        Node cmdTree = Parser.parserCmdLine(cmdline);
        try {
            cmdTree.accept(new EvalVisitor(), null, outputStream);
        } catch (JshException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     *The main function which makes the appropriate calls for Jsh to operate
     * and that loops continually until exited.
     * 
     * @param args The arguments that are passed in after running the Jsh based on which the Jsh runs in interactive mode or not
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args.length != 2) {
                System.err.println("jsh: wrong number of arguments");
                return;
            }
            if (!args[0].equals("-c")) {
                System.err.println("jsh: " + args[0] + ": unexpected argument");
                return;
            }
            
            try {
                eval(args[1], System.out);
            } catch (Exception e) {
                System.err.println("jsh: " + e.getMessage());
            }
        } else {
            System.out.println("Hello World!");
            Scanner input = new Scanner(System.in);
            try {
                while (true) {
                    String prompt = FileSystem.getInstance().getWorkingDirectoryPath() + "> ";
                    System.out.print(prompt);
                    
                    String cmdline = input.nextLine();
                    history.add(cmdline);
                    try {
                        eval(cmdline, System.out); 
                    } catch (Exception e) {
                        System.err.println("jsh: " + e.getMessage());
                    }
                }
            } finally {
                input.close();
            }
        }
    }

}
