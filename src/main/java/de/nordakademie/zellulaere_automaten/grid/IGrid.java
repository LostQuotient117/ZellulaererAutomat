package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;

/**
 * The {@code IGrid} is the interface for the {@code ClassicGrid} and {@code HashMapGrid} which
 * implement two different datastructures and the methods defined in this interface.
 * To decide which Object should be used, we use the {@code GridFactory}.
 */
public interface IGrid<T> {

    public int getRows();
    public int getColumns();
    public T getDataStructure();
    public T getPreviousGrid();

    /**
     * This method goes through the grid data-structure and recalculates the alive value of its cells.
     * @return new grid state with calculated cell states
     */
    public T calculateNextGeneration();

    /**
     * Returns the cell located at the specified coordinates in the grid.
     *
     * @param x the row index of the cell
     * @param y the column index of the cell
     * @return the cell located at the specified (x, y) coordinates
     * @throws IndexOutOfBoundsException if the specified coordinates are out of bounds
     */
    public Cell getCellByCoordinates(int x, int y);
    public Cell getCellByCoordinatesFromPreviousGrid(int x, int y);

    /**
     * Checks if grid is in a stable constellation
     * @return true: when the grid has not changed
     */
    public boolean isStable();

    @Override
    public String toString();
}
