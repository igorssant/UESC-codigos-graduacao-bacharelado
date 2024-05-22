package com.analisadorDeEscopos.utils.verificarTipo;

import com.analisadorDeEscopos.palavrasReservadas.PalavrasReservadas;
import java.util.IllegalFormatConversionException;

public class VerificarTipo {
    public static Boolean palavraEhReservada(String palavraCandidata){
        return PalavrasReservadas.listaDePalavrasReservadas.contains(palavraCandidata);
    }

    public static Boolean variavelEhNumerico(String possivelNumero) {
        try {
            Integer numerointeiro = Integer.parseInt(possivelNumero);
            return true;
        } catch(IllegalFormatConversionException ignored) {}

        try {
            Double numeroFlutuante = Double.parseDouble(possivelNumero);
            return true;
        } catch(IllegalFormatConversionException e) {
            System.err.println("O valor " + possivelNumero + " não é do tipo númérico.");
        }

        return false;
    }
}
