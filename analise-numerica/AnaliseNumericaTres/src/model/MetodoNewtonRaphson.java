/**
 * @author igorsssantana
 */
package model;

import model.interfaces.ModeloMatematico;

public class MetodoNewtonRaphson extends ModeloMatematico{
    private double xAnterior;
    private double xAtual;
    private double fxAnterior;
    private double derivadaFxAnterior;
    private double tolerancia;
    private double erro;
    
    /**
     * Contrutor de classe vazio.
     * Nao possui entrada nenhuma.
     */
    public MetodoNewtonRaphson(){}
    
    /**
     * Contrutor de classe MetodoNewtonRaphson.
     * Recebe como parametro somente o x_anterior
     * @param xAnterior 
     */
    public MetodoNewtonRaphson(double xAnterior) {
        this.xAnterior = xAnterior;
        this.xAtual = 0.0;
    }

    /**
     * Contrutor de classe MetodoNewtonRaphson.
     * Recebe como parametro x_anterior
     * e x_atual.
     * @param xAnterior
     * @param tolerancia 
     */
    public MetodoNewtonRaphson(double xAnterior, double tolerancia) {
        this.xAnterior = xAnterior;
        this.xAtual = 0.0;
        this.fxAnterior = 0.0;
        this.derivadaFxAnterior = 0.0;
        this.tolerancia = tolerancia;
    }
    
    /**
     * Contrutor de classe MetodoNewtonRaphson.
     * Recebe como parametro x_anterior,
     * x_atual e a tolerancia.
     * @param xAnterior
     * @param xAtual
     * @param tolerancia
     */
    public MetodoNewtonRaphson(double xAnterior, double xAtual, double tolerancia){
        this.xAnterior = xAnterior;
        this.xAtual = xAtual;
        this.tolerancia = tolerancia;
        this.fxAnterior = 0.0;
        this.derivadaFxAnterior = 0.0;
    }
    
    /**
     * Contrutor de classe MetodoNewtonRaphson.
     * Recebe como parametro x_anterior,
     * x_atual, a tolernacia e f(x_anterior).
     * @param xAnterior
     * @param xAtual
     * @param tolerancia
     * @param fxAnterior
     */
    public MetodoNewtonRaphson(double xAnterior, double xAtual, double tolerancia, double fxAnterior){
        this.xAnterior = xAnterior;
        this.xAtual = xAtual;
        this.tolerancia = tolerancia;
        this.fxAnterior = fxAnterior;
        this.derivadaFxAnterior = 0.0;
    }
    
    /**
     * Contrutor de classe MetodoNewtonRaphson.
     * Recebe como parametro x_anterior,
     * x_atual, a tolernacia, f(x_anterior)
     * e f'(x_anterior).
     * @param xAnterior
     * @param xAtual
     * @param fxAnterior
     * @param derivadaFxAnterior
     * @param tolerancia 
     */
    public MetodoNewtonRaphson(double xAnterior, double xAtual, double fxAnterior, double derivadaFxAnterior, double tolerancia){
        this.xAnterior = xAnterior;
        this.xAtual = xAtual;
        this.fxAnterior = fxAnterior;
        this.derivadaFxAnterior = derivadaFxAnterior;
        this.tolerancia = tolerancia;
    }

    public double getxAnterior(){
        return this.xAnterior;
    }

    public void setxAnterior(double xAnterior){
        this.xAnterior = xAnterior;
    }

    public double getxAtual(){
        return this.xAtual;
    }

    public void setxAtual(double xAtual){
        this.xAtual = xAtual;
    }
    
    public double getFxAnterior(){
        return this.fxAnterior;
    }

    public void setFxAnterior(double fxAnterior){
        this.fxAnterior = fxAnterior;
    }
    
    public double getDerivadaFxAnterior(){
        return this.derivadaFxAnterior;
    }

    public void setDerivadaFxAnterior(double derivadaFxAnterior){
        this.derivadaFxAnterior = derivadaFxAnterior;
    }

    public double getTolerancia(){
        return tolerancia;
    }

    public void setTolerancia(double tolerancia){
        this.tolerancia = tolerancia;
    }

    public double getErro() {
        return erro;
    }

    public void setErro(double erro) {
        this.erro = erro;
    }
    
    /**
     * Metodo gerarNovoXk.
     * Nao recebe nenhum parametro.
     * Nao retorna nada.
     * Gera um novo x_(k+1) a partir
     * de x_k, seu f(x) e
     * sua derivada f'(x).
     */
    @Override
    public void gerarNovoXk(){
        double auxiliar = 0.0;
        
        if(this.xAtual != 0.0){
            auxiliar = this.xAtual;
        }
        
        this.xAtual = this.xAnterior - (this.fxAnterior / this.derivadaFxAnterior);
        this.xAnterior = auxiliar;
    }
    
    /**
     * Metodo gerarErro
     * Nao recebe nenhum parametro.
     * Nao retorna nada.
     * Gera um novo erro absoluto
     * a partir de x1 e x0.
     */
    @Override
    public void gerarErro(){
        this.erro = Math.abs( (this.xAtual - this.xAnterior) / this.xAnterior );
    }
} // Fim de classe MetodoNewtonRaphson