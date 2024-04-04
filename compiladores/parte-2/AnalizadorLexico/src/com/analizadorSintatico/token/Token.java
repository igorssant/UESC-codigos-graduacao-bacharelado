package com.analizadorSintatico.token;

import java.util.HashMap;

public class Token {
    public static final HashMap<String, String> mapaDeTokens = new HashMap<>(27);

    static {
        /* TIPOS PRIMITIVOS */
        mapaDeTokens.put("int", "TK_INT");
        mapaDeTokens.put("float", "TK_FLOAT");
        mapaDeTokens.put("string", "TK_STRING");

        /* TIPOS COMPLEXOS */
        mapaDeTokens.put("data", "TK_DATA");
        mapaDeTokens.put("endereco", "TK_ENDERECO");

        /* IDENTIFICADORES E PALAVRAS RESERVADAS */
        mapaDeTokens.put("identificador", "TK_IDENTIFICADOR");
        mapaDeTokens.put("palavraReservada", "TK_PALAVRA_RESERVADA");

        /* COMENTARIOS */
        mapaDeTokens.put("comentarioMonolinha", "TK_COMENTARIO_MONOLINHA");
        mapaDeTokens.put("comentarioMultilinha", "TK_COMENTARIO_MULTILINHA");

        /* OPERADORES */
        // ESTE OPERADOR EH UM OPERADOR LOGICO
        mapaDeTokens.put("not", "TK_NEGACAO");

        // ESTE OPERADOR PODE SER UNARIO OU BINARIO ALEM DISSO ELE EH MATEMATICO
        mapaDeTokens.put("menos", "TK_SUBTRACAO");

        // TODOS OS OPERADORES ABAIXO SAO BINARIOS
        // MATEMATICOS
        mapaDeTokens.put("mais", "TK_SOMA");
        mapaDeTokens.put("multiplicacao", "TK_MULTIPLICACAO");
        mapaDeTokens.put("divisao", "TK_DIVISAO");

        // LOGICOS
        mapaDeTokens.put("and", "TK_AND");
        mapaDeTokens.put("or", "TK_OR");

        mapaDeTokens.put("menorQue", "TK_MENOR_QUE");
        mapaDeTokens.put("menorOuIgual", "TK_MENOR_IGUAL");

        mapaDeTokens.put("maiorQue", "TK_MAIOR_QUE");
        mapaDeTokens.put("maiorOuIgual", "TK_MAIOR_IGUAL");

        mapaDeTokens.put("igualA", "TK_E_IGUAL_A");
        mapaDeTokens.put("diferenteDe", "TK_DIFERENTE_DE");

        /* TOKEN DE ATRIBUICAO */
        mapaDeTokens.put("atribuicao", "TK_ATRIBUICAO");

        /* TOKENS DE TERMINACAO */
        mapaDeTokens.put("pontoVirgula", "TK_PONTO_VIRGULA");
        mapaDeTokens.put("doisPontos", "TK_DOIS_PONTOS");

        /* TOKENS DE AGLUTINACAO */
        mapaDeTokens.put("abreParentese", "TK_ABRE_PARENTESE");
        mapaDeTokens.put("fechaParentese", "TK_FECHA_PARENTESE");
    }
}
