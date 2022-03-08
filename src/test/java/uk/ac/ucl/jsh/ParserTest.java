package uk.ac.ucl.jsh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import uk.ac.ucl.jsh.Parser.CallNode;
import uk.ac.ucl.jsh.Parser.Node;
import uk.ac.ucl.jsh.Parser.Parser;
import uk.ac.ucl.jsh.Parser.PipeNode;
import uk.ac.ucl.jsh.Parser.SeqNode;

public class ParserTest {
    @Test
    public void testUnquotedCallCommand() {
        String callCommand = "   a    bc def    ";
        ArrayList<String> expectedTokens = new ArrayList<>(Arrays.asList("a", "bc", "def"));
        ArrayList<String> actualTokens = Parser.parseCallCommand(callCommand);
        assertTrue(expectedTokens.equals(actualTokens));
    }

    @Test
    public void testSingleQuotedCallCommand() {
        String callCommand = "  a a'bc `echo def`'  ";
        ArrayList<String> expectedTokens = new ArrayList<>(Arrays.asList("a", "abc `echo def`"));
        ArrayList<String> actualTokens = Parser.parseCallCommand(callCommand);
        assertTrue(expectedTokens.equals(actualTokens));
    }

    @Test
    public void testDoubleQuotedCallCommand() {
        String callCommand = "  a \"bcdef\" \"ghi `echo jkl`\" ";
        ArrayList<String> expectedTokens = new ArrayList<>(Arrays.asList("a", "bcdef", "ghi jkl"));
        ArrayList<String> actualTokens = Parser.parseCallCommand(callCommand);
        assertTrue(expectedTokens.equals(actualTokens));
    }

    @Test
    public void testBackQuotedCallCommand() {
        String callCommand = "  a `echo mno`   ";
        ArrayList<String> expectedTokens = new ArrayList<>(Arrays.asList("a", "mno"));
        ArrayList<String> actualTokens = Parser.parseCallCommand(callCommand);
        assertTrue(expectedTokens.equals(actualTokens));
    }

    @Test
    public void testEmptyBackQuotedCallCommand() {
        String callCommand = "  a ``   ";
        ArrayList<String> expectedTokens = new ArrayList<>(Arrays.asList("a", ""));
        ArrayList<String> actualTokens = Parser.parseCallCommand(callCommand);
        assertTrue(expectedTokens.equals(actualTokens));
    }

    @Test
    public void testIORedirectionCallCommand() {
        String callCommand = "a < b > c";
        ArrayList<String> expectedTokens = new ArrayList<>(Arrays.asList("a", "<", "b", ">", "c"));
        ArrayList<String> actualTokens = Parser.parseCallCommand(callCommand);
        assertTrue(expectedTokens.equals(actualTokens));
    }

    @Test
    public void testCallCmdLine() {
        String cmdLine = "a bc'def' \"ghi\" `jkl`";
        Node expectedTree = new CallNode(cmdLine);
        Node actualTree = Parser.parserCmdLine(cmdLine);
        assertEquals(expectedTree.toString(), actualTree.toString());
    }

    @Test
    public void testPipeCmdLine() {
        String cmdLine = "a | b";
        Node expectedTree = new PipeNode(new CallNode("a "), new CallNode(" b"));
        Node actualTree = Parser.parserCmdLine(cmdLine);
        assertEquals(expectedTree.toString(), actualTree.toString());
    }

    @Test
    public void testSeqCmdLine() {
        String cmdLine = "a ; b";
        Node expectedTree = new SeqNode(new CallNode("a "), new CallNode(" b"));
        Node actualTree = Parser.parserCmdLine(cmdLine);
        assertEquals(expectedTree.toString(), actualTree.toString());
    }

    @Test
    public void testCmdLine() {
        String cmdLine = "a|b|c;d;e";
        Node expectedTree = new SeqNode(
                                new SeqNode(
                                    new PipeNode(
                                        new PipeNode(new CallNode("a"), new CallNode("b")), 
                                        new CallNode("c")),
                                    new CallNode("d")),
                                new CallNode("e"));

        Node actualTree = Parser.parserCmdLine(cmdLine);
        assertEquals(expectedTree.toString(), actualTree.toString());
    }
}