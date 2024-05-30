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
        this.linha = 0;
    }

    public Escopo(TabelaController tabelaController) {
        this.tabelaController = tabelaController;
        this.pilha = new Stack<>();
        this.possuiValorParaImpressao = false;
        this.valorParaImprimir = "";
        this.linha = 0;
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

    public void incrementarLinha() {
        this.linha++;
    }

    private void rewind() {
        this.linha = 0;
    }

    public void valorParaImpressaoJaFoiUsado() {
        this.possuiValorParaImpressao = false;
        this.valorParaImprimir = "";
    }

    private void atribuirValorParaImpressao(String fraseParaImprimir) {
        this.valorParaImprimir = fraseParaImprimir;
        this.possuiValorParaImpressao = true;
    }

    private Boolean variavelExisteNoEscopoAtual(String variavel, Tabela escopoAtual) {
        for(Linha linha : escopoAtual.getLinhasDaTabela()) {
            if(linha.getNomeDaVariavel() == null) {
                continue;
            }

            if(linha.getNomeDaVariavel().equals(variavel)) {
                return true;
            }
        }

        return false;
    }

    private Linha extrairLinhaDeUmaTabela(String variavel, Tabela tabela) {
        if(tabela != null) {
            Integer indice = tabela.getIndiceDeUmaLinhaPorNomeDaVariavel(variavel);

            if(indice > -1) {
                return tabela
                    .getLinhasDaTabela()
                   .get(indice);
            }
        }

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

            tabela = novoEscopo.getTabela();
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
        this.possuiValorParaImpressao = false;

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

    private void atualizarLinhaDaTabela(Integer indice, String valorDaVariavel) {
        this.tabelaController.atualizarLinha(
            indice,
            null,
            null,
            valorDaVariavel
        );
    }

    private void atribuicaoDeVariavelPosCriacao(String variavel, String valor) {
        this.possuiValorParaImpressao = false;

        if(valor.contains("\"")) {
            if(variavelExisteNoEscopoAtual(variavel, this.tabelaController.getTabela())) {
                atualizarLinhaDaTabela(
                    this.tabelaController.getIndiceDeUmaLinhaPorNomeDaVariavel(variavel),
                    valor.substring(valor.indexOf("\""), valor.lastIndexOf("\"") + 1)
                );
            } else {
                TabelaController novoEscopo = new TabelaController(retornarEscopo(variavel));
                Integer escopo = this.pilha.indexOf(novoEscopo.getTabela());

                novoEscopo.getLinhasDaTabela()
                    .get(novoEscopo.getIndiceDeUmaLinhaPorNomeDaVariavel(variavel))
                    .setValorDaVariavel(valor);
                this.pilha.set(escopo, novoEscopo.getTabela());
            }
        } else if(VerificarTipo.variavelEhNumerico(valor)) {
            if(variavelExisteNoEscopoAtual(variavel, this.tabelaController.getTabela())) {
                atualizarLinhaDaTabela(
                    this.tabelaController.getIndiceDeUmaLinhaPorNomeDaVariavel(variavel),
                    valor
                );
            } else {
                Tabela tabelaTemporaria = retornarEscopo(variavel);

                if(tabelaTemporaria == null) {
                    return;
                }

                TabelaController novoEscopo = new TabelaController(tabelaTemporaria);
                Integer escopo = this.pilha.indexOf(novoEscopo.getTabela());

                novoEscopo.getLinhasDaTabela()
                    .get(novoEscopo.getIndiceDeUmaLinhaPorNomeDaVariavel(variavel))
                    .setValorDaVariavel(valor);
                this.pilha.set(escopo, novoEscopo.getTabela());
            }
        } else {
            Integer indiceDaVariavel = -1,
                    indiceDaVariavelEsquerda = this.tabelaController.getIndiceDeUmaLinhaPorNomeDaVariavel(variavel);
            String tipoDaVariavel = null,
                    valorDaVariavel = null;

            if(indiceDaVariavelEsquerda < 0) {
                atribuirValorParaImpressao("Erro linha " + this.linha + ". Variável não declarada.");
                return;
            }

            String tipoDaVariavelDaEsquerda = this.tabelaController
                    .getLinhasDaTabela()
                    .get(indiceDaVariavelEsquerda)
                    .getTipoDaVariavel();

            if(variavelExisteNoEscopoAtual(valor, this.tabelaController.getTabela())) {
                indiceDaVariavel = this.tabelaController.getIndiceDeUmaLinhaPorNomeDaVariavel(valor);
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

            if(tipoDaVariavel == null || !tipoDaVariavel.equals(tipoDaVariavelDaEsquerda)) {
                atribuirValorParaImpressao("Erro linha " + this.linha + ", tipos não compatíveis.");
                return;
            }

            this.tabelaController.getLinhasDaTabela()
                .get(this.tabelaController.getIndiceDeUmaLinhaPorNomeDaVariavel(variavel))
                .setValorDaVariavel(valorDaVariavel);
        }
    }

    private HashMap<String, String> destrincharStringDeEntrada(String linhaLida) {
        /* Verifica se linha lida é `PRINT`, `FIM` ou `BLOCO` */
        if(
            linhaLida.contains(listaDePalavrasReservadas.getFirst()) ||
            linhaLida.contains(listaDePalavrasReservadas.getLast()) ||
            linhaLida.contains(listaDePalavrasReservadas.get(1))
        ) {
            return null;
        }

        HashMap<String, String> hashmap = new HashMap<>(5);
        /*
         * Durante a criação da variável abaixo...
         * Existe algum token identificador próximo ao início da linha ???
         */
        String parteQueInteressa = (linhaLida.indexOf(dicionarioDeTokens.get("identificador")) < 3)?
                linhaLida:                                                  /* Se existir, use este! */
                linhaLida.substring(linhaLida.indexOf(">") + 1);  /* Se não existir, use este! */

        do {
            String valorDaVariavel = null,
                    nomeDaVariavel = parteQueInteressa.substring(
                parteQueInteressa.indexOf(",") + 2,
                parteQueInteressa.indexOf(">")
            );

            if(parteQueInteressa.length() - (
                    nomeDaVariavel.length() +
                    dicionarioDeTokens.get("identificador").length() +
                    4) > 0) {
                parteQueInteressa = parteQueInteressa.substring(
                    4 +
                    nomeDaVariavel.length() +
                    dicionarioDeTokens.get("identificador").length()
                );
            }

            if(parteQueInteressa.isBlank() || parteQueInteressa.isEmpty()) {
                break;
            }

            if(
                parteQueInteressa.substring(parteQueInteressa.indexOf("<"), parteQueInteressa.indexOf(">") + 1)
                .equals("<" + dicionarioDeTokens.get("atribuicao") + ">")
            ) {
                parteQueInteressa = parteQueInteressa.substring(15);
                valorDaVariavel = parteQueInteressa.substring(
                    parteQueInteressa.indexOf(",") + 2,
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
                parteQueInteressa.indexOf(dicionarioDeTokens.get("separador")) +
                dicionarioDeTokens.get("separador").length() +
                1
            );
        } while(!linhaLida.isEmpty());

        return hashmap;
    }

    private Boolean possuiOperacao(final String linhaLida) {
        return linhaLida.contains(dicionarioDeTokens.get("atribuicao"));
    }

    public void pilhaDeEscopo(final String linhaLida) {
        this.linha++;

        HashMap<String, String> listaDeVariaveis = destrincharStringDeEntrada(linhaLida);

        if(linhaLida.contains(listaDePalavrasReservadas.getFirst())) { /* Linha possui `BLOCO` */
            if(
                this.tabelaController.getIdentificadorDeBloco() != null &&
                !this.tabelaController.getIdentificadorDeBloco().isBlank()
            ) {
                pilha.push(this.tabelaController.getTabela());
                this.tabelaController.limparTabela();
            }

            this.tabelaController.setIdentificadorDeBloco(
                linhaLida.substring(
                    linhaLida.lastIndexOf(",") + 2,
                    linhaLida.lastIndexOf("_") + 1
                )
            );
        } else if(linhaLida.contains(listaDePalavrasReservadas.get(1))) { /* Linha possui `FIM` */
            this.tabelaController = null;

            /*
             * Para mexermos na pilha, a tabela atual não pode ter um identificador de bloco.
             * Além disso, a pilha não pode estar vazia.
             */
            if(!this.pilha.empty()){
                this.tabelaController = new TabelaController(this.pilha.pop());
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
            listaDeVariaveis.forEach((key, value) -> {
                atribuicaoDeVariavelDuranteCriacao(
                    key,
                    value,
                    "NUMERO"
                );
            });
        } else if(linhaLida.contains(listaDePalavrasReservadas.get(3))) {   /* Linha possui `CADEIA` */
            listaDeVariaveis.forEach((key, value) -> {
                atribuicaoDeVariavelDuranteCriacao(
                    key,
                    value,
                    "CADEIA"
                );
            });
        } else {                                                            /* atribuição simples */
            /*
            * Temos duas ou mais variaveis nessa linha,
            * mas essa linha possui pelo menos uma operação ???
            */
            if(possuiOperacao(linhaLida)) {
                listaDeVariaveis.forEach(this::atribuicaoDeVariavelPosCriacao);
            } else {
                atribuirValorParaImpressao("Erro linha " + this.linha + ". Variável não declarada.");
            }
        }
    }
}
