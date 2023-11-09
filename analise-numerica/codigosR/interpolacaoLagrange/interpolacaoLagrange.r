setwd("~/Documents/trabalho/codigosR/interpolacaoLagrange/")

lerDeArquivo <- function(nomeArquivo="entradaDeDados.txt"){
  listaDeArgumentosLidos <- scan(nomeArquivo)
  metade <- floor(length(listaDeArgumentosLidos)/2)
  ponto <- listaDeArgumentosLidos[1]
  x <- listaDeArgumentosLidos[2:(metade+1)]
  fx <- listaDeArgumentosLidos[(metade+2):length(listaDeArgumentosLidos)]
  return(list(P = ponto, X = x, FX = fx))
}

escreverEmArquivo <- function(nomeArquivo="saidaDeDados.txt", dados=NaN){
  write(dados, nomeArquivo, sep = "\n")
}

lagrange <- function(vetor_x, vetor_y, ponto_atual){
  somatorio <- 0
  tamanho <- length(vetor_x)
  
  for(i in 1:tamanho){
    produtorio <- 1
    
    for(j in 1:tamanho){
      if(i != j){
        produtorio <- produtorio * ((ponto_atual - vetor_x[j]) / (vetor_x[i] - vetor_x[j]))
      } 
    } # for interno
    
    somatorio <- somatorio + produtorio * vetor_y[i]
  } # for externo
  
  return(somatorio)
}

main <- function(){
  listaDeConteudoLido <- lerDeArquivo()
  pontoDeInterpolacao <- listaDeConteudoLido$P
  vetor1 <- listaDeConteudoLido$X
  vetor2 <- listaDeConteudoLido$FX

  resultado <- lagrange(vetor1, vetor2, pontoDeInterpolacao)
  
  escreverEmArquivo(dados=resultado)
}

main()
