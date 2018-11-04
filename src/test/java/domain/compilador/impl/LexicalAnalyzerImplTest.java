package domain.compilador.impl;

import domain.compilador.LexicalAnalyzer;
import domain.compilador.Symbol;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.assertj.core.api.Assertions.assertThat;

public class LexicalAnalyzerImplTest {

    private LexicalAnalyzer lexicalAnalyzer;

    @Before
    public void setUp() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("codigo.txt").getFile());
        BufferedReader buffer = new BufferedReader(new FileReader(caminho(file)));
        lexicalAnalyzer = new LexicalAnalyzerImpl(buffer);
    }

    @Test
    public void testAnalyse() throws Exception {
        Symbol tokens = null;

        tokens = lexicalAnalyzer.analyse();

        assertThat(tokens.getLexema()).isEqualTo("inicio");
    }

    private BufferedReader bufferedReaderer() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("codigo.txt").getFile());
        return new BufferedReader(new FileReader(caminho(file)));
    }

    private String caminho(File file) {
        String absolutePath = file.getAbsolutePath();
        return absolutePath.replaceAll("%20", " ").replaceAll("%c2%ba", "º")
                .replaceAll("%c3%ad", "í");
    }

}
