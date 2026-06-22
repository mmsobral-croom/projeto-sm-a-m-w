package esd;

public class APB <T extends Comparable<T>> {
    class NodoAPB {
        T valor;
        NodoAPB esq = null, dir = null;

        NodoAPB(T val) {
            valor = val;
        }
    }

    NodoAPB raiz = null;
    int len = 0; // tamanho da árvore (quantidade de nodos)

    // a seguir devem ser escritos os métodos da classe APB

    public APB() {}

    public void adiciona(T val) {
        if (len == 0) {
            raiz = new NodoAPB(val);
            len++;
        } else {
            // algoritmo para adicionar ...
            NodoAPB atual = raiz;
            while (true) {

                int cmp = val.compareTo(atual.valor);
                if (cmp == 0) break;
                if (cmp < 0) {
                    if (atual.esq == null) {
                        atual.esq = new NodoAPB(val);
                        len++;
                        break;
                    } else {
                        atual = atual.esq;
                    }
                } else {
                    if (atual.dir == null) {
                        atual.dir = new NodoAPB(val);
                        len++;
                        break;
                    } else {
                        atual = atual.dir;
                    }
                }
            }
        }
    }

    public T procura(T val) {

        if (len > 0) {
            NodoAPB atual = raiz;
            while (atual != null) {
                int cmp = val.compareTo(atual.valor);
                if (cmp == 0) {
                    return atual.valor;
                }
                if (cmp < 0) atual = atual.esq;
                else atual = atual.dir;
            }
        }

        return null;
    }

    public T obtem_raiz() {
        if (len > 0) return raiz.valor;
        return null;
    }

    @Override
    public APB<T> clone() {
        APB<T> nova = new APB<>();

        var valores = preOrdem();
        for (var valor : valores) nova.adiciona(valor);

        return nova;
    }

    public boolean esta_vazia() {
        return len == 0;
    }

    // comprimento
    public int tamanho() {
        return len;
    }

    void _preOrdem(NodoAPB atual, ListaSequencial<T> lista) {

        lista.adiciona(atual.valor);

        if (atual.esq != null) _preOrdem(atual.esq, lista);

        if (atual.dir != null) _preOrdem(atual.dir, lista);

    }

    public ListaSequencial<T> preOrdem() {

        ListaSequencial<T> lista = new ListaSequencial<>();

        if (raiz != null) _preOrdem(raiz, lista);

        return lista;

    }

    void _emOrdem(NodoAPB atual, ListaSequencial<T> lista) {
        // aqui se faz a enumeração com abordagem recursiva

        // se tem nodo esquerdo, faz emOrdem desse nodo
        if (atual.esq != null) _emOrdem(atual.esq, lista);

        // adiciona valor do nodo atual na lista
        lista.adiciona(atual.valor);

        // se tem nodo direito, faz emOrdem desse nodo
        if (atual.dir != null) _emOrdem(atual.dir, lista);
    }

    public ListaSequencial<T> emOrdem() {
        // aqui apenas cria-se uma lista, onde os valores enumerados serão armazenados
        ListaSequencial<T> lista = new ListaSequencial<>();

        // aqui se faz de fato a enumeração a partir da raiz, guardando na lista os valores acessados
        if (raiz != null) _emOrdem(raiz, lista);

        return lista;
    }

    void _posOrdem(NodoAPB atual, ListaSequencial<T> lista) {

        if (atual.esq != null) _posOrdem(atual.esq, lista);

        if (atual.dir != null) _posOrdem(atual.dir, lista);

        lista.adiciona(atual.valor);
    }

    public ListaSequencial<T> posOrdem() {

        ListaSequencial<T> lista = new ListaSequencial<>();

        if (raiz != null) _posOrdem(raiz, lista);

        return lista;
    }

    /*

    public ListaSequencial<T> emLargura() {

        Fila<NodoAPB> fila = new Fila<>();

        ListaSequencial<T> resultado = new ListaSequencial<>();

        fila.adiciona(raiz);

        while (!fila.estaVazia()) {
            NodoAPB atual = fila.remove();

            resultado.adiciona(atual.valor);

            if (atual.esq != null) {
                fila.adiciona(atual.esq);
            }

            if (atual.dir != null) {
                fila.adiciona(atual.dir);
            }
        }

        return resultado;
    }

    */

    public T menor() {

        NodoAPB atual = raiz;

        while (true) {
            if (atual.esq != null) {
                atual = atual.esq;
            } else {
                break;
            }
        }

        return atual.valor;
    }

    public T maior() {

        NodoAPB atual = raiz;

        while (true) {
            if (atual.dir != null) {
                atual = atual.dir;
            } else {
                break;
            }
        }

        return atual.valor;
    }

    public T maior_que(T val) {

        NodoAPB atual = raiz;

        T maior = null;

        while (atual != null) {
            int cmp = atual.valor.compareTo(val);

            if (cmp == 0) {
                return atual.valor;
            }

            if (cmp > 0) {
                maior = atual.valor;
                atual = atual.esq;
            } else {
                atual = atual.dir;
            }
        }

        return maior;
    }

    public T menor_que(T val) {

        NodoAPB atual = raiz;

        T menor = null;

        while (atual != null) {
            int cmp = atual.valor.compareTo(val);

            if (cmp == 0) {
                return atual.valor;
            }

            if (cmp < 0) {
                menor = atual.valor;
                atual = atual.dir;
            } else {
                atual = atual.esq;
            }
        }

        return menor;
    }

