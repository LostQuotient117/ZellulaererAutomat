package de.nordakademie.zellulaere_automaten.strategy.neighbors;

import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.ArrayList;
import java.util.List;

public class AbstractNeighbors implements INeighborStrategy{

    /**
     * Returns the list of neighbors for a given cell in the grid.
     * This implementation returns only the direct neighbors (up, down, left, and right).
     * This method can be overridden by subclasses to include additional neighbors,
     * such as diagonal neighbors in a Moore neighborhood.
     * @param cell the cell for which neighbors are to be found
     * @param grid the grid containing all cells
     * @return a list of direct neighboring cells
     */
    @Override
    public List<Cell> getNeighbors(Cell cell, IGrid grid) {
        return getDirectNeighbors(cell, grid);
    }

    /**
     * Returns the list of direct neighbors (up, down, left, right) for a given cell in the grid.
     * This method is intended to be reused by subclasses that need to include direct neighbors
     * in their neighbor calculations.
     *
     * @param cell the cell for which direct neighbors are to be found
     * @param iGrid the grid containing all cells
     * @return a list of direct neighboring cells
     */
    public List<Cell> getDirectNeighbors(Cell cell, IGrid iGrid) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        int priorRow = cell.getRow()-1;
        int priorColumn = cell.getColumn()-1;
        int nextRow = cell.getRow()+1;
        int nextColumn = cell.getColumn()+1;
        int currentRow = cell.getRow();
        int currentColumn = cell.getColumn();
        int gridMaxRow = iGrid.getRows();
        int gridMaxCol = iGrid.getColumns();

        if (priorRow >= 0)
            neighbors.add(iGrid.getCellByCoordinatesFromPreviousGrid(priorRow,currentColumn));

        //below
        if (nextRow < gridMaxRow)
            neighbors.add(iGrid.getCellByCoordinatesFromPreviousGrid(nextRow, currentColumn));

        //left
        if (priorColumn >= 0)
            neighbors.add(iGrid.getCellByCoordinatesFromPreviousGrid(currentRow, priorColumn));

        //right
        if (nextColumn < gridMaxCol)
            neighbors.add(iGrid.getCellByCoordinatesFromPreviousGrid(currentRow, nextColumn));

        return neighbors;
    }
}
