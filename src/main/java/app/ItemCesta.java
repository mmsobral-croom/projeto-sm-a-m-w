package app;

import esd.ListaSequencial;
import sm.Produto;
import sm.Supermercado;

public class ItemCesta {

    private String descricao;
    private String tamanho;

    private ListaSequencial<String> marcas;     // lista para selecionar mais de uma marca na busca de um produto

    public ItemCesta(String descricao) {
        this(descricao, null);
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

    // busca produtos no supermercado e devolve o mais barato que combine com este item
    // (descrição, tamanho e marca). Devolve null se nenhum produto combinar.
    public Produto getProdutoEscolhido(Supermercado supermercado) {
        Supermercado.Resultado resultado = supermercado.busca(this.descricao);

        if (resultado == null) {
            return null;
        }

        Produto escolhido = null;

        for (Produto p : resultado) {
            if (produtoCombina(p)) {
                if (escolhido == null || p.getPreco() < escolhido.getPreco()) {
                    escolhido = p;
                }
            }
        }

        return escolhido;
    }

    // produto combina se está disponível, o nome contém a descrição,
    // o tamanho confere e a marca está entre as aceitas.
    private boolean produtoCombina(Produto p) {
        if (!p.isDisponivel()) {
            return false;
        }

        String nome = p.getNome().toLowerCase();
        return nome.contains(this.descricao) && tamanhoValido(nome) && marcaValida(p);
    }

    private boolean tamanhoValido(String nomeProduto) {
        if (this.tamanho == null) {
            return true;
        }

        return nomeProduto.contains(this.tamanho);
    }

    // se nenhuma marca foi exigida, qualquer marca passa.
    // caso contrário, a marca do produto deve casar com alguma da lista de marcas aceitas.
    private boolean marcaValida(Produto produto) {
        if (this.marcas.comprimento() == 0) {
            return true;
        }

        if (produto.getMarca() == null) {
            return false;
        }

        String marcaProduto = produto.getMarca().toLowerCase();

        for (int i = 0; i < marcas.comprimento(); i++) {
            String marcaAceita = marcas.obtem(i);
            if (marcaProduto.contains(marcaAceita)) {
                return true;
            }
        }

        return false;
    }
}
