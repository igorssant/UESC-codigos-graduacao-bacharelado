package jogo;

import controller.AtacanteController;
import controller.DefensorController;
import model.Atacante;
import model.Defensor;

public class Jogo {
    private AtacanteController atacanteController;
    private DefensorController defensorController;
    private static Jogo instanciaDoJogo;

    private Jogo() {
        atacanteController = new AtacanteController();
        defensorController = new DefensorController();
    }

    private Jogo(Boolean turnoDoAtacante, Boolean turnoDoDefensor) {
        atacanteController = new AtacanteController(new Atacante(turnoDoAtacante));
        defensorController = new DefensorController(new Defensor(turnoDoDefensor));
    }

    private Jogo(
        Short pontosDeVidaGeral,
        Boolean turnoDoAtacante,
        Boolean turnoDoDefensor
    ) {
        atacanteController = new AtacanteController(new Atacante(pontosDeVidaGeral, turnoDoAtacante));
        defensorController = new DefensorController(new Defensor(pontosDeVidaGeral, turnoDoDefensor));
    }

    private Jogo(
        Short vidaDoAtacante,
        Boolean turnoDoAtacante,
        Short vidaDoDefensor,
        Boolean turnoDoDefensor
    ) {
        atacanteController = new AtacanteController(new Atacante(vidaDoAtacante, turnoDoAtacante));
        defensorController = new DefensorController(new Defensor(vidaDoDefensor, turnoDoDefensor));
    }

    public static Jogo getInstance() {
        if(instanciaDoJogo == null) {
            instanciaDoJogo = new Jogo();
        }

        return instanciaDoJogo;
    }

    public static Jogo getInstance(Boolean turnoDoAtacante, Boolean turnoDoDefensor) {
        if(instanciaDoJogo == null) {
            instanciaDoJogo = new Jogo(turnoDoAtacante, turnoDoDefensor);
        }

        return instanciaDoJogo;
    }

    public static Jogo getInstance(
        Short pontosDeVidaGeral,
        Boolean turnoDoAtacante,
        Boolean turnoDoDefensor
    ) {
        if(instanciaDoJogo == null) {
            instanciaDoJogo = new Jogo(pontosDeVidaGeral, turnoDoAtacante, turnoDoDefensor);
        }

        return instanciaDoJogo;
    }

    public static Jogo getInstance(
        Short vidaDoAtacante,
        Boolean turnoDoAtacante,
        Short vidaDoDefensor,
        Boolean turnoDoDefensor
    ) {
        if(instanciaDoJogo == null) {
            instanciaDoJogo = new Jogo(vidaDoAtacante, turnoDoAtacante, vidaDoDefensor, turnoDoDefensor);
        }

        return instanciaDoJogo;
    }

    public void realizarNovoTurno() {
        // TODO
    }

}
