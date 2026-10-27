package clinica.modelo;

import java.time.LocalDate;

                                                  
public class Pagamento implements Identificavel {
    private final int codigo;
    private final Consulta consulta;
    private LocalDate data;
    private final double valor;                                    
    private final FormaPagamento forma;
    private SituacaoPagamento situacao;
    private final Convenio convenio;                                               
    private final double valorCoberto;                              

    public Pagamento(int codigo, Consulta consulta, LocalDate data, double valor, FormaPagamento forma,
                     Convenio convenio, double valorCoberto) {
        this.codigo = codigo; this.consulta = consulta; this.data = data; this.valor = valor;
        this.forma = forma; this.convenio = convenio; this.valorCoberto = valorCoberto;
        this.situacao = SituacaoPagamento.PENDENTE;                                
    }

                                                           
    public double getValorPaciente() { return valor - valorCoberto; }

    @Override public int getCodigo() { return codigo; }
    public Consulta getConsulta() { return consulta; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public double getValor() { return valor; }
    public FormaPagamento getForma() { return forma; }
    public SituacaoPagamento getSituacao() { return situacao; }
    public void setSituacao(SituacaoPagamento situacao) { this.situacao = situacao; }
    public Convenio getConvenio() { return convenio; }
    public double getValorCoberto() { return valorCoberto; }

    @Override
    public String toString() {
        String s = "[" + codigo + "] " + data + " | Consulta " + consulta.getCodigo() + " | Paciente: "
                + consulta.getPaciente().getNome() + " | Total: R$ " + String.format("%.2f", valor)
                + " | " + forma + " | " + situacao;
        if (convenio != null) {
            s += " | Convênio: " + convenio.getNome() + " (cobre R$ " + String.format("%.2f", valorCoberto)
                    + ", paciente R$ " + String.format("%.2f", getValorPaciente()) + ")";
        }
        return s;
    }
}
