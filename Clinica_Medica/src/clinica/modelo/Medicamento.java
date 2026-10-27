package clinica.modelo;

                                          
public class Medicamento implements Identificavel {
    private final int codigo;
    private final String nome;
    private final String principioAtivo;
    private final String fabricante;
    private final String apresentacao;

    public Medicamento(int codigo, String nome, String principioAtivo, String fabricante, String apresentacao) {
        this.codigo = codigo; this.nome = nome; this.principioAtivo = principioAtivo;
        this.fabricante = fabricante; this.apresentacao = apresentacao;
    }

    @Override public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getPrincipioAtivo() { return principioAtivo; }
    public String getFabricante() { return fabricante; }
    public String getApresentacao() { return apresentacao; }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nome + " | Princípio ativo: " + principioAtivo + " | " + fabricante + " | " + apresentacao;
    }
}
