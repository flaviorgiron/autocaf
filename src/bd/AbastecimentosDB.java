package bd;

import model.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ctr.GlobalParameter;
import util.*;

public class AbastecimentosDB {

    public static ArrayList<Abastecimento> buscaAbastecimento(Date dtInicial, Date dtFinal) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy");

            GlobalParameter.getInstance();
            Connection con = GlobalParameter.getConn();

            ArrayList<Abastecimento> abastecimentos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql;

            sql = "SELECT "
                    + " IDABASTEC,STRINGCBC,DATAHORA,TOTALPAGAR,PRECOUNITARIO,VOLUME,TEMPOABASTECIMENTO,NUMEROABASTECIMENTO,KMMEDIO,TEMPOMEDIO,HODOMETRO,HODOMETRO_ANTERIOR, HORIMETRO, HORIMETRO_ANTERIOR, ENCERRANTE, ENCERRANTEANTERIOR, IDENT1, IDENT2,\n"
                    + "B.SEQBICO,B.IDENTBICO, B.NOMEBICO,B.SEQCOMBUSTIVEL,B.IDCBC, B.SITBICO, B.ENCERRANTEINICIAL, \n"
                    + "C.NOMECOMBUSTIVEL,C.IDENTCOMBUSTIVEL, C.SITCOMBUSTIVEL, \n"
                    + "O.*,O.SITOPERADOR, \n"
                    + "V.SEQVEICULO, V.IDENTVEICULO, V.NOMEVEICULO, V.FLEX, V.PLACA, V.TIPO, V.LIMITE, V.VALORINICIAL, V.SITVEICULO, \n"
                    + "CV.SEQCOMBUSTIVEL AS SEQCOMBUSTIVELVEICULO, CV.IDENTCOMBUSTIVEL AS IDENTCOMBUSTIVELVEICULO, CV.SITCOMBUSTIVEL AS COMBVEICULOSITUACAO,CV.NOMECOMBUSTIVEL AS NOMECOMBUSTIVELVEICULO, \n"
                    + "F.*,\n"
                    + "M.*,\n"
                    + "E.*\n"
                    + "FROM ABASTECIMENTOS A   \n"
                    + "LEFT JOIN BICOS B ON A.SEQBICO = B.SEQBICO   \n"
                    + "LEFT JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN OPERADORES O ON A.SEQOPERADOR = O.SEQOPERADOR   \n"
                    + "LEFT JOIN VEICULOS V ON A.SEQVEICULO = V.SEQVEICULO   \n"
                    + "LEFT JOIN COMBUSTIVEIS CV ON V.SEQCOMBUSTIVEL = CV.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA \n"
                    + "LEFT JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA \n"
                    + "LEFT JOIN MOTORISTAS M ON A.SEQMOTORISTA = M.SEQMOTORISTA \n"
                    + "WHERE DATAHORA >= '" + formato.format(dtInicial) + " 00:00:00' AND DATAHORA <= '" + formato.format(dtFinal) + " 23:59:59' "
                    + " ORDER BY IDABASTEC DESC";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                Combustivel cb = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Bico b = new Bico(rs.getInt("SEQBICO"), rs.getString("IDENTBICO"), rs.getString("NOMEBICO"), cb, rs.getString("IDCBC"), rs.getString("SITBICO"), rs.getDouble("ENCERRANTEINICIAL"));

