package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;

import de.nordakademie.zellulaere_automaten.model.Cell;
import java.util.List;

/**
 * Implementation of {@link ICellStateCalculation} using the "Game of Life" ruleset.
 * @author Daria Stolarczyk
 */
public class GameOfLife implements ICellStateCalculation {

    @Override
    public boolean staysAlive(Cell aliveCell, List<Cell> neighbors) {
        long aliveNeighbors = neighbors.stream().filter(Cell::getIsAlive).count();
        return aliveNeighbors == 2 || aliveNeighbors == 3;
    }

    @Override
    public boolean staysDead(Cell deadCell, List<Cell> neighbors) {
        long aliveNeighbors = neighbors.stream().filter(Cell::getIsAlive).count();
        return aliveNeighbors != 3;
    }
}
