package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.logger.LoggerFactory;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.io.IOException;

public abstract class Log implements ILogger {
    /**
     * log is the main call method for the logger.
     * It calls the abstraction of {@link Log} previously defined by {@link LoggerFactory} and therefore executes either
     * {@link LogConsole#writeLog(String, int)} or {@link LogFile#writeLog(String, int)}
     *
     * @param gridInput the already formatted grid as string
     * @param iteration the iteration for the to be printed grid
     */
    @Override
    public void log(String gridInput, int iteration) {
        writeLog(gridInput, iteration);
    }
    /**
 * Logs the end message to the console.
 * This method calls the abstract {@code writeLog} method to log the provided end message.
 * Subclasses must implement the {@code writeLog} method to define the specific logging behavior.
 * @param endMessage the end message to be logged
 */
@Override
public void logEndMessage(String endMessage){
    writeLog(endMessage);
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
    protected abstract void writeLog(String formattedGrid, int iteration);

    /**
     * Writes the end message to the log output.
     * This method is used to log a final message indicating the end of the logging process.
     * Subclasses must implement this method to define the specific logging behavior.
     * @param endMessage the end message to be logged
     */
    protected abstract void writeLog(String endMessage);
        //endregion
    }
