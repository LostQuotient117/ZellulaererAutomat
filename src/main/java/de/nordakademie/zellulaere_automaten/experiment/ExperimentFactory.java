package de.nordakademie.zellulaere_automaten.experiment;

import de.nordakademie.zellulaere_automaten.experiment.gameOfLife.*;
import de.nordakademie.zellulaere_automaten.experiment.parity.*;

public class ExperimentFactory {
    public IExperiment createExperiment(String userInput) {
        int chosenExperimentMode = Integer.parseInt(userInput); // not needed if Jannick gives input as int
        ExperimentTypes experimentTypes = ExperimentTypes.getType(chosenExperimentMode);
        return switch (experimentTypes) {
            case GameOfLifeExperiment1Structure1 -> new GameOfLifeExperiment1Structure1();
            case GameOfLifeExperiment1Structure2 -> new GameOfLifeExperiment1Structure2();
            case GameOfLifeExperiment2Structure1 -> new GameOfLifeExperiment2Structure1();
            case GameOfLifeExperiment2Structure2 -> new GameOfLifeExperiment2Structure2();
            case GameOfLifeExperiment3Structure1 -> new GameOfLifeExperiment3Structure1();
            case GameOfLifeExperiment3Structure2 -> new GameOfLifeExperiment3Structure2();
            case ParityExperiment1Structure1 -> new ParityExperiment1Structure1();
            case ParityExperiment1Structure2 -> new ParityExperiment1Structure2();
            case ParityExperiment2Structure1 -> new ParityExperiment2Structure1();
            case ParityExperiment2Structure2 -> new ParityExperiment2Structure2();
        };
    }
}