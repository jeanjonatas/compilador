package domain.compilador.sintax;

import java.util.stream.Stream;

/**
 * Enum que mapeia as mensagens de erro
 */
public enum ErrorMessage {

    ERROR_0("O codigo fonte tem que iniciar com a palavra inicio", 0),
    ERROR_1("Está faltando um terminador de cadeia", 1),
    ERROR_2("varinicio não foi encontrado na leitura", 2),
    ERROR_3("identificador, leia, escreva, se ou fim não foram encontrados na leitura", 3),
    ERROR_4("identificador ou varfim não foram encontrados na leitura", 4),
    ERROR_5("identificador, leia, escreva, se ou fim não foram encontrados na leitura", 5),
    ERROR_6("varfim ou identificador não foram encontrados na leitura", 6),
    ERROR_7("; (Ponto e vírgula) não foi encontrado na leitura", 7),
    ERROR_8("inteiro, real, literal ou constante númerica não foram encontrados na leitura", 8),
    ERROR_12("varfim ou identificador não foram identificados na leitura", 12),
    ERROR_13("; (Ponto e vírgula) não foi encontrado na leitura", 13),
    ERROR_14("Está faltando um terminador de cadeia", 14),
    ERROR_21("identificador não foi encontrado na leitura", 21),
    ERROR_23("identificador, leia, escreva, se ou fim não foram encontrados na leitura", 23),
    ERROR_24("identificador, literal ou constante númerica não foram encontrados na leitura", 24),
    ERROR_30("atribuição (<-) não foi encontrado na leitura", 30),
    ERROR_31("identificador ou um numero não foi encontrado na leitura", 31),
    ERROR_34("identificador, ) ou ; não fi encontrado na leitura", 34),
    ERROR_36("; (Ponto e vírgula) não foi encontrado na leitura", 36),
    ERROR_39("identificador, leia, escreva, se ou fimse não foram encontrados na leitura", 39),
    ERROR_40("( ou ) não foram encontrados na leitura", 40),
    ERROR_42("operador aritmético (+,-,*,/) não foram encontrados na leitura", 42),
    ERROR_44("( fecha parêntesis não foi encontrado na leitura", 44),
    ERROR_46("entao não foi encontrado na leitura", 46),
    ERROR_47("identificador, leia, escreva, se ou fimse não foram encontrados na leitura", 47);


    public int getCodigo() {
        return codigo;
    }

    private int codigo;

    public String getMessage() {
        return message;
    }

    private String message;

    ErrorMessage(String message, int codigo) {
        this.message = message;
        this.codigo = codigo;
    }

    public static ErrorMessage of(int codigo) {
        return Stream.of(values()).filter(error -> error.getCodigo() == codigo).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nenhum erro mapeado encontrado"));
    }

}
