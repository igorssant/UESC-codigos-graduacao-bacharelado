import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import mpi.*;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * Meu computador é um i7 - gen 11;
 * 8 core - 16 threads.
 * Ao rodar o algoritmo de vetor de distância
 * implementado com MPI, 8 processos serão gerados
 * e executados ao mesmo tempo.
 * 
 * Irei me aproveitar dos 8 cores e fazer
 * 1 processo para cada no do grafo. 
 * Sao 7 nos, entao serao: [0 - 6] processos.
 * 
 * BIBLIOGRAFIA:
 * https://blogs.cisco.com/performance/java-bindings-for-open-mpi
 * https://github.com/open-mpi/ompi-java-test
 * https://github.com/open-mpi/ompi/blob/main/examples/Hello.java
 * https://docs.open-mpi.org/en/v5.0.x/features/java.html
 */


/**
 * Classe VetorDistancia.
 * Classe principal que ira executar o
 * algoritmo de vetor de distancia.
 * Nao possui atributos.
 * Possui somente um metodo publico -> main().
 */
public class VetorDistancia {
    private static List<List<Integer>> lerArquivo(final String caminhoParaArquivo) {
        List<List<Integer>> listaDeValores = new ArrayList<>();
        
        try(BufferedReader buffer = new BufferedReader(new FileReader(caminhoParaArquivo))) {
            String linha = null;

            for(int i = 0; i < No.MAXIMO_DE_VIZINHOS; i++) {
                linha = (buffer.readLine()).replaceAll("\\s+", "");
                String[] possiveisValores = linha.split(";");
                List<Integer> valoresNumericos = new ArrayList<>(No.MAXIMO_DE_VIZINHOS);
                
                for(Integer j = 0; j < (possiveisValores.length - 1); j++) {
                    valoresNumericos.add(Integer.parseInt(possiveisValores[j]));
                }

                listaDeValores.add(valoresNumericos); 
            }
        } catch(FileNotFoundException e) {
            System.err.println("Arquivo não foi achado.");
            System.err.println(e);
        } catch(IOException e) {
            System.err.println("Não foi possível acessar o arquivo.");
            System.err.println(e);
        } catch(Exception e) {
            System.err.println("Ocorreu algum erro inesperado.");
            System.err.println(e);
        }
        
        return listaDeValores;
    }
    
