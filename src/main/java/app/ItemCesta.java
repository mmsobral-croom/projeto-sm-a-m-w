package app;

public class ItemCesta {    // o objeto instanciado desta classe vai representar um item (produto) que será colocado na cesta

    private String descricao;
    private String tamanho;

    public ItemCesta(String descricao) {
        this.descricao = descricao;
        this.tamanho = null;
    }

    public ItemCesta(String descricao, String tamanho) {
        this.descricao = descricao;
        this.tamanho = tamanho;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getTamanho() {
        return this.tamanho;
    }
}