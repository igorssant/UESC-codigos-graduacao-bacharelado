package com.analizadorSintatico.automato;

import com.analizadorSintatico.erro.ErrosNoCodigoFonte;
import com.analizadorSintatico.estado.Estado;
import static com.analizadorSintatico.palavrasReservadas.PalavrasReservadas.palavrasReservadas;
import static com.analizadorSintatico.token.Token.mapaDeTokens;
import static com.analizadorSintatico.expressaoRegular.ExpressaoRegular.listaDeRegex;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;

public class Automato {
    private final Estado estado;
    private String nomeDoToken;
    private String valorDoToken;
    private Boolean tokenPossuiValor;
    private final ErrosNoCodigoFonte errosNoCodigoFonte;

    /**
     * Contrutor de classe vazio
     * Ele inicializa as variaveis
     * - valorDoToken String,
     * - nomeDoToken String,
     * - estado Estado,
     * - ErrosNoCodigoFonte ArrayList(Erro);
     */
    public Automato() {
        this.valorDoToken = "";
        this.nomeDoToken = "";
        this.estado = new Estado();
        errosNoCodigoFonte = new ErrosNoCodigoFonte();
    }

    /**
     * Contrutor de classe que
     * recebe um parametro:
     * @param arquivoDeErros String
     * Além disso ele inicializa as variaveis:
     * - valorDoToken String,
     * - nomeDoToken String,
     * - ErrosNoCodigoFonte ArrayList(Erro);
     */
    public Automato(String arquivoDeErros) {
        this.valorDoToken = "";
        this.nomeDoToken = "";
        this.estado = new Estado();
        errosNoCodigoFonte = new ErrosNoCodigoFonte(arquivoDeErros);
    }

    /**
     * Metodo getter para retornar o
     * nome chave do token, ou seja,
     * ele retorna o token cujo valor
     * é representado pelo que ele
     * lê no arquivo de entrada .cic
     * @return nomeDoToken String
     */
    public String getNomeDoToken() {
        return this.nomeDoToken;
    }

    /**
     * Metodo getter para retornar o estado
     * onde o automato se encontra atualmente
     * @return estadoAtual Estado
     */
    public Integer getEstadoAtual() {
        return this.estado.getEstadoAtual();
    }

    /**
     * Metodo getter para retornar o
     * valor do token, ou seja,
     * ele retorna o que foi
     * lido no arquivo de entrada .cic
     * @return valorDoToken String
     */
    public String getValorDoToken() {
        return this.valorDoToken;
    }

    /**
     * Metodo getter para retornar
     * se o token a ser escrito no arquivo
     * de saida é único ou possui vários valores
     * @return tokenPossuiValor Boolean
     */
    public Boolean getTokenPossuiValor() {
        return this.tokenPossuiValor;
    }

