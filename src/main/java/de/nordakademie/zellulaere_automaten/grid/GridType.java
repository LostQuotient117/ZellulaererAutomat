package de.nordakademie.zellulaere_automaten.grid;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * The GridType enum represents the types of grid implementations available in the application.
 * It provides integer values for each grid type to support easy selection and configuration.
 *
 * Available grid types:
 * - ClassicGrid - Represented by the integer value 1.
 * - SetGrid - Represented by the integer value 2.
 *
 * The enum includes methods to:
 * - Retrieve a grid type based on its integer value (getType).
 * - Provide a formatted list of available grid types for user input (getAvailableGridTypesForUserInput).
 *
 * This enum is designed to ensure that only valid grid types are used, throwing an exception if an invalid value is provided.
 *
 * @author Daria Stolarczyk
 * @author Jannick Gottschalk
 */
public enum GridType {
    ClassicGrid(1),
    SetGrid(2);
    private final int value;

    GridType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static GridType getType(int value) {
        return Arrays.stream(GridType.values())
                .filter(type -> type.getValue() == value)
                .findFirst()
                .orElseThrow(() ->
                        new EnumConstantNotPresentException(GridType.class, "This grid-type is not existent."));
    }
    public static String getAvailableGridTypesForUserInput() {
        return Arrays.stream(GridType.values())
                .map(gridType -> gridType.getValue() + " für " + gridType.name())
                .collect(Collectors.joining(System.lineSeparator()));
    }
}