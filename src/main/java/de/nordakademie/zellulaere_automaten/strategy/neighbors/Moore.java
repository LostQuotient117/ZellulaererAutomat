package de.nordakademie.zellulaere_automaten.strategy.neighbors;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.ArrayList;
import java.util.List;
/**
 * Moore is an implementation of the AbstractNeighbors class, providing neighbors based on the Moore neighborhood.
 * The Moore neighborhood includes all eight neighbors around a given cell: direct neighbors (up, down, left, right)
 * as well as diagonal neighbors (top-left, top-right, bottom-left, bottom-right).
 * @author Daria Stolarczyk
 */
public class Moore extends AbstractNeighbors{

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
