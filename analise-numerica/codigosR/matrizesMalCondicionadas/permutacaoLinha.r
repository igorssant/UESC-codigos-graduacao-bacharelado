permutaVetor <- function(vetor, linha){
  auxiliar <- vetor[linha]
  vetor[linha] <- vetor[(linha+1)]
  vetor[(linha+1)] <- auxiliar
  
  return(vetor)
}

permutaLinhas <- function(matriz, vetor){
  matrizPermutarda <- matrix(rep(0, length(matriz)),
                     nrow = nrow(matriz),
                     ncol = ncol(matriz))
  
  for( i in 1:nrow(matriz) ){
    if(matriz[i, i] == 0 && i < nrow(matriz)){
      auxiliar <- matriz[i, ]
      matriz[i, ] <- matriz[(1+i), ]
      matriz[(1+i), ] <- auxiliar
      vetor <- permutaVetor(vetor, i)
      i <- 1
    }
    
    else if( matriz[i, i] == 0 && i == nrow(matriz) ){
      auxiliar <- matriz[i, ]
      matriz[i, ] <- matriz[(i-1), ]
      matriz[(i-1), ] <- auxiliar
      vetor <- permutaVetor(vetor, (i-1))
      i <- 1
    }
  }
  
  return( list(MATRIZ = matriz, VETOR = vetor) )
}