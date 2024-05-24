package com.analisadorDeEscopos.model;

import java.util.LinkedList;

public class Tabela {
    private LinkedList<Linha> linhasDaTabela;
    private String identificadorDeBloco;

    /**
     * Construtor vazio de classe.
     * Não recebe parâmetros.
     * Inicializa uma tabela vazia
     */
    public Tabela() {
        this.linhasDaTabela = new LinkedList<>();
    }

    /**
     * Construtor unitário de classe.
     * Recebe o identificador de bloco.
     * Além disso inicializa a variável
     * linhasDaTabela.
     * @param identificadorDeBloco String
     */
    public Tabela(String identificadorDeBloco) {
        this.linhasDaTabela = new LinkedList<>();
        this.identificadorDeBloco = identificadorDeBloco;
    }

    /**
     * Construtor de classe que recebe
     * duas variáveis.
     * Recebe o identificador de bloco
     * e uma Linha como parâmetro
     * para inicializar a tabela já com
     * uma linha.
     * @param identificadorDeBloco  String
     * @param linha Linha
     */
    public Tabela(String identificadorDeBloco, Linha linha) {
        this.identificadorDeBloco = identificadorDeBloco;
        this.linhasDaTabela = new LinkedList<>();
        this.linhasDaTabela.add(linha);
    }

    /**
     * Construtor completo de classe.
     * Recebe como parâmetros o identificador
     * do bloco e uma lista de linhas e
     * inicializa uma tabela.
     * @param identificadorDeBloco String
     * @param linhasDaTabela LinkedList(Linha)
     */
    public Tabela(String identificadorDeBloco, LinkedList<Linha> linhasDaTabela) {
        this.identificadorDeBloco = identificadorDeBloco;
        this.linhasDaTabela = linhasDaTabela;
    }

    /**
     * Método getter para
     * retornar toda o identificador
     * de bloco.
     * @return identificadorDeBloco String
     */
    public String getIdentificadorDeBloco() {
        return this.identificadorDeBloco;
    }

    /**
     * Método setter que atualiza o
     * identificador de bloco.
     * @param identificadorDeBloco LinkedList(Linha)
     */
    public void setIdentificadorDeBloco(String identificadorDeBloco) {
        this.identificadorDeBloco = identificadorDeBloco;
    }

    /**
     * Método getter para
     * retornar toda a tabela.
     * @return LinkedList(Linha)
     */
    public LinkedList<Linha> getLinhasDaTabela() {
        return this.linhasDaTabela;
    }

    /**
     * Método setter que cria uma nova tabela
     * e a popula com várias linhas.
     * @param linhasDaTabela LinkedList(Linha)
     */
    public void setLinhasDaTabela(LinkedList<Linha> linhasDaTabela) {
        this.linhasDaTabela = linhasDaTabela;
    }

    /**
     * Método setter para adicionar uma
     * nova Linha na tabela.
     * Rebece uma Linha.
     * @param novaLinha Linha
     */
    public void adicionarLinhaNaTabela(Linha novaLinha) {
        this.linhasDaTabela.add(novaLinha);
    }

    /**
     * Método para remover uma linha da tabela.
     * Recebe como parâmetro a estrutura
     * 'Linha' que deve ser removida.
     * @param linhaParaRemover Linha
     */
    public void removerLinhaDaTabela(Linha linhaParaRemover) {
        this.linhasDaTabela.remove(linhaParaRemover);
    }

    /**
     * Método para remover uma linha da tabela.
     * Recebe como parâmetro o índice da
     * Linha que deve ser removida.
     * @param indice int
     */
    public void removerLinhaDaTabela(int indice) {
        this.linhasDaTabela.remove(indice);
    }

    /**
     * Método utilitário getIndiceDeUmaLinhaPorNomeDaVariavel.
     * Recebe como parâmetro o nome do uma variável.
     * Retorna o primeiro índice da linha onde se encontra
     * este nome de variável. Pode retornar -1 caso
     * a variável não exista na tabela.
     * @param nomeDaVariavel String
     * @return indice do objeto (existe tal objeto) | -1 (erro. Objeto não existe!!!)
     */
    public Integer getIndiceDeUmaLinhaPorNomeDaVariavel(String nomeDaVariavel) {
        for(Linha linha : this.linhasDaTabela) {
            if(linha.getNomeDaVariavel().equals(nomeDaVariavel)) {
                return this.linhasDaTabela.indexOf(linha);
            }
        }

        return -1;
    }
}
