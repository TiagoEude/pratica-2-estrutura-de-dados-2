import java.util.List;

public class TesteLista
{
    Lista lista;
    int contador;

    public TesteLista()
    {
        this.lista = new Lista();
    }

    public void run(List<String> registros)
    {
        for (String registro : registros)
        {
            contador += 1;
            lista.add(registro);
        }
    }
}
