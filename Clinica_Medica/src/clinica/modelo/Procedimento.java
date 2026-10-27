package clinica.modelo;

                                                                                                
public class Procedimento implements Identificavel {
    private final int codigo;
    private final String nome;
    private final String descricao;
    private final double valorPadrao;

    public Procedimento(int codigo, String nome, String descricao, double valorPadrao) {
        this.codigo = codigo; this.nome = nome; this.descricao = descricao; this.valorPadrao = valorPadrao;
    }

    @Override public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getValorPadrao() { return valorPadrao; }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nome + " - " + descricao + " | Valor padrão: R$ " + String.format("%.2f", valorPadrao);
    }
}
