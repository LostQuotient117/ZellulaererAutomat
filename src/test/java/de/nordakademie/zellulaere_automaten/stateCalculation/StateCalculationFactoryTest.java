package de.nordakademie.zellulaere_automaten.stateCalculation;

import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ICellStateCalculation;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.StateCalculationFactory;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.Parity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StateCalculationFactoryTest {

    private StateCalculationFactory factory;

    /**
     * Sets up the StateCalculationFactory instance before each test.
     */

    @BeforeEach
    void setUp() {
        factory = new StateCalculationFactory();
    }

    /**
     * Test for the createCalculationType method when input is "1".
     * The expected result is that an instance of GameOfLife is returned.
     */

    @Test
    void createCalculationType_InputOne_ReturnsGameOfLifeInstance() {
        ICellStateCalculation strategy = factory.createCalculationType("1");
        assertInstanceOf(GameOfLife.class, strategy, "Expected GameOfLife instance");
    }

    /**
     * Test for the createCalculationType method when input is "2".
     * The expected result is that an instance of Parity is returned.
     */
    @Test
    void createCalculationType_InputTwo_ReturnsParityInstance() {
        ICellStateCalculation strategy = factory.createCalculationType("2");
        assertInstanceOf(Parity.class, strategy, "Expected Parity instance");
    }

    /**
     * Test for the createCalculationType method when input is invalid (e.g., "3").
     * The expected result is that an EnumConstantNotPresentException is thrown.
     */
    @Test
    void createCalculationType_InvalidInput_ThrowsException() {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            factory.createCalculationType("3");
        });
    }

    /**
     * Test for the createCalculationType method when input is not a number (e.g., "abc").
     * The expected result is that a NumberFormatException is thrown.
     */
    @Test
    void createCalculationType_NonNumericInput_ThrowsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> {
            factory.createCalculationType("abc");
        });
    }
}
