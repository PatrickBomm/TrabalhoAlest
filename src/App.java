import java.util.*;
public class App {
    
    public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
             
    int nLinha = 0;
    int nPagina = 0;
    
        ListaOrdenadaDePalavras ls = new ListaOrdenadaDePalavras();
    

    ArquivoTexto arquivo = new ArquivoTexto(); // objeto que gerencia o arquivo
    LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
    String l;

    System.out.println("Digite o nome do arquivo com o .txt:");
    String arquivoNome = "assets/";
     arquivoNome += sc.nextLine();
    arquivo.open(arquivoNome);


    do  // laco que passa em cada linha do arquivo
    {
        l = arquivo.getNextLine();
        if (l==null){ // acabou o arquivo?
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
            
            ls.add(palavra, nPagina);

         } while (true);

         System.out.println(ls.listar());
         
        

    } while (true);
    
    arquivo.close();        
    }
}
