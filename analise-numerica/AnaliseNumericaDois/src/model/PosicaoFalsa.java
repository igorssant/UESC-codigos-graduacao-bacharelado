package model;

import model.interfaces.ModeloMatematico;

/**
 * @author igorsssantana
 */
public class PosicaoFalsa extends ModeloMatematico{
    private double limiteA;
    private double limiteB;
    private double tolerancia;
    private double posicaoC;
    private double fa;
    private double fb;
    private double fc;
    
    /**
     * construtor vazio
     */
    public PosicaoFalsa(){}
    
    /**
     * Construtor da classe
     * @param limiteA
     * @param limiteB
     * @param tolerancia
     */
    public PosicaoFalsa(double limiteA, double limiteB, double tolerancia){
        this.limiteA = limiteA;
        this.limiteB = limiteB;
        this.tolerancia = tolerancia;
    }

    
    /**
     * Construtor da classe
     * @param limiteA
     * @param limiteB
     * @param tolerancia
     * @param fa
     * @param fb
     */
    public PosicaoFalsa(double limiteA, double limiteB, double tolerancia, double fa, double fb){
        this.limiteA = limiteA;
        this.limiteB = limiteB;
        this.tolerancia = tolerancia;
        this.fa = fa;
        this.fb = fb;
    }

    public double getLimiteA() {
        return limiteA;
    }

    public void setLimiteA(double limiteA) {
        this.limiteA = limiteA;
    }

    public double getLimiteB() {
        return limiteB;
    }

    public void setLimiteB(double limiteB) {
        this.limiteB = limiteB;
    }

    public double getPosicaoC() {
        return posicaoC;
    }

    public void setPosicaoC(double posicaoC) {
        this.posicaoC = posicaoC;
    }

    public double getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(double tolerancia) {
        this.tolerancia = tolerancia;
    }
    
    public double getFa() {
        return fa;
    }

    public void setFa(double fa) {
        this.fa = fa;
    }

    public double getFb() {
        return fb;
    }

    public void setFb(double fb) {
        this.fb = fb;
    }
    
    public double getFc() {
        return fc;
    }
    
    public void setFc(double fc){
        this.fc = fc;
    }
    
    /**
     * Esse metodo gera o novo C a partir das raizes atuais
     * Nao possui nenhuma entrada, nem saida.
     */
    @Override
    public void gerarPosicaoC(){
        this.posicaoC = ( (this.limiteA * this.fb) - (this.limiteB * this.fa) ) / (this.fb - this.fa);
    }
    
    @Override
    public String toString(){
        return "Ponto C = " + Double.toString(this.posicaoC) + "\nf(c) = " + Double.toString(this.fc);
    }
}