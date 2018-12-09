package domain.compilador.semantic;

import domain.compilador.Symbol;

import java.util.List;
import java.util.stream.Stream;

public enum SemanticRules implements Semantic {

    RULE_5(5) {
        @Override
        public String executar(Symbol simbolo, List<Symbol> symbolsProduced) {
            return "\n\n\n";
        }
    },
    RULE_6(6) {
        @Override
        public String executar(Symbol simbolo, List<Symbol> symbolsProduced) {
            Symbol TIPO = symbolsProduced.get(1);
            Symbol ID = symbolsProduced.get(2);
            ID.setTipo(TIPO.getTipo());
            return String.format("%s %s;\r\n", TIPO.getTipo(), ID.getLexema());
        }
    },
    RULE_7(7) {
        @Override
        public String executar(Symbol simbolo, List<Symbol> symbolsProduced) {
            Symbol TIPO = symbolsProduced.get(1);
            Symbol INT = symbolsProduced.get(0);
            TIPO.setTipo(INT.getTipo());
            return "";
        }
    },
    RULE_8(8) {
        @Override
        public String executar(Symbol simbolo, List<Symbol> symbolsProduced) {
            Symbol TIPO = symbolsProduced.get(1);
            Symbol REAL = symbolsProduced.get(0);
            TIPO.setTipo(REAL.getTipo());
            return "";
        }
    },
    RULE_9(9) {
        @Override
        public String executar(Symbol simbolo, List<Symbol> symbolsProduced) {
            Symbol TIPO = symbolsProduced.get(1);
            Symbol LIT = symbolsProduced.get(0);
            TIPO.setTipo(LIT.getTipo());
            return "";
        }
    },
    RULE_11(11) {
        @Override
        public String executar(Symbol simbolo, List<Symbol> symbolsProduced) {
            Symbol TIPO = symbolsProduced.get(1);
            Symbol LIT = symbolsProduced.get(0);
            TIPO.setTipo(LIT.getTipo());
            return "";
        }
    },
    RULE_12(12) {
        @Override
        public String executar(Symbol simbolo, List<Symbol> symbolsProduced) {
            Symbol TIPO = symbolsProduced.get(1);
            Symbol LIT = symbolsProduced.get(0);
            TIPO.setTipo(LIT.getTipo());
            return "";
        }
    },
    RULE_13(13) {
        @Override
        public String executar(Symbol simbolo, List<Symbol> symbolsProduced) {
            Symbol ARG = symbolsProduced.get(0);
            Symbol LIT = symbolsProduced.get(1);
            ARG.setTipo("lit");
            ARG.setLexema(LIT.getLexema());
            return "";
        }
    };


    private int rule;

    SemanticRules(int rule) {
        this.rule = rule;
    }

    public int getRule() {
        return rule;
    }

    public static boolean existsRule(int index) {
        return Stream.of(values()).anyMatch(rule -> rule.rule == index);
    }

    public static SemanticRules of(int index) {
        return Stream.of(values()).filter(rule -> rule.rule == index).findFirst().orElseThrow(() -> new IllegalArgumentException("Nenhuma regra encontrada"));
    }
}
