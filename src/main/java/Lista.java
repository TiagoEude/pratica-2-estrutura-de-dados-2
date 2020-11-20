import javax.swing.event.DocumentEvent;
import java.util.ArrayList;
import java.util.List;

class NoLista {
    String termo;
    int frequencia;

    NoLista(String termo) {
        this.termo = termo;
        this.frequencia = 1;
    }
}

public class Lista {
    List<NoLista> lista;

    Lista () {
        lista = new ArrayList<>();
    }

    public int add(String termo) {
        for (NoLista no : lista) {
            if (no.termo.compareTo(termo) == 0) {
                no.frequencia += 1;
                return 0;
            }
        }
        NoLista novoNo = new NoLista(termo);
        lista.add(novoNo);
        return 1;
    }

    public int get(String termo)
    {
        for (NoLista no : lista)
        {
            if (no.termo.compareTo(termo) == 0)
            {
                return no.frequencia;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Lista lista = new Lista();
        lista.add("tiago");
        lista.add("bruno");
        lista.add("fernando");
        lista.add("tiago");
        System.out.println(lista.get("tiago"));
    }
}
