package model;

/**
 * @author igorsssantana
 */
public class TabelaLaguerre{
    private int quantidadePontos;
    private double[][] tabela;

    /**
     * Construtor de classe vazio
     */
    public TabelaLaguerre(){}

    /**
     * Contrutor principal da classe
     * recebe a quantidade de pontos
     * @param quantidadePontos 
     */
    public TabelaLaguerre(int quantidadePontos){
        if(quantidadePontos > 1 &&  quantidadePontos < 9){
            this.quantidadePontos = quantidadePontos;
        }
        
        else{
            System.out.println("Uma quadratura deve possuir entre 2 e 6 pontos.\nFoi digitado um número abaixo ou acima desse intervalo!!!");
            System.exit(-1);
        }
        
        gerarNovaTabela();
    }

    /**
     * Construtor completo de classe
     * recebe a quantidade de pontos
     * e a tabela completa
     * @param quantidadePontos
     * @param tabela 
     */
    public TabelaLaguerre(int quantidadePontos, double[][] tabela){
        if(quantidadePontos > 1 &&  quantidadePontos < 9){
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
        this.tabela[0][0] = 0.8535533905;
        this.tabela[0][1] = 0.1464466094;
        //--------------------
        this.tabela[1][0] = 0.5857864376;
        this.tabela[1][1] = 3.414213562;
        
        /*return this.tabela;*/
    }
    
    private void tabelaTresPontos(){
        this.tabela = new double[2][3];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.7110930099;
        this.tabela[0][1] = 0.2785177335;
        this.tabela[0][2] = 0.01038925650;
        //--------------------
        this.tabela[1][0] = 0.4157745567;
        this.tabela[1][1] = 2.294280360;
        this.tabela[1][2] = 6.289945082;
        
        /*return this.tabela;*/
    }
    
    private void tabelaQuatroPontos(){
        this.tabela = new double[2][4];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.6031541043;
        this.tabela[0][1] = 0.3574186924;
        this.tabela[0][2] = 0.03888790851;
        this.tabela[0][3] = 0.0005392947055;
        //--------------------
        this.tabela[1][0] = 0.3225476896;
        this.tabela[1][1] = 1.745761101;
        this.tabela[1][2] = 4.536620296;
        this.tabela[1][3] = 9.395070912;
        
        /*return this.tabela;*/
    }
    
    private void tabelaCincoPontos(){
        this.tabela = new double[2][5];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.5217556105;
        this.tabela[0][1] = 0.3986668110;
        this.tabela[0][2] = 0.07594244968;
        this.tabela[0][3] = 0.003611758679;
        this.tabela[0][4] = 0.00002336997238;
        //--------------------
        this.tabela[1][0] = 0.2635603197;
        this.tabela[1][1] = 1.413403059;
        this.tabela[1][2] = 3.596425771;
        this.tabela[1][3] = 7.085810005;
        this.tabela[1][4] = 12.64080084;
        
        /*return this.tabela;*/
    }
    
    private void tabelaSeisPontos(){
        this.tabela = new double[2][6];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.4589646739;
        this.tabela[0][1] = 0.4170008307;
        this.tabela[0][2] = 0.1133733820;
        this.tabela[0][3] = 0.01039919745;
        this.tabela[0][4] = 0.0002610172028;
        this.tabela[0][5] = 0.0000008985479064;
        //--------------------
        this.tabela[1][0] = 0.2228466041;
        this.tabela[1][1] = 1.188932101;
        this.tabela[1][2] = 2.992736326;
        this.tabela[1][3] = 5.775143569;
        this.tabela[1][4] = 9.837467418;
        this.tabela[1][5] = 15.98297398;
        
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
            
            default:
                System.out.println("Ocorreu um erro inesperado.\nPor favor, tente novamente.");
        }
    }
}