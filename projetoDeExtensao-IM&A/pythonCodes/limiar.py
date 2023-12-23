import cv2
import numpy as np

imagem_de_Lena = cv2.imread ('Exemplo.png')

# Linha abaixo transforma uma imagem colorida em preto e branco
Lena_preto_e_branco = cv2.cvtColor (imagem_de_Lena, cv2.COLOR_BGR2GRAY)

# Limiarizacao MEAN
limiar1 = cv2.adaptiveThreshold (Lena_preto_e_branco, 255, cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY, 199, 5)

# Salvando as imagens com a biblioteca cv2
cv2.imwrite ('Imagem_limiar_Mean.png', limiar1)

