package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.Applications.Find;
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

public class FindTest {
    private static Find findApplication;
    private static FileSystem fileSystem;
    private static ByteArrayOutputStream outputStream;
    private static ArrayList<String> applicationArguments;

    private String lineSeparator = Jsh.lineSeparator;
    private String fileSeparator = Jsh.fileSeparator;
    private String initialWorkingDirectoryPath;
    
    private void assertEqualStrings(String expectedString, String actualString) {
        ArrayList<String> expectedTokens = new ArrayList<>(Arrays.asList(expectedString.trim().split(lineSeparator)));
        ArrayList<String> actualTokens = new ArrayList<>(Arrays.asList(actualString.trim().split(lineSeparator)));
        Collections.sort(expectedTokens);
        Collections.sort(actualTokens);
        assertEquals(expectedTokens, actualTokens);
    }

    @BeforeClass
    public static void setClass() {
        applicationArguments = new ArrayList<>();
        fileSystem = FileSystem.getInstance();
        outputStream = new ByteArrayOutputStream();
        findApplication = new Find();
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
    public void testMissingArguments() {
        try {
            findApplication.execute(applicationArguments, null, outputStream);
            fail("find did not throw a missing arguments exception");
        } catch (JshException e) {
           assertEquals("find: missing arguments", e.getMessage());
        }
    }

    @Test 
    public void testMissingPattern() {
        try {
            applicationArguments.add(fileSeparator + "lib");
            applicationArguments.add("-name");
            findApplication.execute(applicationArguments, null, outputStream);
            fail("find did not throw a wrong argument exception");
        } catch (JshException e) {
            assertEquals("find: wrong argument", e.getMessage());
        }
    }

    @Test
    public void testTooManyArguments() {
        try {
            applicationArguments.add("one");
            applicationArguments.add("two");
            applicationArguments.add("three");
            applicationArguments.add("four");
            findApplication.execute(applicationArguments, null, outputStream);
            fail("find did not throw a too many arguments exception");
        } catch (JshException e) {
            assertEquals("find: too many arguments", e.getMessage());
        }
    }
    
    @Test
    public void testFindFromInvalidPath() {
        try {
            applicationArguments.add("InvalidPath");
            applicationArguments.add("-name");
            applicationArguments.add("Invalid");
            findApplication.execute(applicationArguments, null, outputStream);
            fail("find did not throw a cannot open directory exception");
        } catch (JshException e) {
            assertEquals("find: could not open InvalidPath", e.getMessage());
        }
    }

    @Test
    public void testFindFromFilePath() {
        try {
            applicationArguments.add("Soft");
            applicationArguments.add("-name");
            applicationArguments.add("Invalid");
            findApplication.execute(applicationArguments, null, outputStream);
            fail("find did not throw a cannot open directory exception");
        } catch (JshException e) {
            assertEquals("find: could not open " + "Soft", e.getMessage());
        }
    }

    @Test
    public void testInvalidArgumentsMissingDashName() {
        try {
            applicationArguments.add("Documents");
            applicationArguments.add("NotName");
            applicationArguments.add("FindMe");
            findApplication.execute(applicationArguments, null, outputStream);
            fail("find did not throw an invalid argument exception");
        } catch (JshException e) {
            assertEquals("find: invalid argument " + "NotName", e.getMessage());
        }
    }

    @Test
    public void testFindOneFileFromCurrentDirectory() throws JshException {
        applicationArguments.add("-name");
        applicationArguments.add("Soft");
        findApplication.execute(applicationArguments, null, outputStream);
        assertEqualStrings("." + fileSeparator + "Soft" + lineSeparator, outputStream.toString());
    }

    @Test
    public void testFindDirectoryFromCurrentDirectory() throws JshException {
        applicationArguments.add("-name");
        applicationArguments.add("Documents");
        findApplication.execute(applicationArguments, null, outputStream);
        assertEqualStrings("", outputStream.toString());
    }

    @Test 
    public void testFindMultipleFilesFromRelativePath() throws JshException {
        applicationArguments.add("Other");
        applicationArguments.add("-name");
        applicationArguments.add("Oth*");
        findApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "Other" + fileSeparator + "Oth1" + lineSeparator;
        expectedOutput += "Other" + fileSeparator + "Oth2" + lineSeparator;
        assertEqualStrings(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPatterMatching() throws JshException {
        applicationArguments.add("Other");
        applicationArguments.add("-name");
        applicationArguments.add("*h*");
        findApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "Other" + fileSeparator + "Oth1" + lineSeparator;
        expectedOutput += "Other" + fileSeparator + "Oth2" + lineSeparator;
        assertEqualStrings(expectedOutput, outputStream.toString());
    }

    @Test
    public void testFindFileFromAbsolutePath() throws JshException {
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Documents" + fileSeparator + "Eng");
        applicationArguments.add("-name");
        applicationArguments.add("T*");
        findApplication.execute(applicationArguments, null, outputStream);
        assertEqualStrings(fileSeparator + "tmp" + fileSeparator + "Documents" + fileSeparator + "Eng" + fileSeparator + "Test" + lineSeparator, outputStream.toString());
    }

    @Test
    public void testNoMatchingFile() throws JshException {
        applicationArguments.add("-name");
        applicationArguments.add("NoMatch!");
        findApplication.execute(applicationArguments, null, outputStream);
        assertEqualStrings("", outputStream.toString());
    }

    @Test
    public void testFindFileFromLowerLeverInTree() throws JshException {
        applicationArguments.add("-name");
        applicationArguments.add("Code");
        findApplication.execute(applicationArguments, null, outputStream);
        assertEqualStrings("." + fileSeparator + "Documents" + fileSeparator + "Eng"  + fileSeparator + "Code" + lineSeparator, outputStream.toString());
    }

    @Test
    public void testFindFileFromGlobbedPath() throws JshException {
        applicationArguments.add(fileSeparator + "t*p" + fileSeparator + "Doc*s" + fileSeparator + "Eng");
        applicationArguments.add("-name");
        applicationArguments.add("Test");
        findApplication.execute(applicationArguments, null, outputStream);
        assertEqualStrings(fileSeparator + "tmp" + fileSeparator + "Documents" + fileSeparator + "Eng" + fileSeparator + "Test" + lineSeparator, outputStream.toString());
    }

    @Test
    public void testGlobbedPathAndPattern() throws JshException {
        applicationArguments.add("Ot*");
        applicationArguments.add("-name");
        applicationArguments.add("Oth*");
        findApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "Other" + fileSeparator + "Oth1" + lineSeparator;
        expectedOutput += "Other" + fileSeparator + "Oth2" + lineSeparator;
        assertEqualStrings(expectedOutput, outputStream.toString());
    }
}