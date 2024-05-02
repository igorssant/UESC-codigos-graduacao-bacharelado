package controller;

import model.Atacante;

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

    public Boolean getEhMeuTurno() {
        return this.atacante.getEhMeuTurno();
    }

    public Short getUltimaAcao() {
        return this.atacante.getUltimaAcao();
    }

    public void sofreuDano() {
        this.atacante.sofreuDano();
    }

    public void realizarAcao(Short acao) {
        if(!this.atacante.getEhMeuTurno()) {
            return;
        }

        this.atacante.realizarAcao(acao);
    }

    public void esperaTurnoAdversario() {
        this.atacante.esperaTurnoAdversario();
    }

    public void meuTurno() {
        this.atacante.meuTurno();
    }
}
