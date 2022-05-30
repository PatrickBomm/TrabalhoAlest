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

    // Metodos

    public StopWord() {

    }

    public void start() {
        head = null;
        tail = null;
        count = 0;
    }

    public boolean contains(String palavra) {
        Node aux = head;
        while (aux != null) {
            if (aux.palavra == palavra) {
                System.out.println("tem");
                return true;
            }
            aux = aux.next;
        }
        return false;
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

        String l = "";
        arquivo.open("assets/stopWords.txt");
        do {
            if (l == null) { // acabou o arquivo?
                break;
            }
            l = arquivo.getNextLine();
            add(l);

        } while (true);
        arquivo.close();
    }

    public String listar() {

        Node aux = head;
        String s = "";
        while (aux != null) {
            s += ("Palavra: " + aux.palavra );
            s += ("\n");
            aux = aux.next;
        }
        return s.toString();
    }
}
