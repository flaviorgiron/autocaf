package model;

public class CombustivelTanque {

    private Double capacidade;
    private Combustivel combustivel;
    private Tanque tanque;

    public CombustivelTanque(Double capacidade, Combustivel combustivel, Tanque tanque) {
        this.capacidade = capacidade;
        this.combustivel = combustivel;
        this.tanque = tanque;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public Tanque getTanque() {
        return tanque;
    }

    public void setTanque(Tanque tanque) {
        this.tanque = tanque;
    }

}
