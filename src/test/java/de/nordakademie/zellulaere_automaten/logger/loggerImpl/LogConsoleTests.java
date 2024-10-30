package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogConsoleTests {
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    public static String wantedString100x100;
    public static String wantedStringHeaderAndBody;
    public static String wantedStringHeaderAndBodyWithClassName;

    //region BeforeEach
    /**
     * Sets up the test environment before each test method.
     * This method redirects the standard output to a {@link ByteArrayOutputStream}
     * to capture console output for verification. It also initializes the expected
     * output strings based on the test method name.
     * @param testInfo Provides information about the current test method.
     */
    @BeforeEach
    public void setUp(TestInfo testInfo){
        System.setOut(new PrintStream(outputStreamCaptor));
        String test= testInfo.getDisplayName();
        if (test.equals("writeLogBody_GridString_ConsoleGridBody()") ||
                test.equals("writeLog_GridIterationAndClassName_iteration99()") ||
                test.equals("writeLog_GridIterationAndClassName_iteration1WithClassName()")){
            StringBuilder wantedStringBuilder = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                wantedStringBuilder.append("0".repeat(100));
                wantedStringBuilder.append(System.lineSeparator());
            }
            wantedString100x100 = wantedStringBuilder.toString();
        }
        if (test.equals("writeLog_GridIterationAndClassName_iteration99()")){
            StringBuilder wantedStringBuilder = new StringBuilder();
            wantedStringBuilder.append("### (99)").append(System.lineSeparator());
            for (int i = 0; i < 100; i++) {
                wantedStringBuilder.append("0".repeat(100));
                wantedStringBuilder.append(System.lineSeparator());
            }
            wantedStringHeaderAndBody = wantedStringBuilder.toString();
        }
        if (test.equals("writeLog_GridIterationAndClassName_iteration1WithClassName()")){
            StringBuilder wantedStringBuilder = new StringBuilder();
            wantedStringBuilder.append("Test:").append(System.lineSeparator());
            wantedStringBuilder.append("### (1)").append(System.lineSeparator());
            for (int i = 0; i < 100; i++) {
                wantedStringBuilder.append("0".repeat(100));
                wantedStringBuilder.append(System.lineSeparator());
            }
            wantedStringHeaderAndBodyWithClassName = wantedStringBuilder.toString();
        }
    }
    //endregion
    //region testStepWriter
    /**
     * Tests {@link LogConsole#stepWriterConsole(int, String)} and ensure
     * that the correct output is selected in the console
     * It inputs {@code iteration = 2} which specifies the experiment iteration that needs to be printed
     */
    @Test
    public void stepWriterConsole_IterationAndTestClassName_IterationWithoutClassName(){
        int iteration = 2;
        String wantedOutput = "### (2)";
        LogConsole logConsole = new LogConsole();
        outputStreamCaptor.reset();
        logConsole.stepWriterConsole(iteration, "Test");
        Assertions.assertEquals(wantedOutput, outputStreamCaptor.toString().trim());
    }

    /**
     * Tests {@link LogConsole#stepWriterConsole(int, String)} and ensure
     * that the correct output is selected in the console
     * It inputs {@code iteration = 1} which specifies the experiment iteration that needs to be printed including
     * the test experiment class name.
     */
    @Test
    public void stepWriterConsole_IterationAndTestClassName_IterationWithClassName(){
        int iteration = 1;
        String wantedOutput = "Test:" + System.lineSeparator() + "### (1)";
        LogConsole logConsole = new LogConsole();
        outputStreamCaptor.reset();
        logConsole.stepWriterConsole(iteration, "Test");
        Assertions.assertEquals(wantedOutput, outputStreamCaptor.toString().trim());
    }

    /**
     * Tests {@link LogConsole#stepWriterConsole(int, String)} and ensure
     * that the correct output is selected in the console
     * It inputs {@code step = 100} which specifies the experiment iteration that needs to be printed
     */
    @Test
    public void stepWriter_IterationAndClassName_IterationOutput100(){
        int iteration = 100;
        String wantedOutput = "### (100)";
        LogConsole logConsole = new LogConsole();
        outputStreamCaptor.reset();
        logConsole.stepWriterConsole(iteration, "Test");
        Assertions.assertEquals(wantedOutput, outputStreamCaptor.toString().trim());
    }
    /**
     * Tests {@link LogConsole#stepWriterConsole(int, String)} and ensures
     * that an exception is thrown if {@code step} is negative
     */
    @Test
    public void stepWriter_IllegalIterationAndClassName_IllegalArgumentException(){
        LogConsole logConsole = new LogConsole();
        assertThrows(IllegalArgumentException.class, () -> logConsole.stepWriterConsole(-5, "Test"));
    }

    //endregion
    //region testConsoleWriteLogBody
    /**
     * Tests the {@link LogConsole#writeLogBody(String)} method to ensure it correctly writes the log to the console.
     * This test verifies that the {@code writeLogBody} method outputs the expected string to the console.
     * The output is captured and compared to the predefined {@code wantedString100x100} to ensure accuracy.
     */
    @Test
    public void writeLogBody_GridString_ConsoleGridBody(){
        LogConsole logConsole = new LogConsole();
        outputStreamCaptor.reset();
        logConsole.writeLogBody(wantedString100x100.trim());
        assertEquals(wantedString100x100, outputStreamCaptor.toString());
    }
    //endregion
    //region testConsoleWriteLog
    /**
     * Tests the {@link LogConsole#writeLog(String, int, String)} method with a valid iteration count.
     * This test verifies that the {@code writeLog} method correctly writes the log to the console
     * when called with an iteration count of 99. The output is captured and compared to the predefined
     * {@code wantedStringHeaderAndBody} to ensure accuracy.
     */
    @Test
    public void  writeLog_GridIterationAndClassName_iteration99(){
        LogConsole logConsole = new LogConsole();
        outputStreamCaptor.reset();
        logConsole.writeLog(wantedString100x100.trim(), 99, "Test");
        assertEquals(wantedStringHeaderAndBody, outputStreamCaptor.toString());
    }
    /**
 * Tests the {@link LogConsole#writeLog(String, int, String)} method with an iteration count of 1 and a class name.
 * This test verifies that the {@code writeLog} method correctly writes the log to the console
 * when called with an iteration count of 1 and a class name. The output is captured and compared
 * to the predefined {@code wantedStringHeaderAndBodyWithClassName} to ensure accuracy.
 */
