package domain.compilador;

import domain.compilador.impl.LexicalAnalyserImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Inicializador da classe de arquivo
 */
public class Main {

	public static void main(String[] args) {
		LexicalAnalyser lexicalAnalyser = new LexicalAnalyserImpl();

		Scanner in = new Scanner(System.in);

		System.out.println("Informe o caminho do arquivo de texto a ser lido:\n");
		String nome = in.nextLine();
		try {
			FileReader file = new FileReader(nome);
			BufferedReader buffer = new BufferedReader(file);
			lexicalAnalyser.analyse(buffer);

		} catch (FileNotFoundException e) {
			in.close();
			throw new RuntimeException(String.format("O arquivo %s não foi encontrado", nome));
		}
		in.close();
	}
}
