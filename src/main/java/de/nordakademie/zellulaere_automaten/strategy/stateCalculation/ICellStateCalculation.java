/**
 * This interface defines the StateCalculation
 * that is relevant for the calculation of next generation.
 */

package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.ArrayList;

public interface ICellStateCalculation {
    void calculateCellState(Cell cell, ArrayList<Cell> neighbours);
}
