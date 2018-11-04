package domain.compilador.sintatico;

import java.util.stream.Stream;

/**
 * Class the define the action
 */
public enum Action {

    REDUZIR("R", 1),
    EMPILHAR("S", 1),
    ACEITAR("AC", 2),
    ERRO("ET", 2);

    private String sigla;
    private int reduxString;

    Action(String sigla, int reduxString) {
        this.sigla = sigla;
        this.reduxString = reduxString;
    }

    public String getSigla() {
        return sigla;
    }

    public int getReduxString() {
        return reduxString;
    }

    public static Action of(String value) {
        return Stream.of(values()).filter(action -> value.contains(action.getSigla())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nenhuma ação encontrada"));
    }
}
