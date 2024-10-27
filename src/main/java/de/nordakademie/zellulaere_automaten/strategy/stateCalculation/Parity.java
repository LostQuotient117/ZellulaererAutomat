package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;
import de.nordakademie.zellulaere_automaten.model.Cell;
import java.util.ArrayList;

public class ParityModell implements ICellStateCalculation {
    @Override
    public boolean staysAlive(Cell cell, ArrayList<Cell> neighbours) {
        return false;
    }

    @Override
    public boolean staysDead(Cell cell, ArrayList<Cell> neighbours) {
        return false;
    }
}