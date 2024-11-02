package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.InputHandler;
import de.nordakademie.zellulaere_automaten.model.*;

/**
 * The {@code LogConsole} class extends the {@link Log} class to provide
 * functionality for writing log data to the console.
 * This class implements methods to log the formatted grid and iteration number,
 * as well as the end message, to the console.
 *
 * @author Jannick Gottschalk
 */
public class LogConsole extends Log {

    /**
 * Writes the formatted grid and iteration number to the console.
 * This method formats the log data and writes it to the console.
 *
 * @param formattedGrid the formatted grid string to be logged
 * @param iteration the iteration number to be included in the log
 */
@Override
protected void writeLog(String formattedGrid, int iteration, String className) {
    try {
        stepWriterConsole(iteration, className);
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println("Throwback to main method");
        InputHandler inputHandler = new InputHandler();
        inputHandler.getUserInputs();
    }
    writeLogBody(formattedGrid);
}

    @Override
    protected void writeLog(String endMessage, String className) {
        writeLogBody(endMessage);
    }
    //region used Functions
    /**
     * Writes the formatted grid string to the console.
     * This method outputs the provided string to the standard output stream.
     * It is intended to log the formatted grid representation of a {@link Cell} array.
     * @param formattedGrid the string representation of the grid to be logged
     */
    private void writeLogBody(String formattedGrid) {
        System.out.print(formattedGrid);
        System.out.print(System.lineSeparator());
    }
    /**
     *This function is responsible for printing the first line of a grid,
     *which indicates the current iteration
     * @param step int
     */
    private void stepWriterConsole(int step, String className) {
        if (step < 0){
            throw new IllegalArgumentException("Step number must be a positive integer. This should not happen.");
        }
        if (step == 0){
            System.out.println(System.lineSeparator() + className + ":");
        }
        System.out.println("### (" + step + ")");
    }

    //endregion
}
