package clinica.modelo;

                                                                 
public class Alergia implements Identificavel {
    private final int codigo;
    private final String nome;

    public Alergia(int codigo, String nome) { this.codigo = codigo; this.nome = nome; }

    @Override public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    @Override public String toString() { return "[" + codigo + "] " + nome; }
}
