package clinica.modelo;

import java.io.Serializable;
import java.time.LocalDate;

                                                       
                                                                          
public class ProcedimentoRealizado implements Serializable {
    private final Consulta consulta;
    private final Procedimento procedimento;
    private final LocalDate dataRealizacao;
    private final Profissional profissional;
    private final double valorCobrado;

    public ProcedimentoRealizado(Consulta consulta, Procedimento procedimento, LocalDate dataRealizacao,
                                 Profissional profissional, double valorCobrado) {
        this.consulta = consulta; this.procedimento = procedimento; this.dataRealizacao = dataRealizacao;
        this.profissional = profissional; this.valorCobrado = valorCobrado;
    }

    public Consulta getConsulta() { return consulta; }
    public Procedimento getProcedimento() { return procedimento; }
    public LocalDate getDataRealizacao() { return dataRealizacao; }
    public Profissional getProfissional() { return profissional; }
    public double getValorCobrado() { return valorCobrado; }

    @Override
    public String toString() {
        return dataRealizacao + " | " + procedimento.getNome() + " | Consulta " + consulta.getCodigo()
                + " | Paciente: " + consulta.getPaciente().getNome() + " | Responsável: " + profissional.getNome()
                + " | Cobrado: R$ " + String.format("%.2f", valorCobrado)
                + " (padrão: R$ " + String.format("%.2f", procedimento.getValorPadrao()) + ")";
    }
}