                Combustivel cv = new Combustivel(rs.getInt("SEQCOMBUSTIVELVEICULO"), rs.getString("IDENTCOMBUSTIVELVEICULO"), rs.getString("NOMECOMBUSTIVELVEICULO"), rs.getString("COMBVEICULOSITUACAO"));
                Veiculo v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), cv, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));
                v.setCombustivel(cv);

                Motorista m = new Motorista(rs.getInt("SEQMOTORISTA"), rs.getString("IDENTMOTORISTA"), rs.getString("NOMEMOTORISTA"), rs.getString("SITMOTORISTA"));

                Operador o = new Operador(rs.getInt("SEQOPERADOR"), rs.getString("IDENTOPERADOR"), rs.getString("NOMEOPERADOR"), rs.getString("LOGIN"), rs.getString("SENHA"), rs.getString("SITOPERADOR"), rs.getString("OPERADOR"), e);
                Abastecimento abastec = new Abastecimento();
                abastec.setIdAbastecimento(rs.getInt("IDABASTEC"));
                abastec.setVolume(rs.getDouble("VOLUME"));
                abastec.setTempoAbastecimento(rs.getTime("TEMPOABASTECIMENTO"));
                abastec.setDataHora(rs.getTimestamp("DATAHORA"));
                abastec.setEncerranteLitros(rs.getDouble("ENCERRANTE"));
                abastec.setEncerranteAnterior(rs.getDouble("ENCERRANTEANTERIOR"));
                abastec.setTempoMedio(rs.getDouble("TEMPOMEDIO"));
                abastec.setKmMedio(rs.getDouble("KMMEDIO"));
                abastec.setHodometro(rs.getLong("HODOMETRO"));
                abastec.setHorimetro(rs.getLong("HORIMETRO"));
                abastec.setNumeroAbastecimento(rs.getInt("NUMEROABASTECIMENTO"));

                abastec.setHodometroAnterior(rs.getLong("HODOMETRO_ANTERIOR"));
                abastec.setHorimetroAnterior(rs.getLong("HORIMETRO_ANTERIOR"));
                abastec.setVeiculo(v);
                abastec.setBico(b);
                abastec.setOperador(o);
                abastec.setMotorista(m);
                abastec.setIdent1(rs.getString("IDENT1"));
                abastec.setIdent2(rs.getString("IDENT2"));

                abastecimentos.add(abastec);
            }
            return abastecimentos;
        } catch (Exception e) {
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return null;
        }
    }

    public static ArrayList<Abastecimento> buscaAbastecimento(Date dtInicial, Date dtFinal, Veiculo veiculo, Frota frota, Empresa empresa) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy");

            GlobalParameter.getInstance();
            Connection con = GlobalParameter.getConn();
            String where = "";
            if (veiculo != null) {
                where = " AND V.SEQVEICULO = " + veiculo.getSeqVeiculo();
            }
            if (frota != null) {
                where = where + " AND F.SEQFROTA = " + frota.getSeqFrota();
            }
            if (empresa != null) {
                where = where + " AND E.SEQEMPRESA = " + empresa.getSeqEmpresa();
            }

            ArrayList<Abastecimento> abastecimentos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql;

            sql = "SELECT "
                    + " IDABASTEC,STRINGCBC,DATAHORA,TOTALPAGAR,PRECOUNITARIO,VOLUME,TEMPOABASTECIMENTO,NUMEROABASTECIMENTO,KMMEDIO,TEMPOMEDIO,HODOMETRO,HODOMETRO_ANTERIOR, HORIMETRO, HORIMETRO_ANTERIOR, ENCERRANTE, ENCERRANTEANTERIOR, IDENT1, IDENT2,\n"
                    + "B.SEQBICO,B.IDENTBICO, B.NOMEBICO,B.SEQCOMBUSTIVEL,B.IDCBC, B.SITBICO, B.ENCERRANTEINICIAL, \n"
                    + "C.NOMECOMBUSTIVEL,C.IDENTCOMBUSTIVEL, C.SITCOMBUSTIVEL, \n"
                    + "O.*,O.SITOPERADOR, \n"
                    + "V.SEQVEICULO, V.IDENTVEICULO, V.NOMEVEICULO, V.FLEX, V.PLACA, V.TIPO, V.LIMITE, V.VALORINICIAL, V.SITVEICULO, \n"
                    + "CV.SEQCOMBUSTIVEL AS SEQCOMBUSTIVELVEICULO, CV.IDENTCOMBUSTIVEL AS IDENTCOMBUSTIVELVEICULO, CV.SITCOMBUSTIVEL AS COMBVEICULOSITUACAO,CV.NOMECOMBUSTIVEL AS NOMECOMBUSTIVELVEICULO, \n"
                    + "F.*,\n"
                    + "M.*,\n"
                    + "E.*\n"
                    + "FROM ABASTECIMENTOS A   \n"
                    + "LEFT JOIN BICOS B ON A.SEQBICO = B.SEQBICO   \n"
                    + "LEFT JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN OPERADORES O ON A.SEQOPERADOR = O.SEQOPERADOR   \n"
                    + "LEFT JOIN VEICULOS V ON A.SEQVEICULO = V.SEQVEICULO   \n"
                    + "LEFT JOIN COMBUSTIVEIS CV ON V.SEQCOMBUSTIVEL = CV.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA \n"
                    + "LEFT JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA \n"
                    + "LEFT JOIN MOTORISTAS M ON A.SEQMOTORISTA = M.SEQMOTORISTA \n"
                    + "WHERE DATAHORA >= '" + formato.format(dtInicial) + " 00:00:00' AND DATAHORA <= '" + formato.format(dtFinal) + " 23:59:59' " + where
                    + " ORDER BY IDABASTEC DESC";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                Combustivel cb = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Bico b = new Bico(rs.getInt("SEQBICO"), rs.getString("IDENTBICO"), rs.getString("NOMEBICO"), cb, rs.getString("IDCBC"), rs.getString("SITBICO"), rs.getDouble("ENCERRANTEINICIAL"));

                Combustivel cv = new Combustivel(rs.getInt("SEQCOMBUSTIVELVEICULO"), rs.getString("IDENTCOMBUSTIVELVEICULO"), rs.getString("NOMECOMBUSTIVELVEICULO"), rs.getString("COMBVEICULOSITUACAO"));
                Veiculo v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), cv, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));
                v.setCombustivel(cv);

                Motorista m = new Motorista(rs.getInt("SEQMOTORISTA"), rs.getString("IDENTMOTORISTA"), rs.getString("NOMEMOTORISTA"), rs.getString("SITMOTORISTA"));

                Operador o = new Operador(rs.getInt("SEQOPERADOR"), rs.getString("IDENTOPERADOR"), rs.getString("NOMEOPERADOR"), rs.getString("LOGIN"), rs.getString("SENHA"), rs.getString("SITOPERADOR"), rs.getString("OPERADOR"), e);
                Abastecimento abastec = new Abastecimento();
                abastec.setIdAbastecimento(rs.getInt("IDABASTEC"));
                abastec.setVolume(rs.getDouble("VOLUME"));
                abastec.setTempoAbastecimento(rs.getTime("TEMPOABASTECIMENTO"));
                abastec.setDataHora(rs.getTimestamp("DATAHORA"));
                abastec.setEncerranteLitros(rs.getDouble("ENCERRANTE"));
                abastec.setEncerranteAnterior(rs.getDouble("ENCERRANTEANTERIOR"));
                abastec.setTempoMedio(rs.getDouble("TEMPOMEDIO"));
                abastec.setKmMedio(rs.getDouble("KMMEDIO"));
                abastec.setHodometro(rs.getLong("HODOMETRO"));
                abastec.setHorimetro(rs.getLong("HORIMETRO"));
                abastec.setNumeroAbastecimento(rs.getInt("NUMEROABASTECIMENTO"));

                abastec.setHodometroAnterior(rs.getLong("HODOMETRO_ANTERIOR"));
                abastec.setHorimetroAnterior(rs.getLong("HORIMETRO_ANTERIOR"));
                abastec.setVeiculo(v);
                abastec.setBico(b);
                abastec.setOperador(o);
                abastec.setMotorista(m);
                abastec.setIdent1(rs.getString("IDENT1"));
                abastec.setIdent2(rs.getString("IDENT2"));
                abastecimentos.add(abastec);
            }
            return abastecimentos;
        } catch (Exception e) {
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return null;
        }
    }

    public static ArrayList<Abastecimento> buscaAbastecimentos(Integer limiteRegistros) {
        try {
            GlobalParameter.getInstance();
            Connection con = GlobalParameter.getConn();

            ArrayList<Abastecimento> abastecimentos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql;
            String limite = "";

            if (limiteRegistros > 0) {
                limite = "FIRST " + limiteRegistros;
            }
            sql = "SELECT " + limite
                    + " IDABASTEC,STRINGCBC,DATAHORA,TOTALPAGAR,PRECOUNITARIO,VOLUME,TEMPOABASTECIMENTO,NUMEROABASTECIMENTO,KMMEDIO,TEMPOMEDIO,HODOMETRO,HORIMETRO, ENCERRANTE, HORIMETRO_ANTERIOR, HODOMETRO_ANTERIOR, ENCERRANTEANTERIOR, IDENT1, IDENT2,\n"
                    + "B.SEQBICO,B.IDENTBICO, B.NOMEBICO,B.SEQCOMBUSTIVEL,B.IDCBC, B.SITBICO, B.ENCERRANTEINICIAL, \n"
                    + "C.NOMECOMBUSTIVEL,C.IDENTCOMBUSTIVEL, C.SITCOMBUSTIVEL, \n"
                    + "O.*,O.SITOPERADOR, \n"
                    + "V.SEQVEICULO, V.IDENTVEICULO, V.NOMEVEICULO, V.FLEX, V.PLACA, V.TIPO, V.LIMITE, V.VALORINICIAL, V.SITVEICULO, \n"
                    + "CV.SEQCOMBUSTIVEL AS SEQCOMBUSTIVELVEICULO, CV.IDENTCOMBUSTIVEL AS IDENTCOMBUSTIVELVEICULO, CV.SITCOMBUSTIVEL AS COMBVEICULOSITUACAO,CV.NOMECOMBUSTIVEL AS NOMECOMBUSTIVELVEICULO, \n"
                    + "F.*,\n"
                    + "M.*,\n"
                    + "E.*\n"
                    + "FROM ABASTECIMENTOS A   \n"
                    + "LEFT JOIN BICOS B ON A.SEQBICO = B.SEQBICO   \n"
                    + "LEFT JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN OPERADORES O ON A.SEQOPERADOR = O.SEQOPERADOR   \n"
                    + "LEFT JOIN VEICULOS V ON A.SEQVEICULO = V.SEQVEICULO   \n"
                    + "LEFT JOIN COMBUSTIVEIS CV ON V.SEQCOMBUSTIVEL = CV.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA \n"
                    + "LEFT JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA \n"
                    + "LEFT JOIN MOTORISTAS M ON A.SEQMOTORISTA = M.SEQMOTORISTA \n"
                    + "WHERE A.SEQOPERADOR IS NOT NULL AND A.SEQBICO IS NOT NULL\n"
                    + "ORDER BY DATAHORA DESC";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                Combustivel cb = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Bico b = new Bico(rs.getInt("SEQBICO"), rs.getString("IDENTBICO"), rs.getString("NOMEBICO"), cb, rs.getString("IDCBC"), rs.getString("SITBICO"), rs.getDouble("ENCERRANTEINICIAL"));

                Combustivel cv = new Combustivel(rs.getInt("SEQCOMBUSTIVELVEICULO"), rs.getString("IDENTCOMBUSTIVELVEICULO"), rs.getString("NOMECOMBUSTIVELVEICULO"), rs.getString("COMBVEICULOSITUACAO"));
                Veiculo v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), cv, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));
                v.setCombustivel(cv);
                Motorista m = new Motorista(rs.getInt("SEQMOTORISTA"), rs.getString("IDENTMOTORISTA"), rs.getString("NOMEMOTORISTA"), rs.getString("SITMOTORISTA"));

                Operador o = new Operador(rs.getInt("SEQOPERADOR"), rs.getString("IDENTOPERADOR"), rs.getString("NOMEOPERADOR"), rs.getString("LOGIN"), rs.getString("SENHA"), rs.getString("SITOPERADOR"), rs.getString("OPERADOR"), e);
                Abastecimento abastec = new Abastecimento();
                abastec.setIdAbastecimento(rs.getInt("IDABASTEC"));
                abastec.setVolume(rs.getDouble("VOLUME"));
                abastec.setTempoAbastecimento(rs.getTime("TEMPOABASTECIMENTO"));
                abastec.setDataHora(rs.getTimestamp("DATAHORA"));
                abastec.setEncerranteLitros(rs.getDouble("ENCERRANTE"));
                abastec.setEncerranteAnterior(rs.getDouble("ENCERRANTEANTERIOR"));
                abastec.setNumeroAbastecimento(rs.getInt("NUMEROABASTECIMENTO"));
                abastec.setHodometro(rs.getLong("HODOMETRO"));
                abastec.setHorimetro(rs.getLong("HORIMETRO"));

                abastec.setHodometroAnterior(rs.getLong("HODOMETRO_ANTERIOR"));
                abastec.setHorimetroAnterior(rs.getLong("HORIMETRO_ANTERIOR"));

                abastec.setVeiculo(v);
                abastec.setBico(b);
                abastec.setOperador(o);
                abastec.setMotorista(m);
                abastec.setIdent1(rs.getString("IDENT1"));
                abastec.setIdent2(rs.getString("IDENT2"));
                abastecimentos.add(abastec);
            }
            return abastecimentos;
        } catch (Exception e) {
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return null;
        }
    }

    public static void salvaAbastecimento(Bico bico, Veiculo v, Operador o, Long marcador, Abastecimento a) {
        Statement stmt = null;
        try {
            Connection conn = GlobalParameter.getConn();
            stmt = conn.createStatement();
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            String sql = "INSERT INTO ABASTECIMENTOS (SEQBICO, SEQVEICULO, SEQOPERADOR, SEQMOTORISTA, STRINGCBC, ENCERRANTE, TEMPOABASTECIMENTO, DATAHORA, NUMEROABASTECIMENTO, PRECOUNITARIO, VOLUME, HODOMETRO, HORIMETRO, HODOMETRO_ANTERIOR, HORIMETRO_ANTERIOR, KMMEDIO, TEMPOMEDIO, TOTALPAGAR, ENCERRANTEANTERIOR, IDENT1, IDENT2) VALUES ("
                    + bico.getSeqBico()
                    + ","
                    + (v != null ? v.getSeqVeiculo() : null)
                    + ","
                    + (o != null ? o.getSeqOperador() : null)
                    + ","
                    + (a.getMotorista() != null ? a.getMotorista().getSeqMotorista() : null)
                    + ",'"
                    + a.getStringCBC()
                    + "',"
                    + a.getEncerranteLitros()
                    + ",'"
                    + a.getTempoAbastecimento()
                    + "','"
                    + formato.format(a.getDataHora())
                    + "',"
                    + a.getNumeroAbastecimento()
                    + ","
                    + a.getPrecoUnitario()
                    + ","
                    + a.getVolume()
                    + ","
                    + (v != null ? (v.getTipo().equals("O") ? marcador : 0) : null)
                    + ","
                    + (v != null ? (v.getTipo().equals("H") ? marcador : 0) : null)
                    + ","
                    + (v != null ? (v.getTipo().equals("O") ? a.getHodometroAnterior() : 0) : null)
                    + ","
                    + (v != null ? (v.getTipo().equals("H") ? a.getHorimetroAnterior() : 0) : null)
                    + ","
                    + (v != null ? (v.getTipo().equals("O") ? a.getKmMedio() : 0) : null)
                    + ","
                    + (v != null ? (v.getTipo().equals("H") ? a.getTempoMedio() : 0) : null)
                    + ","
                    + a.getTotalPagar()
                    + ","
                    + a.getEncerranteAnterior()
                    + ",'"
                    + a.getIdent1()
                    + "','"
                    + a.getIdent2()
                    + "')";

            stmt.executeUpdate(sql);
            EstoqueDB.gravaSaldoEstoque("S", bico.getCombustivel().getSeqCombustivel(), a.getVolume(), (v != null ? "SAÍDA PARA ABASTECIMENTO --> VEÍCULO: " + v.getNomeVeiculo() : "SAÍDA PARA ABASTECIMENTO"));
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
                    GeraLog g = new GeraLog();
                    g.gravaErro(e);
                    g.close();
                }
            }
        }
    }

    public static boolean salvaAbastecimentoSimpl(Abastecimento a) {
        Connection conn = GlobalParameter.getConn();
        Statement stmt = null;
        Integer idBico = null;
        Integer idVeiculo = null;
        Integer idOperador = null;
        Integer idMotorista = null;
        String vTipo = "INDEFINIDO";
        try {
            if (a.getBico() != null) {
                idBico = a.getBico().getSeqBico();
            }
            if (a.getVeiculo() != null) {
                idVeiculo = a.getVeiculo().getSeqVeiculo();
                vTipo = a.getVeiculo().getTipo();
            }
            if (a.getOperador() != null) {
                idOperador = a.getOperador().getSeqOperador();
            }
            if (a.getMotorista() != null) {
                idMotorista = a.getMotorista().getSeqMotorista();
            }

            stmt = conn.createStatement();
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            String sql = "INSERT INTO ABASTECIMENTOS (SEQBICO, SEQOPERADOR, SEQVEICULO, SEQMOTORISTA, STRINGCBC, ENCERRANTE, TEMPOABASTECIMENTO, DATAHORA, NUMEROABASTECIMENTO, PRECOUNITARIO, VOLUME, HODOMETRO, HORIMETRO, HODOMETRO_ANTERIOR, HORIMETRO_ANTERIOR, KMMEDIO, TEMPOMEDIO, TOTALPAGAR, ENCERRANTEANTERIOR, IDENT1, IDENT2) VALUES ("
                    + idBico
                    + ","
                    + idOperador
                    + ","
                    + idVeiculo
                    + ","
                    + idMotorista
                    + ",'"
                    + a.getStringCBC()
                    + "',"
                    + a.getEncerranteLitros()
                    + ",'"
                    + a.getTempoAbastecimento()
                    + "','"
                    + formato.format(a.getDataHora())
                    + "',"
                    + a.getNumeroAbastecimento()
                    + ","
                    + a.getPrecoUnitario()
                    + ","
                    + a.getVolume()
                    + ","
                    + (vTipo.equals("O") ? a.getHodometro() : 0)
                    + ","
                    + (vTipo.equals("H") ? a.getHorimetro() : 0)
                    + ","
                    + (vTipo.equals("O") ? a.getHodometroAnterior() : 0)
                    + ","
                    + (vTipo.equals("H") ? a.getHorimetroAnterior() : 0)
                    + ","
                    + (vTipo.equals("O") ? a.getKmMedio() : 0)
                    + ","
                    + (vTipo.equals("H") ? a.getTempoMedio() : 0)
                    + ","
                    + a.getTotalPagar()
                    + ","
                    + a.getEncerranteAnterior()
                    + ",'"
                    + a.getIdent1()
                    + "','"
                    + a.getIdent2()
                    + "')";

            stmt.executeUpdate(sql);
            //GeraLog g = new GeraLog();
            //g.gravaMensagensLog(new Throwable(sql));
            //g.close();
            boolean b = true;
            //if ((a.getBico() != null) && (a.getVeiculo() != null)) {
            if (a.getBico() != null) {
                EstoqueDB.gravaSaldoEstoque("S", a.getBico().getCombustivel().getSeqCombustivel(), a.getVolume(), (idVeiculo != null ? "SAÍDA PARA ABASTECIMENTO --> VEÍCULO: " + a.getVeiculo().getNomeVeiculo() : "SAÍDA PARA ABASTECIMENTO"));
            }
            return b;
        } catch (Exception e) {
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
                }
            }
        }
    }

    public static List<Abastecimento> buscaAbastecimentosManual(String tipo) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            ArrayList<Abastecimento> abastecimentos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql;

            sql = "SELECT TIPOABASTEC,IDABASTEC,STRINGCBC,DATAHORA,TOTALPAGAR,PRECOUNITARIO,VOLUME,TEMPOABASTECIMENTO,NUMEROABASTECIMENTO,KMMEDIO,TEMPOMEDIO,HODOMETRO,HORIMETRO, ENCERRANTE, IDENT1, IDENT2,\n"
                    + "B.SEQBICO,B.IDENTBICO, B.NOMEBICO,B.SEQCOMBUSTIVEL,B.IDCBC, B.SITBICO, B.ENCERRANTEINICIAL, \n"
                    + "C.NOMECOMBUSTIVEL,C.IDENTCOMBUSTIVEL, C.SITCOMBUSTIVEL, \n"
                    + "O.*,O.SITOPERADOR, \n"
                    + "V.SEQVEICULO, V.IDENTVEICULO, V.NOMEVEICULO, V.FLEX, V.PLACA, V.TIPO, V.LIMITE, V.VALORINICIAL, V.SITVEICULO, \n"
                    + "CV.SEQCOMBUSTIVEL AS SEQCOMBUSTIVELVEICULO, CV.IDENTCOMBUSTIVEL AS IDENTCOMBUSTIVELVEICULO, CV.SITCOMBUSTIVEL AS COMBVEICULOSITUACAO,CV.NOMECOMBUSTIVEL AS NOMECOMBUSTIVELVEICULO, \n"
                    + "F.*,\n"
                    + "E.*\n"
                    + "FROM ABASTECIMENTOS A   \n"
                    + "LEFT JOIN BICOS B ON A.SEQBICO = B.SEQBICO   \n"
                    + "LEFT JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN OPERADORES O ON A.SEQOPERADOR = O.SEQOPERADOR   \n"
                    + "LEFT JOIN VEICULOS V ON A.SEQVEICULO = V.SEQVEICULO   \n"
                    + "LEFT JOIN COMBUSTIVEIS CV ON V.SEQCOMBUSTIVEL = CV.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA \n"
                    + "LEFT JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA \n"
                    + "WHERE TIPOABASTEC='" + tipo + "' \n"
                    + "ORDER BY IDABASTEC DESC";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                Combustivel cb = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Bico b = new Bico(rs.getInt("SEQBICO"), rs.getString("IDENTBICO"), rs.getString("NOMEBICO"), cb, rs.getString("IDCBC"), rs.getString("SITBICO"), rs.getDouble("ENCERRANTEINICIAL"));

                Combustivel cv = new Combustivel(rs.getInt("SEQCOMBUSTIVELVEICULO"), rs.getString("IDENTCOMBUSTIVELVEICULO"), rs.getString("NOMECOMBUSTIVELVEICULO"), rs.getString("COMBVEICULOSITUACAO"));
                Veiculo v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), cv, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));
                v.setCombustivel(cv);

                Operador o = new Operador(rs.getInt("SEQOPERADOR"), rs.getString("IDENTOPERADOR"), rs.getString("NOMEOPERADOR"), rs.getString("LOGIN"), rs.getString("SENHA"), rs.getString("SITOPERADOR"), rs.getString("OPERADOR"), e);
                Abastecimento abastec = new Abastecimento();
                abastec.setIdAbastecimento(rs.getInt("IDABASTEC"));
                abastec.setVolume(rs.getDouble("VOLUME"));
                abastec.setTempoAbastecimento(rs.getTime("TEMPOABASTECIMENTO"));
                abastec.setDataHora(rs.getTimestamp("DATAHORA"));
                abastec.setEncerranteLitros(rs.getDouble("ENCERRANTE"));
                abastec.setVeiculo(v);
                abastec.setBico(b);
                abastec.setOperador(o);
                abastec.setIdent1(rs.getString("IDENT1"));
                abastec.setIdent2(rs.getString("IDENT2"));
                abastecimentos.add(abastec);
            }
            return abastecimentos;
        } catch (Exception e) {
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return null;
        }
    }

    public static boolean excluirAbastecimento(Integer seqAbastec) {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            Abastecimento a = AbastecimentosDB.buscaAbastecimento(seqAbastec);
            //int res = stmt.executeUpdate("DELETE FROM ABASTECIMENTOS WHERE TIPOABASTEC='M' AND IDABASTEC = " + seqAbastec);
            int res = stmt.executeUpdate("DELETE FROM ABASTECIMENTOS WHERE IDABASTEC = " + seqAbastec);
            EstoqueDB.gravaSaldoEstoque("E", a.getBico().getCombustivel().getSeqCombustivel(), a.getVolume(), (a.getVeiculo() != null ? "DEVOLUÇÃO ABASTECIMENTO MANUAL --> VEÍCULO: " + a.getVeiculo().getNomeVeiculo() : "DEVOLUÇÃO ABASTECIMENTO MANUAL"));
            return res == 1;
        } catch (Exception e) {
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

    public static Abastecimento buscaAbastecimento(Integer idAbastec) {
        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        try {
            Abastecimento abastec = new Abastecimento();
            Statement stmt = con.createStatement();
            String sql;

            sql = "SELECT "
                    + " IDABASTEC,STRINGCBC,DATAHORA,TOTALPAGAR,PRECOUNITARIO,VOLUME,TEMPOABASTECIMENTO,NUMEROABASTECIMENTO,KMMEDIO,TEMPOMEDIO,HODOMETRO,HODOMETRO_ANTERIOR, HORIMETRO, HORIMETRO_ANTERIOR, ENCERRANTE, ENCERRANTEANTERIOR, IDENT1, IDENT2,\n"
                    + "B.SEQBICO,B.IDENTBICO, B.NOMEBICO,B.SEQCOMBUSTIVEL,B.IDCBC, B.SITBICO, B.ENCERRANTEINICIAL,\n"
                    + "C.NOMECOMBUSTIVEL,C.IDENTCOMBUSTIVEL, C.SITCOMBUSTIVEL, \n"
                    + "O.*,O.SITOPERADOR, \n"
                    + "V.SEQVEICULO, V.IDENTVEICULO, V.NOMEVEICULO, V.FLEX, V.PLACA, V.TIPO, V.LIMITE, V.VALORINICIAL, V.SITVEICULO, \n"
                    + "CV.SEQCOMBUSTIVEL AS SEQCOMBUSTIVELVEICULO, CV.IDENTCOMBUSTIVEL AS IDENTCOMBUSTIVELVEICULO, CV.SITCOMBUSTIVEL AS COMBVEICULOSITUACAO,CV.NOMECOMBUSTIVEL AS NOMECOMBUSTIVELVEICULO, \n"
                    + "F.*,\n"
                    + "M.*,\n"
                    + "E.*\n"
                    + "FROM ABASTECIMENTOS A   \n"
                    + "LEFT JOIN BICOS B ON A.SEQBICO = B.SEQBICO   \n"
                    + "LEFT JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN OPERADORES O ON A.SEQOPERADOR = O.SEQOPERADOR   \n"
                    + "LEFT JOIN VEICULOS V ON A.SEQVEICULO = V.SEQVEICULO   \n"
                    + "LEFT JOIN COMBUSTIVEIS CV ON V.SEQCOMBUSTIVEL = CV.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA \n"
                    + "LEFT JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA \n"
                    + "LEFT JOIN MOTORISTAS M ON A.SEQMOTORISTA = M.SEQMOTORISTA \n"
                    + "WHERE IDABASTEC=" + idAbastec + " ORDER BY IDABASTEC DESC";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                Combustivel cb = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Bico b = new Bico(rs.getInt("SEQBICO"), rs.getString("IDENTBICO"), rs.getString("NOMEBICO"), cb, rs.getString("IDCBC"), rs.getString("SITBICO"), rs.getDouble("ENCERRANTEINICIAL"));

                Combustivel cv = new Combustivel(rs.getInt("SEQCOMBUSTIVELVEICULO"), rs.getString("IDENTCOMBUSTIVELVEICULO"), rs.getString("NOMECOMBUSTIVELVEICULO"), rs.getString("COMBVEICULOSITUACAO"));
                Veiculo v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), cv, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));
                v.setCombustivel(cv);

                Motorista m = new Motorista(rs.getInt("SEQMOTORISTA"), rs.getString("IDENTMOTORISTA"), rs.getString("NOMEMOTORISTA"), rs.getString("SITMOTORISTA"));

                Operador o = new Operador(rs.getInt("SEQOPERADOR"), rs.getString("IDENTOPERADOR"), rs.getString("NOMEOPERADOR"), rs.getString("LOGIN"), rs.getString("SENHA"), rs.getString("SITOPERADOR"), rs.getString("OPERADOR"), e);
                abastec = new Abastecimento();
                abastec.setIdAbastecimento(rs.getInt("IDABASTEC"));
                abastec.setVolume(rs.getDouble("VOLUME"));
                abastec.setTempoAbastecimento(rs.getTime("TEMPOABASTECIMENTO"));
                abastec.setDataHora(rs.getTimestamp("DATAHORA"));
                abastec.setHodometro(rs.getLong("HODOMETRO"));
                abastec.setHorimetro(rs.getLong("HORIMETRO"));
                abastec.setNumeroAbastecimento(rs.getInt("NUMEROABASTECIMENTO"));
                abastec.setEncerranteLitros(rs.getDouble("ENCERRANTE"));
                abastec.setEncerranteAnterior(rs.getDouble("ENCERRANTEANTERIOR"));
                abastec.setTempoMedio(rs.getDouble("TEMPOMEDIO"));
                abastec.setKmMedio(rs.getDouble("KMMEDIO"));
                abastec.setPrecoUnitario(rs.getDouble("PRECOUNITARIO"));
                abastec.setHodometroAnterior(rs.getLong("HODOMETRO_ANTERIOR"));
                abastec.setHorimetroAnterior(rs.getLong("HORIMETRO_ANTERIOR"));

                abastec.setVeiculo(v);
                abastec.setBico(b);
                abastec.setOperador(o);
                abastec.setMotorista(m);

                abastec.setIdent1(rs.getString("IDENT1"));
                abastec.setIdent2(rs.getString("IDENT2"));
            }
            return abastec;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean insertOrUpdateAbastecimentoManual(Abastecimento a, boolean baixaEstoque, String tipo) {
        Connection conn = GlobalParameter.getConn();
        Statement stmt = null;
        if (a.getTempoAbastecimento() == null) {
            a.setTempoAbastecimento(new Time(0, 0, 0));
        }

        try {
            stmt = conn.createStatement();
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            String sql = "UPDATE OR INSERT INTO ABASTECIMENTOS (IDABASTEC, SEQBICO, SEQVEICULO, SEQOPERADOR, SEQMOTORISTA, STRINGCBC, ENCERRANTE, TEMPOABASTECIMENTO, DATAHORA, NUMEROABASTECIMENTO, PRECOUNITARIO, VOLUME, HODOMETRO, HORIMETRO, HODOMETRO_ANTERIOR, HORIMETRO_ANTERIOR, KMMEDIO, TEMPOMEDIO, TOTALPAGAR, TIPOABASTEC, ENCERRANTEANTERIOR) VALUES ("
                    + a.getIdAbastecimento()
                    + ","
                    + a.getBico().getSeqBico()
                    + ","
                    + a.getVeiculo().getSeqVeiculo()
                    + ","
                    + a.getOperador().getSeqOperador()
                    + ","
                    + (a.getMotorista() != null ? a.getMotorista().getSeqMotorista() : null)
                    + ",'"
                    + a.getStringCBC()
                    + "',"
                    + a.getEncerranteLitros()
                    + ",'"
                    + a.getTempoAbastecimento()
                    + "','"
                    + formato.format(a.getDataHora())
                    + "',"
                    + a.getNumeroAbastecimento()
                    + ","
                    + a.getPrecoUnitario()
                    + ","
                    + a.getVolume()
                    + ","
                    + (a.getVeiculo().getTipo().equals("O") ? a.getHodometro() : 0)
                    + ","
                    + (a.getVeiculo().getTipo().equals("H") ? a.getHorimetro() : 0)
                    + ","
                    + (a.getVeiculo().getTipo().equals("O") ? a.getHodometroAnterior() : 0)
                    + ","
                    + (a.getVeiculo().getTipo().equals("H") ? a.getHorimetroAnterior() : 0)
                    + ","
                    + (a.getVeiculo().getTipo().equals("O") ? a.getKmMedio() : 0)
                    + ","
                    + (a.getVeiculo().getTipo().equals("H") ? a.getTempoMedio() : 0)
                    + ","
                    + a.getTotalPagar() + ","
                    + "'" + tipo + "',"
                    + a.getEncerranteAnterior()
                    + ")";

            stmt.executeUpdate(sql);
            if (baixaEstoque) {
                EstoqueDB.gravaSaldoEstoque("S", a.getBico().getCombustivel().getSeqCombustivel(), a.getVolume(), (a.getVeiculo() != null ? "SAÍDA PARA ABASTECIMENTO MANUAL --> VEÍCULO: " + a.getVeiculo().getNomeVeiculo() : "SAÍDA PARA ABASTECIMENTO MANUAL"));
            }
            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    GeraLog g = new GeraLog();
                    g.gravaErro(e);
                    g.close();
                    //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
                }
            }
        }
    }

    public static Double buscaEncerranteAnterior(Bico b, Timestamp timestamp) {
        Connection conn = GlobalParameter.getConn();
        Double retorno = 0.0;
        try {
            Statement stmt = conn.createStatement();
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            String sql = "SELECT FIRST 1 ENCERRANTE AS ENCERRANTEANTERIOR FROM ABASTECIMENTOS WHERE DATAHORA < ' " + formato.format(timestamp) + "' AND SEQBICO = " + b.getSeqBico() + " ORDER BY IDABASTEC DESC";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                retorno = rs.getDouble("ENCERRANTEANTERIOR");
            }

            if (retorno <= 0) {
                retorno = b.getEncerrante();
            }
            return retorno;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return retorno;
        }
    }

    public static boolean atualizaMarcador(Integer idAbastec, Long marcadorAtual, Double media, String tipoVeiculo) {
        Connection conn = GlobalParameter.getConn();
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            String sql = "UPDATE OR INSERT INTO ABASTECIMENTOS (IDABASTEC, HODOMETRO, HORIMETRO, KMMEDIO, TEMPOMEDIO) VALUES ("
                    + idAbastec
                    + ","
                    + (tipoVeiculo.equals("O") ? marcadorAtual : 0)
                    + ","
                    + (tipoVeiculo.equals("H") ? marcadorAtual : 0)
                    + ","
                    + (tipoVeiculo.equals("O") ? media : 0)
                    + ","
                    + (tipoVeiculo.equals("H") ? media : 0)
                    + ")";

            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    GeraLog g = new GeraLog();
                    g.gravaErro(e);
                    g.close();
                    //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
                }
            }
        }
    }

    public static boolean editarAbastec(Abastecimento a) {
        Connection conn = GlobalParameter.getConn();
        Statement stmt = null;
        if (a.getTempoAbastecimento() == null) {
            a.setTempoAbastecimento(new Time(0, 0, 0));
        }

        try {
            stmt = conn.createStatement();
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            String sql = "UPDATE OR INSERT INTO ABASTECIMENTOS (IDABASTEC, SEQBICO, SEQVEICULO, SEQOPERADOR, SEQMOTORISTA, ENCERRANTE, DATAHORA, NUMEROABASTECIMENTO, PRECOUNITARIO, VOLUME, HODOMETRO, HORIMETRO, HODOMETRO_ANTERIOR, HORIMETRO_ANTERIOR, KMMEDIO, TEMPOMEDIO, TOTALPAGAR, ENCERRANTEANTERIOR) VALUES ("
                    + a.getIdAbastecimento()
                    + ","
                    + a.getBico().getSeqBico()
                    + ","
                    + a.getVeiculo().getSeqVeiculo()
                    + ","
                    + a.getOperador().getSeqOperador()
                    + ","
                    + (a.getMotorista() != null ? a.getMotorista().getSeqMotorista() : null)
                    + ","
                    + a.getEncerranteLitros()
                    + ",'"
                    + formato.format(a.getDataHora())
                    + "',"
                    + a.getNumeroAbastecimento()
                    + ","
                    + a.getPrecoUnitario()
                    + ","
                    + a.getVolume()
                    + ","
                    + (a.getVeiculo().getTipo().equals("O") ? a.getHodometro() : 0)
                    + ","
                    + (a.getVeiculo().getTipo().equals("H") ? a.getHorimetro() : 0)
                    + ","
                    + (a.getVeiculo().getTipo().equals("O") ? a.getHodometroAnterior() : 0)
                    + ","
                    + (a.getVeiculo().getTipo().equals("H") ? a.getHorimetroAnterior() : 0)
                    + ","
                    + (a.getVeiculo().getTipo().equals("O") ? a.getKmMedio() : 0)
                    + ","
                    + (a.getVeiculo().getTipo().equals("H") ? a.getTempoMedio() : 0)
                    + ","
                    + a.getTotalPagar()
                    + ","
                    + a.getEncerranteAnterior()
                    + ")";

            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    GeraLog g = new GeraLog();
                    g.gravaErro(e);
                    g.close();
                    //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
                }
            }
        }
    }

    public static ArrayList<Abastecimento> buscaAbastExport(Date dtInicial, Date dtFinal, Integer seqEmpresaInicial, Integer seqEmpresaFinal, Integer seqFrotaInicial, Integer seqFrotaFinal, Integer seqVeiculoInicial, Integer seqVeiculoFinal, String tipo1, String tipo2) {

        SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy");

        GlobalParameter.getInstance();
        Connection con = GlobalParameter.getConn();
        String where;
        where = " AND V.SEQVEICULO >= " + seqVeiculoInicial + " AND V.SEQVEICULO <= " + seqVeiculoFinal;
        where = where + " AND F.SEQFROTA >= " + seqFrotaInicial + " AND F.SEQFROTA <= " + seqFrotaFinal;
        where = where + " AND E.SEQEMPRESA >= " + seqEmpresaInicial + " AND E.SEQEMPRESA <= " + seqEmpresaFinal;
        where = where + " AND (V.TIPO = '" + tipo1 + "' OR V.TIPO = '" + tipo2 + "')";

        try {
            ArrayList<Abastecimento> abastecimentos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql;

            sql = "SELECT "
                    + " IDABASTEC,STRINGCBC,TIPOABASTEC,DATAHORA,TOTALPAGAR,PRECOUNITARIO,VOLUME,TEMPOABASTECIMENTO,TOTALPAGAR, PRECOUNITARIO,NUMEROABASTECIMENTO,KMMEDIO,TEMPOMEDIO,HODOMETRO,HODOMETRO_ANTERIOR, HORIMETRO, HORIMETRO_ANTERIOR, ENCERRANTE, ENCERRANTEANTERIOR, IDENT1, IDENT2,\n"
                    + "B.SEQBICO,B.IDENTBICO, B.NOMEBICO,B.SEQCOMBUSTIVEL,B.IDCBC, B.SITBICO, B.ENCERRANTEINICIAL, \n"
                    + "C.NOMECOMBUSTIVEL,C.IDENTCOMBUSTIVEL, C.SITCOMBUSTIVEL, \n"
                    + "O.*,O.SITOPERADOR, \n"
                    + "V.SEQVEICULO, V.IDENTVEICULO, V.NOMEVEICULO, V.FLEX, V.PLACA, V.TIPO, V.LIMITE, V.VALORINICIAL, V.SITVEICULO, \n"
                    + "CV.SEQCOMBUSTIVEL AS SEQCOMBUSTIVELVEICULO, CV.IDENTCOMBUSTIVEL AS IDENTCOMBUSTIVELVEICULO, CV.SITCOMBUSTIVEL AS COMBVEICULOSITUACAO,CV.NOMECOMBUSTIVEL AS NOMECOMBUSTIVELVEICULO, \n"
                    + "F.*,\n"
                    + "M.*,\n"
                    + "E.*\n"
                    + "FROM ABASTECIMENTOS A   \n"
                    + "LEFT JOIN BICOS B ON A.SEQBICO = B.SEQBICO   \n"
                    + "LEFT JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN OPERADORES O ON A.SEQOPERADOR = O.SEQOPERADOR   \n"
                    + "LEFT JOIN VEICULOS V ON A.SEQVEICULO = V.SEQVEICULO   \n"
                    + "LEFT JOIN COMBUSTIVEIS CV ON V.SEQCOMBUSTIVEL = CV.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA \n"
                    + "LEFT JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA \n"
                    + "LEFT JOIN MOTORISTAS M ON A.SEQMOTORISTA = M.SEQMOTORISTA \n"
                    + "WHERE DATAHORA >= '" + formato.format(dtInicial) + " 00:00:00' AND DATAHORA <= '" + formato.format(dtFinal) + " 23:59:59' " + where
                    + " ORDER BY NUMEROABASTECIMENTO DESC";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                Combustivel cb = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Bico b = new Bico(rs.getInt("SEQBICO"), rs.getString("IDENTBICO"), rs.getString("NOMEBICO"), cb, rs.getString("IDCBC"), rs.getString("SITBICO"), rs.getDouble("ENCERRANTEINICIAL"));

                Combustivel cv = new Combustivel(rs.getInt("SEQCOMBUSTIVELVEICULO"), rs.getString("IDENTCOMBUSTIVELVEICULO"), rs.getString("NOMECOMBUSTIVELVEICULO"), rs.getString("COMBVEICULOSITUACAO"));
                Veiculo v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), cv, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));
                v.setCombustivel(cv);

                Motorista m = new Motorista(rs.getInt("SEQMOTORISTA"), rs.getString("IDENTMOTORISTA"), rs.getString("NOMEMOTORISTA"), rs.getString("SITMOTORISTA"));

                Operador o = new Operador(rs.getInt("SEQOPERADOR"), rs.getString("IDENTOPERADOR"), rs.getString("NOMEOPERADOR"), rs.getString("LOGIN"), rs.getString("SENHA"), rs.getString("SITOPERADOR"), rs.getString("OPERADOR"), e);
                Abastecimento abastec = new Abastecimento();
                abastec.setIdAbastecimento(rs.getInt("IDABASTEC"));
                abastec.setVolume(rs.getDouble("VOLUME"));
                abastec.setTempoAbastecimento(rs.getTime("TEMPOABASTECIMENTO"));
                abastec.setDataHora(rs.getTimestamp("DATAHORA"));
                abastec.setEncerranteLitros(rs.getDouble("ENCERRANTE"));
                abastec.setEncerranteAnterior(rs.getDouble("ENCERRANTEANTERIOR"));
                abastec.setTempoMedio(rs.getDouble("TEMPOMEDIO"));
                abastec.setKmMedio(rs.getDouble("KMMEDIO"));
                abastec.setHodometro(rs.getLong("HODOMETRO"));
                abastec.setHorimetro(rs.getLong("HORIMETRO"));
                abastec.setNumeroAbastecimento(rs.getInt("NUMEROABASTECIMENTO"));
                abastec.setPrecoUnitario(rs.getDouble("PRECOUNITARIO"));
                abastec.setHodometroAnterior(rs.getLong("HODOMETRO_ANTERIOR"));
                abastec.setHorimetroAnterior(rs.getLong("HORIMETRO_ANTERIOR"));
                abastec.setTotalPagar(rs.getDouble("TOTALPAGAR"));
                abastec.setVeiculo(v);
                abastec.setBico(b);
                abastec.setOperador(o);
                if (m.getNomeMotorista() == null) {
                    abastec.setMotorista(null);
                } else {
                    abastec.setMotorista(m);
                }
                abastec.setIdent1(rs.getString("IDENT1"));
                abastec.setIdent2(rs.getString("IDENT2"));
                abastec.setTipoAbastec(rs.getString("TIPOABASTEC"));
                abastec.setStringCBC(rs.getString("STRINGCBC"));
                abastecimentos.add(abastec);
            }
            return abastecimentos;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static boolean atualizaIdentOperador(Operador o, String ident) {
        Connection conn = GlobalParameter.getConn();
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            String sql = "UPDATE ABASTECIMENTOS\n"
                    + " SET SEQOPERADOR = " + o.getSeqOperador() + " WHERE IDENT1 = '" + ident + "' AND (SEQOPERADOR = 0 OR SEQOPERADOR IS NULL);";

            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    GeraLog g = new GeraLog();
                    g.gravaErro(e);
                    g.close();
                    //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
                }
            }
        }
    }

    public static boolean atualizaIdentVeiculo(Veiculo v, String ident) {
        Connection conn = GlobalParameter.getConn();
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            String sql = "UPDATE ABASTECIMENTOS\n"
                    + " SET SEQVEICULO = " + v.getSeqVeiculo() + " WHERE IDENT2 = '" + ident + "' AND (SEQVEICULO = 0 OR SEQVEICULO IS NULL);";

            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    GeraLog g = new GeraLog();
                    g.gravaErro(e);
                    g.close();
                    //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
                }
            }
        }
    }

    public static ArrayList<Abastecimento> buscaAbastecimentoInconsistente() {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy");

            GlobalParameter.getInstance();
            Connection con = GlobalParameter.getConn();
            String where = "";

            ArrayList<Abastecimento> abastecimentos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String sql;

            sql = "SELECT "
                    + " IDABASTEC,STRINGCBC,DATAHORA,TOTALPAGAR,PRECOUNITARIO,VOLUME,TEMPOABASTECIMENTO,NUMEROABASTECIMENTO,KMMEDIO,TEMPOMEDIO,HODOMETRO,HODOMETRO_ANTERIOR, HORIMETRO, HORIMETRO_ANTERIOR, ENCERRANTE, ENCERRANTEANTERIOR, IDENT1, IDENT2,\n"
                    + "B.SEQBICO,B.IDENTBICO, B.NOMEBICO,B.SEQCOMBUSTIVEL,B.IDCBC, B.SITBICO, B.ENCERRANTEINICIAL, \n"
                    + "C.NOMECOMBUSTIVEL,C.IDENTCOMBUSTIVEL, C.SITCOMBUSTIVEL, \n"
                    + "O.*,O.SITOPERADOR, \n"
                    + "V.SEQVEICULO, V.IDENTVEICULO, V.NOMEVEICULO, V.FLEX, V.PLACA, V.TIPO, V.LIMITE, V.VALORINICIAL, V.SITVEICULO, \n"
                    + "CV.SEQCOMBUSTIVEL AS SEQCOMBUSTIVELVEICULO, CV.IDENTCOMBUSTIVEL AS IDENTCOMBUSTIVELVEICULO, CV.SITCOMBUSTIVEL AS COMBVEICULOSITUACAO,CV.NOMECOMBUSTIVEL AS NOMECOMBUSTIVELVEICULO, \n"
                    + "F.*,\n"
                    + "M.*,\n"
                    + "E.*\n"
                    + "FROM ABASTECIMENTOS A   \n"
                    + "LEFT JOIN BICOS B ON A.SEQBICO = B.SEQBICO   \n"
                    + "LEFT JOIN COMBUSTIVEIS C ON B.SEQCOMBUSTIVEL = C.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN OPERADORES O ON A.SEQOPERADOR = O.SEQOPERADOR   \n"
                    + "LEFT JOIN VEICULOS V ON A.SEQVEICULO = V.SEQVEICULO   \n"
                    + "LEFT JOIN COMBUSTIVEIS CV ON V.SEQCOMBUSTIVEL = CV.SEQCOMBUSTIVEL   \n"
                    + "LEFT JOIN FROTAS F ON F.SEQFROTA = V.SEQFROTA \n"
                    + "LEFT JOIN EMPRESAS E ON E.SEQEMPRESA = F.SEQEMPRESA \n"
                    + "LEFT JOIN MOTORISTAS M ON A.SEQMOTORISTA = M.SEQMOTORISTA \n"
                    + "WHERE (HORIMETRO_ANTERIOR>HORIMETRO AND HORIMETRO_ANTERIOR>0 AND HORIMETRO>0) OR (HODOMETRO_ANTERIOR>HODOMETRO AND HODOMETRO_ANTERIOR>0 AND HODOMETRO>0) \n"
                    + " ORDER BY IDABASTEC DESC";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empresa e = new Empresa(rs.getInt("SEQEMPRESA"), rs.getString("IDENTEMPRESA"), rs.getString("NOMEEMPRESA"), rs.getString("ENDERECOCOMPLETO"), rs.getString("SITEMPRESA"));
                Frota f = new Frota(rs.getInt("SEQFROTA"), rs.getString("IDENTFROTA"), rs.getString("NOMEFROTA"), e, rs.getString("SITFROTA"), rs.getDouble("LIMITEFROTA"), rs.getDouble("DISPONIVEL"));
                Combustivel cb = new Combustivel(rs.getInt("SEQCOMBUSTIVEL"), rs.getString("IDENTCOMBUSTIVEL"), rs.getString("NOMECOMBUSTIVEL"), rs.getString("SITCOMBUSTIVEL"));
                Bico b = new Bico(rs.getInt("SEQBICO"), rs.getString("IDENTBICO"), rs.getString("NOMEBICO"), cb, rs.getString("IDCBC"), rs.getString("SITBICO"), rs.getDouble("ENCERRANTEINICIAL"));

                Combustivel cv = new Combustivel(rs.getInt("SEQCOMBUSTIVELVEICULO"), rs.getString("IDENTCOMBUSTIVELVEICULO"), rs.getString("NOMECOMBUSTIVELVEICULO"), rs.getString("COMBVEICULOSITUACAO"));
                Veiculo v = new Veiculo(rs.getInt("SEQVEICULO"), rs.getString("IDENTVEICULO"), rs.getString("NOMEVEICULO"), rs.getString("TIPO"), cv, f, rs.getString("SITVEICULO"), rs.getDouble("LIMITE"), rs.getLong("VALORINICIAL"));
                v.setCombustivel(cv);

                Motorista m = new Motorista(rs.getInt("SEQMOTORISTA"), rs.getString("IDENTMOTORISTA"), rs.getString("NOMEMOTORISTA"), rs.getString("SITMOTORISTA"));

                Operador o = new Operador(rs.getInt("SEQOPERADOR"), rs.getString("IDENTOPERADOR"), rs.getString("NOMEOPERADOR"), rs.getString("LOGIN"), rs.getString("SENHA"), rs.getString("SITOPERADOR"), rs.getString("OPERADOR"), e);
                Abastecimento abastec = new Abastecimento();
                abastec.setIdAbastecimento(rs.getInt("IDABASTEC"));
                abastec.setVolume(rs.getDouble("VOLUME"));
                abastec.setTempoAbastecimento(rs.getTime("TEMPOABASTECIMENTO"));
                abastec.setDataHora(rs.getTimestamp("DATAHORA"));
                abastec.setEncerranteLitros(rs.getDouble("ENCERRANTE"));
                abastec.setEncerranteAnterior(rs.getDouble("ENCERRANTEANTERIOR"));
                abastec.setTempoMedio(rs.getDouble("TEMPOMEDIO"));
                abastec.setKmMedio(rs.getDouble("KMMEDIO"));
                abastec.setHodometro(rs.getLong("HODOMETRO"));
                abastec.setHorimetro(rs.getLong("HORIMETRO"));
                abastec.setNumeroAbastecimento(rs.getInt("NUMEROABASTECIMENTO"));

                abastec.setHodometroAnterior(rs.getLong("HODOMETRO_ANTERIOR"));
                abastec.setHorimetroAnterior(rs.getLong("HORIMETRO_ANTERIOR"));
                abastec.setVeiculo(v);
                abastec.setBico(b);
                abastec.setOperador(o);
                abastec.setMotorista(m);
                abastec.setIdent1(rs.getString("IDENT1"));
                abastec.setIdent2(rs.getString("IDENT2"));
                abastecimentos.add(abastec);
            }
            return abastecimentos;
        } catch (Exception e) {
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return null;
        }
    }

    public static boolean atualizaMarcador(Integer idAbastec, Long marcadorAnterior, Long marcadorAtual, Double media, String tipoVeiculo) {
        Connection conn = GlobalParameter.getConn();
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            String sql = "UPDATE OR INSERT INTO ABASTECIMENTOS (IDABASTEC, HODOMETRO, HORIMETRO, HODOMETRO_ANTERIOR, HORIMETRO_ANTERIOR, KMMEDIO, TEMPOMEDIO) VALUES ("
                    + idAbastec
                    + ","
                    + (tipoVeiculo.equals("O") ? marcadorAtual : 0)
                    + ","
                    + (tipoVeiculo.equals("H") ? marcadorAtual : 0)
                    + ","
                    + (tipoVeiculo.equals("O") ? marcadorAnterior : 0)
                    + ","
                    + (tipoVeiculo.equals("H") ? marcadorAnterior : 0)
                    + ","
                    + (tipoVeiculo.equals("O") ? media : 0)
                    + ","
                    + (tipoVeiculo.equals("H") ? media : 0)
                    + ")";

            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    GeraLog g = new GeraLog();
                    g.gravaErro(e);
                    g.close();
                    //JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
                }
            }
        }
    }

}
