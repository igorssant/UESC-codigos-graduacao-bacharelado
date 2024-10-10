package com.gomoku.controller;

import java.lang.RuntimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Jogo {
    private Short turnoDoJogador;
    private TabuleiroController tabuleiroController;
    private List<JogadorController> jogadores;
    private Scanner scanner;
    private static final Short NUMERO_MAXIMO_JOGADORES = 2;
    private static final Short CONDICAO_VITORIA = 5;

    public Jogo() {
        this.tabuleiroController = new TabuleiroController();
        this.jogadores = new ArrayList<>(NUMERO_MAXIMO_JOGADORES);
        this.scanner =  new Scanner(System.in);
        this.turnoDoJogador = 1;
    }

    public Short[][] getTabuleiroAtualizado() {
        return this.tabuleiroController.getTabuleiro();
    }

    public Short getNumeroDeJogadoresAtual() {
        return (short) this.jogadores.size();
    }

    public Short getTurnoDoJogador() {
        return this.turnoDoJogador;
    }

    public void adicionarJogadorEmPartida() throws RuntimeException {
        Short quantidadeAtualDeJogadores = (short) this.jogadores.size();
        final List<String> cores = JogadorController.getConjuntoDeCoresPossiveis();

        if(quantidadeAtualDeJogadores < NUMERO_MAXIMO_JOGADORES + 1) {
            if(this.jogadores.isEmpty()) {
                this.jogadores.add(new JogadorController(cores.getLast()));
            } else {
                this.jogadores.add(new JogadorController(cores.getFirst()));
            }
        } else {
            throw new RuntimeException("Quantidade máxima de jogadores (2) já foi alcançado.");
        }
    }

    public void receberJogada(Short linha, Short coluna, Short jogador) {
        this.tabuleiroController.atualizarTabuleiro(linha, coluna, jogador);
        this.turnoDoJogador = (short) ((jogador == 1)? 2 : 1);
    }

    public Short verificaVitoria() {
        Boolean vitoriaX = null,
            vitoriaO = null;

        vitoriaO = verificarLinhas((short) 1);
        vitoriaO = Boolean.FALSE.equals(vitoriaO) ? verificarColunas((short) 1) : true;
        vitoriaO = Boolean.FALSE.equals(vitoriaO) ? verificarDiagonalPrimaria((short) 1) : true;
        vitoriaO = Boolean.FALSE.equals(vitoriaO) ? verificarDiagonalSecundaria((short) 1) : true;

        if(vitoriaO) {
            return 1;
        }

        vitoriaX = verificarLinhas((short) 2);
        vitoriaX = Boolean.FALSE.equals(vitoriaX) ? verificarColunas((short) 2) : true;
        vitoriaX = Boolean.FALSE.equals(vitoriaX) ? verificarDiagonalPrimaria((short) 2) : true;
        vitoriaX = Boolean.FALSE.equals(vitoriaX) ? verificarDiagonalSecundaria((short) 2) : true;

        if(vitoriaX) {
            return 2;
        }

        if(tabuleiroController.tabuleiroLotado()) {
            return 3;
        }

        return 0;
    }

    private Boolean verificarLinhas(Short valorParaProcurar) {
        final Short[][] matriz = this.tabuleiroController.getTabuleiro();
        Short contador = 0;

        for(short i = 0; i < TabuleiroController.getNumeroDeIntersecoes(); i++) {       //LINHAS
            for(short j = 0; j < TabuleiroController.getNumeroDeIntersecoes(); j++) {   // COLUNAS
                if(Objects.equals(matriz[i][j], valorParaProcurar)) {
                    contador++;
                } else {
                    contador = 0;
                }

                if(contador.equals(CONDICAO_VITORIA)) {
                    return true;
                }
            }
        }

        return false;
    }

    private Boolean verificarColunas(Short valorParaProcurar) {
        final Short[][] matriz = this.tabuleiroController.getTabuleiro();
        Short contador = 0;

        for(short i = 0; i < TabuleiroController.getNumeroDeIntersecoes(); i++) {       // COLUNAS
            for(short j = 0; j < TabuleiroController.getNumeroDeIntersecoes(); j++) {   // LINHAS
                if(Objects.equals(matriz[j][i], valorParaProcurar)) {
                    contador++;
                } else {
                    contador = 0;
                }

                if(contador.equals(CONDICAO_VITORIA)) {
                    return true;
                }
            }
        }

        return false;
    }

    private Boolean verificarDiagonalPrimaria(Short valorParaProcurar) {
        final Short[][] matriz = this.tabuleiroController.getTabuleiro();
        Short contador = 0;

        /*
         * VARRE A PRIMEIRA METADE DA MATRIZ
         * SUPERIOR
         */
        for(short colunaInicial = 0; colunaInicial < TabuleiroController.getNumeroDeIntersecoes() - 1; colunaInicial++) {
            /*
             * i -> linhas
             * j -> colunas
             */
            for(short i = 0, j = colunaInicial; j < TabuleiroController.getNumeroDeIntersecoes(); i++, j++) {
                if(Objects.equals(matriz[i][j], valorParaProcurar)) {
                    contador++;
                } else {
                    contador = 0;
                }

                if(contador.equals(CONDICAO_VITORIA)) {
                    return true;
                }
            }
        }

        /*
         * VARRE A SEGUNDA METADE DA MATRIZ
         * INFERIOR
         */
        for(short linhaInicial = 0; linhaInicial < TabuleiroController.getNumeroDeIntersecoes() - 1; linhaInicial++) {
            /*
             * i -> linhas
             * j -> colunas
             */
            for(short i = linhaInicial, j = 0; j < TabuleiroController.getNumeroDeIntersecoes(); i++, j++) {
                if(Objects.equals(matriz[i][j], valorParaProcurar)) {
                    contador++;
                } else {
                    contador = 0;
                }

                if(contador.equals(CONDICAO_VITORIA)) {
                    return true;
                }
            }
        }

        return false;
    }

    private Boolean verificarDiagonalSecundaria(Short valorParaProcurar) {
        final Short[][] matriz = this.tabuleiroController.getTabuleiro();
        Short contador = 0;

        /*
         * VARRE A PRIMEIRA METADE DA MATRIZ
         * SUPERIOR
         */
        for(short colunaInicial = (short) (TabuleiroController.getNumeroDeIntersecoes() - 1); colunaInicial > -1; colunaInicial--) {
            /*
             * i -> linhas
             * j -> colunas
             */
            for(short i = 0, j = colunaInicial; i < TabuleiroController.getNumeroDeIntersecoes(); i++, j--) {
                if(Objects.equals(matriz[i][j], valorParaProcurar)) {
                    contador++;
                } else {
                    contador = 0;
                }

                if(contador.equals(CONDICAO_VITORIA)) {
                    return true;
                }
            }
        }

        /*
         * VARRE A SEGUNDA METADE DA MATRIZ
         * INFERIOR
         */
        for(short linhaInicial = 1; linhaInicial < TabuleiroController.getNumeroDeIntersecoes() - 1; linhaInicial++) {
            /*
             * i -> linhas
             * j -> colunas
             */
            for(short i = linhaInicial, j = (short) (TabuleiroController.getNumeroDeIntersecoes() - 1); i < TabuleiroController.getNumeroDeIntersecoes(); i++, j--) {
                if(Objects.equals(matriz[i][j], valorParaProcurar)) {
                    contador++;
                } else {
                    contador = 0;
                }

                if(contador.equals(CONDICAO_VITORIA)) {
                    return true;
                }
            }
        }

        return false;
    }
}
