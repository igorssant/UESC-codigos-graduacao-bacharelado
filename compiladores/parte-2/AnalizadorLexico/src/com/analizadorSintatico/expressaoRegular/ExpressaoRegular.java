package com.analizadorSintatico.expressaoRegular;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ExpressaoRegular {
    public static final ArrayList<Pattern> listaDeRegex = new ArrayList<>(23);

    static {
        /* TIPOS NUMERICOS */
        listaDeRegex.add(Pattern.compile("[0-9]"));
        listaDeRegex.add(Pattern.compile("[.]"));

        /* TIPO STRING */
        listaDeRegex.add(Pattern.compile("\""));

        /* TIPOS DATA */
        listaDeRegex.add(Pattern.compile("/"));
        listaDeRegex.add(Pattern.compile("_"));

        /* TIPO ALFANUMERICO */
        listaDeRegex.add(Pattern.compile("[a-z]"));
        listaDeRegex.add(Pattern.compile("[A-Z]"));

        /* TIPO COMENTARIO */
        listaDeRegex.add(Pattern.compile("#"));

        /* TIPOS DE SINAIS MATEMETICOS */
        listaDeRegex.add(Pattern.compile("<"));
        listaDeRegex.add(Pattern.compile(">"));
        listaDeRegex.add(Pattern.compile("-"));
        listaDeRegex.add(Pattern.compile("[+]"));
        listaDeRegex.add(Pattern.compile("[*]"));
        listaDeRegex.add(Pattern.compile("%"));

        /* TIPOS DE SINAIS LOGICOS */
        listaDeRegex.add(Pattern.compile("="));
        listaDeRegex.add(Pattern.compile("[|]"));
        listaDeRegex.add(Pattern.compile("&"));
        listaDeRegex.add(Pattern.compile("~"));

        /* TIPOS DE TERMINACAO */
        listaDeRegex.add(Pattern.compile(";"));
        listaDeRegex.add(Pattern.compile("\n"));
        listaDeRegex.add(Pattern.compile(" "));

        /* TIPOS DE USO GERAL */
        listaDeRegex.add(Pattern.compile("[(]"));
        listaDeRegex.add(Pattern.compile("[)]"));
    }
}
