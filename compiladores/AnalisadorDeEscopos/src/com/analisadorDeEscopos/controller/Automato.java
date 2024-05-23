package com.analisadorDeEscopos.controller;

import com.analisadorDeEscopos.model.Estado;
import java.util.regex.Matcher;
import static com.analisadorDeEscopos.expressaoRegular.ExpressaoRegular.listaDeRegex;
import static com.analisadorDeEscopos.palavrasReservadas.PalavrasReservadas.listaDePalavrasReservadas;
import static com.analisadorDeEscopos.token.Token.dicionarioDeTokens;

/*
 * CONSIDERE ESTA CLASSE
 * COMO SENDO UMA CLASSE
 * CONTROLLER PARA A CLASSE
 * (MODEL) Estado
 */
public class Automato {
    private final Estado estado;
    private String nomeToken;
    private String valorToken;
    private Boolean lerNovoCaractere;

    /**
     * Construtor vazio de classe.
     * Usado para inicializar
     * as variáveis:
     * - estado Estado
     * - nomeToken String
     * - valorToken String
     */
    public Automato() {
        this.estado = new Estado();
        this.nomeToken = "";
        this.valorToken = "";
        this.lerNovoCaractere = true;
    }

    /**
     * Construtor de classe que
     * recebe como parâmetros
     * nomeToken e valorToken.
     * Além disso, inicializa
     * a variável estado.
     * @param nomeToken String
     * @param valorToken String
     */
    public Automato(String nomeToken, String valorToken) {
        this.estado = new Estado();
        this.nomeToken = nomeToken;
        this.valorToken = valorToken;
        this.lerNovoCaractere = true;
    }

    /**
     * Construtor completo de
     * classe. Recebe todas as
     * variáveis como parâmetros.
     * @param estado Estado
     * @param nomeToken String
     * @param valorToken String
     */
    public Automato(Estado estado, String nomeToken, String valorToken) {
        this.estado = estado;
        this.nomeToken = nomeToken;
        this.valorToken = valorToken;
        this.lerNovoCaractere = true;
    }

    /**
     * Método getter usado
     * para retornar a classe
     * Estado com suas variáveis.
     * @return estado Estado
     */
    public Estado getEstado() {
        return this.estado;
    }

    /**
     * Método getter usado
     * para retornar o nome
     * do token formado.
     * @return nomeToken String
     */
    public String getNomeToken() {
        return this.nomeToken;
    }

    /**
     * Método getter usado
     * para retornar o valor
     * do token formado.
     * @return valorToken String
     */
    public String getValorToken() {
        return this.valorToken;
    }

    /**
     * Método getter usado
     * para retornar o valor
     * booleano da variável
     * lerNovoCaractere.
     * @return lerNovoCaractere Boolean
     */
    public Boolean getLerNovoCaractere() {
        return this.lerNovoCaractere;
    }

    /**
     * Método getter usado para
     * retornar o estado atual.
     * @return estadoAtual Integer
     */
    public Integer getEstadoAtual() {
        return this.estado.getEstadoAtual();
    }

    /**
     * Método setter usado para
     * forçar o retorno ao estado
     * inicial, ou seja, o estado
     * q0.
     */
    public void retornarAoEstadoInicial() {
        this.estado.retornarAoEstadoInicial();
    }

    /**
     * Método getter usado para
     * retornar o caractere que
     * acabou de ser lido.
     * @return caractereAtual String
     */
    public String getCaractereAtual() {
        return this.estado.getCaractereAtual();
    }

    /**
     * Método setter usado para
     * enviar o novo caractere que
     * deve ser lido.
     * @param caractereAtual String
     */
    public void setCaractereAtual(String caractereAtual) {
        this.estado.setCaractereAtual(caractereAtual);
    }

    /**
     * Método getter usado para
     * retornar a linha atual
     * do arquivo onde o cursor
     * se encontra.
     * @return linhaAtual Integer
     */
    public Integer getLinha() {
        return this.estado.getLinha();
    }

    /**
     * Método setter usado para
     * forçar o cursor a retornar
     * a linha 1 do arquivo de
     * entrada.
     */
    public void rewind() {
        this.estado.rewind();
    }

