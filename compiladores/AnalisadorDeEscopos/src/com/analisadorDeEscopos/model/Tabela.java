package com.analisadorDeEscopos.model;

import java.util.LinkedList;

public class Tabela {
    private LinkedList<Linha> linhasDaTabela;

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
     * Recebe uma Linha como parâmetro
     * para inicializar a tabela já com
     * uma linha.
     * @param linha Linha
     */
    public Tabela(Linha linha) {
        this.linhasDaTabela = new LinkedList<>();
        this.linhasDaTabela.add(linha);
    }

    /**
     * Construtor completo de classe.
     * Recebe como parâmetro uma lista
     * de linha e inicializa uma tabela.
     * @param linhasDaTabela LinkedList(Linha)
     */
    public Tabela(LinkedList<Linha> linhasDaTabela) {
        this.linhasDaTabela = linhasDaTabela;
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
}
