package app;

import sm.Supermercado;

// classe para guardar o nome do mercado e total da cesta
public class TotalMercado implements Comparable<TotalMercado> {

    private String nome;
    private float preco;
    private Supermercado supermercado;

    public TotalMercado(String nome, float preco, Supermercado supermercado) {
        this.nome = nome;
        this.preco = preco;
        this.supermercado = supermercado;
    }

    public String getNome() {
        return this.nome;
    }

    public float getPreco() {
        return this.preco;
    }

    public Supermercado getSupermercado() {
        return this.supermercado;
    }

    // a classe implementa Comparable e sobrescreve o metodo compareTo() para ser possível ordenar pelo preço
    @Override
    public int compareTo(TotalMercado outro) {
        return Float.compare(this.preco, outro.preco);
    }


}
