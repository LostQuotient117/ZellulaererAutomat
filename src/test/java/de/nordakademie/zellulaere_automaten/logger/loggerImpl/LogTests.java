package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogTests {
    public static Cell[][] testArray;
    @BeforeAll
    public static void arrayForTestCheckInputType(){
        testArray = new Cell[100][100];
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray[i].length; j++) {
                testArray[i][j] = new Cell(i, j, false);
            }
        }
    }

    @Test
    public void testLog(){
        //hier soll so das mit größte getestet werden
    }

    /**
     * This test checks whether the method {@link Log#inputIsArray(Object)} returns
     * the value true when a {@code testArray[][]} is passed.
     */
    @Test
    public void testInputIsArray_Array(){
        Log log = new LogFile();
        assertTrue(log.inputIsArray(testArray));

    }
}
