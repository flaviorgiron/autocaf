package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ctr.GlobalParameter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.*;
import util.GeraLog;
import util.JConfirmMessage;

public class EstoqueDB {

    public static Double buscaSaldoEstoqueCombustivel(Integer seqCombustivel) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        Double saldoEstoque = 0.0;
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM SALDOESTOQUE WHERE SEQCOMBUSTIVEL = " + seqCombustivel;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                saldoEstoque = rs.getDouble("SALDO");
            }
            return saldoEstoque;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return 0.0;
        }
    }

    public static boolean gravaSaldoEstoque(String tipoMovimento, Integer seqCombustivel, Double quantidade, String obs) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "";

            if (tipoMovimento.equals("E")) {
                sql = "UPDATE SALDOESTOQUE SET SALDO = SALDO + " + quantidade + " WHERE SEQCOMBUSTIVEL = " + seqCombustivel;
            } else if (tipoMovimento.equals("S")) {
                sql = "UPDATE SALDOESTOQUE SET SALDO = SALDO - " + quantidade + " WHERE SEQCOMBUSTIVEL = " + seqCombustivel;
            }

            int res = stmt.executeUpdate(sql);
            gravaMovimentacao(seqCombustivel, tipoMovimento, quantidade, obs);
            return res == 1;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            return false;
        }
    }

    public static void insereRegistro() {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO SALDOESTOQUE (SEQCOMBUSTIVEL, SALDO) VALUES ((SELECT MAX(SEQCOMBUSTIVEL) FROM COMBUSTIVEIS),0)";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    public static void gravaMovimentacao(Integer seqCombustivel, String tipoMovimento, Double quantidade, String obs) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            Date d = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            String dataRegistro = formato.format(d);

            String sql = "INSERT INTO MOVESTOQUE (SEQCOMBUSTIVEL, DATAHORA, TIPOMOVIMENTO, QUANTIDADE, OBSERVACAO, SALDOFINAL) VALUES (" + seqCombustivel + ", '" + dataRegistro + "','" + tipoMovimento + "', " + quantidade + ", '" + obs + "', (SELECT SALDO FROM SALDOESTOQUE WHERE SEQCOMBUSTIVEL = " + seqCombustivel + "))";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    public static void excluiRegistro(Integer seqCombustivel) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM SALDOESTOQUE WHERE SEQCOMBUSTIVEL=" + seqCombustivel;
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    public static ArrayList<MovEstoque> buscaMovimentacoes(Date dtInicial, Date dtFinal, Combustivel combustivel) {
        SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy");

        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();

        try {
            ArrayList<MovEstoque> movEstoques = new ArrayList<>();
            Statement stmt = con.createStatement();
            //Statement stmt2 = con.createStatement();

            String sql;
            sql = "SELECT * FROM MOVESTOQUE "
                    + "WHERE DATAHORA >= '" + formato.format(dtInicial) + " 00:00:00' AND DATAHORA <= '" + formato.format(dtFinal) + " 23:59:59' "
                    + " AND SEQCOMBUSTIVEL = " + combustivel.getSeqCombustivel() + " ORDER BY MOVESTOQUEID DESC";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                MovEstoque m = new MovEstoque();
                m.setCombustivel(combustivel);
                m.setQuantidade(rs.getDouble("QUANTIDADE"));
                m.setDataHora(rs.getTimestamp("DATAHORA"));
                m.setObservacao(rs.getString("OBSERVACAO"));
                m.setTipoMovimento(rs.getString("TIPOMOVIMENTO"));
                movEstoques.add(m);
            }
            return movEstoques;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }
}
