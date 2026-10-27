package clinica.servico;

import clinica.modelo.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

                                                                                   
                                                               
public record ClinicaService(BancoDeDados bd) {
    private <T extends Identificavel> T buscar(List<T> lista, int codigo, String nome) {
        for (T item : lista) {
            if (item.getCodigo() == codigo) return item;
        }
        throw new RegraDeNegocioException(nome + " não encontrado(a) com o código " + codigo + ".");
    }

    private void exigirTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty())
            throw new RegraDeNegocioException("O campo '" + campo + "' é obrigatório.");
    }

    private String somenteNumeros(String s) { return s.replaceAll("\\D", ""); }

    private void validarCpfUnico(String cpf) {
        if (cpf.length() != 11) throw new RegraDeNegocioException("CPF deve ter 11 dígitos.");
        for (Profissional p : bd.getProfissionais())
            if (p.getCpf().equals(cpf)) throw new RegraDeNegocioException("Já existe um profissional com este CPF.");
        for (Paciente p : bd.getPacientes())
            if (p.getCpf().equals(cpf)) throw new RegraDeNegocioException("Já existe um paciente com este CPF.");
    }

    public Paciente cadastrarPaciente(String nome, String cpf, LocalDate nascimento, Sexo sexo,
                                      String telefone, String email, String endereco) {
        exigirTexto(nome, "nome");
        cpf = somenteNumeros(cpf);
        validarCpfUnico(cpf);                      
        Paciente p = new Paciente(bd.proximoCodigo("paciente"), nome.trim(), cpf, nascimento, sexo,
                telefone, email, endereco);
        bd.getPacientes().add(p);
        return p;
    }

    public Medico cadastrarMedico(String nome, String cpf, String telefone, String email,
                                  LocalDate nascimento, String crm, String uf) {
        exigirTexto(nome, "nome");
        exigirTexto(crm, "CRM");
        cpf = somenteNumeros(cpf);
        validarCpfUnico(cpf);
        Medico m = new Medico(bd.proximoCodigo("profissional"), nome.trim(), cpf, telefone, email,
                nascimento, crm.trim(), uf.trim().toUpperCase());
        bd.getProfissionais().add(m);
        return m;
    }

    public Enfermeiro cadastrarEnfermeiro(String nome, String cpf, String telefone, String email,
                                          LocalDate nascimento, String coren) {
        exigirTexto(nome, "nome");
        exigirTexto(coren, "COREN");
        cpf = somenteNumeros(cpf);
        validarCpfUnico(cpf);
        Enfermeiro e = new Enfermeiro(bd.proximoCodigo("profissional"), nome.trim(), cpf, telefone, email,
                nascimento, coren.trim());
        bd.getProfissionais().add(e);
        return e;
    }

    public Profissional cadastrarOutroProfissional(String nome, String cpf, String telefone, String email,
                                                   LocalDate nascimento, String registro, String cargo) {
        exigirTexto(nome, "nome");
        exigirTexto(cargo, "cargo");
        cpf = somenteNumeros(cpf);
        validarCpfUnico(cpf);
        Profissional p = new Profissional(bd.proximoCodigo("profissional"), nome.trim(), cpf, telefone, email,
                nascimento, registro, cargo.trim());
        bd.getProfissionais().add(p);
        return p;
    }

    public Especialidade cadastrarEspecialidade(String nome, String descricao) {
        exigirTexto(nome, "nome");
        Especialidade e = new Especialidade(bd.proximoCodigo("especialidade"), nome.trim(), descricao);
        bd.getEspecialidades().add(e);
        return e;
    }

    public Unidade cadastrarUnidade(String nome, String endereco, String telefone, String horario) {
        exigirTexto(nome, "nome");
        Unidade u = new Unidade(bd.proximoCodigo("unidade"), nome.trim(), endereco, telefone, horario);
        bd.getUnidades().add(u);
        return u;
    }

    public Convenio cadastrarConvenio(String nome, String cnpj, String telefone) {
        exigirTexto(nome, "nome");
        Convenio c = new Convenio(bd.proximoCodigo("convenio"), nome.trim(), cnpj, telefone);
        bd.getConvenios().add(c);
        return c;
    }

    public Alergia cadastrarAlergia(String nome) {
        exigirTexto(nome, "nome");
        Alergia a = new Alergia(bd.proximoCodigo("alergia"), nome.trim());
        bd.getAlergias().add(a);
        return a;
    }

    public Exame cadastrarExame(String nome, String descricao, String tipo) {
        exigirTexto(nome, "nome");
        Exame e = new Exame(bd.proximoCodigo("exame"), nome.trim(), descricao, tipo);
        bd.getExames().add(e);
        return e;
    }

    public Medicamento cadastrarMedicamento(String nome, String principioAtivo, String fabricante, String apresentacao) {
        exigirTexto(nome, "nome");
        Medicamento m = new Medicamento(bd.proximoCodigo("medicamento"), nome.trim(), principioAtivo,
                fabricante, apresentacao);
        bd.getMedicamentos().add(m);
        return m;
    }

    public Procedimento cadastrarProcedimento(String nome, String descricao, double valorPadrao) {
        exigirTexto(nome, "nome");
        if (valorPadrao < 0) throw new RegraDeNegocioException("O valor padrão não pode ser negativo.");
        Procedimento p = new Procedimento(bd.proximoCodigo("procedimento"), nome.trim(), descricao, valorPadrao);
        bd.getProcedimentos().add(p);
        return p;
    }

    public Paciente buscarPaciente(int cod) { return buscar(bd.getPacientes(), cod, "Paciente"); }

    public Profissional buscarProfissional(int cod) { return buscar(bd.getProfissionais(), cod, "Profissional"); }

    public Especialidade buscarEspecialidade(int cod) { return buscar(bd.getEspecialidades(), cod, "Especialidade"); }

    public Unidade buscarUnidade(int cod) { return buscar(bd.getUnidades(), cod, "Unidade"); }

    public Convenio buscarConvenio(int cod) { return buscar(bd.getConvenios(), cod, "Convênio"); }

    public Medicamento buscarMedicamento(int cod) { return buscar(bd.getMedicamentos(), cod, "Medicamento"); }

    public Consulta buscarConsulta(int cod) { return buscar(bd.getConsultas(), cod, "Consulta"); }

    public Agendamento buscarAgendamento(int cod) { return buscar(bd.getAgendamentos(), cod, "Agendamento"); }

    public Medico buscarMedico(int cod) {
        Profissional p = buscarProfissional(cod);
        if (!(p instanceof Medico)) throw new RegraDeNegocioException("O profissional " + cod + " não é médico.");
        return (Medico) p;
    }

    public Paciente buscarPacientePorCpf(String cpf) {
        cpf = somenteNumeros(cpf);
        for (Paciente p : bd.getPacientes()) if (p.getCpf().equals(cpf)) return p;
        throw new RegraDeNegocioException("Nenhum paciente encontrado com este CPF.");
    }

    public List<Medico> listarMedicos() {
        List<Medico> lista = new ArrayList<>();
        for (Profissional p : bd.getProfissionais()) if (p instanceof Medico medico) lista.add(medico);
        return lista;
    }

    public void vincularMedicoEspecialidade(int codMedico, int codEspecialidade) {
        buscarMedico(codMedico).adicionarEspecialidade(buscarEspecialidade(codEspecialidade));
    }

    public void vincularProfissionalUnidade(int codProfissional, int codUnidade) {
        buscarProfissional(codProfissional).adicionarUnidade(buscarUnidade(codUnidade));
    }

    public void vincularEspecialidadeUnidade(int codEspecialidade, int codUnidade) {
        buscarUnidade(codUnidade).adicionarEspecialidade(buscarEspecialidade(codEspecialidade));
    }

    public void vincularConvenioPaciente(int codPaciente, int codConvenio, String carteirinha, LocalDate adesao) {
        Paciente p = buscarPaciente(codPaciente);
        Convenio c = buscarConvenio(codConvenio);
        exigirTexto(carteirinha, "número da carteirinha");
        if (p.buscarConvenio(c) != null)
            throw new RegraDeNegocioException("O paciente já possui este convênio.");
        p.adicionarConvenio(new PacienteConvenio(c, carteirinha.trim(), adesao));
    }

    public void alterarSituacaoConvenio(int codPaciente, int codConvenio, SituacaoConvenio nova) {
        Paciente p = buscarPaciente(codPaciente);
        PacienteConvenio pc = p.buscarConvenio(buscarConvenio(codConvenio));
        if (pc == null) throw new RegraDeNegocioException("O paciente não possui este convênio.");
        pc.setSituacao(nova);
    }

    public void vincularAlergiaPaciente(int codPaciente, int codAlergia) {
        buscarPaciente(codPaciente).adicionarAlergia(buscar(bd.getAlergias(), codAlergia, "Alergia"));
    }

    public Agendamento agendar(int codPaciente, int codMedico, int codEspecialidade, int codUnidade,
                               LocalDate data, LocalTime hora, String observacoes) {
        Paciente paciente = buscarPaciente(codPaciente);
        Medico medico = buscarMedico(codMedico);
        Especialidade esp = buscarEspecialidade(codEspecialidade);
        Unidade unidade = buscarUnidade(codUnidade);

        if (!medico.atende(esp))
            throw new RegraDeNegocioException("O médico " + medico.getNome() + " não atua em " + esp.getNome() + ".");
        if (!medico.atendeEm(unidade))
            throw new RegraDeNegocioException("O médico não atende na unidade " + unidade.getNome() + ".");
        if (!unidade.oferece(esp))
            throw new RegraDeNegocioException("A unidade " + unidade.getNome() + " não oferece " + esp.getNome() + ".");

                                                                                             
        for (Agendamento a : bd.getAgendamentos()) {
            boolean mesmoHorario = a.getMedico().getCodigo() == medico.getCodigo()
                    && a.getData().equals(data) && a.getHorario().equals(hora);
            boolean ativo = a.getSituacao() != SituacaoAgendamento.CANCELADO
                    && a.getSituacao() != SituacaoAgendamento.NAO_COMPARECEU;
            if (mesmoHorario && ativo)
                throw new RegraDeNegocioException("O médico já possui um agendamento neste dia e horário.");
        }

        Agendamento ag = new Agendamento(bd.proximoCodigo("agendamento"), paciente, medico, esp, unidade,
                data, hora, observacoes);
        bd.getAgendamentos().add(ag);
        return ag;
    }

    public void confirmarAgendamento(int cod) {
        Agendamento a = buscarAgendamento(cod);
        if (a.getSituacao() != SituacaoAgendamento.AGENDADO)
            throw new RegraDeNegocioException("Só é possível confirmar um agendamento com situação 'Agendado'.");
        a.setSituacao(SituacaoAgendamento.CONFIRMADO);
    }

    public void cancelarAgendamento(int cod) {
        Agendamento a = buscarAgendamento(cod);
        if (a.getSituacao() != SituacaoAgendamento.AGENDADO && a.getSituacao() != SituacaoAgendamento.CONFIRMADO)
            throw new RegraDeNegocioException("Este agendamento não pode mais ser cancelado (situação: " + a.getSituacao() + ").");
        a.setSituacao(SituacaoAgendamento.CANCELADO);
    }

    public void marcarNaoCompareceu(int cod) {
        Agendamento a = buscarAgendamento(cod);
        if (a.getSituacao() != SituacaoAgendamento.AGENDADO && a.getSituacao() != SituacaoAgendamento.CONFIRMADO)
            throw new RegraDeNegocioException("Só agendamentos 'Agendado' ou 'Confirmado' podem ser marcados como falta.");
        a.setSituacao(SituacaoAgendamento.NAO_COMPARECEU);
    }

    public Consulta registrarConsulta(int codAgendamento, String queixa, String observacoes,
                                      String diagnostico, String conduta) {
        Agendamento ag = buscarAgendamento(codAgendamento);
                                                                                 
        if (ag.getSituacao() != SituacaoAgendamento.AGENDADO && ag.getSituacao() != SituacaoAgendamento.CONFIRMADO)
            throw new RegraDeNegocioException("Não é possível gerar consulta de um agendamento com situação '"
                    + ag.getSituacao() + "'.");
        exigirTexto(queixa, "queixa principal");

        Consulta c = new Consulta(bd.proximoCodigo("consulta"), ag, queixa, observacoes, diagnostico, conduta);
        ag.setConsulta(c);
        ag.setSituacao(SituacaoAgendamento.REALIZADO);
        ag.getPaciente().getProntuario().adicionarConsulta(c);                                    
        bd.getConsultas().add(c);
        return c;
    }

    public SolicitacaoExame solicitarExame(int codConsulta, int codExame) {
        Consulta c = buscarConsulta(codConsulta);
        Exame e = buscar(bd.getExames(), codExame, "Exame");
        SolicitacaoExame s = new SolicitacaoExame(bd.proximoCodigo("solicitacao"), c, e, LocalDate.now());
        c.getExames().add(s);
        return s;
    }

    private SolicitacaoExame buscarSolicitacao(int cod) {
        for (Consulta c : bd.getConsultas())
            for (SolicitacaoExame s : c.getExames())
                if (s.getCodigo() == cod) return s;
        throw new RegraDeNegocioException("Solicitação de exame não encontrada com o código " + cod + ".");
    }

    public void agendarExame(int codSolicitacao) {
        SolicitacaoExame s = buscarSolicitacao(codSolicitacao);
        if (s.getSituacao() != SituacaoExame.SOLICITADO)
            throw new RegraDeNegocioException("Só é possível agendar um exame 'Solicitado'.");
        s.setSituacao(SituacaoExame.AGENDADO);
    }

    public void realizarExame(int codSolicitacao, String resultado) {
        SolicitacaoExame s = buscarSolicitacao(codSolicitacao);
        if (!s.estaPendente())
            throw new RegraDeNegocioException("Este exame já está " + s.getSituacao() + ".");
        exigirTexto(resultado, "resultado");
        s.setSituacao(SituacaoExame.REALIZADO);
        s.setDataRealizacao(LocalDate.now());
        s.setResultado(resultado);
    }

    public void cancelarExame(int codSolicitacao) {
        SolicitacaoExame s = buscarSolicitacao(codSolicitacao);
        if (!s.estaPendente())
            throw new RegraDeNegocioException("Este exame já está " + s.getSituacao() + ".");
        s.setSituacao(SituacaoExame.CANCELADO);
    }

    public Prescricao prescrever(int codConsulta, List<ItemPrescricao> itens) {
        Consulta c = buscarConsulta(codConsulta);
        if (itens == null || itens.isEmpty())
            throw new RegraDeNegocioException("A prescrição precisa ter pelo menos um medicamento.");
        Prescricao p = new Prescricao(bd.proximoCodigo("prescricao"), c, LocalDate.now());
        for (ItemPrescricao i : itens) p.adicionarItem(i);
        c.getPrescricoes().add(p);
        return p;
    }

    public boolean pacienteAlergicoA(Paciente paciente, Medicamento med) {
        for (Alergia a : paciente.getAlergias()) {
            String alergia = a.getNome().toLowerCase();
            if (alergia.equals(med.getNome().toLowerCase()) || alergia.equals(med.getPrincipioAtivo().toLowerCase()))
                return true;
        }
        return false;
    }

    public ProcedimentoRealizado registrarProcedimento(int codConsulta, int codProcedimento,
                                                       int codProfissional, double valorCobrado) {
        Consulta c = buscarConsulta(codConsulta);
        Procedimento proc = buscar(bd.getProcedimentos(), codProcedimento, "Procedimento");
        Profissional prof = buscarProfissional(codProfissional);
        double valor = valorCobrado < 0 ? proc.getValorPadrao() : valorCobrado;            
        ProcedimentoRealizado pr = new ProcedimentoRealizado(c, proc, c.getDataHora().toLocalDate(), prof, valor);
        c.getProcedimentos().add(pr);
        return pr;
    }

    public Pagamento gerarCobranca(int codConsulta, double valor, FormaPagamento forma,
                                   int codConvenio, double valorCoberto) {
        Consulta c = buscarConsulta(codConsulta);
        if (valor <= 0) throw new RegraDeNegocioException("O valor da cobrança deve ser maior que zero.");

        Convenio convenio = null;
        double coberto = 0;
        if (forma == FormaPagamento.CONVENIO) {
            convenio = buscarConvenio(codConvenio);
            PacienteConvenio pc = c.getPaciente().buscarConvenio(convenio);
            if (pc == null || pc.getSituacao() != SituacaoConvenio.ATIVO)
                throw new RegraDeNegocioException("O paciente não possui este convênio ativo.");
            if (valorCoberto < 0 || valorCoberto > valor)
                throw new RegraDeNegocioException("O valor coberto deve estar entre 0 e o valor total.");
            coberto = valorCoberto;
        }
        Pagamento p = new Pagamento(bd.proximoCodigo("pagamento"), c, LocalDate.now(), valor, forma, convenio, coberto);
        c.getPagamentos().add(p);
        return p;
    }

    private Pagamento buscarPagamento(int cod) {
        for (Consulta c : bd.getConsultas())
            for (Pagamento p : c.getPagamentos())
                if (p.getCodigo() == cod) return p;
        throw new RegraDeNegocioException("Pagamento não encontrado com o código " + cod + ".");
    }

    public void registrarPagamento(int codPagamento) {
        Pagamento p = buscarPagamento(codPagamento);
        if (p.getSituacao() != SituacaoPagamento.PENDENTE)
            throw new RegraDeNegocioException("Este pagamento já está " + p.getSituacao() + ".");
        p.setSituacao(SituacaoPagamento.PAGO);
        p.setData(LocalDate.now());                                             
    }

    public void cancelarPagamento(int codPagamento) {
        Pagamento p = buscarPagamento(codPagamento);
        if (p.getSituacao() != SituacaoPagamento.PENDENTE)
            throw new RegraDeNegocioException("Só é possível cancelar pagamentos pendentes.");
        p.setSituacao(SituacaoPagamento.CANCELADO);
    }

    public List<Consulta> historicoDoPaciente(int codPaciente) {
        List<Consulta> lista = new ArrayList<>(buscarPaciente(codPaciente).getProntuario().getConsultas());
        lista.sort(Comparator.comparing(Consulta::getDataHora));
        return lista;
    }

    public List<Agendamento> faltas() {
        List<Agendamento> lista = new ArrayList<>();
        for (Agendamento a : bd.getAgendamentos())
            if (a.getSituacao() == SituacaoAgendamento.NAO_COMPARECEU) lista.add(a);
        return lista;
    }

    public List<SolicitacaoExame> examesDoPaciente(int codPaciente) {
        List<SolicitacaoExame> lista = new ArrayList<>();
        for (Consulta c : buscarPaciente(codPaciente).getProntuario().getConsultas())
            lista.addAll(c.getExames());
        return lista;
    }

    public List<SolicitacaoExame> examesPendentesDoPaciente(int codPaciente) {
        List<SolicitacaoExame> lista = new ArrayList<>();
        for (SolicitacaoExame s : examesDoPaciente(codPaciente)) if (s.estaPendente()) lista.add(s);
        return lista;
    }

    public List<SolicitacaoExame> examesPendentes() {
        List<SolicitacaoExame> lista = new ArrayList<>();
        for (Consulta c : bd.getConsultas())
            for (SolicitacaoExame s : c.getExames()) if (s.estaPendente()) lista.add(s);
        return lista;
    }

    public List<ItemPrescricao> medicamentosDaConsulta(int codConsulta) {
        List<ItemPrescricao> lista = new ArrayList<>();
        for (Prescricao p : buscarConsulta(codConsulta).getPrescricoes()) lista.addAll(p.getItens());
        return lista;
    }

    private boolean dentroDoPeriodo(LocalDate d, LocalDate ini, LocalDate fim) {
        return !d.isBefore(ini) && !d.isAfter(fim);
    }

    public double faturamentoPorPeriodo(LocalDate ini, LocalDate fim) {
        double total = 0;
        for (Consulta c : bd.getConsultas())
            for (Pagamento p : c.getPagamentos())
                if (p.getSituacao() == SituacaoPagamento.PAGO && dentroDoPeriodo(p.getData(), ini, fim))
                    total += p.getValor();
        return total;
    }

    public Map<String, Double> faturamentoAgrupado(LocalDate ini, LocalDate fim, String criterio) {
    Map<String, Double> mapa = new TreeMap<>();
    for (Consulta c : bd.getConsultas()) {
        for (Pagamento p : c.getPagamentos()) {
            if (p.getSituacao() != SituacaoPagamento.PAGO || !dentroDoPeriodo(p.getData(), ini, fim)) continue;
            String chave = switch (criterio) {
                case "MEDICO" -> c.getMedico().getNome();
                case "ESPECIALIDADE" -> c.getEspecialidade().getNome();
                default -> c.getUnidade().getNome();
            };
            mapa.put(chave, mapa.getOrDefault(chave, 0.0) + p.getValor());
        }
    }
    return mapa;
}

    public Map<Paciente, List<Pagamento>> pacientesComPagamentoPendente() {
        Map<Paciente, List<Pagamento>> mapa = new LinkedHashMap<>();
        for (Consulta c : bd.getConsultas())
            for (Pagamento p : c.getPagamentos())
                if (p.getSituacao() == SituacaoPagamento.PENDENTE) {
                    if (!mapa.containsKey(c.getPaciente())) mapa.put(c.getPaciente(), new ArrayList<>());
                    mapa.get(c.getPaciente()).add(p);
                }
        return mapa;
    }

    public Map<String, Integer> consultasPorEspecialidade() {
        Map<String, Integer> mapa = new TreeMap<>();
        for (Consulta c : bd.getConsultas()) {
            String nome = c.getEspecialidade().getNome();
            mapa.put(nome, mapa.getOrDefault(nome, 0) + 1);
        }
        return mapa;
    }

    public List<Medico> medicosDaUnidade(int codUnidade) {
        Unidade u = buscarUnidade(codUnidade);
        List<Medico> lista = new ArrayList<>();
        for (Medico m : listarMedicos()) if (m.atendeEm(u)) lista.add(m);
        return lista;
    }

    public List<ProcedimentoRealizado> procedimentosNoPeriodo(LocalDate ini, LocalDate fim) {
        List<ProcedimentoRealizado> lista = new ArrayList<>();
        for (Consulta c : bd.getConsultas())
            for (ProcedimentoRealizado pr : c.getProcedimentos())
                if (dentroDoPeriodo(pr.getDataRealizacao(), ini, fim)) lista.add(pr);
        return lista;
    }
}
