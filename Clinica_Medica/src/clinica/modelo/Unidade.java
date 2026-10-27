package clinica.modelo;

import java.util.ArrayList;
import java.util.List;

                                         
public class Unidade implements Identificavel {
    private final int codigo;
    private final String nome;
    private final String endereco;
    private final String telefone;
    private final String horarioFuncionamento;
    private final List<Especialidade> especialidades = new ArrayList<>();       

    public Unidade(int codigo, String nome, String endereco, String telefone, String horarioFuncionamento) {
        this.codigo = codigo; this.nome = nome; this.endereco = endereco;
        this.telefone = telefone; this.horarioFuncionamento = horarioFuncionamento;
    }

    public void adicionarEspecialidade(Especialidade e) { if (!oferece(e)) especialidades.add(e); }

    public boolean oferece(Especialidade e) {
        for (Especialidade x : especialidades) if (x.getCodigo() == e.getCodigo()) return true;
        return false;
    }

    @Override public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getTelefone() { return telefone; }
    public String getHorarioFuncionamento() { return horarioFuncionamento; }
    public List<Especialidade> getEspecialidades() { return especialidades; }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nome + " | " + endereco + " | Tel.: " + telefone + " | Horário: " + horarioFuncionamento;
    }
}
