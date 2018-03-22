package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Combustivel;
import model.Empresa;
import model.Frota;
import model.Veiculo;
import ctr.GlobalParameter;
import util.GeraLog;
import util.JConfirmMessage;
import util.TratamentoValores;

public class VeiculosDB {

    public static boolean atualizaLimiteRestante(Integer seqVeiculo, Double volume) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            volume = TratamentoValores.arredondar(volume, 2, 1);
            String sql = "UPDATE VEICULOS SET RESTANTESEMANAL = RESTANTESEMANAL - " + volume + " WHERE LIMITESEMANAL > 0 AND SEQVEICULO=" + seqVeiculo;
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

    public static ArrayList<Veiculo> buscaVeiculos(boolean mostraInativos) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Veiculo> veiculos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "(SITVEICULO ='A' OR SITVEICULO ='I')";
            } else {
                condicao = "(SITVEICULO ='A')";
            }
            sql = "SELECT E.*, V.*, F.*, C.*\n"
                    + "FROM VEICULOS V \n"
                    + "INNER JOIN COMBUSTIVEIS C ON C.SEQCOMBUSTIVEL = V.SEQCOMBUSTIVEL \n"
                    + "INNER JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA\n"
                    + "INNER JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA\n"
                    + "WHERE " + condicao + " ORDER BY NOMEVEICULO ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                Veiculo v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), c, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));

                v.setSegunda(rs.getString("SEGUNDA"));
                v.setTerca(rs.getString("TERCA"));
                v.setQuarta(rs.getString("QUARTA"));
                v.setQuinta(rs.getString("QUINTA"));
                v.setSexta(rs.getString("SEXTA"));
                v.setSabado(rs.getString("SABADO"));
                v.setDomingo(rs.getString("DOMINGO"));

                v.setLimiteSemanal(rs.getDouble("LIMITESEMANAL"));
                //v.setProxRenovacaoAutomatica(rs.getDate("PROXRENAUTO"));
                v.setRestanteSemanal(rs.getDouble("RESTANTESEMANAL"));
                v.setDiaRenovacao(rs.getString("DIARENOVACAO"));

                veiculos.add(v);
            }
            return veiculos;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static ArrayList<Veiculo> buscaVeiculosNome(boolean mostraInativos, String nome) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Veiculo> veiculos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "(SITVEICULO ='A' OR SITVEICULO ='I')";
            } else {
                condicao = "(SITVEICULO ='A')";
            }
            sql = "SELECT E.*, V.*, F.*, C.*\n"
                    + "FROM VEICULOS V \n"
                    + "INNER JOIN COMBUSTIVEIS C ON C.SEQCOMBUSTIVEL = V.SEQCOMBUSTIVEL \n"
                    + "INNER JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA\n"
                    + "INNER JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA\n"
                    + "WHERE " + condicao + " AND V.NOMEVEICULO LIKE '%" + nome + "%' ORDER BY NOMEVEICULO ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                Veiculo v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), c, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));

                v.setSegunda(rs.getString("SEGUNDA"));
                v.setTerca(rs.getString("TERCA"));
                v.setQuarta(rs.getString("QUARTA"));
                v.setQuinta(rs.getString("QUINTA"));
                v.setSexta(rs.getString("SEXTA"));
                v.setSabado(rs.getString("SABADO"));
                v.setDomingo(rs.getString("DOMINGO"));

                v.setLimiteSemanal(rs.getDouble("LIMITESEMANAL"));
                //v.setProxRenovacaoAutomatica(rs.getDate("PROXRENAUTO"));
                v.setRestanteSemanal(rs.getDouble("RESTANTESEMANAL"));
                v.setDiaRenovacao(rs.getString("DIARENOVACAO"));

                veiculos.add(v);
            }
            return veiculos;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Veiculo buscaVeiculo(Integer idVeiculo) {
        Connection conn = GlobalParameter.getConn();
        try {
            Veiculo v = new Veiculo();
            Statement stmt = conn.createStatement();
            String sql = "SELECT E.*, V.*, F.*, C.*\n"
                    + "FROM VEICULOS V \n"
                    + "INNER JOIN COMBUSTIVEIS C ON C.SEQCOMBUSTIVEL = V.SEQCOMBUSTIVEL \n"
                    + "INNER JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA\n"
                    + "INNER JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA\n"
                    + "WHERE SEQVEICULO = " + idVeiculo;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                //Combustivel cc = new Combustivel(rs.getInt("SEQCOMCOMBOIO"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));

                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), c, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));
                v.setHodometroAtual(rs.getLong("HODOMETROATUAL"));
                v.setHorimetroAtual(rs.getLong("HORIMETROATUAL"));

                v.setSegunda(rs.getString("SEGUNDA"));
                v.setTerca(rs.getString("TERCA"));
                v.setQuarta(rs.getString("QUARTA"));
                v.setQuinta(rs.getString("QUINTA"));
                v.setSexta(rs.getString("SEXTA"));
                v.setSabado(rs.getString("SABADO"));
                v.setDomingo(rs.getString("DOMINGO"));
                v.setLimiteSemanal(rs.getDouble("LIMITESEMANAL"));
                //v.setProxRenovacaoAutomatica(rs.getDate("PROXRENAUTO"));
                v.setRestanteSemanal(rs.getDouble("RESTANTESEMANAL"));
                v.setDiaRenovacao(rs.getString("DIARENOVACAO"));
            }
            return v;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Long buscaHodometroHorimetroAnterior(Veiculo v, Timestamp dataConsulta) {
        Connection conn = GlobalParameter.getConn();
        Long retorno = 0L;
        try {
            Statement stmt = conn.createStatement();
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            String sql = "SELECT FIRST 1 HORIMETRO, HODOMETRO FROM ABASTECIMENTOS WHERE DATAHORA < ' " + formato.format(dataConsulta) + "' AND SEQVEICULO = " + v.getSeqVeiculo() + " ORDER BY IDABASTEC DESC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                switch (v.getTipo()) {
                    case "O":
                        retorno = rs.getLong("HODOMETRO");
                        break;
                    case "H":
                        retorno = rs.getLong("HORIMETRO");
                        break;
                }
            }
            //atribui o marcador inicial do veiculo --> Primeiro abastecimento ou virou o motor
            if (retorno <= 0) {
                retorno = v.getValorInicial();
            }
            return retorno;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return retorno;
        }
    }

    public static Veiculo buscaIdentVeiculo(String identVeiculo) {
        Connection conn = GlobalParameter.getConn();
        try {
            Veiculo v = null;
            Statement stmt = conn.createStatement();
            String sql = "SELECT E.*, V.*, F.*, C.*\n"
                    + "FROM VEICULOS V \n"
                    + "INNER JOIN COMBUSTIVEIS C ON C.SEQCOMBUSTIVEL = V.SEQCOMBUSTIVEL \n"
                    + "INNER JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA\n"
                    + "INNER JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA\n"
                    + "WHERE IDENTVEICULO = '" + identVeiculo + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), c, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));

                v.setSegunda(rs.getString("SEGUNDA"));
                v.setTerca(rs.getString("TERCA"));
                v.setQuarta(rs.getString("QUARTA"));
                v.setQuinta(rs.getString("QUINTA"));
                v.setSexta(rs.getString("SEXTA"));
                v.setSabado(rs.getString("SABADO"));
                v.setDomingo(rs.getString("DOMINGO"));
                v.setLimiteSemanal(rs.getDouble("LIMITESEMANAL"));
                //v.setProxRenovacaoAutomatica(rs.getDate("PROXRENAUTO"));
                v.setRestanteSemanal(rs.getDouble("RESTANTESEMANAL"));
                v.setDiaRenovacao(rs.getString("DIARENOVACAO"));
            }
            return v;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean excluirVeiculo(Integer seqVeiculo) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate("DELETE FROM VEICULOS WHERE SEQVEICULO = " + seqVeiculo);
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

    public static boolean insertOrUpdateVeiculo(Veiculo v) {
        Connection conn = GlobalParameter.getConn();

        try {
            Statement stmt = conn.createStatement();
            if (v.getSituacao() == null) {
                v.setSituacao("A");
            }
            if (v.getFlex() == null) {
                v.setFlex("N");
            }

            if (v.getLimiteSemanal() == 0) {
                v.setRestanteSemanal(0.0);
                v.setDiaRenovacao("Selecione");
            }
            String sql = "UPDATE OR INSERT INTO VEICULOS (SEQVEICULO, IDENTVEICULO, NOMEVEICULO, SEQCOMBUSTIVEL, TIPO, SEQFROTA, LIMITE, VALORINICIAL, SITVEICULO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO, LIMITESEMANAL, RESTANTESEMANAL, DIARENOVACAO) VALUES ("
                    + v.getSeqVeiculo()
                    + ",'"
                    + v.getIdentVeiculo()
                    + "','"
                    + v.getNomeVeiculo()
                    + "',"
                    + v.getCombustivel().getSeqCombustivel()
                    + ",'"
                    + v.getTipo()
                    + "',"
                    + v.getFrota().getSeqFrota()
                    + ","
                    + v.getLimite()
                    + ","
                    + v.getValorInicial()
                    + ",'"
                    + v.getSituacao()
                    + "','"
                    + v.getSegunda()
                    + "','"
                    + v.getTerca()
                    + "','"
                    + v.getQuarta()
                    + "','"
                    + v.getQuinta()
                    + "','"
                    + v.getSexta()
                    + "','"
                    + v.getSabado()
                    + "','"
                    + v.getDomingo()
                    + "',"
                    + v.getLimiteSemanal()
                    + ","
                    + v.getRestanteSemanal()
                    + ",'"
                    + v.getDiaRenovacao()
                    + "')";
            //LIMITESEMANAL, PROXRENAUTO, RESTANTESEMANAL, DIARENOVACAO
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            if (e.getCause().getMessage().contains("attempt to store duplicate value")) {
                JConfirmMessage.showMessageDialog("Veículo já cadastrado (Identificador)", "Atenção");
            } else {
                JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            }
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

    public static ArrayList<Veiculo> buscaVeiculosFrota(boolean mostraInativos, Integer seqFrota) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Veiculo> veiculos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "(SITVEICULO ='A' OR SITVEICULO ='I')";
            } else {
                condicao = "(SITVEICULO ='A')";
            }
            sql = "SELECT E.*, V.*, F.*, C.*\n"
                    + "FROM VEICULOS V \n"
                    + "INNER JOIN COMBUSTIVEIS C ON C.SEQCOMBUSTIVEL = V.SEQCOMBUSTIVEL \n"
                    + "INNER JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA\n"
                    + "INNER JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA\n"
                    + "WHERE " + condicao + " AND V.SEQFROTA = " + seqFrota + " ORDER BY NOMEVEICULO ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                Veiculo v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), c, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));

                v.setSegunda(rs.getString("SEGUNDA"));
                v.setTerca(rs.getString("TERCA"));
                v.setQuarta(rs.getString("QUARTA"));
                v.setQuinta(rs.getString("QUINTA"));
                v.setSexta(rs.getString("SEXTA"));
                v.setSabado(rs.getString("SABADO"));
                v.setDomingo(rs.getString("DOMINGO"));
                v.setLimiteSemanal(rs.getDouble("LIMITESEMANAL"));
                //v.setProxRenovacaoAutomatica(rs.getDate("PROXRENAUTO"));
                v.setRestanteSemanal(rs.getDouble("RESTANTESEMANAL"));
                v.setDiaRenovacao(rs.getString("DIARENOVACAO"));
                veiculos.add(v);
            }
            return veiculos;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static ArrayList<Veiculo> buscaVeiculosEmpresa(boolean mostraInativos, Integer seqEmpresa) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Veiculo> veiculos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql, condicao;
            if (mostraInativos) {
                condicao = "(SITVEICULO ='A' OR SITVEICULO ='I')";
            } else {
                condicao = "(SITVEICULO ='A')";
            }
            sql = "SELECT E.*, V.*, F.*, C.*\n"
                    + "FROM VEICULOS V \n"
                    + "INNER JOIN COMBUSTIVEIS C ON C.SEQCOMBUSTIVEL = V.SEQCOMBUSTIVEL \n"
                    + "INNER JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA\n"
                    + "INNER JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA\n"
                    + "WHERE " + condicao + " AND E.SEQEMPRESA = " + seqEmpresa + " ORDER BY NOMEVEICULO ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combustivel c = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                Veiculo v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), c, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));

                v.setSegunda(rs.getString("SEGUNDA"));
                v.setTerca(rs.getString("TERCA"));
                v.setQuarta(rs.getString("QUARTA"));
                v.setQuinta(rs.getString("QUINTA"));
                v.setSexta(rs.getString("SEXTA"));
                v.setSabado(rs.getString("SABADO"));
                v.setDomingo(rs.getString("DOMINGO"));
                v.setLimiteSemanal(rs.getDouble("LIMITESEMANAL"));
                //v.setProxRenovacaoAutomatica(rs.getDate("PROXRENAUTO"));
                v.setRestanteSemanal(rs.getDouble("RESTANTESEMANAL"));
                v.setDiaRenovacao(rs.getString("DIARENOVACAO"));
                veiculos.add(v);
            }
            return veiculos;
        } catch (SQLException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean atualizaMarcadorVeiculo(Integer seqVeiculo, Long marcador, String tipo) {
        Connection conn = GlobalParameter.getConn();
        String sql;
        try {
            Statement stmt = conn.createStatement();
            if (tipo.equals("O")) {
                sql = "UPDATE OR INSERT INTO VEICULOS (SEQVEICULO, HODOMETROATUAL) VALUES ("
                        + seqVeiculo + "," + marcador + ")";
            } else {
                sql = "UPDATE OR INSERT INTO VEICULOS (SEQVEICULO, HORIMETROATUAL) VALUES ("
                        + seqVeiculo + "," + marcador + ")";
            }
            int res = stmt.executeUpdate(sql);
            return res == 1;
        } catch (SQLException | NumberFormatException e) {
            if (e.getCause().getMessage().contains("violation of PRIMARY or UNIQUE KEY")) {
                JConfirmMessage.showMessageDialog("Veículo já cadastrado (Identificador)", "Atenção");
            } else {
                JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            }
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

    public static boolean atualizaIdent(Veiculo v, String ident) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE VEICULOS SET IDENTVEICULO = '" + ident + "' WHERE SEQVEICULO = " + v.getSeqVeiculo();
            int res = stmt.executeUpdate(sql);
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
