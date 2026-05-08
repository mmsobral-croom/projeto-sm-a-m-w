import app.Cesta;
import app.ItemCesta;
import app.TotalMercado;
import esd.ListaSequencial;
import sm.*;

public class Main {
    static void main() {

        Cesta cesta = new Cesta();

        ItemCesta cafe = new ItemCesta("café", "500g");

        cafe.adicionarMarca("Três Corações");
        cafe.adicionarMarca("Melitta");

        cesta.adicionaItem(cafe);
        cesta.adicionaItem(new ItemCesta("tapioca", "1kg"));

        Supermercado[] supermercados = {new Bistek(), new Fort(), new Giassi()};

        // cria uma lista sequencial de "TotalMercado" para poder ordenar por preço
        ListaSequencial<TotalMercado> listaTotal = new ListaSequencial<>();

        for (Supermercado sm : supermercados) {
            float preco = cesta.precoTotal(sm);
            // pula mercados que não têm todos os itens (-1) para não aparecerem como mais barato
            if (preco != -1) {
                listaTotal.adiciona(new TotalMercado(sm.getClass().getSimpleName(), preco, sm));
            }
        }

        listaTotal.ordena();

        for (TotalMercado total : listaTotal) {
            System.out.println(total.getNome() + ": R$ " + total.getPreco());
        }

        System.out.println();

        if (listaTotal.esta_vazia()) {
            System.out.println("Nenhum supermercado tem todos os itens da cesta.");
            return;
        }

        TotalMercado maisBarato = listaTotal.obtem(0);
        System.out.println("Supermercado com a cesta mais barata: " + maisBarato.getNome());
        System.out.println("Itens da cesta:");
        for (ItemCesta item : cesta.getItens()) {
            Produto p = item.getProdutoEscolhido(maisBarato.getSupermercado());
            System.out.println("- " + p.getNome() + ": R$ " + p.getPreco());
        }
    }
}
