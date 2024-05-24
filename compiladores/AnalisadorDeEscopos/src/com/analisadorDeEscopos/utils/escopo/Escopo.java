package com.analisadorDeEscopos.utils.escopo;

import com.analisadorDeEscopos.controller.TabelaController;
import com.analisadorDeEscopos.model.Linha;
import com.analisadorDeEscopos.model.Tabela;
import java.util.Stack;

import static com.analisadorDeEscopos.palavrasReservadas.PalavrasReservadas.listaDePalavrasReservadas;

public class Escopo {
    private TabelaController tabelaController;
    private final Stack<Tabela> pilha;
    private Boolean possuiValorParaImpressao;
    private String valorParaImprimir;
    private Integer linha;

    public Escopo() {
        this.tabelaController = new TabelaController();
        this.pilha = new Stack<>();
        this.possuiValorParaImpressao = false;
        this.valorParaImprimir = "";
        this.linha = 1;
    }

    public Escopo(TabelaController tabelaController) {
        this.tabelaController = tabelaController;
        this.pilha = new Stack<>();
        this.possuiValorParaImpressao = false;
        this.valorParaImprimir = "";
        this.linha = 1;
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

    public Integer getLinha() {
        return this.linha;
    }

    private void incrementarLinha() {
        this.linha++;
    }

    private void rewind() {
        this.linha = 1;
    }

    private void atribuirValorParaImpressao(String fraseParaImprimir) {
        this.valorParaImprimir = fraseParaImprimir;
        this.possuiValorParaImpressao = true;
    }

    private Boolean variavelExisteNoEscopoAtual(String variavel, Tabela escopoAtual) {
        for(Linha linha : escopoAtual.getLinhasDaTabela()) {
            if(linha.getNomeDaVariavel().equals(variavel)) {
                return true;
            }
        }

        return false;
    }

    private Linha extrairLinhaDeUmaTabela(String variavel, Tabela tabela) {
        Integer indice = tabela.getIndiceDeUmaLinhaPorNomeDaVariavel(variavel);

        if(indice > -1) {
            return tabela
                .getLinhasDaTabela()
                .get(indice);
        }

        atribuirValorParaImpressao("Erro linha" + this.linha + " Variável não declarada.");
        return null;
    }

    public void pilhaDeEscopo(String linhaLida) {
        if(linhaLida.contains(listaDePalavrasReservadas.getFirst())) { /* Linha possui `BLOCO` */
            if(!pilha.empty()) {
                pilha.push(tabelaController.getTabela());
            }

            this.tabelaController.setIdentificadorDeBloco(
                linhaLida.substring(
                    linhaLida.indexOf("_") + 1,
                    linhaLida.lastIndexOf("_")
                )
            );
        } else if(linhaLida.contains(listaDePalavrasReservadas.get(1))) { /* Linha possui `FIM` */
            if(this.pilha.contains(tabelaController.getTabela())) {
                this.pilha.pop();
            } else {
                tabelaController.limparTabela();
            }
        } else if(linhaLida.contains(listaDePalavrasReservadas.getLast())) { /* Linha possui `PRINT` */
            /* VERIFICAR SE VARIAVEL EXISTE NO ESCOPO */
            String variavel = linhaLida.substring(
                linhaLida.lastIndexOf(",") + 2,
                linhaLida.lastIndexOf(">")
            );
            Linha linha = null;


            if(variavelExisteNoEscopoAtual(variavel, tabelaController.getTabela())) {
                linha = extrairLinhaDeUmaTabela(variavel, this.tabelaController.getTabela());
            } else {
                Stack<Tabela> pilhaTemporaria = (Stack<Tabela>) this.pilha.clone();

                while(!pilhaTemporaria.empty()) {
                    TabelaController novoEscopo = new TabelaController(pilhaTemporaria.pop());

                    if(!variavelExisteNoEscopoAtual(variavel, novoEscopo.getTabela())) {
                        continue;
                    }

                    linha = extrairLinhaDeUmaTabela(variavel, tabelaController.getTabela());
                }

                /* abaixo força o garbage collector a fazer o trabalho dele */
                pilhaTemporaria = null;
            }

            if(linha != null) {
                atribuirValorParaImpressao(linha.getValorDaVariavel());
            }
        } else {                                           /* Linha não possui nenhum dos anteriores */
            /* TRATAMENTO PESADO PARA SEPARADORES E ATRIBUICOES */
        }
    }
}
/*
 *  1. Ler linha
 *  2. Verificar se linha possui `BLOCO`
 *  3. Se sim, salvar ISTO && PUSH na pilha
 *  4. Se não, continue normal
 *  5. Verificar se linha possui `FIM`
 *  6. Se sim, verificar se bloco existe
 *  7. Se sim, POP na pilha
 *  8. Se bloco não existir, subir erro
 *  9. Se não, continue normal
 * 10. Verificar se linha possui `PRINT`
 * 11. Verificar se linha possui `tk_separador`
 * 12. Se tiver, separar em dois comandos
 * 13. Verificar se tem `tk_atribuicao` na linha
 * 14. Se sim, verificar se é possível
 * 15. Se não for possível, levantar erro
 * 16. Se for possível, continua
 * 17. Se não, salvar variavel com valor padrão
 */
