package de.nordakademie.zellulaere_automaten.grid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link GridType} enum.
 * This class verifies the functionality of the methods within the GridType enum,
 * ensuring that each GridType value behaves as expected.
 *
 * The tests cover:
 * - Retrieving a grid type by its associated integer value using {@code getType}.
 * - Handling invalid values in {@code getType}, which should throw an exception.
 * - Validating that each grid type returns the correct integer value using {@code getValue}.
 * @author Daria Stolarczyk
 */
class GridTypeTest {

    @Test
    void getType_WithValidValue_ShouldReturnCorrectGridType() {
        assertEquals(GridType.ClassicGrid, GridType.getType(1));
        assertEquals(GridType.SetGrid, GridType.getType(2));
    }

    @Test
    void getType_WithInvalidValue_ShouldThrowException() {
        int nonExistentValue = GridType.values().length + 1;
        assertThrows(EnumConstantNotPresentException.class, () -> {
            GridType.getType(nonExistentValue);
        });
    }

    @Test
    void getValue_WhenCalled_ShouldReturnCorrectValue() {
        assertEquals(1, GridType.ClassicGrid.getValue());
        assertEquals(2, GridType.SetGrid.getValue());
    }
}