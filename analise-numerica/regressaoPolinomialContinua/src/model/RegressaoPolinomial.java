package model;

/**
 * @author igorsssantana
 */
public class RegressaoPolinomial{
    private double[][] matriz;
    private double[] vetor;

    /**
     * construtor de classe vazio
     */
    public RegressaoPolinomial(){}

    /**
     * construtor principal da classe
     * @param matriz
     * @param vetor 
     */
    public RegressaoPolinomial(double[][] matriz, double[] vetor){
        this.matriz = matriz;
        this.vetor = vetor;
    }

    public double[][] getMatriz(){
        return this.matriz;
    }

    public void setMatriz(double[][] matriz){
        this.matriz = matriz;
    }

    public double[] getVetor(){
        return this.vetor;
    }

    public void setVetor(double[] vetor){
        this.vetor = vetor;
    }
    
    public double[][] matrizExpandida(int ordem){
        double[][] matrizResultante = new double[ordem][ordem-1];
        
        for(int i = 0; i < ordem-1; i++){
            matrizResultante[ordem-1][i] = this.vetor[i];
        }
        
        for(int i = 0; i < ordem-1; i++){
            for(int j = 0; j < ordem-1; j++){
                matrizResultante[i][j] = this.matriz[i][j];
            }
        }
        
        return matrizResultante;
    }
}