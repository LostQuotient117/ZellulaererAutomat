package de.nordakademie.zellulaere_automaten.strategy.neighbors;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import java.util.List;
/**
 * Neumann is an implementation of the AbstractNeighbors class, providing neighbors based on the Neumann neighborhood.
 * The Neumann neighborhood includes the four direct neighbors around a given cell: up, down, left, right.
 */
public class Neumann extends AbstractNeighbors {
    /**
     * Returns a list of neighboring cells in the Neumann neighborhood.
     * The Neumann neighborhood includes only the direct neighbors: up, down, left, and right.
     *
     * @param cell the cell for which neighbors are to be found
     * @param grid the grid containing all cells
     * @return a list of direct neighboring cells (Neumann neighborhood)
     */
    @Override
    public List<Cell> getNeighbors(Cell cell, IGrid grid) {
        return super.getNeighbors(cell, grid);
    }
}

