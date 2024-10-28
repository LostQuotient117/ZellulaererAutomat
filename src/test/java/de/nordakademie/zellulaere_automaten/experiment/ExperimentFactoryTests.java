package de.nordakademie.zellulaere_automaten.experiment;

import de.nordakademie.zellulaere_automaten.experiment.gameOfLife.GameOfLifeExperiment1Structure1;
import de.nordakademie.zellulaere_automaten.experiment.parity.ParityExperiment2Structure2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExperimentFactoryTests {

    private static ExperimentFactory experimentFactory;

    @BeforeAll
    static void setUp() {
        experimentFactory = new ExperimentFactory();
    }

    @Test
    void createExperiment_WithValidInput_ShouldReturnCorrectExperiment() {
        IExperiment experiment = experimentFactory.createExperiment("1");
        assertInstanceOf(GameOfLifeExperiment1Structure1.class, experiment);

        experiment = experimentFactory.createExperiment("10");
        assertInstanceOf(ParityExperiment2Structure2.class, experiment);
    }

    @Test
    void createExperiment_WithInvalidInput_ShouldThrowException() {
        int nonExistentValue = ExperimentTypes.values().length + 1;
        assertThrows(EnumConstantNotPresentException.class, () -> {
            experimentFactory.createExperiment(String.valueOf(nonExistentValue));
        });
    }
}