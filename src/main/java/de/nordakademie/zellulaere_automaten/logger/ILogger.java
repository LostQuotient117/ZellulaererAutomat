package de.nordakademie.zellulaere_automaten.logger;

/**
 * The {@code ILogger} interface represents the interface for the
 * logger class variants {@code LogFile} and {@code LogConsole}
 * A call to {@code LoggerFactory.getLoggerType}  decides which one is executed
 * @author Jannick.Gottschalk
 */
public interface ILogger {
    /**
     * Specifies that every class ({@code LogFile}{@code LogConsole}) that implements
     * this interface must include the log-method.
     * This method logs the cellular automata.
     */
    void log(String gridInput, int iteration);
    /**
     * Logs the end message to the log output.
     * This method is used to log a final message indicating the end of the logging process.
     * @param endMessage the end message to be logged
     */
    void logEndMessage(String endMessage);
}
