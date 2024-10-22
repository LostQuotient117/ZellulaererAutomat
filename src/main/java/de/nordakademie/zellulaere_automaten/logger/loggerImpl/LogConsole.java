package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.model.*;

public class LogConsole extends Log {

    @Override
    protected void writeLog(String formattedGrid, int iteration) {
    }

    /**
     * Writes the formatted grid string to the console.
     * <p>
     * This method outputs the provided string to the standard output stream.
     * It is intended to log the formatted grid representation of a {@link Cell} array.
     * </p>
     *
     * @param formattedGrid the string representation of the grid to be logged
     */
    public void writeLogBody(String formattedGrid) {
        System.out.print(formattedGrid);
    }
    /**
     *This function is responsible for printing the first line of a grid,
     *which indicates the current iteration
     * @param step int
     */
    public void stepWriterConsole(int step) {
        if (step < 0){
            throw new IllegalArgumentException("Step number must be a positive integer");
        }
        System.out.print("### (" + step + ")");
    }
}
