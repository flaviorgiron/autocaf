package model;

public class Frota {

    private Integer seqFrota;
    private String identFrota;
    private String nomeFrota;
    private String situacao;
    private Empresa empresa;
    private Double disponivel;
    private Double limite;

    public Frota() {
    }

    public Frota(Integer seqFrota) {
        this.seqFrota = seqFrota;
    }

    public Frota(Integer seqFrota, String identFrota, String nomeFrota, Empresa empresa, String situacao, Double limite, Double disponivel) {
        this.seqFrota = seqFrota;
        this.identFrota = identFrota;
        this.nomeFrota = nomeFrota;
        this.empresa = empresa;
        this.situacao = situacao;
        this.disponivel = disponivel;
        this.limite = limite;
    }

    public String getNomeFrota() {
        return nomeFrota;
    }

    public void setNomeFrota(String nomeFrota) {
        this.nomeFrota = nomeFrota;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Double getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Double disponivel) {
        this.disponivel = disponivel;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Integer getSeqFrota() {
        return seqFrota;
    }

    public void setSeqFrota(Integer seqFrota) {
        this.seqFrota = seqFrota;
    }

    public String getIdentFrota() {
        return identFrota;
    }

    public void setIdentFrota(String identFrota) {
        this.identFrota = identFrota;
    }
}
