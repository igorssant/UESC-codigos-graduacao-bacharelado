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
     * Método getter usado para
     * retornar o tipo da variável.
     * @return tipoDaVariavel String
     */
    public String getTipoDaVariavel() {
        return this.linha.getTipoDaVariavel();
    }

    /**
     * Método setter usado para
     * atualizar o valor do variável
     * tipoDaVariavel.
     * @param tipoDaVariavel String
     */
    public void setTipoDaVariavel(String tipoDaVariavel) {
        this.linha.setTipoDaVariavel(tipoDaVariavel);
    }

    /**
     * Método getter usado para
     * retornar o nome da variável.
     * @return nomeDaVariavel String
     */
    public String getNomeDaVariavel() {
        return this.linha.getNomeDaVariavel();
    }

    /**
     * Método setter usado para
     * atualizar o valor do variável
     * nomeDaVariavel.
     * @param nomeDaVariavel String
     */
    public void setNomeDaVariavel(String nomeDaVariavel) {
        this.linha.setNomeDaVariavel(nomeDaVariavel);
    }

    /**
     * Método getter usado para
     * retornar o nome da variável.
     * @return valorDaVariavel String
     */
    public String getValorDaVariavel() {
        return this.linha.getValorDaVariavel();
    }

    /**
     * Método setter usado para
     * atualizar o valor do variável
     * valorDaVariavel.
     * @param valorDaVariavel String
     */
    public void setValorDaVariavel(String valorDaVariavel) {
        this.linha.setValorDaVariavel(valorDaVariavel);
    }
}
