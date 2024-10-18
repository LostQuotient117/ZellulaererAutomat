package de.nordakademie.zellulaere_automaten.logger;

/**
 * A factory class for creating instances of {@link ILogger}.
 * <p>
 * This class provides a method to retrieve the appropriate logger
 * implementation based on a specified parameter. It supports the
 * creation of either a {@link LogFile} or a {@link ConsoleLogger}.
 * </p>
 */
public class LoggerFactory {
    /**
     * Returns an instance of {@link ILogger} based on the specified parameter.
     * @param logType a string indicating whether to return a {@link LogFile}
     * instance (if {@code LogFile}) or a {@link ConsoleLogger}
     * instance (if {@code ConsoleLogger}).
     * @return an instance of {@link ILogger}, either a {@link LogFile} or
     * {@link ConsoleLogger}, based on the value of {@code logAsFile}.
     */
    public ILogger getLoggerType(int logType){
        LoggerTypes loggerTypes = LoggerTypes.fromValue(logType);
        return switch (loggerTypes) {
            case LogFile -> LogFile::new;
            case ConsoleLogger -> ConsoleLogger::new;
        };
    }
}
