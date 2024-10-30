package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.experiment.BaseExperiment;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;

import java.util.HashSet;
import java.util.Set;

public class GameOfLifeExperiment3Structure2 extends BaseExperiment implements IExperiment {

    public GameOfLifeExperiment3Structure2() {
        super(new SetGrid(300, 300, new HashSet<>(), new GameOfLife(), new Moore()), new HashSet<>(), "GameOfLifeExperiment3Structure2");
    }

    @Override
    public void initializeGrid() {
        // All cells are dead by default
        // Update the grid with the new startConfig
        ((SetGrid) this.grid).setActiveCells(startConfig);
    }

    // for GameOfLifeExperiment3Structure2Tests
    public Set<Cell> getStartConfig() {
        return startConfig;
    }
}