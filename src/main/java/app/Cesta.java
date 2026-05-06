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
        float total = 0;
        for (int i = 0; i < itens.comprimento(); i++) {
            ItemCesta item = itens.obtem(i);
            total += item.getPrecoDoItem(item, supermercado);
        }
        return (int) total;
    }
}