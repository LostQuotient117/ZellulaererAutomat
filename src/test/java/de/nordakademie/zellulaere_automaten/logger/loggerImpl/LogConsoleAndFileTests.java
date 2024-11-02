package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the {@link LogConsoleAndFile} class.
 * It verifies the functionality of logging messages to both the console and a file.
 * The tests ensure that the log methods in {@link LogConsoleAndFile} correctly write
 * the expected output to the console and the specified log file.
 *
 * @author Jannick Gottschalk
 */
public class LogConsoleAndFileTests {
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private static String wantedString100x100;
    private static String wantedStringHeaderAndBody;

    /**
     * Sets up the test environment
     */
    @BeforeAll
    public static void setUP(){
        System.setOut(new PrintStream(outputStreamCaptor));
        StringBuilder wantedStringBuilder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            wantedStringBuilder.append("0".repeat(100));
            wantedStringBuilder.append(System.lineSeparator());
        }
        wantedString100x100 = wantedStringBuilder.toString();
        StringBuilder wantedStringBuilder2 = new StringBuilder();
        wantedStringBuilder2.append("### (99)").append(System.lineSeparator());
        for (int i = 0; i < 100; i++) {
            wantedStringBuilder2.append("0".repeat(100));
            wantedStringBuilder2.append(System.lineSeparator());
        }
        wantedStringHeaderAndBody = wantedStringBuilder2.toString();
    }

    /**
     * Tests the {@link LogConsoleAndFile#writeLog(String, int, String)} method to ensure that it writes the expected output to the console and the specified log file.
     */
    @Test
    public void writeLog_FormattedGridIterationNumberAndClassName_ShouldWriteConsoleAndFile(){
        LogConsoleAndFile mockLogConsoleAndFile = mock(LogConsoleAndFile.class);
        String projectRoot = Paths.get("").toAbsolutePath().toString();
        String downloadPath = Paths.get(projectRoot, ("Test.log")).toString();
        outputStreamCaptor.reset();

        // Deletion of old file for the new test
        File file = new File(downloadPath);
        file.delete();

        doAnswer(invocation -> {
            System.out.print(wantedStringHeaderAndBody);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(downloadPath))) {
                writer.write(wantedStringHeaderAndBody);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }).when(mockLogConsoleAndFile).writeLog(wantedString100x100.trim(), 99, "Test");

        mockLogConsoleAndFile.writeLog(wantedString100x100.trim(), 99, "Test");
        verify(mockLogConsoleAndFile).writeLog(wantedString100x100.trim(), 99, "Test");

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
        assertEquals(wantedStringHeaderAndBody, outputStreamCaptor.toString());
    }

    /**
     * Tests the {@link LogConsoleAndFile#writeLog(String, String)} method to ensure that it writes the expected output to the console and the specified log file.
     */
    @Test
    public void writeLog_FormattedGridAndClassName_ShouldWriteConsoleAndFileEndMessage(){
        LogConsoleAndFile mockLogConsoleAndFile = mock(LogConsoleAndFile.class);
        String endMessage =  "Experiment stopped: Reached 100 iterations.";
        String projectRoot = Paths.get("").toAbsolutePath().toString();
        String downloadPath = Paths.get(projectRoot, ("Test.log")).toString();
        outputStreamCaptor.reset();

        // Deletion of old file for the new test
        File file = new File(downloadPath);
        file.delete();

        doAnswer(invocation -> {
            System.out.print(endMessage);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(downloadPath))) {
                writer.write(endMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }).when(mockLogConsoleAndFile).writeLog(wantedString100x100.trim(), "Test");

        mockLogConsoleAndFile.writeLog(wantedString100x100.trim(), "Test");
        verify(mockLogConsoleAndFile).writeLog(wantedString100x100.trim(), "Test");

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(downloadPath))) {
            String line;
            while ((line = reader.readLine()) != null){
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(endMessage.trim(), fileContent.toString().trim());
        assertEquals(endMessage, outputStreamCaptor.toString());
    }
}
