package model;

import model.interfaces.Integral;

/**
 * @author igorsssantana
 */
public class TresOitavos extends Integral{
    private double [] pontos;
    private double h;
    private double integralCalculada;

    /**
     * construtor de classe vazio
     * nao recebe nenhum parametro
     */
    public TresOitavos(){}
    
    /**
     * @param pontos 
     */
    public TresOitavos(double[] pontos){
        this.pontos = pontos;
    }

    /**
     * @param pontos
     * @param h 
     */
    public TresOitavos(double[] pontos, double h){
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
    
    @Override
    public void gerarNovoH(){
        this.h = (this.pontos[3] - this.pontos[0]) / 3f;
    }
}