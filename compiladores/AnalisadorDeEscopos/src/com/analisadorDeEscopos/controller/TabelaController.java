package com.analisadorDeEscopos.controller;

import com.analisadorDeEscopos.model.Linha;
import com.analisadorDeEscopos.model.Tabela;
import java.util.LinkedList;

public class TabelaController {
    private Tabela tabela;

    /**
     * Construtor vazio de classe.
     * Inicializar uma nova Tabela.
     */
    public TabelaController() {
        this.tabela = new Tabela();
    }

    /**
     * Contrutor secundário de classe.
     * Recebe uma Linha como parâmetro
     * e iniciliza uma nova Tabela
     * com esta Linha.
     * @param linha
     */
    public TabelaController(Linha linha) {
        this.tabela = new Tabela(linha);
    }

    /**
     * Contrutor completo e principal
     * ce classe. Recebe uma Tabela.
     * @param tabela Tabela
     */
    public TabelaController(Tabela tabela) {
        this.tabela = tabela;
    }

    /**
     * Método getter para
     * retornar toda a tabela.
     * @return LinkedList(Linha)
     */
    public LinkedList<Linha> getLinhasDaTabela() {
        return this.tabela.getLinhasDaTabela();
    }

    /**
     * Método setter que cria uma nova tabela
     * e a popula com várias linhas.
     * @param linhasDaTabela LinkedList(Linha)
     */
    public void setLinhasDaTabela(LinkedList<Linha> linhasDaTabela) {
        this.tabela.setLinhasDaTabela(linhasDaTabela);
    }

    /**
     * Método setter para adicionar uma
     * nova Linha na tabela.
     * Rebece uma Linha.
     * @param novaLinha Linha
     */
    public void adicionarLinhaNaTabela(Linha novaLinha) {
        this.tabela.adicionarLinhaNaTabela(novaLinha);
    }

    /**
     * Método para remover uma linha da tabela.
     * Recebe como parâmetro a estrutura
     * 'Linha' que deve ser removida.
     * @param linhaParaRemover Linha
     */
    public void removerLinhaDaTabela(Linha linhaParaRemover) {
        this.tabela.removerLinhaDaTabela(linhaParaRemover);
    }

    /**
     * Método para remover uma linha da tabela.
     * Recebe como parâmetro o índice da
     * Linha que deve ser removida.
     * @param indice int
     */
    public void removerLinhaDaTabela(int indice) {
        this.tabela.removerLinhaDaTabela(indice);
    }
}
