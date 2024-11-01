package de.nordakademie.zellulaere_automaten.neighbors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.*;

class NeighborStrategyFactoryTest {

    private NeighborStrategyFactory factory;

    /**
     * Sets up the NeighborStrategyFactory instance before each test.
     */
    @BeforeEach
    void setUp() {
        factory = new NeighborStrategyFactory();
    }

    /**
     * Test for the createNeighborStrategy method when input is "1".
     * The expected result is that an instance of Neumann is returned.
     */
    @Test
    void createNeighborStrategy_InputOne_ReturnsNeumannInstance() {
        INeighborStrategy strategy = factory.createNeighborStrategy("2");
        assertInstanceOf(Neumann.class, strategy, "Expected Neumann instance");
    }

    /**
     * Test for the createNeighborStrategy method when input is "2".
     * The expected result is that an instance of Moore is returned.
     */
    @Test
    void createNeighborStrategy_InputTwo_ReturnsMooreInstance() {
        INeighborStrategy strategy = factory.createNeighborStrategy("1");
        assertInstanceOf(Moore.class, strategy, "Expected Moore instance");
    }

    /**
     * Test for the createNeighborStrategy method when input is invalid (e.g., "3").
     * The expected result is that an EnumConstantNotPresentException is thrown.
     */
    @Test
    void createNeighborStrategy_InvalidInput_ThrowsException() {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            factory.createNeighborStrategy("3");
        });
    }

    /**
     * Test for the createNeighborStrategy method when input is not a number (e.g., "abc").
     * The expected result is that a NumberFormatException is thrown.
     */
    @Test
    void createNeighborStrategy_NonNumericInput_ThrowsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> {
            factory.createNeighborStrategy("abc");
        });
    }
}
