package model;

//import java.sql.Time;
//import java.sql.Timestamp;
public class Historico {

    private Integer idHistorico;
    private String mensagem;
    private String identificado;

    public Historico() {
    }

    public Historico(String mensagem, String identificado) {
        this.mensagem = mensagem;
        this.identificado = identificado;
    }

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getIdentificado() {
        return identificado;
    }

    public void setIdentificado(String identificado) {
        this.identificado = identificado;
    }
//
//    public String getBico() {
//        return bico;
//    }
//
//    public void setBico(String bico) {
//        this.bico = bico;
//    }
//
//    public Double getVolume() {
//        return volume;
//    }
//
//    public void setVolume(Double volume) {
//        this.volume = volume;
//    }
//
//    public Time getDuracao() {
//        return duracao;
//    }
//
//    public void setDuracao(Time duracao) {
//        this.duracao = duracao;
//    }
//
//    public Timestamp getDataHora() {
//        return dataHora;
//    }
//
//    public void setDataHora(Timestamp dataHora) {
//        this.dataHora = dataHora;
//    }
//
//    public Double getEncerrante() {
//        return encerrante;
//    }
//
//    public void setEncerrante(Double encerrante) {
//        this.encerrante = encerrante;
//    }
//
//    public Integer getNumero() {
//        return numero;
//    }
//
//    public void setNumero(Integer numero) {
//        this.numero = numero;
//    }
}
