package app;

import esd.ListaSequencial;
import esd.TabHash;

import java.io.*;

public class CachePersistencia {

    // salva a cache em arquivo texto
    public static void salvar(String arquivo, TabHash<String, ListaSequencial<ProdutoCache>> cache) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {

            for (var par : cache.items()) {

                String chave = par.obtemChave();

                ListaSequencial<ProdutoCache> lista = par.obtemValor();

                for (ProdutoCache produto : lista) {

                    bw.write(
                            chave + "|" + produto.getId() + "|" + produto.getNome() + "|" +
                                    produto.getMarca() + "|" + produto.getEan()
                    );

                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("erro ao salvar cache: " + e);
        }
    }

    // carrega a cache do arquivo
    public static TabHash<String, ListaSequencial<ProdutoCache>> carregar(String arquivo) {

        TabHash<String, ListaSequencial<ProdutoCache>> cache = new TabHash<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] valores = linha.split("\\|");

                if (valores.length != 5) {
                    continue;
                }

                String chave = valores[0];

                ProdutoCache produto = new ProdutoCache(valores[1], valores[2], valores[3], valores[4]);

                ListaSequencial<ProdutoCache> lista;

                if (cache.contem(chave)) {

                    lista = cache.obtem(chave);

                } else {

                    lista = new ListaSequencial<>();

                    cache.adiciona(chave, lista);
                }

                lista.adiciona(produto);
            }

        } catch (IOException e) {
            System.out.println("Cache não encontrada.");
        }

        return cache;
    }
}
