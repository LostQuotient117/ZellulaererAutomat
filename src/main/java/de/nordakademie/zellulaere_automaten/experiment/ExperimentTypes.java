package de.nordakademie.zellulaere_automaten.experiment;

import java.util.HashMap;
import java.util.Map;

public enum ExperimentTypes {
    GameOfLifeExperiment1Structure1,
    GameOfLifeExperiment1Structure2,
    GameOfLifeExperiment2Structure1,
    GameOfLifeExperiment2Structure2,
    GameOfLifeExperiment3Structure1,
    GameOfLifeExperiment3Structure2,
    ParityExperiment1Structure1,
    ParityExperiment1Structure2,
    ParityExperiment2Structure1,
    ParityExperiment2Structure2;

    private static final Map<String, ExperimentTypes> NAME_MAP = new HashMap<>();

    static {
        for (ExperimentTypes type : values()) {
            NAME_MAP.put(type.name(), type);
        }
    }

    public static ExperimentTypes getByName(String name) {
        return NAME_MAP.get(name);
    }
}