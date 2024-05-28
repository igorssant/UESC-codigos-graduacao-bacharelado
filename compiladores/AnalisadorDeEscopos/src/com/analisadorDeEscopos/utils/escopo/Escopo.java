package com.analisadorDeEscopos.utils.escopo;

import com.analisadorDeEscopos.controller.TabelaController;
import com.analisadorDeEscopos.model.Linha;
import com.analisadorDeEscopos.model.Tabela;
import com.analisadorDeEscopos.utils.verificarTipo.VerificarTipo;

import java.util.HashMap;
import java.util.Stack;
import static com.analisadorDeEscopos.palavrasReservadas.PalavrasReservadas.listaDePalavrasReservadas;
import static com.analisadorDeEscopos.token.Token.dicionarioDeTokens;

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

        atribuirValorParaImpressao("Erro linha " + this.linha + ". Variável não declarada.");
        return null;
    }

    private Tabela retornarEscopo(String nomeDaVariavel) {
        Stack<Tabela> pilhaTemporaria = (Stack<Tabela>) this.pilha.clone();
        Tabela tabela = null;

        while(!pilhaTemporaria.empty()) {
            TabelaController novoEscopo = new TabelaController(pilhaTemporaria.pop());

            if(!variavelExisteNoEscopoAtual(nomeDaVariavel, novoEscopo.getTabela())) {
                continue;
            }

            tabela = tabelaController.getTabela();
        }

        if(tabela == null) {
            atribuirValorParaImpressao("Erro linha " + this.linha + ". Variável não declarada.");
            return null;
        }

        return tabela;
    }

    private void atribuicaoDeVariavelDuranteCriacao(
            String nomeDaVariavel,
            String valorDaVariavel,
            String tipoDaVariavel
    ) {
        if(
            tipoDaVariavel.equals(listaDePalavrasReservadas.get(2)) &&
            !VerificarTipo.variavelEhNumerico(valorDaVariavel)
        ) {                                 /* variável é declarada como `NUMERO` e seu valor não é numérico */
            atribuirValorParaImpressao("Erro linha " + this.linha + ", tipos não compatíveis.");
            return;
        }

        if(
            tipoDaVariavel.equals(listaDePalavrasReservadas.get(3)) &&
            VerificarTipo.variavelEhNumerico(valorDaVariavel)
        ) {                                     /* variável é declarada como `CADEIA` é, de fato, é numérico */
            atribuirValorParaImpressao("Erro linha " + this.linha + ", tipos não compatíveis.");
            return;
        }

        if(valorDaVariavel == null) {
            if(tipoDaVariavel.equals(listaDePalavrasReservadas.get(2))) {
                valorDaVariavel = "0";
            } else {
                valorDaVariavel = "";
            }
        }

        this.tabelaController.adicionarLinhaNaTabela(
            new Linha(tipoDaVariavel, nomeDaVariavel, valorDaVariavel)
        );
    }

    private void atribuicaoDeVariavelPosCriacao(String variavel, String valor, Boolean valorEhUmaVariavel) {
        if(!valorEhUmaVariavel) {
            this.tabelaController.atualizarLinha(
                this.tabelaController.getIndiceDeUmaLinhaPorNomeDaVariavel(variavel),
                null,
                null,
                valor
            );
        } else {
            Integer indiceDaVariavel;
            String tipoDaVariavel = null,
                valorDaVariavel = null;

            if(variavelExisteNoEscopoAtual(valor, this.tabelaController.getTabela())) {
                indiceDaVariavel = this.tabelaController.getIndiceDeUmaLinhaPorNomeDaVariavel(variavel);
                tipoDaVariavel = this.tabelaController.getLinhasDaTabela().get(indiceDaVariavel).getTipoDaVariavel();
                valorDaVariavel = this.tabelaController.getLinhasDaTabela().get(indiceDaVariavel).getValorDaVariavel();
            } else {
                Tabela escopoDaVariavel = retornarEscopo(valor);

                if(escopoDaVariavel != null) {
                    Linha linhaDaTabela = extrairLinhaDeUmaTabela(valor, escopoDaVariavel);

                    if(linhaDaTabela == null) {
                        return;
                    }

                    tipoDaVariavel = linhaDaTabela.getTipoDaVariavel();
                    valorDaVariavel = linhaDaTabela.getValorDaVariavel();
                }
            }

            if(!this.tabelaController.getLinhasDaTabela()
                .get(this.tabelaController.getIndiceDeUmaLinhaPorNomeDaVariavel(variavel))
                .getTipoDaVariavel()
                .equals(tipoDaVariavel)
            ) {
                atribuirValorParaImpressao("Erro linha" + linha + ", tipos não compatíveis.");
                return;
            }

            this.tabelaController.getLinhasDaTabela()
                .get(this.tabelaController.getIndiceDeUmaLinhaPorNomeDaVariavel(variavel))
                .setValorDaVariavel(valorDaVariavel);
        }
    }

    private HashMap<String, String> destrincharStringDeEntrada(String linhaLida) {
        HashMap<String, String> hashmap = new HashMap<>(5);
        String parteQueInteressa = linhaLida.substring(linhaLida.indexOf(">") + 1);

        do {
            String valorDaVariavel = null,
                    nomeDaVariavel = parteQueInteressa.substring(
                parteQueInteressa.indexOf(",") + 2,
                parteQueInteressa.indexOf(">")
            );
            parteQueInteressa = parteQueInteressa.substring(4 + nomeDaVariavel.length());

            if(parteQueInteressa.substring(0, 16).equals("<" + dicionarioDeTokens.get("atribuicao") + ">")) {
                parteQueInteressa = parteQueInteressa.substring(16);
                valorDaVariavel = parteQueInteressa.substring(
                    parteQueInteressa.indexOf(","),
                    parteQueInteressa.indexOf(">")
                );

                hashmap.put(nomeDaVariavel, valorDaVariavel);
            } else {
                hashmap.put(nomeDaVariavel, valorDaVariavel);
            }

            if(!parteQueInteressa.contains(dicionarioDeTokens.get("separador"))){
                break;
            }

            parteQueInteressa = parteQueInteressa.substring(
                parteQueInteressa.indexOf(dicionarioDeTokens.get("separador"))
            );
        } while(linhaLida.contains(dicionarioDeTokens.get("separador")));

        return hashmap;
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
            Linha informacoesDaTabela = null;


            if(variavelExisteNoEscopoAtual(variavel, tabelaController.getTabela())) {
                informacoesDaTabela = extrairLinhaDeUmaTabela(variavel, this.tabelaController.getTabela());
            } else {
                informacoesDaTabela = extrairLinhaDeUmaTabela(variavel, retornarEscopo(variavel));
            }

            if(informacoesDaTabela != null) {
                atribuirValorParaImpressao(informacoesDaTabela.getValorDaVariavel());
            }
        } else if(linhaLida.contains(listaDePalavrasReservadas.get(2))) {   /* Linha possui `NUMERO` */
            /* sdwadsad */
            linhaLida.
        } else if(linhaLida.contains(listaDePalavrasReservadas.get(3))) {   /* Linha possui `CADEIA` */
            /* dadwasd */
        } else {                                                            /* atribuição simples */
            /* sdawdwd */
        }

        this.linha++;
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
