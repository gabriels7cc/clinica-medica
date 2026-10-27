package clinica.modelo;

import java.time.LocalDate;

                                                      
public class Enfermeiro extends Profissional {
    private final String coren;

    public Enfermeiro(int codigo, String nome, String cpf, String telefone, String email,
                      LocalDate dataNascimento, String coren) {
        super(codigo, nome, cpf, telefone, email, dataNascimento, "COREN " + coren, "Enfermeiro(a)");
        this.coren = coren;
    }

    public String getCoren() { return coren; }
}
