package bd;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import model.Historico;
import ctr.GlobalParameter;
import util.GeraLog;

public class HistoricoDB {

    public static boolean insertOrUpdate(Historico h) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE OR INSERT INTO HISTORICO (IDHISTORICO, MENSAGEM, IDENTIFICADO) VALUES (NULL,'"
                    + h.getMensagem()
                    + "','" + h.getIdentificado() + "')";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        }
    }

    public static boolean insertHistoricoDadosTela(Integer seqBico, Integer seqVeiculo, Integer seqOperador, Long marcador, Integer seqMotorista) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE OR INSERT INTO HISTORICODADOSTELA (IDHISTDADOSTELA, SEQBICO, SEQVEICULO, SEQOPERADOR, MARCADOR, SEQMOTORISTA) VALUES (NULL,"
                    + seqBico + "," + seqVeiculo + "," + seqOperador + "," + marcador + ", " + seqMotorista + ")";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        }
    }

    public static boolean insertOrUpdate(Historico h, Integer seqBico, String mBico, Double mVolume, Time mDuracao, Timestamp mDataHora, Integer mNumero, Double mEncerrante) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE OR INSERT INTO HISTORICO (IDHISTORICO, MENSAGEM, IDENTIFICADO, SEQBICO, NOMEBICO, VOLUME, DURACAO, DATA_HORA, NUMERO, ENCERRANTE) VALUES (NULL,'"
                    + h.getMensagem()
                    + "','"
                    + h.getIdentificado()
                    + "',"
                    + seqBico
                    + ",'"
                    + mBico
                    + "',"
                    + mVolume
                    + ",'"
                    + mDuracao
                    + "','"
                    + mDataHora
                    + "',"
                    + mNumero
                    + ","
                    + mEncerrante
                    + ")";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        }
    }
}
