package domain.compilador.sintatico;

import domain.compilador.Symbol;

import java.util.Stack;

public class AnalisadorSintatico {

    private Stack<Symbol> simbolos = new Stack<>();
    private Stack<Integer> estados = new Stack<>();
    private ControladorOperacao controladorOperacao;
    private Symbol symbol;

    public AnalisadorSintatico() {
        estados.push(0);
        this.controladorOperacao = new ControladorOperacao();
    }

    public void analyse() {
        Operacao operacao;
        do {
            operacao = controladorOperacao.getOperacao(estados.peek(), symbol.getLexema());
        } while (!operacao.isAceita());

    }
}
