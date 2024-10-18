package de.nordakademie.zellulaere_automaten.logger;

import de.nordakademie.zellulaere_automaten.logger.loggerImpl.ConsoleLogger;
import de.nordakademie.zellulaere_automaten.logger.loggerImpl.LogFile;

/**
 * A factory class for creating instances of {@link ILogger}.
 * <p>
 * This class provides a method to retrieve the appropriate logger
 * implementation based on a specified parameter. It supports the
 * creation of either a {@code LogFile} or a {@code ConsoleLogger}.
 * </p>
 */
public class LoggerFactory {
    /**
     * Returns an instance of {@link ILogger} based on the specified parameter.
     * @param userInput a string indicating whether to return a {@code LogFile}
     * instance (if {@code LogFile}) or a {@code ConsoleLogger}
     * instance (if {@code ConsoleLogger}).
     * @return an instance of {@link ILogger}, either a {@code LogFile} or
     * {@code ConsoleLogger}, based on the value of {@code logAsFile}.
     */
    public ILogger createLogger(String userInput){
        int chosenLoggerMode = Integer.parseInt(userInput);
        LoggerTypes loggerTypes = LoggerTypes.fromValue(chosenLoggerMode);
        return switch (loggerTypes) {
            case LogFile -> new LogFile();
            case ConsoleLogger -> new ConsoleLogger();
        };
    }
}
