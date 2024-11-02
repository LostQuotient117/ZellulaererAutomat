package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
/**
 * The {@code LogFileTests} class contains unit tests for the {@link LogFile}-class.
 * These tests verify the functionality of methods responsible for writing log data to files.
 * The tests cover various scenarios including writing formatted grid data, end messages, and handling multiple iterations.
 *
 * @author Jannick Gottschalk
 */
public class LogFileTests {
    private static String wantedString100x100;
    private static String wantedStringHeaderAndBody;
    private static String wantedStringHeaderAndBody100Iterations;

    //region setUpVariables
    /**
     *Sets up specific string variables required for testing different scenarios.
     * @param testInfo Provides information about the currently running test, including its display name.
     */
    @BeforeEach
    public void setUpVariables(TestInfo testInfo){
        String test = testInfo.getDisplayName();
        if (test.equals("buildStringForFile_GridAndIteration_ShouldReturnGridStringWithIteration()") ||
                test.equals("writeLog_GridStringAndIteration_ShouldWriteFile()") ||
                (test.equals("writeLog100Iterations_gridWithHeaderAndBody_ShouldWriteFile()")) ||
                (test.equals("writeLog_GridStringIterationAndClassName_ShouldWriteFile()"))){
            StringBuilder wantedStringBuilder = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                wantedStringBuilder.append("0".repeat(100));
                wantedStringBuilder.append(System.lineSeparator());
            }
            wantedString100x100 = wantedStringBuilder.toString();
        }
        if (test.equals("exportGridToFile_GridWithHeaderAndBody_ShouldWriteFileWithGrid()") ||
                test.equals("buildStringForFile_GridAndIteration_ShouldReturnGridStringWithIteration()") ||
                test.equals("writeLog_GridStringAndIteration_ShouldWriteFile()") ||
                test.equals("writeLog_GridStringIterationAndClassName_ShouldWriteFile()")){
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
    //endregion
    /**
     * Tests the private method {@code buildStringForFile} of the {@code LogFile} class.
     * This test uses reflection to access the private method and verifies that the method
     * correctly builds a string with a header and a 100x100 grid of zeros.
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
        assertEquals(wantedStringHeaderAndBody, result);
    }
    /**
     * Tests the private method {@code exportGridToFile} of the {@code LogFile} class.
     * This test uses reflection to access the private method and verifies that the method
     * correctly exports a string with a header and a 100x100 grid of zeros to a file.
     *
     * @throws NoSuchMethodException if the method cannot be found
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException if the underlying method is inaccessible
     */
    @Test
    public void exportGridToFile_GridWithHeaderAndBody_ShouldWriteFileWithGrid() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LogFile logFile = new LogFile();
        String projectRoot = Paths.get("").toAbsolutePath().toString();
        String downloadPath = Paths.get(projectRoot, ("Test.log")).toString();

        try (FileWriter writer = new FileWriter(downloadPath, false)) {
            writer.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Method privateExportGridToFile = LogFile.class.getDeclaredMethod("exportGridToFile", String.class, String.class);
        privateExportGridToFile.setAccessible(true);
        privateExportGridToFile.invoke(logFile, wantedStringHeaderAndBody, "Test");

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(downloadPath))) {
            String line;
            while ((line = reader.readLine()) != null){
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(wantedStringHeaderAndBody.trim(), fileContent.toString().trim());
    }
    /**
     * Tests the {@link LogFile#writeLog(String, int, String)}.
     * This test verifies that the method correctly writes a string with a header and a 100x100 grid of zeros to a file.
     */
    @Test
    public void writeLog_GridStringIterationAndClassName_ShouldWriteFile(){
        LogFile mockLogFile = mock(LogFile.class);
        String projectRoot = Paths.get("").toAbsolutePath().toString();
        String downloadPath = Paths.get(projectRoot, ("Test.log")).toString();

        try (FileWriter writer = new FileWriter(downloadPath, false)) {
            writer.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        doAnswer(invocation -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(downloadPath))) {
                writer.write(wantedStringHeaderAndBody);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }).when(mockLogFile).writeLog(wantedString100x100.trim(), 99, "Test");

        mockLogFile.writeLog(wantedString100x100.trim(), 99, "Test");
        verify(mockLogFile).writeLog(wantedString100x100.trim(), 99, "Test");

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(downloadPath))) {
            String line;
            while ((line = reader.readLine()) != null){
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(wantedStringHeaderAndBody.trim(), fileContent.toString().trim());
    }
    /**
     * Tests {@link LogFile#writeLog(String, int, String)} with 100 iterations.
     * This test verifies that the method correctly writes a string with a header and a 100x100 grid of zeros to a file
     * for 100 iterations.
     */
    @Test
    public void writeLog100Iterations_gridWithHeaderAndBody_ShouldWriteFile(){
        LogFile mockLogFile = mock(LogFile.class);
        String projectRoot = Paths.get("").toAbsolutePath().toString();
        String downloadPath = Paths.get(projectRoot, ("Test.log")).toString();

        try (FileWriter writer = new FileWriter(downloadPath, false)) {
            writer.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        doAnswer(invocation -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(downloadPath))) {
                writer.write(wantedStringHeaderAndBody100Iterations);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }).when(mockLogFile).writeLog(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString());

        for (int i = 1; i <= 100; i++) {
            mockLogFile.writeLog(wantedString100x100, i, "Test");
        }
        verify(mockLogFile, Mockito.times(100)).writeLog(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString());

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(downloadPath))) {
            String line;
            while ((line = reader.readLine()) != null){
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(wantedStringHeaderAndBody100Iterations.trim(), fileContent.toString().trim());
    }
    /**
     * Tests the {@link LogFile#writeLog(String, String)} method with an end message.
     * This test verifies that the method correctly writes the end message to the log file.
     * It deletes any existing log file before the test, writes the end message, and then
     * reads the file to ensure the content matches the expected end message.
     */
    @Test
    public void writeLog_EndMessage_EndMessageAsFile() {
        LogFile mockLogFile = mock(LogFile.class);
        String projectRoot = Paths.get("").toAbsolutePath().toString();
        String downloadPath = Paths.get(projectRoot, ("Test.log")).toString();
        String endMessage = "Experiment stopped: Reached 100 iterations.";

        // Overwriting of old file for the new test
        try (FileWriter writer = new FileWriter(downloadPath, false)) {
            writer.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        doAnswer(invocation -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(downloadPath))) {
                writer.write(endMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }).when(mockLogFile).writeLog(endMessage, "Test");

        mockLogFile.writeLog(endMessage, "Test");
        verify(mockLogFile).writeLog(endMessage, "Test");

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(downloadPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(endMessage.trim(), fileContent.toString().trim());
    }
}