    public static void salvarSaidaEmArquivo(final String caminhoParaArquivo, final Character nomeNo, final Integer[] vetorDeDistancia) {
        try(BufferedWriter buffer = new BufferedWriter(new FileWriter(caminhoParaArquivo, true))) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(nomeNo + " : [\t");

            for(Integer valor : vetorDeDistancia) {
                stringBuilder.append(valor + "\t");
            }

            stringBuilder.append("]\n");
            buffer.append(stringBuilder.toString());
        } catch(FileNotFoundException e) {
            System.err.println("Arquivo não foi achado.\n" + e);
        } catch(IOException e) {
            System.err.println("Não foi possível acessar o arquivo.\n" + e);
        } catch(Exception e) {
            System.err.println("Ocorreu algum erro inesperado.\n" + e);
        }
    }

    private static No interligarNosVizinhos(No no, final List<No> vizinhos) throws Exception {
        no.setVizinhos(vizinhos);
        return no;
    }

    private static List<No> adicionarPesosAosVizinhos(List<No> grafo, final List<List<Integer>> listaDePesos) throws Exception {
        for(Integer i = 0; i < grafo.size(); i++) {
            grafo.get(i).setPesos(listaDePesos.get(i));
        }

        return grafo;
    }

    private static List<No> inicializarGrafo(List<No> grafo, final List<List<Integer>> listaDePesos) throws Exception {
        final Integer[][] matrizDistancias = {
            {0, listaDePesos.get(0).get(0), No.getValorInfinito(), No.getValorInfinito(), No.getValorInfinito(), No.getValorInfinito(), No.getValorInfinito()},                 // A
            {listaDePesos.get(1).get(0), 0, No.getValorInfinito(), No.getValorInfinito(), listaDePesos.get(1).get(1), No.getValorInfinito(), No.getValorInfinito()},            // B
            {No.getValorInfinito(), No.getValorInfinito(), listaDePesos.get(2).get(0), 0, No.getValorInfinito(), No.getValorInfinito(), No.getValorInfinito()},                 // C
            {No.getValorInfinito(), listaDePesos.get(3).get(0), listaDePesos.get(3).get(1), 0, listaDePesos.get(3).get(2), listaDePesos.get(3).get(3), No.getValorInfinito()},  // D
            {No.getValorInfinito(), No.getValorInfinito(), No.getValorInfinito(), listaDePesos.get(4).get(0), 0, No.getValorInfinito(), No.getValorInfinito()},                 // E
            {No.getValorInfinito(), No.getValorInfinito(), No.getValorInfinito(), listaDePesos.get(5).get(0), No.getValorInfinito(), 0, listaDePesos.get(5).get(1)},            // F
            {No.getValorInfinito(), No.getValorInfinito(), No.getValorInfinito(), No.getValorInfinito(), No.getValorInfinito(), listaDePesos.get(6).get(0), 0},                 // G
        };

        grafo.set( // vizinhos do no A
            0,
            interligarNosVizinhos(
                grafo.get(0),
                List.of(grafo.get(1)) // B
            )
        );
        grafo.set( // vizinhos do no B
            1,
            interligarNosVizinhos(
                grafo.get(1),
                List.of(
                    grafo.get(0),   // A
                    grafo.get(3)    // C
                )
            )
        );
        grafo.set( // vizinhos do no C
            2,
            interligarNosVizinhos(
                grafo.get(2),
                List.of(grafo.get(3)) // D
            )
        );
        grafo.set( // vizinhos do no D
            3,
            interligarNosVizinhos(
                grafo.get(3),
                List.of(
                    grafo.get(1),   // B
                    grafo.get(2),   // C
                    grafo.get(4),   // E
                    grafo.get(5)    // F
                )
            )
        );
        grafo.set( // vizinhos do no E
            4,
            interligarNosVizinhos(
                grafo.get(4),
                List.of(grafo.get(3)) // D
            )
        );
        grafo.set( // vizinhos do no F
            5,
            interligarNosVizinhos(
                grafo.get(5),
                List.of(
                    grafo.get(3),   // D
                    grafo.get(6)    // G
                )
            )
        );
        grafo.set( // vizinhos do no G
            6,
            interligarNosVizinhos(
                grafo.get(6),
                List.of(grafo.get(5)) // F
            )
        );
        grafo = adicionarPesosAosVizinhos(grafo, listaDePesos);

        for(Integer i = 0; i < grafo.size(); i++) {
            grafo.get(i).setVetorDeDistancias(matrizDistancias[i]);
        }

        return grafo;
    }

    public static void main(String[] args) throws MPIException {
        final String caminhoParaArquivo,
            arquivoSaida = "./saida.txt";
        final List<List<Integer>> valoresLidos;
        Integer idProcesso;
        Integer[][] bufferResposta = new Integer[No.MAXIMO_DE_VIZINHOS][No.MAXIMO_DE_VIZINHOS];
        List<No> grafo = new ArrayList<>(
            List.of(
                new No('A'),
                new No('B'),
                new No('C'),
                new No('D'),
                new No('E'),
                new No('F'),
                new No('G')
            )
        );

        if(args.length > 0 && !Objects.equals(args[0], null)) {
            caminhoParaArquivo = args[0];
        } else {
            caminhoParaArquivo = "./pesos.txt";
        }

        valoresLidos = lerArquivo(caminhoParaArquivo);

        try {
            grafo = inicializarGrafo(grafo, valoresLidos);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao inicializar o grafo.\n" + e);
        }
        
        MPI.Init(args); // INICIO DO MPI
        idProcesso = MPI.COMM_WORLD.getRank();

        for(Integer i = 0; i < 10; i++) {
            if(idProcesso == 0) { // ESTE PROCESSO CUIDA DO NO A
                // VIZINHO B
                MPI.COMM_WORLD.send(grafo.get(0).getVetorDeDistancias(), grafo.get(0).getVetorDeDistancias().length, MPI.INT, 1, 0);
                MPI.COMM_WORLD.recv(bufferResposta[0], bufferResposta[0].length, MPI.INT, 1, 0);
                grafo.get(0).atualizarVetorDeDistancias(bufferResposta[0], 'B');
            } else if(idProcesso == 1) { // ESTE PROCESSO CUIDA DO NO B
                // VIZINHO A
                MPI.COMM_WORLD.recv(bufferResposta[1], bufferResposta[1].length, MPI.INT, 0, 0);
                grafo.get(1).atualizarVetorDeDistancias(bufferResposta[1], 'A');
                MPI.COMM_WORLD.send(grafo.get(1).getVetorDeDistancias(), grafo.get(1).getVetorDeDistancias().length, MPI.INT, 0, 0);
                // VIZINHO D
                MPI.COMM_WORLD.send(grafo.get(1).getVetorDeDistancias(), grafo.get(1).getVetorDeDistancias().length, MPI.INT, 3, 0);
                MPI.COMM_WORLD.recv(bufferResposta[1], bufferResposta[1].length, MPI.INT, 3, 0);
                grafo.get(1).atualizarVetorDeDistancias(bufferResposta[1], 'D');
            } else if(idProcesso == 2) { // ESTE PROCESSO CUIDA DO NO C
                // VIZINHO D
                MPI.COMM_WORLD.recv(bufferResposta[2], bufferResposta[2].length, MPI.INT, 3, 0);
                grafo.get(2).atualizarVetorDeDistancias(bufferResposta[2], 'D');
                MPI.COMM_WORLD.send(grafo.get(2).getVetorDeDistancias(), grafo.get(2).getVetorDeDistancias().length, MPI.INT, 3, 0);
            } else if(idProcesso == 3) { // ESTE PROCESSO CUIDA DO NO D
                // VIZINHO B
                MPI.COMM_WORLD.recv(bufferResposta[3], bufferResposta[3].length, MPI.INT, 1, 0);
                grafo.get(3).atualizarVetorDeDistancias(bufferResposta[3], 'B');
                MPI.COMM_WORLD.send(grafo.get(3).getVetorDeDistancias(), grafo.get(3).getVetorDeDistancias().length, MPI.INT, 1, 0);
                // VIZINHO C
                MPI.COMM_WORLD.send(grafo.get(3).getVetorDeDistancias(), grafo.get(3).getVetorDeDistancias().length, MPI.INT, 2, 0);
                MPI.COMM_WORLD.recv(bufferResposta[3], bufferResposta[3].length, MPI.INT, 2, 0);
                grafo.get(3).atualizarVetorDeDistancias(bufferResposta[3], 'C');
                // VIZINHO E
                MPI.COMM_WORLD.send(grafo.get(3).getVetorDeDistancias(), grafo.get(3).getVetorDeDistancias().length, MPI.INT, 4, 0);
                MPI.COMM_WORLD.recv(bufferResposta[3], bufferResposta[3].length, MPI.INT, 4, 0);
                grafo.get(3).atualizarVetorDeDistancias(bufferResposta[3], 'E');
                // VIZINHO F
                MPI.COMM_WORLD.send(grafo.get(3).getVetorDeDistancias(), grafo.get(3).getVetorDeDistancias().length, MPI.INT, 5, 0);
                MPI.COMM_WORLD.recv(bufferResposta[3], bufferResposta[3].length, MPI.INT, 5, 0);
                grafo.get(3).atualizarVetorDeDistancias(bufferResposta[3], 'F');
            } else if(idProcesso == 4) { // ESTE PROCESSO CUIDA DO NO E
                // VIZINHO D
                MPI.COMM_WORLD.recv(bufferResposta[4], bufferResposta[4].length, MPI.INT, 3, 0);
                grafo.get(4).atualizarVetorDeDistancias(bufferResposta[4], 'D');
                MPI.COMM_WORLD.send(grafo.get(4).getVetorDeDistancias(), grafo.get(4).getVetorDeDistancias().length, MPI.INT, 3, 0);
            } else if(idProcesso == 5) { // ESTE PROCESSO CUIDA DO NO F
                // VIZINHO D
                MPI.COMM_WORLD.recv(bufferResposta[5], bufferResposta[5].length, MPI.INT, 3, 0);
                grafo.get(5).atualizarVetorDeDistancias(bufferResposta[5], 'D');
                MPI.COMM_WORLD.send(grafo.get(5).getVetorDeDistancias(), grafo.get(5).getVetorDeDistancias().length, MPI.INT, 3, 0);
                // VIZINHO G
                MPI.COMM_WORLD.send(grafo.get(5).getVetorDeDistancias(), grafo.get(5).getVetorDeDistancias().length, MPI.INT, 6, 0);
                MPI.COMM_WORLD.recv(bufferResposta[5], bufferResposta[5].length, MPI.INT, 6, 0);
                grafo.get(5).atualizarVetorDeDistancias(bufferResposta[5], 'G');
            } else if(idProcesso == 6) { // ESTE PROCESSO CUIDA DO NO G
                // VIZINHO F
                MPI.COMM_WORLD.recv(bufferResposta[6], bufferResposta[6].length, MPI.INT, 5, 0);
                grafo.get(6).atualizarVetorDeDistancias(bufferResposta[6], 'F');
                MPI.COMM_WORLD.send(grafo.get(6).getVetorDeDistancias(), grafo.get(6).getVetorDeDistancias().length, MPI.INT, 5, 0);
            }
        }

        if(idProcesso == 0) {
            salvarSaidaEmArquivo(arquivoSaida, grafo.get(0).getNomeNo(), grafo.get(0).getVetorDeDistancias());
        } else if(idProcesso == 1) {
            salvarSaidaEmArquivo(arquivoSaida, grafo.get(1).getNomeNo(), grafo.get(1).getVetorDeDistancias());
        } else if(idProcesso == 2) {
            salvarSaidaEmArquivo(arquivoSaida, grafo.get(2).getNomeNo(), grafo.get(2).getVetorDeDistancias());
        } else if(idProcesso == 3) {
            salvarSaidaEmArquivo(arquivoSaida, grafo.get(3).getNomeNo(), grafo.get(3).getVetorDeDistancias());
        } else if(idProcesso == 4) {
            salvarSaidaEmArquivo(arquivoSaida, grafo.get(4).getNomeNo(), grafo.get(4).getVetorDeDistancias());
        } else if(idProcesso == 5) {
            salvarSaidaEmArquivo(arquivoSaida, grafo.get(5).getNomeNo(), grafo.get(5).getVetorDeDistancias());
        } else if(idProcesso == 6) {
            salvarSaidaEmArquivo(arquivoSaida, grafo.get(6).getNomeNo(), grafo.get(6).getVetorDeDistancias());
        }

        MPI.Finalize(); // FIM DO MPI
    }
}
