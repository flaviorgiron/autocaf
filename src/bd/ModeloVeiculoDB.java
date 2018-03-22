package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Combustivel;
import ctr.GlobalParameter;
import util.GeraLog;
import util.JConfirmMessage;

public class ModeloVeiculoDB {

    public static ArrayList<Combustivel> buscaModeloVeiculos(boolean mostraInativos) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Combustivel> combustiveis = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "SITCOMBUSTIVEL ='A' OR SITCOMBUSTIVEL ='I'";
            } else {
                condicao = "SITCOMBUSTIVEL ='A'";
            }
            sql = "SELECT * FROM COMBUSTIVEIS WHERE " + condicao + " ORDER BY IDENTCOMBUSTIVEL ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                combustiveis.add(c);
            }
            return combustiveis;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Combustivel buscaModeloVeiculo(Integer seqCombustivel) {
        Connection conn = GlobalParameter.getConn();
        try {
            Combustivel combustivel = new Combustivel();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMBUSTIVEIS WHERE SEQCOMBUSTIVEL = " + seqCombustivel);
            while (rs.next()) {
                combustivel.setSeqCombustivel(rs.getInt("SEQCOMBUSTIVEL"));
                combustivel.setIdentCombustivel(rs.getString("IDENTCOMBUSTIVEL"));
                combustivel.setNomeCombustivel(rs.getString("NOMECOMBUSTIVEL"));
                combustivel.setSituacao(rs.getString("SITCOMBUSTIVEL"));
            }
            return combustivel;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean excluirModeloVeiculo(Integer seqCombustivel) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate("DELETE FROM COMBUSTIVEIS WHERE SEQCOMBUSTIVEL = " + seqCombustivel);
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

    public static boolean insertOrUpdate(Combustivel c) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            if (c.getSituacao() == null) {
                c.setSituacao("A");
            }
            String sql = "UPDATE OR INSERT INTO COMBUSTIVEIS (SEQCOMBUSTIVEL, IDENTCOMBUSTIVEL, NOMECOMBUSTIVEL, SITCOMBUSTIVEL) VALUES ("
                    + c.getSeqCombustivel()
                    + ",'"
                    + c.getIdentCombustivel()
                    + "','"
                    + c.getNomeCombustivel()
                    + "','"
                    + c.getSituacao() + "')";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            if (e.getCause().getMessage().contains("violation of PRIMARY or UNIQUE KEY")) {
                JConfirmMessage.showMessageDialog("Combustível já cadastrado (Identificador)", "Atenção");
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
