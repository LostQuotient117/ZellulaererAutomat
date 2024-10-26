package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;
import de.nordakademie.zellulaere_automaten.model.Cell;
import java.util.ArrayList;

public class ParityModell implements ICellStateCalculation {

    @Override
    public void calculateCellState(Cell cell, ArrayList<Cell> neighbors){

        int aliveNeighbors = 0;
        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i).getIsAlive()){
                aliveNeighbors++;
            }
        }

        if (aliveNeighbors % 2 == 1) {
                cell.setIsAlive(true);}
        else {
                cell.setIsAlive(false);
        }
    }
}