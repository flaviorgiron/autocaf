package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Frota;
import model.Empresa;
import ctr.GlobalParameter;
import util.GeraLog;
import util.JConfirmMessage;
import util.TratamentoValores;

public class FrotasDB {

    public static ArrayList<Frota> buscaFrotas(boolean mostraInativos) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Frota> frotas = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "(SITFROTA ='A' OR SITFROTA ='I')";
            } else {
                condicao = "(SITFROTA ='A')";
            }
            sql = "SELECT F.*, E.* FROM FROTAS F INNER JOIN EMPRESAS E ON F.SEQEMPRESA = E.SEQEMPRESA WHERE " + condicao + " ORDER BY NOMEFROTA ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                frotas.add(f);
            }
            return frotas;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Frota buscaFrota(Integer seqFrota) {
        Connection conn = GlobalParameter.getConn();
        try {
            Frota frota = new Frota();
            Statement stmt = conn.createStatement();
            String sql = "SELECT F.*,E.* FROM FROTAS F INNER JOIN EMPRESAS E ON F.SEQEMPRESA = E.SEQEMPRESA WHERE SEQFROTA = " + seqFrota;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empresa c = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                frota.setSeqFrota(rs.getInt("SEQFROTA"));
                frota.setIdentFrota(rs.getString("IDENTFROTA"));
                frota.setNomeFrota(rs.getString("NOMEFROTA"));
                frota.setSituacao(rs.getString("SITFROTA"));
                frota.setDisponivel(rs.getDouble("DISPONIVEL"));
                frota.setLimite(rs.getDouble("LIMITEFROTA"));
                frota.setEmpresa(c);
            }
            return frota;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean excluirFrota(Integer seqFrota) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate("DELETE FROM FROTAS WHERE SEQFROTA = " + seqFrota);
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

    public static boolean insertOrUpdateFrota(Frota f) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            if (f.getSituacao() == null) {
                f.setSituacao("A");
            }

            if (f.getLimite() == 0) {
                f.setDisponivel(0.0);
            }

            String sql = "UPDATE OR INSERT INTO FROTAS (SEQFROTA, IDENTFROTA, NOMEFROTA, SEQEMPRESA, LIMITEFROTA, DISPONIVEL, SITFROTA) VALUES ("
                    + f.getSeqFrota()
                    + ",'"
                    + f.getIdentFrota()
                    + "','"
                    + f.getNomeFrota()
                    + "',"
                    + f.getEmpresa().getSeqEmpresa()
                    + ","
                    + f.getLimite()
                    + ","
                    + f.getDisponivel()
                    + ",'"
                    + f.getSituacao() + "')";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            if (e.getCause().getMessage().contains("violation of PRIMARY or UNIQUE KEY")) {
                JConfirmMessage.showMessageDialog("Frota já cadastrada (Identificador)", "Atenção");
            } else {
                JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            }
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

    public static ArrayList<Frota> buscaFrotasEmpresa(boolean mostraInativos, Integer seqEmpresa) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Frota> frotas = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "(SITFROTA ='A' OR SITFROTA ='I')";
            } else {
                condicao = "(SITFROTA ='A')";
            }
            sql = "SELECT F.*, E.* FROM FROTAS F INNER JOIN EMPRESAS E ON F.SEQEMPRESA = E.SEQEMPRESA WHERE " + condicao + " AND E.SEQEMPRESA = " + seqEmpresa + " ORDER BY NOMEFROTA ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                frotas.add(f);
            }
            return frotas;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean atualizaLimiteDisponivel(Integer seqFrota, Double volume) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            volume = TratamentoValores.arredondar(volume, 2, 1);
            String sql = "UPDATE FROTAS SET DISPONIVEL = DISPONIVEL - " + volume + " WHERE LIMITEFROTA > 0 AND DISPONIVEL > 0 AND SEQFROTA=" + seqFrota;
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
