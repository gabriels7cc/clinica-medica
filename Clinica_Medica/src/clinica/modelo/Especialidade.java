package clinica.modelo;

                                                        
public class Especialidade implements Identificavel {
    private final int codigo;
    private final String nome;
    private final String descricao;

    public Especialidade(int codigo, String nome, String descricao) {
        this.codigo = codigo; this.nome = nome; this.descricao = descricao;
    }

    @Override public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    @Override public String toString() { return "[" + codigo + "] " + nome + " - " + descricao; }
}
