package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.logger.LoggerFactory;

/**
 * This class extends the Log class and provides functionality to log messages
 * to both the console and a file. It uses the LoggerFactory to create instances
 * of ILogger for logging.
 *
 * @author Jannick Gottschalk
 */
public class LogConsoleAndFile extends Log{
    /**
     * Logs the formatted grid and iteration information to both the console and a file.
     *
     * @param formattedGrid the formatted grid to be logged
     * @param iteration the current iteration number
     * @param className the name of the class from which the log is being made
     */
    @Override
    protected void writeLog(String formattedGrid, int iteration, String className) {
        LoggerFactory loggerFactory = new LoggerFactory();
        ILogger logger1 = loggerFactory.createLogger("1");
        ILogger logger2 = loggerFactory.createLogger("2");
        logger1.log(formattedGrid, iteration, className);
        logger2.log(formattedGrid, iteration, className);
    }

    /**
     * Logs the end message to both the console and a file.
     *
     * @param endMessage the end message to be logged
     * @param className the name of the class from which the log is being made
     */
    @Override
    protected void writeLog(String endMessage, String className) {
        LoggerFactory loggerFactory = new LoggerFactory();
        ILogger logger1 = loggerFactory.createLogger("1");
        ILogger logger2 = loggerFactory.createLogger("2");
        logger1.logEndMessage(endMessage, className);
        logger2.logEndMessage(endMessage, className);
    }
}
