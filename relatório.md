Projeto 1 - Melhor Preço
Disciplina: Estrutura de Dados
Integrantes da equipe: Alexandre Villela, Mônica Cancellier, Willian Santos
Descrição geral:

O programa foi desenvolvido em um projeto Gradle, utilizando uma das estruturas de
dados implementadas em aula.
Estrutura do projeto:
Utilizando orientação a objetos, o projeto foi dividido em classes, cada uma com
responsabilidade específica.

Classe Main:
- cria uma cesta de compras
- adiciona os itens na cesta
- instancia os buscadores dos supermercados cadastrados
- obtém o nome do supermercado e o preço total de sua cesta
- ordena os resultados por preço
- exibe as informações
  
  Classe Cesta:
  Representa a cesta de compras do usuário.
  Possui:
- uma lista de itens da cesta
- cálculo do preço total da cesta em um supermercado
  
  Classe ItemCesta:
  A classe ItemCesta representa um item que o usuário deseja
- verifica se um produto atende aos critérios definidos
- encontra um produto que seja válido e com o menor valor
  
  Classe TotalMercado:
- armazena o nome do supermercado e preço total de sua cesta
  Esta classe implementa Comparable para permitir a ordenação por preço.
