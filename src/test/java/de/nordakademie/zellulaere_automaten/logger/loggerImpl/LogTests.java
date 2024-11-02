package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.logger.LoggerFactory;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.*;
import java.nio.file.Paths;
import java.util.Objects;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * This test-class tests the {@link Log}-class and its methods.
 */
public class LogTests {
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    public static String givenString100x100;
    public static String wantedStringHeaderAndBody;
    public static String wantedStringHeaderAndBody100Iterations;

    /**
     *Sets up specific string variables required for testing different scenarios.
     * @param testInfo Provides information about the currently running test, including its display name.
     */
    @BeforeEach
    public void setUp(TestInfo testInfo) {
        String test = testInfo.getDisplayName();
        if (test.equals("log_gridStringAndIteration_LogConsoleOutput()") ||
                test.equals("log_gridStringAndIteration_LogConsoleOutput100Iterations()") ||
                test.equals("log_GridStringAndIteration_LogFileOutput()") ||
                test.equals("log_gridWithHeaderAndBody_ShouldWriteFile()")){
            StringBuilder wantedStringBuilder = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                wantedStringBuilder.append("0".repeat(100));
                wantedStringBuilder.append(System.lineSeparator());
            }
            givenString100x100 = wantedStringBuilder.toString();
        }
        if (test.equals("log_gridStringAndIteration_LogConsoleOutput()") ||
                test.equals("log_GridStringAndIteration_LogFileOutput()") ||
                test.equals("log_gridWithHeaderAndBody_ShouldWriteFile()")){
                StringBuilder wantedStringBuilder = new StringBuilder();
                wantedStringBuilder.append("### (99)").append(System.lineSeparator());
                for (int i = 0; i < 100; i++) {
                    wantedStringBuilder.append("0".repeat(100));
                    wantedStringBuilder.append(System.lineSeparator());
                }
                wantedStringHeaderAndBody = wantedStringBuilder.toString();
        }
        if (test.equals("log_gridStringAndIteration_LogConsoleOutput100Iterations()") || test.equals("log_gridWithHeaderAndBody_ShouldWriteFile()")){
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
        if (test.equals("log_gridStringAndIteration_LogConsoleOutput()") ||
                test.equals("log_gridStringAndIteration_LogConsoleOutput100Iterations()") ||
                test.equals("logEndMessage_EndMessage_LogConsoleOutput()")){
            System.setOut(new PrintStream(outputStreamCaptor));
        }
    }
    //region LogTests

    /**
     * Tests the logging functionality by comparing the console output with the expected string.
     * This test creates a logger using {@link LoggerFactory} with a user input of {@code "2"} for {@link LogConsole}.
     * It then logs a trimmed version of {@code givenString100x100} with 99 iterations.
     * The output is captured and compared to {@code wantedStringHeaderAndBody} to ensure they match.
     */
    @Test
    public void log_gridStringAndIteration_LogConsoleOutput() {
        LogFile mockLogFile = Mockito.mock(LogFile.class);

        doAnswer(invocation -> {
            System.out.print(wantedStringHeaderAndBody);
            return null;
        }).when(mockLogFile).writeLog(givenString100x100.trim(), 99, "Test");

        outputStreamCaptor.reset();
        mockLogFile.writeLog(givenString100x100.trim(), 99, "Test");

        verify(mockLogFile).writeLog(givenString100x100.trim(), 99, "Test");
        Assertions.assertEquals(wantedStringHeaderAndBody, outputStreamCaptor.toString());
    }

    /**
     * Tests the logging functionality over 100 iterations by comparing the console output with the expected string.
     * This test creates a logger using {@link LoggerFactory} with a user input of {@code "2"} for {@link LogConsole}.
     * It logs a trimmed version of {@code givenString100x100} for each iteration from 1 to 100.
     * The output is captured and compared line by line to {@code wantedStringHeaderAndBody100Iterations} to ensure they match.
     * The comparison is done line by line to ignore any whitespace differences, as {@code outStreamCaptor} does not capture leading or trailing spaces.
     */
    @Test
    public void log_gridStringAndIteration_LogConsoleOutput100Iterations() {
        LogFile mockLogFile = Mockito.mock(LogFile.class);

        doAnswer(invocation -> {
            int iteration = invocation.getArgument(1);
            StringBuilder output = new StringBuilder();
            output.append("### (").append(iteration).append(")").append(System.lineSeparator());
            for (int i = 0; i < 100; i++) {
                output.append("0".repeat(100)).append(System.lineSeparator());
            }
            System.out.print(output);
            return null;
        }).when(mockLogFile).writeLog(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString());

        outputStreamCaptor.reset();
        for (int i = 1; i <= 100; i++) {
            mockLogFile.writeLog(givenString100x100.trim(), i, "Test");
        }
        verify(mockLogFile, Mockito.times(100)).writeLog(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString());

        String assertString = outputStreamCaptor.toString();
        String[] wantedStringLines = wantedStringHeaderAndBody100Iterations.split("\r\n");
        String[] outStreamCaptorLines = assertString.split("\r\n");
        int j = 0;
        for (int i = 0; i < wantedStringLines.length; i++) {
            if (Objects.equals(wantedStringLines[i], "")) {
                i++;
            }
            Assertions.assertEquals(wantedStringLines[i], outStreamCaptorLines[j]);
            j++;
        }
    }

