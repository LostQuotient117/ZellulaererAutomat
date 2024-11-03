package de.nordakademie.zellulaere_automaten.neighbors;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.NeighborType;

/**
 * Test class for the {@link NeighborType} enum, which defines different types of
 * neighborhood strategies (e.g., Neumann and Moore) for a cellular automaton.
 * This test suite verifies the functionality of the {@code getType} method, ensuring
 * that it returns the correct NeighborType based on the provided integer value
 * and handles invalid input correctly.
 *
 * The tests include:
 * - Retrieving the {@link NeighborType#Neumann} type when the input is 2.
 * - Retrieving the {@link NeighborType#Moore} type when the input is 1.
 * - Verifying that an {@link EnumConstantNotPresentException} is thrown for an invalid input.
 * @author Daria Stolarczyk
 * @author Jannick Gottschalk
 */
class NeighborTypeTest {

    @Test
    void getType_ValidValueOne_ReturnsNeumann() {
        NeighborType result = NeighborType.getType(2);
        assertEquals(NeighborType.Neumann, result);
    }

    @Test
    void getType_ValidValueTwo_ReturnsMoore() {
        NeighborType result = NeighborType.getType(1);
        assertEquals(NeighborType.Moore, result);
    }

    @Test
    void getType_InvalidValue_ThrowsException() {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            NeighborType.getType(3); // Invalid value
        });
    }
}
