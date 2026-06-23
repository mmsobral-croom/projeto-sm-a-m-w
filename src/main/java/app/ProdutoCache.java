package app;

import sm.Produto;

// classe para representar uma versão mais simples de Produto, para ser armazenada na cache.
public class ProdutoCache {
    // ProdutoCache não possui os atributos preco e disponivel, pois estes podem ter o seu valor alterado.
    private String id;
    private String nome;
    private String marca;
    private String ean;

    public ProdutoCache(String id, String nome, String marca, String ean) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.ean = ean;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getEan() {
        return ean;
    }

    // transforma um Produto em um ProdutoCache
    public static ProdutoCache fromProduto(Produto p) {

        return new ProdutoCache(p.getId(), p.getNome(), p.getMarca(), p.getEan());
    }
}
