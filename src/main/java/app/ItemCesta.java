package app;

import esd.ListaSequencial;
import sm.Produto;
import sm.Supermercado;

public class ItemCesta {

    private String descricao;
    private String tamanho;

    private ListaSequencial<String> marcas;     // lista para selecionar mais de uma marca na busca de um produto

    private static CacheSupermercado cache;

    public ItemCesta(String descricao) {
        this(descricao, null);
    }

    public ItemCesta(String descricao, String tamanho) {
        this.descricao = descricao.toLowerCase();
        this.tamanho = tamanho != null ? tamanho.toLowerCase() : null;
        this.marcas = new ListaSequencial<>();
    }

    // recebe a cache carregada pela classe Main e a disponibiliza para todos os
    // objetos da classe ItemCesta durante a execução do programa
    public static void setCache(CacheSupermercado novoCache) {
        cache = novoCache;
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

        // cria uma chave para o supermercado e a descricao, neste formato: supermercado:descricao.
        // exemplo: fort:café
        String chave = supermercado.getClass().getSimpleName().toLowerCase() +
                ":" + this.descricao.toLowerCase();

        // lista para armazenar os produtos obtidos da cache ou da API
        ListaSequencial<Produto> produtos = new ListaSequencial<>();

        // verifica cache para tentar obter os produtos dela
        if (cache != null && cache.contemBusca(chave)) {

            System.out.println("buscando na cache"); // teste para verificar se realiza a busca na cache

            // obtem os produtos da cache
            ListaSequencial<ProdutoCache> produtosCache = cache.obtemBusca(chave);

            ListaSequencial<String> ids = new ListaSequencial<>();

            // obtém os ids dos produtos armazenados na cache e adiciona na lista ids
            for (ProdutoCache produto : produtosCache) {

                ids.adiciona(produto.getId());
            }

            produtos = supermercado.obtem(ids);

        } else {

            System.out.println("buscando na API"); // testa se busca na api

            // consulta a API, pois não encontrou a busca na cache
            Supermercado.Resultado resultado = supermercado.busca(this.descricao);

            if (resultado == null) {
                return null;
            }

            ListaSequencial<ProdutoCache> listaCache = new ListaSequencial<>();

            for (Produto produto : resultado) {

                produtos.adiciona(produto);

                // transforma Produto em ProdutoCache e adiciona na lista listaCache
                listaCache.adiciona(ProdutoCache.fromProduto(produto));
            }
            // salva a busca na cache
            if (cache != null) {
                cache.adicionaBusca(chave, listaCache);
            }
        }

        Produto escolhido = null;

        // escolhe o produto válido, com o menor preço
        for (Produto produto : produtos) {
            if (produtoCombina(produto)) {

                if (escolhido == null || produto.getPreco() < escolhido.getPreco()) {

                    escolhido = produto;
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
