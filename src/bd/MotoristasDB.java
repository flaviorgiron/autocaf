package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Motorista;
import ctr.GlobalParameter;
import util.GeraLog;
import util.JConfirmMessage;

public class MotoristasDB {

    public static ArrayList<Motorista> buscaMotoristas(boolean mostraInativos) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Motorista> motoristas = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "SITMOTORISTA ='A' OR SITMOTORISTA ='I'";
            } else {
                condicao = "SITMOTORISTA ='A'";
            }
            sql = "SELECT * FROM MOTORISTAS WHERE " + condicao + " ORDER BY NOMEMOTORISTA ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Motorista m = new Motorista(rs.getInt("SEQMOTORISTA"), rs.getString("IDENTMOTORISTA"), rs.getString("NOMEMOTORISTA"), rs.getString("SITMOTORISTA"));
                motoristas.add(m);
            }
            return motoristas;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Motorista buscaMotorista(Integer seqMotorista) {
        Connection conn = GlobalParameter.getConn();
        try {
            Motorista motorista = new Motorista();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MOTORISTAS WHERE SEQMOTORISTA = " + seqMotorista);
            while (rs.next()) {
                motorista.setSeqMotorista(rs.getInt("SEQMOTORISTA"));
                motorista.setIdentMotorista(rs.getString("IDENTMOTORISTA"));
                motorista.setNomeMotorista(rs.getString("NOMEMOTORISTA"));
                motorista.setSituacao(rs.getString("SITMOTORISTA"));
            }
            return motorista;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean excluirMotorista(Integer seqMotorista) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate("DELETE FROM MOTORISTAS WHERE SEQMOTORISTA = " + seqMotorista);
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

    public static Motorista buscaIdentMotorista(String identMotorista) {
        Connection conn = GlobalParameter.getConn();

        try {
            Motorista m = null;
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM MOTORISTAS M WHERE IDENTMOTORISTA = '" + identMotorista + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                m = new Motorista();
                m.setSeqMotorista(rs.getInt("SEQMOTORISTA"));
                m.setIdentMotorista(rs.getString("IDENTMOTORISTA"));
                m.setNomeMotorista(rs.getString("NOMEMOTORISTA"));
                m.setSituacao(rs.getString("SITMOTORISTA"));
            }
            return m;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean insertOrUpdate(Motorista c) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            if (c.getSituacao() == null) {
                c.setSituacao("A");
            }
            String sql = "UPDATE OR INSERT INTO MOTORISTAS (SEQMOTORISTA, IDENTMOTORISTA, NOMEMOTORISTA, SITMOTORISTA) VALUES ("
                    + c.getSeqMotorista()
                    + ",'"
                    + c.getIdentMotorista()
                    + "','"
                    + c.getNomeMotorista()
                    + "','"
                    + c.getSituacao() + "')";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            if (e.getCause().getMessage().contains("violation of PRIMARY or UNIQUE KEY")) {
                JConfirmMessage.showMessageDialog("Motorista já cadastrado (Identificador)", "Atenção");
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
