package de.nordakademie.zellulaere_automaten.grid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the GridType enum.
 * It validates the functionality of the GridType enum methods.
 */
class GridTypeTest {

    /**
     * Tests the getType() method with valid values.
     * Ensures that the correct GridType is returned for a given valid integer value.
     */
    @Test
    void getType_WithValidValue_ShouldReturnCorrectGridType() {
        assertEquals(GridType.ClassicGrid, GridType.getType(1));
        assertEquals(GridType.SetGrid, GridType.getType(2));
    }

    /**
     * Tests the getType() method with an invalid value.
     * Ensures that the method throws an EnumConstantNotPresentException when an invalid value is provided.
     */
    @Test
    void getType_WithInvalidValue_ShouldThrowException() {
        int nonExistentValue = GridType.values().length + 1;
        assertThrows(EnumConstantNotPresentException.class, () -> {
            GridType.getType(nonExistentValue);
        });
    }

    /**
     * Tests the getValue() method.
     * Ensures that the correct integer value is returned for each GridType constant.
     */
    @Test
    void getValue_WhenCalled_ShouldReturnCorrectValue() {
        assertEquals(1, GridType.ClassicGrid.getValue());
        assertEquals(2, GridType.SetGrid.getValue());
    }
}