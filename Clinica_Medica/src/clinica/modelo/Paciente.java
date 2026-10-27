package clinica.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

                                                     
public class Paciente extends Pessoa {
    private final Sexo sexo;
    private String endereco;
    private final Prontuario prontuario;                             
    private final List<PacienteConvenio> convenios = new ArrayList<>();                     
    private final List<Alergia> alergias = new ArrayList<>();        

    public Paciente(int codigo, String nome, String cpf, LocalDate dataNascimento, Sexo sexo,
                    String telefone, String email, String endereco) {
        super(codigo, nome, cpf, telefone, email, dataNascimento);
        this.sexo = sexo;
        this.endereco = endereco;
        this.prontuario = new Prontuario(codigo, this);                                      
    }

    public void adicionarConvenio(PacienteConvenio pc) { convenios.add(pc); }
    public void adicionarAlergia(Alergia a) { if (!alergias.contains(a)) alergias.add(a); }

                                                                              
    public PacienteConvenio buscarConvenio(Convenio convenio) {
        for (PacienteConvenio pc : convenios) {
            if (pc.getConvenio().getCodigo() == convenio.getCodigo()) return pc;
        }
        return null;
    }

    public Sexo getSexo() { return sexo; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public Prontuario getProntuario() { return prontuario; }
    public List<PacienteConvenio> getConvenios() { return convenios; }
    public List<Alergia> getAlergias() { return alergias; }
}
