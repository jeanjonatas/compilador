package domain.compilador.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Gerator de código em C
 */
public class CodeGenerator {

    private FileWriter writer;
    private String body = "";
    private PrintWriter printWriter;
    private BufferedWriter buffWrite;

    public CodeGenerator() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        writer = new FileWriter("C:\\compilador\\main.txt", true);
        buffWrite = new BufferedWriter(writer);
        printWriter = new PrintWriter(writer);
    }

    public void writeLine(int temporaryCounter) throws IOException {
        createHeader(temporaryCounter);
        writer = new FileWriter("C:/compilador/main.txt");
        buffWrite = new BufferedWriter(writer);
        printWriter = new PrintWriter(writer);
        buffWrite.append(body);
        buffWrite.close();
        System.out.println("Arquivo salvo com sucesso!");
    }

    public void addCode(String line) {
        body += line + "\n";
    }

    private void createHeader(int temporaryCounter) {
        String header = "#include<stdio.h>\r\n";
        header += "typedef char literal[256];\r\n";
        header += "void main(void)\r\n";
        header += "{\r\n";
        header += "/*----Variaveis temporarias----*/\r\n";


        int tX = 0;
        while (tX <= temporaryCounter) {
            header += String.format("int T%s;\r\n", tX);
            tX++;
        }
        header += "/*-----------------------------*/\r\n";

        String temporary = header;
        temporary += body;
        body = temporary;
        body += "}\r\n";
    }

}
