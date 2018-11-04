package domain.compilador.sintatico;

import domain.compilador.LexicalAnalyzer;
import domain.compilador.Symbol;

import java.util.Stack;

public class SintaxAnalyzer {

    private Stack<Symbol> simbolos = new Stack<>();
    private Stack<Integer> estados = new Stack<>();
    private ControllerOperation controllerOperation;
    private LexicalAnalyzer lexicalAnalyzer;
    private Symbol symbol = new Symbol("", "");

    public SintaxAnalyzer(LexicalAnalyzer lexicalAnalyzer) {
        estados.push(0);
        this.lexicalAnalyzer = lexicalAnalyzer;
        this.controllerOperation = new ControllerOperation();
    }

    public void analyse() {
        Operation operation;
        Symbol firstSymbol = lexicalAnalyzer.analyse();
        symbol = firstSymbol;
        do {
            int topState = estados.peek();
            String token = symbol.getToken();
            operation = controllerOperation.getOperation(topState, token);
            switch (operation.getAction()) {
                case EMPILHAR:
                    empilhar(operation);
                    break;
                case REDUZIR:
                    reduzir(operation);
                    break;
                case ACEITAR:
                    aceitar();
                    break;
                case ERRO:
                    throw new IllegalArgumentException("Erro");
            }


        } while (!operation.isAccepted());

    }

    private void aceitar() {
        System.out.printf("A cadeia foi aceita com sucesso !");
    }

    private void reduzir(Operation operation) {
        Sentences production = Sentences.of(operation.getValor());
        for (int i = 0; i < production.getSize(); i++) {
            this.estados.pop();
            this.simbolos.pop();
        }
        int state = controllerOperation.getStateNonTerminal(this.estados.peek(), production.getGenerator());
        this.simbolos.push(new Symbol(null, production.getGenerator()));
        this.estados.push(state);

        System.out.println(production);
    }

    private void empilhar(Operation operation) {
        this.simbolos.push(this.symbol);
        this.estados.push(operation.getValor());
        this.symbol = lexicalAnalyzer.analyse();
    }
}
