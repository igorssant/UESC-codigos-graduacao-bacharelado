package com.analizadorSintatico.estado;

public class Estado {
    public static Integer ultimoEstado;
    private Integer estadoAtual;
    private Character ultimoCaractereLido;
    private Character caractereAtual;

    public Estado() {
        this.caractereAtual = null;
        this.ultimoCaractereLido = null;
        this.estadoAtual = 0;
    }

    public Estado(Character caractereAtual) {
        this.ultimoCaractereLido = null;
        this.caractereAtual = caractereAtual;
        this.estadoAtual = 0;
    }

    public void setUltimoCaractereLido(Character ultimoCaractereLido) {
        this.ultimoCaractereLido = ultimoCaractereLido;
    }

    public void setCaractereAtual(Character caractereAtual) {
        this.ultimoCaractereLido = this.caractereAtual;
        this.caractereAtual = caractereAtual;
    }

    public void setEstadoAtual(Integer estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public Integer getEstadoAtual() {
        return this.estadoAtual;
    }

    public Character getUltimoCaractereLido() {
        return this.ultimoCaractereLido;
    }

    public Character getCaractereAtual() {
        return this.caractereAtual;
    }
}
