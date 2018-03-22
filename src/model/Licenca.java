package model;

public class Licenca {

    private Integer idLicenca;
    private String licenca;
    private Integer seqEmpresa;
    private String ultimoAcesso;

    public Licenca() {
    }

    public Integer getIdLicenca() {
        return idLicenca;
    }

    public void setIdLicenca(Integer idLicenca) {
        this.idLicenca = idLicenca;
    }

    public String getLicenca() {
        return licenca;
    }

    public void setLicenca(String licenca) {
        this.licenca = licenca;
    }

    public Integer getSeqEmpresa() {
        return seqEmpresa;
    }

    public void setSeqEmpresa(Integer seqEmpresa) {
        this.seqEmpresa = seqEmpresa;
    }

    public String getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(String ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

}
