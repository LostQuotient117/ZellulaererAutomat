package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.logger.LoggerFactory;
import de.nordakademie.zellulaere_automaten.model.Cell;

public abstract class Log implements ILogger {
    /**
     * log is the main call method for the logger.
     * <p> It calls the abstraction of {@link Log} previously defined by {@link LoggerFactory} and therefore executes either
     * {@link LogConsole#writeLog(String, int)} or {@link LogFile#writeLog(String, int)}</p>
     *
     * @param gridInput the already formatted grid as string
     * @param iteration the iteration for the to be printed grid
     */
    @Override
    public void log(String gridInput, int iteration) {
        writeLog(gridInput, iteration);
    }

    //region abstracts
    /**
     * Writes the formatted grid string to the console.
     * <p>
     * This method outputs the provided string to the standard output stream.
     * It is intended to log the formatted grid representation of a {@link Cell} array.
     * </p>
     * <p>
     * Subclasses must implement this method to define the specific logging behavior.
     * </p>
     *
     * @param formattedGrid the string representation of the grid to be logged
     * @param iteration the experiment-iteration of the grid to be printed
     */
    protected abstract void writeLog(String formattedGrid, int iteration);
    //endregion
}
