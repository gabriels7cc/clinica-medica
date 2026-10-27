package clinica.modelo;

import java.time.LocalDate;

                                                          
public class SolicitacaoExame implements Identificavel {
    private final int codigo;
    private final Paciente paciente;
    private final Consulta consulta;
    private final Exame exame;
    private final LocalDate dataSolicitacao;
    private SituacaoExame situacao;
    private LocalDate dataRealizacao;
    private String resultado;

    public SolicitacaoExame(int codigo, Consulta consulta, Exame exame, LocalDate dataSolicitacao) {
        this.codigo = codigo;
        this.consulta = consulta;
        this.paciente = consulta.getPaciente();
        this.exame = exame;
        this.dataSolicitacao = dataSolicitacao;
        this.situacao = SituacaoExame.SOLICITADO;
    }

                                                                  
    public boolean estaPendente() {
        return situacao == SituacaoExame.SOLICITADO || situacao == SituacaoExame.AGENDADO;
    }

    @Override public int getCodigo() { return codigo; }
    public Paciente getPaciente() { return paciente; }
    public Consulta getConsulta() { return consulta; }
    public Exame getExame() { return exame; }
    public LocalDate getDataSolicitacao() { return dataSolicitacao; }
    public SituacaoExame getSituacao() { return situacao; }
    public void setSituacao(SituacaoExame situacao) { this.situacao = situacao; }
    public LocalDate getDataRealizacao() { return dataRealizacao; }
    public void setDataRealizacao(LocalDate dataRealizacao) { this.dataRealizacao = dataRealizacao; }
    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    @Override
    public String toString() {
        String s = "[" + codigo + "] " + exame.getNome() + " | Paciente: " + paciente.getNome()
                + " | Consulta: " + consulta.getCodigo() + " | Solicitado em: " + dataSolicitacao + " | " + situacao;
        if (dataRealizacao != null) s += " | Realizado em: " + dataRealizacao;
        if (resultado != null) s += " | Resultado: " + resultado;
        return s;
    }
}
