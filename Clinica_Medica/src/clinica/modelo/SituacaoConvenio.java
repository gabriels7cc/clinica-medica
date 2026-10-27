package clinica.modelo;

                                                                         
public enum SituacaoConvenio {
    ATIVO("Ativo"),
    SUSPENSO("Suspenso"),
    CANCELADO("Cancelado");

    private final String rotulo;                             

    SituacaoConvenio(String rotulo) { this.rotulo = rotulo; }

    @Override
    public String toString() { return rotulo; }
}
