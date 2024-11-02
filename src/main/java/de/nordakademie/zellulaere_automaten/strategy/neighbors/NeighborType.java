package de.nordakademie.zellulaere_automaten.strategy.neighbors;
import java.util.Arrays;
/**
 * Enum representing different types of neighborhood strategies.
 * Provides methods for selecting neighbor types such as Moore or Neumann.
 */
public enum NeighborType {
    Moore(1),
    Neumann(2);
    private final int value;
    /**
     * Constructor to initialize the NeighborType with its corresponding value.
     *
     * @param value the integer value for user selection representing the neighbor type
     */
    NeighborType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static NeighborType getType(int value) {
        /**
         * Returns the NeighborType based on the provided integer value.
         *
         * @param value the integer value representing the neighbor type
         * @return the corresponding NeighborType
         * @throws EnumConstantNotPresentException if the provided value does not match any existing type
         */
        return Arrays.stream(NeighborType.values())
                .filter(type -> type.getValue() == value)
                .findFirst()
                .orElseThrow(() ->
                        new EnumConstantNotPresentException(NeighborType.class, "This neighbor-type is not existent."));
    }
}
