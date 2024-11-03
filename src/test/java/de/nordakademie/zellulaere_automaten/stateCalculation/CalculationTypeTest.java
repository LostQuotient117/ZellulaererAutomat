package de.nordakademie.zellulaere_automaten.stateCalculation;

import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.CalculationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for the {@link CalculationType} enum, which defines different types of
 * cell state calculation strategies (e.g., Game of Life and Parity) for a cellular automaton.
 * This test suite verifies the functionality of the {@code getType} method, ensuring
 * that it returns the correct CalculationType based on the provided integer value
 * and handles invalid input correctly.
 *
 * The tests include:
 * - Retrieving the {@link CalculationType#GameOfLife} type when the input is 1.
 * - Retrieving the {@link CalculationType#Parity} type when the input is 2.
 * - Verifying that an {@link EnumConstantNotPresentException} is thrown for an invalid input.
 * @author Viktoria Melnyk
 */
public class CalculationTypeTest {

    @Test
    void getType_ValidValueOne_ReturnsGameOfLife() {
        CalculationType result = CalculationType.getType(1);
        assertEquals(CalculationType.GameOfLife, result);
    }

    @Test
    void getType_ValidValueTwo_ReturnsParity() {
        CalculationType result = CalculationType.getType(2);
        assertEquals(CalculationType.Parity, result);
    }

    @Test
    void getType_InvalidValue_ThrowsException() {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            CalculationType.getType(3); // Invalid value
        });
    }
}
