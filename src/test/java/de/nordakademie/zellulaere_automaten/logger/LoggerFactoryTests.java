package de.nordakademie.zellulaere_automaten.logger;

import de.nordakademie.zellulaere_automaten.logger.loggerImpl.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LoggerFactoryTests {
    /**
     * Tests the {@link LoggerFactory#createLogger(String)}} method,
     * to ensure that passing {@code 1} returns an instance
     * of {@link LogFile}.
     */
    @Test
    public void createLoggerType_1_LogFileClass(){
        LoggerFactory mockFactory = mock(LoggerFactory.class);
        ILogger mockLogger = mock(LogFile.class);
        when(mockFactory.createLogger("1")).thenReturn(mockLogger);

        ILogger logger = mockFactory.createLogger("1");
        assertInstanceOf(LogFile.class, logger, "Logger should be instance of LogFile");
    }
    /**
     * Tests the {@link LoggerFactory#createLogger(String)}} method,
     * to ensure that passing {@code 2} returns an instance
     * of {@link LogConsole}.
     */
    @Test
    public void createLoggerType_2_LogConsoleClass(){
        LoggerFactory mockFactory = mock(LoggerFactory.class);
        ILogger mockLogger = mock(LogConsole.class);
        when(mockFactory.createLogger("2")).thenReturn(mockLogger);

        ILogger logger = mockFactory.createLogger("2");
        assertInstanceOf(LogConsole.class, logger, "Logger should be instance of LogConsole");
    }
    /**
     * Tests the {@link LoggerFactory#createLogger(String)}} method,
     * to ensure that passing a not existent userInput returns an {@code EnumConstantNotPresentException}
     */
    @Test
    public void createLoggerType_NotExistentValue_EnumConstantNotPresentException(){
        LoggerFactory mockFactory = mock(LoggerFactory.class);
        int notExistentValue = LoggerTypes.values().length + 100;
        when(mockFactory.createLogger(String.valueOf(notExistentValue))).thenThrow(new EnumConstantNotPresentException(LoggerTypes.class, "INVALID"));
        assertThrows(EnumConstantNotPresentException.class, () ->
                mockFactory.createLogger(String.valueOf(notExistentValue)),
                "Expected an EnumConstantNotPresentException to be thrown for an invalid logger type");
    }
}