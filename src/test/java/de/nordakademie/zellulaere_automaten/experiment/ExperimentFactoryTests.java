package de.nordakademie.zellulaere_automaten.experiment;

import de.nordakademie.zellulaere_automaten.experiment.gameOfLife.GameOfLifeExperiment1Structure1;
import de.nordakademie.zellulaere_automaten.experiment.parity.ParityExperiment2Structure2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code ExperimentFactoryTests} class contains unit tests for the {@link ExperimentFactory} class.
 * It verifies the correct creation of experiments based on valid and invalid input.
 */
class ExperimentFactoryTests {

    private static ExperimentFactory experimentFactory;

    /**
     * Sets up the test environment before all tests.
     * Initializes the experiment factory.
     */
    @BeforeAll
    static void setUp() {
        experimentFactory = new ExperimentFactory();
    }

    /**
     * Tests the creation of experiments with valid input.
     * Verifies that the correct experiment instances are returned.
     */
    @Test
    void createExperiment_WithValidInput_ShouldReturnCorrectExperiment() {
        IExperiment experiment = experimentFactory.createExperiment("GameOfLifeExperiment1Structure1");
        assertInstanceOf(GameOfLifeExperiment1Structure1.class, experiment);

        experiment = experimentFactory.createExperiment("ParityExperiment2Structure2");
        assertInstanceOf(ParityExperiment2Structure2.class, experiment);
    }

    /**
     * Tests the creation of experiments with invalid input.
     * Verifies that an exception is thrown for invalid experiment names.
     */
    @Test
    void createExperiment_WithInvalidInput_ShouldThrowException() {
        int nonExistentValue = ExperimentTypes.values().length + 1;
        assertThrows(IllegalArgumentException.class, () -> experimentFactory.createExperiment(String.valueOf(nonExistentValue)));
    }
}