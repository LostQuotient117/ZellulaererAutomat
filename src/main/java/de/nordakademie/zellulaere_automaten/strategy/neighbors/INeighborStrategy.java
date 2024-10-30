/**
 * This interface defines the SimulationMode that is relevant for the deviation between the neighborhood used.
 */

package de.nordakademie.zellulaere_automaten.strategy.neighbors;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.ArrayList;


public interface INeighborStrategy {

    public ArrayList<Cell> getNeighbors(Cell cell, IGrid grid);
}

