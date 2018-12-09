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

    public SemanticAnalyser() throws IOException {
        codeGenerator = new CodeGenerator();
    }

    public boolean existsRule(int index) {
        return SemanticRules.existsRule(index);

    }

    public void analyseSemantic(int index, Symbol symbol, List<Symbol> symbolsProduced) {
        SemanticRules rule = SemanticRules.of(index);
        String line = rule.executar(symbol, symbolsProduced);
        codeGenerator.addCode(line);
    }

    public void writeFile() {
        try {
            codeGenerator.writeLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("Um erro ocorreu na hora de salvar o arquivo");
        }
    }

}