    /**
     * Método setter usado para
     * forçar o cursor a retornar
     * avançar uma linha no
     * arquivo está sendo lido.
     */
    public void avancarLinha() {
        this.estado.avancarLinha();
    }

    /**
     * Método getter usado para
     * retornar a variável
     * estadoEhFinal.
     * @return estadoEhFinal Boolean
     */
    public Boolean getEstadoEhFinal() {
        return this.estado.getEstadoEhFinal();
    }

    /**
     * Metodo utilitário para limpar
     * a "memória" do automato.
     * Ele é mais usado quando o automato
     * atinge um estado de aceitação e
     * volta ao estado inicial.
     */
    public void esquecerTokenAtual() {
        this.nomeToken = "";
        this.valorToken = "";
    }

    /**
     * Metodo de controle para
     * evitar que o automato leia um novo
     * token e mude de estado.
     * Ele serve para travar a leitura
     * quanto um token lido faz
     * com que o automato mude de estado
     * para outro estado de aceitação (principalmente).
     * @param caractereAtual String
     */
    private void manterEstado(String caractereAtual) {
        this.valorToken = this.valorToken.concat(caractereAtual);
    }

    /**
     * Metodo de controle para
     * forçar o automato a ler o
     * token atual, processá-lo
     * e passar para o próximo
     * estado.
     * @param caractereAtual String
     * @param novoEstado Integer
     */
    private void mudarDeEstado(String caractereAtual, Integer novoEstado) {
        this.estado.setEstadoAtual(novoEstado);
        this.valorToken = this.valorToken.concat(caractereAtual);
    }

    /**
     * Método utilitário usado para
     * salvar o nome do token baseado
     * no dicionário dicionarioDeTokens.
     * @param token String
     */
    private void gerarNomeDoToken(String token) {
        this.nomeToken = dicionarioDeTokens.get(token);
    }

    /**
     * Método utilitário usado para
     * salvar o nome do token baseado
     * no dicionário dicionarioDeTokens.
     * Esta variação verifica se o token
     * gerado é, de fato, uma palavra
     * reservada no conjunto de palavras
     * reservadas.
     */
    private void gerarNomeDoToken() {
        if(listaDePalavrasReservadas.contains(this.valorToken)) {
            this.nomeToken = dicionarioDeTokens.get("palavraReservada");
        } else {
            System.err.println(
                "Palavra reservada mal formada na linha " +
                this.estado.getLinha() + ".\n" +
                this.valorToken + " não existe!"
            );
        }
    }

