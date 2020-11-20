import java.util.ArrayList;
import java.util.List;

public class TesteAVL {
    List<String> registros;
    ArvoreAVL arvoreAVL;
    Integer contador;

    TesteAVL(List registros)
    {
        this.registros = registros;
        this.arvoreAVL = new ArvoreAVL();
    }

    public void run()
    {
        for (String registro : registros) {
            contador += 1;
            arvoreAVL.inserir(arvoreAVL.raiz, registro);
        }
        System.out.println(arvoreAVL);
    }

}
