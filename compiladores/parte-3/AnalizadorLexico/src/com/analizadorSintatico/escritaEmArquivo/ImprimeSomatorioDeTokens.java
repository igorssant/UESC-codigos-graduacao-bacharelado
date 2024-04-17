package com.analizadorSintatico.escritaEmArquivo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class ImprimeSomatorioDeTokens {
    /**
     * Metodo utilitario para imprimir
     * todos os tokens reconhecidos e
     * sua quantidade de ocorrencias
     * @param somatorioDosTokensReconhecidos HashMap (String, Integer)
     */
    public static void imprimirSomatorioDeTokesReconhecidos(HashMap<String, Integer> somatorioDosTokensReconhecidos, File arquivoDoSomatorio) {
        try(PrintWriter bufferDeEscrita = new PrintWriter(arquivoDoSomatorio)) {
            bufferDeEscrita.println("+-----------------------+-----------------------+");
            bufferDeEscrita.printf("|%20s\t|%20s\t|\n", "TOKEN", "USOS");
            bufferDeEscrita.println("+-----------------------+-----------------------+");
            somatorioDosTokensReconhecidos.forEach((token, numeroDeAparicoes) -> {
                if(!token.equals(" ") && !token.equals("\n") && !token.isBlank()) {
                    bufferDeEscrita.printf("|%20s\t|%20s\t|\n", token, numeroDeAparicoes);
                }
            });
            bufferDeEscrita.println("+-----------------------+-----------------------+");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
