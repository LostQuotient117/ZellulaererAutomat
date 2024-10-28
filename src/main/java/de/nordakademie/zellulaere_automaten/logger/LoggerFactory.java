package de.nordakademie.zellulaere_automaten.logger;

import de.nordakademie.zellulaere_automaten.logger.loggerImpl.*;

/**
 * A factory class for creating instances of {@link ILogger}.
 * <p>
 * This class provides a method to retrieve the appropriate logger
 * implementation based on a specified parameter. It supports the
 * creation of either a {@code LogFile} or a {@code LogConsole}.
 * </p>
 * @author Jannick.Gottschalk
 */
public class LoggerFactory {
    /**
     * Returns an instance of {@link ILogger} based on the specified parameter.
     * @param userInput a string indicating whether to return a {@link LogFile}
     * instance (if {@code LogFile}) or a {@link LogConsole}
     * instance (if {@code LogConsole}).
     * @return an instance of {@link ILogger}, either a {@link LogFile} or
     * {@link LogConsole}, based on the value of {@code userInput}.
     */
    public ILogger createLogger(String userInput){
        int chosenLoggerMode = Integer.parseInt(userInput);
        LoggerTypes loggerTypes = LoggerTypes.getType(chosenLoggerMode);
        return switch (loggerTypes) {
            case LogFile -> new LogFile();
            case LogConsole -> new LogConsole();
        };
    }
}
