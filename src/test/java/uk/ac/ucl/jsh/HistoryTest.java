package uk.ac.ucl.jsh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.ucl.jsh.Applications.History;
import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

public class HistoryTest {
    private static History historyApplication;
    private static FileSystem fileSystem;
    private static ByteArrayOutputStream outputStream;
    private static ArrayList<String> applicationArguments;

    private String lineSeparator = Jsh.lineSeparator;
    private String initialWorkingDirectoryPath;
    
    @BeforeClass
    public static void setClass() {
        applicationArguments = new ArrayList<>();
        fileSystem = FileSystem.getInstance();
        outputStream = new ByteArrayOutputStream();
        historyApplication = new History();
    }

    @Before
    // Create the test hierarchy
    public void beforeTest() throws IOException {
        initialWorkingDirectoryPath = fileSystem.getWorkingDirectoryPath();
        fileSystem.createTestFileHierarchy();
        fileSystem.setWorkingDirectory(System.getProperty("java.io.tmpdir"));
        Jsh.clearHistory();
    }

    @After
    // Delete the test hierarchy, reset the command arguments and reset the outputstream
    public void afterTest() throws IOException {
        fileSystem.deleteTestFileHierarchy();
        applicationArguments.clear();
        outputStream.reset();
        fileSystem.setWorkingDirectory(initialWorkingDirectoryPath);
    }  
    
    @Test
    public void zeroArgumentsHistoryTest() throws JshException {
        ArrayList<String> history = Jsh.getHistory();
        history.add("abc");
        history.add("xyz");

        String expectedString = "1. abc" + lineSeparator + "2. xyz" + lineSeparator;
        historyApplication.execute(applicationArguments, null, outputStream);
        assertEquals(expectedString, outputStream.toString());
    }

    @Test
    public void oneArgumentHistoryTest() throws JshException {
        applicationArguments.add("2");

        ArrayList<String> history = Jsh.getHistory();
        history.add("abc");
        history.add("xyz");
        history.add("mnp");

        String expectedString = "2. xyz" + lineSeparator + "3. mnp" + lineSeparator;
        historyApplication.execute(applicationArguments, null, outputStream);
        assertEquals(expectedString, outputStream.toString());
    }

    @Test
    public void oneArgumentTooBigHistoryTest() throws JshException {
        applicationArguments.add("10");

        ArrayList<String> history = Jsh.getHistory();
        history.add("abc");
        history.add("xyz");
        history.add("mnp");

        String expectedString = "1. abc" + lineSeparator + "2. xyz" + lineSeparator + "3. mnp" + lineSeparator;
        historyApplication.execute(applicationArguments, null, outputStream);
        assertEquals(expectedString, outputStream.toString());
    }

    @Test
    public void zeroArgumentsCap500Test() throws JshException {
        ArrayList<String> history = Jsh.getHistory();
        for (int index = 0; index < 200; ++index) {
            history.add("abc");
        }

        StringBuilder expectedString = new StringBuilder();
        for (int index = 200; index < 700; ++index) {
            history.add("abc");
            expectedString.append(Integer.toString(index + 1) + ". abc" + lineSeparator);
        }

        historyApplication.execute(applicationArguments, null, outputStream);
        assertEquals(expectedString.toString(), outputStream.toString());
    }

    @Test
    public void NegativeHistoryTest() {
        applicationArguments.add("-1");
        try {
            historyApplication.execute(applicationArguments, null, outputStream);
            fail("history did not throw an invalid option exception");
        } catch (JshException e) {
            assertEquals("history: invalid option", e.getMessage());
        }   
    }

    @Test
    public void TooManyArgumentsHistoryTest() {
        applicationArguments.add("abc");
        applicationArguments.add("def");

        try {
            historyApplication.execute(applicationArguments, null, outputStream);
        } catch (JshException e) {
            assertEquals("history: too many arguments", e.getMessage());
        }   
    }

}