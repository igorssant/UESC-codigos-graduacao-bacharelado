package model;

/**
 * @author igorsssantana
 */
public class Integral{
    private double a;
    private double b;
    private double h;
    private double integralCalculada;

    /**
     * construtor de classe vazio
     * nao recebe nenhum parametro
     */
    public Integral(){}

    /**
     * @param a
     * @param b 
     */
    public Integral(double a, double b) {
        this.a = a;
        this.b = b;
    }

    /**
     * @param a
     * @param b
     * @param h 
     */
    public Integral(double a, double b, double h) {
        this.a = a;
        this.b = b;
        this.h = h;
    }

    public double getA(){
        return this.a;
    }

    public void setA(double a){
        this.a = a;
    }

    public double getB(){
        return this.b;
    }

    public void setB(double b){
        this.b = b;
    }

    public double getH(){
        return h;
    }

    public void setH(double h){
        this.h = h;
    }

    public double getIntegralCalculada(){
        return integralCalculada;
    }

    public void setIntegralCalculada(double integralCalculada){
        this.integralCalculada = integralCalculada;
    }
    
    public void gerarNovoH(){
        this.h = (this.b - this.a);
    }
}