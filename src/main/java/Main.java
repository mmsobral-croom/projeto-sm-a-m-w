import app.Cesta;
import app.ItemCesta;
import sm.*;

public class Main {
    static void main() {

        Cesta cesta = new Cesta();

        cesta.adicionaItem(new ItemCesta("tapioca"));
        cesta.adicionaItem(new ItemCesta("café", "500g"));

        Supermercado[] supermercados = {new Bistek(), new Fort(), new Giassi()};

        float[] precosSm = new float[supermercados.length];

        for (int i = 0; i < supermercados.length; i++) {
            precosSm[i] = cesta.precoTotal(supermercados[i]);
        }

        for (int i = 0; i < supermercados.length; i++) {
            System.out.println(supermercados[i].getClass().getSimpleName() + ": " + precosSm[i]);
        }




        /*

        // cria um acessador para o Giassi
        Giassi sm = new Giassi();
        for (Produto p : sm.busca("tapioca")) {
            IO.println(p);
        }
        //agora que pegamos 1 de todos os produtos tapioca, devemos repetir isso para pegar todos os produtos da lista
        // talvez seja uma boa comparararmos o primeiro EAN com os outros e verificar se disponivel = true
        // ai teremos uma verficação qual preço é maior entre EAN's disponiveis
        // acho que é de boa pegar o primeiro, provavelmente é o mais popular
        // e pegar o mais popular mais barato é uma estrategia comum e viavel
        //talvez criando métodos como pegaprimeiroprodutoeverificaseédisponivel

        */

    }
}