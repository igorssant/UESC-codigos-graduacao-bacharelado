import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe pública No.
 * A classe possui o atributo `MAXIMO_DE_VIZINHOS`.
 * A classe possui o método `getValorInfinito()`.
 * 
 * O método público da classe é getValorInfinito() -> Integer.
 * 
 * Os objetos possuem os atributos:
 * - nomeNo :: Character;
 * - vizinhos :: List;
 * - pesosParaVizinhos :: AbstractMap<Character, Integer>;
 * - vetorDeDistancias :: Integer[].
 * 
 * Os métodos públicos dos objetos são:
 * - setVizinhos(List<No> vizinhos) -> void;
 * - setPesos(List<Integer> pesos) -> void;
 * - setVetorDeDistancias(Integer[] novoVetorDeDistancias) -> void;
 * - getNomeNo() -> Charater;
 * - getVizinhos() -> List<No>;
 * - getVizinho(Integer indice) -> No;
 * - getPesos() -> AbstractMap<Character, Integer>;
 * - getPeso(Character no) -> Integer;
 * - getVetorDeDistancias() -> Integer[].
 */
public class No {
    public static final Integer MAXIMO_DE_VIZINHOS = 7;
    private final Character nomeNo;
    private final List<No> vizinhos;
    private final AbstractMap<Character, Integer> pesosParaVizinhos;
    private Integer[] vetorDeDistancias;
    
    /**
     * Construtor de classe básico.
     * Recebe como parâmetro o nome do nó.
     * Instância, internamente, a lista de vizinhos,
     * a lista de pesos e o vetor de distâncias.
     * Não possui retorno.
     * @param nomeNo Character
     */
    public No(Character nomeNo) {
        this.nomeNo = nomeNo;
        this.vizinhos = new ArrayList<>(MAXIMO_DE_VIZINHOS);
        this.pesosParaVizinhos = new HashMap<>(MAXIMO_DE_VIZINHOS);
        this.vetorDeDistancias = new Integer[MAXIMO_DE_VIZINHOS];
    }

    /**
     * Construtor de classe No.
     * Recebe como parâmetros: o nome do nó, a lista de vizinhos do nó,
     * a lista de pesos para chegar aos vizinhos e, internamente, instancia
     * o vetor de distâncias (que estará vazio).
     * Não possui retorno.
     * @param nomeNo Character
     * @param vizinhos List<No>
     * @param pesos List<Integer>
     * @throws Exception `quantidade de vizinhos != quantidade de pesos`
     */
    public No(Character nomeNo, List<No> vizinhos, List<Integer> pesos) throws Exception {
        if(vizinhos.size() != pesos.size()) {
            throw new Exception("A quantidade de vizinhos é diferente da quantidade de pesos.");
        }

        this.nomeNo = nomeNo;
        this.vizinhos = new ArrayList<>(vizinhos.size());
        this.pesosParaVizinhos = new HashMap<>(vizinhos.size());
        this.vetorDeDistancias = new Integer[MAXIMO_DE_VIZINHOS];

        vizinhos.forEach(
            (no) -> this.vizinhos.add(no)
        );
        
        for(Integer i = 0; i < this.vizinhos.size(); i++) {
            this.pesosParaVizinhos.put(
                this.vizinhos.get(i).getNomeNo(),
                pesos.get(i)
            );
        }
    }

    /**
     * Construtor completo da classe Nó.
     * Recebe todos os atributos como parâmetros.
     * Não possui retorno.
     * @param nomeNo Character
     * @param vizinhos List<No>
     * @param pesos List<Integer>
     * @param vetorDeDistancias Integer[]
     * @throws Exception `quantidade de vizinhos != quantidade de pesos` || `tamanho de vetorDeDistancias != 7`
     */
    public No(Character nomeNo, List<No> vizinhos, List<Integer> pesos, Integer[] vetorDeDistancias) throws Exception {
        if(vizinhos.size() != pesos.size()) {
            throw new Exception("A quantidade de vizinhos é diferente da quantidade de pesos.");
        }

        if(vetorDeDistancias.length != 7) {
            throw new Exception("O vetor de distâncias deve possuir exatamente ");
        }

        this.nomeNo = nomeNo;
        this.vizinhos = new ArrayList<>(vizinhos.size());
        this.pesosParaVizinhos = new HashMap<>(vizinhos.size());
        this.vetorDeDistancias = vetorDeDistancias;

        vizinhos.forEach(
            (no) -> this.vizinhos.add(no)
        );
        
        for(Integer i = 0; i < this.vizinhos.size(); i++) {
            this.pesosParaVizinhos.put(
                this.vizinhos.get(i).getNomeNo(),
                pesos.get(i)
            );
        }
    }

    /**
     * Método utilitário da classe No.
     * Retorna o valor que será utilizado como Infinito.
     * @return Integer.MAX_VALUE
     */
    public static Integer getValorInfinito() {
        return Integer.MAX_VALUE;
    }

