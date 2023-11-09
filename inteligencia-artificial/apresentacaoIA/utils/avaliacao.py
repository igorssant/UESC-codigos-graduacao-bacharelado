def obter_avaliacao(solucao: list, mochila: any, capacidade_maxima: int) -> int:
    somatorio_peso = 0
    somatorio_beneficio = 0

    for i in range(0, len(solucao)):
        somatorio_peso += solucao[i] * mochila[i][0]		# mochila[i][0] acessa o peso
        somatorio_beneficio += solucao[i] * mochila[i][1]	# mochila[i][1] acessa o benefício

    avaliacao = somatorio_beneficio * (1 - max(0, somatorio_peso - capacidade_maxima))
    return avaliacao
