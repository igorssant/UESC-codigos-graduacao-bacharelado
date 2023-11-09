/**
 * @author igorsssantana
 */
package model.interfaces;

public abstract class ModeloMatematico {
    private double intervaloSuperior;
    private double intervaloInferior;
    private double tolerancia;
    private double estimativa;
    private double fAnterior;
    private double fAtual;
    
    public abstract void gerarErro();
    public abstract void gerarEstimativa();
}