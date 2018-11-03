package domain.compilador;

import java.io.BufferedReader;

/**
 * Interface que será responsável por analisar uma cadeia de strings e entrega os tokens
 * determinados
 *
 * @author Jean Borba
 */
public interface LexicalAnalyser {

  /**
   * Analisa o codigo e devolve os tokens encontrados
   */
	String analyse(BufferedReader bufferedReader);
}
