package app;

import esd.ListaSequencial;
import esd.TabHash;

public class CacheSupermercado {

    // TabHash que associa uma busca realizada
    private TabHash<String, ListaSequencial<ProdutoCache>> cacheBusca;

    // cria uma cache vazia
    public CacheSupermercado() {
        cacheBusca = new TabHash<>();
    }

    // cria uma cache a partir de uma tabela carregada do arquivo
    public CacheSupermercado(TabHash<String, ListaSequencial<ProdutoCache>> tabela) {
        this.cacheBusca = tabela;
    }

    public boolean contemBusca(String descricao) {
        return cacheBusca.contem(descricao.toLowerCase());
    }

    public ListaSequencial<ProdutoCache> obtemBusca(String descricao) {
        return cacheBusca.obtem(descricao.toLowerCase());
    }

    public void adicionaBusca(String descricao, ListaSequencial<ProdutoCache> produtos) {
        cacheBusca.adiciona(descricao.toLowerCase(), produtos);

    }

    public TabHash<String, ListaSequencial<ProdutoCache>> getCacheBusca() {
        return cacheBusca;
    }
}
