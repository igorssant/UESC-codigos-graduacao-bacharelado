normalizar <- function(matrizParaNormalizar){
  for( i in 1:nrow(matrizParaNormalizar) ){
    maiorDaLinha <- max(matrizParaNormalizar[i, ])
    matrizParaNormalizar[i ,] <- matrizParaNormalizar[i, ] / maiorDaLinha
    
  }
  return(matrizParaNormalizar)
}
