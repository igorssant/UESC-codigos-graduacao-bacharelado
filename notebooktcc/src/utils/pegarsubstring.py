def substring(frase:str, separacao:str= "= ") -> str:
    return frase.split(separacao, 1)[1]
    