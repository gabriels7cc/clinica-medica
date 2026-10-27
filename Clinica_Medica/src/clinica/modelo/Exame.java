package clinica.modelo;

                                                           
public class Exame implements Identificavel {
    private final int codigo;
    private final String nome;
    private final String descricao;
    private final String tipo;

    public Exame(int codigo, String nome, String descricao, String tipo) {
        this.codigo = codigo; this.nome = nome; this.descricao = descricao; this.tipo = tipo;
    }

    @Override public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getTipo() { return tipo; }
    @Override public String toString() { return "[" + codigo + "] " + nome + " (" + tipo + ") - " + descricao; }
}
