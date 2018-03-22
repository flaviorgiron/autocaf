package model;

public class Empresa {

    private Integer seqEmpresa;
    private String identEmpresa;
    private String nomeEmpresa;
    private String situacao;
    private String enderecoCompleto;

    public Empresa() {
    }

    public Empresa(Integer seqEmpresa, String identEmpresa, String nomeEmpresa, String enderecoCompleto, String situacao) {
        this.seqEmpresa = seqEmpresa;
        this.identEmpresa = identEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.situacao = situacao;
        this.enderecoCompleto = enderecoCompleto;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Integer getSeqEmpresa() {
        return seqEmpresa;
    }

    public void setSeqEmpresa(Integer seqEmpresa) {
        this.seqEmpresa = seqEmpresa;
    }

    public String getIdentEmpresa() {
        return identEmpresa;
    }

    public void setIdentEmpresa(String identEmpresa) {
        this.identEmpresa = identEmpresa;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }
}
