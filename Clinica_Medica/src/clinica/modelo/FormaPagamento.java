package clinica.modelo;

                                                                       
public enum FormaPagamento {
    DINHEIRO("Dinheiro"),
    CARTAO_CREDITO("Cartão de crédito"),
    CARTAO_DEBITO("Cartão de débito"),
    PIX("PIX"),
    CONVENIO("Convênio");

    private final String rotulo;                             

    FormaPagamento(String rotulo) { this.rotulo = rotulo; }

    @Override
    public String toString() { return rotulo; }
}
