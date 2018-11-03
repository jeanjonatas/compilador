package domain.compilador.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import domain.compilador.Symbol;
import org.apache.commons.lang3.StringUtils;

import domain.compilador.LexicalAnalyser;
import domain.compilador.Token;

public class LexicalAnalyserImpl implements LexicalAnalyser {

	private int[][] transitionTable() {
		int transitionTable[][] = {
				/*
				 * L D OUTRO ESPACO \n \t fim_arquivo OPM { ( ) > < ;
				 * "   =   E | e  \.   .    }   " -
				 */
				{ 12, 13, 6, 0, 0, 0, 3, 11, 1, 18, 19, 10, 7, 4, 5, 8, -1, -1, -1, -1, -1, -1 }, // estado
																									// 0
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 2, -1, -1 }, // estado
																											// 1
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 2
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 3
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 4
				{ 5, 5, -1, 5, 5, 5, -1, -1, -1, -1, -1, 5, 5, 5, 6, 5, 5, -1, 5, -1, 6, -1 }, // estado
																									// 5
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 6
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 8, -1, -1, -1, 8, -1, -1, -1, -1, -1, 9 }, // estado
																											// 7
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 8
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 9
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 8, -1, -1, -1, -1, -1 }, // estado
																											// 10
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 11
				{ 12, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 12
				{ 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16, 14, -1, -1, -1 }, // estado
																											// 13
				{ -1, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 14
				{ -1, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 15
				{ -1, 15, -1, -1, -1, -1, -1, 17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 16
				{ -1, 15, -1, -1, -1, -1, -1, 17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 17
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 18
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, // estado
																											// 19
		};
		return transitionTable;
	}

	public String analyse(BufferedReader bufferedReader) {
		Map<String, Symbol> reservedWords = getReservedWords();
		long inicio = System.currentTimeMillis();
		char character = 0;
		int estadoAtual = 0;
		int column = 0;
		try {
			int transitionTable[][] = transitionTable();
			int value = 0;
			int controle = 0;
			while (value != -1) {
				StringBuilder buffer = new StringBuilder();

				do {
					value = bufferedReader.read();
					if (value == -1) {
						String key = "eof";
						Symbol token = new Symbol(Token.EOF.getToken(), key);
						System.out.println(token);
						break;
					}
					character = (char) value;
					controle++;

					String symbol = String.valueOf(character);
					column = getColumn(symbol, buffer.toString());
					int proximoEstado = transitionTable[estadoAtual][column];

					if (proximoEstado != -1) {
						if ((symbol.equals("\n") || symbol.equals("\r")) && !buffer.toString().contains("\"")) {
							controle = 0;
						}
						estadoAtual = proximoEstado;
						if (!symbol.equals("\n") && !symbol.equals("\t") && !symbol.equals(" ")
								&& !symbol.equals("\r")) {
							buffer.append(symbol);
						} else if (symbol.equals(" ") && estadoAtual == 5) {
							buffer.append(symbol);
						}
					} else {
						if (reservedWords.containsKey(buffer.toString())) {
							System.out.println(reservedWords.get(buffer.toString()));
							buffer = new StringBuilder();
							buffer.append(symbol);
							column = getColumn(symbol, buffer.toString());
							estadoAtual = transitionTable[0][column];
							if (!Token.isFinalState(estadoAtual)) {
								estadoAtual = 0;
							}
						} else {
							String key = buffer.toString();
							Token tokenEnum = Token.of(estadoAtual);
							Symbol token = new Symbol(tokenEnum.getToken(), key);
							System.out.println(token);
							if (tokenEnum.equals(Token.ID)) {
								reservedWords.put(key, token);
							}
							buffer = new StringBuilder();
							estadoAtual = 0;
						}
					}
				} while (controle != 0);
			}

		} catch (IOException e) {
			mensagemErro(character, estadoAtual, column);
		}
		long fim = System.currentTimeMillis();
		System.out.println("ControladorOperacao de Simbolos");
		System.out.println(reservedWords);
		System.out.println("Tempo total de análise léxica: " + (fim - inicio) / 1000.0 + " segundos");
		return reservedWords.toString();
	}

	private void mensagemErro(char character, int estadoAtual, int column) {
		System.out.println("Erro na leitura do caracter" + character + " na [linha=" + estadoAtual + ", coluna="
				+ column + "]");
	}

	private int getColumn(String symbol, String buffer) {
		if (StringUtils.isAlpha(symbol)) {
			return Token.ID.getColumn();
		}
		if (StringUtils.isNumeric(symbol)) {
			return Token.NUM.getColumn();
		}
		if (symbol.equals(" ")) {
			return Token.ESPACO.getColumn();
		}
		if (symbol.equals("\n") || symbol.equals("\r")) {
			return Token.PULA_LINHA.getColumn();
		}
		if (symbol.equals("\t")) {
			return Token.TAB.getColumn();
		}
		if (symbol.equals("")) {
			return Token.EOF.getColumn();
		}
		if (symbol.equals(";")) {
			return Token.PT_V.getColumn();
		}
		if (symbol.equals("-1")) {
			return Token.EOF.getColumn();
		}
		if (symbol.equals("\"")) {
			return Token.LITERAL.getColumn();
		}
		if (symbol.equals("(")) {
			return Token.AB_P.getColumn();
		}
		if (symbol.equals(")")) {
			return Token.FC_P.getColumn();
		}
		if (symbol.equals(">")) {
			return Token.MAIOR.getColumn();
		}
		if (symbol.equals("<")) {
			return Token.MENOR.getColumn();
		}
		if (symbol.equals("=")) {
			return Token.IGUAL.getColumn();
		}
		if (symbol.equals("E") || symbol.equals("e")) {
			return Token.PRECISAO_DECIMAL.getColumn();
		}
		if (symbol.equals("-")) {
			return Token.ATRIBUICAO.getColumn();
		}
		if (symbol.equals("+") || symbol.equals("-") || symbol.equals("/") || symbol.equals("-")) {
			return Token.OPM.getColumn();
		}
		if (symbol.equals(".")) {
			return Token.PONTO.getColumn();
		}
		if (symbol.equals("{")) {
			return Token.ABRE_CHAVE.getColumn();
		}
		if (symbol.equals("}")) {
			return Token.FECHA_CHAVE.getColumn();
		}if(symbol.equals(" ")){
			return Token.ESPACO.getColumn();
		}else {
			if (buffer.contains("\"")) {
				return Token.ID.getColumn();
			}
			System.out.println(symbol);
			throw new IllegalArgumentException("Um erro foi encontrado" + symbol);
		}
	}

	private Map<String, Symbol> getReservedWords() {
		Map<String, Symbol> symbols = new HashMap<String, Symbol>();
		symbols.put("se", new Symbol("se", "se"));
		symbols.put("fim", new Symbol("fim", "fim"));
		symbols.put("inteiro", new Symbol("inteiro", "inteiro"));
		symbols.put("lit", new Symbol("lit", "lit"));
		symbols.put("leia", new Symbol("leia", "leia"));
		symbols.put("real", new Symbol("real", "real"));
		symbols.put("fimse", new Symbol("fimse", "fimse"));
		symbols.put("entao", new Symbol("entao", "entao"));
		symbols.put("senao", new Symbol("senao", "senao"));
		symbols.put("varfim", new Symbol("varfim", "varfim"));
		symbols.put("inicio", new Symbol("inicio", "inicio"));
		symbols.put("escreva", new Symbol("escreva", "escreva"));
		symbols.put("varinicio", new Symbol("varninicio", "varninicio"));
		return symbols;
	}

}
