package com.analizadorSintatico.estado;

public class Estado {
    public static Integer ultimoEstado;
    private Integer estadoAtual;
    private String ultimoCaractereLido;
    private String caractereAtual;

    public Estado() {
        this.caractereAtual = null;
        this.ultimoCaractereLido = null;
        this.estadoAtual = 0;
    }

    public Estado(String caractereAtual) {
        this.ultimoCaractereLido = null;
        this.caractereAtual = caractereAtual;
        this.estadoAtual = 0;
    }

    public void setUltimoCaractereLido(String ultimoCaractereLido) {
        this.ultimoCaractereLido = ultimoCaractereLido;
    }

    public void setCaractereAtual(String caractereAtual) {
        this.ultimoCaractereLido = this.caractereAtual;
        this.caractereAtual = caractereAtual;
    }

    public void setEstadoAtual(Integer estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public Integer getEstadoAtual() {
        return this.estadoAtual;
    }

    public String getUltimoCaractereLido() {
        return this.ultimoCaractereLido;
    }

    public String getCaractereAtual() {
        return this.caractereAtual;
    }
}
