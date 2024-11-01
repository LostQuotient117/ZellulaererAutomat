package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Enum representing the different types of state calculations available.
 */

public enum CalculationType {
    GameOfLife(1),
    Parity(2);
    private final int value;

    /**
     * Constructor for {@link CalculationType}.
     *
     * @param value the integer value representing this calculation type.
     */
    CalculationType(int value) {this.value = value;}
    /**
     * Returns the integer value associated with this calculation type.
     *
     * @return the integer value of the calculation type.
     */
    public int getValue() {return value;}
    /**
     * Returns the {@code CalculationType} corresponding to the given value.
     *
     * @param value the integer value representing a calculation type.
     * @return the {@code CalculationType} matching the given value.
     * @throws EnumConstantNotPresentException if the value does not correspond to any known {@link CalculationType}.
     */
    public static CalculationType getType(int value) {
        return Arrays.stream(CalculationType.values())
                .filter(type -> type.getValue() == value)
                .findFirst()
                .orElseThrow(() ->
                        new EnumConstantNotPresentException(CalculationType.class, "This calculation-type is not existent."));
    }

    /**
     * Returns a string representation of all available calculation types for user input.
     * Each line contains the integer value and the name of a calculation type.
     *
     * @return a string listing all available calculation types.
     */
    public static String getAvailableCalculationTypesForUserInput() {
        return Arrays.stream(CalculationType.values())
                .map(calculationType -> calculationType.getValue() + " für " + calculationType.name())
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
