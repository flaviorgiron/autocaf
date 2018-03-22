package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Empresa;
import ctr.GlobalParameter;
import util.GeraLog;
import util.JConfirmMessage;

public class EmpresasDB {

    public static ArrayList<Empresa> buscaEmpresas(boolean mostraInativos) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Empresa> empresas = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "SITEMPRESA ='A' OR SITEMPRESA ='I'";
            } else {
                condicao = "SITEMPRESA ='A'";
            }
            sql = "SELECT * FROM EMPRESAS WHERE " + condicao + " ORDER BY NOMEEMPRESA ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empresa c = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                empresas.add(c);
            }
            return empresas;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Empresa buscaEmpresa(Integer seqEmpresa) {
        Connection conn = GlobalParameter.getConn();
        try {
            Empresa empresa = new Empresa();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EMPRESAS WHERE SEQEMPRESA = " + seqEmpresa);
            while (rs.next()) {
                empresa.setSeqEmpresa(rs.getInt("SEQEMPRESA"));
                empresa.setIdentEmpresa(rs.getString("IDENTEMPRESA"));
                empresa.setNomeEmpresa(rs.getString("NOMEEMPRESA"));
                empresa.setEnderecoCompleto(rs.getString("ENDERECOCOMPLETO"));
                empresa.setSituacao(rs.getString("SITEMPRESA"));
            }
            return empresa;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean excluirEmpresa(Integer seqEmpresa) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate("DELETE FROM EMPRESAS WHERE SEQEMPRESA = " + seqEmpresa);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            if (e.getCause().getMessage().contains("violation of FOREIGN KEY constraint")) {
                JConfirmMessage.showMessageDialog("Não é possível excluir, registro em utilização por outras rotinas do sistema", "Atenção");
            } else {
                JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            }
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

    public static boolean insertOrUpdate(Empresa c) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            if (c.getSituacao() == null) {
                c.setSituacao("A");
            }
            String sql = "UPDATE OR INSERT INTO EMPRESAS (SEQEMPRESA, IDENTEMPRESA, NOMEEMPRESA, ENDERECOCOMPLETO, SITEMPRESA) VALUES ("
                    + c.getSeqEmpresa()
                    + ",'"
                    + c.getIdentEmpresa()
                    + "','"
                    + c.getNomeEmpresa()
                    + "','"
                    + c.getEnderecoCompleto()
                    + "','"
                    + c.getSituacao() + "')";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            if (e.getCause().getMessage().contains("violation of PRIMARY or UNIQUE KEY")) {
                JConfirmMessage.showMessageDialog("Empresa já cadastrada (Identificador)", "Atenção");
            } else {
                JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            }
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }
}
