/**
 * @author igorsssantana
 */
package model;

import model.interfaces.ModeloMatematico;

public class MetodoSecante extends ModeloMatematico{
    private double xK;
    private double xKMais1;
    private double fxK;
    private double fxKMais1;
    private double tolerancia;
    private double erro;
    
    /**
     * Construtor de classe vazio.
     * Nao recebe nenhum parametro.
     */
    public MetodoSecante(){}
    
    /**
     * Construtor de classe.
     * Recebe como parametros
     * x_k-1 (x_0) e a
     * tolerancia permitida.
     * @param xK
     * @param tolerancia 
     */
    public MetodoSecante(double xK, double tolerancia){
        this.xK = xK;
        this.tolerancia = tolerancia;
    }
    
    /**
     * Construtor de classe.
     * Recebe como parametros
     * x_k-1 (x_0), x_k (x_1)
     * e a tolerancia permitida.
     * @param xK
     * @param xKMais1
     * @param tolerancia 
     */
    public MetodoSecante(double xK, double xKMais1, double tolerancia){
        this.xK = xK;
        this.xKMais1 = xKMais1;
        this.tolerancia = tolerancia;
    }
    
    /**
     * Construtor de classe.
     * Recebe como parametros
     * x_k-1 (x_0), x_k (x_1),
     * f(x_0), f(x_1) e
     * a tolerancia permitida.
     * @param xK
     * @param xKMais1
     * @param fxK
     * @param fxKMais1
     * @param tolerancia 
     */
    public MetodoSecante(double xK, double xKMais1, double fxK, double fxKMais1, double tolerancia){
        this.xK = xK;
        this.xKMais1 = xKMais1;
        this.fxK = fxK;
        this.fxKMais1 = fxKMais1;
        this.tolerancia = tolerancia;
    }
    
    public double getxK(){
        return this.xK;
    }

    public void setxK(double xK){
        this.xK = xK;
    }

    public double getxKMais1(){
        return this.xKMais1;
    }

    public void setxKMais1(double xKMais1){
        this.xKMais1 = xKMais1;
    }

    public double getFxK(){
        return this.fxK;
    }

    public void setFxK(double fxK){
        this.fxK = fxK;
    }

    public double getFxKMais1(){
        return this.fxKMais1;
    }

    public void setFxKMais1(double fxKMais1){
        this.fxKMais1 = fxKMais1;
    }

    public double getTolerancia(){
        return this.tolerancia;
    }

    public void setTolerancia(double tolerancia){
        this.tolerancia = tolerancia;
    }

    public double getErro(){
        return this.erro;
    }

    public void setErro(double erro){
        this.erro = erro;
    }
    
    /**
     * Metodo gerarNovoXKMaisUm.
     * Nao recebe nenhum parametro.
     * Nao retorna nada.
     * Gera um novo x_K+2 a partir
     * de x_k, e X_k+1.
     */
    @Override
    public void gerarNovoXKMaisUm(){
        double auxiliar;
        
        auxiliar = ( (this.fxKMais1 * this.xK) - (this.fxK * this.xKMais1) ) / ( this.fxKMais1 - this.fxK );
        this.xK = this.xKMais1;
        this.xKMais1 = auxiliar;
    }

    /**
     * Metodo gerarErro
     * Nao recebe nenhum parametro.
     * Nao retorna nada.
     * Gera um novo erro absoluto
     * a partir de x_k+1 e x_k.
     */
    @Override
    public void gerarErro(){
        this.erro = Math.abs( (this.xKMais1 - this.xK) / this.xK );
    }
}