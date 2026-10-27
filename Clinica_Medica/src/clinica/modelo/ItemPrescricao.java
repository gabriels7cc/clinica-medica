package clinica.modelo;

import java.io.Serializable;

                                                                                          
public class ItemPrescricao implements Serializable {
    private final Medicamento medicamento;
    private final int quantidade;
    private final String dose;
    private final String frequencia;
    private final String duracao;
    private final String observacoes;

    public ItemPrescricao(Medicamento medicamento, int quantidade, String dose,
                          String frequencia, String duracao, String observacoes) {
        this.medicamento = medicamento; this.quantidade = quantidade; this.dose = dose;
        this.frequencia = frequencia; this.duracao = duracao; this.observacoes = observacoes;
    }

    public Medicamento getMedicamento() { return medicamento; }
    public int getQuantidade() { return quantidade; }
    public String getDose() { return dose; }
    public String getFrequencia() { return frequencia; }
    public String getDuracao() { return duracao; }
    public String getObservacoes() { return observacoes; }

    @Override
    public String toString() {
        return medicamento.getNome() + " | Qtd: " + quantidade + " | Dose: " + dose + " | Frequência: " + frequencia
                + " | Duração: " + duracao + (observacoes.isEmpty() ? "" : " | Obs.: " + observacoes);
    }
}
