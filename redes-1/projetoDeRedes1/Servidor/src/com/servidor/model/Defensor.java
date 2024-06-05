package com.servidor.model;

import com.servidor.interfaces.Jogador;

public class Defensor implements Jogador {
    private Short pontosDeVida;
    private Short ultimaAcao;

    public Defensor() {
        this.pontosDeVida = 3;
        this.ultimaAcao = null;
    }

    public Defensor(Short pontosDeVida) {
        this.pontosDeVida = pontosDeVida;
        this.ultimaAcao = null;
    }

    public Short getPontosDeVida() {
        return pontosDeVida;
    }

    public Short getUltimaAcao() {
        return ultimaAcao;
    }

    public void sofreuDano() {
        this.pontosDeVida--;
    }

    @Override
    public void realizarAcao(Short acao) {
        this.ultimaAcao = acao;
    }

    @Override
    public Boolean perdeu() {
        return pontosDeVida == 0;
    }
}
