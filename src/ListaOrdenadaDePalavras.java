/**
 * Esta classe guarda as palavra do indice remissivo em ordem alfabetica.
 * 
 * @author Isabel H. Manssour
 */
public class ListaOrdenadaDePalavras {
    ListaDeOcorrencias listaOcorrencias = new ListaDeOcorrencias();
    ArquivoTexto aq = new ArquivoTexto();

    // Classe interna
    private class Palavra {
        public int ocorrencia;
        public String palavra;
        public Palavra next;

        public Palavra(String str) {
            this.palavra = str;
            this.next = null;
        }
    }

    private Palavra head;
    private Palavra tail;
    private int count;
    private int maiorOcorrencia = 0;

    // Construtores
    public ListaOrdenadaDePalavras() {

    }
    
    public void start(){
        head = null;
        tail = null;
        count = 0;
    }

    // metodo add para adicionar uma palavra na lista
   // metodo add para adicionar uma palavra na lista
   public void add(String palavra, int numeroDaPagina) {

    Palavra aux = head;
    palavra = removeAllNonAlphaNumeric(palavra.toLowerCase());

    if (count == 0) {
        Palavra n = new Palavra(palavra);
        head = n;
        tail = n;
        n.ocorrencia++;
        count++;
        listaOcorrencias.add(palavra, numeroDaPagina);
        return;
    }

    if(palavra.trim().isEmpty()) {
        return;
    }

    for (int i = 0; i < count; i++) {
        if (aux.palavra.equals(palavra)) {
            this.listaOcorrencias.add(palavra, numeroDaPagina);
            aux.ocorrencia++;
            return;
        }
        aux = aux.next;
    }

    Palavra comp = head;
    int posicao = 0;

    for (int i = 0; i < count ; i++) {
    if ((palavra.compareToIgnoreCase(comp.palavra)) < 0) {
        addIndex(posicao, palavra);
        return;
    } else {
     comp = comp.next;   
     posicao++;
    }
}
    Palavra n = new Palavra(palavra);
    tail.next = n;
    tail = n;
    count++;
    n.ocorrencia++;
    listaOcorrencias.add(palavra, numeroDaPagina);

}

// Método usado pelo outro método add() para adicionar em ordem
public void addIndex(int index, String palavra) {
    // Primeiro verifica se o indice eh valido
    if (index < 0 || index > size()) {
        throw new IndexOutOfBoundsException();
    }

    // Depois cria o nodo
    Palavra n = new Palavra(palavra);

    // "Encadear" o nodo criado na lista
    if (index == 0) { // se insercao no inicio
        if (count == 0) { // se a lista estava vazia
            tail = n;
        } else {
            n.next = head;
        }
        head = n;
    } else if (index == count) { // se insercao no fim
        tail.next = n;
        tail = n;
    } else { // se insercao no meio
        Palavra ant = head;
        for (int i = 0; i < index - 1; i++) {
            ant = ant.next;
        }
        n.next = ant.next;
        ant.next = n;
    }

    // Atualiza o atributo count
    count++;
}

    // metodo toString

    public String listar() {

        Palavra aux = head;
        String s = "";
        while (aux != null) {
            s += ("Palavra: " + aux.palavra + "  |   Paginas: " + listaOcorrencias.todasPaginas(aux.palavra));
            s += ("\n");
            aux = aux.next;
        }
        return s.toString();
    }

    // demais metodos necessarios
    public boolean contains(String palavra) {
        Palavra aux = head;
        while (aux != null) {
            if (aux.palavra == palavra) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    public void maiorOcorrencia() {
        String palavraAux = "";
        Palavra p = head;
        while (p != null) {
            if (p.ocorrencia > maiorOcorrencia) {
                maiorOcorrencia = p.ocorrencia;
                palavraAux = p.palavra;
            }
            p = p.next;
        }
        System.out.println("Palavra: " + palavraAux + " |   Numero Ocorrencias: " + maiorOcorrencia);
    }

    public int size() {
        return count;
    }

    public Palavra pesquisarPalavra(String p) {
        Palavra aux = head;

        for (int i = 0; i < listaOcorrencias.size(); i++) {
            if (aux.palavra.equals(p)) {
            listar(aux);
                return aux;
            }
            aux = aux.next;
        }
        return null;
    }

    public void listar(Palavra p) {
       System.out.println("Palavra: " + p.palavra + "  |   Paginas: " + listaOcorrencias.todasPaginas(p.palavra));
       
    }

    @Override
    public String toString() {

        Palavra aux = head.next;
        StringBuilder s = new StringBuilder();
        while (aux != null) {

            s.append("Palavra: " + aux.palavra + "  |   Paginas: " + listaOcorrencias.todasPaginas(aux.palavra));
            s.append("\n");
            aux = aux.next;
        }
        return s.toString();
    }

    public static String removeAllNonAlphaNumeric(String s) {
        if (s == null) {
            return null;
        }
        return s.replaceAll("[^A-Za-z0-9]", "");
    }
}