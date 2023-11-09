substituicaoReta <- function(L, vetorB){
  Y <- rep( 0, length(vetorB) )
  
  for( i in 1:length(vetorB) ){
    somatorio <- vetorB[i]
    
    for( j in 1:i ){
      somatorio <- somatorio - (Y[j] * L[i, j])
    } # for interno
    
    Y[i] <- somatorio / L[i, i]
  } # for externo
  
  return(Y)
}

substituicaoReversa <- function(U, Y){
  vetorX <- rep( 0, length(Y) )
  
  for( i in length(Y):1 ){
    somatorio <- Y[i]
    
    for( j in i:length(Y) ){
      somatorio = somatorio - ( U[i, j] * vetorX[j] )
    } # for interno
    
    vetorX[i] <- somatorio / U[i, i]
  } # for externo

  return(vetorX)
}

# funcao para a decomposicao da matrizA em um lista de duas matrizes: L e U
decomposicaoLU <- function(matrizA, vetorB){
  # matriz Lower
  matrizL <- matrix( 0.0, length(vetorB), length(vetorB) )
  #matriz Upper
  matrizU <- matrix( 0.0, length(vetorB), length(vetorB) )
  
  # rep() faz um repeticao de um certo valor passado
  # diag() caminha pela diagonal da matriz passada
  # a matriz dese ser quadrada !!!
  diag(matrizL) <- rep( 1, length(vetorB) )
  
  for( i in 1:length(vetorB) ){
    seletorParteInferior <- i + 1
    seletorParteSuperior <- i - 1
    
    for( j in 1:length(vetorB) ){
      matrizU[i, j] <- matrizA[i, j]
      
      if( seletorParteSuperior > 0 ){
        for( k in 1:seletorParteSuperior ){
          matrizU[i, j] <- matrizU[i, j] - ( matrizL[i, k] * matrizU[k, j] )
        }
      } # fim if
    } # for interior
    
    if( seletorParteInferior <= length(vetorB) ){
      for( j in seletorParteInferior:length(vetorB) ){
        matrizL[j, i] <- matrizA[j, i]
        
        if( seletorParteSuperior > 0 ){
          for( k in 1:seletorParteSuperior ){
            matrizL[j, i] <- matrizL[j, i] - ( matrizL[j, k] * matrizU[k, i] )
          }
        } # fim if interior
        
        matrizL[j, i] <- matrizL[j, i] / matrizU[i, i]
      } # for interior
    } # fim if exterior
  } # for exterior
  
  # ( matrizL %*% matrizU ) == matrizA
  listaDecomposta <- list( L = matrizL, U = matrizU )
  return(listaDecomposta)
}

# funcao para a resolucao do metodo
resolucaoLU <- function(listaDecomposta, vetorB){
  Y <- substituicaoReta(listaDecomposta$L, vetorB)
  vetorX <- substituicaoReversa(listaDecomposta$U, Y)
  
  return(vetorX)
}