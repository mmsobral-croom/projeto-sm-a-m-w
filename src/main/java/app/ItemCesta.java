package app;

import esd.ListaSequencial;

public class ItemCesta {    // o objeto instanciado desta classe vai representar um item (produto) que será colocado na cesta

    private String descricao;
    private String tamanho;

    private ListaSequencial<String> marcas;     // lista para selecionar mais de uma marca na busca de um produto

    public ItemCesta(String descricao) {
        this.descricao = descricao.toLowerCase();   // toLowerCase() para aceitar letras maiúsculas ou minúsculas
        this.tamanho = null;
        this.marcas = new ListaSequencial<>();
    }

    public ItemCesta(String descricao, String tamanho) {
        this.descricao = descricao.toLowerCase();
        this.tamanho = tamanho;
        this.marcas = new ListaSequencial<>();
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getTamanho() {
        return this.tamanho;
    }

    public ListaSequencial<String> getMarcas() {
        return this.marcas;
    }

    public void adicionarMarca(String marca) {
        if (marca != null) {
            marcas.adiciona(marca.toLowerCase());
        }
    }
}