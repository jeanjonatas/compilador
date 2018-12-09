package domain.compilador.semantic;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SemanticAnalyserTest {


    @Test
    public void existsRule() throws IOException {
        SemanticAnalyser analyser = new SemanticAnalyser();
        assertTrue(analyser.existsRule(5));
        assertFalse((analyser.existsRule(3)));
    }
}