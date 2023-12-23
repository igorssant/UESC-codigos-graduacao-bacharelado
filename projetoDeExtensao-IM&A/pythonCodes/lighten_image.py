import PIL
from PIL import Image
from PIL import ImageEnhance
import numpy as np
from numpy import asarray

imagem = Image.open ("Exemplo.jpg")  # Comando para abrir uma imagem, como objeto
imagem_cinza = imagem.convert (mode = "L") # mode = “L” converte para escala de cinza

imagem_cinza.save ("imagem_cinza.jpg")
imagem2 = Image.open ("imagem_cinza.jpg")

fator_claro = 1.5

# A linha de comando abaixo recebe a imagem, como matriz
clareia_imagem = ImageEnhance.Brightness (imagem_cinza)

# A linha abaixo recebe, como objeto imagem, a matriz clareia_imagem multiplicada por fator_claro
imagem_clara = clareia_imagem.enhance (fator_claro)
imagem_clara.save ('Imagem_clareada.jpg')