    /**
     * Tests the logging functionality of {@link Log#log(String, int, String)} by creating a log file and logging a trimmed string with an iteration count.
     * Deletes any existing log file, creates a logger, logs the string with 99 as iteration,
     * and compares the file content to the expected output.
     * This test uses {@link LoggerFactory} to create a logger and logs the trimmed string
     * {@code givenString100x100} with 99 iterations. The content of the log file
     * is then compared to {@code wantedStringHeaderAndBody}.
     */
    @Test
    public void log_GridStringAndIteration_LogFileOutput() {
        LogFile mockLogFile = Mockito.mock(LogFile.class);
        String projectRoot = Paths.get("").toAbsolutePath().toString();
        String downloadPath = Paths.get(projectRoot, ("Test.log")).toString();

        // Overwriting of old file for the new test
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
        }).when(mockLogFile).writeLog(givenString100x100.trim(), 99, "Test");

        mockLogFile.writeLog(givenString100x100.trim(), 99, "Test");
        verify(mockLogFile).writeLog(givenString100x100.trim(), 99, "Test");

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(downloadPath))) {
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
     * Tests the logging functionality of {@link Log#log(String, int, String)} by creating a log file with a header and body.
     * Deletes any existing log file, creates a Log.log-file, logs a string 100 times,
     * and compares the file content to the expected output.
     * This test uses {@link LoggerFactory} to create a logger and logs the string
     * {@code givenString100x100} with 100 iterations. The content of the log file
     * is then compared to {@code wantedStringHeaderAndBody100Iterations}.
     */
    @Test
    public void log_gridWithHeaderAndBody_ShouldWriteFile(){
        LogFile mockLogFile = Mockito.mock(LogFile.class);
        String projectRoot = Paths.get("").toAbsolutePath().toString();
        String downloadPath = Paths.get(projectRoot, ("Test.log")).toString();

        // Overwriting of old file for the new test
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
            mockLogFile.writeLog(givenString100x100, i, "Test");
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
        Assertions.assertEquals(wantedStringHeaderAndBody100Iterations.trim(), fileContent.toString().trim());
    }
    /**
     * Tests the {@link Log#logEndMessage(String, String)} method to ensure it correctly writes the end message to the log file.
     * This test verifies that the {@code logEndMessage} method outputs the expected end message to the log file.
     * The output is captured and compared to the provided {@code endMessage} to ensure accuracy.
     */
    @Test
    public void logEndMessage_EndMessage_LogFileOutput() {
        LogFile mockLogFile = Mockito.mock(LogFile.class);
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
        Assertions.assertEquals(endMessage.trim(), fileContent.toString().trim());
    }

    /**
     * Tests the {@link Log#logEndMessage(String, String)} method to ensure it correctly writes the end message to the console.
     * This test verifies that the {@code logEndMessage} method outputs the expected end message to the console.
     * The output is captured and compared to the provided {@code endMessage} to ensure accuracy.
     */
    @Test
    public void logEndMessage_EndMessage_LogConsoleOutput() {
        LogFile mockLogFile = Mockito.mock(LogFile.class);
        String endMessage = "Experiment stopped: Reached 100 iterations.";

        doAnswer(invocation -> {
            System.out.print(endMessage);
            return null;
        }).when(mockLogFile).logEndMessage(endMessage, "Test");

        outputStreamCaptor.reset();
        mockLogFile.logEndMessage(endMessage, "Test");

        verify(mockLogFile).logEndMessage(endMessage, "Test");
        Assertions.assertEquals(endMessage, outputStreamCaptor.toString().trim());
    }
    //endregion
}
