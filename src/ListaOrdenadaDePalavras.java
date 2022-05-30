/**
 * Esta classe guarda as palavra do indice remissivo em ordem alfabetica.
 * 
 * @author Isabel H. Manssour
 */
public class ListaOrdenadaDePalavras {
    public ListaDeOcorrencias listaOcorrencias;

    // Classe interna
    private class Palavra {
        public int ocorrencia;
        public String palavra;
        public Palavra next;

        public Palavra(String str) {
            this.palavra = str;
            this.next = null;
            listaOcorrencias = new ListaDeOcorrencias();
        }
    }

    private Palavra head;
    private Palavra tail;
    private int count;
    private int maiorOcorrencia = 0;

    // Construtores
    public ListaOrdenadaDePalavras() {
        head = null;
        tail = null;
        count = 0;
    }

    // metodo add para adicionar uma palavra na lista
    public void add(String palavra, int numeroDaPagina) {

        Palavra aux = head;
        palavra = palavra.toLowerCase();

        if (count == 0) {
            Palavra n = new Palavra(palavra);
            head = n;
            tail = n;
            n.ocorrencia++;
            count++;
            listaOcorrencias.add(palavra, numeroDaPagina);
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

        Palavra n = new Palavra(palavra);
        tail.next = n;
        tail = n;
        count++;
        n.ocorrencia++;
        listaOcorrencias.add(palavra, numeroDaPagina);

    }

    // metodo toString
    @Override
    public String toString() {

        Palavra aux = head;
        StringBuilder s = new StringBuilder();
        while (aux != null) {

            s.append("Palavra: " + aux.palavra + "  |   Paginas: " + listaOcorrencias.todasPaginas(aux.palavra));
            s.append("\n");
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
        System.out.println("Palavra: " + palavraAux + " | Numero Ocorrencias: " + maiorOcorrencia);
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

    public String listar(Palavra p) {
        return "Palavra: " + p.palavra + "; Lista: " + listaOcorrencias.todasPaginas(p.palavra);
    }
}