package com.analisadorDeEscopos.palavrasReservadas;

import java.util.ArrayList;

public class PalavrasReservadas {
    public static final ArrayList<String> listaDePalavrasReservadas;

    static {
        listaDePalavrasReservadas = new ArrayList<>(5);
        listaDePalavrasReservadas.add("BLOCO");
        listaDePalavrasReservadas.add("FIM");
        listaDePalavrasReservadas.add("NUMERO");
        listaDePalavrasReservadas.add("CADEIA");
        listaDePalavrasReservadas.add("PRINT");
    }
}