    void menores_que(NodoAPB atual, T val, ListaSequencial<T> resultado) {

        int cmp = atual.valor.compareTo(val);

        if (cmp > 0) {
            // valor atual é maior que limiar, então
            // valores menores somente podem existir no ramo esquerdo
            if (atual.esq != null) menores_que(atual.esq, val, resultado);
        } else {
            // valor atual <= limiar, então deve ser incluído
            resultado.adiciona(atual.valor);
            // também valores à esquerda, porque são menores
            if (atual.esq != null) _preOrdem(atual.esq, resultado);
            // e pode haver valores menores à direita
            if (atual.dir != null) menores_que(atual.dir, val, resultado);
        }
    }

    public ListaSequencial<T> menores_que(T val) {

        ListaSequencial<T> resultado = new ListaSequencial<>();
        if (raiz != null) menores_que(raiz, val, resultado);

        return resultado;
    }

    void maiores_que(NodoAPB atual, T val, ListaSequencial<T> result) {

        int cmp = atual.valor.compareTo(val);

        if (cmp < 0) {
            // valor atual é menor que limiar, então
            // valores maiores somente podem existir no ramo direito
            if (atual.dir != null) maiores_que(atual.dir, val, result);
        } else {
            // valor atual >= limiar, então deve ser incluído
            result.adiciona(atual.valor);
            // também valores à direita, porque são maiores
            if (atual.dir != null) _preOrdem(atual.dir, result);
            // e pode haver valores maiores à esquerda
            if (atual.esq != null) maiores_que(atual.esq, val, result);
        }
    }

    public ListaSequencial<T> maiores_que(T val) {

        ListaSequencial<T> resultado = new ListaSequencial<>();
        if (raiz != null) maiores_que(raiz, val, resultado);

        return resultado;
    }

    void faixa(NodoAPB nodo, T limiarInferior, T limiarSuperior, ListaSequencial<T> resultado) {

        if (nodo == null) {
            return;
        }

        int cmpInferior = nodo.valor.compareTo(limiarInferior);
        int cmpSuperior = nodo.valor.compareTo(limiarSuperior);

        if (cmpInferior < 0) {
            faixa(nodo.dir, limiarInferior, limiarSuperior, resultado);

        } else if (cmpSuperior > 0) {
            faixa(nodo.esq, limiarInferior, limiarSuperior, resultado);

        } else {
            faixa(nodo.esq, limiarInferior, limiarSuperior, resultado);
            resultado.adiciona(nodo.valor);
            faixa(nodo.dir, limiarInferior, limiarSuperior, resultado);
        }
    }

    public ListaSequencial<T> faixa(T inicio, T fim) {

        ListaSequencial<T> resultado = new ListaSequencial<>();

        faixa(raiz, inicio, fim, resultado);

        return resultado;
    }

    // Rotação esquerda
    NodoAPB rotaciona_esquerda(NodoAPB n1) {
        // aqui se faz a rotação esquerda. Ao final, deve-se retornar o novo nodo que representa a raiz da árvore
        // rotacionada
        NodoAPB n2 = n1.dir;
        NodoAPB b = n2.esq;

        // conecta n1, n2 e b para fazer a rotação esquerda
        n2.esq = n1;
        n1.dir = b;

        return n2;
    }

    // Rotação direita
    NodoAPB rotaciona_direita(NodoAPB n2) {
        // aqui se faz a rotação direita. Ao final, deve-se retornar o novo nodo que representa a raiz da árvore
        // rotacionada
        NodoAPB n1 = n2.esq;
        NodoAPB b = n1.dir;

        // conecta n1, n2 e b para fazer a rotação direita
        n1.dir = n2;
        n2.esq = b;

        return n1;
    }

    // Este método implementa de fato o balanceamento AVL
    NodoAPB balanceia_avl(NodoAPB raiz) {
        // balanceamento a partir da raiz passada como parãmetro

        if (raiz == null) {
            return null;
        }

        int fb;

        if (raiz.esq != null) {
            raiz.esq = balanceia_avl(raiz.esq);
        }

        if (raiz.dir != null) {
            raiz.dir = balanceia_avl(raiz.dir);
        }

        fb = fatorB(raiz);

        while (fb < -1) {

            if (fatorB(raiz.dir) > 0) {
                raiz.dir = rotaciona_direita(raiz.dir);
            }

            raiz = rotaciona_esquerda(raiz);
            fb = fatorB(raiz);
        }

        while (fb > 1) {

            if (fatorB(raiz.esq) < 0) {
                raiz.esq = rotaciona_esquerda(raiz.esq);
            }

            raiz = rotaciona_direita(raiz);
            fb = fatorB(raiz);
        }

        return raiz;
    }

    // Método para balancear a árvore
    public void balanceia() {
        // aqui prepara-se para calcular a altura a partir da raiz
        if (raiz != null) {
            // realiza o balanceamento da árvore. A raiz é modificada pelo algoritmo, portanto precisa
            // ser atualizada
            raiz = balanceia_avl(raiz);
        }
    }

    int _altura(NodoAPB atual) {
        // aqui se faz de fato o cálculo da altura

        int ae = 0, ad = 0;

        if (atual.esq != null) ae = 1 + _altura(atual.esq);
        if (atual.dir != null) ad = 1 + _altura(atual.dir);

        return Math.max(ae, ad);
    }

    int fatorB(NodoAPB atual) {
        int ae = 0; // altura esquerda
        int ad = 0; // altura direita

        if (atual.esq != null) ae = 1 + _altura(atual.esq);
        if (atual.dir != null) ad = 1 + _altura(atual.dir);

        return ae - ad;
    }

    public int altura() {
        // aqui prepara-se para calcular a altura a partir da raiz
        if (raiz != null) {
            return _altura(raiz);
        }

        // uma árvore vazia tem altura ???
        return 0;
    }
}
