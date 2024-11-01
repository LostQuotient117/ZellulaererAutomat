package de.nordakademie.zellulaere_automaten;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String result = (String) privateExecuteChosenExperiment.invoke(new InputHandler(), calcType, configType, gridType, logType);

        Path generatedFile = Paths.get("").toAbsolutePath().resolve(expectedFileName);
        Assertions.assertTrue(Files.exists(generatedFile), "The expected file was not generated: " + expectedFileName);
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
        String result = (String) privateExecuteAllExperiments.invoke(new InputHandler(), "1");

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

}
