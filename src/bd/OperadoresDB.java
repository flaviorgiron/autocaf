package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Empresa;
import model.Operador;
import ctr.GlobalParameter;
import java.sql.PreparedStatement;
import util.GeraLog;
import util.JConfirmMessage;

public class OperadoresDB {

    public static Operador buscaOperador(Integer seqOperador) {
        Connection conn = GlobalParameter.getConn();
        try {
            Operador operador = new Operador();
            Empresa e = new Empresa();
            String sql = "SELECT * FROM OPERADORES O INNER JOIN EMPRESAS E ON E.SEQEMPRESA = O.SEQEMPRESA WHERE SEQOPERADOR = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, seqOperador);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                operador.setSeqOperador(rs.getInt("SEQOPERADOR"));
                operador.setIdentOperador(rs.getString("IDENTOPERADOR"));
                operador.setNomeOperador(rs.getString("NOMEOPERADOR"));
                operador.setLogin(rs.getString("LOGIN"));
                operador.setSenha(rs.getString("SENHA"));
                operador.setSituacao(rs.getString("SITOPERADOR"));
                operador.setIsOperador(rs.getString("OPERADOR"));
                operador.setIsMaster(rs.getString("MASTER"));

                e.setIdentEmpresa(rs.getString("IDENTEMPRESA"));
                e.setNomeEmpresa(rs.getString("NOMEEMPRESA"));
                e.setEnderecoCompleto(rs.getString("ENDERECOCOMPLETO"));
                e.setSeqEmpresa(rs.getInt("SEQEMPRESA"));
                e.setSituacao(rs.getString("SITEMPRESA"));
                operador.setEmpresa(e);
            }
            return operador;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Operador buscaIdentOperador(String identOperador) {
        Connection conn = GlobalParameter.getConn();
        try {
            Operador operador = null;
            Empresa e = new Empresa();
            String sql = "SELECT * FROM OPERADORES O INNER JOIN EMPRESAS E ON E.SEQEMPRESA = O.SEQEMPRESA WHERE IDENTOPERADOR = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, identOperador);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                operador = new Operador();
                operador.setSeqOperador(rs.getInt("SEQOPERADOR"));
                operador.setIdentOperador(rs.getString("IDENTOPERADOR"));
                operador.setNomeOperador(rs.getString("NOMEOPERADOR"));
                operador.setLogin(rs.getString("LOGIN"));
                operador.setSenha(rs.getString("SENHA"));
                operador.setSituacao(rs.getString("SITOPERADOR"));
                operador.setIsOperador(rs.getString("OPERADOR"));
                operador.setIsMaster(rs.getString("MASTER"));

                e.setIdentEmpresa(rs.getString("IDENTEMPRESA"));
                e.setNomeEmpresa(rs.getString("NOMEEMPRESA"));
                e.setEnderecoCompleto(rs.getString("ENDERECOCOMPLETO"));
                e.setSeqEmpresa(rs.getInt("SEQEMPRESA"));
                e.setSituacao(rs.getString("SITEMPRESA"));
                operador.setEmpresa(e);

            }
            return operador;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Operador consultaLoginSenha(String login, String senha) {
        GlobalParameter.getInstance();
        Connection conn = GlobalParameter.getConn();
        if (conn == null) {
            JConfirmMessage.showMessageDialog("Não foi possível estabelecer uma conexão com o banco de dados.", "Atenção");
            return null;
        }
        Operador operador = null;
        Empresa e = new Empresa();
        try {
            String sql = "SELECT * FROM OPERADORES O INNER JOIN EMPRESAS E ON E.SEQEMPRESA = O.SEQEMPRESA WHERE OPERADOR= 'S' AND UPPER (LOGIN)= ? AND UPPER(SENHA)= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                operador = new Operador();
                operador.setSeqOperador(rs.getInt("SEQOPERADOR"));
                operador.setIdentOperador(rs.getString("IDENTOPERADOR"));
                operador.setNomeOperador(rs.getString("NOMEOPERADOR"));
                operador.setLogin(rs.getString("LOGIN"));
                operador.setSenha(rs.getString("SENHA"));
                operador.setSituacao(rs.getString("SITOPERADOR"));
                operador.setIsOperador(rs.getString("OPERADOR"));
                operador.setIsMaster(rs.getString("MASTER"));

                e.setIdentEmpresa(rs.getString("IDENTEMPRESA"));
                e.setNomeEmpresa(rs.getString("NOMEEMPRESA"));
                e.setEnderecoCompleto(rs.getString("ENDERECOCOMPLETO"));
                e.setSeqEmpresa(rs.getInt("SEQEMPRESA"));
                e.setSituacao(rs.getString("SITEMPRESA"));
                operador.setIsSuperUsuario(rs.getString("SUPERUSUARIO"));
                operador.setEmpresa(e);
            }
            return operador;
        } catch (Exception ex) {
            GeraLog g = new GeraLog();
            g.gravaErro(ex);
            g.close();
            JConfirmMessage.showMessageDialog(ex.getMessage(), "Atenção");
            return null;
        }
    }

    public static Operador consultaLogin(String login, Integer seqOperadorAtual) {
        GlobalParameter.getInstance();
        Connection conn = GlobalParameter.getConn();
        Operador operador = null;
        Empresa e = new Empresa();
        try {
            String sql = "SELECT * FROM OPERADORES O INNER JOIN EMPRESAS E ON E.SEQEMPRESA = O.SEQEMPRESA WHERE SEQOPERADOR <> ? AND UPPER (LOGIN) = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, seqOperadorAtual);
            ps.setString(2, login);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                operador = new Operador();
                operador.setSeqOperador(rs.getInt("SEQOPERADOR"));
                operador.setIdentOperador(rs.getString("IDENTOPERADOR"));
                operador.setNomeOperador(rs.getString("NOMEOPERADOR"));
                operador.setLogin(rs.getString("LOGIN"));
                operador.setSenha(rs.getString("SENHA"));
                operador.setSituacao(rs.getString("SITOPERADOR"));
                operador.setIsOperador(rs.getString("OPERADOR"));
                operador.setIsMaster(rs.getString("MASTER"));

                e.setIdentEmpresa(rs.getString("IDENTEMPRESA"));
                e.setNomeEmpresa(rs.getString("NOMEEMPRESA"));
                e.setEnderecoCompleto(rs.getString("ENDERECOCOMPLETO"));
                e.setSeqEmpresa(rs.getInt("SEQEMPRESA"));
                e.setSituacao(rs.getString("SITEMPRESA"));
                operador.setEmpresa(e);
            }
            return operador;
        } catch (SQLException | NumberFormatException ex) {
            GeraLog g = new GeraLog();
            g.gravaErro(ex);
            g.close();
            JConfirmMessage.showMessageDialog(ex.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean excluirOperador(Integer seqOperador) {
        Connection conn = GlobalParameter.getConn();
        try {
            String sql = "DELETE FROM OPERADORES WHERE SEQOPERADOR = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, seqOperador);
            return ps.execute();
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

    public static boolean insertOrUpdate(Operador o) {
        Connection conn = GlobalParameter.getConn();
        try {
            if (o.getSituacao() == null) {
                o.setSituacao("A");
            }
            if (o.getIsOperador() == null) {
                o.setIsOperador("N");
            }
            if (o.getIsMaster() == null) {
                o.setIsMaster("N");
            }
//            if (o.getLogin() == null) {
//                o.setLogin("");
//            }
//            if (o.getSenha() == null) {
//                o.setSenha("");
//            }

            String sql = "UPDATE OR INSERT INTO OPERADORES (SEQOPERADOR, IDENTOPERADOR, NOMEOPERADOR, LOGIN, SENHA, SITOPERADOR, SEQEMPRESA, OPERADOR, MASTER) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, o.getSeqOperador());
            ps.setString(2, o.getIdentOperador());
            ps.setString(3, o.getNomeOperador());
            ps.setString(4, o.getLogin());
            ps.setString(5, o.getSenha());
            ps.setString(6, o.getSituacao());
            ps.setInt(7, o.getEmpresa().getSeqEmpresa());
            ps.setString(8, o.getIsOperador());
            ps.setString(9, o.getIsMaster());

            int res = ps.executeUpdate();
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            if (e.getCause().getMessage().contains("attempt to store duplicate value")) {
                JConfirmMessage.showMessageDialog("Operador já cadastrado (Identificador)", "Atenção");
            } else {
                JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            }
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

    public static ArrayList<Operador> buscaTodosOperadores(boolean mostraInativos) {
        GlobalParameter.getInstance();
        Connection conn = GlobalParameter.getConn();
        try {
            ArrayList<Operador> operadores = new ArrayList<>();
            String sql;
//            if (mostraInativos) {
//                condicao = "(SITOPERADOR ='A' OR SITOPERADOR ='I')";
//            } else {
//                condicao = "SITOPERADOR ='A'";
//            }
            sql = "SELECT * FROM OPERADORES O INNER JOIN EMPRESAS E ON E.SEQEMPRESA = O.SEQEMPRESA WHERE (SITOPERADOR = ? OR SITOPERADOR = ?) AND SUPERUSUARIO = 'N' ORDER BY NOMEOPERADOR ASC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "A");
            if (mostraInativos) {
                ps.setString(2, "I");
            } else {
                ps.setString(2, "A");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empresa e = new Empresa();
                e.setIdentEmpresa(rs.getString("IDENTEMPRESA"));
                e.setNomeEmpresa(rs.getString("NOMEEMPRESA"));
                e.setSeqEmpresa(rs.getInt("SEQEMPRESA"));
                e.setSituacao(rs.getString("SITEMPRESA"));

                Operador o = new Operador(rs.getInt("SEQOPERADOR"), rs.getString("IDENTOPERADOR"), rs.getString("NOMEOPERADOR"), rs.getString("LOGIN"), rs.getString("SENHA"), rs.getString("SITOPERADOR"), rs.getString("OPERADOR"), e);
                o.setIsMaster(rs.getString("MASTER"));
                operadores.add(o);
            }
            return operadores;
        } catch (SQLException | NumberFormatException ex) {
            GeraLog g = new GeraLog();
            g.gravaErro(ex);
            g.close();
            JConfirmMessage.showMessageDialog(ex.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean atualizaIdent(Operador o, String ident) {
        Connection conn = GlobalParameter.getConn();
        try {
            String sql = "UPDATE OPERADORES SET IDENTOPERADOR = ? WHERE SEQOPERADOR = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ident);
            ps.setInt(2, o.getSeqOperador());

            int res = ps.executeUpdate();
            return res == 1;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
        return false;
    }
}
