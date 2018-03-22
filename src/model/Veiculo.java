package model;

public class Veiculo {

    private Integer seqVeiculo;
    private String identVeiculo;
    private String nomeVeiculo;
    private String tipo;
    private Combustivel combustivel;
    private Frota frota;
    private String situacao;
    private Double limite = 0.0;
    private String flex;
    private Long valorInicial = 0L;
    private Long hodometroAtual = 0L;
    private Long horimetroAtual = 0L;
    private String segunda;
    private String terca;
    private String quarta;
    private String quinta;
    private String sexta;
    private String sabado;
    private String domingo;
    private Combustivel combustivelComboio;
    //private Date proxRenovacaoAutomatica;
    private Double restanteSemanal;
    private Double limiteSemanal;
    private String diaRenovacao;

    public Veiculo() {
    }

    public Veiculo(Integer seqVeiculo, String identVeiculo, String nomeVeiculo, String tipo, Combustivel combustivel, Frota frota, String situacao, Double limite, Long valorInicial) {
        this.seqVeiculo = seqVeiculo;
        this.identVeiculo = identVeiculo;
        this.nomeVeiculo = nomeVeiculo;
        this.tipo = tipo;
        this.combustivel = combustivel;
        this.frota = frota;
        this.situacao = situacao;
        this.limite = limite;
        this.valorInicial = valorInicial;
    }

    public Veiculo(Integer seqVeiculo, String identVeiculo, String nomeVeiculo, String tipo, String situacao, Double limite, Long valorInicial) {
        this.seqVeiculo = seqVeiculo;
        this.identVeiculo = identVeiculo;
        this.nomeVeiculo = nomeVeiculo;
        this.tipo = tipo;
        this.situacao = situacao;
        this.limite = limite;
        this.valorInicial = valorInicial;
    }

    public String getNomeVeiculo() {
        return nomeVeiculo;
    }

    public void setNomeVeiculo(String nomeVeiculo) {
        this.nomeVeiculo = nomeVeiculo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public String getFlex() {
        return flex;
    }

    public void setFlex(String flex) {
        this.flex = flex;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Long getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Long valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Integer getSeqVeiculo() {
        return seqVeiculo;
    }

    public void setSeqVeiculo(Integer seqVeiculo) {
        this.seqVeiculo = seqVeiculo;
    }

    public String getIdentVeiculo() {
        return identVeiculo;
    }

    public void setIdentVeiculo(String identVeiculo) {
        this.identVeiculo = identVeiculo;
    }

    public Long getHodometroAtual() {
        return hodometroAtual;
    }

    public void setHodometroAtual(Long hodometroAtual) {
        this.hodometroAtual = hodometroAtual;
    }

    public Long getHorimetroAtual() {
        return horimetroAtual;
    }

    public void setHorimetroAtual(Long horimetroAtual) {
        this.horimetroAtual = horimetroAtual;
    }
//
//    public String getComboio() {
//        return comboio;
//    }
//
//    public void setComboio(String comboio) {
//        this.comboio = comboio;
//    }
//
//    public String getConexao() {
//        return conexao;
//    }
//
//    public void setConexao(String conexao) {
//        this.conexao = conexao;
//    }
//
//    public Integer getPorta() {
//        return porta;
//    }
//
//    public void setPorta(Integer porta) {
//        this.porta = porta;
//    }
//
//    public Integer getCasasDecimais() {
//        return casasDecimais;
//    }
//
//    public void setCasasDecimais(Integer casasDecimais) {
//        this.casasDecimais = casasDecimais;
//    }

    public Combustivel getCombustivelComboio() {
        return combustivelComboio;
    }

    public void setCombustivelComboio(Combustivel combustivelComboio) {
        this.combustivelComboio = combustivelComboio;
    }

    public String getSegunda() {
        return segunda;
    }

    public void setSegunda(String segunda) {
        this.segunda = segunda;
    }

    public String getTerca() {
        return terca;
    }

    public void setTerca(String terca) {
        this.terca = terca;
    }

    public String getQuarta() {
        return quarta;
    }

    public void setQuarta(String quarta) {
        this.quarta = quarta;
    }

    public String getQuinta() {
        return quinta;
    }

    public void setQuinta(String quinta) {
        this.quinta = quinta;
    }

    public String getSexta() {
        return sexta;
    }

    public void setSexta(String sexta) {
        this.sexta = sexta;
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public String getDomingo() {
        return domingo;
    }

    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }

//    public Date getProxRenovacaoAutomatica() {
//        return proxRenovacaoAutomatica;
//    }
//
//    public void setProxRenovacaoAutomatica(Date proxRenovacaoAutomatica) {
//        this.proxRenovacaoAutomatica = proxRenovacaoAutomatica;
//    }
    public Double getRestanteSemanal() {
        return restanteSemanal;
    }

    public void setRestanteSemanal(Double restanteSemanal) {
        this.restanteSemanal = restanteSemanal;
    }

    public Double getLimiteSemanal() {
        return limiteSemanal;
    }

    public void setLimiteSemanal(Double limiteSemanal) {
        this.limiteSemanal = limiteSemanal;
    }

    public String getDiaRenovacao() {
        return diaRenovacao;
    }

    public void setDiaRenovacao(String diaRenovacao) {
        this.diaRenovacao = diaRenovacao;
    }
}
