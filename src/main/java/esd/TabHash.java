package esd;

import java.lang.reflect.Array;
import java.security.InvalidParameterException;

public class TabHash <K, V> {
    public class Par {
        K chave;
        V valor;

        Par(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }

        public K obtemChave() {
            return chave;
        }

        public V obtemValor() {
            return valor;
        }

        @Override
        public boolean equals(Object outro) {
            Par _outro = (Par)outro;
            return chave.equals(_outro.chave);
        }

        @Override
        public String toString() {
            return chave + "=" + valor;
        }
    }

    ListaSequencial<Par>[] tab;
    int len = 0; // quantos pares estao armazenados
    final int defcap = 31;

    double maxFatorCarga;

    public TabHash() {
        // dimensiona a tabela
        tab = inicia_tabela(defcap);
        maxFatorCarga = 0.75;
    }

    @SuppressWarnings("unchecked")
    ListaSequencial<Par>[] inicia_tabela(int linhas) {
        ListaSequencial<Par>[] tabela = (ListaSequencial<Par>[]) Array.newInstance(ListaSequencial.class, linhas);
        // inicia a lista com essa quantidade de linhas

        for (int i = 0; i < linhas; i++) {
            tabela[i] = new ListaSequencial<>();
        }

        return tabela;
    }

    public void setMaxFatorCarga(double fator) {
        if (fator < 0) throw new InvalidParameterException("fator inválido");
        maxFatorCarga = fator;
    }

    public double fator_carga() {
        double f = len;
        return f/tab.length;
    }

    // expande a tabela hash, de forma que dobre a quantidade de linhas
    // os pares deve ser redistribuídos na tabela (rehashing)
    void expande() {
        // 1.  Expande a tabela: como é formada por uma ListaSequencial, deve-se criar
        // uma nova ListaSequencial contendo o dobro de linhas (listas vazias) da tabela atual

        // 2. para cada par da tabela atual, deve-se recalcular o hash de
        // sua chave, e adicioná-lo à nova lista

        // 3. ao final, substituir a tabela atual pela nova tabela

        var old = tab;
        tab = inicia_tabela(2 * tab.length);
        len = 0;

        // para cada lista da tabela antes da expansão
        for (var lp : old) {
            if (lp != null) {
                // para cada par dessa lita
                for (var p : lp) {
                    // adiciona-o à lista na linha correspondente
                    // da tabela após a expansão
                    adiciona(p.chave, p.valor);
                }
            }
        }
    }

    public void adiciona(K chave, V valor) throws IndexOutOfBoundsException {

        if (chave == null) {
            throw new InvalidParameterException("a chave não pode ser null");
        }

        if (fator_carga() >= maxFatorCarga) {
            expande();
        }

        // calcula o hash da chave, e com ele o número da linha
        int linha = Math.abs(chave.hashCode()) % tab.length;

        // verifica se existe um par contendo esta chave
        // se existir, ele está na linha da tabela correspondente
        // ao hash
        ListaSequencial<Par> pares = tab[linha];
        if (pares != null) {
            //for (Par p : pares)
            for (int pos=0; pos < pares.comprimento(); pos++) {
                Par p = pares.obtem(pos);
                if (chave.equals(p.chave)) {
                    p.valor = valor;
                    return;
                }
            }
            pares.adiciona(new Par(chave, valor));
            len++;
        }
    }

    public V obtem(K chave) {

        if (chave == null) {
            throw new InvalidParameterException("a chave não pode ser null");
        }

        int linha = Math.abs(chave.hashCode()) % tab.length;

        ListaSequencial<Par> pares = tab[linha];

        if (pares != null) {
            for (Par p : pares) {
                if (chave.equals(p.chave)) {
                    return p.valor;
                }
            }
        }

        throw new IndexOutOfBoundsException("chave inexistente");
    }

    public void remove(K chave) {

        if (chave == null) {
            throw new InvalidParameterException("a chave não pode ser null");
        }

        int linha = Math.abs(chave.hashCode()) % tab.length;

        ListaSequencial<Par> pares = tab[linha];

        if (pares != null) {
            for (int pos = 0; pos < pares.comprimento(); pos++) {
                Par p = pares.obtem(pos);

                if (chave.equals(p.chave)) {
                    pares.remove(pos);
                    len--;
                    return;
                }
            }
        }

        throw new IndexOutOfBoundsException("chave inexistente");
    }

    public boolean contem(K chave) {

        if (chave == null) {
            throw new InvalidParameterException("a chave não pode ser null");
        }

        int linha = Math.abs(chave.hashCode()) % tab.length;

        ListaSequencial<Par> pares = tab[linha];

        if (pares != null) {
            for (Par p : pares) {
                if (chave.equals(p.chave)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean esta_vazia() {
        return len == 0;
    }

    public V obtem_ou_default(K chave, V defval) {

        if (chave == null) {
            throw new InvalidParameterException("a chave não pode ser null");
        }

        try {
            return obtem(chave);
        } catch (IndexOutOfBoundsException e) {
            return defval;
        }
    }


    public ListaSequencial<K> chaves() {
        ListaSequencial<K> lk = new ListaSequencial<>();

        for (ListaSequencial<Par> lista : tab) {
            if (lista != null) {
                for (Par p : lista) {
                    lk.adiciona(p.chave);
                }
            }

        }

        return lk;
    }

    public ListaSequencial<V> valores() {
        ListaSequencial<V> lv = new ListaSequencial<>();

        for (ListaSequencial<Par> lista : tab) {
            if (lista != null) {
                for (Par p : lista) {
                    lv.adiciona(p.valor);
                }
            }
        }

        return lv;
    }

    public ListaSequencial<Par> items() {
        ListaSequencial<Par> lp = new ListaSequencial<>();

        for (ListaSequencial<Par> lista : tab) {
            if (lista != null) {
                for (Par p : lista) {
                    lp.adiciona(p);
                }
            }
        }

        return lp;
    }

    public int comprimento() {
        return len;
    }

    public void limpa() {
        // remove os pares
        tab = inicia_tabela(defcap);
        len = 0;
    }
}