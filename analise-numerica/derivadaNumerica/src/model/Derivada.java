package model;

/**
 * @author igorsssantana
 */
public class Derivada{
    private double pontoParaDerivar;
    private double h;
    private double derivadaPrimeira;
    private double derivadaSegunda;

    /**
     * contrutor vazio
     * nao recebe nada
     * nao retorna nada
     */
    public Derivada(){}
    
    /**
     * contrutor principal da classe
     * recebe o ponto para derivar
     * nao possui retorno
     * @param pontosX
     */
    public Derivada(double pontoParaDerivar){
        this.pontoParaDerivar = pontoParaDerivar;
    }
    
    /**
     * contrutor principal da classe
     * recebe o ponto para derivar
     * e h. Nao possui retorno
     * @param pontoParaDerivar
     * @param h
     */
    public Derivada(double pontoParaDerivar, double h){
        this.pontoParaDerivar = pontoParaDerivar;
        this.h = h;
    }

    public double getPontoParaDerivar(){
        return pontoParaDerivar;
    }

    public void setPontoParaDerivar(double pontoParaDerivar){
        this.pontoParaDerivar = pontoParaDerivar;
    }

    public double getH(){
        return h;
    }

    public void setH(double h){
        this.h = h;
    }

    public double getDerivadaPrimeira(){
        return derivadaPrimeira;
    }

    public void setDerivadaPrimeira(double derivadaPrimeira){
        this.derivadaPrimeira = derivadaPrimeira;
    }

    public double getDerivadaSegunda(){
        return derivadaSegunda;
    }

    public void setDerivadaSegunda(double derivadaSegunda){
        this.derivadaSegunda = derivadaSegunda;
    }
}