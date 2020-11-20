import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args)
    {
        List<String> registros = new ArrayList<>();

        String caminho = System.getProperty("user.dir");
        System.out.println(caminho);
        registros = readCsvFile(caminho + "/src/main/resources/tweets.csv");
        int selecao = menuSelecao();

        if (selecao == 1)
        {
            TesteAVL testeAVL = new TesteAVL(registros);
            testeAVL.run();
            System.out.println(testeAVL.contador);
        }
        else if (selecao == 2) {
            TesteHash testeHash = new TesteHash(registros);
            testeHash.run();
            System.out.println(testeHash.contador);
        }
        else if (selecao == 3) {
            TesteLista testeLista = new TesteLista(registros);
            testeLista.run();
            System.out.println(testeLista.contador);
        }
    }

    private static int menuSelecao() {
        Scanner scan = new Scanner(System.in);
        System.out.println("SELELCIONAR UMA DAS ESTRUTURAS");
        System.out.println();
        System.out.println("[1] - ARVORE AVL\n[2] - TABELA HASH\n[3] - LISTA");
        String opcao = scan.next();
        switch (opcao) {
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            default:
                System.out.println("VALOR INVALIDO");
                menuSelecao();
        }

        return 0;
    }

    private static List<String> readCsvFile(String caminho) {
        List<String> registros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(" ");
                for(String valor : valores) {
                    registros.add(valor);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registros;
    }
}
