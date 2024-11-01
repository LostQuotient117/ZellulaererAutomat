package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.experiment.BaseExperiment;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code GameOfLifeExperiment3Structure2} class represents an experiment for the Game of Life with a specific structure.
 * It extends the {@link BaseExperiment} class and implements the {@link IExperiment} interface.
 *
 * @author Lars Nicht
 */
public class GameOfLifeExperiment3Structure2 extends BaseExperiment implements IExperiment {

    /**
     * Constructs a new {@code GameOfLifeExperiment3Structure2} experiment.
     * Initializes the grid with a specific size and configuration.
     */
    public GameOfLifeExperiment3Structure2() {
        super(new SetGrid(300, 300, new HashSet<>(), new GameOfLife(), new Moore()), new HashSet<>(), "GameOfLifeExperiment3Structure2");
    }

    /**
     * Initializes the grid with a specific starting pattern.
     * In this experiment, all cells are dead by default.
     */
    @Override
    public void initializeGrid() {
        ((SetGrid) this.grid).setActiveCells(startConfig);
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