package model;

import model.interfaces.Integral;

/**
 * @author igorsssantana
 */
public class UmTerco extends Integral{
    private double [] pontos;
    private double h;
    private double integralCalculada;

    /**
     * construtor de classe vazio
     * nao recebe nenhum parametro
     */
    public UmTerco(){}

    /**
     * @param pontos 
     */
    public UmTerco(double[] pontos){
        this.pontos = pontos;
    }

    /**
     * @param pontos
     * @param h 
     */
    public UmTerco(double[] pontos, double h){
        this.pontos = pontos;
        this.h = h;
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
    
    @Override
    public void gerarNovoH(){
        this.h = (this.pontos[2] - this.pontos[0]) / 2;
    }
}