package model;

/**
 * @author igorsssantana
 */
public class Integral{
    private double limiteInferior;
    private double limiteSuperior;
    private double h;
    private double integralCalculada;
    private int intervalo;

    /**
     * construtor de classe vazio
     * nao recebe nenhum parametro
     */
    public Integral(){}
    
    /**
     * @param intervalo 
     */
    public Integral(int intervalo){
        this.intervalo = intervalo;
    }

    /**
     * @param limiteInferior
     * @param limiteSuperior 
     */
    public Integral(double limiteInferior, double limiteSuperior){
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
    }

    /**
     * @param limiteInferior
     * @param limiteSuperior
     * @param intervalo 
     */
    public Integral(double limiteInferior, double limiteSuperior, int intervalo){
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.intervalo = intervalo;
    }
    
    /**
     * @param limiteInferior
     * @param limiteSuperior
     * @param h
     * @param intervalo 
     */
    public Integral(double limiteInferior, double limiteSuperior, double h, int intervalo){
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.h = h;
        this.intervalo = intervalo;
    }

    public double getLimiteInferior(){
        return this.limiteInferior;
    }

    public void setLimiteInferior(double limiteInferior){
        this.limiteInferior = limiteInferior;
    }

    public double getLimiteSuperior(){
        return this.limiteSuperior;
    }

    public void setLimiteSuperior(double limiteSuperior){
        this.limiteSuperior = limiteSuperior;
    }

    public int getIntervalo(){
        return this.intervalo;
    }

    public void setIntervalo(int intervalo){
        this.intervalo = intervalo;
    }

    public double getH(){
        return this.h;
    }

    public void setH(double h){
        this.h = h;
    }

    public double getIntegralCalculada(){
        return this.integralCalculada;
    }

    public void setIntegralCalculada(double integralCalculada){
        this.integralCalculada = integralCalculada;
    }
    
    public void calculaNovoH(){
        this.h = (this.limiteSuperior - this.limiteInferior) / (double) this.intervalo;
    }
}