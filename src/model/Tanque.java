package model;

import java.util.ArrayList;

public class Tanque {

    private Integer seqTanque;
    private String identTanque;
    private String nomeTanque;
    private Double capacidadeTotal;
    private String tipo;
    private ArrayList<Bico> bicos;
    private ArrayList<CombustivelTanque> combustiveisTanque;
    private String situacao;

    public Tanque() {
    }

    public Tanque(String identTanque, String nomeTanque, Double capacidadeTotal, String situacao) {
        this.identTanque = identTanque;
        this.nomeTanque = nomeTanque;
        this.capacidadeTotal = capacidadeTotal;
        this.situacao = situacao;
    }

    public Tanque(Integer seqTanque, String identTanque, String nomeTanque, Double capacidadeTotal, String situacao) {
        this.seqTanque = seqTanque;
        this.identTanque = identTanque;
        this.nomeTanque = nomeTanque;
        this.capacidadeTotal = capacidadeTotal;
        this.situacao = situacao;
    }

    public Integer getSeqTanque() {
        return seqTanque;
    }

    public void setSeqTanque(Integer seqTanque) {
        this.seqTanque = seqTanque;
    }

    public String getIdentTanque() {
        return identTanque;
    }

    public void setIdentTanque(String identTanque) {
        this.identTanque = identTanque;
    }

    public String getNomeTanque() {
        return nomeTanque;
    }

    public void setNomeTanque(String nomeTanque) {
        this.nomeTanque = nomeTanque;
    }

    public Double getCapacidadeTotal() {
        return capacidadeTotal;
    }

    public void setCapacidadeTotal(Double capacidadeTotal) {
        this.capacidadeTotal = capacidadeTotal;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public ArrayList<Bico> getBicos() {
        return bicos;
    }

    public void setBicos(ArrayList<Bico> bicos) {
        this.bicos = bicos;
    }

    public ArrayList<CombustivelTanque> getCombustiveisTanque() {
        return combustiveisTanque;
    }

    public void setCombustiveisTanque(ArrayList<CombustivelTanque> combustiveisTanque) {
        this.combustiveisTanque = combustiveisTanque;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
