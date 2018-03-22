package model;

public class Operador {

    private Integer seqOperador;
    private String identOperador;
    private String nomeOperador;
    private String login;
    private String senha;
    private String situacao;
    private String isOperador;
    private Empresa empresa;
    private String isSuperUsuario;
    private String isMaster;

    public Operador() {
    }

    public Operador(Integer seqOperador, String identOperador, String nomeOperador, String login, String senha, String situacao, String isOperador, Empresa empresa) {
        this.seqOperador = seqOperador;
        this.identOperador = identOperador;
        this.nomeOperador = nomeOperador;
        this.login = login;
        this.senha = senha;
        this.situacao = situacao;
        this.isOperador = isOperador;
        this.empresa = empresa;
    }

    public Operador(Integer seqOperador, String identOperador, String nomeOperador, String login, String senha, String situacao, String isOperador) {
        this.seqOperador = seqOperador;
        this.identOperador = identOperador;
        this.nomeOperador = nomeOperador;
        this.login = login;
        this.senha = senha;
        this.situacao = situacao;
        this.isOperador = isOperador;
    }

    public String getNomeOperador() {
        return nomeOperador;
    }

    public void setNomeOperador(String nomeOperador) {
        this.nomeOperador = nomeOperador;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getIsOperador() {
        return isOperador;
    }

    public void setIsOperador(String isOperador) {
        this.isOperador = isOperador;
    }

    public Integer getSeqOperador() {
        return seqOperador;
    }

    public void setSeqOperador(Integer seqOperador) {
        this.seqOperador = seqOperador;
    }

    public String getIdentOperador() {
        return identOperador;
    }

    public void setIdentOperador(String identOperador) {
        this.identOperador = identOperador;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getIsSuperUsuario() {
        return isSuperUsuario;
    }

    public void setIsSuperUsuario(String isSuperUsuario) {
        this.isSuperUsuario = isSuperUsuario;
    }

    public String getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(String isMaster) {
        this.isMaster = isMaster;
    }
}
