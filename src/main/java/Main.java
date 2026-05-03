import app.Cesta;
import app.ItemCesta;
import sm.Giassi;
import sm.Produto;

public class Main {
    static void main() {

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


        /*

        // este é apenas um teste para as classes Cesta e ItemCesta. Pode ser apagado assim que necessário.

        Cesta cesta = new Cesta();

        ItemCesta tapioca = new ItemCesta("tapioca", "1kg");
        ItemCesta cafe = new ItemCesta("café", "500g");

        cafe.adicionarMarca("Melitta");
        cafe.adicionarMarca("Três Corações");

        cesta.adicionaItem(tapioca);
        cesta.adicionaItem(cafe);

        for (int i = 0; i < cesta.comprimento(); i++) {
            ItemCesta item = cesta.getItens().obtem(i);

            System.out.println("Descrição: " + item.getDescricao());

            if (item.getTamanho() != null) {
                System.out.println("Tamanho: " + item.getTamanho());
            }

            if (item.getMarcas().comprimento() != 0) {
                System.out.print("Marcas: ");
                for (int j = 0; j < item.getMarcas().comprimento(); j++) {
                    System.out.print(item.getMarcas().obtem(j) + "; ");
                }
            }
        }

        */
    }
}