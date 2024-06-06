package com.analisadorDeEscopos.model;

public class Estado {
    private Integer estadoAtual;
    private String caractereAtual;
    private Integer linha;
    private Boolean estadoEhFinal;

    /**
     * Construtor vazio de classe.
     * Usado para inicializar as variáveis:
     * - estadoAtual : Integer
     * - caractereAtual : String
     * - linha : Integer
     */
    public Estado() {
        this.estadoAtual = 0;
        this.caractereAtual = null;
        this.linha = 1;
        this.estadoEhFinal = false;
    }

    /**
     * Construtor de classe que recebe
     * a variável caractereAtual.
     * Também é usado para inicializar
     * as variáveis:
     * - estadoAtual : Integer
     * - linha : Integer
     * @param caractereAtual String
     */
    public Estado(String caractereAtual) {
        this.estadoAtual = 0;
        this.caractereAtual = caractereAtual;
        this.linha = 1;
        this.estadoEhFinal = false;
    }

    /**
     * Construtor completo de classe.
     * Recebe todas as variáveis e as
     * inicializa com os valores passados.
     * @param estadoAtual Integer
     * @param caractereAtual String
     * @param linha Integer
     */
    public Estado(Integer estadoAtual, String caractereAtual, Integer linha) {
        this.estadoAtual = estadoAtual;
        this.caractereAtual = caractereAtual;
        this.linha = linha;
        this.estadoEhFinal = false;
    }

    /**
     * Método getter usado para
     * retornar o estado atual.
     * @return estadoAtual Integer
     */
    public Integer getEstadoAtual() {
        return this.estadoAtual;
    }

    /**
     * Método setter usado para
     * atualizar o estado atual.
     * @param estadoAtual Integer
     */
    public void setEstadoAtual(Integer estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    /**
     * Método setter usado para
     * forçar o retorno ao estado
     * inicial, ou seja, o estado
     * q0.
     */
    public void retornarAoEstadoInicial() {
        this.estadoAtual = 0;
    }

    /**
     * Método getter usado para
     * retornar o caractere que
     * acabou de ser lido.
     * @return caractereAtual String
     */
    public String getCaractereAtual() {
        return this.caractereAtual;
    }

    /**
     * Método setter usado para
     * enviar o novo caractere que
     * deve ser lido.
     * @param caractereAtual String
     */
    public void setCaractereAtual(String caractereAtual) {
        this.caractereAtual = caractereAtual;
    }

    /**
     * Método getter usado para
     * retornar a linha atual
     * do arquivo onde o cursor
     * se encontra.
     * @return linhaAtual Integer
     */
    public Integer getLinha() {
        return this.linha;
    }

    /**
     * Método setter usado para
     * forçar o cursor a retornar
     * a linha 1 do arquivo de
     * entrada.
     */
    public void rewind() {
        this.linha = 1;
    }

    /**
     * Método setter usado para
     * forçar o cursor a retornar
     * avançar uma linha no
     * arquivo está sendo lido.
     */
    public void avancarLinha() {
        this.linha++;
    }

    /**
     * Método getter usado para
     * retornar a variável
     * estadoEhFinal.
     * @return estadoEhFinal Boolean
     */
    public Boolean getEstadoEhFinal() {
        return estadoEhFinal;
    }

    /**
     * Método de controle usado para
     * marcar o estado atual como
     * verdade (true).
     */
    public void marcarEstadoAtualComoFinal() {
        this.estadoEhFinal = true;
    }

    /**
     * Método de controle usado para
     * marcar o estado atual como
     * falso (false).
     */
    public void estadoAtualNaoEhFinal() {
        this.estadoEhFinal = false;
    }
}
