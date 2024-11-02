package de.nordakademie.zellulaere_automaten.grid;

import java.util.Arrays;
import java.util.stream.Collectors;

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