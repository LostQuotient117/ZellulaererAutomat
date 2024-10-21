package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogConsoleTests {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }
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
}
