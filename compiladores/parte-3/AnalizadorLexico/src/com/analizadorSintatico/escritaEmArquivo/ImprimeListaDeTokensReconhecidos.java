package com.analizadorSintatico.escritaEmArquivo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ImprimeListaDeTokensReconhecidos {
    /**
     * Metodo utilitario para imprimir
     * todos os tokens, bem como sua
     * linha, sua coluna e seu lexema (se houver)
     * @param listaDeTokensReconhecidos ArrayList(ArrayList(String))
     */
    public static void imprimirListaDeTokensReconhecidos(ArrayList<ArrayList<String>> listaDeTokensReconhecidos, File arquivoDeIndice) {
        try(PrintWriter bufferDeEscrita = new PrintWriter(arquivoDeIndice)) {
            bufferDeEscrita.println("+-----------------------+-----------------------+" +
                    "-----------------------+-----------------------+");
            bufferDeEscrita.printf("|%20s\t|%20s\t|%20s\t|%20s\t|\n", "LINHA", "COLUNA", "TOKEN", "USOS");
            bufferDeEscrita.println("+-----------------------+-----------------------+" +
                    "-----------------------+-----------------------+");
            listaDeTokensReconhecidos.forEach((linha) -> {
                if(!linha.get(3).equals("\n")) {
                    bufferDeEscrita.printf("|%20s\t|%20s\t|%20s\t|%20s\t|\n",
                            linha.get(0),
                            linha.get(1),
                            linha.get(2),
                            linha.get(3)
                    );
                }
            });
            bufferDeEscrita.println("+-----------------------+-----------------------+" +
                    "-----------------------+-----------------------+");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
