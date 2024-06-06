package com.analisadorDeEscopos.palavrasReservadas;

import java.util.ArrayList;

public class PalavrasReservadas {
    public static final ArrayList<String> listaDePalavrasReservadas;

    static {
        listaDePalavrasReservadas = new ArrayList<>(5);
        listaDePalavrasReservadas.add("BLOCO");     /* 0 */
        listaDePalavrasReservadas.add("FIM");       /* 1 */
        listaDePalavrasReservadas.add("NUMERO");    /* 2 */
        listaDePalavrasReservadas.add("CADEIA");    /* 3 */
        listaDePalavrasReservadas.add("PRINT");     /* 4 */
    }
}
