package de.nordakademie.zellulaere_automaten;

import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This class contains unit tests for the {@link InputHandler} class.
 * It tests the methods responsible for executing chosen experiments and all experiments,
 * ensuring that the expected log files are generated and their contents match the expected output.
 *
 * @author Jannick Gottschalk
 * @author Lars Nicht
 */
public class InputHandlerTests {

    /**
     * Tests the {@link InputHandler}{@code .executeChosenExperiment(String, String, String, String)} method to ensure that it generates the expected log file
     * based on the provided user inputs.
     *
     * @throws Exception if any reflection-related operations fail
     */
    @Test
    public void executeChosenExperiment_userInputs_ShouldGenerateFile() throws Exception {
        String calcType = "1";
        String configType = "1";
        String gridType = "1";
        String logType = "1";
        String expectedFileName = "GameOfLifeExperiment1Structure1.log";

        Method privateExecuteChosenExperiment = InputHandler.class.getDeclaredMethod("executeChosenExperiment", String.class, String.class, String.class, String.class);
        privateExecuteChosenExperiment.setAccessible(true);
        privateExecuteChosenExperiment.invoke(new InputHandler(), calcType, configType, gridType, logType);

        Path generatedFile = Paths.get("").toAbsolutePath().resolve(expectedFileName);
        Assertions.assertTrue(Files.exists(generatedFile), "The expected file was not generated: " + expectedFileName);
    }
    /**
     * Tests the {@link InputHandler}{@code buildStringForExperiment(String, String, String, String)} method to ensure that it returns the expected string
     * based on the provided calculation type, configuration type, grid type, and log type.
     *
     * @throws NoSuchMethodException if the method cannot be found
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException if the method cannot be accessed
     */
    @Test
    public void buildStringForExperiment_ShouldReturnExpectedString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method privateBuildStringForExperiment = InputHandler.class.getDeclaredMethod("buildStringForExperiment", String.class, String.class, String.class, String.class);
        privateBuildStringForExperiment.setAccessible(true);

        String calcType = "1";
        String configType = "1";
        String gridType = "1";
        String logType = "1";
        String expectedOutput = "GameOfLifeExperiment1Structure1";

        String result = (String) privateBuildStringForExperiment.invoke(new InputHandler(), calcType, configType, gridType, logType);

        assertNotNull(result, "The result should not be null.");
        assertEquals(expectedOutput, result, "The output string does not match the expected value.");
    }
    /**
     * Tests the {@link InputHandler}{@code .executeAllExperiments(String)} method to ensure that it generates the expected log files
     * and compares their content with the expected output files.
     *
     * @throws Exception if any reflection-related operations fail or if file content does not match
     */
    @Test
    public void executeAllExperiments_WhenCalled_ShouldCheckOutputFilesForDifferences() throws Exception {
        Method privateExecuteAllExperiments = InputHandler.class.getDeclaredMethod("executeAllExperiments", String.class);
        privateExecuteAllExperiments.setAccessible(true);
        privateExecuteAllExperiments.invoke(new InputHandler(), "1");

        Path generatedOutputDir = Paths.get("").toAbsolutePath();
        Path expectedOutputDir = Paths.get("src/test/java/de/nordakademie/zellulaere_automaten/expectedOutputFiles");

        List<String> filesToCompare = List.of("GameOfLifeExperiment1Structure1.log",
                                            "GameOfLifeExperiment1Structure2.log",
                                            "GameOfLifeExperiment2Structure1.log",
                                            "GameOfLifeExperiment2Structure2.log",
                                            "GameOfLifeExperiment3Structure1.log",
                                            "GameOfLifeExperiment3Structure2.log",
                                            "ParityExperiment1Structure1.log",
                                            "ParityExperiment1Structure2.log",
                                            "ParityExperiment2Structure1.log",
                                            "ParityExperiment2Structure2.log");

        for (String fileName : filesToCompare) {
            Path generatedFile = generatedOutputDir.resolve(fileName);
            Path expectedFile = expectedOutputDir.resolve(fileName);

            List<String> generatedLines = Files.readAllLines(generatedFile);
            List<String> expectedLines = Files.readAllLines(expectedFile);

            assertEquals(expectedLines, generatedLines, "File content does not match for: " + fileName);
        }
    }
    /**
     * Tests the {@link InputHandler}{@code .getIExperiments()} method to ensure that it returns the expected array of experiments.
     * It verifies that the number of experiments and their names match the expected values.
     *
     * @throws NoSuchMethodException if the method cannot be found
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException if the method cannot be accessed
     */
    @Test
    public void getIExperiments_ShouldReturnExpectedExperiments() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method privateGetIExperiments = InputHandler.class.getDeclaredMethod("getIExperiments");
        privateGetIExperiments.setAccessible(true);

        IExperiment[] experiments = (IExperiment[]) privateGetIExperiments.invoke(new InputHandler());
        String[] expectedExperimentNames = {
                "GameOfLifeExperiment1Structure1",
                "GameOfLifeExperiment1Structure2",
                "GameOfLifeExperiment2Structure1",
                "GameOfLifeExperiment2Structure2",
                "GameOfLifeExperiment3Structure1",
                "GameOfLifeExperiment3Structure2",
                "ParityExperiment1Structure1",
                "ParityExperiment1Structure2",
                "ParityExperiment2Structure1",
                "ParityExperiment2Structure2"
        };

        assertEquals(expectedExperimentNames.length, experiments.length, "The number of experiments does not match the expected number.");

        for (int i = 0; i < expectedExperimentNames.length; i++) {
            assertEquals(expectedExperimentNames[i], experiments[i].getClass().getSimpleName(), "Experiment name does not match at index " + i);
        }
    }

}
