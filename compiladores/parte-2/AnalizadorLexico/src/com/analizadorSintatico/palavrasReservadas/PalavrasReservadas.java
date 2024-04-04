package com.analizadorSintatico.palavrasReservadas;

import java.util.HashSet;

public class PalavrasReservadas {
    public static final HashSet<String> palavrasReservadas = new HashSet<>(9);

    static {
        palavrasReservadas.add("rotina");
        palavrasReservadas.add("fim_rotina");
        palavrasReservadas.add("imprima");
        palavrasReservadas.add("leia");
        palavrasReservadas.add("enquanto");
        palavrasReservadas.add("se");
        palavrasReservadas.add("senao");
        palavrasReservadas.add("entao");
        palavrasReservadas.add("para");
    }
}
