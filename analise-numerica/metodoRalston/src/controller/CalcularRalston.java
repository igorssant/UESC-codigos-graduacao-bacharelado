package controller;

import java.util.ArrayList;
import model.Derivada;

/**
 * @author igorsssantana
 */
public class CalcularRalston{
    /**
     * @param parXY
     * @param ralston
     * @param funcao
     * @return novo parXY 
     */
    public static double[] metodoRalston(double[] parXY, Derivada ralston, String funcao){
        double a2 = (2.0 / 3.0);
        double a1 = 0.0,
                k1 = 0.0,
                k2 = 0.0,
                p1 = 0.0,
                q11 = 0.0;
        
        a1 = 1.0 - a2;
        p1 = 1.0 / (2.0 * a2);
        q11 = 1.0 / (2.0 * a2);
        k1 = Funcao.funcao(
                funcao,
                parXY[0],
                parXY[1]
        );
        k2 = Funcao.funcao(
                funcao,
                parXY[0] + (p1 * ralston.getH()),
                parXY[1] + (q11 * k1 * ralston.getH())
        );
        
        parXY[0] = parXY[0] + ralston.getH();
        parXY[1] = parXY[1] + ( (a1 * k1) + (a2 * k2) ) * ralston.getH();
        
        return parXY;
    }
    
    /**
     * @param parXY
     * @param ralston
     * @param xFim
     * @param funcao
     * @param maximoDeIteracoes
     * @param erroEsperado
     * @return par ordenado XY
     */
    private static double[] Integrador(double[] parXY, Derivada ralston, double xFim, String funcao){
        /*while(true){*/
        if((xFim - parXY[0]) < ralston.getH()){
            ralston.setH(xFim - parXY[0]);
        }

        parXY = metodoRalston(parXY, ralston, funcao);
            
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
     * @param ralston
     * @return objeto Derivada
     */
    public static Derivada calcularRalston(String funcao, Derivada ralston){
        double xFim = 0f;
        double[] parXY = new double[2];
        ArrayList<Double> xp = new ArrayList<>();
        ArrayList<Double> yp = new ArrayList<>();
        
        /*
         * POSICAO 0 -> VALOR DE X
         * POSICAO 1 -> VALOR DE Y
         */
        parXY[0] = ralston.getXi();
        parXY[1] = ralston.getY();
        /* ======================= */
        xp.add(parXY[0]);
        yp.add(parXY[1]);
        
        while(true){
            xFim = parXY[0] + ralston.getIntervaloSaida();
            
            if(xFim > ralston.getXf()){
                xFim = ralston.getXf();
            }
            
            parXY = Integrador(parXY, ralston, xFim, funcao);
            xp.add(parXY[0]);
            yp.add(parXY[1]);
            
            if(parXY[0] >= ralston.getXf()){
                break;
            }
        }
        
        ralston.setValoresDeX(xp.stream().mapToDouble(d -> d).toArray());
        ralston.setValoresDeY(yp.stream().mapToDouble(d -> d).toArray());
        xp.clear();
        yp.clear();
        
        return ralston;
    }
}