package model;

import java.sql.Time;
import java.sql.Timestamp;

public class Abastecimento {

    private Integer idAbastecimento;
    private Timestamp dataHora;
    private Double totalPagar = 0.0;
    private Double precoUnitario = 0.0;
    private Double volume = 0.0;
    private Time tempoAbastecimento;
    private Integer numeroAbastecimento = 0;
    private Double encerranteLitros = 0.0;
    private Double encerranteAnterior = 0.0;
    //private Double encerranteValor = 0.0;
    //private String codCartao01;
    //private String codCartao02;
    private Double tempoMedio = 0.0;
    private Double kmMedio = 0.0;
    private Operador operador;
    private Veiculo veiculo;
    private Bico bico;
    private Long hodometroAnterior = 0L;
    private Long horimetroAnterior = 0L;

    private Long hodometro = 0L;
    private Long horimetro = 0L;

    private String ident1;
    private String ident2;

    private Motorista motorista;

    private String stringCBC;
    private String tipoAbastec;

    public Abastecimento() {
    }

    public Integer getIdAbastecimento() {
        return idAbastecimento;
    }

    public void setIdAbastecimento(Integer idAbastecimento) {
        this.idAbastecimento = idAbastecimento;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Integer getNumeroAbastecimento() {
        return numeroAbastecimento;
    }

    public void setNumeroAbastecimento(Integer numeroAbastecimento) {
        this.numeroAbastecimento = numeroAbastecimento;
    }

    public Double getEncerranteLitros() {
        return encerranteLitros;
    }

    public void setEncerranteLitros(Double encerranteLitros) {
        this.encerranteLitros = encerranteLitros;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public Time getTempoAbastecimento() {
        return tempoAbastecimento;
    }

    public void setTempoAbastecimento(Time tempoAbastecimento) {
        this.tempoAbastecimento = tempoAbastecimento;
    }

    public Double getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(Double tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public Double getKmMedio() {
        return kmMedio;
    }

    public void setKmMedio(Double kmMedio) {
        this.kmMedio = kmMedio;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
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

    public Long getHodometroAnterior() {
        return hodometroAnterior;
    }

    public void setHodometroAnterior(Long hodometroAnterior) {
        this.hodometroAnterior = hodometroAnterior;
    }

    public Long getHorimetroAnterior() {
        return horimetroAnterior;
    }

    public void setHorimetroAnterior(Long horimetroAnterior) {
        this.horimetroAnterior = horimetroAnterior;
    }

    public Long getHodometro() {
        return hodometro;
    }

    public void setHodometro(Long hodometro) {
        this.hodometro = hodometro;
    }

    public Long getHorimetro() {
        return horimetro;
    }

    public void setHorimetro(Long horimetro) {
        this.horimetro = horimetro;
    }

    public Double getEncerranteAnterior() {
        return encerranteAnterior;
    }

    public void setEncerranteAnterior(Double encerranteAnterior) {
        this.encerranteAnterior = encerranteAnterior;
    }
//
//    public Veiculo getComboio() {
//        return comboio;
//    }
//
//    public void setComboio(Veiculo comboio) {
//        this.comboio = comboio;
//    }

    public String getIdent1() {
        return ident1;
    }

    public void setIdent1(String ident1) {
        this.ident1 = ident1;
    }

    public String getIdent2() {
        return ident2;
    }

    public void setIdent2(String ident2) {
        this.ident2 = ident2;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public String getStringCBC() {
        return stringCBC;
    }

    public void setStringCBC(String stringCBC) {
        this.stringCBC = stringCBC;
    }

    public String getTipoAbastec() {
        return tipoAbastec;
    }

    public void setTipoAbastec(String tipoAbastec) {
        this.tipoAbastec = tipoAbastec;
    }
}
