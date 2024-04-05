package com.analizadorSintatico.estado;

public class Estado {
    public static Integer ultimoEstado;
    private Integer estadoAtual;
    private String ultimoCaractereLido;
    private String caractereAtual;
    private Integer linha;
    private Integer coluna;
    private Boolean estadoEhDeAceitacao;
    private Boolean lerNovoCaractere;
    private String conteudoDaLinha;

    public Estado() {
        this.caractereAtual = this.ultimoCaractereLido = null;
        this.estadoAtual = 0;
        this.linha = this.coluna = 1;
        this.estadoEhDeAceitacao = false;
        this.lerNovoCaractere = true;
        this.conteudoDaLinha = null;
    }

    public Estado(String caractereAtual) {
        this.ultimoCaractereLido = null;
        this.caractereAtual = caractereAtual;
        this.estadoAtual = 0;
        this.linha = this.coluna = 1;
        this.estadoEhDeAceitacao = false;
        this.lerNovoCaractere = true;
        this.conteudoDaLinha = null;
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

    public void setEstadoEhDeAceitacaoComoVerdade() {
        this.estadoEhDeAceitacao = true;
    }

    public void setEstadoEhDeAceitacaoComoFalso() {
        this.estadoEhDeAceitacao = false;
    }

    public void setLerNovoCaractere(Boolean lerNovoCaractere) {
        this.lerNovoCaractere = lerNovoCaractere;
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

    public Integer getColuna() {
        return this.coluna;
    }

    public Integer getLinha() {
        return this.linha;
    }

    public Boolean getEstadoEhDeAceitacao() {
        return this.estadoEhDeAceitacao;
    }

    public Boolean getLerNovoCaractere() {
        return lerNovoCaractere;
    }

    public String getConteudoDaLinha() {
        return conteudoDaLinha;
    }

    public void inverteEstadoDeAceitacao() {
        this.estadoEhDeAceitacao = !this.estadoEhDeAceitacao;
    }

    public void  inverteLerNovoCaractere() {
        this.lerNovoCaractere = !lerNovoCaractere;
    }

    public void resetarColuna() {
        this.coluna = 1;
    }

    public void resetarLinha() {
        this.linha = 1;
    }

    public void incrementarColuna() {
        this.coluna++;
    }

    public void incrementarLinha() {
        this.linha++;
    }

    public void voltarAoEstadoInicial() {
        this.estadoAtual = 0;
    }

    public void adicionarTokenAoConteudoDaLinha(String token) {
        this.conteudoDaLinha = this.caractereAtual.concat(token);
    }

    public void limparConteudoDaLinha() {
        this.conteudoDaLinha = "";
    }
}
