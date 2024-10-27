package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.ArrayList;

/**
 * This interface defines the StateCalculation
 * that is relevant for the calculation of next generation.
 */
public interface ICellStateCalculation {
    boolean staysAlive(Cell cell, ArrayList<Cell> neighbors);
    boolean staysDead(Cell cell, ArrayList<Cell> neighbors);
}
