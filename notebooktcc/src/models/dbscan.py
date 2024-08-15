import numpy as np
import pandas as pd
from sklearn.cluster import DBSCAN

class Bdscan:
    
    
    def __init__(self,
                 epocas = 0.5,
                 peso_minimo = 5,
                 metrica = "euclidean",
                 parametros_metrica = None,
                 algoritmo = "auto",
                 tamanho_folha = 30,
                 metrica_minkowski = None,
                 nro_threads = None) -> None:
        self._epocas_ = epocas
        self._peso_minimo_ = peso_minimo
        self._metrica_ = metrica
        self._parametros_metrica_ = parametros_metrica
        self._algoritmo_ = algoritmo
        self._tamanho_folha_ = tamanho_folha
        self._metrica_minkowski_ = metrica_minkowski
        self._nro_threads_ = nro_threads
        
        self._dbscan = DBSCAN(eps = epocas,
                              min_samples = peso_minimo,
                              metric = metrica,
                              metric_params = parametros_metrica,
                              algorithm = algoritmo,
                              leaf_size = tamanho_folha,
                              p = metrica_minkowski,
                              n_jobs = nro_threads)
    # FIM __init__
    
    def getCore_indices(self) -> np.array:
        # RETORNA UM VETOR DE TAMANHO N
        return self._bdscan.core_sample_indices_
    
    def getComponentes(self) -> np.array:
        # RETORNA UMA MATRIZ MxN
        return self._dbscan.components_
    
    def getDistanciaAoQuadrado(self) -> np.array:
        # RETORNA UM VETOR DE TAMANHO N
        return self._dbscan.labels_
    
    def getNro_features(self) -> int:
        return self._dbscan.n_features_in_
    
    def getNomes_features(self) -> np.array:
        # RETORNA UM VETOR DE TAMANHO N
        return self._dbscan.feature_names_in_
    
    def fit(self, X:pd.DataFrame, sample_weight:np.array = None) -> DBSCAN:
        if sample_weight is not None:
            return self._dbscan.fit(X = X, sample_weight = sample_weight)
        
        else:
            return self._dbscan.fit(X = X)
    # FIM METODO fit()
    
    def fit_predict(self, X:pd.DataFrame, sample_weight:np.array = None) -> np.array:
        if sample_weight is not None:
            return self._dbscan.fit_predict(X = X, sample_weight = sample_weight)
        
        else:
            return self._dbscan.fit_predict(X = X)
    # FIM METODO fit_predict()
    
    def getParams(self, deep:bool = True) -> dict:
        return self._dbscan.get_params(deep = deep)
    
    def setParams(self, **params) -> DBSCAN:
        return self._dbscan.set_params(params = params)
