import app.*;
import esd.ListaSequencial;
import sm.*;

public class Main {

    static void main() {

        CacheSupermercado cache = new CacheSupermercado(CachePersistencia.carregar("cache.txt"));

        ItemCesta.setCache(cache);

        Cesta cesta = Cesta.montaCesta();

        if (cesta.comprimento() == 0) {
            System.out.println("Cesta vazia. Encerrando.");
            return;
        }

        Supermercado[] supermercados = {new Bistek(), new Fort(), new Giassi()};
        ListaSequencial<TotalMercado> ranking = calculaRanking(cesta, supermercados);

        if (ranking.esta_vazia()) {

            System.out.println("Nenhum supermercado tem todos os itens da cesta.");

            CachePersistencia.salvar("cache.txt", cache.getCacheBusca());

            return;
        }

        mostraRanking(ranking);
        mostraDetalheCestaMaisBarata(cesta, ranking.obtem(0));

        CachePersistencia.salvar("cache.txt", cache.getCacheBusca());
    }

    // calcula o preço total da cesta em cada supermercado e devolve a lista ordenada
    // do mais barato pro mais caro. Mercados sem algum item da cesta ficam de fora.
    static ListaSequencial<TotalMercado> calculaRanking(Cesta cesta, Supermercado[] supermercados) {
        ListaSequencial<TotalMercado> ranking = new ListaSequencial<>();

        for (Supermercado sm : supermercados) {
            float preco = cesta.precoTotal(sm);
            if (preco != -1) {
                ranking.adiciona(new TotalMercado(sm.getClass().getSimpleName(), preco, sm));
            }
        }

        ranking.ordena();
        return ranking;
    }

    static void mostraRanking(ListaSequencial<TotalMercado> ranking) {
        System.out.println("-------------------------");
        System.out.println("Ranking de Supermercados:");
        int contador = 1;
        for (TotalMercado total : ranking) {
            System.out.println(contador++ + ". " + total.getNome() + ": R$ " + total.getPreco());
        }
        System.out.println();
    }

    static void mostraDetalheCestaMaisBarata(Cesta cesta, TotalMercado maisBarato) {
        System.out.println("Supermercado com a cesta mais barata: " + maisBarato.getNome());
        System.out.println("Itens da cesta:");

        for (ItemCesta item : cesta.getItens()) {
            Produto produto = item.getProdutoEscolhido(maisBarato.getSupermercado());
            System.out.println("- " + produto.getNome() + ": R$ " + produto.getPreco());
        }
    }
}
