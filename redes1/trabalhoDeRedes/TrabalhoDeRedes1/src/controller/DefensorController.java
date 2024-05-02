package controller;

import model.Defensor;

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

    public Boolean getEhMeuTurno() {
        return this.defensor.getEhMeuTurno();
    }

    public Short getUltimaAcao() {
        return this.defensor.getUltimaAcao();
    }

    public void sofreuDano() {
        this.defensor.sofreuDano();
    }

    public void realizarAcao(Short acao) {
        if(!this.defensor.getEhMeuTurno()) {
            return;
        }

        this.defensor.realizarAcao(acao);
    }

    public void esperaTurnoAdversario() {
        this.defensor.esperaTurnoAdversario();
    }

    public void meuTurno() {
        this.defensor.meuTurno();
    }
}
