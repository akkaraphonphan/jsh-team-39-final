package uk.ac.ucl.jsh.Utilities;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;

import uk.ac.ucl.jsh.Jsh;

/**
 * Globbing class that contains the logic required to perform argument globbing for Applications
 */
public class Globbing {
   /**
     * Utility string that ensures that the paths created by globbing are not system-dependent
     */
    private static String fileSeparator = Jsh.fileSeparator;
    /**
     * ArrayList storing the globbing result
     */
    private static ArrayList<String> globbedArguments;

    /**
     * Function that recursively performs globbing for one argument
     * 
     * @param currentGlobbedPath The current path that was expanded and does not contain any globbing character
     * @param unglobbedPath      The current path that still contains globbing characters and still needs to be expanded
     * @param startingPath       The path that the final globbed path is relative to
     */
    private static void globArgument(String currentGlobbedPath, String unglobbedPath, String startingPath) {
        if(unglobbedPath != "") {
            String globbingPattern, remainingUnglobbedPath;
            if(unglobbedPath.contains(fileSeparator)) {
                globbingPattern = unglobbedPath.substring(0, unglobbedPath.indexOf(fileSeparator));
                remainingUnglobbedPath = unglobbedPath.substring(unglobbedPath.indexOf(fileSeparator) + 1, unglobbedPath.length());
            } 
            else {
                globbingPattern = unglobbedPath;
                remainingUnglobbedPath = "";
            }

            if(globbingPattern.contains("*")) {
                File currentFile = new File(currentGlobbedPath);
                File[] fileArray = currentFile.listFiles();
                PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + globbingPattern);
                for(File file: fileArray) {
                    if(matcher.matches(Paths.get(file.getName()))) {
                        globArgument(currentGlobbedPath + fileSeparator + file.getName(), remainingUnglobbedPath, startingPath);
                    }
                }
            }
            else {
                globArgument(currentGlobbedPath + fileSeparator + globbingPattern, remainingUnglobbedPath, startingPath);
            }

        }
        else {
            String resultingGlobbedPath = Paths.get(startingPath).relativize(Paths.get(currentGlobbedPath)).toString();
            if (startingPath.equals(fileSeparator)) {
                resultingGlobbedPath = fileSeparator + resultingGlobbedPath;
            }
            globbedArguments.add(resultingGlobbedPath);
        }
    }

    /**
     * Function that performs Performs globbing of the applicationArguments. In some cases not all arguments should be globbed
     * (eg for the Find Application the pattern argument should not be globbed), therefore there is a parameter
     * called ignore index for which the argument at that index is not globbed, but added directly to the result
     * 
     * @param applicationArguments  The list of arguments before perfomming globbing
     * @param ignoreIndex           The index of the argument in the applicationArguments that will not be globbed and will be added as it is
     * @return                      An ArrayList of Strings containing the globbing result
     */
    public static ArrayList<String> globArguments(ArrayList<String> applicationArguments, int ignoreIndex) {
        globbedArguments = new ArrayList<String>();
        if(applicationArguments.size() == 0) {
            return globbedArguments;
        }
        
        for(int i = 0; i < applicationArguments.size(); ++ i) {
            String currentArgument = applicationArguments.get(i);
            if(i == ignoreIndex) {
                globbedArguments.add(currentArgument);
                continue;
            }

            int globbedArgumentsSize = globbedArguments.size();
            if(currentArgument.contains("*")) {
                if(currentArgument.startsWith(fileSeparator)) {
                    globArgument("", currentArgument, fileSeparator);
                }
                else {
                    globArgument(FileSystem.getInstance().getWorkingDirectoryPath(), currentArgument, FileSystem.getInstance().getWorkingDirectoryPath());
                }

                if(globbedArgumentsSize == globbedArguments.size()) {
                    globbedArguments.add(currentArgument);
                }
            }
            else {
                globbedArguments.add(currentArgument);
            }
        }
        return globbedArguments;
    }
}