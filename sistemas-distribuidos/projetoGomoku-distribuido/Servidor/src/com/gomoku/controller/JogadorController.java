package com.gomoku.controller;

import com.gomoku.model.JogadorModel;
import java.util.List;

public class JogadorController {
    private JogadorModel jogadorModel;

    public JogadorController() {
        this.jogadorModel = new JogadorModel();
        escolherPeca();
    }

    public JogadorController(String cor) {
        this.jogadorModel = new JogadorModel();
        escolherPeca(cor);
    }

    public JogadorController(Boolean turno) {
        this.jogadorModel = new JogadorModel(turno);
        escolherPeca();
    }

    public JogadorController(String cor, Boolean turno) {
        this.jogadorModel = new JogadorModel(turno);
        escolherPeca(cor);
    }

    public Boolean getTurno() {
        return this.jogadorModel.getTurno();
    }

    public String getCorPeca() {
        return this.jogadorModel.getCorPeca();
    }

    public static List<String> getConjuntoDeCoresPossiveis() {
        return JogadorModel.getConjuntoDeCoresPossiveis();
    }

    public void setTurno(Boolean turno) {
        this.jogadorModel.setTurno(turno);
    }

    public void escolherPeca() {
        this.jogadorModel.escolherPeca();
    }

    public void escolherPeca(String cor) throws RuntimeException {
        this.jogadorModel.escolherPeca(cor);
    }
}
