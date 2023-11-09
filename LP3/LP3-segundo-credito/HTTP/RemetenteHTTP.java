public class RemetenteHTTP extends AbstractRemetente{
    @Override
    public void connect(String enderecoDeDestino){
        setEnderecoDestino(enderecoDeDestino);
        System.out.println("Conectado ao endereço " + getEnderecoDestino() + ".HTTP");
    }

    @Override
    public void send(AbstractPacote dados){
        System.out.println("Enviando para "+getEnderecoDestino()+"\nMensagem: "+dados.getDados()+" HTTP");
    }

    @Override
    public void close(){
        System.out.println("HTTP fechado");  
    }   
}
