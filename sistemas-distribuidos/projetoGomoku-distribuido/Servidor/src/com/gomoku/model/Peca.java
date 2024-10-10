package com.gomoku.model;

public class Peca {
    private String cor;

    public Peca() {
        this.cor = "PRETO";
    }

    public Peca(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return this.cor;
    }
}
