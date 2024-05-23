package com.analisadorDeEscopos.token;

import java.util.HashMap;

public class Token {
    public static final HashMap<String, String> dicionarioDeTokens;

    static {
        dicionarioDeTokens = new HashMap<>(7);

        /* Identificadores */
        dicionarioDeTokens.put("identificador", "tk_identificador");

        /* Identificadores de bloco */
        dicionarioDeTokens.put("bloco", "tk_id_bloco");

        /* Tipos de dados */
        dicionarioDeTokens.put("numero", "tk_numero");
        dicionarioDeTokens.put("string", "tk_cadeia");

        /* Sinal de soma */
        dicionarioDeTokens.put("soma", "tk_soma");

        /* Tokens utilitários */
        dicionarioDeTokens.put("atribuicao", "tk_atribuicao");
        dicionarioDeTokens.put("separador", "tk_separador");
        dicionarioDeTokens.put("palavraReservada", "tk_palavra_reservada");
    }
}
