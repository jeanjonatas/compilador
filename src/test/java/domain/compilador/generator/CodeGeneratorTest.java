package domain.compilador.generator;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CodeGeneratorTest {


    @Test
    public void writeLine() throws IOException {

        CodeGenerator codeGenerator = new CodeGenerator();

        codeGenerator.addCode("Teste");
        codeGenerator.addCode("\n");
        codeGenerator.addCode("Teste2");

        codeGenerator.writeLine(1);
    }
}