package ProjetoLP3.lib.Relatorio;

import ProjetoLP3.lib.Barragem.BarragemConcreta;
import ProjetoLP3.lib.Rio.Rio;

public class Relatorio {
    
    private Rio rio;

    public Relatorio (Rio rio) {
        
        this.rio = rio;
    }

    public void relatorioDeNivelLago (int barragemIndex) {
        
        BarragemConcreta barragem = (BarragemConcreta) this.rio.listarBarragens ().get (barragemIndex);

        System.out.println(barragem.getNivelLago ());
    }

    public void relatorioDeNivelLago () {
        
        for (BarragemConcreta barragem : this.rio.listarBarragens ()) {
            
            System.out.println (barragem.getNivelLago ());
        }
    }

    public void relatorioConsumo (int barragemIndex) {
        
        BarragemConcreta barragem = (BarragemConcreta) this.rio.listarBarragens ().get (barragemIndex);

        System.out.println (barragem.getConsumo ());
    }

    public void relatorioConsumo () {
        
        for (BarragemConcreta barragem : this.rio.listarBarragens ()) {
            
            System.out.println (barragem.getConsumo ());
        }       
    }

    public void relatatorioVazao (int barragemIndex) {
        
        System.out.println (this.rio.listarBarragens ().get (barragemIndex));
    }

    public void relatatorioVazao () {
        
        for (BarragemConcreta barragem : this.rio.listarBarragens ()) {
            
            System.out.println(barragem.getVazao ());
        }    
    }
}