    /**
     * Metodo principal do automato
     * ele recebe o caracterer lido do
     * arquivo de entrada e processa
     * para verificar qual será o próximo
     * estado que o automato irá
     * @param caractereAtual String
     */
    public void lerCaractere(String caractereAtual) throws FileNotFoundException {
        Integer estadoAtual = this.estado.getEstadoAtual();
        Matcher matcher = null;

        this.estado.adicionarTokenAoConteudoDaLinha(caractereAtual);

        switch(estadoAtual) {
            case 0:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(21).matcher(caractereAtual)).matches()) { /* LEU UM ESPACO EM BRANCO */
                    this.estado.setLerNovoCaractere(true);
                    break;
                }

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 3);
                } else if ((matcher = listaDeRegex.get(1).matcher(caractereAtual)).matches()) { /* LEU UM PONTO */
                    mudarDeEstado(caractereAtual, 35);
                } else if ((matcher = listaDeRegex.get(2).matcher(caractereAtual)).matches()) { /* LEU ASPAS */
                    mudarDeEstado(caractereAtual, 1);
                    this.estado.setLerNovoCaractere(true);
                } else if ((matcher = listaDeRegex.get(5).matcher(caractereAtual)).matches()) { /* LEU LETRA MINUSCULA */
                    mudarDeEstado(caractereAtual, 36);
                } else if ((matcher = listaDeRegex.get(27).matcher(caractereAtual)).matches()) { /* LEU LETRA MAIUSCULA ENTRE [A-F] */
                    mudarDeEstado(caractereAtual, 14);
                } else if ((matcher = listaDeRegex.get(7).matcher(caractereAtual)).matches()) { /* LEU TRALHA '#' */
                    mudarDeEstado(caractereAtual, 18);
                } else if ((matcher = listaDeRegex.get(8).matcher(caractereAtual)).matches()) { /* LEU MENOR QUE */
                    mudarDeEstado(caractereAtual, 20);
                } else if ((matcher = listaDeRegex.get(9).matcher(caractereAtual)).matches()) { /* LEU MAIOR QUE */
                    mudarDeEstado(caractereAtual, 52);
                } else if ((matcher = listaDeRegex.get(10).matcher(caractereAtual)).matches()) { /* LEU SINAL DE MENOS */
                    mudarDeEstado(caractereAtual, 58);
                    this.estado.setLerNovoCaractere(false);
                } else if ((matcher = listaDeRegex.get(11).matcher(caractereAtual)).matches()) { /* LEU SINAL DE MAIS */
                    mudarDeEstado(caractereAtual, 57);
                    this.estado.setLerNovoCaractere(false);
                } else if ((matcher = listaDeRegex.get(12).matcher(caractereAtual)).matches()) { /* LEU ASTERISCO */
                    mudarDeEstado(caractereAtual, 59);
                    this.estado.setLerNovoCaractere(false);
                } else if ((matcher = listaDeRegex.get(13).matcher(caractereAtual)).matches()) { /* LEU SINAL DE PORCENTAGEM */
                    mudarDeEstado(caractereAtual, 60);
                    this.estado.setLerNovoCaractere(false);
                } else if ((matcher = listaDeRegex.get(14).matcher(caractereAtual)).matches()) { /* LEU SINAL DE IGUAL */
                    mudarDeEstado(caractereAtual, 49);
                } else if ((matcher = listaDeRegex.get(15).matcher(caractereAtual)).matches()) { /* LEU SIMBOLO PIPE '|' */
                    mudarDeEstado(caractereAtual, 61);
                    this.estado.setLerNovoCaractere(false);
                } else if ((matcher = listaDeRegex.get(16).matcher(caractereAtual)).matches()) { /* LEU 'E' COMERCIAL */
                    mudarDeEstado(caractereAtual, 62);
                    this.estado.setLerNovoCaractere(false);
                } else if ((matcher = listaDeRegex.get(17).matcher(caractereAtual)).matches()) { /* LEU ACENTO TIL */
                    mudarDeEstado(caractereAtual, 56);
                    this.estado.setLerNovoCaractere(false);
                } else if ((matcher = listaDeRegex.get(18).matcher(caractereAtual)).matches()) { /* LEU PONTO E VIRGULA */
                    mudarDeEstado(caractereAtual, 75);
                    this.estado.setLerNovoCaractere(false);
                } else if ((matcher = listaDeRegex.get(19).matcher(caractereAtual)).matches()) { /* LEU DOIS PONTOS */
                    mudarDeEstado(caractereAtual, 73);
                    this.estado.setLerNovoCaractere(false);
                } else if ((matcher = listaDeRegex.get(22).matcher(caractereAtual)).matches()) { /* LEU ABRE PARENTESE */
                    mudarDeEstado(caractereAtual, 77);
                    this.estado.setLerNovoCaractere(false);
                } else if ((matcher = listaDeRegex.get(23).matcher(caractereAtual)).matches()) { /* LEU FECHA PARENTESE */
                    mudarDeEstado(caractereAtual, 79);
                    this.estado.setLerNovoCaractere(false);
                } else if((matcher = listaDeRegex.get(20).matcher(caractereAtual)).matches()) { /* LEU UM CARACTERE '\n' */
                    this.estado.incrementarLinha();
                    this.estado.resetarColuna();
                    this.estado.setEstadoEhDeAceitacaoComoVerdade();
                    manterEstado("\n");
                    this.estado.setLerNovoCaractere(true);
                    this.estado.limparConteudoDaLinha();
                } else if ((matcher = listaDeRegex.get(26).matcher(caractereAtual)).matches()) { /* FINALIZANDO O PROGRAMA LENDO 'EOF' */
                    this.mudarDeEstado("", 47);
                    this.estado.setLerNovoCaractere(false);
                } else {
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Um token inesperado foi lido."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(true);
                }

                break;

            case 1:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(20).matcher(caractereAtual)).matches()) { /* LEU UM CARACTERE '\n' */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Cadeia não fechada."
                    );
                    esquecerTokenAtual();
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                } else if (
                    !(matcher = listaDeRegex.get(2).matcher(caractereAtual)).matches()
                ) {                                                             /* NAO LEU UM CARACTERE ASPAS */
                    manterEstado(caractereAtual);
                } else {                                                        /* LEU UM CARACTERE ASPAS */
                    mudarDeEstado(caractereAtual, 2);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 2:
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = true;
                this.nomeDoToken = mapaDeTokens.get("string");
                this.estado.setLerNovoCaractere(true);
                break;

            case 3:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 6);
                } else if((matcher = listaDeRegex.get(1).matcher(caractereAtual)).matches()) { /* LEU UM PONTO */
                    mudarDeEstado(caractereAtual, 10);
                } else if ((matcher = listaDeRegex.get(24).matcher(caractereAtual)).matches()) {  /* LEU O CARACTERE 'x' */
                    mudarDeEstado(caractereAtual, 15);
                } else {                                                                        /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado("", 8);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 4:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 10);
                } else {                                                                /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado("", 9);
                    this.estado.setLerNovoCaractere(false);
                }
                break;

            case 5:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 7);
                } else if ((matcher = listaDeRegex.get(1).matcher(caractereAtual)).matches()) { /* LEU UM PONTO */
                    mudarDeEstado(caractereAtual, 10);
                } else {                                                                /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado("", 8);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 6:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 5);
                } else if ((matcher = listaDeRegex.get(1).matcher(caractereAtual)).matches()) { /* LEU UM PONTO */
                    mudarDeEstado(caractereAtual, 10);
                } else if ((matcher = listaDeRegex.get(4).matcher(caractereAtual)).matches()) { /* LEU UM UNDERSCORE */
                    mudarDeEstado(caractereAtual, 24);
                } else if ((matcher = listaDeRegex.get(3).matcher(caractereAtual)).matches()) { /* LEU UMA BARRA */
                    mudarDeEstado(caractereAtual, 29);
                } else {                                                                /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado("", 8);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 7:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    manterEstado(caractereAtual);
                } else {                                                                /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado("", 8);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 8:
                this.nomeDoToken = mapaDeTokens.get("int");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = true;
                break;

            case 9:
                this.nomeDoToken = mapaDeTokens.get("float");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = true;
                break;

            case 10:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    manterEstado(caractereAtual);
                } else if ((matcher = listaDeRegex.get(25).matcher(caractereAtual)).matches()) { /* LEU O CARACTERE 'e' */
                    mudarDeEstado(caractereAtual, 11);
                } else {                                                                /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado("", 9);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 11:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(10).matcher(caractereAtual)).matches()) { /* LEU UM MENOS */
                    mudarDeEstado(caractereAtual, 12);
                } else {                                                                /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado(caractereAtual, 13);
                }

                break;

            case 12:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 13);
                }

                break;

            case 13:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    manterEstado(caractereAtual);
                } else {
                    mudarDeEstado("", 9);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 14:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(24).matcher(caractereAtual)).matches()) {  /* LEU O CARACTERE 'x' */
                    mudarDeEstado(caractereAtual, 15);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Formato incorreto para um endereço de memória."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 15:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) {  /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 16);
                } else if ((matcher = listaDeRegex.get(27).matcher(caractereAtual)).matches()) {              /* LEU UMA LETRA MAIUSCULA ENTRE [A-F] */
                    mudarDeEstado(caractereAtual, 16);
                } else {                                                    /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Formato incorreto para um endereço de memória."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 16:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) {  /* LEU UM NUMERO */
                    manterEstado(caractereAtual);
                } else if ((matcher = listaDeRegex.get(27).matcher(caractereAtual)).matches()) {              /* LEU UMA LETRA MAIUSCULA ENTRE [A-F] */
                    manterEstado(caractereAtual);
                } else {
                    mudarDeEstado(caractereAtual, 17);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 17:
                this.nomeDoToken = mapaDeTokens.get("endereco");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = true;
                this.estado.setLerNovoCaractere(false);
                break;

            case 18:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if(!(matcher = listaDeRegex.get(20).matcher(caractereAtual)).matches()) { /* LEU QUALQUER COISA EXCETO '\n' */
                    manterEstado(caractereAtual);
                } else {
                    mudarDeEstado(caractereAtual, 19);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 19:
                this.nomeDoToken = mapaDeTokens.get("comentarioMonolinha");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 20:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(8).matcher(caractereAtual)).matches()) { /* LEU MENOR QUE */
                    mudarDeEstado(caractereAtual, 21);
                } else if((matcher = listaDeRegex.get(9).matcher(caractereAtual)).matches()) { /* LEU MAIOR QUE */
                    mudarDeEstado(caractereAtual, 44);
                    this.estado.setLerNovoCaractere(false);
                } else if((matcher = listaDeRegex.get(14).matcher(caractereAtual)).matches()) { /* LEU SINAL DE IGUAL */
                    mudarDeEstado(caractereAtual, 42);
                } else {                                                                /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado(caractereAtual, 43);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 21:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(8).matcher(caractereAtual)).matches()) { /* LEU MENOR QUE */
                    mudarDeEstado(caractereAtual, 22);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Má formação de um comentário multilinha."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 22:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(9).matcher(caractereAtual)).matches()) { /* LEU MAIOR QUE */
                    mudarDeEstado(caractereAtual, 23);
                } else {                                                               /* LEU QUALQUER OUTRA COISA */
                    manterEstado(caractereAtual);
                }

                break;

            case 23:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(9).matcher(caractereAtual)).matches()) { /* LEU MAIOR QUE */
                    mudarDeEstado(caractereAtual, 70);
                } else  {                                                               /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado(caractereAtual, 22);
                }
                break;

            case 24:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 25);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                      "Data mal formatada. Um número é esperado."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 25:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 26);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Data mal formatada. Um número é esperado."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 26:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(4).matcher(caractereAtual)).matches()) { /* LEU O CARACTERE UNDERSCORE */
                    mudarDeEstado(caractereAtual, 27);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Data mal formatada. Um ' _ ' é esperado."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 27:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 28);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Data mal formatada. Um número é esperado."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 28:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 32);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Data mal formatada. Um número é esperado."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 29:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 30);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Data mal formatada. Um número é esperado."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 30:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 31);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Data mal formatada. Um número é esperado."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 31:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(3).matcher(caractereAtual)).matches()) { /* LEU UMA BARRA ' / ' */
                    mudarDeEstado(caractereAtual, 27);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Data mal formatada. Um ' / ' é esperado."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 32:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 33);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Data mal formatada. Um número é esperado."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 33:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 34);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Data mal formatada. Um número é esperado."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 34:
                this.nomeDoToken = mapaDeTokens.get("data");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = true;
                this.estado.setLerNovoCaractere(true);
                break;

            case 35:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 4);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Formato incorreto de ponto flutuante. Um número é esperado."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 36:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(5).matcher(caractereAtual)).matches()) { /* LEU UMA LETRA MINUSCULA */
                    mudarDeEstado(caractereAtual, 38);
                } else if ((matcher = listaDeRegex.get(6).matcher(caractereAtual)).matches()) { /* LEU UMA LETRA MAIUSCULA */
                    mudarDeEstado(caractereAtual, 37);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Formação incorreta de uma variável/palavra reservada. Só letras são esperadas"
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 37:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(5).matcher(caractereAtual)).matches()) { /* LEU UMA LETRA MINUSCULA */
                    mudarDeEstado(caractereAtual, 40);
                } else {                                                            /* QUALQUER OUTRA COISA */
                    mudarDeEstado("", 41);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 38:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if(
                    (matcher = listaDeRegex.get(5).matcher(caractereAtual)).matches() /* LEU UMA LETRA MINUSCULA OU */
                    ||
                    (matcher = listaDeRegex.get(4).matcher(caractereAtual)).matches() /* LEU UM UNDERSCORE '_' */
                ) {
                    manterEstado(caractereAtual);
                } else {                                                            /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado("", 39);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 39:
                if(palavrasReservadas.contains(this.valorDoToken)) {    /* VERIFICANDO SE A PALAVRA RESERVADA EXISTE */
                    this.nomeDoToken = mapaDeTokens.get("palavraReservada");
                    this.estado.setEstadoEhDeAceitacaoComoVerdade();
                    this.tokenPossuiValor = true;
                } else {                                             /* PALAVRA RESERVADA ESCRITA DE FORMA INCORRETA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Palavra reservada não encontrada."
                    );
                    voltarAoEstadoInicial();
                    esquecerTokenAtual();
                }

                break;

            case 40:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(6).matcher(caractereAtual)).matches()) { /* LEU UMA LETRA MAIUSCULA */
                    mudarDeEstado(caractereAtual, 37);
                } else {                                                            /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado("", 41);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 41:
                this.nomeDoToken = mapaDeTokens.get("identificador");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = true;
                this.estado.setLerNovoCaractere(false);
                break;

            case 42:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(14).matcher(caractereAtual)).matches()) { /* LEU UM SINAL DE IGUAL */
                    mudarDeEstado(caractereAtual, 45);
                } else {                                                                /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado(caractereAtual, 46);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 43:
                this.nomeDoToken = mapaDeTokens.get("menorQue");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                break;

            case 44:
                this.nomeDoToken = mapaDeTokens.get("diferenteDe");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 45:
                this.nomeDoToken = mapaDeTokens.get("atribuicao");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 46:
                this.nomeDoToken = mapaDeTokens.get("menorOuIgual");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                break;

            case 47:
                this.estado.setEstadoAtual(-1);
                this.errosNoCodigoFonte.gerarRelatorioDeErros();
                break;

            case 49:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(14).matcher(caractereAtual)).matches()) { /* LEU UM SINAL DE IGUAL */
                    mudarDeEstado(caractereAtual, 50);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    this.errosNoCodigoFonte.adicionarErro(
                        this.estado.getColuna(),
                        this.estado.getLinha(),
                        "Operador lógico não existente."
                    );
                    voltarAoEstadoInicial();
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 50:
                this.nomeDoToken = mapaDeTokens.get("igualA");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 51:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(9).matcher(caractereAtual)).matches()) { /* LEU UM SINAL DE MAIOR QUE */
                    mudarDeEstado(caractereAtual, 71);
                    this.estado.setLerNovoCaractere(false);
                } else {                                                          /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado(caractereAtual, 22);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 52:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(14).matcher(caractereAtual)).matches()) { /* LEU UM SINAL DE IGUAL */
                    mudarDeEstado(caractereAtual, 53);
                } else {                                                        /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado(caractereAtual, 55);
                }

                break;

            case 53:
                this.nomeDoToken = mapaDeTokens.get("maiorOuIgual");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 55:
                this.nomeDoToken = mapaDeTokens.get("maiorQue");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 56:
                this.nomeDoToken = mapaDeTokens.get("not");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 57:
                this.nomeDoToken = mapaDeTokens.get("mais");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 58:
                this.nomeDoToken = mapaDeTokens.get("menos");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 59:
                this.nomeDoToken = mapaDeTokens.get("multiplicacao");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 60:
                this.nomeDoToken = mapaDeTokens.get("divisao");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 61:
                this.nomeDoToken = mapaDeTokens.get("or");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 62:
                this.nomeDoToken = mapaDeTokens.get("and");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 70:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(9).matcher(caractereAtual)).matches()) { /* LEU UM SINAL DE MAIOR QUE */
                    mudarDeEstado(caractereAtual, 51);
                    this.estado.setLerNovoCaractere(false);
                } else {                                                          /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado(caractereAtual, 22);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 71:
                this.nomeDoToken = mapaDeTokens.get("comentarioMultilinha");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                this.estado.incrementarLinha();
                this.estado.incrementarLinha();
                this.estado.incrementarLinha();
                break;

            case 73:
                this.nomeDoToken = mapaDeTokens.get("doisPontos");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 75:
                this.nomeDoToken = mapaDeTokens.get("pontoVirgula");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 77:
                this.nomeDoToken = mapaDeTokens.get("abreParentese");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            case 79:
                this.nomeDoToken = mapaDeTokens.get("fechaParentese");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
                break;

            default:
                System.err.println("Não existe tal estado!\nEstado: q" + estadoAtual + ".");
                System.exit(-1);
        }

        this.estado.incrementarColuna();
    }

    /**
     * Metodo getter para retornar
     * em qual coluna o caractere
     * atual está
     * @return colunaAtual Integer
     */
    public Integer getColunaAtual() {
        return this.estado.getColuna();
    }

    /**
     * Metodo getter para retornar
     * em qual linha o caractere
     * atual está
     * @return linhaAtual Integer
     */
    public Integer getLinhaAtual() {
        return this.estado.getLinha();
    }

    /**
     * Metodo de controle para
     * evitar que o automato leia um novo
     * token e mude de estado
     * Ele serve para travar a leitura
     * quanto um token lido faz
     * com que o automato mude de estado
     * para outro estado de aceitação (principalmente)
     * @param caractereAtual String
     */
    private void manterEstado(String caractereAtual) {
        this.estado.setCaractereAtual(caractereAtual);
        this.valorDoToken = this.valorDoToken.concat(caractereAtual);
    }

    /**
     * Metodo de controle para
     * forçar o automato a ler o
     * token atual, processá-lo
     * e passar para o próximo
     * estado
     * @param caractereAtual String
     * @param novoEstado Integer
     */
    private void mudarDeEstado(String caractereAtual, Integer novoEstado) {
        this.estado.setEstadoAtual(novoEstado);
        this.estado.setCaractereAtual(caractereAtual);
        this.valorDoToken = this.valorDoToken.concat(caractereAtual);
    }

    /**
     * Metodo utilitário
     * para limpar a "memoria"
     * do automato
     * Ele é mais usado quando o automato
     * atinge um estado de aceitação e
     * volta ao estado inicial
     */
    public void esquecerTokenAtual() {
        this.nomeDoToken = "";
        this.valorDoToken = "";
    }

    /**
     * Metodo de controle para
     * evitar que o automato passe
     * batido por um estado de aceitação
     * e não retorne ao estado inicial
     * @return estadoEhAceitacao Boolean
     */
    public Boolean estadoEhAceitacao() {
        Boolean estadoTemporario = false;

        if(this.estado.getEstadoEhDeAceitacao()) {
            estadoTemporario = true;
            this.estado.inverteEstadoDeAceitacao();
        }

        return estadoTemporario;
    }

    /**
     * Metodo de controler para
     * forçar o automato a ler
     * um novo caractere do arquivo
     * de entrada .cic
     * @return lerNovoCaractere? Boolean
     */
    public boolean lerNovoCaractere() {
        return this.estado.getLerNovoCaractere();
    }

    /**
     * Metodo utilitário para
     * forçar o automato a
     * retornar ao estado inicial
     * Ele é utilizado em estados finais
     */
    public void voltarAoEstadoInicial() {
        this.estado.voltarAoEstadoInicial();
    }
}
