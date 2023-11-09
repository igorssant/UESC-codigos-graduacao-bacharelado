setwd("~/Documents/trabalho/codigosR/aproximacaoPolinomialDiscreto")

source("~/Documents/trabalho/codigosR/aproximacaoPolinomialDiscreto/regressao.r")
source("~/Documents/trabalho/codigosR/aproximacaoPolinomialDiscreto/LU.r")
source("~/Documents/trabalho/codigosR/aproximacaoPolinomialDiscreto/normalizaMatriz.r")
source("~/Documents/trabalho/codigosR/aproximacaoPolinomialDiscreto/permutacaoLinha.r")

lerDeArquivo <- function(nomeArquivo="entradaDeDados.txt"){
  listaDeArgumentosLidos <- scan(nomeArquivo)
  ordem <- listaDeArgumentosLidos[1]
  resto <- listaDeArgumentosLidos[2:length(listaDeArgumentosLidos)]
  return(list(O = ordem, R = resto))
}

escreverEmArquivo <- function(nomeArquivo="saidaDeDados.txt", dados=NaN){
  write(dados, nomeArquivo, sep = "\n")
}

erroPadrao <- function(soma_r, tamanho, nro_equacoes){
  erro <- sqrt( soma_r / (tamanho - (nro_equacoes + 1)) )
  
  return(erro)
}

regressaoPolinomial <- function(pontos, ordem){
  coeficientes <- matrix(1, nrow = (ordem+1), ncol = (ordem+1))
  matriz_expandida <- matrix(1, nrow = ncol(pontos), ncol = (ordem+1))
  y <- c( rep(1, (ordem+1) ) )
  
  for(linha in 1:ncol(matriz_expandida)){
    for(coluna in 1:nrow(matriz_expandida)){
      matriz_expandida[coluna, linha] <- pontos[1, coluna] ^ (linha - 1)
    }
  }
  
  for(i in 1:(ordem+1)){
    y <- pontos[2, ] %*% matriz_expandida#[, i]
  
    for(j in 1:(ordem+1)){
      coeficientes[i, j] <- matriz_expandida[, i] %*% matriz_expandida[, j]
    }
  }
  
  return(list(X = coeficientes, Y = y))
}

main <- function(){
  listaDeConteudoLido <- lerDeArquivo()
  ordem_polinomio <- listaDeConteudoLido$O
  pontos <- matrix(listaDeConteudoLido$R, nrow = 2, byrow = T)
  
  if(ncol(pontos) < (ordem_polinomio + 1)){
    print("Não é possível realizar a regressão")
    # SALVAR NaN no arquivo de saida
    stop()
  }
  
  listaDeMatrizes <- regressaoPolinomial(pontos, ordem_polinomio)
  print(listaDeMatrizes)
  print(permutaLinhas(listaDeMatrizes$X, listaDeMatrizes$Y))
  Y <- listaDeMatrizes$Y
  
  listaDeMatrizes <- decomposicaoLU(listaDeMatrizes$X, listaDeMatrizes$Y)
  aproximacao <- resolucaoLU(listaDeMatrizes, Y)
  
  print(aproximacao)
  escreverEmArquivo(dados = aproximacao)
}

main()
