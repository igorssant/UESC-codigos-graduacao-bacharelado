# calcula e retorna o somatório do peso
def obter_peso(solucao: list, mochila: any) -> int:
    peso = int(0)
    
    for i in range(0, len(solucao)):
        peso += solucao[i] * mochila[i][0]

    return peso
