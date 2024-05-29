package com.analisadorDeEscopos.utils.verificarTipo;

import com.analisadorDeEscopos.palavrasReservadas.PalavrasReservadas;
import java.util.regex.Matcher;

import static com.analisadorDeEscopos.expressaoRegular.ExpressaoRegular.listaDeRegex;

public class VerificarTipo {
    public static Boolean palavraEhReservada(String palavraCandidata){
        return PalavrasReservadas.listaDePalavrasReservadas.contains(palavraCandidata);
    }

    public static Boolean variavelEhNumerico(String possivelNumero) {
        Matcher matcher = null;

        /* VERIFICA SE É NÚMERO INTEIRO */
        if((matcher = listaDeRegex.get(15).matcher(possivelNumero)).matches()) {
            return true;
        }

        /* VERIFICA SE É PONTO FLUTUANTE */
        return (matcher = listaDeRegex.get(16).matcher(possivelNumero)).matches();
    }
}
