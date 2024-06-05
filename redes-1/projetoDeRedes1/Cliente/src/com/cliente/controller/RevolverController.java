package com.cliente.controller;

import com.cliente.model.Revolver;

public class RevolverController {
    private final Revolver revolver;

    public RevolverController() {
        this.revolver = new Revolver();
    }

    public Boolean revolverVazio() {
        return this.revolver.revolverVazio();
    }

    public void adicionarBala() {
        if(this.revolver.getQuantidadeDeBalasAtual() < Revolver.MAXIMO_DE_BALAS) {
            this.revolver.adicionarBala();
        }
    }

    public Boolean disparar() {
        if(!revolverVazio()) {
            this.revolver.disparar();
            return true;
        }

        return false;
    }

    public Short getQuantidadeDeBalasAtual() {
        return this.revolver.getQuantidadeDeBalasAtual();
    }
}
