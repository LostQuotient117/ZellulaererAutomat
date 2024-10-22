package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogTests {
    public static Cell[][] testArray100x100;
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
    public static void arrayForTestCheckInputType(){
        testArray100x100 = new Cell[100][100];
        for (int i = 0; i < testArray100x100.length; i++) {
            for (int j = 0; j < testArray100x100[i].length; j++) {
                testArray100x100[i][j] = new Cell(i, j, false);
            }
        }
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
            wantedStringBuilder.append('\n');
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
     * {@code true} when a {@code testArray[][]} is passed.
     */
    @Test
    public void testInputIsArray_Array(){
        Log log = new LogFile();
        assertTrue(log.inputIsArray(testArray100x100));
    }
    //einfügen mit richtigen Daten, wenn die Datenstruktur der Hashmap fest steht

    /**
     * This test checks whether the method {@link Log#inputIsArray(Object)} returns
     * {@code false} when a {@code hashmap} is passed.
     */
    //@Test
    //public void testInputIsArray_NotArray(){
     //   Log log = new LogFile();
     //   assertFalse(log.inputIsArray("not an array"));
    //}

    //endregion

    //region formatArrayToStringTests

    /**
     * Tests the {@link Log#formatArrayToString(Cell[][])} method to ensure it correctly formats
     * a 100x100 array of {@link Cell} objects into the expected string representation.
     * <p>
     * This test compares the output of the {@code formatArrayToString} method with the predefined
     * {@code wantedString} to verify that the formatting is accurate.
     * </p>
     */
    @Test
    public void formatArrayToStringTest_Array(){
        Log log = new LogConsole();
        assertEquals(wantedString, log.formatArrayToString(testArray100x100));
    }

    //endregion
}
