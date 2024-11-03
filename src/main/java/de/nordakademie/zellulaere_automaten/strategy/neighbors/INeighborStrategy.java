/**
 * This interface defines the SimulationMode that is relevant for the deviation between the neighborhood used.
 */

package de.nordakademie.zellulaere_automaten.strategy.neighbors;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.ArrayList;
import java.util.List;
/**
 * INeighborStrategy provides methods to find neighbors of a cell in a grid.
 * Different implementations can determine various types of neighbors, like direct or diagonal.
 * @author Daria Stolarczyk
 */
public interface INeighborStrategy {
    /**
     * Finds the neighbors of the given cell in the grid.
     *
     * @param cell the cell whose neighbors are to be found
     * @param grid the grid containing all cells
     * @return a list of neighboring cells
     */
    public List<Cell> getNeighbors(Cell cell, IGrid grid);
}

