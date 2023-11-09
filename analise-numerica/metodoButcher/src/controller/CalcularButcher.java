package controller;

import java.util.ArrayList;
import model.Derivada;

/**
 * @author igorsssantana
 */
public class CalcularButcher{
    /**
     * @param parXY
     * @param butcher
     * @param funcao
     * @return novo parXY 
     */
    public static double[] metodoButcher(double[] parXY, Derivada butcher, String funcao){
        double meio = (1.0 / 2.0),
                umQuarto = (1.0 / 4.0),
                tresQuartos = (3.0 / 4.0),
                umOitavo = (1.0 / 8.0),
                k1 = 0.0,
                k2 = 0.0,
                k3 = 0.0,
                k4 = 0.0,
                k5 = 0.0,
                k6 = 0.0;
        
        k1 = Funcao.funcao(
                funcao,
                parXY[0],
                parXY[1]
        );
        k2 = Funcao.funcao(
                funcao,
                parXY[0] + (umQuarto * butcher.getH()),
                parXY[1] + (umQuarto * k1 * butcher.getH())
        );
        k3 = Funcao.funcao(
                funcao,
                parXY[0] + (umQuarto * butcher.getH()),
                parXY[1] + (umOitavo * k1 * butcher.getH()) + (umOitavo * k2 * butcher.getH())
        );
        k4 = Funcao.funcao(
                funcao,
                parXY[0] + (meio * butcher.getH()),
                parXY[1] - (k2 * meio * butcher.getH()) + (k3 * butcher.getH())
        );
        k5 = Funcao.funcao(
                funcao,
                parXY[0] + (tresQuartos * butcher.getH()),
                parXY[1] + (k1 * (3.0 / 16.0) * butcher.getH()) + (k4 * (9.0 / 16.0) * butcher.getH())
        );
        k6 = Funcao.funcao(
                funcao,
                parXY[0] + butcher.getH(),
                parXY[1] - (k1 * (3.0 / 7.0) * butcher.getH())
                        + (k2 * (2.0 / 7.0) * butcher.getH())
                        + (k3 * (12.0 / 7.0) * butcher.getH())
                        - (k4 * (12.0 / 7.0) * butcher.getH())
                        + (k5 * (8.0 / 7.0) * butcher.getH())
        );
        
        parXY[0] = parXY[0] + butcher.getH();
        parXY[1] = parXY[1] + (1.0 / 90.0) * ((7f * k1) + (32f * k3) + (12f * k4) + (32f * k5) + (7f * k6)) * butcher.getH();
        
        return parXY;
    }
    
    /**
     * @param parXY
     * @param butcher
     * @param xFim
     * @param funcao
     * @param maximoDeIteracoes
     * @param erroEsperado
     * @return par ordenado XY
     */
    private static double[] Integrador(double[] parXY, Derivada butcher, double xFim, String funcao){
        /*while(true){*/
        if((xFim - parXY[0]) < butcher.getH()){
            butcher.setH(xFim - parXY[0]);
        }

        parXY = metodoButcher(parXY, butcher, funcao);
            
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
     * @param butcher
     * @return objeto Derivada
     */
    public static Derivada calcularButcher(String funcao, Derivada butcher){
        double xFim = 0f;
        double[] parXY = new double[2];
        ArrayList<Double> xp = new ArrayList<>();
        ArrayList<Double> yp = new ArrayList<>();
        
        /*
         * POSICAO 0 -> VALOR DE X
         * POSICAO 1 -> VALOR DE Y
         */
        parXY[0] = butcher.getXi();
        parXY[1] = butcher.getY();
        /* ======================= */
        xp.add(parXY[0]);
        yp.add(parXY[1]);
        
        while(true){
            xFim = parXY[0] + butcher.getIntervaloSaida();
            
            if(xFim > butcher.getXf()){
                xFim = butcher.getXf();
            }
            
            parXY = Integrador(parXY, butcher, xFim, funcao);
            xp.add(parXY[0]);
            yp.add(parXY[1]);
            
            if(parXY[0] >= butcher.getXf()){
                break;
            }
        }
        
        butcher.setValoresDeX(xp.stream().mapToDouble(d -> d).toArray());
        butcher.setValoresDeY(yp.stream().mapToDouble(d -> d).toArray());
        xp.clear();
        yp.clear();
        
        return butcher;
    }
}