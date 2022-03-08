package uk.ac.ucl.jsh.Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import uk.ac.ucl.jsh.Jsh;

/**
 * The FileSystem class that follows the Singleton pattern and stores and provides access to the current working directory for all Applications.
 * It also provides utility functions for Applications and for testing.
 */
public final class FileSystem {
    /**
     * The reference to the only instance of the FileSystem class
     */
    private static final FileSystem INSTANCE = new FileSystem(System.getProperty("user.dir"));
    /**
     * String represeting the Path to the current working directory
     */
    private String workingDirectoryPath;

    /**
     * Constructs the only instance of the FileSystem class, taking in a path that will be assigned to the workingDirectoryPath;
     * 
     * @param workingDirectoryPath Path to the folder that will become the FileSystem's workingDirectoryPath
     */
    private FileSystem(String workingDirectoryPath) {
        this.setWorkingDirectory(workingDirectoryPath);
    }

    /**
     * Getter function for the FileSystem instance
     * @return The reference to the only instance of the FileSystem class
     */
    public static FileSystem getInstance() {
        return INSTANCE;
    }


    /**
     * Utility function used to delete a directory and all the files it contains
     * 
     * @param crtDirectory The directory that needs to be deleted
     */
    private void deleteDirectory(File crtDirectory) {
        File[] files = crtDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                deleteDirectory(file);
            }
         }
        crtDirectory.delete();
    }

    /**
     * Utility function used to populate files with the same piece of text that is used for testing the Applications
     * 
     * @return A string representing the content that will be written to the file
     */
    private String generateFileText() {
        String resultString = new String();
        resultString += "This is a test" + Jsh.lineSeparator;
        resultString += "This is a test of another test" + Jsh.lineSeparator;
        resultString += Jsh.lineSeparator;
        return resultString;
    }

    /**
     * Utility function used to populate files with a certain amount of lines of text that are used for testing the Applications
     * 
     * @param lines Number of lines of text to generate
     * @return      A string representing the content that will be written to the file
     */
    private String generateLongFileText(int lines) {
        String resultString = new String();
        for(int i = 0; i < lines; ++ i) {
            resultString += "Line number: " + Integer.toString(i) + Jsh.lineSeparator;
        }
        return resultString;
    }

    /**
     * Getter function for the current working directory, used by the Applications
     * 
     * @return A string representing the path to the current working directory
     */
    public String getWorkingDirectoryPath() {
        return this.workingDirectoryPath;
    }

    /**
     * Setter function for the current working directory, used by the Applications and by Test classes
     * 
     * @param workingDirectoryPath
     */
    public void setWorkingDirectory(String workingDirectoryPath) {
        this.workingDirectoryPath = workingDirectoryPath;
    }

    /**
     * Utility function used by the Applications to abstract away the concern of absolute path or relative path to files given as argument
     * 
     * @param filePath      A string representing an absolute or relative path to a File given as argument to the Application
     * @return              A file at the correct absolute path
     * @throws JshException The exception thrown if the filePath is invalid
     */
    public File getFile(String filePath) throws JshException {
        try {
            return new File(getFilePath(filePath));
        } catch (NullPointerException e) {
            throw new JshException(e.getMessage());
        }
    }

    /**
     * Utility function that takes a String representing an absolute or relative path to a File and returns a String representing the correct
     * absolute path of the filePath given as argument
     * 
     * @param filePath A string representing a relative or absolute path to a File 
     * @return         A string representing the correct absolute path to the File at the path filePath
     */
    public String getFilePath(String filePath) {
        if(filePath.startsWith(Jsh.fileSeparator)) {
            return filePath;
        }
        
        return workingDirectoryPath + Jsh.fileSeparator + filePath;
    }

    /**
     * Utility function used to create a File Hierarchy that is used by the Test classes
     * 
     * @throws IOException Exception being thrown if the File hierearchy fails to create
     */
    public void createTestFileHierarchy() throws IOException {
         // Create two utilities Strings
         String tmpPath = System.getProperty("java.io.tmpdir");
         String fileSeparator = Jsh.fileSeparator;
 
         // Create tmp's children
         Path documentsPath = Files.createDirectory(Paths.get(tmpPath + fileSeparator + "Documents"));
         Path otherPath = Files.createDirectory(Paths.get(tmpPath + fileSeparator + "Other"));
         Path softFilePath = Files.createFile(Paths.get(tmpPath + fileSeparator + "Soft"));
         Path helloFilePath = Files.createFile(Paths.get(tmpPath + fileSeparator + "Hello"));

         // Create Documents's children
         Path engPath = Files.createDirectory(Paths.get(documentsPath + fileSeparator + "Eng"));
         Path wareFilePath = Files.createFile(Paths.get(documentsPath + fileSeparator + "Ware"));
         Files.createFile(Paths.get(documentsPath + fileSeparator + "Proj.txt"));
         
         // Create Eng's children
         Path testFilePath = Files.createFile(Paths.get(engPath + fileSeparator + "Test"));
         Files.createFile(Paths.get(engPath + fileSeparator + "Code"));
         Files.createFile(Paths.get(engPath + fileSeparator + "Plan"));
 
         // Create Other's children
         Path oth1FilePath = Files.createFile(Paths.get(otherPath + fileSeparator + "Oth1"));
         Path oth2FilePath = Files.createFile(Paths.get(otherPath + fileSeparator + "Oth2"));
         Files.createFile(Paths.get(otherPath + fileSeparator + ".test"));
         Files.createDirectory(Paths.get(otherPath + fileSeparator + "Empty"));


         // Write to the created files
         Files.write(softFilePath, generateFileText().getBytes(),     StandardOpenOption.APPEND);
         Files.write(helloFilePath, "hello".getBytes(),               StandardOpenOption.APPEND);
         Files.write(wareFilePath, generateFileText().getBytes(),     StandardOpenOption.APPEND);
         Files.write(oth1FilePath, generateFileText().getBytes(),     StandardOpenOption.APPEND);
         Files.write(oth2FilePath, generateFileText().getBytes(),     StandardOpenOption.APPEND);
         Files.write(testFilePath, generateLongFileText(20).getBytes(), StandardOpenOption.APPEND);
        
    }
    
    /**
     * Utility function used to delete the File Hierarchy that is used by the Test classes
     * 
     * @throws IOException Exception being thrown if the file Hierarchy fails to delete
     */
    public void deleteTestFileHierarchy() throws IOException {
        String tmpPath = System.getProperty("java.io.tmpdir");

        deleteDirectory(new File(tmpPath+Jsh.fileSeparator+"Documents"));
        deleteDirectory(new File(tmpPath+Jsh.fileSeparator+"Other"));
        Files.deleteIfExists(Paths.get(tmpPath + Jsh.fileSeparator + "Soft"));
        Files.deleteIfExists(Paths.get(tmpPath + Jsh.fileSeparator + "Hello"));
    }

}
