package com.analisadorDeEscopos.model;

public class Linha {
    private String nomeToken;
    private String valorToken;
    private String tokenGerado;

    /**
     * Construtor vazio de classe
     */
    public Linha() {}

    /**
     * Construtor de classe de dois parâmetros.
     * Recebe como argumentos:
     * @param nomeToken String
     * @param valorToken String
     */
    public Linha(String nomeToken, String valorToken) {
        this.nomeToken = nomeToken;
        this.valorToken = valorToken;
    }

    /**
     * Constror completo de classe.
     * Ele recebe 3 parâmetros.
     * Os parâmetros são:
     * @param nomeToken String
     * @param valorToken String
     * @param tokenGerado String
     */
    public Linha(String nomeToken, String valorToken, String tokenGerado) {
        this.nomeToken = nomeToken;
        this.valorToken = valorToken;
        this.tokenGerado = tokenGerado;
    }

    /**
     * Método getter para
     * o atributo nomeToken
     * @return nomeToken String
     */
    public String getNomeToken() {
        return this.nomeToken;
    }

    /**
     * Método setter para
     * o atributo nomeToken
     * @param nomeToken String
     */
    public void setNomeToken(String nomeToken) {
        this.nomeToken = nomeToken;
    }

    /**
     * Método getter para
     * o atributo nomeToken
     * @return nomeToken String
     */
    public String getValorToken() {
        return this.valorToken;
    }

    /**
     * Método setter para
     * o atributo valorToken
     * @param valorToken String
     */
    public void setValorToken(String valorToken) {
        this.valorToken = valorToken;
    }

    /**
     * Método getter para
     * o atributo tokenGerado
     * @return tokenGerado String
     */
    public String getTokenGerado() {
        return this.tokenGerado;
    }

    /**
     * Método setter para
     * o atributo tokenGerado
     * @param tokenGerado String
     */
    public void setTokenGerado(String tokenGerado) {
        this.tokenGerado = tokenGerado;
    }
}
