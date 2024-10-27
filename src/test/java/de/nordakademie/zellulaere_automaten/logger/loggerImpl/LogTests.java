package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.logger.LoggerFactory;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Objects;

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
        if (test.equals("log_gridStringAndIteration_LogConsoleOutput()") || test.equals("log_gridStringAndIteration_LogConsoleOutput100Iterations()")){
            StringBuilder wantedStringBuilder = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                wantedStringBuilder.append("0".repeat(100));
                wantedStringBuilder.append(System.lineSeparator());
            }
            givenString100x100 = wantedStringBuilder.toString();
        }
        if (test.equals("log_gridStringAndIteration_LogConsoleOutput()")){
                StringBuilder wantedStringBuilder = new StringBuilder();
                wantedStringBuilder.append("### (99)").append(System.lineSeparator());
                for (int i = 0; i < 100; i++) {
                    wantedStringBuilder.append("0".repeat(100));
                    wantedStringBuilder.append(System.lineSeparator());
                }
                wantedStringHeaderAndBody = wantedStringBuilder.toString();
        }
        if (test.equals("log_gridStringAndIteration_LogConsoleOutput100Iterations()")){
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
        if (test.equals("log_gridStringAndIteration_LogConsoleOutput()") || test.equals("log_gridStringAndIteration_LogConsoleOutput100Iterations()")){
            System.setOut(new PrintStream(outputStreamCaptor));
        }
    }
    //region LogTests

    /**
     * Tests the logging functionality by comparing the console output with the expected string.
     * <p>
     * This test creates a logger using {@link LoggerFactory} with a user input of {@code "2"} for {@link LogConsole}.
     * It then logs a trimmed version of {@code givenString100x100} with 99 iterations.
     * The output is captured and compared to {@code wantedStringHeaderAndBody} to ensure they match.
     * </p>
     */
    @Test
    public void log_gridStringAndIteration_LogConsoleOutput() {
        LoggerFactory loggerFactory = new LoggerFactory();
        // "2" as userInput for LogConsole
        ILogger log = loggerFactory.createLogger("2");
        //99 for testing
        outputStreamCaptor.reset();
        log.log(givenString100x100.trim(), 99);
        Assertions.assertEquals(wantedStringHeaderAndBody, outputStreamCaptor.toString());
    }

    /**
     * Tests the logging functionality over 100 iterations by comparing the console output with the expected string.
     * <p>This test creates a logger using {@link LoggerFactory} with a user input of {@code "2"} for {@link LogConsole}.
     * It logs a trimmed version of {@code givenString100x100} for each iteration from 1 to 100.
     * The output is captured and compared line by line to {@code wantedStringHeaderAndBody100Iterations} to ensure they match.</p>
     * <p>The comparison is done line by line to ignore any whitespace differences, as {@code outStreamCaptor} does not capture leading or trailing spaces.</p>
     */
    @Test
    public void log_gridStringAndIteration_LogConsoleOutput100Iterations() {
        LoggerFactory loggerFactory = new LoggerFactory();
        // "2" as userInput for LogConsole
        ILogger log = loggerFactory.createLogger("2");
        outputStreamCaptor.reset();
        for (int i = 1; i <= 100; i++) {
            log.log(givenString100x100.trim(), i);
        }
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

    //TODO: Tests for LogFile
    //endregion
}
