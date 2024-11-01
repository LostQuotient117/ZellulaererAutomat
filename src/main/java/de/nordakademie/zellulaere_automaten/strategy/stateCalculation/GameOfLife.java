package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;

import de.nordakademie.zellulaere_automaten.model.Cell;
import java.util.List;

/**
 * Implementation of {@link ICellStateCalculation} using the "Game of Life" ruleset.
 */

public class GameOfLife implements ICellStateCalculation {
    /**
     * Evaluates if an alive cell should continue to live based on the number of its alive neighbors.
     * In the "Game of Life", an alive cell survives if it has 2 or 3 alive neighbors.
     *
     * @param aliveCell the alive cell to be evaluated
     * @param neighbors the list of neighboring cells
     * @return {@code true} if the cell remains alive, otherwise {@code false}
     */
    @Override
    public boolean staysAlive(Cell aliveCell, List<Cell> neighbors) {
        long aliveNeighbors = neighbors.stream().filter(Cell::getIsAlive).count();
        return aliveNeighbors == 2 || aliveNeighbors == 3;
    }
    /**
     * Evaluates if a dead cell should remain dead based on the number of its alive neighbors.
     * In the "Game of Life", a dead cell remains dead unless it has exactly 3 alive neighbors,
     * in which case it becomes alive.
     *
     * @param deadCell the dead cell to be evaluated
     * @param neighbors the list of neighboring cells
     * @return {@code true} if the cell remains dead, otherwise {@code false}
     */
    @Override
    public boolean staysDead(Cell deadCell, List<Cell> neighbors) {
        long aliveNeighbors = neighbors.stream().filter(Cell::getIsAlive).count();
        return aliveNeighbors != 3;
    }
}
