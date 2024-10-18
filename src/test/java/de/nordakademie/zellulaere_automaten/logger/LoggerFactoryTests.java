package de.nordakademie.zellulaere_automaten.logger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LoggerFactoryTests {

    /**
     * Tests the {@code LoggerFactory.getLoggerType(boolean)} method,
     * to ensure that passing {@code true} returns an instance
     * of {@code LogFile}.
     */
    @Test
    public void testLoggerFactorygetLoggerType_True(){
        LoggerFactory loggerFactory = new LoggerFactory();
        ILogger logger = loggerFactory.getLoggerType(true);
        assertInstanceOf(LogFile.class, logger, "Logger should be instance of LogFile");
    }

    /**
     * Tests the {@code LoggerFactory.getLoggerType(boolean)} method,
     * to ensure that passing {@code false} returns an instance
     * of {@code ConsoleLogger}.
     */
    @Test
    public void testLoggerFactorygetLoggerType_False(){
        LoggerFactory loggerFactory = new LoggerFactory();
        ILogger logger = loggerFactory.getLoggerType(false);
        assertInstanceOf(ConsoleLogger.class, logger, "Logger should be instance of ConsoleLogger");
    }
}
