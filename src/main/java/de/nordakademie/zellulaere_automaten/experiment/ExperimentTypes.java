package de.nordakademie.zellulaere_automaten.experiment;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration representing different types of experiments.
 * Each enum constant corresponds to a specific experiment type.
 */
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
    ParityExperiment2Structure2,

    TestGameOfLifeExperiment1Structure1,
    TestGameOfLifeExperiment1Structure2,

    TestParityExperiment1Structure1,
    TestParityExperiment1Structure2;

    private static final Map<String, ExperimentTypes> NAME_MAP = new HashMap<>();

    /**
     * Static initializer block is used to populate the NAME_MAP with all the experiment types.
     * This block iterates over all the values of the ExperimentTypes enum and puts
     * each type's name and corresponding enum constant into the NAME_MAP.
     */
    static {
        for (ExperimentTypes type : values()) {
            NAME_MAP.put(type.name(), type);
        }
    }

    /**
     * Retrieves the experiment type corresponding to the specified name.
     *
     * @param name the name of the experiment type
     * @return the corresponding {@link ExperimentTypes} instance, or {@code null} if not found
     */
    public static ExperimentTypes getByName(String name) {
        return NAME_MAP.get(name);
    }
}