package de.nordakademie.zellulaere_automaten;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputHandlerTests {

    @Test
    void executeAllExperiments_WhenCalled_ShouldCheckOutputFilesForDifferences() throws Exception {

        // Call the method to generate output files
        InputHandler inputHandler = new InputHandler();
        inputHandler.executeAllExperiments("1");

        // Define paths to the generated and expected output files
        Path generatedOutputDir = Paths.get("").toAbsolutePath();
        Path expectedOutputDir = Paths.get("src/test/java/de/nordakademie/zellulaere_automaten/expectedOutputFiles");

        // List of files to compare
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

            // Read the contents of the files
            List<String> generatedLines = Files.readAllLines(generatedFile);
            List<String> expectedLines = Files.readAllLines(expectedFile);

            // Compare the contents of the files, ignoring creation time
            assertEquals(expectedLines, generatedLines, "File content does not match for: " + fileName);
        }
    }

}
