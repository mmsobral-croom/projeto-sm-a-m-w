# Projeto 1: Melhor Preço

### Disciplina: Estrutura de Dados

### Integrantes da equipe: Alexandre Villela, Mônica Cancellier, Willian Santos

<hr>

### Descrição Geral

O programa foi desenvolvido em um projeto Gradle, utilizando uma das estruturas de
dados implementadas em sala de aula.

O objetivo do programa é comparar os preços de produtos entre diferentes
supermercados, identificando qual deles oferece o menor preço total para uma cesta
de compras definida pelo usuário.

Os supermercados cadastrados são:

- Bistek
- Fort Atacadista
- Giassi

O programa realiza buscas de produtos nos supermercados disponibilizados pelo professor
e calcula o valor total da cesta em cada mercado.

### Estrutura do Projeto

Utilizando orientação a objetos, o projeto foi dividido em classes, cada uma com
responsabilidades específicas.

**Classe Main:**

A classe Main é responsável por:
- receber a compra do usuário via input
- adicionar os itens na cesta;
- iniciar os buscadores dos supermercados cadastrados;
- obter o nome dos supermercados e o preço total de cada cesta;
- ordenar os resultados por preço;
- exibir as informações.
  
**Classe Cesta:**

Esta classe representa a cesta de compras do usuário. Ela possui:
- método que monta a cesta de compras;
- uma lista de itens da cesta;
- método para adicionar um novo item na cesta;
- cálculo do preço total da cesta em um supermercado.

> A classe Cesta utiliza uma Lista Sequencial para armazenar os itens.
  
**Classe ItemCesta:**

A classe ItemCesta representa um item que o usuário adicionou na cesta para fazer
a pesquisa de preço. É responsável por:

- verificar se um produto atende aos critérios definidos;
- encontrar um produto válido e com o menor preço.
  
**Classe TotalMercado:**

- armazena o nome do supermercado e preço total da cesta.

> Esta classe implementa a interface Comparable para permitir a ordenação por preço.

## Relacionamento entre as Classes

A relação principal entre as classes ocorre da seguinte forma:

- **Main** cria uma **Cesta**;
- **Cesta** armazena vários **ItemCesta**;
- **ItemCesta** consulta os objetos da classe **Supermercado**;
- os resultados são armazenados em **TotalMercado**.

## Estrutura de Dados Utilizadas

### Lista Sequencial

A principal estrutura de dados utilizada no desenvolvimento do projeto foi a Lista Sequencial.

Esta estrutura foi utilizada para:

- armazenar os itens da cesta;
- armazenar as marcas de produtos;
- armazenar os resultados dos supermercados.

A Lista Sequencial foi escolhida por possuir:

- acesso rápido por índice;
- método para ordenar que utiliza o algorimo merge sort.

<hr>

## Manual de Utilização

Requisitos:

- Java JDK 25;
- Gradle.

Execução pelo Intellij:

- Abrir o projeto;
- Carregar o projeto Gradle;
- Executar a classe Main;

## Demonstração de Uso

Cria uma cesta e adiciona alguns produtos:

<img src="/img/exemplo-parte-1.png">

Saída esperada:

<img src="/img/exemplo-parte-2.png">