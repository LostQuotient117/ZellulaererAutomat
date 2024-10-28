package de.nordakademie.zellulaere_automaten.strategy.neighbors;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.ArrayList;

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
    public ArrayList<Cell> getNeighbors(Cell cell, IGrid grid){
        ArrayList<Cell> mooreNeighbors = getDirectNeighbors(cell, grid);
        int priorRow = cell.getRow()-1;
        int priorColumn = cell.getColumn()-1;
        int nextRow = cell.getColumn()+1;
        int nextColumn = cell.getColumn()+1;

        //diagonal top right
        if (priorRow >= 0 && priorColumn >= 0)
            mooreNeighbors.add(grid.getCellByCoordinates(priorRow, priorColumn));

        //diagonal top left
        if (priorRow >= 0 && nextColumn < grid.getColumns())
            mooreNeighbors.add(grid.getCellByCoordinates(priorRow, nextColumn));

        //diagonal bottom right
        if (nextRow < grid.getRows() && priorColumn >= 0)
            mooreNeighbors.add(grid.getCellByCoordinates(nextRow, priorColumn));

        //diagonal bottom left
        if (nextRow < grid.getRows() && nextColumn < grid.getColumns())
            mooreNeighbors.add(grid.getCellByCoordinates(nextRow, nextColumn));

        return mooreNeighbors;
    }
}
