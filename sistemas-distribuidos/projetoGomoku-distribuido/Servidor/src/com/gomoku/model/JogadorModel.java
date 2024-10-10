package com.gomoku.model;

import java.lang.RuntimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JogadorModel {
    private Boolean turno;
    private Peca peca;
    private static final List<String> CONJUNTO_DE_CORES = new ArrayList<>(
        Arrays.asList("BRANCO", "PRETO")
    );

    public JogadorModel() {
        this.turno = false;
    }

    public JogadorModel(Boolean turno) {
        this.turno = turno;
    }

    public Boolean getTurno() {
        return this.turno;
    }

    public String getCorPeca() {
        return this.peca.getCor();
    }

    public static List<String> getConjuntoDeCoresPossiveis() {
        return CONJUNTO_DE_CORES;
    }

    public void setTurno(Boolean turno) {
        this.turno = turno;
    }

    public void escolherPeca() {
        this.peca = new Peca();
    }

    public void escolherPeca(String cor) throws RuntimeException {
        cor = cor.toUpperCase();

        if(CONJUNTO_DE_CORES.contains(cor)) {
            this.peca = new Peca(cor);
        } else {
            throw new RuntimeException("Cor Inválida");
        }
    }
}
