import pegarsubstring

class Parametros:
    
    
    # ===================================== #
    # METODO CONSTRUTOR                     #
    # ===================================== #
    def __init__(self,
                 semente_randomica:int = None,
                 nro_iteracoes_inicial:int = None,
                 nro_maximo_iteracoes:int = None,
                 metodo_inicializacao:str = None,
                 nro_clusters:int = None,
                 parametro_fuzzy:int = None,
                 path_arquivo_hiperparametros:str = None) -> None:
        if path_arquivo_hiperparametros is None:
            self._semente_randomica = semente_randomica
            self._nro_iteracoes_inicial = nro_iteracoes_inicial
            self._nro_maximo_iteracoes = nro_maximo_iteracoes
            self._metodo_inicializacao = metodo_inicializacao
            self._nro_clusters = nro_clusters
            self._parametro_fuzzy = parametro_fuzzy
            
        else:
            with open(file = path_arquivo_hiperparametros, mode = "r") as arquivo:
                self._semente_randomica = pegarsubstring.substring(arquivo.readline())
                self._nro_iteracoes_inicial = pegarsubstring.substring(arquivo.readline())
                self._nro_maximo_iteracoes = pegarsubstring.substring(arquivo.readline())
                self._metodo_inicializacao = pegarsubstring.substring(arquivo.readline())
                self._nro_clusters = pegarsubstring.substring(arquivo.readline())
                self._parametro_fuzzy = pegarsubstring.substring(arquivo.readline())
    # FIM DO METODO CONSTRUTOR
    
    # ===================================== #
    # METODOS GETTER                        #
    # ===================================== #
    
    def getSemente_randomica(self) -> int:
        return self._semente_randomica
    
    def getNro_iteracoes_inicial(self) -> int:
        return self._nro_iteracoes_inicial
    
    def getNro_maximo_iteracoes(self) -> int:
        return self._nro_maximo_iteracoes
    
    def getMetodo_inicializacao(self) -> str:
        return self._metodo_inicializacao
    
    def getNro_clusters(self) -> int:
        return self._nro_clusters
    
    def getParametroFuzzy(self) -> int:
        return self._parametro_fuzzy

    # ===================================== #
    # METODOS SETTER                        #
    # ===================================== #
    def setSemente_randomica(self, semente_randomica:int) -> None:
        self._semente_randomica = semente_randomica
    
    def setNro_iteracoes_inicial(self, nro_iteracoes_inicial:int) -> None:
        self._nro_iteracoes_inicial = nro_iteracoes_inicial
    
    def setNro_maximo_iteracoes(self, nro_maximo_iteracoes:int) -> None:
        self._nro_maximo_iteracoes = nro_maximo_iteracoes
    
    def setMetodo_inicializacao(self, metodo_inicializacao:str) -> None:
        self._metodo_inicializacao = metodo_inicializacao
    
    def setNro_clusters(self, nro_clusters:int) -> None:
        self._nro_clusters = nro_clusters

    def setParametroFuzzy(self, parametro_fuzzy) -> None:
        self._parametro_fuzzy = parametro_fuzzy
