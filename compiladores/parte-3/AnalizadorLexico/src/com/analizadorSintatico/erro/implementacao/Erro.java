package com.analizadorSintatico.erro.implementacao;

public class Erro {
    private Integer coluna;
    private Integer linha;
    private String causa;

    /**
     * Contrutor vazio de classe
     * nenhuma variavel sera instanciada
     */
    public Erro() {}

    /**
     * Contrutor completo de classe
     * Ele recebe todas as variaiveis:
     * @param coluna Integer
     * @param linha Integer
     * @param causa String
     */
    public Erro(Integer coluna, Integer linha, String causa) {
        this.coluna = coluna;
        this.linha = linha;
        this.causa = causa;
    }

    /**
     * Metodo getter
     * Retorna a coluna
     * onde foi encontrado o erro
     * @return coluna Integer
     */
    public Integer getColuna() {
        return this.coluna;
    }

    /**
     * Metodo setter
     * Recebe o valor inteiro
     * e atribui a variavel coluna
     * @param coluna Integer
     */
    public void setColuna(Integer coluna) {
        this.coluna = coluna;
    }

    /**
     * Metodo getter
     * Retorna a linha
     * onde foi encontrado o erro
     * @return linha Integer
     */
    public Integer getLinha() {
        return linha;
    }

    /**
     * Metodo setter
     * Recebe o valor inteiro
     * e atribui a variavel linha
     * @param linha Integer
     */
    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    /**
     * Metodo getter
     * Retorna uma string
     * descrevendo o erro capturado
     * @return causa String
     */
    public String getCausa() {
        return causa;
    }

    /**
     * Metodo setter
     * Recebe uma string contendo a causa
     * e atribui a variavel causa
     * @param causa String
     */
    public void setCausa(String causa) {
        this.causa = causa;
    }
}
