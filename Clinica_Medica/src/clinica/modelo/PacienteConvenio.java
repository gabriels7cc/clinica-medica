package clinica.modelo;

import java.io.Serializable;
import java.time.LocalDate;

                                                                 
                                                                          
public class PacienteConvenio implements Serializable {
    private final Convenio convenio;
    private final String numeroCarteirinha;
    private final LocalDate dataAdesao;
    private SituacaoConvenio situacao;

    public PacienteConvenio(Convenio convenio, String numeroCarteirinha, LocalDate dataAdesao) {
        this.convenio = convenio;
        this.numeroCarteirinha = numeroCarteirinha;
        this.dataAdesao = dataAdesao;
        this.situacao = SituacaoConvenio.ATIVO;
    }

    public Convenio getConvenio() { return convenio; }
    public String getNumeroCarteirinha() { return numeroCarteirinha; }
    public LocalDate getDataAdesao() { return dataAdesao; }
    public SituacaoConvenio getSituacao() { return situacao; }
    public void setSituacao(SituacaoConvenio situacao) { this.situacao = situacao; }

    @Override
    public String toString() {
        return convenio.getNome() + " | Carteirinha: " + numeroCarteirinha + " | Adesão: " + dataAdesao + " | " + situacao;
    }
}
