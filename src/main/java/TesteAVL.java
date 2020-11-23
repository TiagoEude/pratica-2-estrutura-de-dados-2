import java.util.List;

public class TesteAVL {
    ArvoreAVL arvoreAVL;
    int contador;

    TesteAVL() { this.arvoreAVL = new ArvoreAVL(); }

    public void run(List<String> registros)
    {
        for (String registro : registros) {
            this.arvoreAVL.inserir(this.arvoreAVL.raiz, registro);
            this.contador += 1;
        }
        this.arvoreAVL.preOrder(this.arvoreAVL.raiz);
    }
}
