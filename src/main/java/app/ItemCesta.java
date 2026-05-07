package app;

import esd.ListaSequencial;
import sm.Produto;
import sm.Supermercado;

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
        this.tamanho = tamanho != null ? tamanho.toLowerCase() : null;
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

    /*

    public float getPrecoDoItem(ItemCesta item, Supermercado supermercado) {
        //pega o preço SÓ do primeiro produto. precisa
        // implementar uma lógica tipo bater marca com marca
        // e tamanho com tamanho pra comparar o preço
        Supermercado.Resultado resultado = supermercado.busca(item.getDescricao());
        if (resultado == null) {
            return 0;
        }
        for (Produto p : resultado) {
            return p.getPreco();
        }
        return 0;
    }

    */

    public float getPrecoDoItem(Supermercado supermercado) {
        Produto escolhido = getProdutoEscolhido(supermercado);

        if (escolhido == null) {
            return -1;
        }

        return escolhido.getPreco();
    }

    public Produto getProdutoEscolhido(Supermercado supermercado) {
        // itera sobre o "resultado". Para cada produto: verifica se está disponível e o tamanho. Falta verificar as marcas
        // No final, retorna o produto com menor preço
        Supermercado.Resultado resultado = supermercado.busca(this.descricao);

        if (resultado == null) {
            return null;
        }

        Produto escolhido = null;

        for (Produto p : resultado) {
            if (p.isDisponivel()) {

                String nome = p.getNome().toLowerCase();

                if (nome.contains(this.descricao)) {

                    boolean tamanhoValido = true;

                    if (this.tamanho != null) {
                        tamanhoValido = nome.contains(this.tamanho);
                    }

                    if (tamanhoValido) {
                        if (escolhido == null || p.getPreco() < escolhido.getPreco()) {
                            escolhido = p;
                        }
                    }
                }
            }
        }
        return escolhido;
    }
}
