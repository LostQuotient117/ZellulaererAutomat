package de.nordakademie.zellulaere_automaten.grid;

/**
 * The {@code IGrid} is the interface for the {@code ClassicGrid} and {@code HashMapGrid} which
 * implement two different datastructures and the methods defined in this interface.
 * To decide which Object should be used, we use the {@code GridFactory}.
 */
public interface IGrid {

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

    @Override
    public String toString();
}
