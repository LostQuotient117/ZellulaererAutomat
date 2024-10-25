/**
 * This interface defines the SimulationMode that is relevant for the deviation between the neighborhood used.
 */

package de.nordakademie.zellulaere_automaten.strategy.simulationMode;
import de.nordakademie.zellulaere_automaten.model.Cell;
import java.util.ArrayList;
import java.util.List;


public interface SimulationMode {

    public List<Cell> getNeighbors(Cell cell);

}

