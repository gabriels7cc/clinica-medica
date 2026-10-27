package clinica.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

                                                                                
public class Prescricao implements Identificavel {
    private final int codigo;
    private final Consulta consulta;
    private final LocalDate data;
    private final List<ItemPrescricao> itens = new ArrayList<>();

    public Prescricao(int codigo, Consulta consulta, LocalDate data) {
        this.codigo = codigo; this.consulta = consulta; this.data = data;
    }

    public void adicionarItem(ItemPrescricao item) { itens.add(item); }

    @Override public int getCodigo() { return codigo; }
    public Consulta getConsulta() { return consulta; }
    public LocalDate getData() { return data; }
    public List<ItemPrescricao> getItens() { return itens; }
}
