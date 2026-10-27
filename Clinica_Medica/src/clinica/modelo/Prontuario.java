package clinica.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

                                                                             
public class Prontuario implements Serializable {
    private final int codigo;
    private final Paciente paciente;
    private final LocalDate dataAbertura;
    private final List<Consulta> consultas = new ArrayList<>();

    public Prontuario(int codigo, Paciente paciente) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.dataAbertura = LocalDate.now();
    }

    public void adicionarConsulta(Consulta c) { consultas.add(c); }

    public int getCodigo() { return codigo; }
    public Paciente getPaciente() { return paciente; }
    public LocalDate getDataAbertura() { return dataAbertura; }
    public List<Consulta> getConsultas() { return consultas; }
}
