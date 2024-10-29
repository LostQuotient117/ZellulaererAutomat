package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import org.junit.jupiter.api.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This Test-Class tests the methods of the {@link LogFile}-class
 */
public class LogFileTests {
    private static String wantedString100x100;
    private static String wantedStringHeaderAndBody;
    private static String wantedStringHeaderAndBody100Iterations;


    /**
     *Sets up specific string variables required for testing different scenarios.
     * @param testInfo Provides information about the currently running test, including its display name.
     */
    @BeforeEach
    public void setUpVariables(TestInfo testInfo){
        String test = testInfo.getDisplayName();
        if (test.equals("buildStringForFile_GridAndIteration_ShouldReturnGridStringWithIteration()") ||
                test.equals("writeLog_GridStringAndIteration_ShouldWriteFile()") ||
                (test.equals("writeLog100Iterations_gridWithHeaderAndBody_ShouldWriteFile()"))){
            StringBuilder wantedStringBuilder = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                wantedStringBuilder.append("0".repeat(100));
                wantedStringBuilder.append(System.lineSeparator());
            }
            wantedString100x100 = wantedStringBuilder.toString();
        }
        if (test.equals("exportGridToFile_GridWithHeaderAndBody_ShouldWriteFileWithGrid()") ||
            test.equals("writeLog_GridStringAndIteration_ShouldWriteFile()")){
            StringBuilder wantedStringBuilder = new StringBuilder();
            wantedStringBuilder.append("### (99)").append(System.lineSeparator());
            for (int i = 0; i < 100; i++) {
                wantedStringBuilder.append("0".repeat(100));
                wantedStringBuilder.append(System.lineSeparator());
            }
            wantedStringHeaderAndBody = wantedStringBuilder.toString();
        }
        if (test.equals("writeLog100Iterations_gridWithHeaderAndBody_ShouldWriteFile()")){
            StringBuilder wantedStringBuilder = new StringBuilder();
            for (int i = 1; i <= 100; i++) {
                wantedStringBuilder.append("### (").append(i).append(")").append(System.lineSeparator());
                for (int j = 0; j < 100; j++) {
                    wantedStringBuilder.append("0".repeat(100));
                    wantedStringBuilder.append(System.lineSeparator());
                }
                wantedStringBuilder.append(System.lineSeparator());
            }
            wantedStringHeaderAndBody100Iterations = wantedStringBuilder.toString();
        }
    }
    /**
     * Tests the private method {@code buildStringForFile} of the {@code LogFile} class.
     * <p>
     * This test uses reflection to access the private method and verifies that the method
     * correctly builds a string with a header and a 100x100 grid of zeros.
     * </p>
     *
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException if the underlying method is inaccessible
     * @throws NoSuchMethodException if the method cannot be found
     */
    @Test
    public void buildStringForFile_GridAndIteration_ShouldReturnGridStringWithIteration() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        LogFile logFile = new LogFile();
        Method privateBuildStringForFile = LogFile.class.getDeclaredMethod("buildStringForFile", String.class, int.class);
        privateBuildStringForFile.setAccessible(true);
        String result = (String) privateBuildStringForFile.invoke(logFile, wantedString100x100, 99);
        Assertions.assertEquals(wantedStringHeaderAndBody, result);
    }
    /**
     * Tests the private method {@code exportGridToFile} of the {@code LogFile} class.
     * <p> This test uses reflection to access the private method and verifies that the method
     * correctly exports a string with a header and a 100x100 grid of zeros to a file.</p>
     *
     * @throws NoSuchMethodException if the method cannot be found
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException if the underlying method is inaccessible
     */
    @Test
    public void exportGridToFile_GridWithHeaderAndBody_ShouldWriteFileWithGrid() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LogFile logFile = new LogFile();
        String filename = "src/main/java/de/nordakademie/zellulaere_automaten/logger/loggerOutput/Log.log";

        //deletion of old file for the new test
        File file = new File(filename);
        file.delete();

        Method privateExportGridToFile = LogFile.class.getDeclaredMethod("exportGridToFile", String.class);
        privateExportGridToFile.setAccessible(true);
        privateExportGridToFile.invoke(logFile, wantedStringHeaderAndBody);

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null){
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(wantedStringHeaderAndBody.trim(), fileContent.toString().trim());
    }
    /**
     * Tests the {@link LogFile#writeLog(String formattedGrid, int iteration)}.
     * <p> This test verifies that the method correctly writes a string with a header and a 100x100 grid of zeros to a file.</p>
     */
    @Test
    public void writeLog_GridStringAndIteration_ShouldWriteFile(){
        LogFile logFile = new LogFile();
        String filename = "src/main/java/de/nordakademie/zellulaere_automaten/logger/loggerOutput/Log.log";

        //deletion of old file for the new test
        File file = new File(filename);
        file.delete();

        logFile.writeLog(wantedString100x100, 99);

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null){
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(wantedStringHeaderAndBody.trim(), fileContent.toString().trim());
    }
    /**
     * Tests {@link LogFile#writeLog(String, int)} with 100 iterations.
     * <p> This test verifies that the method correctly writes a string with a header and a 100x100 grid of zeros to a file
     * for 100 iterations.</p>
     */
    @Test
    public void writeLog100Iterations_gridWithHeaderAndBody_ShouldWriteFile(){
        LogFile logFile = new LogFile();
        String filename = "src/main/java/de/nordakademie/zellulaere_automaten/logger/loggerOutput/Log.log";

        //deletion of old file for the new test
        File file = new File(filename);
        file.delete();

        for (int i = 1; i <= 100; i++) {
            logFile.writeLog(wantedString100x100, i);
        }

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null){
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(wantedStringHeaderAndBody100Iterations.trim(), fileContent.toString().trim());
    }
    /**
     * Tests the {@link LogFile#writeLog(String)} method with an end message.
     * This test verifies that the method correctly writes the end message to the log file.
     * It deletes any existing log file before the test, writes the end message, and then
     * reads the file to ensure the content matches the expected end message.
     */
    @Test
    public void writeLog_EndMessage_EndMessageAsFile() {
        LogFile logFile = new LogFile();
        String filename = "src/main/java/de/nordakademie/zellulaere_automaten/logger/loggerOutput/Log.log";
        String endMessage = "Experiment stopped: Reached 100 iterations.";

        // Deletion of old file for the new test
        File file = new File(filename);
        file.delete();

        logFile.writeLog(endMessage);

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(endMessage.trim(), fileContent.toString().trim());
    }
}