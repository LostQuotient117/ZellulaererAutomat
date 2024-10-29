package de.nordakademie.zellulaere_automaten.strategy.neighbors;

import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.ArrayList;

public class AbstractNeighbors implements INeighborStrategy {

    /**
     * Returns the list of neighbors for a given cell in the grid.
     * This implementation returns only the direct neighbors (up, down, left, and right).
     * This method can be overridden by subclasses to include additional neighbors,
     * such as diagonal neighbors in a Moore neighborhood.
     *
     * @param cell the cell for which neighbors are to be found
     * @param grid the grid containing all cells
     * @return a list of direct neighboring cells
     */
    @Override
    public ArrayList<Cell> getNeighbors(Cell cell, IGrid grid) {
        return getDirectNeighbors(cell, grid);
    }

    /**
     * Returns the list of direct neighbors (up, down, left, right) and diagonal neighbors for a given cell in the grid.
     * <p>
     * This method iterates through the possible directions to find the neighboring cells. It includes both direct
     * neighbors (N, S, W, E) and diagonal neighbors (NW, NE, SW, SE).
     * </p>
     * <p>
     * For each direction, it calculates the new row and column indices and checks if they are within the grid bounds.
     * If they are, the corresponding cell is added to the list of neighbors.
     * </p>
     *
     * @param cell the cell for which direct neighbors are to be found
     * @param grid the grid containing all cells
     * @return a list of direct and diagonal neighboring cells
     */
    @Override
    public ArrayList<Cell> getDirectNeighbors(Cell cell, IGrid grid) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}, // Direct neighbors (N, S, W, E)
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // Diagonal neighbors (NW, NE, SW, SE)
        };

        for (int[] direction : directions) {
            int newRow = cell.getRow() + direction[0];
            int newCol = cell.getColumn() + direction[1];

            if (newRow >= 0 && newRow < grid.getRows() && newCol >= 0 && newCol < grid.getColumns()) {
                neighbors.add(grid.getCellByCoordinates(newRow, newCol));
            }
        }

        return neighbors;
    }
}
