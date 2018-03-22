package model;

public class Motorista {

    private Integer seqMotorista;
    private String identMotorista;
    private String nomeMotorista;
    private String situacao;

    public Motorista() {
    }

    public Motorista(Integer seqMotorista) {
        this.seqMotorista = seqMotorista;
    }

    public Motorista(Integer seqMotorista, String identMotorista) {
        this.seqMotorista = seqMotorista;
        this.identMotorista = identMotorista;
    }

    public Motorista(Integer seqMotorista, String identMotorista, String nomeMotorista) {
        this.seqMotorista = seqMotorista;
        this.identMotorista = identMotorista;
        this.nomeMotorista = nomeMotorista;
    }

    public Motorista(Integer seqMotorista, String identMotorista, String nomeMotorista, String situacao) {
        this.seqMotorista = seqMotorista;
        this.identMotorista = identMotorista;
        this.nomeMotorista = nomeMotorista;
        this.situacao = situacao;
    }

    public Integer getSeqMotorista() {
        return seqMotorista;
    }

    public void setSeqMotorista(Integer seqMotorista) {
        this.seqMotorista = seqMotorista;
    }

    public String getIdentMotorista() {
        return identMotorista;
    }

    public void setIdentMotorista(String identMotorista) {
        this.identMotorista = identMotorista;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

}
