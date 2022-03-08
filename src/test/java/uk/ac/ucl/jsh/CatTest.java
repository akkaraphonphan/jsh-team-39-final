package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.Applications.Cat;
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


public class CatTest {
    private static Cat catApplication;
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
        catApplication = new Cat();
    }

    @Before
    // Create the test hierarchy
    public void beforeTest() throws IOException {
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
    public void testMissingInput() {
        try {
            catApplication.execute(applicationArguments, null, outputStream);
            fail("cat did not throw a missing input exception");
        } catch (JshException e) {
            assertEquals("cat: missing input", e.getMessage());
        }
    }

    @Test 
    public void testInvalidPath() {
        try {
            applicationArguments.add(fileSeparator + "InvalidPath");
            catApplication.execute(applicationArguments, System.in, outputStream);
            fail("cat did not throw an invalid path exception");
        } catch(JshException e) {
           String expectedMessage = "cat: " + fileSeparator + "InvalidPath (No such file or directory)";
           assertEquals(expectedMessage, e.getMessage());
         }
    }

    @Test
    public void testDirectoryPath() {
        try {
            applicationArguments.add("Documents");
            catApplication.execute(applicationArguments, System.in, outputStream);
            fail("cat did not throw a directory path exception");
        } catch(JshException e) {
            String expectedMessage = "cat: " + fileSeparator + "tmp" + fileSeparator + "Documents (Is a directory)";
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    
    @Test
    public void testReadingFromInputStream () throws IOException, JshException {
        ByteArrayOutputStream aux = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(aux));
        String expectedOutput = "Hello world" + lineSeparator + "I am here!" + lineSeparator;
        writer.write(expectedOutput);
        writer.flush();
        writer.close();
        ByteArrayInputStream testInput = new ByteArrayInputStream(aux.toByteArray());
        catApplication.execute(applicationArguments, testInput, outputStream);  
        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    public void testFileAbsolutePath() throws JshException {
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Documents" + fileSeparator + "Ware");
        catApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        expectedOutput += lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }  
    
    @Test
    public void testFileRelativePath() throws JshException {
        applicationArguments.add("Documents" + fileSeparator + "Ware");
        catApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        expectedOutput += lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testMultipleFiles() throws JshException {
        applicationArguments.add("Documents" + fileSeparator + "Ware");
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Soft");
        catApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        expectedOutput += lineSeparator;
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        expectedOutput += lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testMultipleFilesFromTwoGlobArguments() throws JshException {
        applicationArguments.add("Docu*s" + fileSeparator + "Wa*e");
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "S*t");
        catApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        expectedOutput += lineSeparator;
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        expectedOutput += lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testMultipleFilesFromGlobArgument() throws JshException {
        applicationArguments.add("Other" + fileSeparator + "Ot*");
        catApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        expectedOutput += lineSeparator;
        expectedOutput += "This is a test" + lineSeparator;
        expectedOutput += "This is a test of another test" + lineSeparator;
        expectedOutput += lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

}