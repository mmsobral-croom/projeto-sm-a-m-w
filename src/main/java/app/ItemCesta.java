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

    public float getPrecoDoItem(Supermercado supermercado) {
        Produto escolhido = getProdutoEscolhido(supermercado);

        if (escolhido == null) {
            return -1;
        }

        return escolhido.getPreco();
    }

    public Produto getProdutoEscolhido(Supermercado supermercado) {
        // itera sobre o "resultado". Para cada produto: verifica se está disponível, verifica tamanho e a marca.
        // No final, retorna o produto escolhido (que possui o menor valor);

        Supermercado.Resultado resultado = supermercado.busca(this.descricao);

        if (resultado == null) {
            return null;
        }

        Produto escolhido = null;

        for (Produto p : resultado) {
            if (p.isDisponivel()) {

                String nome = p.getNome().toLowerCase();

                if (nome.contains(this.descricao) && tamanhoValido(nome) && marcaValida(p)) {

                    if (escolhido == null || p.getPreco() < escolhido.getPreco()) {
                        escolhido = p;
                    }
                }
            }
        }
        return escolhido;
    }

    private boolean tamanhoValido(String nomeProduto) {
        // Retorna true se a string "nomeProduto" contém o "tamanho" deste item;
        if (this.tamanho == null) {
            return true;
        }

        return nomeProduto.contains(this.tamanho);
    }

    private boolean marcaValida(Produto produto) {
        if (this.marcas.comprimento() == 0) {
            return true;
        }

        if (produto.getMarca() == null) {
            return false;
        }

        // verifica se a marca de produto (parâmetro) é compátivel com alguma marca armazenada
        // na lista "marcas"

        String marca = produto.getMarca().toLowerCase();

        for (int i = 0; i < marcas.comprimento(); i++) {
            String marcaProduto = marcas.obtem(i);

            if (marca.contains(marcaProduto)) {
                return true;
            }
        }
        return false;
    }
}
