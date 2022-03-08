package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.Applications.Grep;
import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GrepTest {
    private static Grep grepApplication;
    private static FileSystem fileSystem;
    private static ByteArrayOutputStream outputStream;
    private static ArrayList<String> applicationArguments;

    private String lineSeparator = Jsh.lineSeparator;
    private String fileSeparator = Jsh.fileSeparator;
    private String initialWorkingDirectoryPath;
    
    @BeforeClass
    public static void setClass() {
        applicationArguments = new ArrayList<>();
        fileSystem = FileSystem.getInstance();
        outputStream = new ByteArrayOutputStream();
        grepApplication = new Grep();
    }

    @Before
    // Create the File Hierarchy
    public void createHierarchy() throws IOException {
        initialWorkingDirectoryPath = fileSystem.getWorkingDirectoryPath();
        fileSystem.createTestFileHierarchy();
        fileSystem.setWorkingDirectory(System.getProperty("java.io.tmpdir"));
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
    public void testInvalidNumberOfArgumentsMissingArguments() {
        try {
            grepApplication.execute(applicationArguments, null, outputStream);
            fail("grep did not throw a missing arguments exception");
        } catch (JshException e) {
           assertEquals("grep: missing arguments", e.getMessage());
        }
    }

    @Test
    public void testMissingInput() {
        try {
            applicationArguments.add("pattern");
            grepApplication.execute(applicationArguments, null, outputStream);
            fail("grep did not throw a missing input exception");
        } catch (JshException e) {
            assertEquals("grep: missing input", e.getMessage());
        }
    }

    @Test
    public void testInvalidArgumentInvalidPath() {
        try {
            applicationArguments.add("pattern");
            applicationArguments.add("InvalidPath");
            grepApplication.execute(applicationArguments, null, outputStream);
            fail("grep did no throw a cannot open exception");
        } catch (JshException e) {
            assertEquals("grep: " + fileSeparator + "tmp" + fileSeparator + "InvalidPath (No such file or directory)", e.getMessage());
        }
    }

    @Test
    public void testReadingFromDirectoryPath() {
        try {
            applicationArguments.add("pattern");
            applicationArguments.add("Documents");
            grepApplication.execute(applicationArguments, null, outputStream);
            fail("grep did not throw a cannot open exception");
        } catch (JshException e) {
            assertEquals("grep: " + fileSeparator + "tmp" + fileSeparator + "Documents (Is a directory)", e.getMessage());
        }
    }



    @Test
    public void testReadFromEmptyFile() throws JshException {
        applicationArguments.add(".*");
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Documents" + fileSeparator + "Eng" + fileSeparator + "Plan");
        grepApplication.execute(applicationArguments, null, outputStream);
        assertEquals("", outputStream.toString());
    }   

    @Test
    public void testReadFromInputStream() throws IOException, JshException {
        ByteArrayOutputStream aux = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(aux));
        String inputString = "Hello world" + lineSeparator + "Hello there" + lineSeparator + "Bye" + lineSeparator + lineSeparator;
        writer.write(inputString);
        writer.flush();
        writer.close();
        ByteArrayInputStream testInput = new ByteArrayInputStream(aux.toByteArray());
        applicationArguments.add("Hello");
        grepApplication.execute(applicationArguments, testInput, outputStream);
        String expectedOutput = "Hello world" + lineSeparator + "Hello there" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testReadFromInputStreamWihComplexRegex() throws IOException, JshException {
        ByteArrayOutputStream aux = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(aux));
        String inputString = "Hello world" + lineSeparator + "Hello there" + lineSeparator + "Bye" + lineSeparator + "Wow, Hello" + lineSeparator + lineSeparator;
        writer.write(inputString);
        writer.flush();
        writer.close();
        ByteArrayInputStream testInput = new ByteArrayInputStream(aux.toByteArray());
        applicationArguments.add(".+el{2}.{1}");
        grepApplication.execute(applicationArguments, testInput, outputStream);
        String expectedOutput = "Hello world" + lineSeparator + "Hello there" + lineSeparator + "Wow, Hello" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testReadFromFileAbsolutePath() throws JshException {
        applicationArguments.add("Lin.*");
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Documents" + fileSeparator + "Eng" + fileSeparator + "Test");
        grepApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        for(int i = 0; i < 20; ++ i) {
            expectedOutput += "Line number: " + Integer.toString(i) + lineSeparator;
        }
        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    public void testReadFromFileRelativePath() throws JshException {
        applicationArguments.add("test");
        applicationArguments.add("Soft");
        grepApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    public void testReadFromMultipleFiles() throws JshException {
        applicationArguments.add("test");
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Soft");
        applicationArguments.add("Documents" + fileSeparator + "Ware");
        grepApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void readFromFileWithInputStreamNotNull() throws IOException, JshException {
        applicationArguments.add("test");
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Soft");
        ByteArrayOutputStream aux = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(aux));
        String inputString = "Hello world" + lineSeparator + "Hello there" + lineSeparator + "Bye" + lineSeparator + lineSeparator;
        writer.write(inputString);
        writer.flush();
        writer.close();
        ByteArrayInputStream testInput = new ByteArrayInputStream(aux.toByteArray());
        grepApplication.execute(applicationArguments, testInput, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    public void testReadFromGlobbedPath() throws JshException{
        applicationArguments.add("Line");
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Docu*s" + fileSeparator + "Eng" + fileSeparator + "Test");
        grepApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        for(int i = 0; i < 20; ++ i) {
            expectedOutput += "Line number: " + Integer.toString(i) + lineSeparator;
        }
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testWithGlobbedArguments() throws JshException{
        applicationArguments.add("L*e");
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Docu*s" + fileSeparator + "Eng" + fileSeparator + "Test");
        grepApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        for(int i = 0; i < 20; ++ i) {
            expectedOutput += "Line number: " + Integer.toString(i) + lineSeparator;
        }
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testMultipleFIlesFromGlobbedArgument() throws JshException {
        applicationArguments.add("test");
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Other" + fileSeparator + "Oth*");
        grepApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        assertEqualStrings(expectedOutput, outputStream.toString());
    }

    private void assertEqualStrings(String expectedString, String actualString) {
        ArrayList<String> expectedTokens = new ArrayList<>(Arrays.asList(expectedString.trim().split(lineSeparator)));
        ArrayList<String> actualTokens = new ArrayList<>(Arrays.asList(actualString.trim().split(lineSeparator)));
        Collections.sort(expectedTokens);
        Collections.sort(actualTokens);
        assertEquals(expectedTokens, actualTokens);
    }
}