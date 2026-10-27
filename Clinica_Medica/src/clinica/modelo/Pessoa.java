package clinica.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

                                                                                        
public abstract class Pessoa implements Identificavel {
    private final int codigo;
    private String nome;
    private final String cpf;
    private String telefone;
    private String email;
    private final LocalDate dataNascimento;

    protected Pessoa(int codigo, String nome, String cpf, String telefone, String email, LocalDate dataNascimento) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    @Override public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setNome(String nome) { this.nome = nome; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nome + " | CPF: " + cpf + " | Nasc.: "
                + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
