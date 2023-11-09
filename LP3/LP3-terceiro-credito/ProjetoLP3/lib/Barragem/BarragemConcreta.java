package ProjetoLP3.lib.Barragem;

public class BarragemConcreta implements Barragem {

    private int comportasAbertas;
    private int numeroDeComportas;
    private float nivelDoLago;
    private float vazaoDaBarragem;
    private float consumoDeAgua;
    private final int minimoDeComportasBarragem = 3;
    private final int maximoDeComportasAbertas = 10;
    private final int minimoDeComportasAbertas = 3;
    private final float metrosCubicos = 0.5f;
    private final float paraLitros = 1000.0f;
    
    public BarragemConcreta () {} // Construtor Vazio
    
    public BarragemConcreta (int comportasAbertas, int numeroDeComportas, float nivelDoLago, float vazaoDaBarragem, float consumoDeAgua) {
        
        this.comportasAbertas = comportasAbertas;
        this.numeroDeComportas = numeroDeComportas;
        this.nivelDoLago = nivelDoLago;
        this.vazaoDaBarragem = vazaoDaBarragem;
        this.consumoDeAgua = consumoDeAgua;
    }
    
    @Override
    public void abrirComportas (int numero) {
        
        if (this.comportasAbertas >= maximoDeComportasAbertas) {
            
            return ;
        }
        
        this.comportasAbertas += numero;
    }

    @Override
    public void fecharComportas (int numero) {
        
        float vazaoNula = 0;
        
        if (this.comportasAbertas <= minimoDeComportasAbertas) {
            
            return ;
        }
        
        this.comportasAbertas -= numero;
        
        if (this.comportasAbertas == 0) {
            
            setVazao (vazaoNula);
        }  
    }

    @Override
    public void adicionarComporta (int numero) {
        
        if (numero < 1) {
            
            System.out.println ("Erro!!!\nNão é possível adicionar barragens com número negativo (ou nulo).");
            return ;
        }
        
        this.numeroDeComportas += numero; 
    }

    @Override
    public void removeComporta (int numero) {
        
        if (numero < 1) {
            
            System.out.println ("Erro!!!\nNão é possível retirar barragens com número negativo (ou nulo).");
            return ;
        }
        
        else if (numeroDeComportas <= minimoDeComportasBarragem) {
            
            System.out.println ("Erro!!!\nNão é possível retirar mais comportas dessa barragem.");
            return ;
        }
        
        this.numeroDeComportas -= numero;  
    }

    @Override
    public float getNivelLago () {
        
        return this.nivelDoLago;
    }

    @Override
    public void setNivelLago (float quantidade) {
        
        this.nivelDoLago = quantidade;
    }

    @Override
    public float getVazao () {
        
        this.vazaoDaBarragem = comportasAbertas * metrosCubicos; // transforma para litros
        return this.vazaoDaBarragem; // retorna em litros
    }

    @Override
    public void setVazao (float vazao) {
        
        this.vazaoDaBarragem = vazao;
    }

    @Override
    public float getConsumo () {
        
        this.consumoDeAgua = this.vazaoDaBarragem * paraLitros;
        return consumoDeAgua;
    }

    @Override
    public void setConsumo (float consumo) {
        
        this.consumoDeAgua = consumo;
    }   
}