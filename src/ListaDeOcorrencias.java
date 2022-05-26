

/**
 * Esta classe guarda os numeros das paginas em que uma palavra ocorre.
 * @author Isabel H. Manssour
 */
public class ListaDeOcorrencias {
        
    // Classe interna Node
    private class Node {
        public int numeroDaPagina;
        public Node next;   

        public Node(int n) {
            numeroDaPagina = n;
            next = null;
        }
    }
    
    // Atributos
    private Node head;
    private Node tail;
    private int count;
    private ListaOrdenadaDePalavras ls;

    // Metodos 
    public ListaDeOcorrencias() {
        head = null;
        tail = null;
        count = 0;
    }
    
    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (head == null);
    }   
    
    /**
     * Retorna o numero de elementos da lista.
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }  
    
    /**
     * Esvazia a lista
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Adiciona um numero de pagina ao final da lista, caso ele ainda
     * nao tenha sido adicionado.
     * @param element elemento a ser adicionado ao final da lista
     * @return true se adicionou no final da lista o numero de pagina  
     * recebido por parametro, e false caso contrario.
     */
    public boolean add(int numPagina)  {
       
        Node n = new Node(numPagina);
        if(count == 0){
            head = n;
            tail = n;
            count++; 
            return true;
        }
        if(contains(numPagina)){
            return false;
        }
       tail.next = n;
       tail = n;
       count++;
       return true;

    }  
    
    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */    
    public Integer get(int index) {
        int counter = 0;

        Node aux = head;

        for(int i = 0; i < count; i++){
            if(counter -  1 == index){
                return aux.numeroDaPagina;
            }
            aux = aux.next;
            counter++;
        }

        return 0;
    }
 
    /**
     * Retorna true se a lista contem o numero de pagina passado
     * por parametro.
     * @param numPagina o elemento a ser procurado
     * @return true se a lista contem o elemento especificado
     */
    public boolean contains(int numPagina) {
        Node aux = head;
        while(aux != null) {
            if (aux.numeroDaPagina == numPagina) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }    
    
    public StringBuilder listar(){
        StringBuilder s = new StringBuilder();
        int auxiliar;
        Node aux = head;

        while (aux != null) {
            auxiliar = aux.numeroDaPagina;
            if(ls.listaOcorrencias.equals(aux)){
            s.append(auxiliar);
            s.append("; ");
            aux = aux.next;}
        }
        return s;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int auxiliar;
        Node aux = head;

        while (aux != null) {
            auxiliar = aux.numeroDaPagina;
            s.append(auxiliar);
            s.append("\n");
            aux = aux.next;
        }

        return s.toString();
    }
}
