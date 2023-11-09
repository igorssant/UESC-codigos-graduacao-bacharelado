package model;

/**
 * @author igorsssantana
 */
public class Derivada{
    private double y;
    private double xi;
    private double xf;
    private double h;
    private double intervaloSaida;
    private double[] valoresDeX;
    private double[] valoresDeY;

    /**
     * contrutor vazio
     * nao recebe nada
     * nao retorna nada
     */
    public Derivada(){}

    /**
     * @param y
     * @param xi
     * @param xf
     * @param h
     * @param intervaloSaida 
     */
    public Derivada(double y, double xi, double xf, double h, double intervaloSaida){
        this.y = y;
        this.xi = xi;
        this.xf = xf;
        this.h = h;
        this.intervaloSaida = intervaloSaida;
    }

    /**
     * @param y
     * @param xi
     * @param xf
     * @param h
     * @param intervaloSaida
     * @param valoresDeX
     * @param valoresDeY 
     */
    public Derivada(double y, double xi, double xf, double h, double intervaloSaida, double[] valoresDeX, double[] valoresDeY){
        this.y = y;
        this.xi = xi;
        this.xf = xf;
        this.h = h;
        this.intervaloSaida = intervaloSaida;
        this.valoresDeX = valoresDeX;
        this.valoresDeY = valoresDeY;
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

    public double getIntervaloSaida(){
        return this.intervaloSaida;
    }

    public void setIntervaloSaida(double intervaloSaida){
        this.intervaloSaida = intervaloSaida;
    }

    public double[] getValoresDeX(){
        return this.valoresDeX;
    }
    
    public double getValoresDeX(int indice){
        return this.valoresDeX[indice];
    }

    public void setValoresDeX(double[] valoresDeX){
        this.valoresDeX = valoresDeX;
    }
    
    public void setValoresDeX(int indice, double valorDeX){
        this.valoresDeX[indice] = valorDeX;
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