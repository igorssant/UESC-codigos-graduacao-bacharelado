# SE ESTIVER UTILIZANDO ESTA CLASSE DE MANEIRA DIRETA
# EXCLUA AS LINHA 3 e 4
#
import sys
sys.path.append("./utils/")
import pegarsubstring
import numpy as np

class ParametrosKmeans:
    
    
    # ===================================== #
    # METODO CONSTRUTOR                     #
    # ===================================== #
    def __init__(self,
                 semente_randomica:int = None,
                 nro_iteracoes_inicial:int = None,
                 nro_maximo_iteracoes:int = None,
                 metodo_inicializacao:str = None,
                 nro_clusters:int = None,
                 path_arquivo_hiperparametros:str|None = None,
                 nro_tentivas_centroide:str|int = "auto",
                 tolerancia:int = 0.0001,
                 algoritmo_kmeans:str = "lloyd",
                 fazer_copia:bool = True) -> None:
        if path_arquivo_hiperparametros is None:
            self._semente_randomica = semente_randomica
            self._nro_iteracoes_inicial = nro_iteracoes_inicial
            self._nro_maximo_iteracoes = nro_maximo_iteracoes
            self._metodo_inicializacao = metodo_inicializacao
            self._nro_clusters = nro_clusters
            self._nro_tentativas_centroide_ = nro_tentivas_centroide
            self._tolerancia_ = tolerancia
            self._algoritmo_kmeans_ = algoritmo_kmeans
            self._fazer_copia_ = fazer_copia
            
        else:
            with open(file = path_arquivo_hiperparametros, mode = "r") as arquivo:
                self._semente_randomica = int(pegarsubstring.substring(arquivo.readline()))
                self._nro_iteracoes_inicial = int(pegarsubstring.substring(arquivo.readline()))
                self._nro_maximo_iteracoes = int(pegarsubstring.substring(arquivo.readline()))
                self._metodo_inicializacao = pegarsubstring.substring(arquivo.readline())
                self._nro_clusters = int(pegarsubstring.substring(arquivo.readline()))
                
                possivel_valor_inteiro = pegarsubstring.substring(arquivo.readline())
                
                try:
                    self._nro_tentativas_centroide_ = int(possivel_valor_inteiro)
                except ValueError:
                    self._nro_tentativas_centroide_ = possivel_valor_inteiro
                
                self._tolerancia_ = float(pegarsubstring.substring(arquivo.readline()))
                self._algoritmo_kmeans_ = pegarsubstring.substring(arquivo.readline())
                self._fazer_copia_ = bool(pegarsubstring.substring(arquivo.readline()))
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
    
    def getNro_tentivas_centroide(self) -> int:
        return self._nro_tentativas_centroide_
        
    def getTolerancia(self) -> float:
        return self._tolerancia_
        
    def getAlgoritmo_kmeans(self) -> str:
        return self._algoritmo_kmeans_
    
    def getFazer_copia(self) -> bool:
        return self._fazer_copia_

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
        
    def setNro_tentivas_centroide(self, nro_tentativas_centroide:int) -> None:
        self._nro_tentativas_centroide_ = nro_tentativas_centroide
        
    def setTolerancia(self, tolerancia:float) -> None:
        self._tolerancia_ = tolerancia
        
    def setAlgoritmo_kmeans(self, algoritmo_kmeans:str) -> None:
        self._algoritmo_kmeans_ = algoritmo_kmeans
    
    def setFazer_copia(self, fazer_copia:bool) -> None:
        self._fazer_copia_ = fazer_copia
# FIM CLASSE ParametrosKmeans



