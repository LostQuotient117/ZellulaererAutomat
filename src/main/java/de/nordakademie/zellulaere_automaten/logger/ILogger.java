package de.nordakademie.zellulaere_automaten.logger;

/**
 * The {@code ILogger} interface represents the interface for the
 * logger class variants {@code LogFile} and {@code ConsoleLogger}
 * A call to {@code LoggerFactory.getLoggerType}  decides which one is executed
 * @author Jannick.Gottschalk
 */
public interface ILogger {
    /**
     * Specifies that every class ({@code LogFile}{@code ConsoleLogger}) that implements
     * this interface must include the log-method.
     * This method logs the cellular automata.
     */
    void log();
}
