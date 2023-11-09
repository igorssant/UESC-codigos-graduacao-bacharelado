package model;
/**
 * @author igorsssantana
 */
public class Integral{
    private double limiteInferior;
    private double limiteSuperior;
    private double integralCalculada;

    /**
     * Construtor vazio
     */
    public Integral(){}

    /**
     * Contrutor principal da classe
     * recebe limite inferior
     * e limite superior
     * @param limiteInferior
     * @param limiteSuperior 
     */
    public Integral(double limiteInferior, double limiteSuperior){
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
    }

    /**
     * Contrutor da classe
     * recebe a, b e o valor
     * real da integral
     * @param limiteInferior
     * @param limiteSuperior
     * @param integralCalculada 
     */
    public Integral(double limiteInferior, double limiteSuperior, double integralCalculada){
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.integralCalculada = integralCalculada;
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

    public double getIntegralCalculada(){
        return this.integralCalculada;
    }

    public void setIntegralCalculada(double integralCalculada){
        this.integralCalculada = integralCalculada;
    }
}