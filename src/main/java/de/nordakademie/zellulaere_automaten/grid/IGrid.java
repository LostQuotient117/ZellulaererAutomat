package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.experiment.Tuple;

import java.util.ArrayList;

/**
 * The {@code IGrid} is the interface for the {@code ClassicGrid} and {@code HashMapGrid} which
 * implement two different datastructures and the methods defined in this interface.
 * To decide which Object should be used, we use the {@code GridFactory}.
 */
public interface IGrid {

    /**
     *This method initializes the grid with all cells being off.
     * @param rows: defines number of rows for grid
     * @param columns: defines number of columns for grid
     */
    public void initializeEmptyGrid(int rows, int columns);

    /**
     *This method initializes the grid with given configuration.
     * @param rows: defines number of rows for grid
     * @param columns: defines number of columns for grid
     * @param configuration defines a list of int tuples which show active cells and their coordinates
     */
    public void initializeStartConfig(int rows, int columns, ArrayList<Tuple<Integer, Integer>> configuration);

    /**
     * This method goes through the grid data-structure and recalculates the alive value of its cells.
     * @return new grid state with calculated cell states
     */
    public IGrid calculateNextGeneration();

    /**
     * Checks if grid is in a stable constellation
     * @return true: when the grid has not changed
     */
    public boolean isStable();
}
