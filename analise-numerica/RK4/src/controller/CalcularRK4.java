package controller;

import java.util.ArrayList;
import model.Derivada;

/**
 * @author igorsssantana
 */
public class CalcularRK4{
    /**
     * @param parXY
     * @param rk4
     * @param funcao
     * @return novo parXY 
     */
    public static double[] metodoRK4(double[] parXY, Derivada rk4, String funcao){
        double meio = (1.0 / 2.0);
        double k1 = 0.0,
                k2 = 0.0,
                k3 = 0.0,
                k4 = 0.0;
        
        k1 = Funcao.funcao(
                funcao,
                parXY[0],
                parXY[1]
        );
        k2 = Funcao.funcao(
                funcao,
                parXY[0] + (meio * rk4.getH()),
                parXY[1] + (meio * k1 * rk4.getH())
        );
        k3 = Funcao.funcao(
                funcao,
                parXY[0] + (meio * rk4.getH()),
                parXY[1] + (meio * k2 * rk4.getH())
        );
        k4 = Funcao.funcao(
                funcao,
                parXY[0] + (meio * rk4.getH()),
                parXY[1] + (k3 * rk4.getH())
        );
        
        parXY[0] = parXY[0] + rk4.getH();
        parXY[1] = parXY[1] + (1.0 / 6.0) * (k1 + (2f * k2) + (2f * k3) + k4) * rk4.getH();
        
        return parXY;
    }
    
    /**
     * @param parXY
     * @param rk4
     * @param xFim
     * @param funcao
     * @param maximoDeIteracoes
     * @param erroEsperado
     * @return par ordenado XY
     */
    private static double[] Integrador(double[] parXY, Derivada rk4, double xFim, String funcao){
        /*while(true){*/
        if((xFim - parXY[0]) < rk4.getH()){
            rk4.setH(xFim - parXY[0]);
        }

        parXY = metodoRK4(parXY, rk4, funcao);
            
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
     * @param rk4
     * @return objeto Derivada
     */
    public static Derivada calcularRK4(String funcao, Derivada rk4){
        double xFim = 0f;
        double[] parXY = new double[2];
        ArrayList<Double> xp = new ArrayList<>();
        ArrayList<Double> yp = new ArrayList<>();
        
        /*
         * POSICAO 0 -> VALOR DE X
         * POSICAO 1 -> VALOR DE Y
         */
        parXY[0] = rk4.getXi();
        parXY[1] = rk4.getY();
        /* ======================= */
        xp.add(parXY[0]);
        yp.add(parXY[1]);
        
        while(true){
            xFim = parXY[0] + rk4.getIntervaloSaida();
            
            if(xFim > rk4.getXf()){
                xFim = rk4.getXf();
            }
            
            parXY = Integrador(parXY, rk4, xFim, funcao);
            xp.add(parXY[0]);
            yp.add(parXY[1]);
            
            if(parXY[0] >= rk4.getXf()){
                break;
            }
        }
        
        rk4.setValoresDeX(xp.stream().mapToDouble(d -> d).toArray());
        rk4.setValoresDeY(yp.stream().mapToDouble(d -> d).toArray());
        xp.clear();
        yp.clear();
        
        return rk4;
    }
}