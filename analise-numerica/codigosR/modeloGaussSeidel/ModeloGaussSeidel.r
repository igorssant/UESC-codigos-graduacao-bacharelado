# favor rodar a linha abaixo sempre que iniciar o R-studio
#setwd("~/Documents/trabalho/codigosR/modeloGaussSeidel")

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

modeloGaussSeidel <- function(matrizA, vetorB, tolerancia=9e-7, maximoDeIteracoes=10){
  contador <- 0
  xK <- rep(0, length(vetorB))
  
  while(contador < maximoDeIteracoes){
    for( i in 1:length(vetorB) ){
      somatorio <- 0
      
      for( j in 1:length(vetorB) ){
        if(i != j){
          somatorio <- somatorio + ( matrizA[i, j] * xK[j] )
        }
      } # for interno
      
      xK[i] <- (vetorB[i] - somatorio) / matrizA[i, i]
    } # for externo
    
    contador <- 1 + contador
  }
  
  return(xK)
}

main <- function(){
  arquivoDeLeitura <- "entradaDeDados.txt"
  arquivoDeOpcoes <- "opcoes.txt"
  arquivoDeEscrita <- "saidaDeDados.txt"
  
  #dadosLidos eh uma matriz
  dadosLidos <- lerDeArquivo(arquivoDeLeitura)
  corte <- (nrow(dadosLidos)) - 1
  tamanho <- (nrow(dadosLidos))
  
  matrizA <- dadosLidos[1:corte, 1:corte] # uma matriz
  vetorB <- dadosLidos[tamanho,]          # um vetor
  
  dadosLidos <- lerOpcoes(arquivoDeOpcoes)
  tolerancia <- dadosLidos[1]
  
  if(length(dadosLidos) > 1){
    maximoDeIteracoes <- dadosLidos[2]
  }

  vetorX <- modeloGaussSeidel(matrizA, vetorB, tolerancia, maximoDeIteracoes)  # um vetor
  print(vetorX)
  #escreverEmArquivo(arquivoDeEscrita, vetorX)
}

main()
