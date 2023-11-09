package controller;

import java.util.ArrayList;
import model.Derivada;

/**
 * @author igorsssantana
 */
public class CalcularHeun{
    /**
     * @param parXY
     * @param heun
     * @param funcao
     * @param maximoDeIteracoes
     * @param erroEsperado
     * @return novo parXY 
     */
    public static double[] heunIterativo(double[] parXY, Derivada heun, String funcao, int maximoDeIteracoes, double erroEsperado){
        int iteracao = 0;
        double dy1dx = 0f,
                dy2dx,
                ye = 0f,
                velhoYe = 0f,
                slope = 0f,
                erroReal = 0f;
        
        dy1dx = Funcao.funcao(funcao, parXY[0], parXY[1]);
        ye = parXY[1] + (dy1dx * heun.getH());
        
        while(true){
            velhoYe = ye;
            dy2dx = Funcao.funcao(
                    funcao,
                    (parXY[0] + heun.getH()),
                    ye
            );
            slope = (dy1dx + dy2dx) / 2;
            ye = parXY[1] + (slope * heun.getH());
            iteracao++;
            erroReal = Math.abs((ye - velhoYe) / ye);
            
            if(erroReal <= erroEsperado || iteracao > maximoDeIteracoes){
                break;
            }
        }
        
        parXY[1] = ye;
        parXY[0] = parXY[0] + heun.getH();
        
        return parXY;
    }
    
    /**
     * @param parXY
     * @param heun
     * @param xFim
     * @param funcao
     * @param maximoDeIteracoes
     * @param erroEsperado
     * @return par ordenado XY
     */
    private static double[] Integrador(double[] parXY, Derivada heun, double xFim, String funcao, int maximoDeIteracoes, double erroEsperado){
        /*while(true){*/
        if((xFim - parXY[0]) < heun.getH()){
            heun.setH(xFim - parXY[0]);
        }

        parXY = heunIterativo(parXY, heun, funcao, maximoDeIteracoes, erroEsperado);
            
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
     * @param heun
     * @return objeto Derivada
     */
    public static Derivada calcularHeun(String funcao, Derivada heun, int maximoDeIteracoes, double erroEsperado){
        double xFim = 0f;
        double[] parXY = new double[2];
        ArrayList<Double> xp = new ArrayList<>();
        ArrayList<Double> yp = new ArrayList<>();
        
        /*
         * POSICAO 0 -> VALOR DE X
         * POSICAO 1 -> VALOR DE Y
         */
        parXY[0] = heun.getXi();
        parXY[1] = heun.getY();
        /* ======================= */
        xp.add(parXY[0]);
        yp.add(parXY[1]);
        
        while(true){
            xFim = parXY[0] + heun.getIntervaloSaida();
            
            if(xFim > heun.getXf()){
                xFim = heun.getXf();
            }
            
            parXY = Integrador(parXY, heun, xFim, funcao, maximoDeIteracoes, erroEsperado);
            xp.add(parXY[0]);
            yp.add(parXY[1]);
            
            if(parXY[0] >= heun.getXf()){
                break;
            }
        }
        
        heun.setValoresDeX(xp.stream().mapToDouble(d -> d).toArray());
        heun.setValoresDeY(yp.stream().mapToDouble(d -> d).toArray());
        xp.clear();
        yp.clear();
        
        return heun;
    }
}