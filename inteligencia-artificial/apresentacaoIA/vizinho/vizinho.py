from utils import avaliacao

def gerar_vizinhos(melhor_solucao: list, max_vizinhos: int) -> list:
    vizinhos = []
    posicao = 0

    for i in range(0, max_vizinhos):
        vizinho = []
        
        for j in range(0, len(melhor_solucao)):
            if  j == posicao:
                if melhor_solucao[j] == 0:
                    vizinho.append(1)
                else:
                    vizinho.append(0)
            else:
                vizinho.append(melhor_solucao[j])
        
        vizinhos.append(vizinho)
        posicao += 1

    return vizinhos

# obtem o valor de avaliação de cada vizinho
def obter_avaliacao_vizinhos(vizinhos: list, mochila: any, capacidade_maxima: int, max_vizinhos: int) -> list:
    vizinhos_avaliacao = []

    for i in range(0, max_vizinhos):
        vizinhos_avaliacao.append(avaliacao.obter_avaliacao(vizinhos[i], mochila, capacidade_maxima))
    
    return vizinhos_avaliacao

def obter_bit_modificado(melhor_solucao: list, melhor_vizinho: list) -> int:
    for i in range(0, len(melhor_solucao)):
        if melhor_solucao[i] != melhor_vizinho[i]:
            return i

# lista_tabu: lista tabu para proibir determinada modificação de bit
# melhor_solucao: melhor solução corrente
# vizinhos: lista com todos os vizinhos
def obter_vizinho_melhor_avaliacao(vizinhos_avaliacao: list, lista_tabu: list, melhor_solucao: list, vizinhos: list) -> int:
    maxima_avaliacao = max(vizinhos_avaliacao)
    posicao = 0
    bit_proibido = -1
    
    # verifica se a lista tabu não possui elementos
    # se possuir, é porque tem bit proibido, então pega esse bit
    if len(lista_tabu) != 0:
        bit_proibido = lista_tabu[0]
    
    # obter a posição do melhor vizinho
    for i in range(0, len(vizinhos_avaliacao)):
        if vizinhos_avaliacao[i] == maxima_avaliacao:
            posicao = i
            break
        
    # vizinho é resultado de movimento proibido?
    if bit_proibido != -1:
        
        # obtém a posição do bit que foi modificado para gerar esse vizinho
        bit_posicao = obter_bit_modificado(melhor_solucao, vizinhos[posicao])
        
        # é um bit que está na lista_tabu ??? (compara com bit_proibido)
        if bit_posicao == bit_proibido:
            melhor_posicao = 0
            
            # procura o segundo melhor vizinho
            for i in range(1, len(vizinhos_avaliacao)):
                if i != bit_posicao:
                    if vizinhos_avaliacao[i] > vizinhos_avaliacao[melhor_posicao]:
                        melhor_posicao = i
                        
            return melhor_posicao
    
    return posicao
