package com.gomoku.utils;

import java.util.Scanner;

public class Utils {
    public static String pegarJogadaDaTela(Scanner scanner) {
        return scanner.nextLine();
    }

    public static Short[] recuperarLinhaColunaDeFrase(String frase) {
        Short[] jogada = new Short[2];
        
        jogada[0] = Short.parseShort(frase.split(" ")[0]);
        jogada[1] = Short.parseShort(frase.split(" ")[1]);
        
        return jogada;
    }
}
