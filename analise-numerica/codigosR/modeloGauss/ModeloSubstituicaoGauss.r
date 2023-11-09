# favor rodar a linha abaixo sempre que iniciar o R-studio
setwd("~/Documents/trabalho/codigosR/modeloGauss")

# funcao para ler os parametros necessarios em aquivo .txt
lerDeArquivo <- function(arquivoDeLeitura){
  dadosLidos <- as.matrix(read.table(arquivoDeLeitura, sep = " ", dec = ".", header = F))
  return(dadosLidos)
}

# funcao para escrever o resultado final em aquivo .txt
escreverEmArquivo <- function(arquivoDeEscrita, dados){
  write.table(x = dados, file = arquivoDeEscrita, sep = "\t", append = F, row.names = F, col.names = F)
}

# funcao que faz a eliminacao de gauss sobre as matrizes
eliminacaoGauss <- function(matrizA, vetorB){
  tamanho <- length(vetorB)
  vetorX <- c(1:tamanho)
  
  # parte da eliminacao
  for(i in 1:(tamanho-1)){
    for(j in (i+1):tamanho){
      multiplicador <- ( matrizA[j, i] / matrizA[i, i] )
      
      #iteracao da matrizA
      for(k in i:tamanho){
       matrizA[j, k] <- matrizA[j, k] - ( multiplicador * matrizA[i, k] )
      }
      
      vetorB[j] <- vetorB[j] - ( multiplicador * vetorB[i] )
    } # for interior > j
  } # for exterior > i
  
  # parte da substituicao
  vetorX[tamanho] <- vetorB[tamanho] / matrizA[tamanho, tamanho]
  
  for(i in (tamanho-1):1){
    somatorio <- vetorB[i]
    
    for(j in (i+1):tamanho){
      somatorio <- somatorio - ( matrizA[i, j] * vetorX[j] )
    } # for interno > j
    
    vetorX[i] <- somatorio / matrizA[i, i]
  } # for externo > i
  
  return (vetorX)
}

main <- function(){
  arquivoDeLeitura <- "entradaDeDados.txt"
  arquivoDeEscrita <- "saidaDeDados.txt"
  
  #dadosLidos eh uma matriz
  dadosLidos <- lerDeArquivo(arquivoDeLeitura)
  corte <- (nrow(dadosLidos)) - 1
  tamanho <- (nrow(dadosLidos))
  
  matrizA <- dadosLidos[1:corte,]             # uma matriz
  vetorB <- dadosLidos[tamanho,]              # um vetor
  vetorX <- eliminacaoGauss(matrizA, vetorB)  # um vetor
  
  escreverEmArquivo(arquivoDeEscrita, vetorX)
}

main()
