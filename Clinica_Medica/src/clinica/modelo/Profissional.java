package clinica.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

                                                                                       
public class Profissional extends Pessoa {
    private final String registroProfissional;
    private final String cargo;
    private final List<Unidade> unidades = new ArrayList<>();       

    public Profissional(int codigo, String nome, String cpf, String telefone, String email,
                        LocalDate dataNascimento, String registroProfissional, String cargo) {
        super(codigo, nome, cpf, telefone, email, dataNascimento);
        this.registroProfissional = registroProfissional;
        this.cargo = cargo;
    }

    public void adicionarUnidade(Unidade u) { if (!atendeEm(u)) unidades.add(u); }

    public boolean atendeEm(Unidade u) {
        for (Unidade x : unidades) if (x.getCodigo() == u.getCodigo()) return true;
        return false;
    }

    public String getRegistroProfissional() { return registroProfissional; }
    public String getCargo() { return cargo; }
    public List<Unidade> getUnidades() { return unidades; }

    @Override
    public String toString() { return super.toString() + " | " + cargo + " | Registro: " + registroProfissional; }
}
