package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;
import de.nordakademie.zellulaere_automaten.model.Cell;
import java.util.ArrayList;

public class GameOfLife implements ICellStateCalculation {

    @Override
    public boolean staysAlive(Cell aliveCell, ArrayList<Cell> neighbors) {
        long aliveNeighbors = neighbors.stream().filter(Cell::getIsAlive).count();
        return aliveNeighbors == 2 || aliveNeighbors == 3;
    }

    @Override
    public boolean staysDead(Cell deadCell, ArrayList<Cell> neighbors) {
        long aliveNeighbors = neighbors.stream().filter(Cell::getIsAlive).count();
        return aliveNeighbors != 3;
    }
}
