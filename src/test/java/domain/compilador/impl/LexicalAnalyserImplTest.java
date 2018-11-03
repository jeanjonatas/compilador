package domain.compilador.impl;

import domain.compilador.LexicalAnalyser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.assertj.core.api.Assertions.assertThat;

public class LexicalAnalyserImplTest {

    private LexicalAnalyser lexicalAnalyser;

    @Before
    public void setUp() {
        lexicalAnalyser = new LexicalAnalyserImpl();
    }

    @Test
    public void testAnalyse() throws Exception {
        String tokens = lexicalAnalyser.analyse(bufferedReaderer());

        assertThat(tokens).isEqualTo(
                "{A=Symbol (token=id, lexema=A),  A=Symbol (token=id, lexema= A), B=Symbol (token=id, lexema=B),  B=Symbol (token=id, lexema= B), leia=Symbol (token=leia, lexema=leia), varinicio=Symbol (token=varninicio, lexema=varninicio), C=Symbol (token=id, lexema=C),  C=Symbol (token=id, lexema= C), D=Symbol (token=id, lexema=D),  D=Symbol (token=id, lexema= D), inteiro=Symbol (token=inteiro, lexema=inteiro), escreva=Symbol (token=escreva, lexema=escreva), inicio=Symbol (token=inicio, lexema=inicio), fim=Symbol (token=fim, lexema=fim), real=Symbol (token=real, lexema=real), varfim=Symbol (token=varfim, lexema=varfim), senao=Symbol (token=senao, lexema=senao), se=Symbol (token=se, lexema=se), fimse=Symbol (token=fimse, lexema=fimse), lit=Symbol (token=lit, lexema=lit), entao=Symbol (token=entao, lexema=entao)}");
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
