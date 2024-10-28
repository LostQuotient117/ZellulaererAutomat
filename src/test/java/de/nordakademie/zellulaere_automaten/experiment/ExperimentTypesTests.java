package de.nordakademie.zellulaere_automaten.experiment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExperimentTypesTests {

    @Test
    void getType_WithValidValue_ShouldReturnCorrectExperimentType() {
        assertEquals(ExperimentTypes.GameOfLifeExperiment1Structure1, ExperimentTypes.getType(1));
        assertEquals(ExperimentTypes.ParityExperiment2Structure2, ExperimentTypes.getType(10));
    }

    @Test
    void getType_WithInvalidValue_ShouldThrowException() {
        int nonExistentValue = ExperimentTypes.values().length + 1;
        assertThrows(EnumConstantNotPresentException.class, () -> {
            ExperimentTypes.getType(nonExistentValue);
        });
    }

    @Test
    void getValue_WhenCalled_ShouldReturnCorrectValue() {
        assertEquals(1, ExperimentTypes.GameOfLifeExperiment1Structure1.getValue());
        assertEquals(10, ExperimentTypes.ParityExperiment2Structure2.getValue());
    }
}