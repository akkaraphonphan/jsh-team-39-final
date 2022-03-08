package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.Applications.Sed;
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

public class SedTest {
    private static Sed sedApplication;
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
        sedApplication = new Sed();
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
    public void testInvalidNumberOfArguments() {
        try {
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw a missing arguments exception");
        } catch (JshException e) {
           assertEquals("sed: missing arguments", e.getMessage());
        }
    }

    @Test
    public void testMissingInputStreamAndFile() {
        try {
            applicationArguments.add("s/test/repl/");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw a missing input exception");
        } catch (JshException e) {
            assertEquals("sed: missing input", e.getMessage());
        }
    }

    @Test 
    public void testInvalidFirstArgumentEmptyString() {
        try {
            applicationArguments.add("");
            applicationArguments.add("Soft");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw an invalid first argument exception");
        } catch (JshException e) {
            assertEquals("sed: invalid first argument", e.getMessage());
        }
    }

    @Test
    public void testInvalidFirstArgumentWrongFirstCharacter() {
        try {
            applicationArguments.add("t/Regex/Replace/");
            applicationArguments.add("Soft");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw an invalid first argument exception");
        } catch (JshException e) {
            assertEquals("sed: invalid first argument", e.getMessage());
        }
    }

    @Test
    public void testInvalidFirstArgumentSpecialDelimiterTooManyTimes() {
        try {
            applicationArguments.add("sgRegexgReplacementgg");
            applicationArguments.add("Soft");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw an invalid first argument exception");
        } catch (JshException e) {
            assertEquals("sed: invalid first argument", e.getMessage());
        }
    }

    @Test
    public void testCharactersUsedInReplacementAndAsDelimiter() {
        try {
            applicationArguments.add("ssRegexsReplacesMesg");
            applicationArguments.add("Soft");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw an invalid first argument exception");
        } catch (JshException e) {
            assertEquals("sed: invalid first argument", e.getMessage());
        }
    }

    @Test
    public void testCharactergUsedInReplacementAndAsDelimiter() {
        try {
            applicationArguments.add("sgRegexgReplacementg");
            applicationArguments.add("Soft");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw an invalid first argument exception");
        } catch (JshException e) {
            assertEquals("sed: invalid first argument", e.getMessage());
        }
    }

    @Test
    public void testInvalidFirstArgumentWrongLastCharacter() {
        try {
            applicationArguments.add("s/Regex/Replacement/n");
            applicationArguments.add("Soft");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw an invalid first argument exception");
        } catch (JshException e) {
            assertEquals("sed: invalid first argument", e.getMessage());
        }
    }

    @Test
    public void testInvalidFisrtArgumentEmptyRegex() {
        try {
            applicationArguments.add("s//Huh/");
            applicationArguments.add("Soft");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw an invalid first argument exception");
        } catch (JshException e) {
            assertEquals("sed: invalid first argument", e.getMessage());
        }
    }

    @Test
    public void testInvalidFirstArgumentTooManyDelimiters() {
        try {
            applicationArguments.add("s/Regex/Replacem/nt/");
            applicationArguments.add("Soft");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw an invalid first argument exception");
        } catch (JshException e) {
            assertEquals("sed: invalid first argument", e.getMessage());
        }
    }
    
    @Test
    public void testInvalidFirstArgumentMissingDelimiter() {
        try {
            applicationArguments.add("s/RegexReplacement/");
            applicationArguments.add("Soft");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw an invalid first argument exception");
        } catch (JshException e) {
            assertEquals("sed: invalid first argument", e.getMessage());
        }
    }

    @Test
    public void testInvalidFirstArguementWrongEnding() {
        try {
            applicationArguments.add("s/Regex/Replacement");
            applicationArguments.add("Soft");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw an invalid first argument exception");
        } catch (JshException e) {
            assertEquals("sed: invalid first argument", e.getMessage());
        }
    }

    @Test
    public void testReadFromInvalidPath() {
        try {
            applicationArguments.add("s/Regex/Replacement/");
            applicationArguments.add("INVALID PATH");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw a cannot open file exception");
        } catch (JshException e) {
            assertEquals("sed: " + fileSeparator + "tmp" + fileSeparator + "INVALID PATH (No such file or directory)", e.getMessage());
        }
    }

    @Test
    public void testInvalidArgumentsTooMany() {
        try {
            applicationArguments.add("s/Regex/Replacement/");
            applicationArguments.add("okay");
            applicationArguments.add("TOO MANY");
            sedApplication.execute(applicationArguments, null, outputStream);
            fail("sed did not throw a too many arguments exception");
        } catch (JshException e) {
            assertEquals("sed: too many arguments", e.getMessage());
        }
    }

