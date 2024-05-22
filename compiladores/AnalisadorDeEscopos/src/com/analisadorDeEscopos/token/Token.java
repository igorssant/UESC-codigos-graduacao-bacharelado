package com.analisadorDeEscopos.token;

import java.util.HashMap;

public class Token {
    public static final HashMap<String, String> dicionarioDeTokens;

    static {
        dicionarioDeTokens = new HashMap<>(5);

        /* Identificadores */
        dicionarioDeTokens.put("identificador", "tk_identificador");

        /* Identificadores de bloco */
        dicionarioDeTokens.put("bloco", "tk_id_bloco");

        /* Tipos de dados */
        dicionarioDeTokens.put("numero", "tk_numero");
        dicionarioDeTokens.put("string", "tk_cadeia");

        /* Sinal de soma */
        dicionarioDeTokens.put("soma", "tk_soma");
    }
}
