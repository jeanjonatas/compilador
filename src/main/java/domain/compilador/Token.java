package domain.compilador;

import java.util.stream.Stream;

public enum Token{

  ID(0, "id", 12),
  NUM(1, "num", 13),
  OUTRO(2, "", -6),
  ESPACO(3, "", 0),
  PULA_LINHA(4, "", 0),
  TAB(5, "", 0),
  EOF(6, "EOF", 3),
  OPM(7, "opm", 11),
  ABRE_CHAVE(8, "comentario", 1),
  AB_P(9, "AB_P", 18),
  FC_P(10, "FC_P", 19),
  MAIOR(11, "opr", 10),
  MENOR(12, "opr",7),
  PT_V(13, "PT_V",4),
  LITERAL(14, "literal", 6),
  IGUAL(15, "opr", 8),
  PRECISAO_DECIMAL(16, "num", 16),
  ESCAPE(17, "num", 13),
  ATRIBUICAO(21, "atrib", 9),
  PONTO(18, "", 14),
  FECHA_CHAVE(19, "comentario", 0),
  DECIMAL_NUM(1, "num", 15);
  
  private int column;
  private String token;
  private int estadoFinal;

  Token(int column, String token, int estadoFinal) {
    this.token = token;
    this.column = column;
    this.estadoFinal = estadoFinal;
  }

  public String getToken() {
    return token;
  }

  public int getEstadoFinal() {
    return estadoFinal;
  }

  public int getColumn() {
    return column;
  }
  
  public static boolean isFinalState(int state){
	  return Stream.of(values()).anyMatch(i->i.getEstadoFinal() == state);
  }

  public static Token of(int state) {
    return Stream.of(values()).filter(i -> i.getEstadoFinal() == state).findFirst().get();
  }
}
