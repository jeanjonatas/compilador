package domain.compilador.sintax;

import java.util.Arrays;

public enum Sentences {

    SENTENCE_1("P'", "P", 1),
    SENTENCE_2("P", "inicio V A", 3),
    SENTENCE_3("V", "varinicio LV", 2),
    SENTENCE_4("LV", "D LV", 2),
    SENTENCE_5("LV", "varfim ;", 2),
    SENTENCE_6("D", "id TIPO ;", 3),
    SENTENCE_7("TIPO", "inteiro", 1),
    SENTENCE_8("TIPO", "real", 1),
    SENTENCE_9("TIPO", "lit", 1),
    SENTENCE_10("A", "ES A", 2),
    SENTENCE_11("ES", "leia id ;", 3),
    SENTENCE_12("ES", "escreva ARG ;", 3),
    SENTENCE_13("ARG", "literal", 1),
    SENTENCE_14("ARG", "num", 1),
    SENTENCE_15("ARG", "id", 1),
    SENTENCE_16("A", "CMD A", 2),
    SENTENCE_17("CMD", "id rcb LD ;", 4),
    SENTENCE_18("LD", "OPRD opm OPRD", 3),
    SENTENCE_19("LD", "OPRD", 1),
    SENTENCE_20("OPRD", "id", 1),
    SENTENCE_21("OPRD", "num", 1),
    SENTENCE_22("A", "COND A", 2),
    SENTENCE_23("COND", "CABECALHO CORPO", 2),
    SENTENCE_24("CABECALHO", "se ( EXP_R ) entao", 5),
    SENTENCE_25("EXP_R", "OPRD opr OPRD", 3),
    SENTENCE_26("CORPO", "ES CORPO", 2),
    SENTENCE_27("CORPO", "CMD CORPO", 2),
    SENTENCE_28("CORPO", "COND CORPO", 2),
    SENTENCE_29("CORPO", "fimse", 1),
    SENTENCE_30("A", "fim", 1);

    private int size;
    private String generator;
    private String generated;

    public int getSize() {
        return size;
    }

    public String getGenerator() {
        return generator;
    }

    public String getGenerated() {
        return generated;
    }

    Sentences(String generator, String generated, int size) {
        this.size = size;
        this.generated = generated;
        this.generator = generator;
    }

    public static Sentences of(int position) {
        return Arrays.asList(values()).get(position - 1);
    }

    @Override
    public String toString() {
        return String.format("%s  %s --> %s", this.ordinal(), this.generator, this.generated);
    }
}