    public void lerCaractere(String caractere) {
        Matcher matcher = null;

        this.estado.setCaractereAtual(caractere);

        switch(this.estado.getEstadoAtual()) {
            case 0: /* estado inicial do autômato */
                this.estado.estadoAtualNaoEhFinal();

                if((matcher = listaDeRegex.get(12).matcher(caractere)).matches()) {     /* Leu quebra de linha (\n) */
                    this.estado.avancarLinha();
                    this.estado.marcarEstadoAtualComoFinal();
                    this.nomeToken = "";
                    this.valorToken = caractere;
                    break;
                } else if ((matcher = listaDeRegex.get(11).matcher(caractere)).matches()) { /* leu espaço em branco */
                    this.lerNovoCaractere = true;
                    break;
                } else if((matcher = listaDeRegex.get(5).matcher(caractere)).matches()) { /* Leu aspas duplas (\") */
                    mudarDeEstado("", 5);
                } else if((matcher = listaDeRegex.get(4).matcher(caractere)).matches()) { /* Leu um número */
                    mudarDeEstado(caractere, 8);
                } else if(
                    (matcher = listaDeRegex.get(0).matcher(caractere)).matches()
                ) {                                                               /* Leu sinal de subtração (-) */
                    mudarDeEstado(caractere, 11);
                } else if((matcher = listaDeRegex.get(1).matcher(caractere)).matches()) {/* leu sinal de adição (+) */
                    mudarDeEstado(caractere, 18);
                } else if((matcher = listaDeRegex.get(7).matcher(caractere)).matches()) { /* Leu underscore (_) */
                    mudarDeEstado(caractere, 13);
                } else if((matcher = listaDeRegex.get(10).matcher(caractere)).matches()) { /* Leu letra minúscula */
                    mudarDeEstado(caractere, 16);
                } else if((matcher = listaDeRegex.get(9).matcher(caractere)).matches()) { /* Leu letra maiúscula */
                    mudarDeEstado(caractere, 1);
                } else if ((matcher = listaDeRegex.get(2).matcher(caractere)).matches()) { /* Leu sinal de igual */
                    this.lerNovoCaractere = false;
                    mudarDeEstado("", 21);
                } else if((matcher = listaDeRegex.get(14).matcher(caractere)).matches()) { /* Leu uma vírgula (,) */
                    this.lerNovoCaractere = false;
                    mudarDeEstado("", 6);
                } else if((matcher = listaDeRegex.get(13).matcher(caractere)).matches()) { /* Alcançou EOF */
                    mudarDeEstado(caractere, 20);
                } else {
                    System.err.println(
                        "Caractere lido não pertence a gramática reconhecida pelo autômato.\n" +
                        "[ " + caractere + " ] foi lido."
                    );
                }

                this.lerNovoCaractere = true;
                break; /* FIM CASE 0 */

            case 1: /* caminho palavra reservada */
                if((matcher = listaDeRegex.get(9).matcher(caractere)).matches()) { /* Leu letra maiúscula */
                    mudarDeEstado(caractere, 2);
                } else {
                    System.err.println("Erro semântico na linha " + this.estado.getLinha());
                    esquecerTokenAtual();
                    this.estado.retornarAoEstadoInicial();
                }

                break; /* FIM CASE 1 */

            case 2: /* caminho palavra reservada */
                if((matcher = listaDeRegex.get(9).matcher(caractere)).matches()) { /* Leu letra maiúscula */
                    mudarDeEstado(caractere, 3);
                } else {
                    System.err.println("Erro semântico na linha " + this.estado.getLinha());
                    esquecerTokenAtual();
                    this.estado.retornarAoEstadoInicial();
                }

                break; /* FIM CASE 2 */

            case 3: /* caminho palavra reservada */
                if(!(matcher = listaDeRegex.get(9).matcher(caractere)).matches()) { /* Não leu letra maiúscula */
                    mudarDeEstado("", 4);
                    this.lerNovoCaractere = false;
                } else {
                    manterEstado(caractere);
                }

                break; /* FIM CASE 3 */

            case 4: /* caminho palavra reservada - aceitação */
                this.estado.marcarEstadoAtualComoFinal();
                gerarNomeDoToken();
                break; /* FIM CASE 4 */

            case 5: /* caminho cadeia */
                if((matcher = listaDeRegex.get(8).matcher(caractere)).matches()) { /* Leu um caractere != \" */
                    manterEstado(caractere);
                } else {                                                           /* Leu aspas duplas */
                    mudarDeEstado("", 7);
                }

                break; /* FIM CASE 5 */

            case 6:
                this.estado.marcarEstadoAtualComoFinal();
                gerarNomeDoToken("separador");
                this.lerNovoCaractere = true;
                break; /* FIM CASE 5 */

            case 7: /* caminho cadeia - aceitação */
                this.estado.marcarEstadoAtualComoFinal();
                gerarNomeDoToken("string");
                break; /* FIM CASE 7 */

            case 8: /* caminho número */
                if((matcher = listaDeRegex.get(4).matcher(caractere)).matches()) { /* Leu um número */
                    manterEstado(caractere);
                } else if ((matcher = listaDeRegex.get(3).matcher(caractere)).matches()) { /* Leu o caractere (.) */
                    mudarDeEstado(caractere, 9);
                } else {                                                           /* Leu qualquer outra coisa */
                    mudarDeEstado("", 12);
                    this.lerNovoCaractere = false;
                }

                break; /* FIM CASE 8 */

            case 9: /* caminho número */
                if((matcher = listaDeRegex.get(4).matcher(caractere)).matches()) { /* Leu um número */
                    mudarDeEstado(caractere, 10);
                } else {                                                            /* Leu qualquer outra coisa */
                    System.err.println("Número mal formado na linha " + this.estado.getLinha());
                    esquecerTokenAtual();
                    this.estado.retornarAoEstadoInicial();
                }

                break; /* FIM CASE 9 */

            case 10: /* caminho número */
                if((matcher = listaDeRegex.get(4).matcher(caractere)).matches()) { /* Leu um número */
                    manterEstado(caractere);
                } else {                                                           /* Leu qualquer outra coisa */
                    mudarDeEstado("", 12);
                    this.lerNovoCaractere = false;
                }

                break; /* FIM CASE 10 */

            case 11: /* caminho número */
                if((matcher = listaDeRegex.get(4).matcher(caractere)).matches()) { /* Leu um número */
                    mudarDeEstado(caractere, 8);
                } else {                                                           /* Leu qualquer outra coisa */
                    System.err.println("Número mal formado na linha " + this.estado.getLinha());
                    esquecerTokenAtual();
                    this.estado.retornarAoEstadoInicial();
                }

                break; /* FIM CASE 11 */

            case 12: /* caminho número - aceitação */
                this.estado.marcarEstadoAtualComoFinal();
                gerarNomeDoToken("numero");
                break; /* FIM CASE 12 */

            case 13: /* caminho identificador de bloco */
                if(
                    (matcher = listaDeRegex.get(4).matcher(caractere)).matches() ||
                    (matcher = listaDeRegex.get(10).matcher(caractere)).matches()
                ) {                                             /* Leu um número ou leu uma letra minúscula */
                    mudarDeEstado(caractere, 14);
                } else {                                                        /* Leu qualquer outra coisa */
                    System.err.println("Palavra reservada mal formada na linha " + this.estado.getLinha());
                    esquecerTokenAtual();
                    this.estado.retornarAoEstadoInicial();
                }

                break; /* FIM CASE 13 */

            case 14: /* caminho identificador de bloco */
                if(
                    (matcher = listaDeRegex.get(4).matcher(caractere)).matches() ||
                    (matcher = listaDeRegex.get(10).matcher(caractere)).matches()
                ) {                                             /* Leu um número ou leu uma letra minúscula */
                    manterEstado(caractere);
                } else if((matcher = listaDeRegex.get(7).matcher(caractere)).matches()) { /* Leu underscore (_) */
                    mudarDeEstado(caractere, 15);
                    this.lerNovoCaractere = false;
                } else {                                                        /* Leu qualquer outra coisa */
                    System.err.println("Palavra reservada mal formada na linha " + this.estado.getLinha());
                    esquecerTokenAtual();
                    this.estado.retornarAoEstadoInicial();
                }

                break; /* FIM CASE 14 */

            case 15: /* caminho identificador de bloco - aceitação */
                this.estado.marcarEstadoAtualComoFinal();
                gerarNomeDoToken("bloco");
                break; /* FIM CASE 15 */

            case 16: /* caminho identificador de variável */
                if(
                    (matcher = listaDeRegex.get(4).matcher(caractere)).matches() ||
                    (matcher = listaDeRegex.get(7).matcher(caractere)).matches() ||
                    (matcher = listaDeRegex.get(10).matcher(caractere)).matches()
                ) {                         /* Leu um número ou leu underscore (_) ou leu uma letra minúscula */
                    manterEstado(caractere);
                } else {
                    mudarDeEstado("", 17);
                    this.lerNovoCaractere = false;
                }

                break; /* FIM CASE 16 */

            case 17: /* caminho identificador de variável - aceitação */
                this.estado.marcarEstadoAtualComoFinal();
                gerarNomeDoToken("identificador");
                break; /* FIM CASE 17 */

            case 18: /* caminho número OU caminho soma */
                if((matcher = listaDeRegex.get(4).matcher(caractere)).matches()) {  /* Leu um número */
                    mudarDeEstado(caractere, 8);
                } else {
                    mudarDeEstado("", 19);
                    this.lerNovoCaractere = false;
                }

                break; /* FIM CASE 18 */

            case 19: /* caminho soma - aceitação */
                this.estado.marcarEstadoAtualComoFinal();
                gerarNomeDoToken("soma");
                break; /* FIM CASE 19 */

            case 20: /* Final do arquivo foi alcançado */
                mudarDeEstado("", -1);
                this.lerNovoCaractere = false;
                break; /* FIM CASE 20 */

            case 21:
                this.lerNovoCaractere = false;
                this.estado.marcarEstadoAtualComoFinal();
                gerarNomeDoToken("atribuicao");
                break; /* FIM CASE 21 */

            default:
                System.err.println("Estado não pertence ao autômato!!!\n[ " + this.estado.getEstadoAtual() + "]\n");
        }
    }
}
