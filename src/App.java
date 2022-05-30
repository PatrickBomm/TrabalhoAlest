import java.io.File;
import java.io.IOException;
import java.util.*;

public class App {
    Scanner sc = new Scanner(System.in);
    ListaOrdenadaDePalavras ls = new ListaOrdenadaDePalavras();
    ListaDeOcorrencias ocorrencias = new ListaDeOcorrencias();
    StopWord sw = new StopWord();
    int countPalavras;

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.view();

    }

    public void view() throws IOException {
        
        ocorrencias.start();
        sw.readTxt();
        ls.start();
        nomeArquivo();

        boolean cond = true;

        while (cond) {
            System.out.println("\nMenu!\n");
            System.out.println("Escolha a alternativa desejada:\n");
            System.out.println(
                    "1. Exibir todo o índice remissivo (em ordem alfabética);\n2. Exibir o percentual de stopwords do texto (quanto % do texto é formado por stopwords);\n3. Encontrar a palavra mais frequente, isto é, com maior número de ocorrências;\n4. Pesquisar palavras, isto é, o usuário informa uma palavra e o programa lista os números das páginas em que a palavra ocorre;\n5. Carregar outro arquivo txt;\n6. Encerrar o programa.");
            int opc = sc.nextInt();

            switch (opc) {
                case 1:
                if(ls.size() ==0){
                    System.out.println("\nNenhum objeto encontrado!");
                    break;
                }
                    System.out.println(ls.listar());

                    break;
                case 2:
                    int percentual = (sw.sizePalavras() * 100) / countPalavras;
                    System.out.println("\nPercentual: " + percentual + "%");

                    break;
                case 3:
                    ls.maiorOcorrencia();

                    break;
                case 4:
                if(ls.size() ==0){
                    System.out.println("\nNenhum objeto encontrado!");
                    break;
                }

                    System.out.println("\nDigite a palavra que deseja pesquisar: ");
                    sc.nextLine();
                    String palavra = sc.nextLine();
                    palavra = palavra.toLowerCase();
                    if(ls.pesquisarPalavra(palavra) == null){
                        System.out.println("\nPalavra não encontrada!");
                        break;
                    }else{
                        ls.listar(ls.pesquisarPalavra(palavra));
                        
                    }

                    break;
                case 5:

                    ls.clear();
                    ocorrencias.clear();
                    sw.clear();

                    countPalavras = 0;

                    sc.nextLine();                    

                    nomeArquivo();

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

    public void readTxt(String arquivoNome) throws IOException {
        int nLinha = 0;
        int nPagina = 1;

        LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
        String l;

        ArquivoTexto arquivo = new ArquivoTexto();


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
                String palavra = linha.getNextWord(); // obtem a proxima palavra da linha
                if (palavra == null)// acabou a linha
                {
                    break;
                }
                countPalavras++;
                if (nPagina >= 0) {
                    int a = sw.contains(palavra);

                    if (a == 0) {
                        ls.add(palavra, nPagina);

                    }

                }

            } while (true);

        } while (true);

        arquivo.close();

    }

    public void visualizarArquivos() throws IOException {

        File file = new File("assets/");
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            System.out.println(arquivos.getName());
        }

    }

    public void nomeArquivo() throws IOException{
        System.out.println("\nArquivos na pasta 'assets':\n");
        visualizarArquivos();

        System.out.println("\nDigite o nome do arquivo com o .txt:");
        String arquivoNome = "assets/";
        arquivoNome += sc.nextLine();

        readTxt(arquivoNome);
    }
}
