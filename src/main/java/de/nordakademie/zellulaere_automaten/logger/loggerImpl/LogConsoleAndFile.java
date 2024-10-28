package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.logger.LoggerFactory;

public class LogConsoleAndFile extends Log{
    @Override
    protected void writeLog(String formattedGrid, int iteration) {
        LoggerFactory loggerFactory = new LoggerFactory();
        ILogger logger1 = loggerFactory.createLogger("1");
        ILogger logger2 = loggerFactory.createLogger("2");
        logger1.log(formattedGrid, iteration);
        logger2.log(formattedGrid, iteration);
    }
}
