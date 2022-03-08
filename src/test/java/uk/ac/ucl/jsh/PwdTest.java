package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.Applications.Pwd;
import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class PwdTest {
    private static Pwd pwdApplication;
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
        pwdApplication = new Pwd();
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
    public void testInvalidNumberOfArguments() {
        applicationArguments.add("unwantedParameter");
        try {
            pwdApplication.execute(applicationArguments, System.in, outputStream);
            fail("pwd did not throw a too many arguments exception");
        } catch(JshException e) {
           assertEquals("pwd: too many arguments", e.getMessage());
         }

    }

    @Test
    public void testRootDirectory() throws JshException {
        fileSystem.setWorkingDirectory(fileSeparator);
        pwdApplication.execute(applicationArguments, System.in, outputStream);
        assertEquals(fileSeparator + lineSeparator, outputStream.toString());
    }
    
    @Test
    public void testRandomDirectory() throws JshException {
        fileSystem.setWorkingDirectory(fileSeparator + "tmp" + fileSeparator + "Other");
        pwdApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = fileSeparator + "tmp" + fileSeparator + "Other" + lineSeparator;
        assertEquals(expectedOutput , outputStream.toString());
    }

}