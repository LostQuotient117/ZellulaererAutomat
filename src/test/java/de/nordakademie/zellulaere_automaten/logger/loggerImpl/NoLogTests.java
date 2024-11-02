package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link NoLog} class.
 * This class contains tests to verify the behavior of the {@code NoLog} class,
 * ensuring that no logs are printed when the methods are called.
 */
public class NoLogTests {
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Sets up the output stream to capture the console output for testing.
     * This method is executed before each test to ensure that the console output
     * is redirected to the {@code outputStreamCaptor}.
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Tests the {@link NoLog#writeLog(String, int, String)} method to ensure that no logs are printed.
     * This test verifies that the {@code writeLog} method does not print any logs to the console
     * when called with a formatted grid, iteration, and class name. The output is captured and
     * compared to the expected message to ensure that no logs are printed.
     */
    @Test
    public void writeLog_WithFormattedGrid_ShouldPrintMessageOnce() {
        NoLog noLog = new NoLog();
        String formattedGrid = "grid";
        int iteration = 1;
        String className = "TestClass";

        outputStreamCaptor.reset();

        noLog.writeLog(formattedGrid, iteration, className);
        noLog.writeLog(formattedGrid, iteration, className);

        String expectedMessage = "No Log will be printed for TestClass. Please choose another Loggertype to see the result." + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    /**
     * Tests the {@link NoLog#writeLog(String, String)} method to ensure that no logs are printed.
     * This test verifies that the {@code writeLog} method does not print any logs to the console
     * when called with an end message and class name. The output is captured and compared to the
     * expected message to ensure that no logs are printed.
     */
    @Test
    public void writeLog_WithEndMessage_ShouldPrintMessageOnce() {
        NoLog noLog = new NoLog();
        String endMessage = "end";
        String className = "TestClass";

        outputStreamCaptor.reset();

        noLog.writeLog(endMessage, className);
        noLog.writeLog(endMessage, className);

        String expectedMessage = "No Log will be printed for TestClass. Please choose another Loggertype to see the result." + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }
}
