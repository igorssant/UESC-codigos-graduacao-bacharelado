/**
 * @author igorsssantana
 */
package model.interfaces;

public abstract class ModeloMatematico {
    private double xK;
    private double xKMais1;
    private double tolerancia;
    private double erro;
    
    public abstract void gerarNovoXKMaisUm();
    
    public abstract void gerarErro();
}