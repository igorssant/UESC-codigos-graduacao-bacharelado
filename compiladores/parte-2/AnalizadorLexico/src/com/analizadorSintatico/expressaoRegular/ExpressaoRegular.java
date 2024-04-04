package com.analizadorSintatico.expressaoRegular;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ExpressaoRegular {
    public static final ArrayList<Pattern> listaDeRegex = new ArrayList<>(24);

    static {
        /* TIPOS NUMERICOS */
        listaDeRegex.add(Pattern.compile("^[0-9]$"));  /* 00 */
        listaDeRegex.add(Pattern.compile("^[.]$"));    /* 01 */

        /* TIPO STRING */
        listaDeRegex.add(Pattern.compile("\""));     /* 02 */

        /* TIPOS DATA */
        listaDeRegex.add(Pattern.compile("/"));      /* 03 */
        listaDeRegex.add(Pattern.compile("_"));      /* 04 */

        /* TIPO ALFANUMERICO */
        listaDeRegex.add(Pattern.compile("^[a-z]$"));  /* 05 */
        listaDeRegex.add(Pattern.compile("^[A-Z]$"));  /* 06 */

        /* TIPO COMENTARIO */
        listaDeRegex.add(Pattern.compile("#"));      /* 07 */

        /* TIPOS DE SINAIS MATEMETICOS */
        listaDeRegex.add(Pattern.compile("<"));      /* 08 */
        listaDeRegex.add(Pattern.compile(">"));      /* 09 */
        listaDeRegex.add(Pattern.compile("-"));      /* 10 */
        listaDeRegex.add(Pattern.compile("[+]"));    /* 11 */
        listaDeRegex.add(Pattern.compile("[*]"));    /* 12 */
        listaDeRegex.add(Pattern.compile("%"));      /* 13 */

        /* TIPOS DE SINAIS LOGICOS */
        listaDeRegex.add(Pattern.compile("="));      /* 14 */
        listaDeRegex.add(Pattern.compile("[|]"));    /* 15 */
        listaDeRegex.add(Pattern.compile("&"));      /* 16 */
        listaDeRegex.add(Pattern.compile("~"));      /* 17 */

        /* TIPOS DE TERMINACAO */
        listaDeRegex.add(Pattern.compile(";"));     /* 18 */
        listaDeRegex.add(Pattern.compile(":"));     /* 19 */
        listaDeRegex.add(Pattern.compile("\n"));    /* 20 */
        listaDeRegex.add(Pattern.compile(" "));     /* 21 */

        /* TIPOS DE USO GERAL */
        listaDeRegex.add(Pattern.compile("[(]"));   /* 22 */
        listaDeRegex.add(Pattern.compile("[)]"));   /* 23 */
    }
}
