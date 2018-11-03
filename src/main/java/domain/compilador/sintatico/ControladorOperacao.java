package domain.compilador.sintatico;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ControladorOperacao {

    private final String REGEX = ";";

    public Operacao getOperacao(int estado, String simbolo) {
        List<String[]> terminais = getTabelaTerminal();
        String[] colunasSimbolos = terminais.get(0);
        String[] colunaPrincipal = colunasSimbolos[0].split(REGEX);
        int coluna = 0;
        for (int i = 0; i < colunaPrincipal.length; i++) {
            if (colunaPrincipal[i].equals(simbolo)) {
                coluna = i;
                break;
            }
        }


        String[] line = terminais.get(estado + 1);
        String[] linePrincipal = line[0].split(REGEX);
        String operacao = linePrincipal[coluna];

        return new Operacao(operacao);
    }

    public List<String[]> getTabelaTerminal() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("TabelaTerminais.csv").getFile());
        try {
            return new CSVReader(new FileReader(file)).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Não foi possível encontrar o caminho", e);
        }
    }
}
