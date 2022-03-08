package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.Applications.Ls;
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
import java.util.Arrays;
import java.util.Collections;

public class LsTest {
    private static Ls lsApplication;
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
        lsApplication = new Ls();
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
        applicationArguments.add(fileSeparator);
        applicationArguments.add("..");
        try {
            lsApplication.execute(applicationArguments, System.in, outputStream);
            fail("ls did not throw a too many arguments exception");
        } catch(JshException e) {
           assertEquals("ls: too many arguments", e.getMessage());
        }
    }

    @Test
    public void testCurrentDirectory() throws JshException {
        // The filesystem's current working directory will be /tmp/Documents
        fileSystem.setWorkingDirectory(fileSeparator + "tmp" + fileSeparator + "Documents");
        lsApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = "Ware" + "\t" + "Proj.txt" + "\t" + "Eng" + lineSeparator;
        assertEqualStrings(expectedOutput, outputStream.toString());
    }

    @Test
    public void testArgumentDirectoryRelativePath() throws JshException {
        // The filesystem's current working directory will be /tmp, but the argument will point to /tmp/Documents
        applicationArguments.add("Documents"); 
        lsApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = "Ware" + "\t" + "Proj.txt" + "\t" + "Eng" + lineSeparator;
        assertEqualStrings(expectedOutput, outputStream.toString());
    }

    @Test
    public void testArgumentDirectoryAbsolutePath() throws JshException {
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Documents" + fileSeparator + "Eng");
        lsApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = "Code" + "\t" + "Test" + "\t" + "Plan" + lineSeparator;
        assertEqualStrings(expectedOutput, outputStream.toString());
    }

    @Test
    public void testIgnoreDotFiles() throws JshException {
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Other");
        lsApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = "Oth1" + "\t" + "Empty" + "\t" + "Oth2" + lineSeparator;
        assertEqualStrings(expectedOutput, outputStream.toString());
    }

    @Test
    public void testEmptyDirectory() throws JshException {
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Other" + fileSeparator + "Empty");
        lsApplication.execute(applicationArguments, System.in, outputStream);
        assertEqualStrings("", outputStream.toString());
    }

    @Test
    public void testGlobbedPath() throws JshException {
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "D*s" + fileSeparator + "E*g");
        lsApplication.execute(applicationArguments, System.in, outputStream);
        String expectedOutput = "Code" + "\t" + "Test" + "\t" + "Plan" + lineSeparator;
        assertEqualStrings(expectedOutput, outputStream.toString());
    }

    private void assertEqualStrings(String expectedString, String actualString) {
        ArrayList<String> expectedTokens = new ArrayList<>(Arrays.asList(expectedString.trim().split("\t")));
        ArrayList<String> actualTokens = new ArrayList<>(Arrays.asList(actualString.trim().split("\t")));
        Collections.sort(expectedTokens);
        Collections.sort(actualTokens);
        assertEquals(expectedTokens, actualTokens);
    }
}