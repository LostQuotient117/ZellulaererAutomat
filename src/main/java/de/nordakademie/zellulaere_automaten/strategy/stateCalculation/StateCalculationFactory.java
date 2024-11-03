package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;

import de.nordakademie.zellulaere_automaten.grid.GridType;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.Set;

/**
 * Factory class responsible for creating instances of various state calculation types.
 * This class allows the creation of different implementations of {@link ICellStateCalculation}
 * based on the user's input.
 * @author Viktoria Melnyk
 */

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