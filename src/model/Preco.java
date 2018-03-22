package model;

import java.sql.Timestamp;

public class Preco {

    private Integer idPreco;
    private Double preco;
    private Timestamp dataHora;
    private Bico bico;

    public Preco(Double preco, Timestamp dataHora, Bico bico) {
        this.preco = preco;
        this.dataHora = dataHora;
        this.bico = bico;
    }

    public Preco(Integer idPreco, Double preco, Timestamp dataHora, Bico bico) {
        this.idPreco = idPreco;
        this.preco = preco;
        this.dataHora = dataHora;
        this.bico = bico;
    }

    public Preco() {
    }

    public Integer getIdPreco() {
        return idPreco;
    }

    public void setIdPreco(Integer idPreco) {
        this.idPreco = idPreco;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public Bico getBico() {
        return bico;
    }

    public void setBico(Bico bico) {
        this.bico = bico;
    }
}
