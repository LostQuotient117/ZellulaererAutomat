package de.nordakademie.zellulaere_automaten.strategy.neighbors;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.ArrayList;
import java.util.List;
/**
 * Moore is an implementation of the AbstractNeighbors class, providing neighbors based on the Moore neighborhood.
 * The Moore neighborhood includes all eight neighbors around a given cell: direct neighbors (up, down, left, right)
 * as well as diagonal neighbors (top-left, top-right, bottom-left, bottom-right).
 */
public class Moore extends AbstractNeighbors{

    /**
     * Returns a list of neighboring Moore cells.
     * This method creates a list of neighboring cells, based on the Moore neighborhood.
     * The list includes the direct neighbors which are above, below, to the left, to the right,
     * above right, above left, below right and below left to the specific cell.
     *
     * @param cell the cell for that
     * @return list of neighboring Moore cells
     */
    @Override
    public List<Cell> getNeighbors(Cell cell, IGrid iGrid){
        List<Cell> mooreNeighbors = getDirectNeighbors(cell, iGrid);
        int priorRow = cell.getRow()-1;
        int priorColumn = cell.getColumn()-1;
        int nextRow = cell.getRow()+1;
        int nextColumn = cell.getColumn()+1;

        //diagonal top right
        if (priorRow >= 0 && priorColumn >= 0)
            mooreNeighbors.add(iGrid.getCellByCoordinatesFromPreviousGrid(priorRow, priorColumn));

        //diagonal top left
        if (priorRow >= 0 && nextColumn < iGrid.getColumns())
            mooreNeighbors.add(iGrid.getCellByCoordinatesFromPreviousGrid(priorRow, nextColumn));

        //diagonal bottom right
        if (nextRow < iGrid.getRows() && priorColumn >= 0)
            mooreNeighbors.add(iGrid.getCellByCoordinatesFromPreviousGrid(nextRow, priorColumn));

        //diagonal bottom left
        if (nextRow < iGrid.getRows() && nextColumn < iGrid.getColumns())
            mooreNeighbors.add(iGrid.getCellByCoordinatesFromPreviousGrid(nextRow, nextColumn));

        return mooreNeighbors;
    }
}
