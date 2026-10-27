package clinica.modelo;

                                                            
public class Convenio implements Identificavel {
    private final int codigo;
    private final String nome;
    private final String cnpj;
    private final String telefone;

    public Convenio(int codigo, String nome, String cnpj, String telefone) {
        this.codigo = codigo; this.nome = nome; this.cnpj = cnpj; this.telefone = telefone;
    }

    @Override public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getCnpj() { return cnpj; }
    public String getTelefone() { return telefone; }
    @Override public String toString() { return "[" + codigo + "] " + nome + " | CNPJ: " + cnpj + " | Tel.: " + telefone; }
}
