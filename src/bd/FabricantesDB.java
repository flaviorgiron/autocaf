package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Fabricante;
import ctr.GlobalParameter;
import util.GeraLog;
import util.JConfirmMessage;

public class FabricantesDB {

    public static ArrayList<Fabricante> buscaFabricantes(boolean mostraInativos) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Fabricante> fabricantes = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "SITFABRICANTE ='A' OR SITFABRICANTE ='I'";
            } else {
                condicao = "SITFABRICANTE ='A'";
            }
            sql = "SELECT * FROM FABRICANTES WHERE " + condicao + " ORDER BY NOMEFABRICANTE ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Fabricante c = new Fabricante(rs.getInt("SEQFABRICANTE"), rs.getString("IDENTFABRICANTE"), rs.getString("NOMEFABRICANTE"), rs.getString("SITFABRICANTE"));
                fabricantes.add(c);
            }
            return fabricantes;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Fabricante buscaFabricante(Integer seqFabricante) {
        Connection conn = GlobalParameter.getConn();
        try {
            Fabricante fabricante = new Fabricante();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FABRICANTES WHERE SEQFABRICANTE = " + seqFabricante);
            while (rs.next()) {
                fabricante.setSeqFabricante(rs.getInt("SEQFABRICANTE"));
                fabricante.setIdentFabricante(rs.getString("IDENTFABRICANTE"));
                fabricante.setNomeFabricante(rs.getString("NOMEFABRICANTE"));
                fabricante.setSituacao(rs.getString("SITFABRICANTE"));
            }
            return fabricante;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean excluirFabricante(Integer seqFabricante) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate("DELETE FROM FABRICANTES WHERE SEQFABRICANTE = " + seqFabricante);
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

    public static boolean insertOrUpdate(Fabricante c) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            if (c.getSituacao() == null) {
                c.setSituacao("A");
            }
            String sql = "UPDATE OR INSERT INTO FABRICANTES (SEQFABRICANTE, IDENTFABRICANTE, NOMEFABRICANTE, SITFABRICANTE) VALUES ("
                    + c.getSeqFabricante()
                    + ",'"
                    + c.getIdentFabricante()
                    + "','"
                    + c.getNomeFabricante()
                    + "','"
                    + c.getSituacao() + "')";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            if (e.getCause().getMessage().contains("violation of PRIMARY or UNIQUE KEY")) {
                JConfirmMessage.showMessageDialog("Fabricante já cadastrado (Identificador)", "Atenção");
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
