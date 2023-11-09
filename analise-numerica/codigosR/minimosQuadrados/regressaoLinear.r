setwd("~/Documents/trabalho/codigosR/minimosQuadrados/")

lerDeArquivo <- function(nomeArquivo="entradaDeDados.txt"){
  listaDeArgumentosLidos <- scan(nomeArquivo)
  metade <- length(listaDeArgumentosLidos) / 2
  x <- listaDeArgumentosLidos[1: metade]
  fx <- listaDeArgumentosLidos[(metade+1):length(listaDeArgumentosLidos)]
  return(list(X = x, FX = fx))
}

escreverEmArquivo <- function(nomeArquivo="saidaDeDados.txt", dados=NULL){
  sink(nomeArquivo)
  print(dados)
  sink()
}

desvioPadrao <- function(vetor){
  tamanho <- length(vetor)
  somatorio <- 0
  
  for(i in 1:tamanho){
    somatorio <- somatorio + vetor[i]
  }
  
  media <- somatorio / tamanho
  desvio <- 0
  
  for(i in 1:tamanho){
    desvio <- desvio + (vetor[i] - media)^2
  }
  
  desvio <- sqrt(desvio / (tamanho - 1))
  
  return(desvio)
}

erroPadrao <- function(soma_r, tamanho){
  erro <- sqrt( soma_r / (tamanho - 2) )

  return(erro)
}

variancia <- function(vetor){
  tamanho <- length(vetor)
  mediaAritmetica <- mean(vetor)
  somatorio <- 0
  
  for(i in 1:tamanho){
    somatorio <- somatorio + (vetor[i] - mediaAritmetica)^2
  }
  
  variancia <- somatorio / (tamanho - 1)
  
  return(variancia)
}

coeficienteDeVariacao <- function(vetor){
  mediaAritmetica <- mean(vetor)
  desvio <- desvioPadrao(vetor)
  coeficiente <- desvio / mediaAritmetica
  
  return(coeficiente * 100)
}

coeficienteDeVariacaoValor <- function(soma_t, soma_r){
  r2 <- (soma_t - soma_r) / soma_t

  return(r2)
}

calculaA1 <- function(soma_x, soma_y, soma_xy, soma_x2, tamanho){
  dividendo <- ( (tamanho * soma_xy) - (soma_x * soma_y) )
  divisor <- ( (tamanho * soma_x2) - (soma_x^2) )
  resultado <- dividendo / divisor
  
  return(resultado)
}

calculaA0 <- function(media_x, media_y, a1){
  resultado <- media_y - (a1 * media_x)
  
  return(resultado)
}

regressaoLinear <- function(vetor_1, vetor_2){
  soma_x <- 0
  soma_y <- 0
  soma_xy <- 0
  soma_x2 <- 0
  soma_t <- 0
  soma_r <- 0
  tamanho <- length(vetor_1)
  
  for(i in 1:tamanho){
    soma_x <- soma_x + vetor_1[i]
    soma_y <- soma_y + vetor_2[i]
    soma_xy <- soma_xy + (vetor_1[i] * vetor_2[i])
    soma_x2 <- soma_x2 + (vetor_1[i]^2)
  }
  
  media_x <- mean(soma_x)
  media_y <- mean(soma_y)
  a1 <- calculaA1(soma_x, soma_y, soma_xy, soma_x2, tamanho) 
  a0 <- calculaA0(media_x, media_y, a1)
  
  for(i in i:tamanho){
    soma_t <- soma_t + ((vetor_2[i] - media_y)^2)
    soma_r <- soma_r + ((vetor_2[i] - (a1 * vetor_1[i]) - a0)^2)
  }
  
  syx <- erroPadrao(soma_r, tamanho)
  r2 <- coeficienteDeVariacaoValor(soma_t, soma_r)
  
  return(list(SYX = syx, R2 = r2))
}

main <- function(){
  listaDeConteudoLido <- lerDeArquivo()
  vetor <- listaDeConteudoLido$X
  vetor_2 <- listaDeConteudoLido$FX
  desvio <- desvioPadrao(vetor)
  variancia <- variancia(vetor)
  coeficiente <- coeficienteDeVariacao(vetor)
  regressao <- regressaoLinear(vetor, vetor_2)
  print(desvio)
  print(variancia)
  print(paste(round(coeficiente/100, 2), "%"))
  print(regressao)
  escreverEmArquivo(
    dados = list(
      paste("sd =", desvio),
      paste("var =", variancia),
      paste("coef =", coeficiente/100, "%"), regressao)
  )
}

main()
