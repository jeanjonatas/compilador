package domain.compilador.semantic;

import domain.compilador.Symbol;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.stream.Stream;

public enum SemanticRules implements Semantic {

    RULE_5(5) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            return "\n\n\n";
        }
    },
    RULE_6(6) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol TIPO = symbolsProduced.get(1);
            Symbol ID = symbolsProduced.get(2);
            ID.setTipo(TIPO.getTipo());
            String tipo = ID.getTipo();
            tipo = getTipo(ID, tipo);
            return String.format("%s %s;\r\n", tipo, ID.getLexema());
        }
    },
    RULE_7(7) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol TIPO = symbolsProduced.get(1);
            Symbol INT = symbolsProduced.get(0);
            TIPO.setTipo(INT.getTipo());
            return "";
        }
    },
    RULE_8(8) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol TIPO = symbolsProduced.get(1);
            Symbol REAL = symbolsProduced.get(0);
            TIPO.setTipo(REAL.getTipo());
            return "";
        }
    },
    RULE_9(9) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol TIPO = symbolsProduced.get(1);
            Symbol LIT = symbolsProduced.get(0);
            TIPO.setTipo(LIT.getTipo());
            return "";
        }
    },
    RULE_11(11) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol ID = symbolsProduced.get(1);
            if (ID.getTipo().equals("int")) {
                return String.format("scanf(\"%%d\", &%s);\r\n", ID.getLexema());
            } else if (ID.getTipo().equals("real")) {
                return String.format("scanf(\"%%lf\", &%s);\r\n", ID.getLexema());
            } else if (ID.getTipo().equals("literal")) {
                return String.format("scanf(\"%%s\", &%s);\r\n", ID.getLexema());
            } else {
                throw new IllegalArgumentException("“Erro: Variável não declarada\r\n");
            }
        }
    },
    RULE_12(12) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol ID = symbolsProduced.get(1);
            if (ID.getTipo().equals("int")) {
                return String.format("printf(\"%%d\", %s);\r\n", ID.getLexema());
            } else if (ID.getTipo().equals("real")) {
                return String.format("printf(\"%%lf\", %s);\r\n", ID.getLexema());
            } else if (ID.getTipo().equals("lit")) {
                return String.format("printf(%s);\r\n", ID.getLexema());
            } else if (ID.getTipo().equals("literal")) {
                return String.format("printf(\"%%s\", %s);\r\n", ID.getLexema());
            } else {
                throw new IllegalArgumentException("“Erro: Variável não declarada\r\n");
            }
        }
    },
    RULE_13(13) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol LIT = symbolsProduced.get(0);
            Symbol ARG = symbolsProduced.get(1);
            ARG.setTipo("lit");
            ARG.setLexema(LIT.getLexema());
            return "";
        }
    },
    RULE_14(14) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol NUM = symbolsProduced.get(0);
            Symbol ARG = symbolsProduced.get(1);
            ARG.setTipo("num");
            ARG.setLexema(NUM.getLexema());
            return "";
        }
    },
    RULE_15(15) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol ID = symbolsProduced.get(0);
            Validate.notNull(ID.getTipo(), "Variável não foi declarada");
            Symbol ARG = symbolsProduced.get(1);
            ARG.setTipo(ID.getTipo());
            ARG.setLexema(ID.getLexema());
            return "";
        }
    },
    RULE_17(17) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol ID = symbolsProduced.get(3);
            Symbol LD = symbolsProduced.get(1);
            Symbol rcb = symbolsProduced.get(2);
            Validate.notNull(ID.getTipo(), "Variável não foi declarada");
            if (ID.getTipo().equals(LD.getTipo())) {
                return String.format("%s%s%s;\r\n", ID.getLexema(), rcb.getTipo(), LD.getLexema());
            } else {
                throw new IllegalArgumentException("Erro: Operandos com tipos incompatíveis");
            }
        }
    },
    RULE_18(18) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol LD = symbolsProduced.get(3);
            Symbol OPRD1 = symbolsProduced.get(2);
            Symbol OPM = symbolsProduced.get(1);
            Symbol OPRD2 = symbolsProduced.get(0);
            if (!OPRD1.getTipo().equals("literal") && !OPRD2.getTipo().equals("literal")) {
                String TX = String.format("T%s", SemanticAnalyser.getAndAddCounter());
                LD.setLexema(TX);
                LD.setTipo(OPRD1.getTipo());
                return String.format("%s=%s%s%s;\r\n", TX, OPRD1.getLexema(), OPM.getTipo(), OPRD2.getLexema());
            } else {
                throw new IllegalArgumentException("Erro: Operandos com tipos diferentes.");
            }
        }
    },
    RULE_19(19) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol LD = symbolsProduced.get(1);
            Symbol OPRD = symbolsProduced.get(0);
            LD.setTipo(OPRD.getTipo());
            LD.setLexema(OPRD.getLexema());
            return "";
        }
    },
    RULE_20(20) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol ID = symbolsProduced.get(0);
            Validate.notNull(ID.getTipo(), "Erro: Variável não declarada.");
            Symbol OPRD = symbolsProduced.get(1);
            OPRD.setTipo(ID.getTipo());
            OPRD.setLexema(ID.getLexema());
            return "";
        }
    },
    RULE_21(21) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol NUM = symbolsProduced.get(0);
            Symbol OPRD = symbolsProduced.get(1);
            OPRD.setTipo(NUM.getTipo());
            OPRD.setLexema(NUM.getLexema());
            return "";
        }
    }, RULE_23(23) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            return "}\r\n";
        }
    },
    RULE_24(24) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol EXP = symbolsProduced.get(2);
            return String.format("if(%s){\r\n", EXP.getLexema());
        }
    },
    RULE_25(25) {
        @Override
        public String executar(List<Symbol> symbolsProduced) {
            Symbol EXP = symbolsProduced.get(3);
            Symbol OPRD1 = symbolsProduced.get(2);
            Symbol OPR = symbolsProduced.get(1);
            Symbol OPRD2 = symbolsProduced.get(0);
            if ((OPRD1.getTipo().equals("literal") && OPRD2.getTipo().equals("literal")) || ((OPRD1.getTipo().equals("real") || OPRD1.getTipo().equals("int")) && (OPRD2.getTipo().equals("real") || OPRD2.getTipo().equals("int")))) {
                String TX = String.format("T%s", SemanticAnalyser.getAndAddCounter());
                EXP.setLexema(TX);
                return String.format("%s=%s%s%s;\r\n", TX, OPRD1.getLexema(), OPR.getTipo(), OPRD2.getLexema());
            } else {
                throw new IllegalArgumentException("Erro: Operandos com tipos incompatíveis.");
            }
        }
    };

    private static String getTipo(Symbol ID, String tipo) {
        if (ID.getTipo().equals("real")) {
            tipo = "double";
        }
        return tipo;
    }

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
