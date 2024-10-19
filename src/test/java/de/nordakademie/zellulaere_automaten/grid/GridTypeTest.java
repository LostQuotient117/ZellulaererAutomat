package de.nordakademie.zellulaere_automaten.grid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GridTypeTest {

    @Test
    public void testGetType_ValidValue() {
        assertEquals(GridType.ClassicGrid, GridType.getType(1));
        assertEquals(GridType.HashMapGrid, GridType.getType(2));
    }

    @Test
    public void testGetType_InvalidValue() {
        int nonExistentValue = GridType.values().length + 1;
        assertThrows(EnumConstantNotPresentException.class, () -> {
            GridType.getType(nonExistentValue);
        });
    }

    @Test
    public void testGetValue() {
        assertEquals(1, GridType.ClassicGrid.getValue());
        assertEquals(2, GridType.HashMapGrid.getValue());
    }
}
