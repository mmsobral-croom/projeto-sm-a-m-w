package esd;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class ListaSequencial<T> implements Iterable <T> {

    // esta classe define um iterador da Lista Sequencial
    // foi definida como classe aninhada da Lista Sequencial, mas isso não é obrigatório !
    public class ListaSequencialIterator implements Iterator<T> {
        // Cada estrutura de dados terá atributos específicos para ser possível
        // iterá-la. No caso da Lista Sequencial, são estes:

        // A Lista Sequencial que está sendo iterada
        ListaSequencial<T> q;

        // a posição atual da iteração
        int pos = 0;

        // pelo construtor se passa a Lista Sequencial a ser iterada
        ListaSequencialIterator(ListaSequencial<T> q) {
            this.q = q;
        }

        @Override
        public boolean hasNext() {
            // a iteração não chegou ao fim se a posição atual da iteração for menor que o comprimento da Lista Sequencial
            return pos < q.comprimento();
        }

        @Override
        public T next() {
            // somente pode obter o próximo valor da iteração se ela não chegou ao fim !
            if (! hasNext()) {
                throw new NoSuchElementException("fim da iteração");
            }
            // retorna o próximo valor da iteração, e também incrementa a posição atual da iteração
            return q.obtem(pos++);
        }
    }

    T[] area;
    int len = 0;
    final int defcap = 8;

    @SuppressWarnings("unchecked")
    public ListaSequencial() {
        area = (T[])new Object[defcap];
    }

    // este metodo da classe Lista Sequencial cria um iterador, que possibilita iterar do primeiro ao último objeto
    // armazenado nesta Lista Sequencial
    @Override
    public Iterator<T> iterator() {
        // cria um iterador. Note que esta Lista Sequencial é passada como parãmetro na criação do iterador
        return new ListaSequencialIterator(this);
    }

    @SuppressWarnings("unchecked")
    void expande() {
        // isto será usado quando for necessário expandir a capacidade da lista
        T[] novo = (T[]) new Object[area.length * 2];

        for (int i = 0; i < len; i++) {
            novo[i] = area[i];
        }

        area = novo;
    }

    public boolean esta_vazia() {
        // retorna tue se lista estiver vazia, ou false caso contrário
        return len == 0;
    }

    public int capacidade() {
        // retorna um inteiro que representa a capacidade da lista
        return area.length;
    }

    public void adiciona(T elemento) {
        // adiciona um valor ao final da lista
        if (len == area.length) {
            expande();
        }

        area[len] = elemento;

        len++;
    }

    public void insere(T elemento) {
        // insere um valor no início (posição 0)
        // move uma posição para frente os valores a partir dessa posição

        if (len == area.length) {
            expande();
        }

        for (int i = len; i > 0; i--) {
            area[i] = area[i - 1];
        }

        area[0] = elemento;

        len++;
    }

    public void insere(int indice, T elemento) {
        // insere um valor na posição indicada por "indice"
        // move uma posição para frente os valores a partir dessa posição
        // dispara IndexOutOfBoundsException se "indice" for inválido

        if (indice < 0 || indice > len) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        if (len == area.length) {
            expande();
        }

        for (int i = len; i > indice; i--) {
            area[i] = area[i - 1];
        }

        area[indice] = elemento;

        len++;
    }

    public void insere_ordenado(Comparable valor) {
        // insere o valor na lista, preservando seu ordenamento

        if (len == area.length) {
            expande();
        }

        int posicao = 0;

        for (int i = posicao; posicao < len; posicao++) {
            int cmp = valor.compareTo(area[posicao]);
            if (cmp <= 0) {
                break;
            }
        }
        insere(posicao, (T) valor);
    }

    public T remove(int indice) {
        // remove um valor da posição indicada pelo parâmetro "indice"
        // move uma posição para trás os valores das próximas posições
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida
        // retorna o valor que foi removido da lista

        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        if (esta_vazia()) {
            throw new IndexOutOfBoundsException("A lista está vazia");
        }

        T dado = area[indice];

        for (int i = indice; i < len - 1; i++) {
            area[i] = area[i + 1];
        }

        len--;

        return dado;
    }

    public T remove_rapido(int indice) {
        // remove um valor da posição indicada pelo parâmetro "indice"
        // move para essa posição o valor que está no final da lista
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida
        // retorna o valor que foi removido da lista

        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }

        if (esta_vazia()) {
            throw new IndexOutOfBoundsException("A lista está vazia.");
        }

        T dado = area[indice];

        area[indice] = area[len - 1];

        area[len - 1] = null;

        len--;

        return dado;
    }

    public T remove_ultimo() {
        // remove o último valor da lista
        // disparar uma exceção IndexOutOfBoundsException caso lista vazia
        // retorna o valor que foi removido da lista

        if (esta_vazia()) {
            throw new IndexOutOfBoundsException("A lista está vazia.");
        }

        T dado = area[len - 1];

        area[len - 1] = null;

        len--;

        return dado;
    }

    public void remove(T valor) {
        // remove este valor da lista

        int posicao = 0;

        for (int i = posicao; posicao < len; posicao++) {
            if (area[i].equals(valor)) {
                break;
            }
        }

        for (int i = posicao; i < len - 1; i++) {
            area[i] = area[i + 1];
        }

        len--;
    }

    public int procura(T valor) {
        // retorna um inteiro que representa aposição onde valor foi encontrado pela primeira vez (contando do início da lista)
        // retorna -1 se não o encontrar !
        for (int i = 0; i < len; i++) {
            if (area[i].equals(valor)) {
                return i;
            }
        }

        return -1;
    }

    public boolean esta_ordenada() {
        if (len > 1) {
            for (int i = 0; i < len - 1; i++) {
                Comparable atual = (Comparable) area[i];
                Comparable proximo = (Comparable) area[i + 1];

                int cmp = atual.compareTo(proximo);

                if (cmp > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public T obtem(int indice) {
        // retorna o valor armazenado na posição indica pelo parâmetro "indice"
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida
        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }

        if (esta_vazia()) {
            throw new IndexOutOfBoundsException("A lista está vazia.");
        }

        return area[indice];
    }

    public T primeiro() {
        // retorna o valor armazenado no início da lista
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida

        if (esta_vazia()) {
            throw new IndexOutOfBoundsException("A lista está vazia");
        }

        return area[0];
    }

    public T ultimo() {
        // retorna o valor armazenado no final da lista
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida

        if (esta_vazia()) {
            throw new IndexOutOfBoundsException("A lista está vazia.");
        }

        return area[len - 1];
    }
    public void substitui(int indice, T valor) {
        // armazena o valor na posição indicada por "indice", substituindo o valor lá armazenado atualmente
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida

        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }

        area[indice] = valor;
    }

    public int comprimento() {
        // retorna um inteiro que representa o comprimento da lista (quantos valores estão armazenados)
        return len;
    }

    public void limpa() {
        // esvazia a lista
        for (int i = 0; i < len; i++) {
            area[i] = null;
        }

        len = 0;
    }

    public int busca_binaria(Comparable valor) {
        // procura o valor dentro da lista usando busca binária
        // retorna a posição onde se encontra, ou -1 caso não exista

        int inicio = 0;
        int fim = len - 1;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2;

            Comparable valorMeio = (Comparable) area[meio];

            int cmp = valorMeio.compareTo(valor);

            if (cmp == 0) {
                return meio;
            } else if (cmp < 0) {
                inicio = meio + 1;
            } else {
                fim = meio -1;
            }
        }
        return -1;
    }

    private void ordena_mescla(int pos1, int pos2) {
        if (pos2 - pos1 > 1) {
            int meio = pos1 + (pos2 - pos1) / 2;

            ordena_mescla(pos1, meio);
            ordena_mescla(meio, pos2);

            mescla(pos1, meio, pos2);
        }
    }

    public void ordena() { // merge sort
        ordena_mescla(0, len);
    }

    private void mescla(int pos1, int meio, int pos2) {
        // cria um arranjo auxiliar vazio
        T[] aux = (T[]) new Comparable[pos2 - pos1];

        int i = pos1;
        int j = meio;
        int k = 0;

        // mescla as duas metades da sequencia, entre pos1 e pos2
        // cada uma dessas metades já está ordenada
        while (i < meio && j < pos2) {

            Comparable esquerda = (Comparable) area[i];

            if (esquerda.compareTo(area[j]) <= 0) {
                aux[k] = area[i];
                i++;
            } else {
                aux[k] = area[j];
                j++;
            }
            k++;
        }

        while (i < meio) {
            aux[k] = area[i];
            i++;
            k++;
        }

        while (j < pos2) {
            aux[k] = area[j];
            j++;
            k++;
        }

        for (i = pos1; i < pos2; i++) {
            area[i] = aux[i - pos1];
        }
    }

    public void embaralha() {
        // instruções do algoritmo
        if (len > 1) {
            Random gerador = new Random();
            for (int i = len - 1; i > 0; i--) {
                int j = gerador.nextInt(i + 1);

                T auxiliar = area[i];

                area[i] = area[j];
                area[j] = auxiliar;
            }
        }
    }
}