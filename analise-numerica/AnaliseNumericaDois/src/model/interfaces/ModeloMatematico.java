/**
 * @author igorsssantana
 */
package model.interfaces;

public abstract class ModeloMatematico {
    private double limiteA;
    private double limiteB;
    private double tolerancia;
    private double posicaoC;
    private double fa;
    private double fb;
    private double fc;
    
    public abstract void gerarPosicaoC();
}
