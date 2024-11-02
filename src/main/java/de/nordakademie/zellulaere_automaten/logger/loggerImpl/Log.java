package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.model.Cell;

/**
 * Abstract base class for logging grid data and end messages.
 * This class provides a template for logging functionality, requiring subclasses
 * to implement the specific logging behavior.
 *
 * @author Jannick Gottschalk
 */
public abstract class Log implements ILogger {
    /**
     * Logs the provided grid input with the specified iteration and class name.
     * This method delegates the actual logging to the abstract {@code writeLog} method,
     * which must be implemented by subclasses to define the specific logging behavior.
     *
     * @param gridInput the string representation of the grid to be logged
     * @param iteration the experiment iteration of the grid to be logged
     * @param className the name of the class calling the log method
     */
    @Override
    public void log(String gridInput, int iteration, String className) {
        writeLog(gridInput, iteration, className);
    }
    /**
 * Logs the end message to the console.
 * This method calls the abstract {@code writeLog} method to log the provided end message.
 * Subclasses must implement the {@code writeLog} method to define the specific logging behavior.
 * @param endMessage the end message to be logged
 */
@Override
public void logEndMessage(String endMessage, String className) {
    writeLog(endMessage, className);
}

    //region abstracts
    /**
     * Writes the formatted grid string to the console.
     * This method outputs the provided string to the standard output stream.
     * It is intended to log the formatted grid representation of a {@link Cell} array.
     * Subclasses must implement this method to define the specific logging behavior.
     * @param formattedGrid the string representation of the grid to be logged
     * @param iteration the experiment-iteration of the grid to be printed
     */
    protected abstract void writeLog(String formattedGrid, int iteration, String className);
    /**
     * Writes the end message to the log.
     * This method outputs the provided end message to the log.
     * Subclasses must implement this method to define the specific logging behavior.
     *
     * @param endMessage the end message to be logged
     * @param className the name of the class calling the log method
     */
    protected abstract void writeLog(String endMessage, String className);
        //endregion
    }
