/**
 * Esta classe guarda as palavra do indice remissivo em ordem alfabetica.
 * 
 * @author Isabel H. Manssour
 */
public class ListaOrdenadaDePalavras {
    public ListaDeOcorrencias listaOcorrencias;

    // Classe interna
    private class Palavra {
        public String s;
        public Palavra next;

        public Palavra(String str) {
            s = str;
            next = null;
            listaOcorrencias = new ListaDeOcorrencias();
        }
    }

    private Palavra head;
    private Palavra tail;
    private int count;

    // Construtores
    public ListaOrdenadaDePalavras() {
        head = null;
        tail = null;
        count = 0;
    }

    // metodo add para adicionar uma palavra na lista
    public void add(String palavra, int numeroDaPagina) {

        if (count == 0) {
            Palavra n = new Palavra(palavra);
            head = n;
            tail = n;
            count++;
            listaOcorrencias.add(numeroDaPagina);

            return;
        }
        if (contains(palavra)) {
            listaOcorrencias.add(numeroDaPagina);
            return;
        }else{
            Palavra n = new Palavra(palavra);
        tail.next = n;
        tail = n;
        count++;
        listaOcorrencias.add(numeroDaPagina);
        }
    }

    // metodo toString

    // demais metodos necessarios
    public boolean contains(String palavra) {
        Palavra aux = head;
        while (aux != null) {
            if (aux.s == palavra) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    public String listar() {
        StringBuilder s = new StringBuilder();
        Palavra aux = head;

        while (aux != null) {

            s.append("Palavra: " + aux.s + "  |   Linhas: ");
            s.append(listaOcorrencias.listar());
            s.append("\n");
            aux = aux.next;
        }
        return s.toString();
    }
}