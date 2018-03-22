package model;

public class Bico {

    private Integer seqBico;
    private String identBico;
    private String nomeBico;
    private String idCBC;
    private String situacao;
    private String externo;
    private Combustivel combustivel;
    private Double encerrante;
    private String comboio;
    private String conexao;
    private Integer porta;
    private Integer casasDecimais;
    private Integer idTWC;

    public Bico() {
    }

    public Bico(Integer seqBico, String identBico, String nomeBico, Combustivel combustivel, String idCBC, String situacao, Double encerrante) {
        this.seqBico = seqBico;
        this.identBico = identBico;
        this.nomeBico = nomeBico;
        this.idCBC = idCBC;
        this.situacao = situacao;
        this.combustivel = combustivel;
        this.encerrante = encerrante;
    }

    public Bico(Integer seqBico, String identBico, String nomeBico, String idCBC, String situacao, Double encerrante) {
        this.seqBico = seqBico;
        this.identBico = identBico;
        this.nomeBico = nomeBico;
        this.idCBC = idCBC;
        this.situacao = situacao;
        this.encerrante = encerrante;
    }

    public Bico(Integer seqBico, String identBico, String nomeBico) {
        this.seqBico = seqBico;
        this.identBico = identBico;
        this.nomeBico = nomeBico;
    }

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public String getIdCBC() {
        return idCBC;
    }

    public void setIdCBC(String idCBC) {
        this.idCBC = idCBC;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Integer getSeqBico() {
        return seqBico;
    }

    public void setSeqBico(Integer seqBico) {
        this.seqBico = seqBico;
    }

    public String getIdentBico() {
        return identBico;
    }

    public void setIdentBico(String identBico) {
        this.identBico = identBico;
    }

    public String getNomeBico() {
        return nomeBico;
    }

    public void setNomeBico(String nomeBico) {
        this.nomeBico = nomeBico;
    }

    public Double getEncerrante() {
        return encerrante;
    }

    public void setEncerrante(Double encerrante) {
        this.encerrante = encerrante;
    }

    public String getComboio() {
        return comboio;
    }

    public void setComboio(String comboio) {
        this.comboio = comboio;
    }

    public String getConexao() {
        return conexao;
    }

    public void setConexao(String conexao) {
        this.conexao = conexao;
    }

    public Integer getPorta() {
        return porta;
    }

    public void setPorta(Integer porta) {
        this.porta = porta;
    }

    public Integer getCasasDecimais() {
        return casasDecimais;
    }

    public void setCasasDecimais(Integer casasDecimais) {
        this.casasDecimais = casasDecimais;
    }

    public String getExterno() {
        return externo;
    }

    public void setExterno(String externo) {
        this.externo = externo;
    }

    public Integer getIdTWC() {
        return idTWC;
    }

    public void setIdTWC(Integer idTWC) {
        this.idTWC = idTWC;
    }
}
