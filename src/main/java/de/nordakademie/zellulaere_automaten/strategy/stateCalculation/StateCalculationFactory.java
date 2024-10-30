package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;

import de.nordakademie.zellulaere_automaten.grid.GridType;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.Set;

public class StateCalculationFactory {
    public ICellStateCalculation createCalculationType(String userInputCalculationType){
        int chosenCalculationType = Integer.parseInt(userInputCalculationType);
        CalculationType calculationType = CalculationType.getType(chosenCalculationType);
        return switch (calculationType) {
            case GameOfLife -> new GameOfLife();
            case Parity -> new Parity();
        };
    }
}