package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;

import de.nordakademie.zellulaere_automaten.model.Cell;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface that defines the logic for determining the next state of a cell.
 */

public interface ICellStateCalculation {
    /**
     * Determines if a cell should remain alive based on its neighbors' current states.
     *
     * @param cell the cell being evaluated
     * @param neighbors the list of neighboring cells
     * @return {@code true} if the cell should remain alive, otherwise {@code false}
     */
    boolean staysAlive(Cell cell, List<Cell> neighbors);
    /**
     * Determines if a cell should remain dead based on its neighbors' current states.
     *
     * @param cell the cell being evaluated
     * @param neighbors the list of neighboring cells
     * @return {@code true} if the cell should remain dead, otherwise {@code false}
     */
    boolean staysDead(Cell cell, List<Cell> neighbors);
}
