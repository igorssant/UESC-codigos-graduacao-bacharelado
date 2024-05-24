package com.analisadorDeEscopos.utils.escritaEmArquivo;

import java.io.FileWriter;
import java.io.IOException;

public class EscritaEmArquivoDeSaida {
    public static void escrevarLinhaEmArquivo(String linha, FileWriter bufferEscrita) throws IOException {
        bufferEscrita.write(linha);
    }
}
