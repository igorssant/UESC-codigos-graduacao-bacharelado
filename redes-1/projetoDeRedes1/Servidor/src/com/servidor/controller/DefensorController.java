package com.servidor.controller;

import com.servidor.model.Defensor;

public class DefensorController {
    private Defensor defensor;

    public DefensorController() {
        this.defensor = new Defensor();
    }

    public DefensorController(Defensor defensor) {
        this.defensor = defensor;
    }

    public Short getPontosDeVida() {
        return this.defensor.getPontosDeVida();
    }

    public Short getUltimaAcao() {
        return this.defensor.getUltimaAcao();
    }

    public void sofreuDano() {
        this.defensor.sofreuDano();
    }

    public void realizarAcao(Short acao) {
        this.defensor.realizarAcao(acao);
    }

    public Boolean perdeu() {
        return this.defensor.perdeu();
    }
}
