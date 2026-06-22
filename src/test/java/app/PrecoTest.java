package app;

import org.junit.jupiter.api.Test;
import sm.Giassi;

public class PrecoTest {

    @Test
    void testGetPrecoDoItem() {
        Giassi sm = new Giassi();

        ItemCesta tapioca = new ItemCesta("tapioca");
        float p1 = tapioca.getPrecoDoItem(sm);
        System.out.println("preco tapioca = " + p1);

        ItemCesta cafe = new ItemCesta("café", "500g");
        cafe.adicionarMarca("Melitta");
        float p2 = cafe.getPrecoDoItem(sm);
        System.out.println("preco cafe melitta 500g = " + p2);

        Cesta cesta = new Cesta();
        cesta.adicionaItem(tapioca);
        cesta.adicionaItem(cafe);
        float total = cesta.precoTotal(sm);
        System.out.println("preco total cesta = " + total);
    }
}
