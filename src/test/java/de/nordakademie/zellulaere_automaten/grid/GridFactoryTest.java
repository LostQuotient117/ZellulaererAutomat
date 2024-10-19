package de.nordakademie.zellulaere_automaten.grid;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridFactoryTest
{
    private static GridFactory gridFactory;

    @BeforeAll
    public static void setUp(){
        gridFactory = new GridFactory();
    }

    @Test
    public void testGridFactory_CreateClassicGird_True(){
        IGrid grid = gridFactory.createGrid("1");
        assertInstanceOf(ClassicGrid.class, grid, String.format("Grid should be instance of ClassicGrid, but its %s", grid.getClass()));
    }

    @Test
    public void testGridFactory_CreateHashMapGird_True(){
        IGrid grid = gridFactory.createGrid("2");
        assertInstanceOf(HashMapGrid.class, grid, String.format("Grid should be instance of ClassicGrid, but its %s", grid.getClass()));
    }

    @Test
    public void testGridFactory_CreateFalseGirdType_False(){
        IGrid grid = gridFactory.createGrid("2");
        assertFalse(grid instanceof ClassicGrid,
                "Expected grid to not be an instance of ClassicGrid");
    }

    @Test
    public void testGridFactory_CreateFalseGridType_ThrowsException() {
        int notExistentValue = GridType.values().length + 1;
        assertThrows(EnumConstantNotPresentException.class, () -> {
            gridFactory.createGrid(""+notExistentValue);
        }, "Expected an EnumConstantNotPresentException to be thrown for an invalid grid type");
    }
}
