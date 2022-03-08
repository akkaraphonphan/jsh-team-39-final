package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.Applications.Echo;
import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class EchoTest {
    private static Echo echoApplication;
    private static FileSystem fileSystem;
    private static ByteArrayOutputStream outputStream;
    private static ArrayList<String> applicationArguments;

    private String lineSeparator = Jsh.lineSeparator;
    private static String initialWorkingDirectoryPath;
  
    @BeforeClass
    public static void setClass() {
        applicationArguments = new ArrayList<>();
        outputStream = new ByteArrayOutputStream();
        echoApplication = new Echo();

        fileSystem = FileSystem.getInstance();
        initialWorkingDirectoryPath = fileSystem.getWorkingDirectoryPath();
        fileSystem.setWorkingDirectory("java.io.tmpdir");
    }

    @Before
    // Create the File Hierarchy
    public void createHierarchy() throws IOException {
        fileSystem.createTestFileHierarchy();
        fileSystem.setWorkingDirectory(System.getProperty("java.io.tmpdir"));
    }

      @After
     // Delete the test hierarchy, reset the command arguments and reset the outputstream
     public void afterTest() throws IOException {
         fileSystem.deleteTestFileHierarchy();
         applicationArguments.clear();
         outputStream.reset();
    }     

    @AfterClass
    public static void afterClass() {
        fileSystem.setWorkingDirectory(initialWorkingDirectoryPath);
    }

    @Test
    public void testOneArgument() throws JshException {
        applicationArguments.add("hello world");
        echoApplication.execute(applicationArguments, System.in, outputStream);
        assertEquals("hello world" + lineSeparator, outputStream.toString());
    }

    @Test
    public void testMultipleArguments() throws JshException {
        applicationArguments.add("first");
        applicationArguments.add("second");
        applicationArguments.add("third");
        echoApplication.execute(applicationArguments, System.in, outputStream);
        assertEquals("first second third" + lineSeparator, outputStream.toString());
    }

    @Test
    public void testNoArguments() throws JshException {
        echoApplication.execute(applicationArguments, null, outputStream);
        assertEquals(lineSeparator, outputStream.toString());
    }

    @Test
    public void testMultipleArgumentsFromGlobbing() throws JshException {
        fileSystem.setWorkingDirectory(Jsh.fileSeparator + "tmp" + Jsh.fileSeparator + "Other");
        System.out.println(fileSystem.getWorkingDirectoryPath());
        applicationArguments.add("*");
        echoApplication.execute(applicationArguments, null, outputStream);
        assertEqualStrings(".test Oth1 Oth2 Empty", outputStream.toString());
    }

    private void assertEqualStrings(String expectedString, String actualString) {
        ArrayList<String> expectedTokens = new ArrayList<>(Arrays.asList(expectedString.trim().split(" ")));
        ArrayList<String> actualTokens = new ArrayList<>(Arrays.asList(actualString.trim().split(" ")));
        Collections.sort(expectedTokens);
        Collections.sort(actualTokens);
        assertEquals(expectedTokens, actualTokens);
    }
}