package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;

/**
 * The {@code IGrid} interface defines the structure for a grid in a cellular automaton.
 * Implementing classes, such as {@code ClassicGrid} and {@code HashMapGrid}, represent
 * different data structures that can store cell states and calculate new generations.
 * The {@code GridFactory} is used to decide which grid implementation to instantiate
 * based on the requirements.
 *
 * @param <T> the type of the data structure used to store the grid's cell states
 * @author Daria Stolarczyk
 */
public interface IGrid<T> {

    /**
     * Returns the number of rows in the grid.
     *
     * @return the number of rows in the grid
     */
    int getRows();

    /**
     * Returns the number of columns in the grid.
     *
     * @return the number of columns in the grid
     */
    int getColumns();

    /**
     * Returns the data structure that represents the active cells in the current grid state.
     *
     * @return the data structure containing the active cells
     */
    T getDataStructure();

    /**
     * Returns the data structure that represents the active cells from the previous generation.
     *
     * @return the data structure containing the active cells from the last generation
     */
    T getPreviousGrid();

    /**
     * Calculates the next generation of cell states in the grid based on defined rules and strategies.
     * Updates the data structure with the new generation's active cells.
     *
     * @return the data structure representing the new grid state
     */
    T calculateNextGeneration();

    /**
     * Returns the cell located at the specified coordinates in the current grid state.
     * If the cell does not exist, returns a dead cell at the specified location.
     *
     * @param x the row index of the cell
     * @param y the column index of the cell
     * @return the cell located at the specified (x, y) coordinates
     * @throws IndexOutOfBoundsException if the specified coordinates are out of bounds
     */
    Cell getCellByCoordinates(int x, int y);

    /**
     * Returns the cell located at the specified coordinates from the previous generation's grid state.
     * If the cell does not exist, returns a dead cell at the specified location.
     *
     * @param x the row index of the cell
     * @param y the column index of the cell
     * @return the cell located at the specified (x, y) coordinates in the previous generation
     * @throws IndexOutOfBoundsException if the specified coordinates are out of bounds
     */
    Cell getCellByCoordinatesFromPreviousGrid(int x, int y);

    /**
     * Checks if the grid has reached a stable state by comparing the current and previous generations.
     *
     * @return {@code true} if the grid state has not changed between generations, {@code false} otherwise
     */
    boolean isStable();

    /**
     * Returns a string representation of the current grid state.
     * Each cell's state is represented, typically with "1" for alive and "0" for dead.
     *
     * @return a string representation of the grid
     */
    @Override
    String toString();
}