    /**
     * Método Setter do atributo `vizinhos`.
     * Recebe como parâmetro uma lista de objetos da classe No.
     * Deve ser usado se, e somente se, a lista de vizinhos
     * não foi preenchida durante a criação do objeto da classe.
     * Não possui retorno.
     * @param vizinhos List<No>
     * @throws Exception `Houve a tentativa de sobreescrever os vizinhos originais`
     */
    public void setVizinhos(List<No> vizinhos) throws Exception {
        vizinhos.forEach((no) -> this.vizinhos.add(no));
    }

    /**
     * Método Setter do atributo `pesosParaVizinhos`.
     * Recebe como parâmetro uma lista de pesos, do tipo Integer.
     * Deve ser usado se, e somente se, a lista de pesos
     * não foi preenchida durante a criação do objeto da classe.
     * Não possui retorno.
     * @param pesos List<Integer>
     * @throws Exception `Houve a tentativa de sobreescrever os pesos originais` || `A quantidade de vizinhos é diferente da quantidade de pesos`
     */
    public void setPesos(List<Integer> pesos) throws Exception {
        if(!this.pesosParaVizinhos.isEmpty()) {
            throw new Exception("Não é possível sobreescrever os pesos originais de um Nó.");
        }

        if(pesos.size() != this.vizinhos.size()) {
            throw new Exception("A quantidade de vizinhos é diferente da quantidade de pesos.");
        }

        for(Integer i = 0; i < pesos.size(); i++) {
            this.pesosParaVizinhos.put(
                this.vizinhos.get(i).getNomeNo(),
                pesos.get(i)
            );
        }
    }

    /**
     * Método Setter do atributo `vetorDeDistancias`
     * Recebe como parâmetro um vetor de tipo Integer.
     * Pode ser usado para inicializar o vetor,
     * <b>NÃO</b> para atualizar seus valores originais.
     * Não possui retorno.
     * @param novoVetorDeDistancias Integer[]
     */
    public void setVetorDeDistancias(Integer[] novoVetorDeDistancias) {
        this.vetorDeDistancias = novoVetorDeDistancias;
    }

    /**
     * Método Getter do atributo `nomeNo`.
     * Não recebe parâmetros.
     * Usado para retornar o nome do nó em questão.
     * Retorna o nome do nó, do tipo Character.
     * @return nomeNo :: Character
     */
    public Character getNomeNo() {
        return this.nomeNo;
    }

    /**
     * Método Getter do atributo `vizinhos`.
     * Não recebe parâmetros.
     * Usado para retornar a lista de vizinhos do nó em questão.
     * Retorna a lista de nós vizinhos, do tipo List<No>.
     * @return vizinhos :: List<No>
     */
    public List<No> getVizinhos() {
        return this.vizinhos;
    }

    /**
     * Método Getter do atributo `vizinhos`.
     * Recebe como parâmetro o índice de um nó.
     * Usado para retornar um único nó que esteja contido
     * na lista de vizinhos do objeto No em questão.
     * Retorna um único nó, do tipo No.
     * @param indice Integer
     * @return um único nó :: No
     * @throws IndexOutOfBoundsException caso o índice não exista
     */
    public No getVizinho(Integer indice) throws IndexOutOfBoundsException {
        return this.vizinhos.get(indice);
    }

    /**
     * Método Getter do atributo `pesosParaVizinhos`.
     * Não recebe parâmetros.
     * Usado para retornar um dicionário contendo todos os nós
     * vizinho e seus respectivos pesos. É indicado fazer o cast
     * do retorno deste método para a classe HashMap.
     * Retorna um AbstractMap contendo todos os nós
     * vizinho e seus respectivos pesos.
     * @return pesosParaVizinhos :: AbstractMap<Character, Integer>
     */
    public AbstractMap<Character, Integer> getPesos() {
        return this.pesosParaVizinhos;
    }

    /**
     * Método Getter do atributo `pesosParaVizinhos`.
     * Recebe como parâmetro o nome de um nó, do tipo Character.
     * Usado para retornar o peso para o nó vizinho a partir do seu nome.
     * Retorna o peso para se chegar a um nó vizinho, caso o nó seja
     * vizinho de fato, a partir do nome do nó desejado.
     * @param no Character
     * @return pesoParaNoVizinho :: Integer
     */
    public Integer getPeso(Character no) {
        return this.pesosParaVizinhos.get(no);
    }

    /**
     * Método Getter do atributo `vetorDeDistancias`.
     * Não recebe parâmetros.
     * Usado para retornar o vetor de distâncias atual do
     * nó em questão.
     * Retorna o vetor de distâncias do nó em questão.
     * @return vetorDeDistancias :: Integer[]
     */
    public Integer[] getVetorDeDistancias() {
        return this.vetorDeDistancias;
    }

    public void atualizarVetorDeDistancias(Integer[] novoVetor, Character qualVizinho) {
        Integer peso = this.pesosParaVizinhos.get(qualVizinho);

        for(Integer i = 0; i < novoVetor.length; i++) {
            if(novoVetor[i] < No.getValorInfinito()) {
                novoVetor[i] = novoVetor[i] + peso;
            }
        }

        for(Integer i = 0; i < this.vetorDeDistancias.length; i++) {
            if(this.vetorDeDistancias[i] > novoVetor[i]) {
                this.vetorDeDistancias[i] = novoVetor[i];
            }
        }
    }
}
