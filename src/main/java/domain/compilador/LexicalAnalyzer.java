package domain.compilador;

import java.io.BufferedReader;

/**
 * Interface que será responsável por analisar uma cadeia de strings e entrega os tokens
 * determinados
 *
 * @author Jean Borba
 */
public interface LexicalAnalyzer {

    /**
     * Analisa o codigo e devolve os tokens encontrados
     */
    Symbol analyse();

    int getLine();

    int getColumn();

}
