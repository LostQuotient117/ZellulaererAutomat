package de.nordakademie.zellulaere_automaten.neighbors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.*;

class NeighborStrategyFactoryTest {

    /**
     * Test class for the {@link NeighborStrategyFactory}, which is responsible for creating
     * instances of different neighbor strategies for a cellular automaton. This test suite
     * verifies that the factory correctly instantiates the appropriate strategy based on
     * given input values and handles invalid input cases properly.
     *
     * The tests cover:
     * - Creating a {@link Neumann} instance when the input is "2".
     * - Creating a {@link Moore} instance when the input is "1".
     * - Throwing an {@link EnumConstantNotPresentException} for an invalid numeric input.
     * - Throwing a {@link NumberFormatException} for non-numeric input.
     * @author Daria Stolarczyk
     * @author Jannick Gottschalk
     */
    private NeighborStrategyFactory factory;

    @BeforeEach
    void setUp() {
        factory = new NeighborStrategyFactory();
    }

    @Test
    void createNeighborStrategy_InputOne_ReturnsNeumannInstance() {
        INeighborStrategy strategy = factory.createNeighborStrategy("2");
        assertInstanceOf(Neumann.class, strategy, "Expected Neumann instance");
    }

    @Test
    void createNeighborStrategy_InputTwo_ReturnsMooreInstance() {
        INeighborStrategy strategy = factory.createNeighborStrategy("1");
        assertInstanceOf(Moore.class, strategy, "Expected Moore instance");
    }

    @Test
    void createNeighborStrategy_InvalidInput_ThrowsException() {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            factory.createNeighborStrategy("3");
        });
    }

    @Test
    void createNeighborStrategy_NonNumericInput_ThrowsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> {
            factory.createNeighborStrategy("abc");
        });
    }
}
