import java.util.List;

public class TesteLista {

    List<String> registros;
    Integer contador;

    public TesteLista(List<String> registros)
    {
        this.registros = registros;
    }

    public void run()
    {
        Lista lista = new Lista();

        for (String registro : registros)
        {
            contador += 1;
            lista.add(registro);
        }
    }
}
