package controller;

import java.util.ArrayList;
import model.Derivada;

/**
 * @author igorsssantana
 */
public class CalcularEulerMelhorado{
    /**
     * @param parXY
     * @param eulerMelhorado
     * @param funcao
     * @return novo parXY 
     */
    public static double[] eulerMelhorado(double[] parXY, Derivada eulerMelhorado, String funcao){
        double dydx = 0f,
                ym = 0f,
                dymdx = 0f;
        
        dydx = Funcao.funcao(funcao, parXY[0], parXY[1]);
        ym = parXY[1] + ((dydx * eulerMelhorado.getH()) / 2);
        dymdx = Funcao.funcao(
                funcao,
                parXY[0] + (eulerMelhorado.getH() / 2), 
                ym);
        parXY[1] = parXY[1] + (dymdx * eulerMelhorado.getH());
        parXY[0] = parXY[0] + eulerMelhorado.getH();
        
        return parXY;
    }
    
    /**
     * @param parXY
     * @param eulerMelhorado
     * @param xFim
     * @param funcao
     * @return par ordenado XY
     */
    private static double[] Integrador(double[] parXY, Derivada eulerMelhorado, double xFim, String funcao){
        /*while(true){*/
        if((xFim - parXY[0]) < eulerMelhorado.getH()){
            eulerMelhorado.setH(xFim - parXY[0]);
        }

        parXY = eulerMelhorado(parXY, eulerMelhorado, funcao);
            
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
     * @param eulerMelhorado
     * @return objeto Derivada
     */
    public static Derivada metodoDeEulerMelhorado(String funcao, Derivada eulerMelhorado){
        double xFim = 0f;
        double[] parXY = new double[2];
        ArrayList<Double> xp = new ArrayList<>();
        ArrayList<Double> yp = new ArrayList<>();
        
        /*
         * POSICAO 0 -> VALOR DE X
         * POSICAO 1 -> VALOR DE Y
         */
        parXY[0] = eulerMelhorado.getXi();
        parXY[1] = eulerMelhorado.getY();
        /* ======================= */
        xp.add(parXY[0]);
        yp.add(parXY[1]);
        
        while(true){
            xFim = parXY[0] + eulerMelhorado.getIntervaloSaida();
            
            if(xFim > eulerMelhorado.getXf()){
                xFim = eulerMelhorado.getXf();
            }
            
            parXY = Integrador(parXY, eulerMelhorado, xFim, funcao);
            xp.add(parXY[0]);
            yp.add(parXY[1]);
            
            if(parXY[0] >= eulerMelhorado.getXf()){
                break;
            }
        }
        
        eulerMelhorado.setValoresDeX(xp.stream().mapToDouble(d -> d).toArray());
        eulerMelhorado.setValoresDeY(yp.stream().mapToDouble(d -> d).toArray());
        xp.clear();
        yp.clear();
        
        return eulerMelhorado;
    }
}