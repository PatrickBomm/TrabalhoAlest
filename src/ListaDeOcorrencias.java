/**
 * Esta classe guarda os numeros das paginas em que uma palavra ocorre.
 * 
 * @author Isabel H. Manssour
 */
public class ListaDeOcorrencias {
    // Classe interna Node
    private class Node {
        public int numeroDaPagina;
        public Node next;
        public String palavra;

        public Node(String p, int n) {
            this.numeroDaPagina = n;
            this.palavra = p;
            next = null;
        }
    }

    // Atributos
    private Node head;
    private Node tail;
    private int count;

    // Metodos

    public ListaDeOcorrencias() {

    }

    public void start() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Retorna true se a lista nao contem elementos.
     * 
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Retorna o numero de elementos da lista.
     * 
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }

    /**
     * Adiciona um numero de pagina ao final da lista, caso ele ainda
     * nao tenha sido adicionado.
     * 
     * @param element elemento a ser adicionado ao final da lista
     * @return true se adicionou no final da lista o numero de pagina
     *         recebido por parametro, e false caso contrario.
     */
    public boolean add(String palavra, int numPagina) {

        Node n = new Node(palavra, numPagina);
        if (count == 0) {
            head = n;
            tail = n;
            count++;
            return true;
        }

        Node aux = head;
        while (aux != null) {
            if (aux == n) {
                return false;
            }
            aux = aux.next;
        }
        tail.next = n;
        tail = n;
        count++;
        return true;

    }

    /**
     * Retorna true se a lista contem o numero de pagina passado
     * por parametro.
     * 
     * @param numPagina o elemento a ser procurado
     * @return true se a lista contem o elemento especificado
     */
    public boolean contains(String p) {
        Node aux = head;
        while (aux != null) {
            if (aux.palavra == p) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    public String todasPaginas(String palavra) {

        Node aux = head;
        String s = "";
        while (aux != null) {
            if (aux.palavra.equals(palavra)) {
                s += (aux.numeroDaPagina + "; ");
            }
            aux = aux.next;
        }
        return s;

    }

    public String toString() {
        String s = "";
        Node aux = head;

        while (aux != null) {
            s += (aux.numeroDaPagina);
            s += ("; ");
            aux = aux.next;
        }
        return s;

    }
}
