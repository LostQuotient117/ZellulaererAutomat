package de.nordakademie.zellulaere_automaten.stateCalculation;

import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.CalculationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculationTypeTest {

    /**
     * Test for the getType method when a valid integer (1) is provided.
     * The expected result is that the method returns CalculationType.GameOfLife.
     */
    @Test
    void getType_ValidValueOne_ReturnsGameOfLife() {
        CalculationType result = CalculationType.getType(1);
        assertEquals(CalculationType.GameOfLife, result);
    }

    /**
     * Test for the getType method when a valid integer (2) is provided.
     * The expected result is that the method returns CalculationType.Parity.
     */
    @Test
    void getType_ValidValueTwo_ReturnsParity() {
        CalculationType result = CalculationType.getType(2);
        assertEquals(CalculationType.Parity, result);
    }

    /**
     * Test for the getType method when an invalid integer is provided.
     * The expected result is that an EnumConstantNotPresentException is thrown.
     */
    @Test
    void getType_InvalidValue_ThrowsException() {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            CalculationType.getType(3); // Invalid value
        });
    }
}
