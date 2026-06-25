[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/0aiXsnlU)
# Projeto 2: Revisão do Projeto sobre Melhor Preço

No projeto 1, sua equipe desenvolveu um programa que possibilite que um usuário defina uma cesta de compras, e então descubra qual supermercado oferece o melhor preço. No entanto, o tempo de resposta para as buscas de informações nos supermercados depende dos acessos feitos às suas respectivas APIs web. Esses acessos são necessários para:

1. Pesquisar cada produto desejado, dentre um conjunto de produtos com nomes compatíveis com uma string de busca
2. Para cada produto da cesta, procurar o produto similar em cada supermercado para obter seu preço

Os tempos de busca podem ser reduzidos se o programa mantiver localmente cópias de informações sobre produtos (i.e. uma cache). Se listas de produtos obtidas em acessos do tipo 1 forem mantidas em cache, apenas acessos do tipo 2 serão necessários para obter preços atualizados. Além disso, os acessos do tipo 2 podem ser feitos com base no productId de cada produto, e assim serem mais eficientes (o productId é um identificador de produto específico em cada supermercado). Essas extensões ao programa podem melhorar seu tempo de resposta e, com isso, a experiência do usuário.

## Orientações para a nova versão

Para a nova versão do Melhor Preço, deve ser implementada uma cache persistente dos produtos obtidos em cada supermercado. Essa cache deve possibilitar procurar produtos cujos nomes satisfaçam o critério de busca, de forma similar à da versão desenvolvida no projeto 1. Com isso, a consulta à APi de um supermercado somente será necessária nestes casos:

1. Quando não houver produtos que satisfaçam a busca: o resultado dessa consulta deve então ser armazenado na cache
2. Quando for obter o preço atualizado e disponibilidade de um produto: este tipo de busca pode (deve) ser feita com base no Id do produto em cada supermercado

A cache deve ser implementada explorando as estruturas de dados estudadas na unidade 3. cabe à sua equipe identificar as melhores estruturas de dados para cada situação. Note que as buscas por produtos podem ser feitas por nome (ou parte do nome) ou por EAN.

Para que a cache seja persistente, seu conteúdo deve ser gravado em um arquivo quando o programa for concluído. Assim, quando o programa for iniciado, a cache pode ser recuperada do arquivo. Se achar melhor, mais de um arquivo pode ser usado (ex: um por supermercado).