package com.cliente.controller;

import com.cliente.enums.Acoes;
import com.cliente.model.Atacante;

public class AtacanteController {
    private Atacante atacante;

    public AtacanteController() {
        this.atacante = new Atacante();
    }

    public AtacanteController(Atacante atacante) {
        this.atacante = atacante;
    }

    public Short getPontosDeVida() {
        return this.atacante.getPontosDeVida();
    }

    public Short getUltimaAcao() {
        return this.atacante.getUltimaAcao();
    }

    public void sofreuDano() {
        this.atacante.sofreuDano();
    }

    public void realizarAcao(Short acao) {
        assert acao != null;

        if(Acoes.contemValor(acao)) {
            this.atacante.realizarAcao(acao);
        }
    }

    public Boolean perdeu() {
        return this.atacante.perdeu();
    }
}
