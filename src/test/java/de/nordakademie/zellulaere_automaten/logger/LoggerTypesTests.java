package de.nordakademie.zellulaere_automaten.logger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code LoggerTypesTests} class contains unit tests for the {@link LoggerTypes} enum.
 * These tests verify the functionality of the methods in the {@link LoggerTypes} enum.
 *
 * @author Jannick Gottschalk
 */
public class LoggerTypesTests {
    /**
     * Tests the {@code getValue} method of the {@link LoggerTypes} enum.
     * Verifies that the correct integer value is returned for each enum constant.
     */
    @Test
    public void testGetValue() {
        assertEquals(0, LoggerTypes.NoLog.getValue());
        assertEquals(1, LoggerTypes.LogFile.getValue());
        assertEquals(2, LoggerTypes.LogConsole.getValue());
        assertEquals(3, LoggerTypes.LogConsoleAndFile.getValue());
    }
    /**
     * Tests the {@code getType} method of the {@link LoggerTypes} enum.
     * Verifies that the correct enum constant is returned for each integer value.
     */
    @Test
    public void testGetType() {
        assertEquals(LoggerTypes.NoLog, LoggerTypes.getType(0));
        assertEquals(LoggerTypes.LogFile, LoggerTypes.getType(1));
        assertEquals(LoggerTypes.LogConsole, LoggerTypes.getType(2));
        assertEquals(LoggerTypes.LogConsoleAndFile, LoggerTypes.getType(3));
    }
    /**
     * Tests the {@code getType} method of the {@link LoggerTypes} enum with an invalid value.
     * Verifies that an {@link EnumConstantNotPresentException} is thrown for an invalid value.
     */
    @Test
    public void testGetType_InvalidValue() {
        Exception exception = assertThrows(EnumConstantNotPresentException.class, () -> LoggerTypes.getType(4));
        assertTrue(exception.getMessage().contains("This type of logger does not exist"));
    }
    /**
     * Tests the {@code getAvailableLoggerTypesForUserInput} method of the {@link LoggerTypes} enum.
     * Verifies that the correct string representation of available logger types is returned.
     */
    @Test
    public void testGetAvailableLoggerTypesForUserInput() {
        String expected = "0 für NoLog" + System.lineSeparator() +
                "1 für LogFile" + System.lineSeparator() +
                "2 für LogConsole" + System.lineSeparator() +
                "3 für LogConsoleAndFile";
        assertEquals(expected, LoggerTypes.getAvailableLoggerTypesForUserInput());
    }
}
