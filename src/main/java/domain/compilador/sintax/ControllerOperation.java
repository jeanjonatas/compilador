package domain.compilador.sintax;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ControllerOperation {

    private final String REGEX = ";";
    private final String TABELA_TERMINAIS = "TabelaTerminais.csv";
    private final String TABELA_NAO_TERMINAIS = "TabelaNaoTerminais.csv";

    public Operation getOperation(int estado, String simbolo) {
        List<String[]> terminais = getTable(TABELA_TERMINAIS);
        String[] colunasSimbolos = terminais.get(0);
        String[] colunaPrincipal = colunasSimbolos[0].split(REGEX);
        int coluna = 0;
        for (int i = 0; i < colunaPrincipal.length; i++) {
            String mainColunm = colunaPrincipal[i];
            if (mainColunm.equals(simbolo)) {
                coluna = i;
                break;
            }
        }

        String[] line = terminais.get(estado + 1);
        String[] linePrincipal = line[0].split(REGEX);
        String operacao = linePrincipal[coluna];

        return new Operation(operacao);
    }


    public int getStateNonTerminal(int estado, String simbolo) {
        List<String[]> terminais = getTable(TABELA_NAO_TERMINAIS);
        String[] colunasSimbolos = terminais.get(0);
        String[] colunaPrincipal = colunasSimbolos[0].split(REGEX);
        int coluna = 0;
        for (int i = 0; i < colunaPrincipal.length; i++) {
            String mainColunm = colunaPrincipal[i];
            if (mainColunm.equals(simbolo)) {
                coluna = i;
                break;
            }
        }

        String[] line = terminais.get(estado + 1);
        String[] linePrincipal = line[0].split(REGEX);
        String operacao = linePrincipal[coluna];

        return NumberUtils.toInt(operacao);
}


    public List<String[]> getTable(String table) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(table).getFile());
        try {
            return new CSVReader(new FileReader(file)).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Não foi possível encontrar o caminho", e);
        }
    }
}
