package com.analisadorDeEscopos.controller;

import com.analisadorDeEscopos.model.Linha;

public class LinhaController {
    private Linha linha;

    /**
     * Construtor de classe vazio.
     * Inicializa uma nova Linha.
     */
    public LinhaController() {
        this.linha = new Linha();
    }

    /**
     * Construtor completo da classe.
     * Recebe uma Linha como parâmetro.
     * @param linha Linha
     */
    public LinhaController(Linha linha) {
        this.linha = linha;
    }

    /**
     * Método getter usado para
     * retornar uma Linha.
     * @return linha Linha
     */
    public Linha getLinha() {
        return this.linha;
    }

    /**
     * Método setter usado para
     * substuir a Linha atual
     * por uma nova Linha.
     * @param linha Linha
     */
    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    /**
     * Método getter para
     * o atributo nomeToken
     * @return nomeToken String
     */
    public String getNomeToken() {
        return this.linha.getNomeToken();
    }

    /**
     * Método setter para
     * o atributo nomeToken
     * @param nomeToken String
     */
    public void setNomeToken(String nomeToken) {
        this.linha.setNomeToken(nomeToken);
    }

    /**
     * Método getter para
     * o atributo nomeToken
     * @return nomeToken String
     */
    public String getValorToken() {
        return this.linha.getValorToken();
    }

    /**
     * Método setter para
     * o atributo valorToken
     * @param valorToken String
     */
    public void setValorToken(String valorToken) {
        this.linha.setValorToken(valorToken);
    }

    /**
     * Método getter para
     * o atributo tokenGerado
     * @return tokenGerado String
     */
    public String getTokenGerado() {
        return this.linha.getTokenGerado();
    }

    /**
     * Método setter para
     * o atributo tokenGerado
     * @param tokenGerado String
     */
    public void setTokenGerado(String tokenGerado) {
        this.linha.setTokenGerado(tokenGerado);
    }
}
