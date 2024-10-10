package com.gomoku.model;

import java.lang.RuntimeException;

public class TabuleiroModel {
    private Short[][] tabuleiro = new Short[15][15];
    private static final Short NUMERO_INTERSECOES = 15;
    private Boolean tabuleiroVazio = true;
    private Boolean tabuleiroLotado = false;

    public TabuleiroModel() {
        for(short i = 0; i < NUMERO_INTERSECOES; i++) {
            for(short j = 0; j < NUMERO_INTERSECOES; j++) {
                this.tabuleiro[i][j] = 0;
            }
        }
    }

    public static Short getNumeroDeIntersecoes() {
        return NUMERO_INTERSECOES;
    }

    public Short[][] getTabuleiro() {
        return this.tabuleiro;
    }

    public void setTabuleiro(Short[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public Boolean tabuleiroVazio() {
        return this.tabuleiroVazio;
    }

    public Boolean tabuleiroLotado() {
        return this.tabuleiroLotado;
    }

    public void atualizarTabuleiro(Short linha, Short coluna, Short jogadorQueJogou) throws RuntimeException {
        if(tabuleiroVazio) {
            tabuleiroVazio = false;
        }

        if(!tabuleiroLotado) {
            this.tabuleiro[linha][coluna] = jogadorQueJogou;

            if(tabuleiroEstaLotado()) {
                this.tabuleiroLotado = true;
            }
        } else {
            throw new RuntimeException("O tabuleiro está lotado.");
        }
    }

    public void limparTabuleiro() {
        for(short i = 0; i < NUMERO_INTERSECOES; i++) {
            for(short j = 0; j < NUMERO_INTERSECOES; j++) {
                this.tabuleiro[i][j] = 0;
            }
        }
    }

    private Boolean tabuleiroEstaLotado() {
        Short quantidadeTotalDeCasas = (short) Math.pow((NUMERO_INTERSECOES - 1), 2),
            contador = 0;

        for(short i = 0; i < NUMERO_INTERSECOES; i++) {
            for(short j = 0; j < NUMERO_INTERSECOES; j++) {
               if(this.tabuleiro[i][j] != 0) {
                   contador++;
               }
            }
        }

        return contador.equals(quantidadeTotalDeCasas);
    }
}
