package de.nordakademie.zellulaere_automaten.strategy.simulationMode;
import de.nordakademie.zellulaere_automaten.model.Cell;
import java.util.ArrayList;
import java.util.List;

public class Neumann implements SimulationMode {
    /**
     * Returns a list of neighboring cells.
     *
     * This method creates a list of neighboring cells, based on the Neumann neighborhood.
     * The list includes the direct neighbors which are above, below, to the left and to the right to the specific cell.
     *
     * @param cell the cell for that
     * @return list of neighboring cells
     */
    @Override
    public List<Cell> getNeighbors(Cell cell){
        List<Cell> neighbors = new ArrayList<>();

        neighbors.add(new Cell(cell.getRow(), cell.getColumn() -1,false));
        neighbors.add(new Cell(cell.getRow(), cell.getColumn() +1,false));
        neighbors.add(new Cell(cell.getRow() -1, cell.getColumn(),false));
        neighbors.add(new Cell(cell.getRow() +1, cell.getColumn(),false));

        return neighbors;
    }
}

