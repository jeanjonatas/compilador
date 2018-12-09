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
    private int temporaryCounter = 0;

    public CodeGenerator() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        writer = new FileWriter("C:\\compilador\\main.txt", true);
        buffWrite = new BufferedWriter(writer);
        printWriter = new PrintWriter(writer);
        createHeader();
    }

    public void writeLine() throws IOException {
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

    public void addTempory() {
        this.temporaryCounter++;
    }

    private void createHeader() {
        String header = "#include<stdio.h>\r\n";
        header += "typedef char literal[256];\r\n";
        header += "void main(void)\r\n";
        header += "{\r\n";
        header += "/*----Variaveis temporarias----*/\r\n";


        while (temporaryCounter > -1) {
            header += String.format("int T%s;\r\n", temporaryCounter);
            temporaryCounter--;
        }
        header += "/*-----------------------------*/\r\n";

        body += header;


    }

}
