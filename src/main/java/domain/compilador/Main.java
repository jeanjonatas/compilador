package domain.compilador;

import domain.compilador.lexical.LexicalAnalyzerImpl;
import domain.compilador.sintax.SintaxAnalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Inicializador da classe de arquivo
 */
public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Informe o caminho do arquivo de texto a ser lido:\n");
        String nome = in.nextLine();
        try {
            FileReader file = new FileReader(nome);
            BufferedReader buffer = new BufferedReader(file);
            LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzerImpl(buffer);
            SintaxAnalyzer sintaxAnalyzer = new SintaxAnalyzer(lexicalAnalyzer);
            sintaxAnalyzer.analyse();
        } catch (FileNotFoundException e) {
            in.close();
            throw new RuntimeException(String.format("O arquivo %s não foi encontrado", nome));
        } catch (IOException e) {
            throw new RuntimeException("Arquivo de saida não foi encontrado", e);
        }
        in.close();
    }
}
