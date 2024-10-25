package de.nordakademie.zellulaere_automaten.strategy.simulationMode;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Moore implements SimulationMode{
    /**
     * Returns a list of neighboring Moore cells.
     *
     * This method creates a list of neighboring cells, based on the Moore neighborhood.
     * The list includes the direct neighbors which are above, below, to the left, to the right,
     * above right, above left, below right and below left to the specific cell.
     *
     * @param cell the cell for that
     * @return list of neighboring Moore cells
     */
    @Override
    public ArrayList<Cell> getNeighbors(Cell cell){
        ArrayList<Cell> mooreNeighbors = new ArrayList<>(getNeighbors(cell));

        mooreNeighbors.add(new Cell(cell.getRow() -1, cell.getColumn() -1,false));
        mooreNeighbors.add(new Cell(cell.getRow() -1, cell.getColumn() +1,false));
        mooreNeighbors.add(new Cell(cell.getRow() +1, cell.getColumn() -1,false));
        mooreNeighbors.add(new Cell(cell.getRow() +1, cell.getColumn() +1,false));

        return mooreNeighbors;
    }
}
