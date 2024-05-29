package com.analisadorDeEscopos.utils.escritaEmArquivo;

import java.io.BufferedWriter;
import java.io.IOException;

public class EscritaEmArquivoDeSaida {
    public static void escreverLinhaEmArquivo(String linha, BufferedWriter bufferEscrita) throws IOException {
        bufferEscrita.write(linha);
        bufferEscrita.newLine();
    }
}
