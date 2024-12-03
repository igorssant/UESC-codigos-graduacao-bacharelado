#include <stdlib.h>
#include <stdio.h>
#include <mpi.h>
#include <time.h>
#include <sys/time.h>

/* Este programa esta dividido em duas partes principais, ou seja, o mestre e
 * os escravos. O mestre fica encarregado de dividir a carga de trabalho
 * entre os escravos, enquanto estes ficam encarregado de realizar a múltiplicação
 * propriamente dita. O resultado da multiplicação é recolhido e apresentado
 * pelo mestre. */

// Número de linhas e colunas das matrizes sob multiplicação, ou seja
// as matrizes são quadradas e de tamanho fixo, embora o código-fonte poderia
// ser facilmente alterado para trabalhar com um número N qualquer. 
#define N 4

MPI_Status status;

// Matrizes a, b e c utilizadas na multiplicação, mas ainda sem
// conteúdo. Apenas a memória é alocada aqui, de forma estática. 
double matrix_a[N][N],matrix_b[N][N],matrix_c[N][N];

int main(int argc, char **argv)
{
  int processCount, processId, slaveTaskCount, source, dest, rows, offset;

  // Os argumentos recebidos do programa principal são passados para 
  // a API MPI. 
  MPI_Init(&argc, &argv);
  // Cada processo obtém um identificar único. Isso é um principio básico de 
  // implementação de sistemas distribuídos. 
  MPI_Comm_rank(MPI_COMM_WORLD, &processId);
  // O número de processos ativos em uma comunicação fica armazenado na 
  // variável processCount, que é alterada para este valor. 
  MPI_Comm_size(MPI_COMM_WORLD, &processCount);

  // A quantidade de escravos é igual a quantidade total de processos menos um,
  // pois na quantidade total o mestre também é contado. 
  slaveTaskCount = processCount - 1;

 // Se o ID é 0, então o processo em execução é o mestre. Caso contrário, ele
 // é um escravo. 
 if (processId == 0) {
	
    // As matrizes a e b serão preenchidas com números aleatórios. Para tanto, 
    // a função srand é chamada para inicializar o processo de geração de 
    // números aleatórios. 
    srand ( time(NULL) );
    
    /* A função rand retorna um inteiro aleatório, mas pode ser maior do que 
     * 10. Então, divide-se por 10 este valor e obtém-se o resto, para que
     * o valor não seja maior do que 10. */
    for (int i = 0; i<N; i++) {
      for (int j = 0; j<N; j++) {
        matrix_a[i][j]= rand()%10; // nenhum número é maior do que 10. 
        matrix_b[i][j]= rand()%10; // nenhum número é maior do que 10. 
      }
    }
	
  printf("\n\t\tMultiplicando matrizes com MPI. \n");

    /* Um laço simples, que mostra o conteúdo da matriz A na tela.  */
    printf("\nMatrix A\n\n");
    for (int i = 0; i<N; i++) {
      for (int j = 0; j<N; j++) {
        printf("%.0f\t", matrix_a[i][j]);
      }
	    printf("\n");
    }

    /* Um laço simples, que mostra o conteúdo da matriz B na tela.  */
    printf("\nMatrix B\n\n");
    for (int i = 0; i<N; i++) {
      for (int j = 0; j<N; j++) {
        printf("%.0f\t", matrix_b[i][j]);
      }
	    printf("\n");
    }

    /* Determina a quantidade de linhas da matriz A que devem ser enviadas
     * para cada escravo. */
    rows = N/slaveTaskCount;
    
    /* Esta variável é a linha inicial, dentro de cada processo escravo, 
     * onde o processamento deve começar a ser feito. O valor deve ser diferente
     * para cada processo escravo. */ 
    offset = 0;

    /* Envia mensagens para cada processo escravo, com as informações necessárias
     * para cada processamento, ou seja, offset, a quantidade de linhas rows, 
     * cada uma das linhas, mais a matriz B completa.  */ 
    for (dest=1; dest <= slaveTaskCount; dest++)
    {
      // Envia o offset. 
      MPI_Send(&offset, 1, MPI_INT, dest, 1, MPI_COMM_WORLD);
      // Envia a quantidade de linhas rows, que é N / N_ESCRAVOS
      MPI_Send(&rows, 1, MPI_INT, dest, 1, MPI_COMM_WORLD);
      // Envia todas as linhas de um único escravo. 
      MPI_Send(&matrix_a[offset][0], rows*N, MPI_DOUBLE,dest,1, MPI_COMM_WORLD);
      // Envia a matriz B completa. 
      MPI_Send(&matrix_b, N*N, MPI_DOUBLE, dest, 1, MPI_COMM_WORLD);
      
      // O offset, ou seja, a linha inicial, é atualizado para cada escravo. 
      offset = offset + rows;
    }

    /* O processo mestre aguarda o recebimento do resultado do processamento
     * vindo de cada processo escravo. */
    for (int i = 1; i <= slaveTaskCount; i++)
    {
      source = i;
      // Recebe o offset de cada processo escravo. 
      MPI_Recv(&offset, 1, MPI_INT, source, 2, MPI_COMM_WORLD, &status);
      // Recebe o número de linhas de cada processo escravo. 
      MPI_Recv(&rows, 1, MPI_INT, source, 2, MPI_COMM_WORLD, &status);
      /* Recebe todas as linhas calculadas por cada escravo, começando em offset, 
       * indo até offset + rows, e as armazena na matriz C. */
      MPI_Recv(&matrix_c[offset][0], rows*N, MPI_DOUBLE, source, 2, MPI_COMM_WORLD, &status);
    }

    /* Depois que todas as linhas da matriz C são recebidas, mostra o resultado
     * na tela do computador. */
    printf("\nResult Matrix C = Matrix A * Matrix B:\n\n");
    for (int i = 0; i<N; i++) {
      for (int j = 0; j<N; j++)
        printf("%.0f\t", matrix_c[i][j]);
      printf ("\n");
    }
    printf ("\n");
  }

  // Se o processo em execução for um escravo. 
  if (processId > 0) {

    // A origem das mensagens é o processo mestre, cujo ID é 0. 
    source = 0;

    /* Cada processo escravo aguarda o recebimento das mensagens vindas
     * do processo mestre. */

    // Recebe o offset do processo mestre, específico para cada escravo. 
    MPI_Recv(&offset, 1, MPI_INT, source, 1, MPI_COMM_WORLD, &status);
    // Recebe a quantidade de linhas vindas do processo mestre. 
    MPI_Recv(&rows, 1, MPI_INT, source, 1, MPI_COMM_WORLD, &status);
    // Recebe todas as linhas N / escravos vindas do processo mestre. 
    MPI_Recv(&matrix_a, rows*N, MPI_DOUBLE, source, 1, MPI_COMM_WORLD, &status);
    // Recebe a matriz B. Talvez essa linha não fosse necessária. 
    MPI_Recv(&matrix_b, N*N, MPI_DOUBLE, source, 1, MPI_COMM_WORLD, &status);

    
    /* Efetua uma multiplicação parcial, ou seja, uma parte da multiplicação
     * atribuída a cada escravo. */
    for (int k = 0; k<N; k++) {
      for (int i = 0; i<rows; i++) {
        // O valor inicial da soma deve ser zero. 
        matrix_c[i][k] = 0.0;
        // A(i, j) é multiplicado com B(j, k). 
        for (int j = 0; j<N; j++)
          matrix_c[i][k] = matrix_c[i][k] + matrix_a[i][j] * matrix_b[j][k];
      }
    }

    /* O resultado da multiplicação parcial atribuído a cada escravo
     * é enviado para o processo mestre. */
    
    // Envia o offset. 
    MPI_Send(&offset, 1, MPI_INT, 0, 2, MPI_COMM_WORLD);
    // Envia a quantidade de linhas N / ESCRAVOS para o processo mestre. 
    MPI_Send(&rows, 1, MPI_INT, 0, 2, MPI_COMM_WORLD);
    // Envia todas as linhas N / ESCRAVOS para o processo mestre. 
    // Estas linhas são o resultado da multiplicação parcial. 
    MPI_Send(&matrix_c, rows*N, MPI_DOUBLE, 0, 2, MPI_COMM_WORLD);
  }

  // Desaloca os recursos e finaliza. 
  MPI_Finalize();
}
