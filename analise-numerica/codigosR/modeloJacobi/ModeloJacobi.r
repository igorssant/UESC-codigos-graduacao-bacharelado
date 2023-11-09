# favor rodar a linha abaixo sempre que iniciar o R-studio
setwd("~/Documents/trabalho/codigosR/modeloJacobi")

# funcao para ler os parametros necessarios em aquivo .txt
lerDeArquivo <- function(arquivoDeLeitura){
  dadosLidos <- as.matrix(read.table(arquivoDeLeitura, sep = " ", dec = ".", header = F))
  return(dadosLidos)
}

lerOpcoes <- function(arquivoDeLeitura){
  dadosLidos <- as.list(readLines(arquivoDeLeitura, skipNul = F, encoding = "utf-8", warn = F))
  return(dadosLidos)
}

# funcao para escrever o resultado final em aquivo .txt
escreverEmArquivo <- function(arquivoDeEscrita, dados){
  write.table(x = dados, file = arquivoDeEscrita, sep = "\t", append = F, row.names = F, col.names = F)
}

metodoJacobi <- function(matrizA, vetorB, tolerancia=9e-7, maximoDeIteracoes=10){
  contador <- 0
  auxiliar <- rep(0, length(vetorB) )
  vetorX <- auxiliar
  
  while(contador < maximoDeIteracoes){
    for(i in 1:length(vetorB)){
      somatorio <- 0.0
      
      for(j in 1:length(vetorB)){
        if(i != j){
          somatorio <- somatorio + matrizA[i, j] * auxiliar[j]
        }
      } # for interno
      
      vetorX[i] <- (vetorB[i] - somatorio) / matrizA[i, i]
    } # for externo
    
    erroRelativo <- abs(( max(vetorX) - max(auxiliar) ) / max(vetorX) )
    
    if(erroRelativo <= tolerancia){
      break
    }
    
    auxiliar <- vetorX
    contador <- contador + 1
  }
  
  return(vetorX)
}

main <- function(){
  arquivoDeLeitura <- "entradaDeDados.txt"
  arquivoDeOpcoes <- "opcoes.txt"
  arquivoDeEscrita <- "saidaDeDados.txt"
  
  #dadosLidos eh uma matriz
  dadosLidos <- lerDeArquivo(arquivoDeLeitura)
  corte <- (nrow(dadosLidos)) - 1
  tamanho <- (nrow(dadosLidos))
  
  matrizA <- dadosLidos[1:corte, 1:corte]                                 # uma matriz
  vetorB <- dadosLidos[tamanho,]                                          # um vetor
  
  dadosLidos <- lerOpcoes(arquivoDeOpcoes)
  tolerancia <- dadosLidos[1]
  
  if(length(dadosLidos) > 1){
    maximoDeIteracoes <- dadosLidos[2]
  }
  
  vetorX <- metodoJacobi(matrizA, vetorB, tolerancia, maximoDeIteracoes)  # um vetor

  escreverEmArquivo(arquivoDeEscrita, vetorX)
}

main()