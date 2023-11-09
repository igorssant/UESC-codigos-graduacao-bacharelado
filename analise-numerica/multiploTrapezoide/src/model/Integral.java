package model;

/**
 * @author igorsssantana
 */
public class Integral{
    private double [] pontos;
    private double h;
    private double integralCalculada;
    private int intervalo;

    /**
     * construtor de classe vazio
     * nao recebe nenhum parametro
     */
    public Integral(){}
    
    /**
     * construtor de classe
     * recebe o vetor de pontos
     * tipo double
     * @param pontos 
     */
    public Integral(double[] pontos){
        this.pontos = pontos;
    }
    
    /**
     * construtor de classe
     * recebe o vetor de pontos
     * double
     * e recebe o valor
     * de h double
     * @param pontos
     * @param h 
     */
    public Integral(double[] pontos, double h){
        this.pontos = pontos;
        this.h = h;
    }

    /**
     * construtor de classe
     * recebe o valor
     * de h double
     * e recebe a quantidade
     * de intervalos
     * int
     * @param h
     * @param intervalo 
     */
    public Integral(double h, int intervalo){
        this.h = h;
        this.intervalo = intervalo;
    }

    /**
     * construtor principal da
     * classe. Recebe o vetor
     * de pontos double,
     * o valor de h double
     * e a quantidade de
     * intervalos int
     * @param pontos
     * @param h
     * @param intervalo 
     */
    public Integral(double[] pontos, double h, int intervalo){
        this.pontos = pontos;
        this.h = h;
        this.intervalo = intervalo;
    }

    public double[] getPontos(){
        return this.pontos;
    }
    
    public double getPontos(int indice){
        return this.pontos[indice];
    }

    public void setPontos(double[] pontos){
        this.pontos = pontos;
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
}