class ParametrosMeanshift:
    
    
    # ===================================== #
    # METODO CONSTRUTOR                     #
    # ===================================== #
    def __init__(self,
                 frequencia_minima:int = 1,
                 nro_max_iteracoes:int = 300,
                 clusterizar_todos_pontos:bool = True,
                 usar_versao_discreta_pontos:bool = False,
                 semente_randomica:int = 42,
                 nro_threads:int|None = None,
                 quantil:float = 0.3,
                 qtd_amostras:int|None = None,
                 path_arquivo_hiperparametros:str|None = None) -> None:
        if path_arquivo_hiperparametros is None:
                self._frequencia_minima_ = frequencia_minima
                self._nro_max_iteracoes_ = nro_max_iteracoes
                self._clusterizar_todos_pontos_ = clusterizar_todos_pontos
                self._usar_versao_discreta_pontos_ = usar_versao_discreta_pontos
                self._semente_randomica_ = semente_randomica
                self._nro_threads_ = nro_threads
                self._quantil_ = quantil
                self._qtd_amostras_ = qtd_amostras
            
        else:
            with open(file = path_arquivo_hiperparametros, mode = "r") as arquivo:
                self._frequencia_minima_ = int(pegarsubstring.substring(arquivo.readline()))
                self._nro_max_iteracoes_ = int(pegarsubstring.substring(arquivo.readline()))
                self._clusterizar_todos_pontos_ = bool(pegarsubstring.substring(arquivo.readline()))
                self._usar_versao_discreta_pontos_ = bool(pegarsubstring.substring(arquivo.readline()))
                self._semente_randomica_ = int(pegarsubstring.substring(arquivo.readline()))
                
                possivel_valor_nulo = pegarsubstring.substring(arquivo.readline())
                
                if possivel_valor_nulo == "":
                    self._nro_threads_ = None
                    
                else:
                    self._nro_threads_ = int(possivel_valor_nulo)
                
                self._quantil_ = float(pegarsubstring.substring(arquivo.readline()))
                
                possivel_valor_nulo = pegarsubstring.substring(arquivo.readline())
                
                if possivel_valor_nulo == "":
                    self._qtd_amostras_ = None
                    
                else:
                    self._qtd_amostras_ = int(possivel_valor_nulo)
                    
    # FIM DO METODO CONSTRUTOR
    
    # ===================================== #
    # METODOS GETTER                        #
    # ===================================== #
    def getFrequencia_minima(self) -> int:
        return self._frequencia_minima_
    
    def getNro_max_iteracoes(self) -> int:
        return self._nro_max_iteracoes_
    
    def getClusterizar_todos_pontos(self) -> bool:
        return self._clusterizar_todos_pontos_
    
    def getUsar_versao_discreta_pontos(self) -> bool:
        return self._usar_versao_discreta_pontos_
    
    def getSemente_randomica(self) -> int:
        return self._semente_randomica_
    
    def getNro_threads(self) -> int|None:
        return self._nro_threads_

    def getQuantil(self) -> float:
        return self._quantil_
    
    def getQtd_amostras(self) -> int|None:
        return self._qtd_amostras_
    
    # ===================================== #
    # METODOS SETTER                        #
    # ===================================== #
    def setFrequencia_minima(self, frequencia_minima:int) -> None:
        self._frequencia_minima_ = frequencia_minima
    
    def setNro_max_iteracoes(self, nro_max_iteracoes:int) -> None:
        self._nro_max_iteracoes_ = nro_max_iteracoes
    
    def setClusterizar_todos_pontos(self, clusterizar_todos_pontos:bool) -> None:
        self._clusterizar_todos_pontos_ = clusterizar_todos_pontos
    
    def setUsar_versao_discreta_pontos(self, usar_versao_discreta_pontos:bool) -> None:
        self._usar_versao_discreta_pontos_ = usar_versao_discreta_pontos
    
    def setSemente_randomica(self, semente_randomica:int) -> None:
        self._semente_randomica_ = semente_randomica
    
    def setNro_threads(self, nro_threads:int) -> None:
        self._nro_threads_ = nro_threads
    
    def setQuantil(self, quantil:int) -> None:
        self._quantil_ = quantil
    
    def setQtd_amostras(self, qtd_amostras:int) -> None:
        self._qtd_amostras_ = qtd_amostras
