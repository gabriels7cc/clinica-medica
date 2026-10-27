package clinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

                                                                                         
public class Consulta implements Identificavel {
    private final int codigo;
    private final Agendamento agendamento;
    private final Paciente paciente;
    private final Medico medico;
    private final Especialidade especialidade;
    private final Unidade unidade;
    private final LocalDateTime dataHora;
    private final String queixaPrincipal;
    private final String observacoesMedicas;
    private String diagnostico = null;
    private String conduta = null;

                                                                
    private final List<SolicitacaoExame> exames = new ArrayList<>();
    private final List<Prescricao> prescricoes = new ArrayList<>();
    private final List<ProcedimentoRealizado> procedimentos = new ArrayList<>();
    private final List<Pagamento> pagamentos = new ArrayList<>();

    public Consulta(int codigo, Agendamento agendamento, String queixaPrincipal,
                    String observacoesMedicas, String diagnostico, String conduta) {
        this.codigo = codigo;
        this.agendamento = agendamento;
        this.paciente = agendamento.getPaciente();
        this.medico = agendamento.getMedico();
        this.especialidade = agendamento.getEspecialidade();
        this.unidade = agendamento.getUnidade();
        this.dataHora = LocalDateTime.of(agendamento.getData(), agendamento.getHorario());
        this.queixaPrincipal = queixaPrincipal;
        this.observacoesMedicas = observacoesMedicas;
        this.diagnostico = diagnostico;
        this.conduta = conduta;
    }

    public Consulta(Agendamento agendamento, int codigo, LocalDateTime dataHora, Especialidade especialidade, Medico medico, String observacoesMedicas, Paciente paciente, String queixaPrincipal, Unidade unidade) {
        this.agendamento = agendamento;
        this.codigo = codigo;
        this.dataHora = dataHora;
        this.especialidade = especialidade;
        this.medico = medico;
        this.observacoesMedicas = observacoesMedicas;
        this.paciente = paciente;
        this.queixaPrincipal = queixaPrincipal;
        this.unidade = unidade;
    }

                                                                                           
    public double totalProcedimentos() {
        double total = 0;
        for (ProcedimentoRealizado p : procedimentos) total += p.getValorCobrado();
        return total;
    }

    @Override public int getCodigo() { return codigo; }
    public Agendamento getAgendamento() { return agendamento; }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public Especialidade getEspecialidade() { return especialidade; }
    public Unidade getUnidade() { return unidade; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getQueixaPrincipal() { return queixaPrincipal; }
    public String getObservacoesMedicas() { return observacoesMedicas; }
    public String getDiagnostico() { return diagnostico; }
    public String getConduta() { return conduta; }
    public List<SolicitacaoExame> getExames() { return exames; }
    public List<Prescricao> getPrescricoes() { return prescricoes; }
    public List<ProcedimentoRealizado> getProcedimentos() { return procedimentos; }
    public List<Pagamento> getPagamentos() { return pagamentos; }

    @Override
    public String toString() {
        return "[" + codigo + "] " + dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + " | " + medico.getNome() + " (" + especialidade.getNome() + ") | " + unidade.getNome()
                + "\n      Queixa: " + queixaPrincipal + "\n      Diagnóstico: " + diagnostico
                + "\n      Conduta: " + conduta + "\n      Observações: " + observacoesMedicas;
    }
}