    @Test
    public void testReplaceWithSpecialDelimiter() throws JshException {
        applicationArguments.add("sgtestgreplg");
        applicationArguments.add("Soft");
        sedApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a repl" + lineSeparator;
        expectedOutput += "This is a repl of another repl" + lineSeparator;
        expectedOutput += lineSeparator;
        
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testReplaceAllOccurences() throws JshException {
        applicationArguments.add("s/test/repl/g");
        applicationArguments.add("Soft");
        sedApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a repl" + lineSeparator;
        expectedOutput += "This is a repl of another repl" + lineSeparator;
        expectedOutput += lineSeparator;
        
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testReplaceAllOccurencesWithSpecialDelimiter() throws JshException {
        applicationArguments.add("sgtestgreplgg");
        applicationArguments.add("Soft");
        sedApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a repl" + lineSeparator;
        expectedOutput += "This is a repl of another repl" + lineSeparator;
        expectedOutput += lineSeparator;
        
        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    public void testReadFromFileRelativePath() throws JshException {
        applicationArguments.add("s/test/repl/");
        applicationArguments.add("Soft");
        sedApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a repl" + lineSeparator;
        expectedOutput += "This is a repl of another test" + lineSeparator;
        expectedOutput += lineSeparator;
        
        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    public void testReadingFromFileAbsolutePath() throws JshException {
        applicationArguments.add("s/test/repl/");
        applicationArguments.add(fileSeparator + "tmp" + fileSeparator + "Documents" + fileSeparator + "Ware");
        sedApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a repl" + lineSeparator;
        expectedOutput += "This is a repl of another test" + lineSeparator;
        expectedOutput += lineSeparator;
        
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testDifferentDelimiterSymbol() throws JshException {
        applicationArguments.add("s%test%repl%g");
        applicationArguments.add("Soft");
        sedApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a repl" + lineSeparator;
        expectedOutput += "This is a repl of another repl" + lineSeparator;
        expectedOutput += lineSeparator;
        
        assertEquals(expectedOutput, outputStream.toString());
    }
    
    @Test
    public void testEmptyReplacement() throws JshException {
        applicationArguments.add("s/This is a test//");
        applicationArguments.add("Soft");
        sedApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += lineSeparator;
        expectedOutput += " of another test" + lineSeparator;
        expectedOutput += lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }
    
    @Test
    public void testNoMatches() throws JshException {
        applicationArguments.add("s/RANDOM STRING/Replaced/g");
        applicationArguments.add("Soft");
        sedApplication.execute(applicationArguments, null, outputStream);
        String exepctedOutput = new String();
        exepctedOutput += "This is a test" + lineSeparator;
        exepctedOutput += "This is a test of another test" + lineSeparator;
        exepctedOutput += lineSeparator;
        assertEquals(exepctedOutput, outputStream.toString());
    }
    
    
    @Test
    public void testReadingFromInputStream() throws IOException, JshException {
        ByteArrayOutputStream aux = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(aux));
        writer.write("Hello world" + lineSeparator);
        writer.write("Hello Hello world!" + lineSeparator);
        writer.write("world" + lineSeparator);
        writer.flush();
        writer.close();

        ByteArrayInputStream testInput = new ByteArrayInputStream(aux.toByteArray());
        applicationArguments.add("s/Hello/Sad/g");
        sedApplication.execute(applicationArguments, testInput, outputStream);
        
        String expectedOutput = "Sad world" + lineSeparator;
        expectedOutput += "Sad Sad world!" + lineSeparator;
        expectedOutput += "world" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testReadingFromInputStreamWithSpecialDelimiter() throws IOException, JshException {
        ByteArrayOutputStream aux = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(aux));
        writer.write("Hello world" + lineSeparator);
        writer.write("Hello Hello world!" + lineSeparator);
        writer.write("world" + lineSeparator);
        writer.flush();
        writer.close();

        ByteArrayInputStream testInput = new ByteArrayInputStream(aux.toByteArray());
        applicationArguments.add("ssHellosSadsg");
        sedApplication.execute(applicationArguments, testInput, outputStream);
        
        String expectedOutput = "Sad world" + lineSeparator;
        expectedOutput += "Sad Sad world!" + lineSeparator;
        expectedOutput += "world" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }
    
    @Test
    public void testGlobbedPath() throws JshException {
        applicationArguments.add("s/test/repl/g");
        applicationArguments.add("S*t");
        sedApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a repl" + lineSeparator;
        expectedOutput += "This is a repl of another repl" + lineSeparator;
        expectedOutput += lineSeparator;
        
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testRegexArgument() throws JshException {
        applicationArguments.add("s/t+est/repl/g");
        applicationArguments.add("Soft");
        sedApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a repl" + lineSeparator;
        expectedOutput += "This is a repl of another repl" + lineSeparator;
        expectedOutput += lineSeparator;
        
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testGlobbedArguments() throws JshException {
        applicationArguments.add("s/test/repl*/g");
        applicationArguments.add("Soft");
        sedApplication.execute(applicationArguments, null, outputStream);
        String expectedOutput = new String();
        expectedOutput += "This is a repl*" + lineSeparator;
        expectedOutput += "This is a repl* of another repl*" + lineSeparator;
        expectedOutput += lineSeparator;
        
        assertEquals(expectedOutput, outputStream.toString());
    }
    
}