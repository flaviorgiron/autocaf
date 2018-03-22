package model;

public class ModeloVeiculo {

    private Integer seqModeloVeiculo;
    private String identModeloVeiculo;
    private String nomeModeloVeiculo;
    private String situacao;
    private Fabricante fabricante;

    public ModeloVeiculo(Integer seqModeloVeiculo, String identModeloVeiculo, String nomeModeloVeiculo, String situacao, Fabricante fabricante) {
        this.seqModeloVeiculo = seqModeloVeiculo;
        this.identModeloVeiculo = identModeloVeiculo;
        this.nomeModeloVeiculo = nomeModeloVeiculo;
        this.situacao = situacao;
        this.fabricante = fabricante;
    }

    public ModeloVeiculo() {
    }

    public Integer getSeqModeloVeiculo() {
        return seqModeloVeiculo;
    }

    public void setSeqModeloVeiculo(Integer seqModeloVeiculo) {
        this.seqModeloVeiculo = seqModeloVeiculo;
    }

    public String getIdentModeloVeiculo() {
        return identModeloVeiculo;
    }

    public void setIdentModeloVeiculo(String identModeloVeiculo) {
        this.identModeloVeiculo = identModeloVeiculo;
    }

    public String getNomeModeloVeiculo() {
        return nomeModeloVeiculo;
    }

    public void setNomeModeloVeiculo(String nomeModeloVeiculo) {
        this.nomeModeloVeiculo = nomeModeloVeiculo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }
}
