package com.gomoku.model;

public class PecaModel {
    private String cor;

    public PecaModel() {
        this.cor = "PRETO";
    }

    public PecaModel(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return this.cor;
    }
}
