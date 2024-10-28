package de.nordakademie.zellulaere_automaten.experiment;

import java.util.Arrays;

public enum ExperimentTypes {
    GameOfLifeExperiment1Structure1(1),
    GameOfLifeExperiment1Structure2(2),
    GameOfLifeExperiment2Structure1(3),
    GameOfLifeExperiment2Structure2(4),
    GameOfLifeExperiment3Structure1(5),
    GameOfLifeExperiment3Structure2(6),
    ParityExperiment1Structure1(7),
    ParityExperiment1Structure2(8),
    ParityExperiment2Structure1(9),
    ParityExperiment2Structure2(10);

    private final int value;

    ExperimentTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ExperimentTypes getType(int value) {
        return Arrays.stream(ExperimentTypes.values())
                .filter(type -> type.getValue() == value)
                .findFirst()
                .orElseThrow(() ->
                        new EnumConstantNotPresentException(ExperimentTypes.class, "This experiment does not exist."));
    }
}