package uk.ac.ucl.jsh.Parser;

import java.util.ArrayList;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import uk.ac.ucl.jsh.antlr.CallParser.CallParserLexer;
import uk.ac.ucl.jsh.antlr.CallParser.CallParserParser;
import uk.ac.ucl.jsh.antlr.CmdLineParser.CmdLineParserLexer;
import uk.ac.ucl.jsh.antlr.CmdLineParser.CmdLineParserParser;

/**
 * Parser class that deals with Parsing the input from the user and creating a Parse tree based on that input
 */
public class Parser {
    /**
     * Function that returns the Parse tree of the current input line 
     * 
     * @param cmdLine The current input line
     * @return        A node representing the Root of the Parse tree
     */
    public static Node parserCmdLine(String cmdLine) {
        CmdLineParserLexer parserLexer = new CmdLineParserLexer(CharStreams.fromString(cmdLine));
        CmdLineParserParser parserParser = new CmdLineParserParser(new CommonTokenStream(parserLexer));
        CmdLineParserParser.CompileUnitContext compileUnit = parserParser.compileUnit();
        return new BuildCmdTree().visitCompileUnit(compileUnit); 
    }

    /**
     * Function that returns the application name and its arguments from a call command (which is part of a command line)
     * 
     * @param callCommand The call command
     * @return            An ArrayList representing the application name and its arguments
     */
    public static ArrayList<String> parseCallCommand(String callCommand) {
        CallParserLexer parserLexer = new CallParserLexer(CharStreams.fromString(callCommand));
        CallParserParser parserParser = new CallParserParser(new CommonTokenStream(parserLexer));
        CallParserParser.CompileUnitContext compileUnit = parserParser.compileUnit();
        ArrayList<String> tokens = new BuildCallCommand().visitCompileUnit(compileUnit);
        return tokens;
    }
}