package clinica.app;

import clinica.modelo.*;
import clinica.servico.BancoDeDados;
import clinica.servico.ClinicaService;
import clinica.servico.RegraDeNegocioException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

                                                                                   
public class Main {
    private static final String ARQUIVO = "clinica.dat";
    private static BancoDeDados bd;
    private static ClinicaService servico;

    public static void main(String[] args) {
        bd = BancoDeDados.carregar(ARQUIVO);
        servico = new ClinicaService(bd);
        if (bd.estaVazio()) {
            DadosIniciais.popular(servico);
            bd.salvar(ARQUIVO);
            System.out.println("Dados de exemplo carregados.");
        }
        menuPrincipal();
        bd.salvar(ARQUIVO);
        System.out.println("Dados salvos. Até logo!");
    }

                                                                                          
    private static void executar(Runnable acao) {
        try {
            acao.run();
            bd.salvar(ARQUIVO);
        } catch (RegraDeNegocioException e) {
            System.out.println("\n[ERRO] " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("\n[ERRO INESPERADO] " + e);
        }
    }

    private static void listar(List<?> lista) {
        if (lista.isEmpty()) System.out.println("  (nenhum registro)");
        for (Object o : lista) System.out.println("  " + o);
    }

                                                                            
                     
                                                                            
    private static void menuPrincipal() {
        int op;
        do {
            Console.titulo("Sistema de Gestão - Clínica Médica");
            System.out.println("1 - Cadastros");
            System.out.println("2 - Agendamentos");
            System.out.println("3 - Atendimento (consultas, exames, prescrições, procedimentos)");
            System.out.println("4 - Financeiro");
            System.out.println("5 - Consultas e relatórios");
            System.out.println("0 - Salvar e sair");
            op = Console.inteiro("Opção");
            switch (op) {
                case 1: menuCadastros(); break;
                case 2: menuAgendamentos(); break;
                case 3: menuAtendimento(); break;
                case 4: menuFinanceiro(); break;
                case 5: menuRelatorios(); break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

                                                                            
                
                                                                            
    private static void menuCadastros() {
        int op;
        do {
            Console.titulo("Cadastros");
            System.out.println(" 1 - Paciente             2 - Listar pacientes");
            System.out.println(" 3 - Médico               4 - Enfermeiro        5 - Outro profissional");
            System.out.println(" 6 - Listar profissionais");
            System.out.println(" 7 - Especialidade        8 - Unidade           9 - Convênio");
            System.out.println("10 - Alergia             11 - Exame            12 - Medicamento");
            System.out.println("13 - Procedimento");
            System.out.println("14 - Vincular (médico/especialidade, profissional/unidade, especialidade/unidade)");
            System.out.println("15 - Vincular convênio ao paciente   16 - Alterar situação do convênio do paciente");
            System.out.println("17 - Vincular alergia ao paciente");
            System.out.println("18 - Listar catálogos (especialidades, unidades, convênios, exames, medicamentos, procedimentos, alergias)");
            System.out.println(" 0 - Voltar");
            op = Console.inteiro("Opção");
            final int escolha = op;
            if (op != 0) executar(() -> acaoCadastro(escolha));
        } while (op != 0);
    }

    private static void acaoCadastro(int op) {
        switch (op) {
            case 1: {
                Paciente p = servico.cadastrarPaciente(Console.texto("Nome completo"), Console.texto("CPF (11 dígitos)"),
                        Console.data("Data de nascimento"), Console.escolher("Sexo:", Sexo.values()),
                        Console.texto("Telefone"), Console.texto("E-mail"), Console.texto("Endereço"));
                System.out.println("Paciente cadastrado: " + p + " (prontuário criado)");
                break;
            }
            case 2: listar(bd.getPacientes()); Console.pausa(); break;
            case 3: {
                Medico m = servico.cadastrarMedico(Console.texto("Nome completo"), Console.texto("CPF (11 dígitos)"),
                        Console.texto("Telefone"), Console.texto("E-mail"), Console.data("Data de nascimento"),
                        Console.texto("Número do CRM"), Console.texto("UF do CRM"));
                System.out.println("Médico cadastrado: " + m);
                break;
            }
            case 4: {
                Enfermeiro e = servico.cadastrarEnfermeiro(Console.texto("Nome completo"), Console.texto("CPF (11 dígitos)"),
                        Console.texto("Telefone"), Console.texto("E-mail"), Console.data("Data de nascimento"),
                        Console.texto("Número do COREN"));
                System.out.println("Enfermeiro cadastrado: " + e);
                break;
            }
            case 5: {
                Profissional p = servico.cadastrarOutroProfissional(Console.texto("Nome completo"), Console.texto("CPF (11 dígitos)"),
                        Console.texto("Telefone"), Console.texto("E-mail"), Console.data("Data de nascimento"),
                        Console.texto("Registro profissional"), Console.texto("Cargo (ex.: Fisioterapeuta)"));
                System.out.println("Profissional cadastrado: " + p);
                break;
            }
            case 6: listar(bd.getProfissionais()); Console.pausa(); break;
            case 7: System.out.println("Cadastrada: " + servico.cadastrarEspecialidade(Console.texto("Nome"), Console.texto("Descrição"))); break;
            case 8: System.out.println("Cadastrada: " + servico.cadastrarUnidade(Console.texto("Nome"), Console.texto("Endereço"),
                    Console.texto("Telefone"), Console.texto("Horário de funcionamento"))); break;
            case 9: System.out.println("Cadastrado: " + servico.cadastrarConvenio(Console.texto("Nome"), Console.texto("CNPJ"),
                    Console.texto("Telefone de contato"))); break;
            case 10: System.out.println("Cadastrada: " + servico.cadastrarAlergia(Console.texto("Nome da alergia"))); break;
            case 11: System.out.println("Cadastrado: " + servico.cadastrarExame(Console.texto("Nome"), Console.texto("Descrição"),
                    Console.texto("Tipo"))); break;
            case 12: System.out.println("Cadastrado: " + servico.cadastrarMedicamento(Console.texto("Nome"),
                    Console.texto("Princípio ativo"), Console.texto("Fabricante"), Console.texto("Apresentação"))); break;
            case 13: System.out.println("Cadastrado: " + servico.cadastrarProcedimento(Console.texto("Nome"),
                    Console.texto("Descrição"), Console.decimal("Valor padrão (R$)"))); break;
            case 14: vincular(); break;
            case 15: {
                listar(bd.getPacientes());
                int pac = Console.inteiro("Código do paciente");
                listar(bd.getConvenios());
                servico.vincularConvenioPaciente(pac, Console.inteiro("Código do convênio"),
                        Console.texto("Número da carteirinha"), Console.data("Data de adesão"));
                System.out.println("Convênio vinculado.");
                break;
            }
            case 16: {
                int pac = Console.inteiro("Código do paciente");
                int conv = Console.inteiro("Código do convênio");
                servico.alterarSituacaoConvenio(pac, conv, Console.escolher("Nova situação:", SituacaoConvenio.values()));
                System.out.println("Situação alterada.");
                break;
            }
            case 17: {
                listar(bd.getAlergias());
                servico.vincularAlergiaPaciente(Console.inteiro("Código do paciente"), Console.inteiro("Código da alergia"));
                System.out.println("Alergia vinculada.");
                break;
            }
            case 18: {
                System.out.println("ESPECIALIDADES"); listar(bd.getEspecialidades());
                System.out.println("UNIDADES"); listar(bd.getUnidades());
                System.out.println("CONVÊNIOS"); listar(bd.getConvenios());
                System.out.println("EXAMES"); listar(bd.getExames());
                System.out.println("MEDICAMENTOS"); listar(bd.getMedicamentos());
                System.out.println("PROCEDIMENTOS"); listar(bd.getProcedimentos());
                System.out.println("ALERGIAS"); listar(bd.getAlergias());
                Console.pausa();
                break;
            }
            default: System.out.println("Opção inválida.");
        }
    }

    private static void vincular() {
        System.out.println("1 - Médico <-> Especialidade\n2 - Profissional <-> Unidade\n3 - Especialidade <-> Unidade");
        int tipo = Console.inteiro("Opção");
        switch (tipo) {
            case 1:
                listar(servico.listarMedicos()); int m = Console.inteiro("Código do médico");
                listar(bd.getEspecialidades());
                servico.vincularMedicoEspecialidade(m, Console.inteiro("Código da especialidade"));
                break;
            case 2:
                listar(bd.getProfissionais()); int p = Console.inteiro("Código do profissional");
                listar(bd.getUnidades());
                servico.vincularProfissionalUnidade(p, Console.inteiro("Código da unidade"));
                break;
            case 3:
                listar(bd.getEspecialidades()); int e = Console.inteiro("Código da especialidade");
                listar(bd.getUnidades());
                servico.vincularEspecialidadeUnidade(e, Console.inteiro("Código da unidade"));
                break;
            default: System.out.println("Opção inválida."); return;
        }
        System.out.println("Vínculo realizado.");
    }

                                                                            
                   
                                                                            
    private static void menuAgendamentos() {
        int op;
        do {
            Console.titulo("Agendamentos");
            System.out.println("1 - Novo agendamento");
            System.out.println("2 - Confirmar agendamento");
            System.out.println("3 - Cancelar agendamento");
            System.out.println("4 - Marcar não comparecimento");
            System.out.println("5 - Listar agendamentos");
            System.out.println("0 - Voltar");
            op = Console.inteiro("Opção");
            final int escolha = op;
            if (op != 0) executar(() -> acaoAgendamento(escolha));
        } while (op != 0);
    }

    private static void acaoAgendamento(int op) {
        switch (op) {
            case 1: {
                listar(bd.getPacientes());
                int pac = Console.inteiro("Código do paciente");
                listar(servico.listarMedicos());
                int med = Console.inteiro("Código do médico");
                listar(bd.getEspecialidades());
                int esp = Console.inteiro("Código da especialidade");
                listar(bd.getUnidades());
                int uni = Console.inteiro("Código da unidade");
                Agendamento a = servico.agendar(pac, med, esp, uni, Console.data("Data"), Console.hora("Horário"),
                        Console.texto("Observações"));
                System.out.println("Agendado: " + a);
                break;
            }
            case 2: servico.confirmarAgendamento(Console.inteiro("Código do agendamento")); System.out.println("Confirmado."); break;
            case 3: servico.cancelarAgendamento(Console.inteiro("Código do agendamento")); System.out.println("Cancelado."); break;
            case 4: servico.marcarNaoCompareceu(Console.inteiro("Código do agendamento")); System.out.println("Registrado."); break;
            case 5: listar(bd.getAgendamentos()); Console.pausa(); break;
            default: System.out.println("Opção inválida.");
        }
    }

                                                                            
                  
                                                                            
    private static void menuAtendimento() {
        int op;
        do {
            Console.titulo("Atendimento");
            System.out.println("1 - Registrar consulta (a partir de um agendamento)");
            System.out.println("2 - Solicitar exame");
            System.out.println("3 - Agendar exame solicitado");
            System.out.println("4 - Registrar resultado de exame (realizado)");
            System.out.println("5 - Cancelar exame");
            System.out.println("6 - Prescrever medicamentos");
            System.out.println("7 - Registrar procedimento realizado");
            System.out.println("8 - Ver detalhes de uma consulta");
            System.out.println("0 - Voltar");
            op = Console.inteiro("Opção");
            final int escolha = op;
            if (op != 0) executar(() -> acaoAtendimento(escolha));
        } while (op != 0);
    }

    private static void acaoAtendimento(int op) {
        switch (op) {
            case 1: {
                List<Agendamento> abertos = new ArrayList<>();
                for (Agendamento a : bd.getAgendamentos())
                    if (a.getSituacao() == SituacaoAgendamento.AGENDADO || a.getSituacao() == SituacaoAgendamento.CONFIRMADO)
                        abertos.add(a);
                System.out.println("Agendamentos aguardando atendimento:");
                listar(abertos);
                Consulta c = servico.registrarConsulta(Console.inteiro("Código do agendamento"),
                        Console.texto("Queixa principal"), Console.texto("Observações médicas"),
                        Console.texto("Diagnóstico"), Console.texto("Conduta adotada"));
                System.out.println("Consulta registrada:\n  " + c);
                break;
            }
            case 2: {
                listar(bd.getExames());
                int cons = Console.inteiro("Código da consulta");
                int ex = Console.inteiro("Código do exame");
                System.out.println("Solicitado: " + servico.solicitarExame(cons, ex));
                break;
            }
            case 3: listar(servico.examesPendentes()); servico.agendarExame(Console.inteiro("Código da solicitação")); System.out.println("Exame agendado."); break;
            case 4: {
                listar(servico.examesPendentes());
                int cod = Console.inteiro("Código da solicitação");
                servico.realizarExame(cod, Console.texto("Resultado"));
                System.out.println("Resultado registrado.");
                break;
            }
            case 5: listar(servico.examesPendentes()); servico.cancelarExame(Console.inteiro("Código da solicitação")); System.out.println("Exame cancelado."); break;
            case 6: prescrever(); break;
            case 7: {
                int cons = Console.inteiro("Código da consulta");
                listar(bd.getProcedimentos());
                int proc = Console.inteiro("Código do procedimento");
                listar(bd.getProfissionais());
                int prof = Console.inteiro("Código do profissional responsável");
                double valor = -1;
                if (Console.confirmar("Cobrar valor diferente do padrão?")) valor = Console.decimal("Valor efetivamente cobrado (R$)");
                System.out.println("Registrado: " + servico.registrarProcedimento(cons, proc, prof, valor));
                break;
            }
            case 8: detalharConsulta(servico.buscarConsulta(Console.inteiro("Código da consulta"))); Console.pausa(); break;
            default: System.out.println("Opção inválida.");
        }
    }

    private static void prescrever() {
        int cons = Console.inteiro("Código da consulta");
        Consulta c = servico.buscarConsulta(cons);
        listar(bd.getMedicamentos());
        List<ItemPrescricao> itens = new ArrayList<>();
        do {
            Medicamento m = servico.buscarMedicamento(Console.inteiro("Código do medicamento"));
            if (servico.pacienteAlergicoA(c.getPaciente(), m)) {
                System.out.println("  *** ATENÇÃO: o paciente possui alergia relacionada a " + m.getNome() + "! ***");
                if (!Console.confirmar("Deseja prescrever mesmo assim?")) continue;
            }
            itens.add(new ItemPrescricao(m, Console.inteiro("Quantidade"), Console.texto("Dose"),
                    Console.texto("Frequência"), Console.texto("Duração do tratamento"), Console.texto("Observações")));
        } while (Console.confirmar("Adicionar outro medicamento?"));
        servico.prescrever(cons, itens);
        System.out.println("Prescrição registrada com " + itens.size() + " medicamento(s).");
    }

    private static void detalharConsulta(Consulta c) {
        System.out.println(c);
        System.out.println("  Paciente: " + c.getPaciente().getNome());
        System.out.println("  Exames:"); listar(c.getExames());
        System.out.println("  Medicamentos prescritos:"); listar(servico.medicamentosDaConsulta(c.getCodigo()));
        System.out.println("  Procedimentos:"); listar(c.getProcedimentos());
        System.out.println("  Pagamentos:"); listar(c.getPagamentos());
    }

                                                                            
                 
                                                                            
    private static void menuFinanceiro() {
        int op;
        do {
            Console.titulo("Financeiro");
            System.out.println("1 - Gerar cobrança de uma consulta");
            System.out.println("2 - Registrar pagamento (baixar cobrança pendente)");
            System.out.println("3 - Cancelar cobrança pendente");
            System.out.println("4 - Listar pagamentos de uma consulta");
            System.out.println("0 - Voltar");
            op = Console.inteiro("Opção");
            final int escolha = op;
            if (op != 0) executar(() -> acaoFinanceiro(escolha));
        } while (op != 0);
    }

    private static void acaoFinanceiro(int op) {
        switch (op) {
            case 1: {
                Consulta c = servico.buscarConsulta(Console.inteiro("Código da consulta"));
                System.out.printf("Total de procedimentos desta consulta: R$ %.2f%n", c.totalProcedimentos());
                double valor = Console.decimal("Valor total da cobrança (R$)");
                FormaPagamento forma = Console.escolher("Forma de pagamento:", FormaPagamento.values());
                int codConv = 0;
                double coberto = 0;
                if (forma == FormaPagamento.CONVENIO) {
                    System.out.println("Convênios do paciente:"); listar(c.getPaciente().getConvenios());
                    codConv = Console.inteiro("Código do convênio utilizado");
                    coberto = Console.decimal("Valor coberto pelo convênio (R$)");
                }
                System.out.println("Cobrança gerada: " + servico.gerarCobranca(c.getCodigo(), valor, forma, codConv, coberto));
                break;
            }
            case 2: servico.registrarPagamento(Console.inteiro("Código do pagamento")); System.out.println("Pagamento registrado."); break;
            case 3: servico.cancelarPagamento(Console.inteiro("Código do pagamento")); System.out.println("Cobrança cancelada."); break;
            case 4: listar(servico.buscarConsulta(Console.inteiro("Código da consulta")).getPagamentos()); Console.pausa(); break;
            default: System.out.println("Opção inválida.");
        }
    }

                                                                            
                             
                                                                            
    private static void menuRelatorios() {
        int op;
        do {
            Console.titulo("Consultas e relatórios");
            System.out.println(" 1 - Histórico de consultas de um paciente");
            System.out.println(" 2 - Pacientes que não compareceram");
            System.out.println(" 3 - Exames solicitados de um paciente");
            System.out.println(" 4 - Exames pendentes de um paciente");
            System.out.println(" 5 - Exames pendentes (todos os pacientes)");
            System.out.println(" 6 - Medicamentos prescritos em uma consulta");
            System.out.println(" 7 - Faturamento por período");
            System.out.println(" 8 - Faturamento por médico / especialidade / unidade");
            System.out.println(" 9 - Pacientes com pagamentos pendentes");
            System.out.println("10 - Quantidade de consultas por especialidade");
            System.out.println("11 - Médicos que atendem em uma unidade");
            System.out.println("12 - Procedimentos realizados em um período");
            System.out.println("13 - Buscar paciente por CPF");
            System.out.println(" 0 - Voltar");
            op = Console.inteiro("Opção");
            final int escolha = op;
            if (op != 0) executar(() -> acaoRelatorio(escolha));
        } while (op != 0);
    }

    private static void acaoRelatorio(int op) {
        switch (op) {
            case 1: {
                int cod = Console.inteiro("Código do paciente");
                System.out.println("Histórico de " + servico.buscarPaciente(cod).getNome() + ":");
                listar(servico.historicoDoPaciente(cod));
                break;
            }
            case 2: listar(servico.faltas()); break;
            case 3: listar(servico.examesDoPaciente(Console.inteiro("Código do paciente"))); break;
            case 4: listar(servico.examesPendentesDoPaciente(Console.inteiro("Código do paciente"))); break;
            case 5: listar(servico.examesPendentes()); break;
            case 6: listar(servico.medicamentosDaConsulta(Console.inteiro("Código da consulta"))); break;
            case 7: {
                LocalDate ini = Console.data("Data inicial");
                LocalDate fim = Console.data("Data final");
                System.out.printf("Faturamento de %s a %s: R$ %.2f%n", ini.format(Console.FORMATO_DATA),
                        fim.format(Console.FORMATO_DATA), servico.faturamentoPorPeriodo(ini, fim));
                break;
            }
            case 8: {
                String criterio = Console.escolher("Agrupar por:", Agrupamento.values()).name();
                LocalDate ini = Console.data("Data inicial");
                LocalDate fim = Console.data("Data final");
                Map<String, Double> mapa = servico.faturamentoAgrupado(ini, fim, criterio);
                if (mapa.isEmpty()) System.out.println("  (nenhum faturamento no período)");
                for (Map.Entry<String, Double> e : mapa.entrySet())
                    System.out.printf("  %-30s R$ %.2f%n", e.getKey(), e.getValue());
                break;
            }
            case 9: {
                Map<Paciente, List<Pagamento>> mapa = servico.pacientesComPagamentoPendente();
                if (mapa.isEmpty()) System.out.println("  (nenhum paciente com pendência)");
                for (Map.Entry<Paciente, List<Pagamento>> e : mapa.entrySet()) {
                    double total = 0;
                    for (Pagamento p : e.getValue()) total += p.getValor();
                    System.out.printf("  %s - total pendente: R$ %.2f%n", e.getKey().getNome(), total);
                    for (Pagamento p : e.getValue()) System.out.println("      " + p);
                }
                break;
            }
            case 10: {
                Map<String, Integer> mapa = servico.consultasPorEspecialidade();
                if (mapa.isEmpty()) System.out.println("  (nenhuma consulta realizada)");
                for (Map.Entry<String, Integer> e : mapa.entrySet())
                    System.out.printf("  %-20s %d consulta(s)%n", e.getKey(), e.getValue());
                break;
            }
            case 11: listar(bd.getUnidades()); listar(servico.medicosDaUnidade(Console.inteiro("Código da unidade"))); break;
            case 12: listar(servico.procedimentosNoPeriodo(Console.data("Data inicial"), Console.data("Data final"))); break;
            case 13: System.out.println(servico.buscarPacientePorCpf(Console.texto("CPF"))); break;
            default: System.out.println("Opção inválida.");
        }
        Console.pausa();
    }

                                                                            
    private enum Agrupamento {
        MEDICO("Por médico"), ESPECIALIDADE("Por especialidade"), UNIDADE("Por unidade");
        private final String rotulo;
        Agrupamento(String rotulo) { this.rotulo = rotulo; }
        @Override public String toString() { return rotulo; }
    }
}
