package de.nordakademie.zellulaere_automaten.strategy.neighbors;

import de.nordakademie.zellulaere_automaten.grid.GridType;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.Set;

public class NeighborStrategyFactory {
    public INeighborStrategy createNeighborStrategy(String userInputNeighborStrategy){
        int chosenNeighborStrategy = Integer.parseInt(userInputNeighborStrategy);
        NeighborType neighborType = NeighborType.getType(chosenNeighborStrategy);
        return switch (neighborType) {
            case Neumann -> new Neumann();
            case Moore -> new Moore();
        };
    }
}