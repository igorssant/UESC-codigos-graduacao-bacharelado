package model.interfaces;

/**
 * @author igorsssantana
 */
public abstract class Integral{
    private double [] pontos;
    private double h;
    private double integralCalculada;
    
    public abstract void gerarNovoH();
}