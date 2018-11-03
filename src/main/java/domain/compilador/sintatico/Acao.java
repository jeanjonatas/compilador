package domain.compilador.sintatico;

import java.util.stream.Stream;

/**
 * Class the define the action
 */
public enum Acao {

    REDUZIR("R", 1),
    EMPILHAR("S", 1),
    ACEITAR("AC", 2),
    ERRO("ET", 2);

    private String sigla;
    private int reduxString;

    Acao(String sigla, int reduxString) {
        this.sigla = sigla;
        this.reduxString = reduxString;
    }

    public String getSigla() {
        return sigla;
    }

    public int getReduxString() {
        return reduxString;
    }

    public static Acao of(String value) {
        return Stream.of(values()).filter(acao -> value.contains(acao.getSigla())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nenhuma ação encontrada"));
    }
}
