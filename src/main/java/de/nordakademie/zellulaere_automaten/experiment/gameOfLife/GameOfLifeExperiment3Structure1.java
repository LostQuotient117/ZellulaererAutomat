package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.experiment.BaseExperiment;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;

import java.util.HashSet;
import java.util.Set;

public class GameOfLifeExperiment3Structure1 extends BaseExperiment implements IExperiment {

    public GameOfLifeExperiment3Structure1() {
        super(new ClassicGrid(300, 300, new HashSet<>() , new GameOfLife(), new Moore()), new HashSet<>(), "GameOfLifeExperiment3Structure1");
    }

    @Override
    public void initializeGrid() {
        // All cells are dead by default
    }

    // for GameOfLifeExperiment3Structure1Tests
    public Set<Cell> getStartConfig() {
        return startConfig;
    }
}