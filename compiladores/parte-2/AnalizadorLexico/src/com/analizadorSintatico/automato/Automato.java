package com.analizadorSintatico.automato;

import com.analizadorSintatico.estado.Estado;

public class Automato {
    private Estado estado;

    public Automato() {
        this.estado = new Estado();
    }

    public Automato(Character caractereAtual) {
        this.estado = new Estado(caractereAtual);
    }

    public Integer getUltimoEstado() {
        return Estado.ultimoEstado;
    }

    public Integer getEstadoAtual() {
        return this.estado.getEstadoAtual();
    }

    public void lerCaracterer(Character caractereAtual) {
        Integer estadoAtual = this.estado.getEstadoAtual();

        if(false) {

        }
    }

    private void manterEstado(Character caractereAtual) {
        this.estado.setCaractereAtual(caractereAtual);
    }

    private void mudarDeEstado(Character caractereAtual, Integer novoEstado) {
        this.estado.setEstadoAtual(novoEstado);
    }
}
