package domain.compilador.sintax;

import domain.compilador.LexicalAnalyzer;
import domain.compilador.lexical.LexicalAnalyzerImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class SintaxAnalyzerTest {

    private SintaxAnalyzer sintaxAnalyzer;
    private LexicalAnalyzer lexicalAnalyzer;

    @Before
    public void setUp() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("codigo.txt").getFile());
        BufferedReader buffer = new BufferedReader(new FileReader(caminho(file)));
        lexicalAnalyzer = new LexicalAnalyzerImpl(buffer);
        sintaxAnalyzer = new SintaxAnalyzer(lexicalAnalyzer);
    }

    @Test
    public void analyse() {

        sintaxAnalyzer.analyse();

    }

    private String caminho(File file) {
        String absolutePath = file.getAbsolutePath();
        return absolutePath.replaceAll("%20", " ").replaceAll("%c2%ba", "º")
                .replaceAll("%c3%ad", "í");
    }
}