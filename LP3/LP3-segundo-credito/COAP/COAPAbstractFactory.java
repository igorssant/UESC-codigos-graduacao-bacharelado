public class COAPAbstractFactory extends GUIAbstractFactory{
    @Override
    public AbstractPacote criarPacote(){
        return new PacoteCOAP();
    }

    @Override
    public AbstractRemetente criarRemetente(){
        return new RemetenteCOAP();
    } 
}
