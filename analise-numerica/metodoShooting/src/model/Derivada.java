package model;

/**
 * @author igorsssantana
 */
public class Derivada{
    private double y;
    private double xi;
    private double xf;
    private double h;
    private double tolerancia;
    private double alfa;
    private double beta;
    private double[] valoresDeY;

    /**
     * contrutor vazio
     * nao recebe nada
     * nao retorna nada
     */
    public Derivada(){}

    /**
     * construtor completo de classe
     * recebe y, xi, xf, h, tolerancia,
     * alfa e beta
     * @param y
     * @param xi
     * @param xf
     * @param h
     * @param tolerancia
     * @param alfa
     * @param beta 
     */
    public Derivada(double y, double xi, double xf, double h, double tolerancia, double alfa, double beta) {
        this.y = y;
        this.xi = xi;
        this.xf = xf;
        this.h = h;
        this.tolerancia = tolerancia;
        this.alfa = alfa;
        this.beta = beta;
    }
    
    public double getY(){
        return this.y;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getXi(){
        return this.xi;
    }

    public void setXi(double xi) {
        this.xi = xi;
    }

    public double getXf(){
        return this.xf;
    }

    public void setXf(double xf){
        this.xf = xf;
    }
    
    public double getH(){
        return this.h;
    }

    public void setH(double h){
        this.h = h;
    }

    public double getTolerancia(){
        return this.tolerancia;
    }

    public void setTolerancia(double tolerancia){
        this.tolerancia = tolerancia;
    }

    public double getAlfa(){
        return this.alfa;
    }

    public void setAlfa(double alfa){
        this.alfa = alfa;
    }

    public double getBeta(){
        return this.beta;
    }

    public void setBeta(double beta){
        this.beta = beta;
    }
    
    public void inicializaVetorY(int tamanho){
        this.valoresDeY = new double[tamanho];
    }

    public double[] getValoresDeY() {
        return this.valoresDeY;
    }
    
    public double getValoresDeY(int indice){
        return this.valoresDeY[indice];
    }

    public void setValoresDeY(double[] valoresDeY){
        this.valoresDeY = valoresDeY;
    }
    
    public void setValoresDeY(int indice, double valorDeY){
        this.valoresDeY[indice] = valorDeY;
    }
}