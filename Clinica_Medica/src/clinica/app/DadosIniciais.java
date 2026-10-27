package clinica.app;

import clinica.modelo.*;
import clinica.servico.ClinicaService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

                                                                                    
public class DadosIniciais {

    public static void popular(ClinicaService s) {
                              
        Especialidade cardio = s.cadastrarEspecialidade("Cardiologia", "Doenças do coração");
        Especialidade derma = s.cadastrarEspecialidade("Dermatologia", "Doenças da pele");
        Especialidade orto = s.cadastrarEspecialidade("Ortopedia", "Ossos e articulações");
        Especialidade pedi = s.cadastrarEspecialidade("Pediatria", "Saúde de crianças e adolescentes");
        Especialidade neuro = s.cadastrarEspecialidade("Neurologia", "Sistema nervoso");
        Especialidade geral = s.cadastrarEspecialidade("Clínica Geral", "Atendimento clínico geral");

                        
        Unidade centro = s.cadastrarUnidade("Unidade Centro", "Rua das Flores, 100 - São Paulo/SP", "(11) 3000-1000", "Seg a Sex 07:00-19:00");
        Unidade guarulhos = s.cadastrarUnidade("Unidade Guarulhos", "Av. Tiradentes, 500 - Guarulhos/SP", "(11) 2400-2000", "Seg a Sáb 08:00-18:00");
        for (Especialidade e : new Especialidade[]{cardio, derma, geral, pedi}) s.vincularEspecialidadeUnidade(e.getCodigo(), centro.getCodigo());
        for (Especialidade e : new Especialidade[]{orto, neuro, geral, pedi}) s.vincularEspecialidadeUnidade(e.getCodigo(), guarulhos.getCodigo());

                       
        Medico m1 = s.cadastrarMedico("Carlos Eduardo Menezes", "11111111111", "(11) 90000-0001", "carlos@clinica.com", LocalDate.of(1978, 3, 12), "123456", "SP");
        Medico m2 = s.cadastrarMedico("Ana Paula Ribeiro", "22222222222", "(11) 90000-0002", "ana@clinica.com", LocalDate.of(1985, 7, 25), "234567", "SP");
        Medico m3 = s.cadastrarMedico("Roberto Almeida", "33333333333", "(11) 90000-0003", "roberto@clinica.com", LocalDate.of(1972, 11, 2), "345678", "SP");
        Medico m4 = s.cadastrarMedico("Juliana Torres", "44444444444", "(11) 90000-0004", "juliana@clinica.com", LocalDate.of(1990, 1, 30), "456789", "SP");
        s.vincularMedicoEspecialidade(m1.getCodigo(), cardio.getCodigo());
        s.vincularMedicoEspecialidade(m1.getCodigo(), geral.getCodigo());
        s.vincularMedicoEspecialidade(m2.getCodigo(), derma.getCodigo());
        s.vincularMedicoEspecialidade(m3.getCodigo(), orto.getCodigo());
        s.vincularMedicoEspecialidade(m3.getCodigo(), neuro.getCodigo());
        s.vincularMedicoEspecialidade(m4.getCodigo(), pedi.getCodigo());
        s.vincularProfissionalUnidade(m1.getCodigo(), centro.getCodigo());
        s.vincularProfissionalUnidade(m2.getCodigo(), centro.getCodigo());
        s.vincularProfissionalUnidade(m3.getCodigo(), guarulhos.getCodigo());
        s.vincularProfissionalUnidade(m4.getCodigo(), centro.getCodigo());
        s.vincularProfissionalUnidade(m4.getCodigo(), guarulhos.getCodigo());

                          
        Enfermeiro enf = s.cadastrarEnfermeiro("Marta Souza", "55555555555", "(11) 90000-0005", "marta@clinica.com", LocalDate.of(1988, 5, 9), "654321-SP");
        s.vincularProfissionalUnidade(enf.getCodigo(), centro.getCodigo());

                                    
        Convenio unimed = s.cadastrarConvenio("Unimed", "11.111.111/0001-11", "0800 111 1111");
        Convenio amil = s.cadastrarConvenio("Amil", "22.222.222/0001-22", "0800 222 2222");
        Alergia penicilina = s.cadastrarAlergia("Amoxicilina");
        Alergia dipirona = s.cadastrarAlergia("Dipirona");
        s.cadastrarAlergia("Látex");

                         
        Paciente p1 = s.cadastrarPaciente("Maria da Silva", "10000000001", LocalDate.of(1980, 4, 15), Sexo.FEMININO, "(11) 98888-0001", "maria@email.com", "Rua A, 10 - Guarulhos/SP");
        Paciente p2 = s.cadastrarPaciente("João Pereira", "10000000002", LocalDate.of(1992, 9, 3), Sexo.MASCULINO, "(11) 98888-0002", "joao@email.com", "Rua B, 20 - São Paulo/SP");
        Paciente p3 = s.cadastrarPaciente("Lucas Oliveira", "10000000003", LocalDate.of(2018, 2, 20), Sexo.MASCULINO, "(11) 98888-0003", "responsavel.lucas@email.com", "Rua C, 30 - Guarulhos/SP");
        s.vincularConvenioPaciente(p1.getCodigo(), unimed.getCodigo(), "UNI-0001", LocalDate.of(2020, 1, 10));
        s.vincularConvenioPaciente(p3.getCodigo(), amil.getCodigo(), "AMI-9876", LocalDate.of(2022, 6, 1));
        s.vincularAlergiaPaciente(p1.getCodigo(), penicilina.getCodigo());
        s.vincularAlergiaPaciente(p2.getCodigo(), dipirona.getCodigo());

                         
        Exame hemograma = s.cadastrarExame("Hemograma completo", "Análise das células do sangue", "Laboratorial");
        Exame eletro = s.cadastrarExame("Eletrocardiograma", "Atividade elétrica do coração", "Imagem/Gráfico");
        s.cadastrarExame("Raio-X de tórax", "Imagem do tórax", "Imagem");
        Medicamento para = s.cadastrarMedicamento("Paracetamol", "Paracetamol", "EMS", "Comprimido 750mg");
        s.cadastrarMedicamento("Ibuprofeno", "Ibuprofeno", "Medley", "Comprimido 600mg");
        s.cadastrarMedicamento("Novalgina", "Dipirona", "Sanofi", "Comprimido 500mg");                               
        Procedimento curativo = s.cadastrarProcedimento("Curativo", "Curativo simples", 80.0);
        s.cadastrarProcedimento("Pequena cirurgia", "Remoção de lesão cutânea", 450.0);
        s.cadastrarProcedimento("Infiltração articular", "Aplicação intra-articular", 300.0);

                                                                              
        Agendamento a1 = s.agendar(p1.getCodigo(), m1.getCodigo(), cardio.getCodigo(), centro.getCodigo(),
                LocalDate.of(2026, 9, 14), LocalTime.of(9, 0), "Retorno de rotina");
        s.confirmarAgendamento(a1.getCodigo());
        Consulta c1 = s.registrarConsulta(a1.getCodigo(), "Dor no peito", "Paciente ansiosa", "Angina leve", "Repouso e exames");
        s.solicitarExame(c1.getCodigo(), eletro.getCodigo());
        SolicitacaoExame ex2 = s.solicitarExame(c1.getCodigo(), hemograma.getCodigo());
        s.realizarExame(ex2.getCodigo(), "Valores dentro da normalidade");
        List<ItemPrescricao> itens = new ArrayList<>();
        itens.add(new ItemPrescricao(para, 1, "750mg", "8 em 8 horas", "5 dias", "Se houver dor"));
        s.prescrever(c1.getCodigo(), itens);
        Pagamento pg1 = s.gerarCobranca(c1.getCodigo(), 300.0, FormaPagamento.CONVENIO, unimed.getCodigo(), 250.0);
        s.registrarPagamento(pg1.getCodigo());

        Agendamento a2 = s.agendar(p2.getCodigo(), m2.getCodigo(), derma.getCodigo(), centro.getCodigo(),
                LocalDate.of(2026, 9, 20), LocalTime.of(14, 30), "Mancha na pele");
        Consulta c2 = s.registrarConsulta(a2.getCodigo(), "Mancha no braço", "Sem sinais de gravidade", "Dermatite", "Pomada e retorno em 15 dias");
        s.registrarProcedimento(c2.getCodigo(), curativo.getCodigo(), enf.getCodigo(), 60.0);                             
        Pagamento pg2 = s.gerarCobranca(c2.getCodigo(), 260.0, FormaPagamento.PIX, 0, 0);
        s.registrarPagamento(pg2.getCodigo());

        Agendamento a3 = s.agendar(p3.getCodigo(), m4.getCodigo(), pedi.getCodigo(), centro.getCodigo(),
                LocalDate.of(2026, 10, 5), LocalTime.of(10, 0), "Febre há 2 dias");
        Consulta c3 = s.registrarConsulta(a3.getCodigo(), "Febre", "Criança ativa", "Virose", "Hidratação");
        s.gerarCobranca(c3.getCodigo(), 280.0, FormaPagamento.CONVENIO, amil.getCodigo(), 280.0);                 
        s.solicitarExame(c3.getCodigo(), hemograma.getCodigo());

        Agendamento a4 = s.agendar(p2.getCodigo(), m1.getCodigo(), geral.getCodigo(), centro.getCodigo(),
                LocalDate.of(2026, 10, 8), LocalTime.of(16, 0), "Check-up");
        s.marcarNaoCompareceu(a4.getCodigo());

        Agendamento a5 = s.agendar(p1.getCodigo(), m3.getCodigo(), orto.getCodigo(), guarulhos.getCodigo(),
                LocalDate.of(2026, 10, 30), LocalTime.of(11, 0), "Dor no joelho");
        s.cancelarAgendamento(a5.getCodigo());

        s.agendar(p3.getCodigo(), m4.getCodigo(), pedi.getCodigo(), guarulhos.getCodigo(),
                LocalDate.of(2026, 11, 3), LocalTime.of(9, 30), "Retorno");
    }
}
