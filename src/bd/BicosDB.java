package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Bico;
import model.Combustivel;
import ctr.GlobalParameter;
import util.GeraLog;
import util.JConfirmMessage;

public class BicosDB {

    public static ArrayList<Bico> buscaBicosInternos(boolean mostraInativos) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Bico> bicos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "SITBICO ='A' OR SITBICO ='I'";
            } else {
                condicao = "SITBICO ='A'";
            }
            sql = "SELECT * FROM BICOS B INNER JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL WHERE " + condicao + " AND COMBOIO = 'N' AND EXTERNO = 'N' ORDER BY NOMEBICO ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Bico b = new Bico(rs.getInt("SEQBICO"), rs.getString("IDENTBICO"), rs.getString("NOMEBICO"), c, rs.getString("IDCBC"), rs.getString("SITBICO"), rs.getDouble("ENCERRANTEINICIAL"));
                b.setComboio(rs.getString("COMBOIO"));
                b.setConexao(rs.getString("CONEXAO"));
                b.setPorta(rs.getInt("PORTA"));
                b.setCasasDecimais(rs.getInt("CASASDECIMAIS"));
                b.setExterno(rs.getString("EXTERNO"));
                b.setIdTWC(rs.getInt("IDTWC"));
                bicos.add(b);
            }
            return bicos;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static ArrayList<Bico> buscaBicos(boolean mostraInativos) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Bico> bicos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "SITBICO ='A' OR SITBICO ='I'";
            } else {
                condicao = "SITBICO ='A'";
            }
            sql = "SELECT * FROM BICOS B INNER JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL WHERE " + condicao + " AND COMBOIO = 'N' ORDER BY NOMEBICO ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Bico b = new Bico(rs.getInt("SEQBICO"), rs.getString("IDENTBICO"), rs.getString("NOMEBICO"), c, rs.getString("IDCBC"), rs.getString("SITBICO"), rs.getDouble("ENCERRANTEINICIAL"));
                b.setComboio(rs.getString("COMBOIO"));
                b.setConexao(rs.getString("CONEXAO"));
                b.setPorta(rs.getInt("PORTA"));
                b.setCasasDecimais(rs.getInt("CASASDECIMAIS"));
                b.setExterno(rs.getString("EXTERNO"));
                b.setIdTWC(rs.getInt("IDTWC"));
                bicos.add(b);
            }
            return bicos;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static ArrayList<Bico> buscaBicosExternos(boolean mostraInativos) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Bico> bicos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "SITBICO ='A' OR SITBICO ='I'";
            } else {
                condicao = "SITBICO ='A'";
            }
            sql = "SELECT * FROM BICOS B INNER JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL WHERE " + condicao + " AND COMBOIO = 'N' AND EXTERNO = 'S' ORDER BY NOMEBICO ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Bico b = new Bico(rs.getInt("SEQBICO"), rs.getString("IDENTBICO"), rs.getString("NOMEBICO"), c, rs.getString("IDCBC"), rs.getString("SITBICO"), rs.getDouble("ENCERRANTEINICIAL"));
                b.setComboio(rs.getString("COMBOIO"));
                b.setConexao(rs.getString("CONEXAO"));
                b.setPorta(rs.getInt("PORTA"));
                b.setCasasDecimais(rs.getInt("CASASDECIMAIS"));
                b.setExterno(rs.getString("EXTERNO"));
                b.setIdTWC(rs.getInt("IDTWC"));
                bicos.add(b);
            }
            return bicos;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static ArrayList<Bico> buscaComboios(boolean mostraInativos) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Bico> bicos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "SITBICO ='A' OR SITBICO ='I'";
            } else {
                condicao = "SITBICO ='A'";
            }
            sql = "SELECT * FROM BICOS B INNER JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL WHERE " + condicao + " AND COMBOIO = 'S' AND EXTERNO = 'N' ORDER BY NOMEBICO ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Bico b = new Bico(rs.getInt("SEQBICO"), rs.getString("IDENTBICO"), rs.getString("NOMEBICO"), c, rs.getString("IDCBC"), rs.getString("SITBICO"), rs.getDouble("ENCERRANTEINICIAL"));
                b.setComboio(rs.getString("COMBOIO"));
                b.setConexao(rs.getString("CONEXAO"));
                b.setPorta(rs.getInt("PORTA"));
                b.setCasasDecimais(rs.getInt("CASASDECIMAIS"));
                b.setExterno(rs.getString("EXTERNO"));
                b.setIdTWC(rs.getInt("IDTWC"));
                bicos.add(b);
            }
            return bicos;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Bico buscaBico(Integer seqBico) {
        Connection conn = GlobalParameter.getConn();
        try {
            Bico b = new Bico();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM BICOS B INNER JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL WHERE SEQBICO = " + seqBico;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                b.setSeqBico(rs.getInt("SEQBICO"));
                b.setIdentBico(rs.getString("IDENTBICO"));
                b.setNomeBico(rs.getString("NOMEBICO"));
                b.setIdCBC(rs.getString("IDCBC"));
                b.setSituacao(rs.getString("SITBICO"));
                b.setEncerrante(rs.getDouble("ENCERRANTEINICIAL"));
                b.setComboio(rs.getString("COMBOIO"));
                b.setConexao(rs.getString("CONEXAO"));
                b.setPorta(rs.getInt("PORTA"));
                b.setCasasDecimais(rs.getInt("CASASDECIMAIS"));
                b.setExterno(rs.getString("EXTERNO"));
                b.setIdTWC(rs.getInt("IDTWC"));
                b.setCombustivel(c);
            }
            return b;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Bico buscaBicoCBC(String idCBC) {
        Connection conn = GlobalParameter.getConn();
        try {
            Bico b = null;
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM BICOS B INNER JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL WHERE IDCBC = '" + idCBC + "' AND EXTERNO = 'N'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                b = new Bico();
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                b.setSeqBico(rs.getInt("SEQBICO"));
                b.setIdentBico(rs.getString("IDENTBICO"));
                b.setNomeBico(rs.getString("NOMEBICO"));
                b.setIdCBC(rs.getString("IDCBC"));
                b.setSituacao(rs.getString("SITBICO"));
                b.setEncerrante(rs.getDouble("ENCERRANTEINICIAL"));
                b.setComboio(rs.getString("COMBOIO"));
                b.setConexao(rs.getString("CONEXAO"));
                b.setPorta(rs.getInt("PORTA"));
                b.setCasasDecimais(rs.getInt("CASASDECIMAIS"));
                b.setExterno(rs.getString("EXTERNO"));
                b.setIdTWC(rs.getInt("IDTWC"));
                b.setCombustivel(c);
            }
            return b;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean buscaBicoJaCadastradoCBC(String idCBC, Integer seqBicoIgnorar, String statusAtual) {
        Connection conn = GlobalParameter.getConn();
        try {
            Bico b = null;
            Statement stmt = conn.createStatement();
            String sql;

            if (statusAtual.equals("I")) {
                return false;
            }//inativos não precisa verificar se já esta cadastrado.

            if ((seqBicoIgnorar > 0)) {
                sql = "SELECT * FROM BICOS B INNER JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL WHERE SITBICO = 'A' AND IDCBC = '" + idCBC + "' AND SEQBICO <> " + seqBicoIgnorar;
            } else {
                sql = "SELECT * FROM BICOS B INNER JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL WHERE SITBICO = 'A' AND IDCBC = '" + idCBC + "'";
            }

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                b = new Bico();
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                b.setSeqBico(rs.getInt("SEQBICO"));
                b.setIdentBico(rs.getString("IDENTBICO"));
                b.setNomeBico(rs.getString("NOMEBICO"));
                b.setIdCBC(rs.getString("IDCBC"));
                b.setSituacao(rs.getString("SITBICO"));
                b.setEncerrante(rs.getDouble("ENCERRANTEINICIAL"));
                b.setComboio(rs.getString("COMBOIO"));
                b.setConexao(rs.getString("CONEXAO"));
                b.setPorta(rs.getInt("PORTA"));
                b.setCasasDecimais(rs.getInt("CASASDECIMAIS"));
                b.setExterno(rs.getString("EXTERNO"));
                b.setIdTWC(rs.getInt("IDTWC"));
                b.setCombustivel(c);
            }
            return b != null;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        }
    }

    public static boolean buscaBicoJaCadastradoTWC(String idTerminal, Integer seqBicoIgnorar, String statusAtual) {
        Connection conn = GlobalParameter.getConn();
        try {
            Bico b = null;
            Statement stmt = conn.createStatement();
            String sql;

            if (statusAtual.equals("I")) {
                return false;
            }//inativos não precisa verificar se já esta cadastrado.

            if ((seqBicoIgnorar > 0)) {
                sql = "SELECT * FROM BICOS B INNER JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL WHERE SITBICO = 'A' AND IDTWC = '" + idTerminal + "' AND SEQBICO <> " + seqBicoIgnorar;
            } else {
                sql = "SELECT * FROM BICOS B INNER JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL WHERE SITBICO = 'A' AND IDTWC = '" + idTerminal + "'";
            }

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                b = new Bico();
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                b.setSeqBico(rs.getInt("SEQBICO"));
                b.setIdentBico(rs.getString("IDENTBICO"));
                b.setNomeBico(rs.getString("NOMEBICO"));
                b.setIdCBC(rs.getString("IDCBC"));
                b.setSituacao(rs.getString("SITBICO"));
                b.setEncerrante(rs.getDouble("ENCERRANTEINICIAL"));
                b.setComboio(rs.getString("COMBOIO"));
                b.setConexao(rs.getString("CONEXAO"));
                b.setPorta(rs.getInt("PORTA"));
                b.setCasasDecimais(rs.getInt("CASASDECIMAIS"));
                b.setExterno(rs.getString("EXTERNO"));
                b.setIdTWC(rs.getInt("IDTWC"));
                b.setCombustivel(c);
            }
            return b != null;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        }
    }

    public static Bico buscaIdentBico(String identBico) {
        Connection conn = GlobalParameter.getConn();
        try {
            Bico b = null;
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM BICOS B INNER JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL WHERE IDENTBICO = '" + identBico + "' AND EXTERNO = 'N'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                b = new Bico();
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                b.setSeqBico(rs.getInt("SEQBICO"));
                b.setIdentBico(rs.getString("IDENTBICO"));
                b.setNomeBico(rs.getString("NOMEBICO"));
                b.setIdCBC(rs.getString("IDCBC"));
                b.setSituacao(rs.getString("SITBICO"));
                b.setEncerrante(rs.getDouble("ENCERRANTEINICIAL"));
                b.setComboio(rs.getString("COMBOIO"));
                b.setConexao(rs.getString("CONEXAO"));
                b.setPorta(rs.getInt("PORTA"));
                b.setCasasDecimais(rs.getInt("CASASDECIMAIS"));
                b.setExterno(rs.getString("EXTERNO"));
                b.setIdTWC(rs.getInt("IDTWC"));
                b.setCombustivel(c);
            }
            return b;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean excluirBico(Integer seqBico) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate("DELETE FROM BICOS WHERE SEQBICO = " + seqBico);
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

    public static boolean insertOrUpdateBico(Bico b) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            if (b.getSituacao() == null) {
                b.setSituacao("A");
            }
            if (b.getIdCBC() == null) {
                b.setIdCBC("99");
            }
            String sql = "UPDATE OR INSERT INTO BICOS (SEQBICO, IDENTBICO, NOMEBICO, SEQCOMBUSTIVEL, IDCBC, ENCERRANTEINICIAL, SITBICO, COMBOIO, CONEXAO, PORTA, CASASDECIMAIS, EXTERNO, IDTWC) VALUES ("
                    + b.getSeqBico()
                    + ",'"
                    + b.getIdentBico()
                    + "','"
                    + b.getNomeBico()
                    + "',"
                    + b.getCombustivel().getSeqCombustivel()
                    + ",'"
                    + b.getIdCBC()
                    + "',"
                    + b.getEncerrante()
                    + ",'"
                    + b.getSituacao()
                    + "','"
                    + b.getComboio()
                    + "','"
                    + b.getConexao()
                    + "',"
                    + b.getPorta()
                    + ","
                    + b.getCasasDecimais()
                    + ",'"
                    + b.getExterno()
                    + "','"
                    + b.getIdTWC()
                    + "')";
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            if (e.getCause().getMessage().contains("violation of PRIMARY or UNIQUE KEY")) {
                JConfirmMessage.showMessageDialog("Bico já cadastrado (Identificador)", "Atenção");
            } else {
                JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            }
            return false;
        }
    }
}
