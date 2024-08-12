import numpy as np
import pandas as pd
from sklearn.cluster import KMeans

class Kmeans:
    
    
    def __init__(self,
                 nro_clusters:int = 2,
                 nro_inicial_centroides:str = "random",
                 nro_tentativas_centroides:str = "auto",
                 nro_max_iteracao:int = 300,
                 tolerancia:float = 0.0001,
                 verbose = 0,
                 semente = None,
                 fazer_copia = True,
                 algoritmo = "lloyd") -> None:
        self._nro_clusters_ = nro_clusters
        self._nro_inicial_centroides_ = nro_inicial_centroides
        self._nro_tentativas_centroides_ = nro_tentativas_centroides
        self._nro_max_iteracao_ = nro_max_iteracao
        self._tolerancia_ = tolerancia
        self._verbose_ = verbose
        self._semente_ = semente
        self._fazer_copia_ = fazer_copia
        self._algoritmo_ = algoritmo
        self._kmeans = KMeans(n_clusters = nro_clusters,
                             init = nro_inicial_centroides,
                             n_init = nro_tentativas_centroides,
                             max_iter = nro_max_iteracao,
                             tol = tolerancia,
                             verbose = verbose,
                             random_state = semente,
                             copy_x = fazer_copia,
                             algorithm = algoritmo)
    # FIM __init__
    
    def getCentroides(self) -> np.array:
        return self._kmeans.cluster_centers_
    
    def getlabels(self) -> np.array:
        return self._kmeans.labels_
    
    def getDistanciaAoQuadrado(self) -> float:
        return self._kmeans.inertia_
    
    def getNumeroIteracoes(self) -> int:
        return self._kmeans.n_iter_
    
    def getNomesFeatures(self) -> np.array:
        return self._kmeans.n_features_in_
    
    def fit(self, X:pd.DataFrame, sample_weight:np.array = None) -> KMeans:
        return self._kmeans.fit(X = X, sample_weight = sample_weight)
    
    def fit_predict(self, X:pd.DataFrame, sample_weight:np.array = None) -> KMeans:
        return self._kmeans.fit_predict(X = X, sample_weight = sample_weight)
        
    def fit_transform(self, X:pd.DataFrame, sample_weight:np.array = None) -> np.array:
        return self._kmeans.fit_transform(X = X, sample_weight = sample_weight)
    
    def get_feature_names_out(self, input_features:np.array = None) -> np.array:
        return self._kmeans.get_feature_names_out(input_features)
    
    def get_params(self, deep:bool = True) -> dict:
        return self._kmeans.get_params(deep)
    
    def predict(self, X:pd.DataFrame, sample_weight:np.array = None) -> np.array:
        return self._kmeans.predict(X = X, sample_weight = sample_weight)
    
    def score(self, X:pd.DataFrame, sample_weight:np.array = None) -> float:
        return self._kmeans.score(X = X, sample_weight = sample_weight)
    
    def set_params(self, **params) -> KMeans:
        return self._kmeans.set_params(params = params)
    
    def transform(self, X:pd.DataFrame) -> np.array:
        return self._kmeans.transform(X = X)
    