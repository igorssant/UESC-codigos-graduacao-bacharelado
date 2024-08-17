def substring(frase:str, separacao:str= "= ") -> str:
     try:
          frase = frase.split(separacao, 1)[1]
          frase = frase.split("\n")[0]
     
     except IndexError:
          frase = ""
          
     return frase
