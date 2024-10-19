package de.nordakademie.zellulaere_automaten.logger;

import de.nordakademie.zellulaere_automaten.logger.loggerImpl.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LoggerFactoryTests {

    /**
     * Tests the {@link LoggerFactory#createLogger(String)}} method,
     * to ensure that passing {@code true} returns an instance
     * of {@link LogFile}.
     */
    @Test
    public void testLoggerFactoryCreateLoggerType_1(){
        LoggerFactory loggerFactory = new LoggerFactory();
        ILogger logger = loggerFactory.createLogger("1");
        assertInstanceOf(LogFile.class, logger, "Logger should be instance of LogFile");
    }
    /**
     * Tests the {@link LoggerFactory#createLogger(String)}} method,
     * to ensure that passing {@code false} returns an instance
     * of {@link LogConsole}.
     */
    @Test
    public void testLoggerFactoryCreateLoggerType_2(){
        LoggerFactory loggerFactory = new LoggerFactory();
        ILogger logger = loggerFactory.createLogger("2");
        assertInstanceOf(LogConsole.class, logger, "Logger should be instance of LogConsole");
    }
}