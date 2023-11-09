package model;

/**
 * @author igorsssantana
 */
public class Integral{
    private double [] pontos;
    private double h;
    private double integralCalculada;

    /**
     * construtor de classe vazio
     * nao recebe nenhum parametro
     */
    public Integral(){}
    
    /**
     * @param pontos 
     */
    public Integral(double[] pontos){
        this.pontos = pontos;
    }

    /**
     * @param pontos
     * @param h 
     */
    public Integral(double[] pontos, double h){
        this.pontos = pontos;
        this.h = h;
    }

    public double[] getPontos(){
        return this.pontos;
    }
    
    /**
     * @param indice
     * @return 
     */
    public double getPontos(int indice){
        return this.pontos[indice];
    }

    public void setPontos(double[] pontos){
        this.pontos = pontos;
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
        this.h = (this.pontos[3] - this.pontos[0]) / 3f;
    }
}