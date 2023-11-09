from vizinho import vizinho
from utils import avaliacao, peso
import random

def main() -> None:
    # o primeiro é o peso e o segundo é o benefício
    mochila = [[5,1], [4,3], [9,2], [1,3], [5,5]]
    iteracao = melhor_iteracao = 0
    melhor_solucao = []     		# irá guardar a melhor solução
    lista_tabu = []         		# lista tabu inicialmente vazia
    capacidade_maxima = 23  		# capacidade máxima da mochila
    bt_max = 1              		# quantidade máxima de iterações sem melhora no valor da melhor solução
    max_vizinhos = 5        		# quantidade máxima de vizinhos

    for i in range(0, 5): 
        bit = random.randrange(2)   # gera números de 0 (inclusive) a 1 (inclusive)
        melhor_solucao.append(bit)  # adiciona o bit na lista
        
    print('Solução inicial: {0}, Avaliação: {1}'.format(melhor_solucao, avaliacao.obter_avaliacao(melhor_solucao, mochila, capacidade_maxima)))

    peso_corrente = peso.obter_peso(melhor_solucao, mochila)
    melhor_avaliacao = avaliacao.obter_avaliacao(melhor_solucao, mochila, capacidade_maxima)
    vizinhos = vizinho.gerar_vizinhos(melhor_solucao, max_vizinhos)
    vizinhos_avaliacao = vizinho.obter_avaliacao_vizinhos(vizinhos, mochila, capacidade_maxima, max_vizinhos)
    posicao_melhor_vizinho = vizinho.obter_vizinho_melhor_avaliacao(vizinhos_avaliacao, lista_tabu, melhor_solucao, vizinhos)

    if vizinhos_avaliacao[posicao_melhor_vizinho] > melhor_avaliacao:
        bit_modificado = vizinho.obter_bit_modificado(melhor_solucao, vizinhos[posicao_melhor_vizinho])
        lista_tabu.append(bit_modificado)                   	# guarda o movimento proibido
        melhor_solucao = vizinhos[posicao_melhor_vizinho][:]    # temos uma solução melhor, faz uma cópia
        melhor_iteracao += 1

    iteracao += 1

    while True:
        if (iteracao - melhor_iteracao) > bt_max:
            break
        
        vizinhos = vizinho.gerar_vizinhos(melhor_solucao, max_vizinhos)[:]
        vizinhos_avaliacao = vizinho.obter_avaliacao_vizinhos(vizinhos, mochila, capacidade_maxima, max_vizinhos)[:]
        posicao_melhor_vizinho = vizinho.obter_vizinho_melhor_avaliacao(vizinhos_avaliacao, lista_tabu, melhor_solucao, vizinhos)
        
        if vizinhos_avaliacao[posicao_melhor_vizinho] > melhor_avaliacao:
            bit_modificado = vizinho.obter_bit_modificado(melhor_solucao, vizinhos[posicao_melhor_vizinho])
            lista_tabu[0] = bit_modificado                              	# guarda o movimento proibido
            melhor_solucao = vizinhos[posicao_melhor_vizinho][:]            # temos uma solução melhor, faz uma cópia da lista
            melhor_avaliacao = vizinhos_avaliacao[posicao_melhor_vizinho]   # atualiza a melhor avaliação
            melhor_iteracao += 1
        
        iteracao += 1

    print('Solução final: {0}, Avaliação: {1}'.format(melhor_solucao, avaliacao.obter_avaliacao(melhor_solucao, mochila, capacidade_maxima)))
    print('Melhor iteração: {0}'.format(melhor_iteracao))   # mostra a iteração onde foi achada a melhor solução
    print('Iteração: {0}'.format(iteracao))                 # mostra a iteração global
    
main()
