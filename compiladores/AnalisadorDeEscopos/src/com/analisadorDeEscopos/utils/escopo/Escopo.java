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

    /**
     * Contrutor de classe.
     * Não recebe parâmetros.
     * Inicializa as variáveis:
     * - pilha;
     * - tabelaController;
     * - linha;
     * - valorParaImprimir.
     */
    public Escopo() {
        this.tabelaController = new TabelaController();
        this.pilha = new Stack<>();
        this.possuiValorParaImpressao = false;
        this.valorParaImprimir = "";
        this.linha = 0;
    }

    /**
     * Contrutor de classe.
     * Recebe como parâmetro um
     * controller para uma tabela.
     * Além disso, inicializa as variáveis:
     * - pilha;
     * - linha;
     * - valorParaImprimir.
     * @param tabelaController TabelaController
     */
    public Escopo(TabelaController tabelaController) {
        this.tabelaController = tabelaController;
        this.pilha = new Stack<>();
        this.possuiValorParaImpressao = false;
        this.valorParaImprimir = "";
        this.linha = 0;
    }

    /**
     * Métdodo getter usado
     * para retornar o controller
     * para a tabela (tabelaController).
     * @return tabelaController TabelaController
     */
    public TabelaController getTabelaController() {
        return this.tabelaController;
    }

    /**
     * Método setter para inicializar um
     * novo controller para tabela.
     * @param tabelaController TabelaController
     */
    public void setTabelaController(TabelaController tabelaController) {
        this.tabelaController = tabelaController;
    }

    /**
     * Método getter que retorna
     * se há algum valor salvo para
     * se impresso no arquivo
     * de saída final.
     * @return possuiValorParaImpressao Boolean
     */
    public Boolean getPossuiValorParaImpressao() {
        return this.possuiValorParaImpressao;
    }

    /**
     * Método getter que retorna
     * oo valor que será escrito
     * no arquivo de saída final.
     * @return valorParaImprimir String
     */
    public String getValorParaImprimir() {
        return this.valorParaImprimir;
    }

    /**
     * Método getter usado para
     * retornar em qual linha se
     * encontra, atualmente, o
     * cursor, no arquivo que
     * está sendo lido.
     * @return linha Integer
     */
    public Integer getLinha() {
        return this.linha;
    }

    /**
     * Método utilitário usado
     * para mudar, manualmente,
     * o valor da linha onde
     * o cursor se encontra no
     * arquivo que está sendo lido..
     */
    public void incrementarLinha() {
        this.linha++;
    }

    /**
     * Método utilitário
     * usado para representar o
     * retorno do cursor
     * para a primeira
     * linha do arquivo.
     */
    private void rewind() {
        this.linha = 0;
    }

    /**
     * Método utilitário usado
     * para resetar o valor da variável
     * valorParaImprimir. ALém disso,
     * seta a variével possuiValorParaImpressao
     * para false, impedindo que algo seja impresso
     * no arquivo final.
     */
    public void valorParaImpressaoJaFoiUsado() {
        this.possuiValorParaImpressao = false;
        this.valorParaImprimir = "";
    }

    /**
     * Método utilitário usado para
     * salvar o valor que deve ser
     * impresso no arquivo final.
     * @param fraseParaImprimir String
     */
    private void atribuirValorParaImpressao(String fraseParaImprimir) {
        this.valorParaImprimir = fraseParaImprimir;
        this.possuiValorParaImpressao = true;
    }

    /**
     * Método utilitário que
     * verifica se dada variável
     * realmente existe no escopo atual
     * (tabela atual). Se existir retorna
     * true, caso contrário retorna false.
     * @param variavel String
     * @param escopoAtual Tabela
     * @return true | false Boolean
     */
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

    /**
     * Método utilitário que itera
     * sobre uma tabela e retorna a linha
     * onde dada variável se encontra.
     * Retorna null, caso não exista.
     * @param variavel String
     * @param tabela Tabela
     * @return linha::Linha | null
     */
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

    /**
     * Método utilitário que itera
     * sobre a pilha e retorna a
     * tabela (escopo) onde dada
     * variável se encontra.
     * Retorna null caso dada
     * variável não exista em
     * nenhum escopo da pilha.
     * @param nomeDaVariavel String
     * @return tabela::Tabela | null
     */
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

    /**
     * Método utilitário que salva
     * na tabela uma variável e seu
     * valor, no momento de criação
     * da variável. Caso a variável
     * não possua valor, será salvo
     * valores padrões definidos pela
     * linguagem.
     * @param nomeDaVariavel String
     * @param valorDaVariavel String
     * @param tipoDaVariavel String
     */
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

    /**
     * Método utilitário que
     * atualiza uma única linha da
     * tabela atual (escopo atual)
     * com o novo valor de uma variável.
     * @param indice Integer
     * @param valorDaVariavel String
     */
    private void atualizarLinhaDaTabela(Integer indice, String valorDaVariavel) {
        this.tabelaController.atualizarLinha(
            indice,
            null,
            null,
            valorDaVariavel
        );
    }

    /**
     * Método utilitário usado para
     * salvar na tabela atual (escopo atual)
     * o valor de uma variável. Este método
     * é usado somente após a criação de
     * dada variável.
     * @param variavel String
     * @param valor String
     */
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

    /**
     * Método utilitário que
     * itera sobre a linha lida,
     * reonhece e retira as variáveis.
     * Então as salva em um dicionário
     * e retorna este dicionário.
     * Caso a linha seja uma abertura/fechamento
     * de bloco, ou seja um comando para impressão
     * o método irá retornar null, pois é usado
     * somente para a manipulação das variáveis.
     * @param linhaLida String
     * @return dicionarioDeVariaveis::HashMap<String, String> | null
     */
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
        } while(!parteQueInteressa.isEmpty());

        return hashmap;
    }

    /**
     * Método utilitário que
     * verifica se na linha lida
     * possui uma operação matemática.
     * Retorna true se houver e retorna
     * false caso contrário.
     * @param linhaLida String
     * @return true | false Boolean
     */
    private Boolean possuiOperacao(final String linhaLida) {
        return linhaLida.contains(dicionarioDeTokens.get("atribuicao"));
    }

    /**
     * Método príncipal e mais importante
     * do projeto. Ele recebe a linha
     * que foi lida do aquivo e faz
     * a ligação com todos os outros
     * métodos desta classe.
     * É a partir deste método que
     * são verificados os:
     * - erros;
     * - variáveis e seus valores;
     * - abertura/fechamento de escopo;
     * - comando para impressão.
     * @param linhaLida String
     */
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
