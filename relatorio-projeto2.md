# Projeto 2: Revisão do Projeto sobre Melhor Preço

### Disciplina: Estrutura de Dados

### Integrantes da equipe: Alexandre Villela, Mônica Cancellier, Willian Santos

<hr>

## Descrição Geral

O programa foi desenvolvido em um projeto Gradle, utilizando estruturas de dados implementadas em sala de aula.

O objetivo é melhorar o tempo de resposta do programa que identifica o supermercado com menor preço para uma cesta de produtos definida pelo usuário.

Nesta nova versão do Melhor Preço, utilizamos a estrutura de dados TabHash para implementar uma cache para armazenar os dados dos produtos obtidos em cada supermercado de forma persistente.
Desta forma, ao fazer uma busca que já foi realizada anteriormente, o programa obtém as informações diretamente da cache. Apenas informações como o preço e a disponibilidade do produto são atualizadas.

## Estrutura do Projeto

Utilizando orientação a objetos, o projeto foi dividido em classes, cada uma com
responsabilidades específicas.

Além das classes que já existiam na primeira versão do programa, foram criadas as classes ProdutoCache, CacheSupermercado e CachePersistencia.


### Classe ProdutoCache:

A classe ProdutoCache representa uma versão mais simples da classe Produto para armazenar os seus valores na cache.
Esta classe possui os mesmos atributos da classe Produto, com exceção de preco e disponível, porque os valores destes devem ser atualizados.

- possui o método fromProduto(), que recebe um objeto de Produto e partir deste, retorna um novo ProdutoCache.


### Classe CacheSupermercado:

A classe CacheSupermercado declara uma TabHash, na qual a chave é da classe String e o valor é de ListaSequencial que armazena objetos de ProdutoCache. É responsável por:

- consultar, adicionar e recuperar os dados da cache.


### Classe CachePersistencia:

A classe CachePersistencia é responsável por:

- salvar a cache em arquivo texto.
- carregar a cache.


A seguir, estão informções das classes que já existiam no projeto 1. As classes Main e ItemCesta foram atualizadas:

### Classe Main:

A classe Main é responsável por:

- **chamar método para carregar a cache do arquivo, caso este exista.**
- **chamar método para salvar a cache no arquivo.**
- receber a compra do usuário via input.
- adicionar os itens na cesta.
- iniciar os buscadores dos supermercados cadastrados.
- obter o nome dos supermercados e o preço total de cada cesta.
- ordenar os resultados por preço.
- exibir as informações.

### Classe Cesta:

Esta classe representa a cesta de compras do usuário. Ela possui:

- método que monta a cesta de compras.
- uma lista de itens da cesta.
- método para adicionar um novo item na cesta.
- cálculo do preço total da cesta em um supermercado.

> A classe Cesta utiliza uma Lista Sequencial para armazenar os itens.

### Classe ItemCesta:

A classe ItemCesta representa um item que o usuário adicionou na cesta para fazer
a pesquisa de preço. É responsável por:

- **verificar primeiro se um produto está na cache para realizar a busca. Caso contrário, a busca é realizada usando a API.**
- verificar se um produto atende aos critérios definidos.
- encontrar um produto válido e com o menor preço.

### Classe TotalMercado:

- armazena o nome do supermercado e preço total da cesta.

> Esta classe implementa a interface Comparable para permitir a ordenação por preço.

## Relacionamento entre as Classes

A relação principal entre as classes ocorre da seguinte forma:

- **Main** carrega a cache do arquivo e cria uma **Cesta**.
- **Cesta** armazena vários **ItemCesta**.
- **ItemCesta** consulta primeiro a **CacheSupermercado**.
- caso a informação não esteja na cache, **ItemCesta** consulta um **Supermercado**.
- os produtos retornados são convertidos em **ProdutoCache** e armazenados na cache.
- **CachePersistencia** salva e recupera os dados da cache em arquivo.
- os resultados dos supermercados são armazenados em **TotalMercado**.

## Estrutura de Dados Utilizadas

Nesta versão do Melhor Preço houve a introdução da estrutura de dados TabHash.

### TabHash

Para a implementação da cache persistente foi utilizada a estrutura de dados TabHash.

Esta estrutura foi utilizada para:

- armazenar os resultados das buscas realizadas nos supermercados.
- associar uma chave de busca a uma lista de produtos armazenados na cache.
- recuperar buscas já realizadas anteriormente no programa.

A TabHash foi escolhida por possuir:

- busca eficiente por chave.
- inserção eficiente de novos registros.

### Lista Sequencial

Esta estrutura foi utilizada para:

- armazenar os itens da cesta.
- armazenar as marcas de produtos.
- armazenar os resultados dos supermercados.
- armazenar produtos formatados para serem adicionados na cache.

A Lista Sequencial foi escolhida por possuir:

- acesso rápido por índice;
- método para ordenar que utiliza o algorimo merge sort.

<hr>

## Manual de Utilização

Requisitos:

- Java JDK 25
- Gradle

Execução pelo Intellij:

- Abrir o projeto.
- Carregar o projeto Gradle.
- Executar a classe Main.

Uma outra alternativa para executar o programa é utilizar este comando no terminal:

```bash
./gradlew run
```

## Demonstração de Uso

Adiciona alguns produtos na cesta:

<img src="/img/exemplo-parte-1.png">

Saída esperada:

<img src="/img/exemplo-parte-2.png">

O arquivo cache.txt é gerado ou atualizado na raiz do projeto Gradle:

<img src="/img/exemplo-parte-3.png">