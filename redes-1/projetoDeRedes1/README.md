# Projeto Final de Redes I

## Indice
- [Enunciado](#enunciado)
- [Composição da nota](#composição-da-nota)
- [O Protocolo](#o-protoloco)
- [Funcionamento do Software](#funcionamento-do-software)
- [Propósito do Software](#propósito-do-software)
- [Motivação da Escolha do Protocolo TCP](#motivação-da-escolha-do-protocolo-tcp)
- [Requisitos Mínimos](#requisitos-mínimos)

## Enunciado
Desenvolver um programa de computador distribuído modelo cliente-servidor.
O programa deve ter pelo menos duas partes: um programa cliente e um programa servidor.
O cliente e o servidor devem se comunicar através de um socket TCP ou um socket UDP.
Deve ser criado e documentado um protocolo da camada de aplicação explicando todos os eventos, os estados e as mensagens que as partes comunicantes trocam para manter o software funcionando.
Além da documentação do protocolo, deve ser documentado o funcionamento do  software, qual o propósito do software, qual a motivação da escolha do protocolo de transporte e os requisitos mínimos de funcionamento.

Toda a documentação do software deve estar num arquivo README.md.
Deve ser enviado um link do github com o software para o classroom (**Não será aceito o upload dos arquivos diretamente no classroom**).
O programa pode ser escrito em java ou em python e pode ser feito por uma equipe de até 4 pessoas.

## Composição da Nota
- Programa: 3,5
- Protocolo de aplicação: 3,0
- Documentação: 2,5
- Originalidade: 1,0

## O Protoloco
Este software usa como protocolo os [*Records*](https://docs.oracle.com/en/java/javase/17/language/records.html) do Java.

Introduzido no Java 14, uma classe *Record* é uma classe pública e estática, onde todos os seus valores são finais, ou seja, imutáveis e, além disso, a classe possui apenas os *getters*.

A classe deve receber seus atributos no momento da instanciação, pois ela não aceita nenhum novo parâmetro.

**Exemplo:**
```java
PessoaRecord objetoPessoa = new PessoaRecord(CPF, nome, sobrenome, idade, sexo, args);
```

Para acessar os métodos do Record, basta criar uma instância da classe preferida e chamar por seu método utilizando o nome do atributo que deve ser buscado com a primeira letra maiúscula.

**Exemplo:**
- Temos uma classe pessoa;
  - CPF, nome, sobrenome, idade, sexo, etc.
- Para acessar o valor do nome, basta fazer a chamada: *objetoPessoa.Nome();*
- Para acesser o valor do CPF, basta fazer a chamada: *objetoPessoa.CPF();*
- Isso vale para todos os campos.

O Record criado e usado neste software segue o padrão abaixo:
> Para o projeto do Cliente:
>
> Ele se encontra na pasta projetoDeRedes1/Cliente/src/com/jogada/ e se chama Jogada.java
>
> O mesmo para para o Servidor:
>
> Ele se encontra na pasta projetoDeRedes1/Servidor/src/com/jogada/ e se chama Jogada.java

A anatomia da classe é:
```java
/* ARQUIVO Jogada.java */
import java.io.Serializable;

public record Jogada(Short jogada) implements Serializable {
    private static final Long serialVersionUID = 30000l;
}
```

Onde:
- jogada :: ***Short*** é o valor da jogada feita (aceita os valores de 0 a 4);
- serialVersionUID :: ***Long*** é uma identidade da classe. Java precisa comparar ambos serialVersionUID (do Cliente e do Servidor) antes de prosseguir com a execução do código.
Se os valores de serialVersionUID forem diferentes, o programa Java irá entendê-los como classes diferentes e sua execução será abortada.

O protocolo falha caso o valor de jogada esteja fora do conjunto [0, 1, 2, 3, 4] ou se o serialVersionUID do Cliente e de Servidor sejam diferentes.

Seu funcionamento se dá por:
- O Cliente/Servidor instancia uma nova classe Jogada com o valor de sua jogada;
- Armazena esta Jogada em uma variável;
- Envia a variável pela rede usando o protocolo TPC;
- Recebe a resposta do seu oposto;
- Compara os valores do atributo *jogada*;
- Repete este processo até que os Pontos de Vida do Cliente/Servidor seja reduzido a 0, ou até que o Cliente efetue a jogada de desistência (4).

## Funcionamento do Software

## Propósito do Software
O propósito deste software é simular/emular uma brincadeira infantil.
Jogada com 2 pessoas, uma de frente para a outra.

Nesta brincadeira, 

## Motivação da Escolha do Protocolo TCP
Este jogo, na vida real, é realizado de forma síncrona, ou seja, ambos jogadores fazem suas jogadas ao mesmo tempo.
E ambos têm o feedback da jogada do adversário ao mesmo tempo. Para simular esta interação, foi escolhido o protocolo TCP.
Ambos Cliente e Servidor devem realizar suas jogadas, ao mesmo tempo, e ter a resposta de seu adversário.
Ou seja, deve ser garantido que os pacotes das jogadas saiam de um host e cheguem ao outro.
O protoloco TCP faz todos os tratamentos citados anteriormente de forma nativa.

Neste software, foi tomado como convenção que o Servidor sempre realiza sua jogada primeiro, envia os pacotes para o Servidor, que responde com seus próprios pacotes.
Somente depois que a troca de pacotes foi bem sucedida que as outras funcionalidade do software entram em ação.

## Requisitos Mínimos
- Java versão 21 ou maior;
- JDK versão 21 ou maior;
- JRE versão 21 ou maior.
