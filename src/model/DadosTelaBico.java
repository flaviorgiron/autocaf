package model;

public class DadosTelaBico {

    private Veiculo veiculo;
    private Bico bico;
    private Operador operador;
    private Long hodometro = 0L;
    private Motorista motorista;

    public DadosTelaBico(Veiculo veiculo, Bico bico, Operador operador, Long hodometro, Motorista motorista) {
        this.veiculo = veiculo;
        this.bico = bico;
        this.operador = operador;
        this.hodometro = hodometro;
        this.motorista = motorista;
    }

    public DadosTelaBico() {
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Bico getBico() {
        return bico;
    }

    public void setBico(Bico bico) {
        this.bico = bico;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public Long getHodometro() {
        return hodometro;
    }

    public void setHodometro(Long hodometro) {
        this.hodometro = hodometro;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }
}
