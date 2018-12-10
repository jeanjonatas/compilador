package domain.compilador.sintax;

import domain.compilador.LexicalAnalyzer;
import domain.compilador.Symbol;
import domain.compilador.semantic.SemanticAnalyser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SintaxAnalyzer {

    private Stack<Integer> estados = new Stack<>();
    private Stack<Symbol> simbolos = new Stack<>();
    private ControllerOperation controllerOperation;
    private LexicalAnalyzer lexicalAnalyzer;
    private Symbol symbol = new Symbol("", "");
    private SemanticAnalyser semanticAnalyser;

    public SintaxAnalyzer(LexicalAnalyzer lexicalAnalyzer) throws IOException {
        estados.push(0);
        this.lexicalAnalyzer = lexicalAnalyzer;
        this.semanticAnalyser = new SemanticAnalyser();
        this.controllerOperation = new ControllerOperation();
    }

    public void analyse() {
        long inicio = System.currentTimeMillis();
        Operation operation;
        Symbol firstSymbol = lexicalAnalyzer.analyse();
        symbol = firstSymbol;
        do {
            int topState = estados.peek();
            String token = symbol.getToken();
            if(symbol.getToken().equals("id") && symbol.getLexema().equals("B") || topState == 36){
                System.out.println("");
            }
            operation = controllerOperation.getOperation(topState, token);
            switch (operation.getAction()) {
                case EMPILHAR:
                    empilhar(symbol, operation);
                    break;
                case REDUZIR:
                    reduzir(symbol, operation);
                    break;
                case ACEITAR:
                    aceitar();
                    break;
                case ERRO:
                    erro(operation);
                default:
                    errorDefault();
            }

        } while (!operation.isAccepted());
        long fim = System.currentTimeMillis();
        System.out.println("Tempo total de análise: " + (fim - inicio) / 1000.0 + " segundos");
    }

    private void erro(Operation operation) {
        ErrorMessage error = ErrorMessage.of(operation.getValor());
        System.out.println(error.getMessage());
        throw new IllegalArgumentException(error.getMessage());
    }

    private void errorDefault() {
        String msg = "Um erro ocorreu na analise da linha:%s e coluna: %s";
        throw new IllegalArgumentException(String.format(msg, lexicalAnalyzer.getLine(), lexicalAnalyzer.getColumn()));
    }

    private void aceitar() {
        System.out.println("A cadeia foi aceita com sucesso !");
        semanticAnalyser.writeFile();
    }

    private void reduzir(Symbol symbol, Operation operation) {
        if(operation.getValor() == 19){
            System.out.println(operation);
        }
        Sentences production = Sentences.of(operation.getValor());
        List<Symbol> symbolsSemantic = new ArrayList<>();
        for (int i = 0; i < production.getSize(); i++) {
            this.estados.pop();
            Symbol symbolRemoved = this.simbolos.pop();
            symbolsSemantic.add(symbolRemoved);
        }
        int state = controllerOperation.getStateNonTerminal(this.estados.peek(), production.getGenerator());
        this.estados.push(state);
        this.simbolos.push(new Symbol(production.getGenerator(), null));

        System.out.println(production);
        if (semanticAnalyser.existsRule(operation.getValor())) {

            try {
                symbolsSemantic.add(this.simbolos.peek());
                semanticAnalyser.analyseSemantic(operation.getValor(), symbol, symbolsSemantic);
            } catch (Exception ex) {
                String msg = "Um erro ocorreu na linha %s e na coluna %s";
                throw new IllegalArgumentException(String.format(msg, lexicalAnalyzer.getLine(), lexicalAnalyzer.getColumn()), ex);
            }
        }
//Chamar o semântico
    }

    private void empilhar(Symbol simbolo, Operation operation) {
        this.simbolos.push(simbolo);
        this.estados.push(operation.getValor());
        this.symbol = lexicalAnalyzer.analyse();
    }
}
