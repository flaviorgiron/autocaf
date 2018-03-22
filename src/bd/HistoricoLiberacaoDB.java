package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ctr.GlobalParameter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.*;
import util.GeraLog;

public class HistoricoLiberacaoDB {

    public static List<HistoricoLiberacao> buscaHistoricos(Date dtInicial, Date dtFinal) {
        SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy");

        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();

        try {
            ArrayList<HistoricoLiberacao> historicos = new ArrayList<>();
            Statement stmt = con.createStatement();
            //Statement stmt2 = con.createStatement();

            String sql;
            sql = "SELECT V.*, O.*, B.*, M.*, H.DATAHORA, H.MARCADOR FROM HISTORICODADOSTELA AS H \n"
                    + "LEFT JOIN VEICULOS AS V ON V.SEQVEICULO = H.SEQVEICULO \n"
                    + "LEFT JOIN OPERADORES AS O ON O.SEQOPERADOR = H.SEQOPERADOR \n"
                    + "LEFT JOIN BICOS AS B ON B.SEQBICO = H.SEQBICO \n"
                    + "LEFT JOIN MOTORISTAS AS M ON M.SEQMOTORISTA = H.SEQMOTORISTA \n"
                    + "WHERE DATAHORA >= '" + formato.format(dtInicial) + " 00:00:00' AND DATAHORA <= '" + formato.format(dtFinal) + " 23:59:59' ";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                Bico b = null;
                Veiculo v = null;
                Motorista m = null;
                Operador o = null;

                if (rs.getObject("SEQBICO") != null) {
                    b = new Bico(rs.getInt("SEQBICO"), rs.getString("IDENTBICO"), rs.getString("NOMEBICO"), rs.getString("IDCBC"), rs.getString("SITBICO"), rs.getDouble("ENCERRANTEINICIAL"));
                }
                if (rs.getObject("SEQVEICULO") != null) {
                    v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));
                }
                if (rs.getObject("SEQMOTORISTA") != null) {
                    m = new Motorista(rs.getInt("SEQMOTORISTA"), rs.getString("IDENTMOTORISTA"), rs.getString("NOMEMOTORISTA"), rs.getString("SITMOTORISTA"));
                }
                if (rs.getObject("SEQOPERADOR") != null) {
                    o = new Operador(rs.getInt("SEQOPERADOR"), rs.getString("IDENTOPERADOR"), rs.getString("NOMEOPERADOR"), rs.getString("LOGIN"), rs.getString("SENHA"), rs.getString("SITOPERADOR"), rs.getString("OPERADOR"));
                }

                HistoricoLiberacao h = new HistoricoLiberacao();
                h.setDataHora(rs.getTimestamp("DATAHORA"));
                h.setBico(b);
                h.setOperador(o);
                h.setVeiculo(v);
                h.setHodometro(rs.getLong("MARCADOR"));
                h.setMotorista(m);

                historicos.add(h);
            }
            return historicos;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            return null;
        }
    }
}
