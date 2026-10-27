package clinica.modelo;

                                                                          
public enum SituacaoPagamento {
    PENDENTE("Pendente"),
    PAGO("Pago"),
    CANCELADO("Cancelado");

    private final String rotulo;                             

    SituacaoPagamento(String rotulo) { this.rotulo = rotulo; }

    @Override
    public String toString() { return rotulo; }
}
