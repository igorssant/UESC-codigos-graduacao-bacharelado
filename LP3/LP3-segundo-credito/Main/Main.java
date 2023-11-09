public class Main {
    public static void main (String[] args)
    { 
       GUIAbstractFactory qualquer = new FabricaHTTP();
       AbstractPacote qualquerPacote =  qualquer.criarPacote();
       AbstractRemetente qualquerRemetente = qualquer.criarRemetente();
    
      CriptografarComplexo criptografia = new CriptografarComplexo(new Criptografar());
      AutenticarComplexo autenticar = new AutenticarComplexo(new Autenticar());

      criptografia.criptografar();
      autenticar.autenticar();

       qualquerPacote.prepararDados("CPF dos brasileiros");

       qualquerRemetente.connect("www.darkweb.com");
       qualquerRemetente.send(qualquerPacote);
       qualquerRemetente.close();

       System.out.println("\n\n\n");

       qualquerPacote = new PacoteCOAP();
       qualquerRemetente = new RemetenteCOAP();
       qualquer = new COAPAbstractFactory();

       qualquerPacote.prepararDados("Dados bancários");

       qualquerRemetente.connect("www.dadosbancarios.com");
       qualquerRemetente.send(qualquerPacote);
       qualquerRemetente.close();       
    }  
}