@Test
public void writeLog_GridIterationAndClassName_iteration1WithClassName(){
    LogConsole logConsole = new LogConsole();
    outputStreamCaptor.reset();
    logConsole.writeLog(wantedString100x100.trim(), 1, "Test");
    assertEquals(wantedStringHeaderAndBodyWithClassName, outputStreamCaptor.toString());
}
    /**
     * Tests the {@link LogConsole#writeLog(String, int, String)} method with an illegal iteration count.
     * This test verifies that the {@code writeLog} method throws an {@link IllegalArgumentException}
     * when called with a negative iteration count. This ensures that the method handles invalid input
     * appropriately.
     */
    @Test
    public void testConsoleWriteLog_illegalIteration(){
        LogConsole logConsole = new LogConsole();
        assertThrows(IllegalArgumentException.class, () -> logConsole.writeLog(wantedStringHeaderAndBody, -5, "Test"));
    }

    /**
     * Tests the {@link LogConsole#writeLog(String, String)} method to ensure it correctly writes the end message to the console.
     * This test verifies that the {@code writeLog} method outputs the expected end message to the console.
     * The output is captured and compared to the provided {@code endMessage} to ensure accuracy.
     */
    @Test
    public void testWriteLog_EndMessage() {
        LogConsole logConsole = new LogConsole();
        String endMessage =  "Experiment stopped: Reached 100 iterations.";
        outputStreamCaptor.reset();
        logConsole.writeLog(endMessage, "Test");
        Assertions.assertEquals(endMessage, outputStreamCaptor.toString().trim());
    }
    //endregion
}
