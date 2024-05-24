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
     * Construtor unitário de classe.
     * Recebe o identificador de bloco
     * como parâmetro e inicializa uma
     * nova Tabela com este identificador.
     * @param identificadorDeBloco String
     */
    public TabelaController(String identificadorDeBloco) {
        this.tabela = new Tabela(identificadorDeBloco);
    }

    /**
     * Contrutor secundário de classe.
     * Recebe uma Linha como parâmetro
     * e inicializa uma nova Tabela
     * com esta Linha.
     * @param identificadorDeBloco String
     * @param linha Linha
     */
    public TabelaController(String identificadorDeBloco, Linha linha) {
        this.tabela = new Tabela(identificadorDeBloco, linha);
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
     * Método getter usado para
     * retornar a tabela salva
     * @return tabela Tabela
     */
    public Tabela getTabela() {
        return this.tabela;
    }

    /**
     * Método getter para
     * retornar toda o identificador
     * de bloco.
     * @return identificadorDeBloco String
     */
    public String getIdentificadorDeBloco() {
        return this.tabela.getIdentificadorDeBloco();
    }

    /**
     * Método setter que atualiza o
     * identificador de bloco.
     * @param identificadorDeBloco LinkedList(Linha)
     */
    public void setIdentificadorDeBloco(String identificadorDeBloco) {
        this.tabela.setIdentificadorDeBloco(identificadorDeBloco);
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

    /**
     * Método utilitário usado
     * para matar a tabela em
     * uso e iniciar uma nova
     * tabela.
     */
    public void limparTabela() {
        this.tabela = new Tabela();
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
        return this.tabela.getIndiceDeUmaLinhaPorNomeDaVariavel(nomeDaVariavel);
    }
}
