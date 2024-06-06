package com.analisadorDeEscopos.model;

import static com.analisadorDeEscopos.palavrasReservadas.PalavrasReservadas.listaDePalavrasReservadas;
import static com.analisadorDeEscopos.token.Token.dicionarioDeTokens;

public class Linha {
    private String tipoDaVariavel;
    private String nomeDaVariavel;
    private String valorDaVariavel;
    private String tokenResultante;

    /**
     * Construtor vazio de classe
     */
    public Linha() {
        this.tokenResultante = dicionarioDeTokens.get("identificador");
    }

    /**
     * Construtor de classe de dois parâmetros.
     * Recebe como argumentos:
     * - tipoDaVariavel;
     * - nomeDaVariavel.
     * Vale ressaltar que valorDaVariavel
     * será inicializado com o valor 0.
     * @param tipoDaVariavel String
     * @param nomeDaVariavel String
     */
    public Linha(String tipoDaVariavel, String nomeDaVariavel) {
        this.tipoDaVariavel = tipoDaVariavel;
        this.nomeDaVariavel = nomeDaVariavel;
        this.tokenResultante = dicionarioDeTokens.get("identificador");

        /* O tipo da variável foi declarada como `NUMERO` ??? */
        if(tipoDaVariavel.equals(listaDePalavrasReservadas.get(2))) {
            this.valorDaVariavel = "0";
        } else {
            this.valorDaVariavel = "";
        }

    }

    /**
     * Constror completo de classe.
     * Ele recebe 3 parâmetros.
     * Os parâmetros são:
     * @param tipoDaVariavel String
     * @param nomeDaVariavel String
     * @param valorDaVariavel String
     */
    public Linha(String tipoDaVariavel, String nomeDaVariavel, String valorDaVariavel) {
        this.tipoDaVariavel = tipoDaVariavel;
        this.nomeDaVariavel = nomeDaVariavel;
        this.valorDaVariavel = valorDaVariavel;
        this.tokenResultante = dicionarioDeTokens.get("identificador");
    }

    /**
     * Constror completo de classe.
     * Ele recebe todos os parâmetros.
     * Os parâmetros são:
     * @param tipoDaVariavel String
     * @param nomeDaVariavel String
     * @param valorDaVariavel String
     * @param tokenResultante String
     */
    public Linha(String tipoDaVariavel, String nomeDaVariavel, String valorDaVariavel, String tokenResultante) {
        this.tipoDaVariavel = tipoDaVariavel;
        this.nomeDaVariavel = nomeDaVariavel;
        this.valorDaVariavel = valorDaVariavel;
        this.tokenResultante = tokenResultante;
    }

    /**
     * Método getter usado para
     * retornar o tipo da variável.
     * @return tipoDaVariavel String
     */
    public String getTipoDaVariavel() {
        return this.tipoDaVariavel;
    }

    /**
     * Método setter usado para
     * atualizar o valor do variável
     * tipoDaVariavel.
     * @param tipoDaVariavel String
     */
    public void setTipoDaVariavel(String tipoDaVariavel) {
        this.tipoDaVariavel = tipoDaVariavel;
    }

    /**
     * Método getter usado para
     * retornar o nome da variável.
     * @return nomeDaVariavel String
     */
    public String getNomeDaVariavel() {
        return this.nomeDaVariavel;
    }

    /**
     * Método setter usado para
     * atualizar o valor do variável
     * nomeDaVariavel.
     * @param nomeDaVariavel String
     */
    public void setNomeDaVariavel(String nomeDaVariavel) {
        this.nomeDaVariavel = nomeDaVariavel;
    }

    /**
     * Método getter usado para
     * retornar o nome da variável.
     * @return valorDaVariavel String
     */
    public String getValorDaVariavel() {
        return this.valorDaVariavel;
    }

    /**
     * Método setter usado para
     * atualizar o valor do variável
     * valorDaVariavel.
     * @param valorDaVariavel String
     */
    public void setValorDaVariavel(String valorDaVariavel) {
        this.valorDaVariavel = valorDaVariavel;
    }

    /**
     * Método getter usado para
     * retornar o o token formado.
     * @return tokenResultante String
     */
    public String getTokenResultante() {
        return this.tokenResultante;
    }

    /**
     * Método setter usado para
     * atualizar o valor do variável
     * tokenResultante.
     * @param tokenResultante String
     */
    public void setTokenResultante(String tokenResultante) {
        this.tokenResultante = tokenResultante;
    }
}