# FIM CLASSE ParametrosMeanshift



class ParametrosCmeans:
    
    
    # ===================================== #
    # METODO CONSTRUTOR                     #
    # ===================================== #
    def __init__(self,
                 nro_clusters:int = 2,
                 parametro_fuzzy:float = 2.0,
                 nro_maximo_iteracoes:int = 300,
                 distancia:str = "Euclidian",
                 semente_randomica:int = 42,
                 tolerancia:float = 0.0001,
                 modelo_treinado = False,
                 nro_threads:int|None = None,
                 path_arquivo_hiperparametros:str|None = None) -> None:
        if path_arquivo_hiperparametros is None:
            self._nro_clusters_ = nro_clusters
            self._parametro_fuzzy_ = parametro_fuzzy
            self._nro_maximo_iteracoes_ = nro_maximo_iteracoes
            self._distancia_ = distancia
            self._semente_randomica_ = semente_randomica
            self._tolerancia_ = tolerancia
            self._modelo_treinado_ = modelo_treinado
            self._nro_threads_ = nro_threads
            
        else:
            with open(file = path_arquivo_hiperparametros, mode = "r") as arquivo:
                self._nro_clusters_ = int(pegarsubstring.substring(arquivo.readline()))
                self._parametro_fuzzy_ = float(pegarsubstring.substring(arquivo.readline()))
                self._nro_maximo_iteracoes_ = int(pegarsubstring.substring(arquivo.readline()))
                self._distancia_ = pegarsubstring.substring(arquivo.readline())
                self._semente_randomica_ = int(pegarsubstring.substring(arquivo.readline()))
                self._tolerancia_ = float(pegarsubstring.substring(arquivo.readline()))
                self._modelo_treinado_ = bool(pegarsubstring.substring(arquivo.readline()))
                
                possivel_valor_nulo = pegarsubstring.substring(arquivo.readline())
                
                if possivel_valor_nulo == "":
                    self._nro_threads_ = None
                    
                else:
                    self._nro_threads_ = int(possivel_valor_nulo)
    # FIM DO METODO CONSTRUTOR
    
    # ===================================== #
    # METODOS GETTER                        #
    # ===================================== #
    def getNro_cluster(self) -> int:
        return self._nro_clusters_

    def getParametro_fuzzy(self) -> float:
        return self._parametro_fuzzy_

    def getNro_maximo_iteracoes(self) -> int:    
        return self._nro_maximo_iteracoes_

    def getDistancia(self) -> str:
        return self._distancia_

    def getSemente_randomica(self) -> int:
        return self._semente_randomica_

    def getTolerancia(self) -> float:
        return self._tolerancia_

    def getModelo_treinado(self) -> bool:
        return self._modelo_treinado_

    def getNro_threads(self) -> int|None:
        return self._nro_threads_

    # ===================================== #
    # METODOS SETTER                        #
    # ===================================== #
    def setNro_cluster(self, nro_clusters:int) -> None:
        self._nro_clusters_ = nro_clusters

    def setParametro_fuzzy(self, parametro_fuzzy:float) -> None:
        self._parametro_fuzzy_ = parametro_fuzzy

    def setNro_maximo_iteracoes(self, nro_maximo_iteracoes:int) -> None:    
        self._nro_maximo_iteracoes_ = nro_maximo_iteracoes

    def setDistancia(self, distancia:str) -> None:
        self._distancia_ = distancia

    def setSemente_randomica(self, semente_randomica:int) -> None:
        self._semente_randomica_ = semente_randomica

    def setTolerancia(self, tolerancia:float) -> None:
        self._tolerancia_ = tolerancia

    def setModelo_treinado(self, modelo_treinado:bool) -> None:
        self._modelo_treinado_ = modelo_treinado

    def setNro_threads(self, nro_threads:int) -> None:
        self._nro_threads_ = nro_threads
