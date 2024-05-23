package com.analisadorDeEscopos.utils.escritaEmArquivo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import static com.analisadorDeEscopos.expressaoRegular.ExpressaoRegular.listaDeRegex;

public class EscritaEmArquivoTranspilado {
    public static void escreverTokenEmArquivo(String nomeToken, String valorToken, FileWriter bufferEscrita) throws IOException {
        Matcher matcher = null;

        /*
         * verifica se o valor do token é `\n`
         * se for, apenas escreva `\n` no arquivo
         */
        if((matcher = listaDeRegex.get(12).matcher(valorToken)).matches()) {
            bufferEscrita.write(valorToken);
        } else if(valorToken.isEmpty()) {                   /* o token não possui valor */
            bufferEscrita.write("<" + nomeToken + ">");
        } else {
            bufferEscrita.write("<" + nomeToken + ", " + valorToken + ">");
        }
    }
}
