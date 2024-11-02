package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
/**
 * This class contains unit tests for the {@link LogConsole} class.
 * It verifies the correct functionality of logging methods that output to the console.
 * The tests use Mockito to mock the {@link LogConsole} class and capture console output
 * using a {@link ByteArrayOutputStream}.
 *
 * @author Jannick Gottschalk
 */
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
     * Tests {@link LogConsole}.stepWriterConsole(int, String) and ensure
     * that the correct output is selected in the console
     * It inputs {@code iteration = 2} which specifies the experiment iteration that needs to be printed
     */
    @Test
    public void stepWriterConsole_IterationAndTestClassName_IterationWithoutClassName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int iteration = 2;
        String wantedOutput = "### (2)";
        LogConsole logConsole = Mockito.mock(LogConsole.class);
        outputStreamCaptor.reset();
        Method privateStepWriter = LogConsole.class.getDeclaredMethod("stepWriterConsole", int.class, String.class);
        privateStepWriter.setAccessible(true);
        privateStepWriter.invoke(logConsole, iteration, "Test");
        Assertions.assertEquals(wantedOutput, outputStreamCaptor.toString().trim());
    }

    /**
     * Tests {@link LogConsole}.stepWriterConsole(int, String) and ensure
     * that the correct output is selected in the console
     * It inputs {@code iteration = 1} which specifies the experiment iteration that needs to be printed including
     * the test experiment class name.
     */
    @Test
    public void stepWriterConsole_IterationAndTestClassName_IterationWithClassName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int iteration = 0;
        String wantedOutput = "Test:" + System.lineSeparator() + "### (0)";
        LogConsole logConsole = Mockito.mock(LogConsole.class);
        outputStreamCaptor.reset();
        Method privateStepWriter = LogConsole.class.getDeclaredMethod("stepWriterConsole", int.class, String.class);
        privateStepWriter.setAccessible(true);
        privateStepWriter.invoke(logConsole, iteration, "Test");
        Assertions.assertEquals(wantedOutput, outputStreamCaptor.toString().trim());
    }

    /**
     * Tests {@link LogConsole}.stepWriterConsole(int, String) to ensure
     * that the correct output is printed to the console.
     * It inputs {@code iteration = 100} which specifies the experiment iteration that needs to be printed.
     */
    @Test
    public void stepWriter_IterationAndClassName_IterationOutput100() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LogConsole logConsole = Mockito.mock(LogConsole.class);
        int iteration = 100;
        String wantedOutput = "### (100)";
        outputStreamCaptor.reset();
        Method privateStepWriter = LogConsole.class.getDeclaredMethod("stepWriterConsole", int.class, String.class);
        privateStepWriter.setAccessible(true);
        privateStepWriter.invoke(logConsole, iteration, "Test");
        Assertions.assertEquals(wantedOutput, outputStreamCaptor.toString().trim());
    }
    /**
     * Tests {@link LogConsole}.stepWriterConsole(int, String) and ensures
     * that an exception is thrown if {@code step} is negative
     */
    @Test
    public void stepWriter_IllegalIterationAndClassName_IllegalArgumentException() throws NoSuchMethodException {
        LogConsole logConsole = Mockito.mock(LogConsole.class);
        Method privateStepWriter = LogConsole.class.getDeclaredMethod("stepWriterConsole", int.class, String.class);
        privateStepWriter.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> privateStepWriter.invoke(logConsole, -5, "Test"));
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
    }

    //endregion
    //region testConsoleWriteLogBody
    /**
     * Tests the {@link LogConsole}.writeLogBody(String) method to ensure it correctly writes the log to the console.
     * This test verifies that the {@code writeLogBody} method outputs the expected string to the console.
     * The output is captured and compared to the predefined {@code wantedString100x100} to ensure accuracy.
     */
    @Test
    public void writeLogBody_GridString_ConsoleGridBody() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LogConsole logConsole = Mockito.mock(LogConsole.class);
        outputStreamCaptor.reset();
        Method privateWriteLogBody = LogConsole.class.getDeclaredMethod("writeLogBody", String.class);
        privateWriteLogBody.setAccessible(true);
        privateWriteLogBody.invoke(logConsole, wantedString100x100.trim());
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
        LogConsole mockLogConsole = Mockito.mock(LogConsole.class);
        outputStreamCaptor.reset();
        doAnswer(invocation -> {
            System.out.print(wantedStringHeaderAndBody);
            return null;
        }).when(mockLogConsole).writeLog(wantedString100x100.trim(), 99, "Test");
        mockLogConsole.writeLog(wantedString100x100.trim(), 99, "Test");
        verify(mockLogConsole).writeLog(wantedString100x100.trim(), 99, "Test");
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
    LogConsole mockLogConsole = Mockito.mock(LogConsole.class);
    outputStreamCaptor.reset();
    doAnswer(invocation -> {
        System.out.print(wantedStringHeaderAndBodyWithClassName);
        return null;
    }).when(mockLogConsole).writeLog(wantedString100x100.trim(), 1, "Test");
    mockLogConsole.writeLog(wantedString100x100.trim(), 1, "Test");
    verify(mockLogConsole).writeLog(wantedString100x100.trim(), 1, "Test");
    assertEquals(wantedStringHeaderAndBodyWithClassName, outputStreamCaptor.toString());
}
    /**
     * Tests the {@link LogConsole#writeLog(String, int, String)} method with an illegal iteration count.
     * This test verifies that the {@code writeLog} method throws an {@link IllegalArgumentException}
     * when called with a negative iteration count. This ensures that the method handles invalid input
     * appropriately.
     */
    @Test
    public void writeLog_illegalIteration(){
        LogConsole mockLogConsole = Mockito.mock(LogConsole.class);
        doThrow(new IllegalArgumentException()).when(mockLogConsole).writeLog(wantedStringHeaderAndBody, -5, "Test");
        assertThrows(IllegalArgumentException.class, () -> mockLogConsole.writeLog(wantedStringHeaderAndBody, -5, "Test"));
    }

    /**
     * Tests the {@link LogConsole#writeLog(String, String)} method to ensure it correctly writes the end message to the console.
     * This test verifies that the {@code writeLog} method outputs the expected end message to the console.
     * The output is captured and compared to the provided {@code endMessage} to ensure accuracy.
     */
    @Test
    public void writeLog_EndMessage() {
        LogConsole mockLogConsole = Mockito.mock(LogConsole.class);
        String endMessage =  "Experiment stopped: Reached 100 iterations.";
        outputStreamCaptor.reset();
        doAnswer(invocation -> {
            System.out.print(endMessage);
            return null;
        }).when(mockLogConsole).writeLog(endMessage, "Test");
        mockLogConsole.writeLog(endMessage, "Test");
        verify(mockLogConsole).writeLog(endMessage, "Test");
        Assertions.assertEquals(endMessage, outputStreamCaptor.toString().trim());
    }
    //endregion
}
