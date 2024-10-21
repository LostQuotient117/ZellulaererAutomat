package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LogTests {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testLog(){
        //hier soll so das mit größte getestet werden
    }
    public void teststepWriter(){
        int step = 1;
        String wantedOutput = "### (1)";
        Log log = new LogFile();
        log.stepWriter(step);

        Assertions.assertEquals(wantedOutput, outputStreamCaptor.toString().trim());
    }
}
