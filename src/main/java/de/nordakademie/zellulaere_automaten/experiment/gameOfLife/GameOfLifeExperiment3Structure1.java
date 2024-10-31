package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.experiment.BaseExperiment;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code GameOfLifeExperiment3Structure1} class represents an experiment for the Game of Life with a specific structure.
 * It extends the {@link BaseExperiment} class and implements the {@link IExperiment} interface.
 */
public class GameOfLifeExperiment3Structure1 extends BaseExperiment implements IExperiment {

    /**
     * Constructs a new {@code GameOfLifeExperiment3Structure1} experiment.
     * Initializes the grid with a specific size and configuration.
     */
    public GameOfLifeExperiment3Structure1() {
        super(new ClassicGrid(300, 300, new HashSet<>() , new GameOfLife(), new Moore()), new HashSet<>(), "GameOfLifeExperiment3Structure1");
    }

    /**
     * Initializes the grid with a specific starting pattern.
     * In this experiment, all cells are dead by default.
     */
    @Override
    public void initializeGrid() {
    }

    /**
     * Returns the start configuration of cells for testing purposes.
     *
     * @return the start configuration of cells
     */
    public Set<Cell> getStartConfig() {
        return startConfig;
    }
}