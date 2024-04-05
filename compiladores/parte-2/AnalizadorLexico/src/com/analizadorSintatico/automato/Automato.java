package com.analizadorSintatico.automato;

import com.analizadorSintatico.erro.UnexpectedTokenException;
import com.analizadorSintatico.estado.Estado;

import static com.analizadorSintatico.palavrasReservadas.PalavrasReservadas.palavrasReservadas;
import static com.analizadorSintatico.token.Token.mapaDeTokens;
import static com.analizadorSintatico.expressaoRegular.ExpressaoRegular.listaDeRegex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Automato {
    private Estado estado;
    private String nomeDoToken;
    private String valorDoToken;
    private Boolean tokenPossuiValor;

    public Automato() {
        this.valorDoToken = "";
        this.nomeDoToken = "";
        this.estado = new Estado();
    }

    public Automato(String caractereAtual) {
        this.valorDoToken = "";
        this.nomeDoToken = "";
        this.estado = new Estado(caractereAtual);
    }

    public Integer getUltimoEstado() {
        return Estado.ultimoEstado;
    }

    public String getNomeDoToken() {
        return this.nomeDoToken;
    }

    public Integer getEstadoAtual() {
        return this.estado.getEstadoAtual();
    }

    public String getValorDoToken() {
        return this.valorDoToken;
    }

    public Boolean getTokenPossuiValor() {
        return this.tokenPossuiValor;
    }

    public void lerCaractere(String caractereAtual) {
        Integer estadoAtual = this.estado.getEstadoAtual();
        Pattern regexParaHexadecimal = Pattern.compile("^[A-F]$");
        Matcher matcherParaHexadecimal = regexParaHexadecimal.matcher(caractereAtual),
            matcher = null;

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
                } else if ((matcher = listaDeRegex.get(5).matcher(caractereAtual)).matches()) { /* LEU LETRA MINUSCULA */
                    mudarDeEstado(caractereAtual, 36);
                } else if (matcherParaHexadecimal.matches()) {                         /* LEU LETRA MAIUSCULA ENTRE [A-F] */
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
                    System.exit(0);
                } else {
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 1:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if(!(matcher = listaDeRegex.get(2).matcher(caractereAtual)).matches()) {
                    manterEstado(caractereAtual);
                } else {
                    mudarDeEstado("", 2);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 2:
                this.nomeDoToken = mapaDeTokens.get("string");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = true;
                break;

            case 3:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 6);
                } else if((matcher = listaDeRegex.get(1).matcher(caractereAtual)).matches()) { /* LEU UM PONTO */
                    mudarDeEstado(caractereAtual, 4);
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
                    mudarDeEstado(caractereAtual, 4);
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
                    mudarDeEstado(caractereAtual, 4);
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
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 15:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) {  /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 16);
                } else if (matcherParaHexadecimal.matches()) {              /* LEU UMA LETRA MAIUSCULA ENTRE [A-F] */
                    mudarDeEstado(caractereAtual, 16);
                } else {                                                    /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 16:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) {  /* LEU UM NUMERO */
                    manterEstado(caractereAtual);
                } else if (matcherParaHexadecimal.matches()) {              /* LEU UMA LETRA MAIUSCULA ENTRE [A-F] */
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
                this.estado.setLerNovoCaractere(true);
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
                esquecerTokenAtual();
                this.nomeDoToken = mapaDeTokens.get("comentarioMonoLinha");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = true;
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
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 22:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(9).matcher(caractereAtual)).matches()) { /* LEU MAIOR QUE */
                    mudarDeEstado(caractereAtual, 23);
                } else  {                                                               /* LEU QUALQUER OUTRA COISA */
                    manterEstado(caractereAtual);
                }

                break;

            case 23:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(9).matcher(caractereAtual)).matches()) { /* LEU MAIOR QUE */
                    mudarDeEstado(caractereAtual, 23);
                } else  {                                                               /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado(caractereAtual, 22);
                }
                break;

            case 24:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 25);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 25:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 26);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 26:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(4).matcher(caractereAtual)).matches()) { /* LEU O CARACTERE UNDERSCORE */
                    mudarDeEstado(caractereAtual, 27);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 27:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 28);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 28:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 32);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 29:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 30);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 30:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 31);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 31:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 27);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 32:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 33);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 33:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 34);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
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
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 36:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(5).matcher(caractereAtual)).matches()) { /* LEU UMA LETRA MINUSCULA */
                    mudarDeEstado(caractereAtual, 38);
                } else if ((matcher = listaDeRegex.get(6).matcher(caractereAtual)).matches()) { /* LEU UMA LETRA MAIUSCULA */
                    mudarDeEstado(caractereAtual, 37);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
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

                if((matcher = listaDeRegex.get(5).matcher(caractereAtual)).matches()) { /* LEU UMA LETRA MINUSCULA */
                    manterEstado(caractereAtual);
                } else {                                                            /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado(caractereAtual, 39);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 39:
                if(palavrasReservadas.contains(this.valorDoToken)) {        /* VERIFICANDO SE A PALAVRA RESERVADA EXISTE */
                    this.nomeDoToken = mapaDeTokens.get("palavraReservada");
                    this.estado.setEstadoEhDeAceitacaoComoVerdade();
                } else {                                                    /* PALAVRA RESERVADA ESCRITA DE FORMA INCORRETA */
                    throw new RuntimeException("Não existe tal comando");
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
                this.estado.setLerNovoCaractere(true);
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

            case 49:
                this.estado.setEstadoEhDeAceitacaoComoFalso();

                if((matcher = listaDeRegex.get(14).matcher(caractereAtual)).matches()) { /* LEU UM SINAL DE IGUAL */
                    mudarDeEstado(caractereAtual, 50);
                } else {                                                              /* LEU QUALQUER OUTRA COISA */
                    throw new UnexpectedTokenException(
                        this.estado.getLinha(),
                        this.estado.getColuna(),
                        this.estado.getConteudoDaLinha()
                    );
                }

                break;

            case 50:
                this.nomeDoToken = mapaDeTokens.get("igualA");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = false;
                this.estado.setLerNovoCaractere(true);
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
                    mudarDeEstado(caractereAtual, 71);
                    this.estado.setLerNovoCaractere(false);
                } else {                                                          /* LEU QUALQUER OUTRA COISA */
                    mudarDeEstado(caractereAtual, 22);
                    this.estado.setLerNovoCaractere(false);
                }

                break;

            case 71:
                esquecerTokenAtual();
                this.nomeDoToken = mapaDeTokens.get("comentarioMultilinha");
                this.estado.setEstadoEhDeAceitacaoComoVerdade();
                this.tokenPossuiValor = true;
                this.estado.setLerNovoCaractere(true);
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

    public Integer getColunaAtual() {
        return this.estado.getColuna();
    }

    public Integer getLinhaAtual() {
        return this.estado.getLinha();
    }

    private void manterEstado(String caractereAtual) {
        this.estado.setCaractereAtual(caractereAtual);
        this.valorDoToken = this.valorDoToken.concat(caractereAtual);
    }

    private void mudarDeEstado(String caractereAtual, Integer novoEstado) {
        this.estado.setEstadoAtual(novoEstado);
        this.estado.setCaractereAtual(caractereAtual);
        this.valorDoToken = this.valorDoToken.concat(caractereAtual);
    }

    public void esquecerTokenAtual() {
        this.nomeDoToken = "";
        this.valorDoToken = "";
    }

    public Boolean estadoEhAceitacao() {
        Boolean estadoTemporario = false;

        if(this.estado.getEstadoEhDeAceitacao()) {
            estadoTemporario = true;
            this.estado.inverteEstadoDeAceitacao();
        }

        return estadoTemporario;
    }

    public boolean lerNovoCaractere() {
        return this.estado.getLerNovoCaractere();
    }

    public void voltarAoEstadoInicial() {
        this.estado.voltarAoEstadoInicial();
    }
}
