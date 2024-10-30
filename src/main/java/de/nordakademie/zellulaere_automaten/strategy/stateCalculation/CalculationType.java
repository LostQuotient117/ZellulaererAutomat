package de.nordakademie.zellulaere_automaten.strategy.stateCalculation;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.CalculationType;

import java.util.Arrays;

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
}
