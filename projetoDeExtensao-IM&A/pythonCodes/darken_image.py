import PIL
from PIL import Image
from PIL import ImageEnhance
import numpy as np
from numpy import asarray

imagem = Image.open ("Exemplo.jpg") # Comando para abrir uma imagem, como objeto
imagem_cinza = imagem.convert (mode = "L") # mode = “L” converte para escala de cinza

imagem_cinza.save ("imagem_cinza.jpg") # Comando para salvar a imagem
imagem2 = Image.open ("imagem_cinza.jpg")

fator_escuro = 0.5

# A linha de comando abaixo recebe a imagem, como matriz
escurece_imagem = ImageEnhance.Brightness (imagem_cinza) 

# A linha abaixo recebe, como objeto imagem, a matriz escurece_imagem multiplicada por fator_escuro
imagem_escura = escurece_imagem.enhance (fator_escuro)
imagem_escura.save ('Imagem_escurecida.jpg')

