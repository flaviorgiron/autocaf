package model;

public class Combustivel {

    private Integer seqCombustivel;
    private String identCombustivel;
    private String nomeCombustivel;
    private String situacao;

    public Combustivel() {
    }

    public Combustivel(Integer seqCombustivel) {
        this.seqCombustivel = seqCombustivel;
    }

    public Combustivel(Integer seqCombustivel, String identCombustivel, String nomeCombustivel, String situacao) {
        this.seqCombustivel = seqCombustivel;
        this.identCombustivel = identCombustivel;
        this.nomeCombustivel = nomeCombustivel;
        this.situacao = situacao;
    }

    public String getNomeCombustivel() {
        return nomeCombustivel;
    }

    public void setNomeCombustivel(String nomeCombustivel) {
        this.nomeCombustivel = nomeCombustivel;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getIdentCombustivel() {
        return identCombustivel;
    }

    public void setIdentCombustivel(String identCombustivel) {
        this.identCombustivel = identCombustivel;
    }

    public Integer getSeqCombustivel() {
        return seqCombustivel;
    }

    public void setSeqCombustivel(Integer seqCombustivel) {
        this.seqCombustivel = seqCombustivel;
    }

}
