package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;
import de.nordakademie.zellulaere_automaten.model.Cell;
import java.util.ArrayList;

public class Parity implements ICellStateCalculation {
    @Override
    public boolean staysAlive(Cell cell, ArrayList<Cell> neighbors) {
        long aliveNeighbors = neighbors.stream().filter(Cell::getIsAlive).count();
        return aliveNeighbors % 2 == 1;
    }

    @Override
    public boolean staysDead(Cell cell, ArrayList<Cell> neighbors) {
        long aliveNeighbors = neighbors.stream().filter(Cell::getIsAlive).count();
        return aliveNeighbors % 2 == 0;
    }
}