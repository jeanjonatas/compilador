package domain.compilador.semantic;

import domain.compilador.Symbol;

import java.util.List;

/**
 * Interface que identifica se um determinada ação vai ser executada ou não
 */
public interface Semantic {

    String executar(Symbol simbolo, List<Symbol> symbolsProduced);
}
