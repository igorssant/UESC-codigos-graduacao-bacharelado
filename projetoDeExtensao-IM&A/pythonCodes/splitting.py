import cv2

# abre a imagem com a máscara do preto e branco
imagem = cv2.imread ('Exemplo.jpg', cv2.IMREAD_GRAYSCALE)

constante_de_split = 127 # Tom para a separação
constante_valor_maximo = 255 # Cor branca

# função que faz o splitting da imagem
thresh, imagem_split = cv2.threshold (imagem, constante_de_split, constante_valor_maximo, cv2.THRESH_BINARY)

# Salvando a imagem
cv2.imwrite ("Imagem_splitting.png", imagem_split)

