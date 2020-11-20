import java.util.List;

public class TesteHash
{
    List<String> registros;
    Integer contador;

    TesteHash(List<String> registros)
    {
        this.registros = registros;
    }

    public void run()
    {
        TabelaHash<String, Integer> tabelaHash = new TabelaHash<>();
        int frequencia;

        for (String registro : registros)
        {
            contador += 1;
            if (tabelaHash.get(registro) == null)
            {
                tabelaHash.add(registro, 1);
            } else if (tabelaHash.get(registro) != null){
                frequencia = 1 + tabelaHash.get(registro);
                tabelaHash.add(registro, frequencia);
            }
        }
        System.out.println();
    }
}
