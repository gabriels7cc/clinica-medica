package clinica.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

                                                                      
public class Medico extends Profissional {
    private final String crm;
    private final String ufCrm;
    private final List<Especialidade> especialidades = new ArrayList<>();       

    public Medico(int codigo, String nome, String cpf, String telefone, String email,
                  LocalDate dataNascimento, String crm, String ufCrm) {
        super(codigo, nome, cpf, telefone, email, dataNascimento, "CRM-" + ufCrm + " " + crm, "Médico");
        this.crm = crm;
        this.ufCrm = ufCrm;
    }

    public void adicionarEspecialidade(Especialidade e) { if (!atende(e)) especialidades.add(e); }

    public boolean atende(Especialidade e) {
        for (Especialidade x : especialidades) if (x.getCodigo() == e.getCodigo()) return true;
        return false;
    }

    public String getCrm() { return crm; }
    public String getUfCrm() { return ufCrm; }
    public List<Especialidade> getEspecialidades() { return especialidades; }
}
