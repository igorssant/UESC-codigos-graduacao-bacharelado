package model;

/**
 * @author igorsssantana
 */
public class MatrizLU{
    private double[][] matriz;
    private int nroLinhas;
    private int nroColunas;
    private double determinante;

    /**
     * Construtor vazio de classe
     * Nao recebe nada
     * Nao possui retorno
     */
    public MatrizLU(){
        this.nroColunas = this.nroLinhas = 0;
        this.determinante = Double.NaN;
    }

    /**
     * contrutor de classe
     * recebe uma matriz quadrada
     * @param matriz 
     */
    public MatrizLU(double[][] matriz){
        this.matriz = matriz;
        this.nroLinhas = matriz.length;
        this.nroColunas = matriz[0].length;
        this.determinante = determinante(matriz);
    }

    /**
     * construtor de classe
     * recebe a matriz quadrada
     * e sua determinante
     * @param matriz
     * @param determinante 
     */
    public MatrizLU(double[][] matriz, double determinante) {
        this.matriz = matriz;
        this.determinante = determinante;
    }

    /**
     * contrutor de classe completo
     * @param matriz
     * @param nroLinhas
     * @param nroColunas
     * @param determinante 
     */
    public MatrizLU(double[][] matriz, int nroLinhas, int nroColunas, double determinante) {
        this.matriz = matriz;
        this.nroLinhas = nroLinhas;
        this.nroColunas = nroColunas;
        this.determinante = determinante;
    }

    public double[][] getMatriz(){
        return this.matriz;
    }

    public void setMatriz(double[][] matriz){
        this.matriz = matriz;
    }

    public int getNroLinhas(){
        return this.nroLinhas;
    }

    public void setNroLinhas(int nroLinhas){
        this.nroLinhas = nroLinhas;
    }

    public int getNroColunas(){
        return this.nroColunas;
    }

    public void setNroColunas(int nroColunas){
        this.nroColunas = nroColunas;
    }

    public double getDeterminante(){
        return this.determinante;
    }

    public void setDeterminante(double determinante){
        this.determinante = determinante;
    }
    
    /**
     * metodo para calcular a determinande de
     * dada matriz quadrada
     * recebe uma matriz quadrada
     * retorna sua determinante
     * @param matriz
     * @return determinante da matriz passada como parametro 
     */
    private double determinante(double[][] matriz){
        double auxiliar[][] = null;
        double resultado = 0f;

        if(matriz.length == 1){
            resultado = matriz[0][0];
            return (resultado);
        }

        else if(matriz.length == 2){
            resultado = ((matriz[0][0] * matriz[1][1]) - (matriz[0][1] * matriz[1][0]));
            return (resultado);
        }

        for(int i = 0; i < matriz[0].length; i++){
            auxiliar = new double[matriz.length - 1][matriz[0].length - 1];

            for(int j = 1; j < matriz.length; j++){
                for(int k = 0; k < matriz[0].length; k++){
                    if(k < i){
                        auxiliar[j - 1][k] = matriz[j][k];
                    }
                    
                    else if (k > i){
                        auxiliar[j - 1][k - 1] = matriz[j][k];
                    }
                }
            }

            resultado += matriz[0][i] * Math.pow (-1, (double) i) * determinante(auxiliar);
        }
        
        return (resultado);
    }
}