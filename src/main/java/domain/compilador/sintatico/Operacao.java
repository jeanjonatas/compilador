package domain.compilador.sintatico;

import org.apache.commons.lang3.math.NumberUtils;

public class Operacao {

    private Acao acao;
    private int valor;

    public Operacao(String value) {
        this.acao = Acao.of(value);
        this.valor = NumberUtils.toInt(value.substring(acao.getReduxString()));
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Acao getAcao() {
        return acao;
    }

    public boolean isAceita() {
        return Acao.ACEITAR.equals(this.acao);
    }

    @Override
    public String toString() {
        return "Operacao{" +
                "acao=" + acao +
                ", valor=" + valor +
                '}';
    }
}
