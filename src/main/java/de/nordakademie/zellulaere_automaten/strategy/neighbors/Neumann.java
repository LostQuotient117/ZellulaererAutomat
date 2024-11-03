package de.nordakademie.zellulaere_automaten.strategy.neighbors;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import java.util.List;
/**
 * Neumann is an implementation of the AbstractNeighbors class, providing neighbors based on the Neumann neighborhood.
 * The Neumann neighborhood includes the four direct neighbors around a given cell: up, down, left, right.
 * @author Daria Stolarczyk
 */
public class Neumann extends AbstractNeighbors {
    @Override
    public List<Cell> getNeighbors(Cell cell, IGrid grid) {
        return super.getNeighbors(cell, grid);
    }
}

