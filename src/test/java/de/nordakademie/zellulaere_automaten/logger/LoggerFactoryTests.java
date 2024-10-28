package de.nordakademie.zellulaere_automaten.logger;

import de.nordakademie.zellulaere_automaten.logger.loggerImpl.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LoggerFactoryTests {

    private static LoggerFactory loggerFactory;

    @BeforeAll
    public static void setUp() {
        loggerFactory = new LoggerFactory();
    }
    /**
     * Tests the {@link LoggerFactory#createLogger(String)}} method,
     * to ensure that passing {@code 1} returns an instance
     * of {@link LogFile}.
     */
    @Test
    public void testLoggerFactoryCreateLoggerType_1(){
        ILogger logger = loggerFactory.createLogger("1");
        assertInstanceOf(LogFile.class, logger, "Logger should be instance of LogFile");
    }
    /**
     * Tests the {@link LoggerFactory#createLogger(String)}} method,
     * to ensure that passing {@code 2} returns an instance
     * of {@link LogConsole}.
     */
    @Test
    public void testLoggerFactoryCreateLoggerType_2(){
        ILogger logger = loggerFactory.createLogger("2");
        assertInstanceOf(LogConsole.class, logger, "Logger should be instance of LogConsole");
    }

    /**
     * Tests the {@link LoggerFactory#createLogger(String)}} method,
     * to ensure that passing a not existent userInput returns an {@code EnumConstantNotPresentException}
     */
    @Test
    public void testLoggerFactoryCreateLoggerType_wrongInput(){
        LoggerFactory mockFactory = mock(LoggerFactory.class);
        int notExistentValue = LoggerTypes.values().length + 1;
        when(mockFactory.createLogger(String.valueOf(notExistentValue))).thenThrow(new EnumConstantNotPresentException(LoggerTypes.class, "INVALID"));
        assertThrows(EnumConstantNotPresentException.class, () ->
                mockFactory.createLogger(String.valueOf(notExistentValue)),
                "Expected an EnumConstantNotPresentException to be thrown for an invalid logger type");
    }
}