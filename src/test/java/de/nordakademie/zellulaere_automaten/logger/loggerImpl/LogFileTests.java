package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LogFileTests {
    private static String wantedString100x100;
    private static String wantedStringHeaderAndBody;
    private static String wantedStringHeaderAndBody100Iterations;
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
        wantedString100x100 = wantedStringBuilder.toString();
    }
    /**
     * Initializes a string representing a header and a 100x100 grid of zeros for testing purposes.
     * <p>
     * This method is annotated with {@code @BeforeAll} to ensure that the string is set up before any tests are run.
     * The string consists of a header line followed by one hundred lines, each containing one hundred zeros,
     * with each line separated by a newline character. The header includes the iteration count in parentheses.
     * </p>
     */
    @BeforeAll
    public static void wantedStringHeaderAndBodyForTestConsoleWriteLog() {
        StringBuilder wantedStringBuilder = new StringBuilder();
        wantedStringBuilder.append("### (99)").append(System.lineSeparator());
        for (int i = 0; i < 100; i++) {
            wantedStringBuilder.append("0".repeat(100));
            wantedStringBuilder.append(System.lineSeparator());
        }
        wantedStringHeaderAndBody = wantedStringBuilder.toString();
    }
    /**
     * Generates a test string with 100 iterations of a specific pattern.
     * Each iteration consists of a header line followed by 100 lines of 100 zeros.
     * The generated string is stored in the static variable {@code wantedStringHeaderAndBody100Iterations}.
     */
    @BeforeAll
    public static void wantedStringHeaderAndBody100IterationsForTestWriteLogWith100Iterations(){
        StringBuilder wantedStringBuilder = new StringBuilder();
        for (int i = 1; i <= 100; i++) {
            wantedStringBuilder.append("### (").append(i).append(")").append(System.lineSeparator());
            for (int j = 0; j < 100; j++) {
                wantedStringBuilder.append("0".repeat(100));
                wantedStringBuilder.append(System.lineSeparator());
            }
            wantedStringBuilder.append(System.lineSeparator());
        }
        wantedStringHeaderAndBody100Iterations = wantedStringBuilder.toString();
    }
    /**
     * Tests the private method {@code buildStringForFile} of the {@code LogFile} class.
     * <p>
     * This test uses reflection to access the private method and verifies that the method
     * correctly builds a string with a header and a 100x100 grid of zeros.
     * </p>
     *
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException if the underlying method is inaccessible
     * @throws NoSuchMethodException if the method cannot be found
     */
    @Test
    public void testBuildStringForFile() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        LogFile logFile = new LogFile();
        Method privateBuildStringForFile = LogFile.class.getDeclaredMethod("buildStringForFile", String.class, int.class);
        privateBuildStringForFile.setAccessible(true);
        String result = (String) privateBuildStringForFile.invoke(logFile, wantedString100x100, 99);
        Assertions.assertEquals(wantedStringHeaderAndBody, result);
    }
    /**
     * Tests the private method {@code exportGridToFile} of the {@code LogFile} class.
     * <p>
     * This test uses reflection to access the private method and verifies that the method
     * correctly exports a string with a header and a 100x100 grid of zeros to a file.
     * </p>
     *
     * @throws NoSuchMethodException if the method cannot be found
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException if the underlying method is inaccessible
     */
    @Test
    public void testExportGridToFile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LogFile logFile = new LogFile();
        String filename = "src/main/java/de/nordakademie/zellulaere_automaten/logger/loggerOutput/Log.log";

        //deletion of old file for the new test
        File file = new File(filename);
        file.delete();

        Method privateExportGridToFile = LogFile.class.getDeclaredMethod("exportGridToFile", String.class);
        privateExportGridToFile.setAccessible(true);
        privateExportGridToFile.invoke(logFile, wantedStringHeaderAndBody);

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null){
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(wantedStringHeaderAndBody.trim(), fileContent.toString().trim());
    }
    /**
     * Tests the {@link LogFile#writeLog(String formattedGrid, int iteration)}.
     * <p>
     * This test verifies that the method correctly writes a string with a header and a 100x100 grid of zeros to a file.
     * </p>
     */
    @Test
    public void testWriteLog(){
        LogFile logFile = new LogFile();
        String filename = "src/main/java/de/nordakademie/zellulaere_automaten/logger/loggerOutput/Log.log";

        //deletion of old file for the new test
        File file = new File(filename);
        file.delete();

        logFile.writeLog(wantedString100x100, 99);

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null){
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(wantedStringHeaderAndBody.trim(), fileContent.toString().trim());
    }
    /**
     * Tests {@link LogFile#writeLog(String, int)} with 100 iterations.
     * <p>
     * This test verifies that the method correctly writes a string with a header and a 100x100 grid of zeros to a file
     * for 100 iterations.
     * </p>
     */
    @Test
    public void testWriteLogWith100Iterations(){
        LogFile logFile = new LogFile();
        String filename = "src/main/java/de/nordakademie/zellulaere_automaten/logger/loggerOutput/Log.log";

        //deletion of old file for the new test
        File file = new File(filename);
        file.delete();

        for (int i = 1; i <= 100; i++) {
            logFile.writeLog(wantedString100x100, i);
        }

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null){
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(wantedStringHeaderAndBody100Iterations.trim(), fileContent.toString().trim());
    }
}