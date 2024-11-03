package de.nordakademie.zellulaere_automaten.stateCalculation;

import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ICellStateCalculation;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.StateCalculationFactory;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.Parity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the {@link StateCalculationFactory} class, which is responsible for creating
 * instances of different cell state calculation strategies based on an input string.
 * This test suite verifies that the factory correctly returns instances of {@link GameOfLife}
 * and {@link Parity} based on valid input values, and throws appropriate exceptions for
 * invalid input.
 *
 * The tests cover:
 * - Verifying that an instance of {@link GameOfLife} is returned when the input is "1".
 * - Verifying that an instance of {@link Parity} is returned when the input is "2".
 * - Ensuring that an {@link EnumConstantNotPresentException} is thrown for an invalid numeric input.
 * - Ensuring that a {@link NumberFormatException} is thrown for a non-numeric input.
 * @author Viktoria Melnyk
 */
public class StateCalculationFactoryTest {

    private StateCalculationFactory factory;

    @BeforeEach
    void setUp() {
        factory = new StateCalculationFactory();
    }

    @Test
    void createCalculationType_InputOne_ReturnsGameOfLifeInstance() {
        ICellStateCalculation strategy = factory.createCalculationType("1");
        assertInstanceOf(GameOfLife.class, strategy, "Expected GameOfLife instance");
    }

    @Test
    void createCalculationType_InputTwo_ReturnsParityInstance() {
        ICellStateCalculation strategy = factory.createCalculationType("2");
        assertInstanceOf(Parity.class, strategy, "Expected Parity instance");
    }

    @Test
    void createCalculationType_InvalidInput_ThrowsException() {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            factory.createCalculationType("3");
        });
    }

    @Test
    void createCalculationType_NonNumericInput_ThrowsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> {
            factory.createCalculationType("abc");
        });
    }
}
