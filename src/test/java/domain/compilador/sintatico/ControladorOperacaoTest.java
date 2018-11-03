package domain.compilador.sintatico;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ControladorOperacaoTest {

    ControladorOperacao controladorOperacao;

    @Test
    public void getOperacao() {

        controladorOperacao = new ControladorOperacao();

        Operacao operacao = controladorOperacao.getOperacao(0, "inicio");
        assertThat(operacao.getAcao()).isEqualTo(Acao.EMPILHAR);
        assertThat(operacao.getValor()).isEqualTo(2);
    }

    @Test
    public void getOperacao_Reduce() {

        controladorOperacao = new ControladorOperacao();

        Operacao operacao = controladorOperacao.getOperacao(12, "id");
        assertThat(operacao.getAcao()).isEqualTo(Acao.REDUZIR);
        assertThat(operacao.getValor()).isEqualTo(6);
    }
}