package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;

import de.nordakademie.zellulaere_automaten.model.Cell;
import java.util.List;

/**
 * Implementation of {@link ICellStateCalculation} using a parity-based ruleset.
 * This ruleset uses the parity of alive neighbors to determine the next state.
 */

public class Parity implements ICellStateCalculation {
    /**
     * Determines if a cell should remain alive based on the parity of the number of alive neighbors.
     * A cell stays alive if it has an odd number of alive neighbors.
     *
     * @param cell the cell being evaluated
     * @param neighbors the list of neighboring cells
     * @return {@code true} if the cell remains alive, otherwise {@code false}
     */
    @Override
    public boolean staysAlive(Cell cell, List<Cell> neighbors) {
        long aliveNeighbors = neighbors.stream().filter(Cell::getIsAlive).count();
        return aliveNeighbors % 2 == 1;
    }
    /**
     * Determines if a cell should remain dead based on the parity of the number of alive neighbors.
     * A cell stays dead if it has an even number of alive neighbors.
     *
     * @param cell the cell being evaluated
     * @param neighbors the list of neighboring cells
     * @return {@code true} if the cell remains dead, otherwise {@code false}
     */
    @Override
    public boolean staysDead(Cell cell, List<Cell> neighbors) {
        long aliveNeighbors = neighbors.stream().filter(Cell::getIsAlive).count();
        return aliveNeighbors % 2 == 0;
    }
}