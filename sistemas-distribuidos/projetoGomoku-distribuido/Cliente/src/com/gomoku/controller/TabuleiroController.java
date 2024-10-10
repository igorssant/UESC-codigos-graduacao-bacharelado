package com.gomoku.controller;

import com.gomoku.model.TabuleiroModel;
import java.lang.RuntimeException;

public class TabuleiroController {
    private TabuleiroModel tabuleiroModel;

    public TabuleiroController() {
        this.tabuleiroModel = new TabuleiroModel();
    }

    public Boolean tabuleiroVazio() {
        return this.tabuleiroModel.tabuleiroVazio();
    }

    public Short[][] getTabuleiro() {
        return this.tabuleiroModel.getTabuleiro();
    }

    public void setTabuleiro(Short[][] tabuleiro) {
        this.tabuleiroModel.setTabuleiro(tabuleiro);
    }

    public Boolean tabuleiroLotado() {
        return this.tabuleiroModel.tabuleiroLotado();
    }

    public static Short getNumeroDeIntersecoes() {
        return TabuleiroModel.getNumeroDeIntersecoes();
    }

    public void atualizarTabuleiro(Short linha, Short coluna, Short jogadorQueJogou) throws RuntimeException {
        this.tabuleiroModel.atualizarTabuleiro(linha, coluna, jogadorQueJogou);
    }

    public void limparTabuleiro() {
        this.tabuleiroModel.limparTabuleiro();
    }
}
