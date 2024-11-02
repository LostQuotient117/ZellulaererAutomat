package de.nordakademie.zellulaere_automaten.strategy.neighbors;

import de.nordakademie.zellulaere_automaten.grid.GridType;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.Set;
/**
 * NeighborStrategyFactory is a factory class used to create instances of different neighbor strategies.
 * Based on the user's input, it returns the appropriate implementation of INeighborStrategy.
 */
public class NeighborStrategyFactory {
    /**
     * Creates a neighbor strategy based on user input.
     * Supports strategies like Neumann and Moore.
     *
     * @param userInputNeighborStrategy the user input specifying the desired neighbor strategy
     * @return an instance of INeighborStrategy based on the given input
     */
    public INeighborStrategy createNeighborStrategy(String userInputNeighborStrategy){
        int chosenNeighborStrategy = Integer.parseInt(userInputNeighborStrategy);
        NeighborType neighborType = NeighborType.getType(chosenNeighborStrategy);
        return switch (neighborType) {
            case Neumann -> new Neumann();
            case Moore -> new Moore();
        };
    }
}