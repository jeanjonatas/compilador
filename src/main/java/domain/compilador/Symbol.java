package domain.compilador;

import java.util.Objects;

public class Symbol {

  private String tipo;
  private final String token;
  private final String lexema;

  public Symbol(String token, String lexema) {
    this.token = token;
    this.lexema = lexema;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getToken() {
    return token;
  }

  public String getLexema() {
    return lexema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Symbol symbol = (Symbol) o;
    return Objects.equals(token, symbol.token) &&
        Objects.equals(lexema, symbol.lexema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, lexema);
  }

  @Override
  public String toString() {
    return String.format(
        "Symbol (token=%s, lexema=%s)", this.token, this.lexema);
  }
}
