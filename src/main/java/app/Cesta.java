package app;

import esd.ListaSequencial;
import sm.Supermercado;

import java.util.Scanner;

public class Cesta {

    private ListaSequencial<ItemCesta> itens;

    public Cesta() {
        this.itens = new ListaSequencial<>();
    }

    public ListaSequencial<ItemCesta> getItens() {
        return this.itens;
    }

    public void adicionaItem(ItemCesta item) {
        if (item != null) {
            itens.adiciona(item);
        }
    }

    public int comprimento() {
        return this.itens.comprimento();
    }

    public static Cesta montaCesta() {
        Scanner scanner = new Scanner(System.in);
        Cesta cesta = new Cesta();

        System.out.println("Envie um item por linha no formato: nome,marca,tamanho (ex: café,três corações,500g)");
        System.out.println("aperte enter sem nada pra finalizar.");

        while (true) {
            String linha = scanner.nextLine().trim();
            if (linha.isEmpty()) {
                break;
            }

            String[] partes = linha.split(",", -1);
            if (partes.length != 3) {
                System.out.println("Formato inválido — use nome,marca,tamanho");
                continue;
            }

            String nome = partes[0].trim();
            String marca = partes[1].trim();
            String tamanho = partes[2].trim();

            if (nome.isEmpty() || marca.isEmpty() || tamanho.isEmpty()) {
                System.out.println("Nome, marca e tamanho são obrigatórios");
                continue;
            }

            ItemCesta item = new ItemCesta(nome, tamanho);
            item.adicionarMarca(marca);
            cesta.adicionaItem(item);
        }

        return cesta;
    }

    public float precoTotal(Supermercado supermercado) {
        float total = 0;
        for (int i = 0; i < itens.comprimento(); i++) {
            ItemCesta item = itens.obtem(i);

            float preco = item.getPrecoDoItem(supermercado);

            if (preco == -1) {
                return -1;
            }

            total += preco;
        }

        return total;
    }
}