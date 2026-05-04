package app;

import esd.ListaSequencial;
import sm.Supermercado;

public class Cesta {

    private ListaSequencial<ItemCesta> itens;

    public Cesta() {
        this.itens = new ListaSequencial<>();
    }

    public ListaSequencial<ItemCesta> getItens() {
        return this.itens;
    }

    public void adicionaItem(ItemCesta item) {
        if (item != null) {
            itens.adiciona(item);
        }
    }

    public int comprimento() {
        return this.itens.comprimento();
    }

    public int precoTotal(Supermercado supermercado) {
        return 1; //implementar
    }
}