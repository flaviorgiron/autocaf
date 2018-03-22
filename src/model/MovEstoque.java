package model;

import java.sql.Timestamp;

public class MovEstoque {

    private Timestamp dataHora;
    private Combustivel combustivel;
    private String tipoMovimento;
    private Double quantidade;
    private String observacao;

    public MovEstoque(Timestamp dataHora, Combustivel combustivel, String tipoMovimento, Double quantidade, String observacao) {
        this.dataHora = dataHora;
        this.combustivel = combustivel;
        this.tipoMovimento = tipoMovimento;
        this.quantidade = quantidade;
        this.observacao = observacao;
    }

    public MovEstoque() {
    }

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }
}
