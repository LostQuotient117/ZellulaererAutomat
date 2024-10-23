package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.List;

public abstract class Log implements ILogger {
    @Override
    public void log(Object gridInput) {

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
    //region formatToString
    /**
     * Converts a two-dimensional array of {@link Cell} objects into a string representation.
     * <p>
     * Each {@link Cell} in the array is represented by either '1' or '0' based on its {@code isAlive} state.
     * The resulting string consists of lines of '1's and '0's, with each line corresponding to a row in the array,
     * and each line separated by a newline character.
     * </p>
     *
     * @param inputArray the two-dimensional array of {@link Cell} objects to be formatted
     * @return a string representation of the array, with '1' for alive cells and '0' for dead cells
     */
    public String formatArrayToString(Cell[][] inputArray) {
        StringBuilder formattedString = new StringBuilder();
        for (Cell[] cells : inputArray) {
            for (Cell cell : cells) {
                formattedString.append(cell.getIsAlive() ? '1' : '0');
            }
            formattedString.append(System.lineSeparator());
        }
        return formattedString.toString();
    }

    /**
     * Converts a two-dimensional list of {@link Cell} objects into a string representation.
     * <p>
     * Each {@link Cell} in the list is represented by '1' if it is not null, and '0' if it is null.
     * The resulting string consists of lines of '1's and '0's, with each line corresponding to a row in the array,
     * and each line separated by a newline character.
     * </p>
     *
     * @param inputList the two-dimensional list of {@link Cell} objects to be formatted
     * @return a string representation of the list, with '1' for alive cells and '0' for dead cells
     */
    public String formatListToString(List<List<Cell>> inputList) {
        StringBuilder formattedString = new StringBuilder();
        for (List<Cell> cells : inputList) {
            for (Cell cell : cells) {
                if (cell != null) {
                    formattedString.append('1');
                }else{
                    formattedString.append('0');
                }
            }
            formattedString.append(System.lineSeparator());
        }
        return formattedString.toString();
    }

    //endregion /
    //region listCheck

    /**
     * Checks if the given input is a list of lists containing {@link Cell} objects or {@code null} values.
     * <p>
     * This method verifies that the input is a list of lists, where each inner list contains either
     * {@link Cell} objects or {@code null} values. It returns {@code true} if the structure is valid and contains
     * at least one non-{@code null} {@link Cell}. If any element is not a {@link Cell},
     *the method returns {@code false}.
     * </p>
     *
     * @param gridInput the input to be checked, expected to be a list of lists
     * @return {@code true} if the input is a valid list of lists containing {@link Cell} objects or {@code null} values,
     * and contains at least one non-null {@link Cell}; {@code false} otherwise
     */
    public boolean isListOfListsOfCells(Object gridInput) {
        if (gridInput instanceof List<?> outerList) {
            if (!outerList.isEmpty() && outerList.getFirst() instanceof List<?>) {
                for (Object innerListObj : outerList) {
                    if (innerListObj instanceof List<?> innerList) {
                        for (Object element : innerList) {
                            if (element != null && !(element instanceof Cell)) {
                                return false;
                            }
                        }
                    } else {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    //endregion
}
