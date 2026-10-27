package clinica.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

                                                                 
public class Agendamento implements Identificavel {
    private final int codigo;
    private final Paciente paciente;
    private final Medico medico;
    private final Especialidade especialidade;
    private final Unidade unidade;
    private final LocalDate data;
    private final LocalTime horario;
    private SituacaoAgendamento situacao;
    private String observacoes;
    private Consulta consulta;                                                    

    public Agendamento(int codigo, Paciente paciente, Medico medico, Especialidade especialidade,
                       Unidade unidade, LocalDate data, LocalTime horario, String observacoes) {
        this.codigo = codigo; this.paciente = paciente; this.medico = medico;
        this.especialidade = especialidade; this.unidade = unidade;
        this.data = data; this.horario = horario; this.observacoes = observacoes;
        this.situacao = SituacaoAgendamento.AGENDADO;                                           
    }

    @Override public int getCodigo() { return codigo; }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public Especialidade getEspecialidade() { return especialidade; }
    public Unidade getUnidade() { return unidade; }
    public LocalDate getData() { return data; }
    public LocalTime getHorario() { return horario; }
    public SituacaoAgendamento getSituacao() { return situacao; }
    public void setSituacao(SituacaoAgendamento situacao) { this.situacao = situacao; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }

    @Override
    public String toString() {
        return "[" + codigo + "] " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " + horario
                + " | Paciente: " + paciente.getNome() + " | Médico: " + medico.getNome()
                + " | " + especialidade.getNome() + " | " + unidade.getNome() + " | " + situacao;
    }
}
