package model;

public class Fabricante {

    private Integer seqFabricante;
    private String identFabricante;
    private String nomeFabricante;
    private String situacao;

    public Fabricante() {
    }

    public Fabricante(Integer seqFabricante) {
        this.seqFabricante = seqFabricante;
    }

    public Fabricante(Integer seqFabricante, String identFabricante, String nomeFabricante, String situacao) {
        this.seqFabricante = seqFabricante;
        this.identFabricante = identFabricante;
        this.nomeFabricante = nomeFabricante;
        this.situacao = situacao;
    }

    public String getNomeFabricante() {
        return nomeFabricante;
    }

    public void setNomeFabricante(String nomeFabricante) {
        this.nomeFabricante = nomeFabricante;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getIdentFabricante() {
        return identFabricante;
    }

    public void setIdentFabricante(String identFabricante) {
        this.identFabricante = identFabricante;
    }

    public Integer getSeqFabricante() {
        return seqFabricante;
    }

    public void setSeqFabricante(Integer seqFabricante) {
        this.seqFabricante = seqFabricante;
    }
}
