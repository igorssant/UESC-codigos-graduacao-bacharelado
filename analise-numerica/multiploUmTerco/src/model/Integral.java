package model;

/**
 * @author igorsssantana
 */
public class Integral{
    private double pontoX0;
    private double pontoXi;
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
    public Integral(int intervalo) {
        this.intervalo = intervalo;
    }
    
    /**
     * @param pontoX0
     * @param pontoXi 
     */
    public Integral(double pontoX0, double pontoXi) {
        this.pontoX0 = pontoX0;
        this.pontoXi = pontoXi;
    }
    
    /**
     * @param pontoX0
     * @param pontoXi
     * @param intervalo 
     */
    public Integral(double pontoX0, double pontoXi, int intervalo) {
        this.pontoX0 = pontoX0;
        this.pontoXi = pontoXi;
        this.intervalo = intervalo;
    }

    public double getPontoX0(){
        return this.pontoX0;
    }

    public void setPontoX0(double pontoX0){
        this.pontoX0 = pontoX0;
    }

    public double getPontoXi(){
        return this.pontoXi;
    }

    public void setPontoXi(double pontoXi){
        this.pontoXi = pontoXi;
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
        this.h = (this.pontoXi - this.pontoX0) / this.intervalo;
    }
}