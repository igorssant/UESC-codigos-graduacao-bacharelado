package com.cliente.model;

public class Revolver {
    private Short quantidadeDeBalasAtual;
    public static final Short MAXIMO_DE_BALAS = 6;

    public Revolver() {
        this.quantidadeDeBalasAtual = 0;
    }

    public Short getQuantidadeDeBalasAtual() {
        return quantidadeDeBalasAtual;
    }

    public Boolean revolverVazio() {
        return this.quantidadeDeBalasAtual == 0;
    }

    public void adicionarBala() {
        this.quantidadeDeBalasAtual++;
    }

    public void disparar() {
        this.quantidadeDeBalasAtual--;
    }
}
