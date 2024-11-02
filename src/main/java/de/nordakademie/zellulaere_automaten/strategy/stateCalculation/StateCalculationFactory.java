package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;

import de.nordakademie.zellulaere_automaten.grid.GridType;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.Set;

/**
 * Factory class responsible for creating instances of various state calculation types.
 * This class allows the creation of different implementations of {@link ICellStateCalculation}
 * based on the user's input.
 */

public class StateCalculationFactory {
    /**
     * Creates an instance of {@link ICellStateCalculation} based on the user-provided input.
     *
     * @param userInputCalculationType a String representing the calculation type selected by the user.
     *  This should be a valid integer value.
     * @return an instance of {@link ICellStateCalculation} that matches the user's chosen calculation type.
     * @throws NumberFormatException if the {@code userInputCalculationType} cannot be parsed as a valid integer.
     * @throws IllegalArgumentException if the parsed integer does not correspond to any valid {@link CalculationType}.
     */
    public ICellStateCalculation createCalculationType(String userInputCalculationType){
        int chosenCalculationType = Integer.parseInt(userInputCalculationType);
        CalculationType calculationType = CalculationType.getType(chosenCalculationType);
        return switch (calculationType) {
            case GameOfLife -> new GameOfLife();
            case Parity -> new Parity();
        };
    }
}