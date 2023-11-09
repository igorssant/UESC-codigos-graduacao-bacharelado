package controller;

import model.Derivada;

/**
 * @author igorsssantana
 */
public class Shooting{
    /**
     * 
     * @param funcao
     * @param rk4
     * @param iteracao
     * @param chute
     * @return 
     */
    public static Derivada metodoRK4(String funcao, Derivada rk4, int iteracao, double chute){
        double meio = (1.0 / 2.0),
                umQuarto = (1.0 / 4.0),
                temporario = 0f;
        double k1 = 0.0,
                k2 = 0.0,
                k3 = 0.0,
                k4 = 0.0,
                pontoAtual = rk4.getXi() + (iteracao * rk4.getH());
        
        k1 = rk4.getH() * rk4.getValoresDeY(iteracao - 1);
        k2 = rk4.getH() * (
                rk4.getValoresDeY(iteracao - 1) +
                (meio * rk4.getH() * chute)
            );
        k3 = rk4.getH() * (
                rk4.getValoresDeY(iteracao - 1) +
                (meio * rk4.getH() * chute) +
                umQuarto * Math.pow(rk4.getH(), 2) *
                Funcao.funcao(
                    funcao,
                    pontoAtual
                )
            );
        k4 = rk4.getH() * (
                rk4.getValoresDeY(iteracao - 1) +
                (rk4.getH() * chute) +
                (meio * Math.pow(rk4.getH(), 2)) *
                Funcao.funcao(
                    funcao,
                    (pontoAtual + rk4.getH())
                )
            );
        
        temporario = ((1.0 / 6.0) * (k1 + (2f * k2) + (2f * k3) + k4)) + rk4.getValoresDeY(iteracao - 1);
        rk4.setValoresDeY(iteracao, temporario);
        
        return rk4;
    }
    
    /**
     * 
     * @param funcao
     * @param shoot
     * @param chute
     * @return 
     */
    public static double[] integrador(String funcao, Derivada shoot, double chute){
        int tamanho = (int) ((shoot.getXf() - shoot.getXi()) / shoot.getH());
        
        if(shoot.getValoresDeY() == null){
            shoot.inicializaVetorY(tamanho);
        }
        
        shoot.setValoresDeY(0, 0f);
        
        for(int i = 1; i < tamanho; i++){
            shoot = metodoRK4(funcao, shoot, i, chute);
        }
        
        return shoot.getValoresDeY();
    }
    
    /**
     * 
     * @param funcao
     * @param shoot
     * @param maximoIteracoes
     * @return 
     */
    public static double shooting(String funcao, Derivada shoot, int maximoIteracoes){
        int contador = 0;
        double erroLocal = 0f,
                chuteInicial = shoot.getAlfa(),
                segundoChute = shoot.getAlfa() + shoot.getH(),
                chuteFinal = 0f;
        
        while(true){
            shoot.setValoresDeY(integrador(funcao, shoot, segundoChute));
            erroLocal = shoot.getValoresDeY(shoot.getValoresDeY().length - 1) - shoot.getBeta();
            
            if(Math.abs(erroLocal) < shoot.getTolerancia()){
                return shoot.getValoresDeY(shoot.getValoresDeY().length - 1);
            }
            
            chuteFinal = segundoChute - erroLocal * (
                        (segundoChute - chuteInicial) / (shoot.getValoresDeY(shoot.getValoresDeY().length - 1) - shoot.getValoresDeY(shoot.getValoresDeY().length - 2))
                    );
            
            if(contador > (maximoIteracoes - 1)){
                break;
            }
            
            contador++;
            chuteInicial = segundoChute;
            segundoChute = chuteFinal;
        }
        
        System.err.println("O método de shooting não conseguiu convergir dados os pontos!!!");
        //return Double.NaN;
        return shoot.getValoresDeY(shoot.getValoresDeY().length - 1);
    }
}