package com.analisadorDeEscopos.expressaoRegular;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ExpressaoRegular {
    public static final ArrayList<Pattern> listaDeRegex;

    static {
        listaDeRegex = new ArrayList<>(14);

        listaDeRegex.add(Pattern.compile("-"));     /* 00 */
        listaDeRegex.add(Pattern.compile("[+]"));   /* 01 */
        listaDeRegex.add(Pattern.compile("="));     /* 02 */
        listaDeRegex.add(Pattern.compile("[.]"));   /* 03 */
        listaDeRegex.add(Pattern.compile("[0-9]")); /* 04 */
        listaDeRegex.add(Pattern.compile("\""));    /* 05 */
        listaDeRegex.add(Pattern.compile("[*]"));   /* 06 */
        listaDeRegex.add(Pattern.compile("_"));     /* 07 */

        /* ABAIXO USADO SOMENTE EM CADEIAS ENCONTRADA DENTRO DE ASPAS DUPLAS  */
        listaDeRegex.add(                                 /* 08 */
            Pattern.compile("[a-z]|[A-Z]|[*]|_|[0-9]|\\s|-|[|]|&|~|<|>|%|;|:|[(]|[)]|#|[$]|\\^")
        );

        /* ABAIXO USADO SOMENTE PARA PALAVRAS RESERVADAS */
        listaDeRegex.add(Pattern.compile("[A-Z]")); /* 09 */

        /* ABAIXO USAO SOMENTE PARA IDENTIFICADOR DE BLOCO */
        listaDeRegex.add(Pattern.compile("[a-z]")); /* 10 */

        /* REGEX PARA ESPAÇOS EM BRANCO */
        listaDeRegex.add(Pattern.compile("\\s"));   /* 11 */

        /* REGEX PARA FINAL DE LINHA */
        listaDeRegex.add(Pattern.compile("\n"));    /* 12 */

        /* END OF FILE */
        listaDeRegex.add(Pattern.compile("\\uFFFF"));/* 13 */
    }
}
