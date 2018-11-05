package domain.compilador.sintax;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ControllerOperationTest {

    ControllerOperation controllerOperation;

    @Test
    public void getOperacao() {

        controllerOperation = new ControllerOperation();

        Operation operation = controllerOperation.getOperation(0, "inicio");
        assertThat(operation.getAction()).isEqualTo(Action.EMPILHAR);
        assertThat(operation.getValor()).isEqualTo(2);
    }

    @Test
    public void getOperacao_Reduce() {

        controllerOperation = new ControllerOperation();

        Operation operation = controllerOperation.getOperation(12, "id");
        assertThat(operation.getAction()).isEqualTo(Action.REDUZIR);
        assertThat(operation.getValor()).isEqualTo(6);
    }
}