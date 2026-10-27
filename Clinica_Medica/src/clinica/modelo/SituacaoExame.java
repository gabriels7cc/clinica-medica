package clinica.modelo;

                                                                      
public enum SituacaoExame {
    SOLICITADO("Solicitado"),
    AGENDADO("Agendado"),
    REALIZADO("Realizado"),
    CANCELADO("Cancelado");

    private final String rotulo;                             

    SituacaoExame(String rotulo) { this.rotulo = rotulo; }

    @Override
    public String toString() { return rotulo; }
}
