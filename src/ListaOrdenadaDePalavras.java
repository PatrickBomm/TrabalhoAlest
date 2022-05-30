/**
 * Esta classe guarda as palavra do indice remissivo em ordem alfabetica.
 * 
 * @author Isabel H. Manssour
 */
public class ListaOrdenadaDePalavras {
    ListaDeOcorrencias listaOcorrencias = new ListaDeOcorrencias();

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
    public void add(String palavra, int numeroDaPagina) {

        Palavra aux = head;
        palavra = palavra.toLowerCase();
        Palavra n = new Palavra(palavra);

        if (count == 0) {
            head = n;
            tail = n;
            n.ocorrencia++;
            count++;
            listaOcorrencias.add(palavra, numeroDaPagina);
            return;
        }

        while(aux != null){
            if (aux.palavra.equals(palavra)) {
                listaOcorrencias.add(palavra, numeroDaPagina);
                aux.ocorrencia++;
                return;
            }
            aux = aux.next;
        }
        tail.next = n;
        tail = n;
        count++;
        n.ocorrencia++;
        listaOcorrencias.add(palavra, numeroDaPagina);

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
}