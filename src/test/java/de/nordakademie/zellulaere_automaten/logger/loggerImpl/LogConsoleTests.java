package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogConsoleTests {
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    public static String wantedString100x100;

    //region BeforeEach
    /**
     * Sets up the test environment by redirecting the standard output stream.
     * <p>
     * This method is annotated with {@code @BeforeEach} to ensure that it runs before each test.
     * It redirects {@code System.out} to a {@link PrintStream} that captures the output, allowing
     * for verification of printed messages during tests.
     * </p>
     */
    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    //endregion
    //region BeforeAll
    /**
     * Initializes a string representing a 100x100 grid of zeros for testing purposes.
     * <p>
     * This method is annotated with {@code @BeforeAll} to ensure that the string is
     * set up before any tests are run. The string consists of one hundred lines, each containing
     * one hundred zeros, with each line separated by a newline character to imitate the grid structure.
     * </p>
     */
    @BeforeAll
    public static void wantedStringForTestFormatArraytoStringTests() {
        StringBuilder wantedStringBuilder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            wantedStringBuilder.append("0".repeat(100));
            wantedStringBuilder.append('\n');
        }
        wantedString100x100 = wantedStringBuilder.toString();
    }
    //endregion
    //region testStepWriter
    /**
     * Tests {@link LogConsole#stepWriterConsole(int)} and ensure
     * that the correct output is selected in the console
     * It inputs {@code step = 1} which specifies the experiment iteration that needs to be printed
     */
    @Test
    public void teststepWriter_Console_1(){
        int step = 1;
        String wantedOutput = "### (1)";
        LogConsole logConsole = new LogConsole();
        logConsole.stepWriterConsole(step);
        Assertions.assertEquals(wantedOutput, outputStreamCaptor.toString().trim());
    }

    /**
     * Tests {@link LogConsole#stepWriterConsole(int)} and ensure
     * that the correct output is selected in the console
     * It inputs {@code step = 100} which specifies the experiment iteration that needs to be printed
     */
    @Test
    public void teststepWriter_Console_100(){
        int step = 100;
        String wantedOutput = "### (100)";
        LogConsole logConsole = new LogConsole();
        logConsole.stepWriterConsole(step);
        Assertions.assertEquals(wantedOutput, outputStreamCaptor.toString().trim());
    }
    /**
     * Tests {@link LogConsole#stepWriterConsole(int)} and ensures
     * that an exception is thrown if {@code step} is negative
     */
    @Test
    public void teststepWriter_Console_negativeInput(){
        LogConsole logConsole = new LogConsole();
        assertThrows(IllegalArgumentException.class, () -> logConsole.stepWriterConsole(-5));
    }

    //endregion
    //region testConsoleWriter
    /**
     * Tests the {@link LogConsole#writeLog(String)} method to ensure it correctly writes the log to the console.
     * <p>
     * This test verifies that the {@code writeLog} method outputs the expected string to the console.
     * The output is captured and compared to the predefined {@code wantedString} to ensure accuracy.
     * </p>
     */
    @Test
    public void testConsoleWriter(){
        LogConsole logConsole = new LogConsole();
        logConsole.writeLog(wantedString100x100);
        assertEquals(wantedString100x100, outputStreamCaptor.toString());
    }
    //endregion
}
