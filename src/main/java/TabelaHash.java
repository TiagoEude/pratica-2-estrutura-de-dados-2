import java.util.ArrayList;

class NoHash {
    String chave;
    int ocorrencia;

    NoHash proximo;

    NoHash(String chave, int valor) {
        this.chave = chave;
        this.ocorrencia = valor;
    }
}

public class TabelaHash {
    private ArrayList<NoHash> tabelaHash;
    private int tamanhoTabela;
    private int tamanhoAtual;

    TabelaHash() {
        tabelaHash = new ArrayList<>();
        tamanhoTabela = 10000;
        tamanhoAtual = 0;

        for (int i = 0; i < tamanhoTabela; i++) tabelaHash.add(null);
    }

    public int tamanho() { return tamanhoAtual; }

    public int getIndex(String chave) {
        int hashCode = chave.hashCode();
        return (hashCode & 0x7fffffff) % tamanhoTabela;
    }

    public int remove (String chave) {
        int index = getIndex(chave);
        NoHash cabeca = tabelaHash.get(index);
        NoHash anterior = null;

        while (cabeca != null) {
            if (cabeca.chave.equals(chave)) break;

            anterior = cabeca;
            cabeca = cabeca.proximo;
        }

        if (cabeca == null) return 0;

        tamanhoAtual--;

        if (anterior != null ) anterior.proximo = cabeca.proximo;
        else tabelaHash.set(index, cabeca.proximo);

        return cabeca.ocorrencia;
    }

    public int get (String chave) {
        int index = getIndex(chave);
        NoHash cabeca = tabelaHash.get(index);

        while (cabeca != null) {
            if (cabeca.chave.equals(chave)) return cabeca.ocorrencia;
            cabeca = cabeca.proximo;
        }

        return 0;
    }

    public void add (String chave, int valor) {
        int index = getIndex(chave);
        NoHash cabeca = tabelaHash.get(index);

        while (cabeca != null) {
            if (cabeca.chave.equals(chave)) {
                cabeca.ocorrencia =  valor;
                return;
            }
            cabeca = cabeca.proximo;
        }

        tamanhoAtual++;
        cabeca = tabelaHash.get(index);
        NoHash novoNo = new NoHash(chave, valor);
        novoNo.proximo = cabeca;
        tabelaHash.set(index, novoNo);

        if ((1.0 * tamanhoAtual) / tamanhoTabela >= 0.7) {
            ArrayList<NoHash> temp = tabelaHash;
            tabelaHash = new ArrayList<>();
            tamanhoTabela = 2 * tamanhoTabela;
            tamanhoAtual = 0;
            for (int i = 0; i < tamanhoTabela; i++) tabelaHash.add(null);
            for (NoHash noCabeca : temp) {
                while (noCabeca != null) {
                    add(noCabeca.chave, noCabeca.ocorrencia);
                    noCabeca = noCabeca.proximo;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        TabelaHash hash = new TabelaHash();
        System.out.println(hash.get("coder"));
        hash.add("this",1 );
        hash.add("coder",2 );
        hash.add("this",4 );
        hash.add("hi",5 );
        System.out.println(hash.tamanho());
        System.out.println(hash.remove("this"));
        System.out.println(hash.remove("this"));
        System.out.println(hash.tamanho());
        hash.add("coder", 1000);
        hash.add("coder", 30);
        hash.add("coder", 200);
        hash.add("coder", 70);
        System.out.println(hash.get("coder"));
        int num = 1 + hash.get("coder");
        hash.add("coder", num);
        System.out.println(hash.get("coder"));
        int num1 = 1 + hash.get("coder");
        hash.add("coder", num1);
        System.out.println(hash.get("coder"));
    }
}
