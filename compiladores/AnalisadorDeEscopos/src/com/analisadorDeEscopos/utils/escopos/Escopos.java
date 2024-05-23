package com.analisadorDeEscopos.utils.escopos;

import com.analisadorDeEscopos.controller.TabelaController;
import com.analisadorDeEscopos.model.Tabela;
import java.util.Stack;

public class Escopos {
    private TabelaController tabelaController;
    private final Stack<Tabela> pilha;
    private Boolean possuiValorParaImpressao;
    private String valorParaImprimir;

    public Escopos() {
        this.tabelaController = new TabelaController();
        this.pilha = new Stack<>();
        this.possuiValorParaImpressao = false;
        this.valorParaImprimir = "";
    }

    public Escopos(TabelaController tabelaController) {
        this.tabelaController = tabelaController;
        this.pilha = new Stack<>();
        this.possuiValorParaImpressao = false;
        this.valorParaImprimir = "";
    }

    public TabelaController getTabelaController() {
        return this.tabelaController;
    }

    public void setTabelaController(TabelaController tabelaController) {
        this.tabelaController = tabelaController;
    }

    public Boolean getPossuiValorParaImpressao() {
        return this.possuiValorParaImpressao;
    }

    public String getValorParaImprimir() {
        return this.valorParaImprimir;
    }



    /*
     * 1. Ler linha
     * 2. Verificar se linha possui `BLOCO`
     * 3. Se sim, salvar ISTO && PUSH na pilha
     * 4. Se não, continue normal
     * 5. Verificar se linha possui `FIM`
     * 6. Se sim, verificar se bloco existe
     * 7. Se sim, POP na pilha
     * 8. Se bloco não existir, subir erro
     * 9. Se não, continue normal
     * 10. Verificar se linha possui `PRINT`
     * 11. Verificar se linha possui `tk_separador`
     * 12. Se tiver, separar em dois comandos
     * 13. Verificar se tem `tk_atribuicao` na linha
     * 14. Se sim, verificar se é possível
     * 15. Se não for possível, levantar erro
     * 16. Se for possível, continua
     * 17. Se não, salvar variavel com valor padrão
     */
}
