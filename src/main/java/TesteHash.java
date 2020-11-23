import java.util.List;

public class TesteHash
{
    TabelaHash tabelaHash;
    int contador;

    TesteHash() { this.tabelaHash = new TabelaHash(); }

    public void run(List<String> registros)
    {
        int frequencia;

        for (String registro : registros)
        {
            contador += 1;
            if (tabelaHash.get(registro) == 0)
            {
                tabelaHash.add(registro, 1);
            } else if (tabelaHash.get(registro) != 0){
                frequencia = 1 + tabelaHash.get(registro);
                tabelaHash.add(registro, frequencia);
            }
        }
        System.out.println();
    }
}
