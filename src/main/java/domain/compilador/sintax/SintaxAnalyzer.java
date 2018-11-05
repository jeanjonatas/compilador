package domain.compilador.sintax;

import domain.compilador.LexicalAnalyzer;
import domain.compilador.Symbol;

import java.util.Stack;

public class SintaxAnalyzer {

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
        long inicio = System.currentTimeMillis();
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
        throw new IllegalArgumentException(error.getMessage());
    }

    private void errorDefault() {
        String msg = "Um erro ocorreu na analise da linha:%s e coluna: %s";
        throw new IllegalArgumentException(String.format(msg, lexicalAnalyzer.getLine(), lexicalAnalyzer.getColumn()));
    }

    private void aceitar() {
        System.out.println("A cadeia foi aceita com sucesso !");
    }

    private void reduzir(Operation operation) {
        Sentences production = Sentences.of(operation.getValor());
        for (int i = 0; i < production.getSize(); i++) {
            this.estados.pop();
        }
        int state = controllerOperation.getStateNonTerminal(this.estados.peek(), production.getGenerator());
        this.estados.push(state);

        System.out.println(production);
    }

    private void empilhar(Operation operation) {
        this.estados.push(operation.getValor());
        this.symbol = lexicalAnalyzer.analyse();
    }
}
