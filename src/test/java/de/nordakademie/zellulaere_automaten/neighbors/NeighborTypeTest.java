package de.nordakademie.zellulaere_automaten.neighbors;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.NeighborType;

class NeighborTypeTest {

    /**
     * Test for the getType method when a valid integer (1) is provided.
     * The expected result is that the method returns NeighborType.Neumann.
     */
    @Test
    void getType_ValidValueOne_ReturnsNeumann() {
        NeighborType result = NeighborType.getType(1);
        assertEquals(NeighborType.Neumann, result);
    }

    /**
     * Test for the getType method when a valid integer (2) is provided.
     * The expected result is that the method returns NeighborType.Moore.
     */
    @Test
    void getType_ValidValueTwo_ReturnsMoore() {
        NeighborType result = NeighborType.getType(2);
        assertEquals(NeighborType.Moore, result);
    }

    /**
     * Test for the getType method when an invalid integer is provided.
     * The expected result is that an EnumConstantNotPresentException is thrown.
     */
    @Test
    void getType_InvalidValue_ThrowsException() {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            NeighborType.getType(3); // Invalid value
        });
    }
}
