# Criando ambiente virtual

## Índice
- [Pré-requisitos](#pré-requisitos)
- [Baixar o Anaconda](#anaconda)
    - [Configurando o Anaconda](#configurações-do-anaconda)
        - [Anaconda para Windows](#windows-1)
        - [Anaconda para Linux e MacOS](#linux-e-macos-1)
- [Criando Ambiente Virtal](#criando-ambiente-virtal-utilizando-o-arquivo-environmentyml)
- [Atualizando um Ambiente Virtual](#atualizando-um-ambiente-virtual)
- [Documentação do Anaconda](#documentação-do-anaconda)

## Pré-requisitos
### Windows
Caso você esteja utilizando o sistema operacional Windows, será necessário que você baixe a linguagem Python em sua máquina.

Para isso entre no site oficial do Python utilizando este [link](https://www.python.org/) e siga as diretivas do site para instalar e configurar o intepretador da linguagem em sua máquina.

### Linux e MacOS
Caso você esteja usando Linux ou MacOS, não há nenhum pré-requisito.

## Anaconda
Link para a página principal do [Anaconda](https://anaconda.org/).

Para baixar o Anaconda, vá até a página de Download gratuito do anaconda, ou acesse por [este link](https://www.anaconda.com/download). Antes de instalar, será pedido para fazer um registro no site, mas se preferir poderá ignorar esta passo pressionando *skip registration*.

Escolha sua pasta de instalação e baixe. Anaconda é multiplataforma, ou seja, funciona perfeitamente em qualquer sistema operacional (Windows, Linux MacOS).

### Configurações do Anaconda
#### Windows
Em seguida, abra o instalador do anaconda e siga os passos. É indicado que siga a instalação padrão do Anaconda e não mudar nenhuma das opções cadastradas previamente pelo time do Anaconda.

O Anaconda será então instalado e configurado automaticamente no Windows.

Caso você use windows este passo foi o único necessário para configurar o Anaconda. Pode ignorar o passo seguinte e pular para [Criar Ambiente Virtual Utiliando o arquivo environment.yml](#criando-ambiente-virtal-utilizando-o-arquivo-environmentyml).

#### Linux e MacOS
Em seguida, abra o terminal e siga para a pasta onde o arquivo de instalação foi baixado.

Na pasta, descompacte o arquivo, caso necessário, e execute o arquivo *shell script* de instalação do anaconda.

Para executar o arquivo, rode o comando:
```sh
sh nome_do_arquivo.sh
```

O script irá fazer algumas sobre costumização do Anaconda, mas é indicado deixar as configurações no padrão de fábrica e apenas seguir a instalação normal.

Após a instalação, o Anaconda irá imprimir comandos úteis para rodar o anaconda e criar ambientes. É indicado que salve estes comandos.

A instalação está finalizada e o Anaconda já pode ser usado normalmente.

## Criando Ambiente Virtal utilizando o arquivo environment.yml
Para criar o ambiente virtual utilizando o arquivo `environment.yml`, use o comando abaixo:
```sh
conda env create -f environment.yml
```

Após a execução do comando, ative o ambiente virtual e utilize a vontade.
```sh
conda activate .tcc-igorsousa-venv
```

## Atualizando um Ambiente Virtual
Caso já possua um ambiente virtual criado e não queira criar um novo. Vá para o arquivo `environment.yml` e mude o nome que se encontra na linha `1`.
Nome antigo:
```yml
name: .tcc-igorsousa-venv
```

Mude para um nome à sua escolha:
```yml
name: .nome-a-sua-escolha
```

E salve o arquivo. Depois de salvo, utilize os comandos abaixo em um terminal a sua escolha e o Anaconda irá atualizar todos os pacotes e bibliotecas automaticamente enquanto baixa os novos.
```sh
conda activate nome-do-seu-ambiente-virtual
conda env update --file environment.yml --prune
```

## Documentação do Anaconda
Para acessar a documentação do anaconda, utilize o link abaixo:
- [Link para o site do Anaconda](https://conda.io/projects/conda/en/latest/user-guide/getting-started.html)
