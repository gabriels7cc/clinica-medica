package clinica.servico;

import clinica.modelo.*;

import java.io.*;
import java.util.*;

                                                                    
                                                                    
public class BancoDeDados implements Serializable {
    private final List<Paciente> pacientes = new ArrayList<>();
    private final List<Profissional> profissionais = new ArrayList<>();                                
    private final List<Especialidade> especialidades = new ArrayList<>();
    private final List<Unidade> unidades = new ArrayList<>();
    private final List<Convenio> convenios = new ArrayList<>();
    private final List<Alergia> alergias = new ArrayList<>();
    private final List<Exame> exames = new ArrayList<>();
    private final List<Medicamento> medicamentos = new ArrayList<>();
    private final List<Procedimento> procedimentos = new ArrayList<>();
    private final List<Agendamento> agendamentos = new ArrayList<>();
    private final List<Consulta> consultas = new ArrayList<>();

                                                                                      
    private final Map<String, Integer> contadores = new HashMap<>();

    public int proximoCodigo(String tipo) {
        int proximo = contadores.getOrDefault(tipo, 0) + 1;
        contadores.put(tipo, proximo);
        return proximo;
    }

    public List<Paciente> getPacientes() { return pacientes; }
    public List<Profissional> getProfissionais() { return profissionais; }
    public List<Especialidade> getEspecialidades() { return especialidades; }
    public List<Unidade> getUnidades() { return unidades; }
    public List<Convenio> getConvenios() { return convenios; }
    public List<Alergia> getAlergias() { return alergias; }
    public List<Exame> getExames() { return exames; }
    public List<Medicamento> getMedicamentos() { return medicamentos; }
    public List<Procedimento> getProcedimentos() { return procedimentos; }
    public List<Agendamento> getAgendamentos() { return agendamentos; }
    public List<Consulta> getConsultas() { return consultas; }

    public boolean estaVazio() { return pacientes.isEmpty() && profissionais.isEmpty(); }

                                            
    public void salvar(String arquivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(this);
        } catch (IOException e) {
            throw new RegraDeNegocioException("Erro ao salvar os dados: " + e.getMessage());
        }
    }

                                                                                       
    public static BancoDeDados carregar(String arquivo) {
        File f = new File(arquivo);
        if (!f.exists()) return new BancoDeDados();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            return (BancoDeDados) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Não foi possível ler o arquivo de dados. Iniciando banco vazio.");
            return new BancoDeDados();
        }
    }
}
