package controller;

import java.util.ArrayList;
import model.Derivada;

/**
 * @author igorsssantana
 */
public class CalcularEuler{
    /**
     * @param parXY
     * @param euler
     * @param funcao
     * @return novo parXY 
     */
    public static double[] Euler(double[] parXY, Derivada euler, String funcao){
        double dydx = Funcao.funcao(funcao, parXY[0], parXY[1]);
        
        parXY[1] = parXY[1] + (dydx * euler.getH());
        parXY[0] = parXY[0] + euler.getH();
        
        return parXY;
    }
    
    /**
     * @param parXY
     * @param euler
     * @param xFim
     * @param funcao
     * @return par ordenado XY
     */
    private static double[] Integrador(double[] parXY, Derivada euler, double xFim, String funcao){
        /*while(true){*/
        if((xFim - parXY[0]) < euler.getH()){
            euler.setH(xFim - parXY[0]);
        }

        parXY = Euler(parXY, euler, funcao);
            
            /*if(parXY[0] >= xFim){
                break;
            }
        }*/
        return parXY;
    }
    
    /**
     * metodo para calcular a
     * primeira derivada nos pontos
     * @param funcao
     * @param euler
     * @return objeto Derivada
     */
    public static Derivada metodoDeEuler(String funcao, Derivada euler){
        double xFim = 0f;
        double[] parXY = new double[2];
        ArrayList<Double> xp = new ArrayList<>();
        ArrayList<Double> yp = new ArrayList<>();
        
        /*
         * POSICAO 0 -> VALOR DE X
         * POSICAO 1 -> VALOR DE Y
         */
        parXY[0] = euler.getXi();
        parXY[1] = euler.getY();
        /* ======================= */
        xp.add(parXY[0]);
        yp.add(parXY[1]);
        
        while(true){
            xFim = parXY[0] + euler.getIntervaloSaida();
            
            if(xFim > euler.getXf()){
                xFim = euler.getXf();
            }
            
            parXY = Integrador(parXY, euler, xFim, funcao);
            xp.add(parXY[0]);
            yp.add(parXY[1]);
            
            if(parXY[0] >= euler.getXf()){
                break;
            }
        }
        
        euler.setValoresDeX(xp.stream().mapToDouble(d -> d).toArray());
        euler.setValoresDeY(yp.stream().mapToDouble(d -> d).toArray());
        xp.clear();
        yp.clear();
        
        return euler;
    }
}