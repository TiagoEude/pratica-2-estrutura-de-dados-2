import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args)
    {
        List<String> registros;

        String caminho = System.getProperty("user.dir");
        String termo = menuTermo();
        registros = readCsvFile(caminho + "/src/main/resources/" + termo + ".csv");
        int selecao = menuSelecao();

        if (selecao == 1)
        {
            TesteAVL testeAVL = new TesteAVL();
            long startTime = System.nanoTime();
            testeAVL.run(registros);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Tempo de execução: " + totalTime + " nanosegundos Para " + testeAVL.contador + " inserções");

        }
        else if (selecao == 2) {
            TesteHash testeHash = new TesteHash();
            long startTime = System.nanoTime();
            testeHash.run(registros);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Tempo de execução: " + totalTime + " nanosegundos Para " + testeHash.contador + " inserções");
        }
        else if (selecao == 3) {
            TesteLista testeLista = new TesteLista();
            long startTime = System.nanoTime();
            testeLista.run(registros);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Tempo de execução: " + totalTime + " nanosegundos Para " + testeLista.contador + " inserções");
            
        }

        print(registros, selecao);
    }

    private static String menuTermo()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("SELEÇÃO DE TERMO:\n[1] - FLAMENGO\n[2] - ELEIÇÕES\n[3] - AMAZÔNIA\n[4] - PANTANAL\n[5] - COVID-19");
        String termo = scan.next();
        switch(termo) {
            case "1":
                return "flamengo";
            case "2":
                return "eleicoes";
            case "3":
                return "amazonia";
            case "4":
                return "pantanal";
            case "5":
                return "covid";
            default:
                System.out.println("VALOR INVALIDO");
                menuTermo();
        }
        return "";
    }

    private static int menuSelecao() {
        Scanner scan = new Scanner(System.in);
        System.out.println("SELELÇÃO DA ESTRUTURA: \n[1] - ARVORE AVL\n[2] - TABELA HASH\n[3] - LISTA");
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
                Collections.addAll(registros, valores);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registros;
    }

    private static void print(List<String> lista, int tamanho) {

        Dictionary<String, Integer> dicionario = organiza(lista);
        System.out.println(dicionario);
    }

    private static Dictionary<String, Integer> organiza(List<String> lista) {
        Dictionary<String, Integer> dicionario = new Hashtable<>();
        for(String palavra : lista) {
            if (dicionario.get(palavra) != null) {
                Integer frequencia = 1 + dicionario.get(palavra);
                dicionario.put(palavra, frequencia);

            } else {
                dicionario.put(palavra, 1);
            }
        }
        return dicionario;
    }
}
