import java.util.*;

public class App {
    Scanner sc = new Scanner(System.in);
    ListaOrdenadaDePalavras ls = new ListaOrdenadaDePalavras();
    private int countPalavras;

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.view();

    }

    public void view() {

        readTxt();

        boolean cond = true;

        while (cond) {
            System.out.println("\nMenu!\n");
            System.out.println("Escolha a alternativa desejada:\n");
            System.out.println(
                    "1. Exibir todo o índice remissivo (em ordem alfabética);\n2. Exibir o percentual de stopwords do texto (quanto % do texto é formado por stopwords);\n3. Encontrar a palavra mais frequente, isto é, com maior número de ocorrências;\n4. Pesquisar palavras, isto é, o usuário informa uma palavra e o programa lista os números das páginas em que a palavra ocorre;\n5. Listar todo o índice remissivo;\n6. Encerrar o programa.");
            int opc = sc.nextInt();

            switch (opc) {
                case 1:
                    System.out.println(ls.toString());

                    break;
                case 2:
                    int percentual = (ls.size() * 100) / countPalavras;

                    System.out.println("Percentual: " + percentual + "%");

                    break;
                case 3:
                    ls.maiorOcorrencia();

                    break;
                case 4:

                    System.out.println("Digite a palavra que deseja pesquisar: ");
                    sc.nextLine();
                    String palavra = sc.nextLine();
                    palavra = palavra.toLowerCase();
                    ls.pesquisarPalavra(palavra);

                    break;
                case 5:

                    break;
                case 6:
                    cond = false;
                    System.out.println("\nFinalizando programa!");
                    break;
                default:
                    System.out.println("\nOpcao Invalida!");
                    break;
            }

        }

    }

    public void readTxt() {
        int nLinha = 0;
        int nPagina = 0;

        LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
        String l;

        ArquivoTexto arquivo = new ArquivoTexto();

        System.out.println("Digite o nome do arquivo com o .txt:");
        String arquivoNome = "assets/";
        arquivoNome += sc.nextLine();

        arquivo.open(arquivoNome);

        do // laco que passa em cada linha do arquivo
        {
            l = arquivo.getNextLine();
            if (l == null) { // acabou o arquivo?
                break;
            }
            nLinha++; // conta mais uma linha lida do arquivo
            if (nLinha == 10) // chegou ao fim da pagina?
            {
                nLinha = 0;
                nPagina++;
            }

            linha.setLine(l); // define o texto da linha
            do // laco que passa em cada palavra de uma linha
            {
                String palavra = linha.getNextWord();
                countPalavras++; // obtem a proxima palavra da linha
                if (palavra == null)// acabou a linha
                {
                    break;
                }
                ls.add(palavra, nPagina);
            } while (true);
            
        } while (true);

        arquivo.close();
    }
}
