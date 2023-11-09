public class FabricaHTTP extends GUIAbstractFactory {
    @Override
    public AbstractPacote criarPacote() {
        return new PacoteHTTP ();   
    }

    @Override
    public AbstractRemetente criarRemetente () {
        return new RemetenteHTTP ();  
    } 
}