# FIM CLASSE ParametrosCmeans

class ParametrosDBSCAN:
    
    
    # ===================================== #
    # METODO CONSTRUTOR                     #
    # ===================================== #
    def __init__(self,
                 epocas:float = 0.5,
                 peso_minimo:int = 5,
                 metrica:str = "euclidean",
                 parametros_metrica:dict|None = None,
                 algoritmo:str = "auto",
                 tamanho_folha:int = 30,
                 metrica_minkowski:float|None = None,
                 nro_threads:int|None = None,
                 semente_randomica:int = 42,
                 path_arquivo_hiperparametros:str|None = None) -> None:
        if path_arquivo_hiperparametros is not None:
            self._epocas_ = epocas
            self._peso_minimo_ = peso_minimo
            self._metrica_ = metrica
            self._algoritmo_ = algoritmo
            self._tamanho_folha_ = tamanho_folha
            self._metrica_minkowski_ = metrica_minkowski
            self._nro_threads_ = nro_threads
            self._semente_randomica_ = semente_randomica
            
        else:
            with open(file = path_arquivo_hiperparametros, mode = "r") as arquivo:
                self._epocas_ = int(pegarsubstring.substring(arquivo.readline()))
                self._peso_minimo_ = int(pegarsubstring.substring(arquivo.readline()))
                self._metrica_ = pegarsubstring.substring(arquivo.readline())
                self._algoritmo_ = pegarsubstring.substring(arquivo.readline())
                self._tamanho_folha_ = pegarsubstring.substring(arquivo.readline())
                self._metrica_minkowski_ = pegarsubstring.substring(arquivo.readline())
                self._nro_threads_ = pegarsubstring.substring(arquivo.readline())
                self._semente_randomica_ = pegarsubstring.substring(arquivo.readline())
                
        self._parametros_metrica_ = parametros_metrica
    # FIM DO METODO CONSTRUTOR

    # ===================================== #
    # METODOS GETTER                        #
    # ===================================== #
    def getEpocas(self) -> int:
        return self._epocas_

    def getPeso_minimo(self) -> int:
        return self._peso_minimo_
    
    def getMetrica(self) -> int:
        return self._metrica_
    
    def getParametros_metrica(self) -> dict|None:
        return self._parametros_metrica_
    
    def getAlgoritmo(self) -> str:
        return self._algoritmo_
    
    def getTamanho_folha(self) -> int:
        return self._tamanho_folha_
    
    def getMetricaMinkowski(self) -> float:
        return self._metrica_minkowski_
    
    def getNro_threads(self) -> int:
        return self._nro_threads_
    
    def getSemente_randomica(self) -> int:
        return self._semente_randomica_

    # ===================================== #
    # METODOS SETTER                        #
    # ===================================== #
    def setEpocas(self, epocas:int) -> None:
        self._epocas_ = epocas

    def setPeso_minimo(self, peso_minimo:int) -> None:
        self._peso_minimo_ = peso_minimo
    
    def setMetrica(self, metrica:int) -> None:
        self._metrica_ = metrica
    
    def setParametros_metrica(self, parametros_metrica:dict) -> None:
        self._parametros_metrica_ = parametros_metrica
    
    def setAlgoritmo(self, algoritmo:str) -> None:
        self._algoritmo_ = algoritmo
    
    def setTamanho_folha(self, tamanho_folha:int) -> None:
        self._tamanho_folha_ = tamanho_folha
    
    def setMetricaMinkowski(self, metrica_minkowski:float) -> None:
        self._metrica_minkowski_ = metrica_minkowski
    
    def setNro_threads(self, nro_threads:int) -> None:
        self._nro_threads_ = nro_threads
        
    def setSemente_randomica(self, semente_randomica:int) -> None:
        self._semente_randomica_ = semente_randomica
# FIM CLASSE ParametrosDBSCAN
