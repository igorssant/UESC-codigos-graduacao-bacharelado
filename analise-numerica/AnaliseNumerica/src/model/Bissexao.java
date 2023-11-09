/**
 * @author igorsssantana
 */
package model;

import model.interfaces.ModeloMatematico;

public class Bissexao extends ModeloMatematico{
    private double intervaloSuperior;
    private double intervaloInferior;
    private double erro;
    private double estimativa;
    private double fAnterior;
    private double fAtual;
    private double tolerancia;
    
    /**
     * Contrutor de classe vazia.
     * Nao recebe nenhum parametro.
     */
    public Bissexao(){}
    
    /**
     * Contrutor de classe com dois parametros.
     * Recebe como parametro: Intervalo Superior
     * e Intervalo Inferior.
     * @param intervaloSuperior
     * @param intervaloInferior 
     */
    public Bissexao(double intervaloSuperior, double intervaloInferior){
        this.intervaloSuperior = intervaloSuperior;
        this.intervaloInferior = intervaloInferior;
    }
    
    /**
     * Contrutor de classe com dois parametros.
     * Recebe como parametro: Intervalo Superior,
     * Intervalo Inferior e Tolerancia/Erro.
     * @param intervaloSuperior
     * @param intervaloInferior
     * @param tolerancia
     */
    public Bissexao(double intervaloSuperior, double intervaloInferior, double tolerancia) {
        this.intervaloSuperior = intervaloSuperior;
        this.intervaloInferior = intervaloInferior;
        this.tolerancia = tolerancia;
    }
    
    /**
     * Contrutor de classe com dois parametros.
     * Recebe como parametro: Intervalo Superior,
     * Intervalo Inferior, Tolerancia/Erro e Estimativa.
     * @param intervaloSuperior
     * @param intervaloInferior
     * @param tolerancia
     * @param estimativa 
     */
    public Bissexao(double intervaloSuperior, double intervaloInferior, double tolerancia, double estimativa) {
        this.intervaloSuperior = intervaloSuperior;
        this.intervaloInferior = intervaloInferior;
        this.tolerancia = tolerancia;
        this.estimativa = estimativa;
    }
    
    public double getIntervaloSuperior(){
        return this.intervaloSuperior;
    }

    public void setIntervaloSuperior(double intervaloSuperior){
        this.intervaloSuperior = intervaloSuperior;
    }

    public double getIntervaloInferior(){
        return this.intervaloInferior;
    }

    public void setIntervaloInferior(double intervaloInferior){
        this.intervaloInferior = intervaloInferior;
    }

    public double getErro(){
        return this.erro;
    }

    public void setErro(double erro){
        this.erro = erro;
    }

    public double getEstimativa(){
        return this.estimativa;
    }

    public void setEstimativa(double estimativa){
        this.estimativa = estimativa;
    }

    public double getFAnterior(){
        return this.fAnterior;
    }

    public void setFAnterior(double fAnterior){
        this.fAnterior = fAnterior;
    }

    public double getFAtual(){
        return this.fAtual;
    }

    public void setFAtual(double fAtual){
        this.fAtual = fAtual;
    }
    
    public double getTolerancia(){
        return this.tolerancia;
    }

    public void setTolerancia(double tolerancia){
        this.tolerancia = tolerancia;
    }
    
    /**
     * Esse metodo nao retorna nenhum valor.
     * Esse metodo nao recebe nenhum parametro.
     * Quando chamado, esse metodo gera uma nova tolerancia/erro.
     */
    @Override
    public void gerarErro(){
        this.erro = ( (this.intervaloSuperior - this.intervaloInferior) / (2 * this.estimativa) ) * 100;
    }
    
    /**
     * Esse metodo nao retorna nenhum valor.
     * Esse metodo nao recebe nenhum parametro.
     * Quando chamado, esse metodo gera uma nova estimativa.
     */
    @Override
    public void gerarEstimativa(){
        this.estimativa = (this.intervaloInferior + this.intervaloSuperior) / 2;
    }
}