package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.Applications.Cd;
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


public class CdTest {
    private static Cd cdApplication;
    private static FileSystem fileSystem;
    private static ByteArrayOutputStream outputStream;
    private static ArrayList<String> applicationArguments;

    private String fileSeparator = Jsh.fileSeparator;
    private String initialWorkingDirectoryPath;
    
    @BeforeClass
    public static void setClass() {
        applicationArguments = new ArrayList<>();
        fileSystem = FileSystem.getInstance();
        outputStream = new ByteArrayOutputStream();
        cdApplication = new Cd();
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
    public void testMoreArguments() {
        try {
            applicationArguments.add("..");
            applicationArguments.add("..");
            applicationArguments.add("..");
            applicationArguments.add("..");
            cdApplication.execute(applicationArguments, System.in, outputStream);
            fail("cd did not throw a too many arguments exception");

        } catch(JshException e) {
           assertEquals("cd: too many arguments", e.getMessage());
        }
    }

    @Test 
    public void testMoreArgumentsFromGlobbing() throws IOException {
        try {
            applicationArguments.add("*t*");
            cdApplication.execute(applicationArguments, System.in, outputStream);
            fail("cd did not throw a too many arguments exception");
        } catch(JshException e) {
           assertEquals("cd: too many arguments", e.getMessage());
        }
    }

	@Test
    public void testLessArguments() {
        try {
            cdApplication.execute(applicationArguments, System.in, outputStream);
            fail("cd did not throw a missing argument exception");
        } catch(JshException e) {
            assertEquals("cd: missing argument", e.getMessage());
        }
    }

    @Test 
    public void testInvalidPath() {
        try {
            applicationArguments.add("invalid" + fileSeparator + "Path");
            cdApplication.execute(applicationArguments, System.in, outputStream);
            fail("cd did not throw an invalid path exception");
        } catch(JshException e) {
            String expectedMessage = "cd: " + "invalid" + fileSeparator + "Path" + " is not an existing directory";
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test 
    public void testFilePath() {
        try {
            applicationArguments.add(fileSeparator + "Soft");
            cdApplication.execute(applicationArguments, System.in, outputStream);
            fail("cd did not throw an invalid path exception");
        } catch(JshException e) {
            assertEquals("cd: " + fileSeparator + "Soft" + " is not an existing directory", e.getMessage());
        }
    }

    @Test
    public void testDotsInterpretation() {
        try {
            applicationArguments.add("...");
            cdApplication.execute(applicationArguments, System.in, outputStream);
            fail("cd did not throw an invalid path exception");
        } catch(JshException e) {
            assertEquals("cd: " + "..." + " is not an existing directory", e.getMessage());
        }
    }
 
    @Test
    public void testGoingUpFromRoot() throws JshException {
        applicationArguments.add("..");
        fileSystem.setWorkingDirectory(fileSeparator);
        cdApplication.execute(applicationArguments, System.in, outputStream);
        assertEquals(fileSeparator, fileSystem.getWorkingDirectoryPath());
    }

    @Test
    public void testCurrentDirectory() throws JshException {
        applicationArguments.add(".");
        cdApplication.execute(applicationArguments, System.in, outputStream);
        assertEquals(fileSeparator + "tmp", fileSystem.getWorkingDirectoryPath());
    }

    @Test
    public void testParentDirectory() throws JshException {
        applicationArguments.add("..");
        cdApplication.execute(applicationArguments, System.in, outputStream);
        assertEquals(fileSeparator, fileSystem.getWorkingDirectoryPath());
    }

    @Test
    public void testRelativePathToDirectory() throws JshException {
        applicationArguments.add("Documents" + fileSeparator + "Eng");
        cdApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = System.getProperty("java.io.tmpdir") + fileSeparator + "Documents" + fileSeparator + "Eng";
        assertEquals(expectedOutput, fileSystem.getWorkingDirectoryPath());
    }

    @Test
   public void testAbsolutePathToDirectory() throws JshException {
       applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Other");
       cdApplication.execute(applicationArguments, System.in, outputStream);
       String expectedOutput = fileSeparator + "tmp" + fileSeparator + "Other";
       assertEquals(expectedOutput, fileSystem.getWorkingDirectoryPath());
    }

    @Test
    public void testGlobbedAbsolutePathToDirectory() throws JshException {
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "O*r");
        cdApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = fileSeparator + "tmp" + fileSeparator + "Other";
        assertEquals(expectedOutput, fileSystem.getWorkingDirectoryPath());
     }

}