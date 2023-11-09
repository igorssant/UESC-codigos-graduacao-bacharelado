package model;

/**
 * @author igorsssantana
 */
public class TabelaLegendre{
    private int quantidadePontos;
    private double[][] tabela;

    /**
     * Contrutor vazio
     */
    public TabelaLegendre(){}

    /**
     * Contrutor principal
     * da classe
     * @param quantidadePontos 
     */
    public TabelaLegendre(int quantidadePontos){
        if(quantidadePontos > 1 &&  quantidadePontos < 7){
            this.quantidadePontos = quantidadePontos;
        }
        
        else{
            System.out.println("Uma quadratura deve possuir entre 2 e 6 pontos.\nFoi digitado um número abaixo ou acima desse intervalo!!!");
            System.exit(-1);
        }
        
        gerarNovaTabela();
    }

    /**
     * Contrutor principal
     * da classe
     * @param quantidadePontos
     * @param tabela 
     */
    public TabelaLegendre(int quantidadePontos, double[][] tabela) {
        if(quantidadePontos > 1 &&  quantidadePontos < 7){
            this.quantidadePontos = quantidadePontos;
            this.tabela = tabela;
        }
        
        else{
            System.out.println("Uma quadratura deve possuir entre 2 e 6 pontos.\nFoi digitado um número abaixo ou acima desse intervalo!!!");
            System.exit(-1);
        }
        
        gerarNovaTabela();
    }

    public int getQuantidadePontos(){
        return this.quantidadePontos;
    }

    public void setQuantidadePontos(int quantidadePontos){
        this.quantidadePontos = quantidadePontos;
    }
    
    public double[][] getTabela(){
        return this.tabela;
    }

    public void setTabela(double[][] tabela){
        this.tabela = tabela;
    }
    
    public double getPeso(int indice){
        return this.tabela[0][indice];
    }
    
    public double getArgumento(int indice){
        return this.tabela[1][indice];
    }
    
    private void tabelaDoisPontos(){
        this.tabela = new double[2][2];
        
        /*
         *  LINHA 0 == PESOSe
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 1f;
        this.tabela[0][1] = 1f;
        //--------------------
        this.tabela[1][0] = -0.57350269;
        this.tabela[1][1] = 0.57350269;
        
        /*return this.tabela;*/
    }
    
    private void tabelaTresPontos(){
        this.tabela = new double[2][3];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.5555556;
        this.tabela[0][1] = 0.8888889;
        this.tabela[0][2] = 0.5555556;
        //--------------------
        this.tabela[1][0] = -0.774596669;
        this.tabela[1][1] = 0f;
        this.tabela[1][2] = 0.774596669;
        
        /*return this.tabela;*/
    }
    
    private void tabelaQuatroPontos(){
        this.tabela = new double[2][4];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.3478548;
        this.tabela[0][1] = 0.6521452;
        this.tabela[0][2] = 0.6521452;
        this.tabela[0][3] = 0.3478548;
        //--------------------
        this.tabela[1][0] = -0.861136312;
        this.tabela[1][1] = -0.339981044;
        this.tabela[1][2] = 0.339981044;
        this.tabela[1][3] = 0.861136312;
        
        /*return this.tabela;*/
    }
    
    private void tabelaCincoPontos(){
        this.tabela = new double[2][5];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.2369269;
        this.tabela[0][1] = 0.4786287;
        this.tabela[0][2] = 0.5688889;
        this.tabela[0][3] = 0.4786287;
        this.tabela[0][4] = 0.2369269;
        //--------------------
        this.tabela[1][0] = -0.906179846;
        this.tabela[1][1] = -0.538469310;
        this.tabela[1][2] = 0f;
        this.tabela[1][3] = 0.538469310;
        this.tabela[1][4] = 0.906179846;
        
        /*return this.tabela;*/
    }
    
    private void tabelaSeisPontos(){
        this.tabela = new double[2][6];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.1713245;
        this.tabela[0][1] = 0.3607616;
        this.tabela[0][2] = 0.4679139;
        this.tabela[0][3] = 0.4679139;
        this.tabela[0][4] = 0.3607616;
        this.tabela[0][5] = 0.1713245;
        //--------------------
        this.tabela[1][0] = -0.932469514;
        this.tabela[1][1] = -0.661209386;
        this.tabela[1][2] = -0.238619186;
        this.tabela[1][3] = 0.238619186;
        this.tabela[1][4] = 0.661209386;
        this.tabela[1][5] = 0.932469514;
        
        /*return this.tabela;*/
    }
    
    private void gerarNovaTabela(){
        switch(this.quantidadePontos){
            case 2:
                tabelaDoisPontos();
                break;
            
            case 3:
                tabelaTresPontos();
                break;
            
            case 4:
                tabelaQuatroPontos();
                break;
            
            case 5:
                tabelaCincoPontos();
                break;
            
            case 6:
                tabelaSeisPontos();
                break;
            
            default:
                System.out.println("Ocorreu um erro inesperado.\nPor favor, tente novamente.");
        }
    }
}