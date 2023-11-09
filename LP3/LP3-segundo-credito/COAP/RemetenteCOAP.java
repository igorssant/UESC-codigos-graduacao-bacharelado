public class RemetenteCOAP extends AbstractRemetente{
	@Override
	public void connect(String enderecoDeDestino){
		System.out.println("Conectando com " + enderecoDeDestino + " .COAP");
		setEnderecoDestino(enderecoDeDestino);
	}

	@Override
	public  void send(AbstractPacote dados){
		System.out.println("Enviado para " +  getEnderecoDestino() + "\n Mensagem: " + dados.getDados() + " COAP");
	}

	@Override
	public void close(){
		System.out.println("COAP fechado");
	}
}
