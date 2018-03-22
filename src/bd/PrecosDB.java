package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import model.Bico;
import model.Preco;
import ctr.GlobalParameter;
import util.GeraLog;
import util.JConfirmMessage;

public class PrecosDB {

    public static Preco buscaUltimoPreco(Bico b) {
        Connection conn = GlobalParameter.getConn();
        try {
            Preco p = new Preco();
            p.setPreco(0.0);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT FIRST 1 * FROM PRECOS WHERE SEQBICO = " + b.getSeqBico() + " ORDER BY IDPRECO DESC");
            while (rs.next()) {
                p.setIdPreco(rs.getInt("IDPRECO"));
                p.setPreco(rs.getDouble("PRECO"));
                p.setDataHora(rs.getTimestamp("DATAHORA"));
            }
            return p;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean insertOrUpdate(Preco p) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            String sql = "UPDATE OR INSERT INTO PRECOS (IDPRECO, PRECO, SEQBICO, DATAHORA) VALUES ("
                    + p.getIdPreco()
                    + ","
                    + p.getPreco()
                    + ","
                    + p.getBico().getSeqBico()
                    + ",'"
                    + formato.format(p.getDataHora()) + "')";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        }
    }
}
