package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test-class tests the {@link Log}-class and its methods.
 */
public class LogTests {
    public static Cell[][] testArray100x100;
    public static List<List<Cell>> testList100x100;
    public static String wantedStringForList;
    public static String wantedString;

    //region BeforeAll

    /**
     * Initializes a 100x100 array of {@link Cell} objects for testing purposes.
     * <p>
     * This method is annotated with {@code @BeforeAll} to ensure that the array is
     * set up before any tests are run. Each cell in the array is instantiated with
     * its respective row and column indices and a default state of {@code false}.
     * </p>
     */
    @BeforeAll
    public static void arrayForTesting(){
        testArray100x100 = new Cell[100][100];
        for (int i = 0; i < testArray100x100.length; i++) {
            for (int j = 0; j < testArray100x100[i].length; j++) {
                testArray100x100[i][j] = new Cell(i, j, false);
            }
        }
    }
    /**
     * Initializes a 100x100 grid of {@link Cell}´s and a corresponding string representation for testing purposes.
     * <p>
     * The grid is initialized with null values, and specific Cells are added at positions {@code (1,2)}, {@code (50,98)}, and {@code (35,75)}.
     * The string representation marks these positions with {@code '1'} and all other positions with {@code '0'}.
     * </p>
     */
    @BeforeAll
    public static void listAndStringForTestingListToString(){
        testList100x100 = new ArrayList<>();
        //initialisation of inner list
        for (int i = 0; i < 100; i++) {
            List<Cell> innerList = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                innerList.add(null);
            }
            testList100x100.add(innerList);
        }
        //adding some Cells to List for testing
        testList100x100.get(1).set(2, new Cell(1, 2, true));
        testList100x100.get(50).set(98, new Cell(50, 98, true));
        testList100x100.get(35).set(75, new Cell(35, 75, true));

        StringBuilder wantedStringForListBuilder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if ((i == 1 && j == 2) || (i == 50 && j == 98) || (i ==35 && j == 75)) {
                    wantedStringForListBuilder.append('1');
                } else {
                    wantedStringForListBuilder.append('0');
                }
            }
            wantedStringForListBuilder.append(System.lineSeparator());
        }
        wantedStringForList = wantedStringForListBuilder.toString();
    }

    /**
     * Initializes a string representing a 100x100 grid of zeros for testing purposes.
     * <p>
     * This method is annotated with {@code @BeforeAll} to ensure that the string is
     * set up before any tests are run. The string consists of one hundred lines, each containing
     * one hundred zeros, with each line separated by a newline character to imitate the grid structure.
     * </p>
     */
    @BeforeAll
    public static void wantedStringForTestFormatArraytoStringTests() {
        StringBuilder wantedStringBuilder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            wantedStringBuilder.append("0".repeat(100));
            wantedStringBuilder.append(System.lineSeparator());
        }
        wantedString = wantedStringBuilder.toString();
    }

    //endregion

    @Test
    public void testLog(){
        //hier soll so das mit größte getestet werden
    }
    //region isArrayTests

    /**
     * This test checks whether the method {@link Log#inputIsArray(Object)} returns
     * {@code true} when a {@code Array[][]} is passed.
     */
    @Test
    public void testInputIsArray_Array(){
        Log log = new LogFile();
        assertTrue(log.inputIsArray(testArray100x100));
    }

    /**
     * This test checks whether the method {@link Log#inputIsArray(Object)} returns
     * {@code false} when a {@code List<List<Cell>>} is passed.
     */
    @Test
    public void testInputIsArray_List(){
        Log log = new LogFile();
        assertFalse(log.inputIsArray(testList100x100));
    }
    //endregion
    //region formatToStringTests

    /**
     * Tests the {@link Log#formatArrayToString(Cell[][])} method to ensure it correctly formats
     * a 100x100 array of {@link Cell} objects into the expected string representation.
     * <p>
     * This test compares the output of the {@code formatArrayToString} method with the predefined
     * {@code wantedString} to verify that the formatting is accurate.
     * </p>
     */
    @Test
    public void formatArrayToStringTest(){
        Log log = new LogConsole();
        assertEquals(wantedString, log.formatArrayToString(testArray100x100));
    }

    /**
     * Tests the {@link Log#formatListToString(List)} method to ensure it correctly formats the grid of {@link Cell}´s as {@code List} into a {@code string}.
     * <p>
     * This test compares the output of the {@link Log#formatListToString(List)} method with the expected {@code string} representation
     * of the grid, ensuring that the method works as intended.
     * </p>
     */
    @Test
    public void formatListToStringTest(){
        Log log = new LogConsole();
        assertEquals(wantedStringForList, log.formatListToString(testList100x100));
    }
    //endregion
}
