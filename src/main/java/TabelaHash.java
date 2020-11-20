import java.util.ArrayList;

class NoHash<C, V> {
    C chave;
    V valor;

    NoHash<C, V> proximo;

    public NoHash(C chave, V valor) {
        this.chave = chave;
        this.valor = valor;
    }
}

public class TabelaHash<C, V> {
    private ArrayList<NoHash<C, V>> tabelaHash;
    private int tamanhoTabela;
    private int tamanhoAtual;

    public TabelaHash() {
        tabelaHash = new ArrayList<>();
        tamanhoTabela = 1000;
        tamanhoAtual = 0;

        for (int i = 0; i < tamanhoTabela; i++) tabelaHash.add(null);
    }

    public int tamanho () { return tamanhoAtual; }

    public int getIndex(C chave) {
        int hashCode = chave.hashCode();
        return (hashCode & 0x7fffffff) % tamanhoTabela;
    }

    public V remove (C chave) {
        int index = getIndex(chave);
        NoHash<C, V> cabeca = tabelaHash.get(index);
        NoHash<C, V> anterior = null;

        while (cabeca != null) {
            if (cabeca.chave.equals(chave)) break;

            anterior = cabeca;
            cabeca = cabeca.proximo;
        }

        if (cabeca == null) return null;

        tamanhoAtual--;

        if (anterior != null ) anterior.proximo = cabeca.proximo;
        else tabelaHash.set(index, cabeca.proximo);

        return cabeca.valor;
    }

    public V get (C chave) {
        int index = getIndex(chave);
        NoHash<C, V> cabeca = tabelaHash.get(index);

        while (cabeca != null) {
            if (cabeca.chave.equals(chave)) return cabeca.valor;
            cabeca = cabeca.proximo;
        }

        return null;
    }

    public void add (C chave, V valor) {
        int index = getIndex(chave);
        NoHash<C, V> cabeca = tabelaHash.get(index);

        while (cabeca != null) {
            if (cabeca.chave.equals(chave)) {
                cabeca.valor =  valor;
                return;
            }
            cabeca = cabeca.proximo;
        }

        tamanhoAtual++;
        cabeca = tabelaHash.get(index);
        NoHash<C, V> novoNo = new NoHash<>(chave, valor);
        novoNo.proximo = cabeca;
        tabelaHash.set(index, novoNo);

        if ((1.0 * tamanhoAtual) / tamanhoTabela >= 0.7) {
            ArrayList<NoHash<C, V>> temp = tabelaHash;
            tabelaHash = new ArrayList<>();
            tamanhoTabela = 2 * tamanhoTabela;
            tamanhoAtual = 0;
            for (int i = 0; i < tamanhoTabela; i++) tabelaHash.add(null);
            for (NoHash<C, V> noCabeca : temp) {
                while (noCabeca != null) {
                    add(noCabeca.chave, noCabeca.valor);
                    noCabeca = noCabeca.proximo;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        TabelaHash<String, Integer> hash = new TabelaHash<>();
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
        Integer num = 1 + hash.get("coder");
        hash.add("coder", num);
        System.out.println(hash.get("coder"));
    }
}
