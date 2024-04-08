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

    /**
     * Construtor vazio de classe
     * Usado para inicializar as variaveis:
     * - caractereAtual String
     * - estadoAtual Integer
     * - linha Integer
     * - coluna Integer
     * - estadoEhDeAceitacao? Boolean
     * - lerNovoCaractere? Boolean
     * - conteudoDaLinha String
     */
    public Estado() {
        this.caractereAtual = this.ultimoCaractereLido = null;
        this.estadoAtual = 0;
        this.linha = this.coluna = 1;
        this.estadoEhDeAceitacao = false;
        this.lerNovoCaractere = true;
        this.conteudoDaLinha = null;
    }

    /**
     * Construtor de classe que
     * recebe a variável
     * @param caractereAtual String
     * e inicializa as variaveis:
     * - caractereAtual String
     * - estadoAtual Integer
     * - linha Integer
     * - coluna Integer
     * - estadoEhDeAceitacao? Boolean
     * - lerNovoCaractere? Boolean
     * - conteudoDaLinha String
     */
    public Estado(String caractereAtual) {
        this.ultimoCaractereLido = null;
        this.caractereAtual = caractereAtual;
        this.estadoAtual = 0;
        this.linha = this.coluna = 1;
        this.estadoEhDeAceitacao = false;
        this.lerNovoCaractere = true;
        this.conteudoDaLinha = null;
    }

    /**
     * Metodo setter para forçar
     * o ultimo caractere lido,
     * que engatilhou a mudança
     * para o estado atual, a
     * ser salvo
     * @param ultimoCaractereLido String
     */
    public void setUltimoCaractereLido(String ultimoCaractereLido) {
        this.ultimoCaractereLido = ultimoCaractereLido;
    }

    /**
     * Metodo setter para forçar
     * o caractere ainda não lido,
     * que irá engatilhar a mudança
     * para próximo, a er salvo
     * @param caractereAtual String
     */
    public void setCaractereAtual(String caractereAtual) {
        this.ultimoCaractereLido = this.caractereAtual;
        this.caractereAtual = caractereAtual;
    }

    /**
     * Metodo setter para forçar
     * um estado ser salvo
     * @param estadoAtual Integer
     */
    public void setEstadoAtual(Integer estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    /**
     * Metodo setter sem parametros
     * de entrada
     * Ele força o automato
     * a entender o estado atual
     * como VERDADE
     */
    public void setEstadoEhDeAceitacaoComoVerdade() {
        this.estadoEhDeAceitacao = true;
    }

    /**
     * Metodo setter sem parametros
     * de entrada
     * Ele força o automato
     * a entender o estado atual
     * como FALSO
     */
    public void setEstadoEhDeAceitacaoComoFalso() {
        this.estadoEhDeAceitacao = false;
    }

    /**
     * Metodo setter para receber
     * um novo caractere e salva-lo
     * @param lerNovoCaractere Boolean
     */
    public void setLerNovoCaractere(Boolean lerNovoCaractere) {
        this.lerNovoCaractere = lerNovoCaractere;
    }

    /**
     * Metodo getter para
     * retornar o estado atual do
     * automato
     * @return estadoAtual Integer
     */
    public Integer getEstadoAtual() {
        return this.estadoAtual;
    }

    /**
     * Metodo getter para
     * retornar o ultimo caractere
     * lido pelo automato
     * @return ultimoCaracterLido String
     */
    public String getUltimoCaractereLido() {
        return this.ultimoCaractereLido;
    }

    /**
     * Metodo getter para
     * retornar o caractere atual
     * que foi lido pelo arquivo
     * de entrda .cic
     * @return caractereAtual String
     */
    public String getCaractereAtual() {
        return this.caractereAtual;
    }

    /**
     * Metodo getter para
     * retornar a coluna atual
     * onde se encontra o cursor
     * @return coluna Integer
     */
    public Integer getColuna() {
        return this.coluna;
    }

    /**
     * Metodo getter para
     * retornar a linha atual
     * onde se encontra o cursor
     * @return linha Integer
     */
    public Integer getLinha() {
        return this.linha;
    }

    /**
     * Metodo getter para
     * retornar se o estado
     * atual é, de fato, um
     * estado de aceitação
     * @return estadoEhDeAceitacao? Boolean
     */
    public Boolean getEstadoEhDeAceitacao() {
        return this.estadoEhDeAceitacao;
    }

    /**
     * Metodo getter para
     * retornar se o automato
     * deve, ou não, ler
     * um novo caractere do
     * arquivo .cic
     * @return lerNovoCaractere? Boolean
     */
    public Boolean getLerNovoCaractere() {
        return lerNovoCaractere;
    }

    /**
     * Metodo getter para
     * retornar o conteudo completo
     * lido de uma única linha
     * É usado somente para tratamento
     * de erros
     * @return conteudoDaLinha String
     */
    public String getConteudoDaLinha() {
        return conteudoDaLinha;
    }

    /**
     * Metodo usado para
     * inverter a variavel
     * estadoDeAceitacao?
     * Se estiver com o valor
     * true, ficará com o valor
     * false, e vice-versa
     */
    public void inverteEstadoDeAceitacao() {
        this.estadoEhDeAceitacao = !this.estadoEhDeAceitacao;
    }

    /**
     * Metodo usado para
     * inverter a variavel
     * lerNovoCaractere?
     * Se estiver com o valor
     * true, ficará com o valor
     * false, e vice-versa
     */
    public void  inverteLerNovoCaractere() {
        this.lerNovoCaractere = !lerNovoCaractere;
    }

    /**
     * Metodo usado para
     * retornar o cursor para
     * a coluna 1, ou seja,
     * o inicio da linha
     */
    public void resetarColuna() {
        this.coluna = 1;
    }

    /**
     * Metodo usado para
     * retornar o cursor para
     * a linha 1, ou seja,
     * o inicio do arquivo
     */
    public void resetarLinha() {
        this.linha = 1;
    }

    /**
     * Metodo usado para
     * incremetar, em 1,
     * o valor da variavel
     * coluna
     */
    public void incrementarColuna() {
        this.coluna++;
    }

    /**
     * Metodo usado para
     * incremetar, em 1,
     * o valor da variavel
     * linha
     */
    public void incrementarLinha() {
        this.linha++;
    }

    /**
     * Metodo usado para
     * resetar os estados
     * do automato, ou seja,
     * retornar ao estado q0
     */
    public void voltarAoEstadoInicial() {
        this.estadoAtual = 0;
    }

    /**
     * Metodo setter para
     * adicionar mais um
     * caractere à variável
     * conteudoDaLinha
     * Usado para tratamento
     * de erros
     * @param token
     */
    public void adicionarTokenAoConteudoDaLinha(String token) {
        this.conteudoDaLinha = this.caractereAtual.concat(token);
    }

    /**
     * Metodo setter para
     * limpar o conteudo
     * salvo na variavel
     * conteudoDaLinha
     * Usado para resetar a
     * variavel e prepara-la
     * para ler o conteudo da
     * proxima linha do
     * arquivo de entrada .cic
     */
    public void limparConteudoDaLinha() {
        this.conteudoDaLinha = "";
    }
}
