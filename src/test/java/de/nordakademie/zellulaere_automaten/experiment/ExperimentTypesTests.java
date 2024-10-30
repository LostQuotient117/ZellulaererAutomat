package de.nordakademie.zellulaere_automaten.experiment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code ExperimentTypesTests} class contains unit tests for the {@link ExperimentTypes} enum.
 * It verifies the correct retrieval of experiment types based on their names.
 */
class ExperimentTypesTests {

    /**
     * Tests the retrieval of experiment types by name.
     * Verifies that the correct enum constants are returned for valid names.
     */
    @Test
    void getByName_WithValidName_ShouldReturnCorrectExperimentType() {
        assertEquals(ExperimentTypes.GameOfLifeExperiment1Structure1, ExperimentTypes.getByName("GameOfLifeExperiment1Structure1"));
        assertEquals(ExperimentTypes.ParityExperiment2Structure2, ExperimentTypes.getByName("ParityExperiment2Structure2"));
    }

    /**
     * Tests the retrieval of experiment types by name with an invalid name.
     * Verifies that null is returned for invalid names.
     */
    @Test
    void getByName_WithInvalidName_ShouldReturnNull() {
        assertNull(ExperimentTypes.getByName("NonExistentExperiment"));
    }
}