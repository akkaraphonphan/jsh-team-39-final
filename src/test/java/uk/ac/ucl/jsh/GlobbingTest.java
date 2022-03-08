package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.Applications.Application;
import uk.ac.ucl.jsh.Utilities.FileSystem;
import uk.ac.ucl.jsh.Utilities.JshException;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GlobbingTest {
    private static FileSystem fileSystem;

    private String fileSeparator = Jsh.fileSeparator;
    
    @Before
    // Create the test hierarchy
    public void beforeTest() throws IOException {
        fileSystem = FileSystem.getInstance();
        fileSystem.createTestFileHierarchy();
        fileSystem.setWorkingDirectory(System.getProperty("java.io.tmpdir"));
    }

    @After
    // Delete the test hierarchy, reset the command arguments and reset the outputstream
    public void afterTest() throws IOException {
        fileSystem.deleteTestFileHierarchy();
    }   

    private void assertEqualsArrayLists(ArrayList<String> expected, ArrayList<String> actual) {
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testGlobAll() {
        fileSystem.setWorkingDirectory(System.getProperty("java.io.tmpdir") + fileSeparator + "Documents");
        ArrayList<String> argumentsBeforeGlobbing = new ArrayList<>();
        argumentsBeforeGlobbing.add("*");
        ArrayList<String> globbingResult = Application.globArguments(argumentsBeforeGlobbing, -1);
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("Eng");
        expectedResult.add("Ware");
        expectedResult.add("Proj.txt");
        assertEqualsArrayLists(expectedResult, globbingResult);
    }

    @Test
    public void testBasicGlobbingRelativePath() {
        ArrayList<String> argumentsBeforeGlobbing = new ArrayList<>();
        argumentsBeforeGlobbing.add("Doc*");
        ArrayList<String> globbingResult = Application.globArguments(argumentsBeforeGlobbing, -1);
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("Documents");
        assertEqualsArrayLists(expectedResult, globbingResult);
    }

    @Test
    public void testBasicGlobbingAbsolutePath() {
        ArrayList<String> argumentsBeforeGlobbing = new ArrayList<>();
        argumentsBeforeGlobbing.add(fileSeparator + "tmp" + fileSeparator + "Doc*");
        ArrayList<String> globbingResult = Application.globArguments(argumentsBeforeGlobbing, -1);
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add(fileSeparator + "tmp" + fileSeparator + "Documents");
        assertEqualsArrayLists(expectedResult, globbingResult);
    } 

    @Test
    public void testNoMatches() {
        ArrayList<String> argumentsBeforeGlobbing = new ArrayList<>();
        argumentsBeforeGlobbing.add("No*Match");
        ArrayList<String> globbingResult = Application.globArguments(argumentsBeforeGlobbing, -1);
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("No*Match");
        assertEqualsArrayLists(expectedResult, globbingResult);    
    }

    @Test
    public void testIgnoreIndex() {
        ArrayList<String> argumentsBeforeGlobbing = new ArrayList<>();
        argumentsBeforeGlobbing.add("Doc*");
        ArrayList<String> globbingResult = Application.globArguments(argumentsBeforeGlobbing, 0);
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("Doc*");
        assertEqualsArrayLists(expectedResult, globbingResult);
    }

    @Test
    public void testNoGlobbing() {
        ArrayList<String> argumentsBeforeGlobbing = new ArrayList<>();
        argumentsBeforeGlobbing.add("No");
        argumentsBeforeGlobbing.add("Globbing");
        ArrayList<String> globbingResult = Application.globArguments(argumentsBeforeGlobbing, -1);
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("No");
        expectedResult.add("Globbing");
        assertEqualsArrayLists(expectedResult, globbingResult);
    }

    @Test
    public void testMultipleGlobbingArguments() {
        ArrayList<String> argumentsBeforeGlobbing = new ArrayList<>();
        argumentsBeforeGlobbing.add("Doc*");
        argumentsBeforeGlobbing.add("S*t");
        ArrayList<String> globbingResult = Application.globArguments(argumentsBeforeGlobbing, -1);
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("Documents");
        expectedResult.add("Soft");
        assertEqualsArrayLists(expectedResult, globbingResult);
    }

    @Test
    public void testComplexAbsolutePathGlobbing() throws JshException {
        ArrayList<String> argumentsBeforeGlobbing = new ArrayList<>();
        argumentsBeforeGlobbing.add(fileSeparator + "tmp" + fileSeparator + "Doc*" + fileSeparator + "*g" + fileSeparator + "Co*");
        argumentsBeforeGlobbing.add(fileSeparator + "tmp" + fileSeparator + "Oth*" + fileSeparator + "Ot*");
        ArrayList<String> globbingResult = Application.globArguments(argumentsBeforeGlobbing, -1);
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add(fileSeparator + "tmp" + fileSeparator + "Documents" + fileSeparator + "Eng" + fileSeparator + "Code");
        expectedResult.add(fileSeparator + "tmp" + fileSeparator + "Other" + fileSeparator + "Oth1");
        expectedResult.add(fileSeparator + "tmp" + fileSeparator + "Other" + fileSeparator + "Oth2");
        assertEqualsArrayLists(expectedResult, globbingResult);
    }

}