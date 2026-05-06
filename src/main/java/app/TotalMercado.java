package app;

// classe para guardar o nome do mercado e total da cesta
public class TotalMercado implements Comparable<TotalMercado> {

    private String nome;
    private float preco;

    public TotalMercado(String nome, float preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return this.nome;
    }

    public float getPreco() {
        return this.preco;
    }

    // a classe implementa Comparable e sobrescreve o metodo compareTo() para ser possível ordenar pelo preço
    @Override
    public int compareTo(TotalMercado outro) {
        return Float.compare(this.preco, outro.preco);
    }


}