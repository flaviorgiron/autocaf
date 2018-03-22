package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Licenca;
import ctr.GlobalParameter;
import util.GeraLog;
import util.JConfirmMessage;

public class LicencaDB {

    public static Licenca buscaLicenca() {
        try {
            GlobalParameter.getInstance();
            Connection conn = GlobalParameter.getConn();
            if (conn == null) {
                JConfirmMessage.showMessageDialog("Não foi possível estabelecer uma conexão com o banco de dados.", "Atenção");
                return null;
            }

            Licenca l = new Licenca();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM LICENCA WHERE IDLICENCA = 1");
            while (rs.next()) {
                l.setIdLicenca(rs.getInt("IDLICENCA"));
                l.setLicenca(rs.getString("LICENCA"));
                l.setSeqEmpresa(rs.getInt("SEQEMPRESA"));
                l.setUltimoAcesso(rs.getString("ULTIMOACESSO"));
            }
            return l;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean insertOrUpdate(String novaLicenca) {
        try {
            GlobalParameter.getInstance();
            Connection conn = GlobalParameter.getConn();
            if (conn == null) {
                JConfirmMessage.showMessageDialog("Não foi possível estabelecer uma conexão com o banco de dados.", "Atenção");
                return false;
            }

            Statement stmt = conn.createStatement();
            String sql = "UPDATE OR INSERT INTO LICENCA (IDLICENCA, LICENCA) VALUES ("
                    + 1
                    + ", '"
                    + novaLicenca
                    + "')";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        }
    }

    public static boolean atualizaUltimoAcesso(String ultimoAcesso) {
        try {
            GlobalParameter.getInstance();
            Connection conn = GlobalParameter.getConn();
            if (conn == null) {
                JConfirmMessage.showMessageDialog("Não foi possível estabelecer uma conexão com o banco de dados.", "Atenção");
                return false;
            }

            Statement stmt = conn.createStatement();
            String sql = "UPDATE OR INSERT INTO LICENCA (IDLICENCA, ULTIMOACESSO) VALUES ("
                    + 1
                    + ", '"
                    + ultimoAcesso
                    + "')";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        }
    }

}
