package clinica.modelo;

                                                                            
public enum SituacaoAgendamento {
    AGENDADO("Agendado"),
    CONFIRMADO("Confirmado"),
    REALIZADO("Realizado"),
    CANCELADO("Cancelado"),
    NAO_COMPARECEU("Não compareceu");

    private final String rotulo;                             

    SituacaoAgendamento(String rotulo) { this.rotulo = rotulo; }

    @Override
    public String toString() { return rotulo; }
}
