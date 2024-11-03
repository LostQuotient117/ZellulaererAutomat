package de.nordakademie.zellulaere_automaten.strategy.neighbors;
import java.util.Arrays;
/**
 * Enum representing different types of neighborhood strategies.
 * Provides methods for selecting neighbor types such as Moore or Neumann.
 * @author Daria Stolarczyk
 */
public enum NeighborType {
    Moore(1),
    Neumann(2);
    private final int value;
    NeighborType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static NeighborType getType(int value) {
        return Arrays.stream(NeighborType.values())
                .filter(type -> type.getValue() == value)
                .findFirst()
                .orElseThrow(() ->
                        new EnumConstantNotPresentException(NeighborType.class, "This neighbor-type is not existent."));
    }
}
