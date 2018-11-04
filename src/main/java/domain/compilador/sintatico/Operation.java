package domain.compilador.sintatico;

import org.apache.commons.lang3.math.NumberUtils;

public class Operation {

    private Action action;
    private int valor;

    public Operation(String value) {
        this.action = Action.of(value);
        this.valor = NumberUtils.toInt(value.substring(action.getReduxString()));
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Action getAction() {
        return action;
    }

    public boolean isAccepted() {
        return Action.ACEITAR.equals(this.action);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "action=" + action +
                ", valor=" + valor +
                '}';
    }
}
