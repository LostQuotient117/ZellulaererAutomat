package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;

import de.nordakademie.zellulaere_automaten.strategy.neighbors.NeighborType;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Enum representing the different types of state calculations available.
 * @author Jannick Gottschalk
 * @author Viktoria Melnyk
 */
public enum CalculationType {
    GameOfLife(1),
    Parity(2);
    private final int value;

    CalculationType(int value) {this.value = value;}

    public int getValue() {return value;}

    public static CalculationType getType(int value) {
        return Arrays.stream(CalculationType.values())
                .filter(type -> type.getValue() == value)
                .findFirst()
                .orElseThrow(() ->
                        new EnumConstantNotPresentException(CalculationType.class, "This calculation-type is not existent."));
    }

    public static String getAvailableCalculationTypesForUserInput() {
        return Arrays.stream(CalculationType.values())
                .map(calculationType -> calculationType.getValue() + " für " + calculationType.name() + " mit " + NeighborType.getType(calculationType.getValue()).name())
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
