setwd("~/Documents/trabalho/codigosR/interpolacaoNewton/")

lerDeArquivo <- function(nomeArquivo = "entradaDeDados.txt"){
  listaDeArgumentosLidos <- scan(nomeArquivo, dec = ".")
  metade <- floor(length(listaDeArgumentosLidos)/2)
  ponto <- listaDeArgumentosLidos[1]
  x <- listaDeArgumentosLidos[2:(metade+1)]
  fx <- listaDeArgumentosLidos[(metade+2):length(listaDeArgumentosLidos)]
  return(list(P = ponto, X = x, FX = fx))
}

escreverEmArquivo <- function(nomeArquivo = "saidaDeDados.txt", dados=NULL){
  write(dados, nomeArquivo, sep = "\n")
}

diferencasDivididas <- function(vetor_x, vetor_y){
  tabela <- matrix(0, nrow = length(vetor_x), ncol = length(vetor_x))
  tamanho <- length(vetor_x)
  tabela[1:(tamanho), 1] <- vetor_y
  
  for(j in 2:tamanho){
    for(i in 1:(tamanho - j + 1)){
      tabela[i, j] <- ( tabela[(i + 1), (j - 1)] - tabela[i, (j - 1)] ) / ( vetor_x[(i + j - 1)] - vetor_x[i] )
    } # for interno
  } # for externo
  
  return(tabela)
}

interpolacaoNewton <- function(vetor_x, vetor_y, ponto_atual, tabelaDiferencas){
  tamanho <- length(vetor_x)
  somatorio <- vetor_y[1]
  
  for(i in 2:tamanho){
    produtorio <- 1
    
    for(j in 1:(i-1)){
      produtorio <- produtorio * (ponto_atual - vetor_x[j])
    } # for interno
    
    somatorio <- somatorio + produtorio * tabelaDiferencas[1, i]
  } # for externo
  
  return(somatorio)
}

main <- function(){
  listaDoConteudoLido <- lerDeArquivo()
  # O ponto para interpolar
  ponto <- listaDoConteudoLido$P
  # x
  vetor1 <- listaDoConteudoLido$X
  # f(x)
  vetor2 <- listaDoConteudoLido$FX
  tabela <- diferencasDivididas(vetor1, vetor2)

  print(tabela)
  print(interpolacaoNewton(vetor1, vetor2, ponto, tabela))
  
}

main()
