package model;

import interfaces.Jogador;

public class Defensor implements Jogador {
    private Short pontosDeVida;
    private Boolean ehMeuTurno;
    private Short ultimaAcao;

    public Defensor() {
        this.pontosDeVida = 3;
        this.ehMeuTurno = false;
        this.ultimaAcao = null;
    }

    public Defensor(Boolean ehMeuTurno) {
        this.pontosDeVida = 3;
        this.ehMeuTurno = ehMeuTurno;
        this.ultimaAcao = null;
    }

    public Defensor(Short pontosDeVida, Boolean ehMeuTurno) {
        this.pontosDeVida = pontosDeVida;
        this.ehMeuTurno = ehMeuTurno;
        this.ultimaAcao = null;
    }

    public Short getPontosDeVida() {
        return pontosDeVida;
    }

    public Boolean getEhMeuTurno() {
        return ehMeuTurno;
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
    public void esperaTurnoAdversario() {
        this.ehMeuTurno = false;
    }

    @Override
    public void meuTurno() {
        this.ehMeuTurno = true;
    }
}
