package clinica.modelo;

                                                             
public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTRO("Outro");

    private final String rotulo;                             

    Sexo(String rotulo) { this.rotulo = rotulo; }

    @Override
    public String toString() { return rotulo; }
}
