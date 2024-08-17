import numpy as np
import pandas as pd
from sklearn.cluster import MeanShift

class Meanshift:
    
    
    def __init__(self,
                 bandwidth:float = None,
                 sementes:np.array = None,
                 usar_versao_discreta_pontos:bool = False,
                 frequencia_minima:int = 1,
                 clusterizar_todos_pontos:bool = True,
                 nro_threads:int = None,
                 nro_max_iteracoes:int = 300) -> None:
        self._bandwidth_ = bandwidth
        self._sementes_ = sementes
        self._usar_versao_discreta_pontos_ = usar_versao_discreta_pontos
        self._frequencia_minima_ = frequencia_minima
        self._clusterizar_todos_pontos_ = clusterizar_todos_pontos
        self._nro_threads_ = nro_threads
        self._nro_max_iteracoes_ = nro_max_iteracoes
        self._meanshift = MeanShift(bandwidth = bandwidth,
                                   seeds = sementes,
                                   bin_seeding = usar_versao_discreta_pontos,
                                   min_bin_freq = frequencia_minima,
                                   cluster_all = clusterizar_todos_pontos,
                                   n_jobs = nro_threads,
                                   max_iter = nro_max_iteracoes)
    # fim metodo __init__

    def getCentroides(self) -> np.array:
        return self._meanshift.cluster_centers_
    
    def getLabels(self) -> np.array:
        return self._meanshift.labels_
    
    def getNro_iteracoes(self) -> int:
        return self._meanshift.n_iter_
    
    def getNroFeaturesObserevadas(self) -> int:
        return self._meanshift.n_features_in_
    
    def getNomesDasFeatures(self) -> np.array:
        return self._meanshift.feature_names_in_

    def fit(self, X:pd.DataFrame) -> MeanShift:
        return self._meanshift.fit(X)

    def fit_predict(self, X:pd.DataFrame) -> np.array:
        return self._meanshift.fit_predict(X)
    
    def get_params(self, deep:bool=True) -> dict:
        return self._meanshift.get_params(deep)
    
    def predict(self, X:pd.DataFrame) -> np.array:
        return self._meanshift.predict(X)
    
    def set_params(self, **params) -> MeanShift:
        return self._meanshift.set_params(params = params)
    