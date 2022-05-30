public class StopWord {
    ArquivoTexto arquivo = new ArquivoTexto();

    private class Node {
        public Node next;
        public String palavra;

        public Node(String p) {
            this.palavra = p;
            next = null;
        }
    }

    // Atributos
    private Node head;
    private Node tail;
    private int count;
    private int countPalavras;

    // Metodos

    public StopWord() {

    }

    public void start() {
        head = null;
        tail = null;
        count = 0;
    }

    public int contains(String palavra) {
        Node aux = head;
        while (aux != null) {
            if (aux.palavra.equals(palavra)) {
                countPalavras++;
                return 1;
            }
            aux = aux.next;
        }
        return 0;
    }

    public void add(String palavra) {
        if (palavra == null) {
            return;
        }
        palavra = palavra.toLowerCase();
        Node n = new Node(palavra);
        if (count == 0) {
            head = n;
            tail = n;
            count++;
            return;
        }
        tail.next = n;
        tail = n;
        count++;

    }

    public void readTxt() {

        String aux = "";
        String l = "";
        arquivo.open("assets/stopWords.txt");
        do {
            if (l == null || aux == null) {
                break;
            }
            l = arquivo.getNextLine();
            aux = l;
            if (aux != null) {
                l = aux.replaceAll(" ", "");
                add(l);
            }
        } while (true);
        arquivo.close();
    }

    public String listar() {

        Node aux = head;
        String s = "";
        while (aux != null) {
            s += ("Palavra: " + aux.palavra);
            s += ("\n");
            aux = aux.next;
        }
        return s.toString();
    }

    public int size() {
        return count;
    }

    public int sizePalavras() {
        return countPalavras;
    }

}
