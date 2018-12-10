package domain.compilador.semantic;

import domain.compilador.Symbol;
import domain.compilador.generator.CodeGenerator;

import java.io.IOException;
import java.util.List;

/**
 * Analisador semântico
 */
public class SemanticAnalyser {

    private CodeGenerator codeGenerator;
    private static int temporaryCounter = -1;


    public SemanticAnalyser() throws IOException {
        codeGenerator = new CodeGenerator();
    }

    public boolean existsRule(int index) {
        return SemanticRules.existsRule(index);

    }

    public void analyseSemantic(int index, Symbol symbol, List<Symbol> symbolsProduced) {
        SemanticRules rule = SemanticRules.of(index);
        String line = rule.executar(symbolsProduced);
        codeGenerator.addCode(line);
    }

    public static int getCounter() {
        return temporaryCounter;
    }

    public static int getAndAddCounter() {
        temporaryCounter++;
        return temporaryCounter;
    }

    public void writeFile() {
        try {
            codeGenerator.writeLine(temporaryCounter);
        } catch (IOException e) {
            throw new IllegalArgumentException("Um erro ocorreu na hora de salvar o arquivo");
        }
    }

}
