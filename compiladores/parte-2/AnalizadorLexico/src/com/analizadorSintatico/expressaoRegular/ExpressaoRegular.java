package com.analizadorSintatico.expressaoRegular;

import java.util.HashMap;
import java.util.regex.Pattern;

public class ExpressaoRegular {
    public static final HashMap<String, Pattern> mapaRegex = new HashMap<>();

    static {
        /* TIPOS NUMERICOS */
        mapaRegex.put("TK_NUMBER", Pattern.compile("[0-9]"));
        mapaRegex.put("TK_FLOAT_POINT", Pattern.compile("[.]"));

        /* TIPO STRING */
        mapaRegex.put("TK_ASPAS_DUPLAS", Pattern.compile("\""));

        /* TIPOS DATA */
        mapaRegex.put("TK_DATA_BARRA", Pattern.compile("/"));
        mapaRegex.put("TK_DATA_UNDERSCORE", Pattern.compile("_"));

        /* TIPO ALFANUMERICO */
        mapaRegex.put("TK_LOWER_CASE", Pattern.compile("[a-z]"));
        mapaRegex.put("TK_UPPER_CASE", Pattern.compile("[A-Z]"));

        /* TIPO COMENTARIO */
        mapaRegex.put("TK_COMENTARIO_MONOLINHA", Pattern.compile("#"));

        /* TIPOS DE SINAIS MATEMETICOS */
        mapaRegex.put("TK_MENOR_QUE", Pattern.compile("<"));
        mapaRegex.put("TK_MAIOR_QUE", Pattern.compile(">"));
        mapaRegex.put("TK_SUBTRACAO", Pattern.compile("-"));
        mapaRegex.put("TK_SOMA", Pattern.compile("[+]"));
        mapaRegex.put("TK_MULTIPLICACAO", Pattern.compile("[*]"));
        mapaRegex.put("TK_DIVISAO", Pattern.compile("%"));

        /* TIPOS DE SINAIS LOGICOS */
        mapaRegex.put("TK_IGUAL_A", Pattern.compile("="));
        mapaRegex.put("TK_OR", Pattern.compile("[|]"));
        mapaRegex.put("TK_AND", Pattern.compile("&"));
        mapaRegex.put("TK_NOT", Pattern.compile("~"));

        /* TIPOS DE TERMINACAO */
        mapaRegex.put("TK_DOIS_PONTOS", Pattern.compile(";"));
        mapaRegex.put("TK_NOVA_LINHA", Pattern.compile("\n"));
        mapaRegex.put("TK_ESPACO_BRANCO", Pattern.compile(" "));

        /* TIPOS DE USO GERAL */
        mapaRegex.put("TK_ABRE_PARENTESE", Pattern.compile("[(]"));
        mapaRegex.put("TK_FECHA_PARENTESE", Pattern.compile("[)]"));
    }
}

/*
 * DEPOIS USAR:
 * Matcher matcher = mapaRegex.get(TOKEN).matcher(caractereAtual);
 * matcher.matches();
 */