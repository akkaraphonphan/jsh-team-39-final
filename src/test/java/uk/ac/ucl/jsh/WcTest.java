package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.Applications.Wc;
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


public class WcTest {
    private static Wc wcApplication;
    private static ArrayList<String> applicationArguments;
    private static FileSystem fileSystem;
    private static ByteArrayOutputStream outputStream;
    private String lineSeparator = System.getProperty("line.separator");
    private String fileSeparator = System.getProperty("file.separator");
    
    @BeforeClass
    public static void setClass() {
        applicationArguments = new ArrayList<>();
        fileSystem = FileSystem.getInstance();
        outputStream = new ByteArrayOutputStream();
        wcApplication = new Wc();
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

    @Test
    public void testInvalidArgument() {
        try {
            applicationArguments.add("-q");
            wcApplication.execute(applicationArguments, null, outputStream);
            fail("wc did not throw an invalid argument exception");
        } catch (JshException e) {
           assertEquals("wc: file does not exist", e.getMessage());
        }
    }

    @Test
    public void testOneInvalidPathNonExistant() {
        try {
            applicationArguments.add("Soft");
            applicationArguments.add("InvalidPath");
            wcApplication.execute(applicationArguments, null, outputStream);
            fail("wc did not throw an invalid argument exception");
        } catch (JshException e) {
           assertEquals("wc: file does not exist", e.getMessage());
        }
    }

    @Test
    public void testOneInvalidPathToFolder() {
        try {
            applicationArguments.add("Soft");
            applicationArguments.add("Documents");
            wcApplication.execute(applicationArguments, null, outputStream);
            fail("wc did not throw an invalid argument exception");
        } catch (JshException e) {
            assertEquals("wc: " + "Documents" + " is a directory", e.getMessage());
        } 
    }

    @Test
    public void testMissingInput() {
        try {
            applicationArguments.add("-m");
            System.out.println(applicationArguments.get(0));
            wcApplication.execute(applicationArguments, null, outputStream);
            fail("wc did not throw a missing input exception");
        } catch (JshException e) {
           assertEquals("wc: missing input", e.getMessage());
        }
    }

    @Test
    public void testFlagM() throws JshException {
        applicationArguments.add("-m");
        applicationArguments.add("Soft");
        wcApplication.execute(applicationArguments, null, outputStream);
        assertEquals("47 " + lineSeparator, outputStream.toString());
    }

    @Test
    public void testFlagW() throws JshException {
        applicationArguments.add("-w");
        applicationArguments.add("Soft");
        wcApplication.execute(applicationArguments, null, outputStream);
        assertEquals("11 " + lineSeparator, outputStream.toString());
    }

    @Test
    public void testFlagL() throws JshException {
        applicationArguments.add("-l");
        applicationArguments.add("Soft");
        wcApplication.execute(applicationArguments, null, outputStream);
        assertEquals("3 " + lineSeparator, outputStream.toString());
    }

    @Test
    public void testTwoFlagsInSameArgument() throws JshException {
        applicationArguments.add("-lw");
        applicationArguments.add("Soft");
        wcApplication.execute(applicationArguments, null, outputStream);
        assertEquals("3 11 " + lineSeparator, outputStream.toString());
    }

    @Test
    public void testTwoFlagsInDifferentArguments() throws JshException {
        applicationArguments.add("-l");
        applicationArguments.add("Soft");
        applicationArguments.add("-w");
        wcApplication.execute(applicationArguments, null, outputStream);
        assertEquals("3 11 " + lineSeparator, outputStream.toString());
    }

    @Test
    public void testAllFlagsWithAllOptions() throws JshException {
        applicationArguments.add("-lwm");
        applicationArguments.add("Soft");
        wcApplication.execute(applicationArguments, null, outputStream);
        assertEquals("3 11 47 " + lineSeparator, outputStream.toString());
    }

    @Test
    public void testNoOptions() throws JshException {
        applicationArguments.add("Soft");
        wcApplication.execute(applicationArguments, null, outputStream);
        assertEquals("3 11 47 " + lineSeparator, outputStream.toString());
    }

    @Test
    public void testAllFlagsAbsolutePath() throws JshException {
        applicationArguments.add("-mlw");
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Other" + fileSeparator + "Oth1");
        wcApplication.execute(applicationArguments, null, outputStream);
        assertEquals("3 11 47 " + lineSeparator, outputStream.toString());
    }

    @Test
    public void testFlagAppearingMoreThanOnce() throws JshException {
        applicationArguments.add("-lw");
        applicationArguments.add("Soft");
        applicationArguments.add("-w");
        applicationArguments.add("-w");
        wcApplication.execute(applicationArguments, null, outputStream);
        assertEquals("3 11 " + lineSeparator, outputStream.toString());
    }

    @Test
    public void testReadFromMultipleFiles() throws JshException {
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Other" + fileSeparator + "Oth1");
        applicationArguments.add("Soft");
        wcApplication.execute(applicationArguments, null, outputStream);
        assertEquals("6 22 94 " + lineSeparator, outputStream.toString());
    }

    @Test
    public void testReadFromMultipleGlobbedFiles() throws JshException {
        applicationArguments.add("Docu*s" + fileSeparator + "Wa*e");
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "S*t");
        wcApplication.execute(applicationArguments, null, outputStream);
        assertEquals("6 22 94 " + lineSeparator, outputStream.toString());
    }

    @Test 
    public void testReadFromInputStreamWithFlags() throws IOException, JshException {
        applicationArguments.add("-m");
        ByteArrayOutputStream aux = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(aux));
        String input = "Hello world" + lineSeparator + "I am here!" + lineSeparator;
        writer.write(input);
        writer.flush();
        writer.close();
        ByteArrayInputStream testInput = new ByteArrayInputStream(aux.toByteArray());
        wcApplication.execute(applicationArguments, testInput, outputStream);  
        assertEquals("23 " + lineSeparator, outputStream.toString());
    }

    @Test
    public void testReadFromInputStream() throws IOException, JshException {
        ByteArrayOutputStream aux = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(aux));
        String input = "Hello world" + lineSeparator + "I am here!" + lineSeparator;
        writer.write(input);
        writer.flush();
        writer.close();
        ByteArrayInputStream testInput = new ByteArrayInputStream(aux.toByteArray());
        wcApplication.execute(applicationArguments, testInput, outputStream);  
        assertEquals("2 5 23 " + lineSeparator, outputStream.toString());
    }

    @Test
    public void testOnlyOneLineFile() throws JshException {
        applicationArguments.add("-m");
        applicationArguments.add("Hello");
        wcApplication.execute(applicationArguments, null, outputStream);
        assertEquals("5 " + lineSeparator, outputStream.toString());
    }
}