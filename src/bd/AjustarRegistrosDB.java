package bd;

import java.sql.Connection;
import java.sql.Statement;
import ctr.GlobalParameter;
import util.GeraLog;

public class AjustarRegistrosDB {

    public static boolean recalcularMediaHorimetros() {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();

            String sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET TEMPOMEDIO = VOLUME/(HORIMETRO-HORIMETRO_ANTERIOR)\n"
                    + "WHERE (HORIMETRO > 0 AND HORIMETRO_ANTERIOR > 0) AND (HORIMETRO>HORIMETRO_ANTERIOR) AND TEMPOMEDIO <> VOLUME/(HORIMETRO-HORIMETRO_ANTERIOR)";

            stmt.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

    public static boolean recalcularMediaOdometros() {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();

            String sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET KMMEDIO = (HODOMETRO-HODOMETRO_ANTERIOR)/VOLUME\n"
                    + "WHERE (HODOMETRO > 0 AND HODOMETRO_ANTERIOR > 0) AND (HODOMETRO>HODOMETRO_ANTERIOR) AND KMMEDIO <> (HODOMETRO-HODOMETRO_ANTERIOR)/VOLUME";

            stmt.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

    public static boolean ajustarHorimetros() {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET HORIMETRO = HORIMETRO/10\n"
                    + "WHERE HORIMETRO > 999999";
            stmt.executeUpdate(sql);

            sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET HORIMETRO_ANTERIOR = HORIMETRO_ANTERIOR/10\n"
                    + "WHERE HORIMETRO_ANTERIOR > 999999";
            stmt.executeUpdate(sql);

            sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET HORIMETRO = HORIMETRO/10\n"
                    + "WHERE HORIMETRO > 999999";
            stmt.executeUpdate(sql);

            sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET HORIMETRO_ANTERIOR = HORIMETRO_ANTERIOR/10\n"
                    + "WHERE HORIMETRO_ANTERIOR > 999999";
            stmt.executeUpdate(sql);

            sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET TEMPOMEDIO = VOLUME/(HORIMETRO-HORIMETRO_ANTERIOR)\n"
                    + "WHERE (HORIMETRO > 0 AND HORIMETRO_ANTERIOR > 0) AND (HORIMETRO>HORIMETRO_ANTERIOR) AND TEMPOMEDIO <> VOLUME/(HORIMETRO-HORIMETRO_ANTERIOR)";

            stmt.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

    public static boolean ajustarOdometros() {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET HODOMETRO = HODOMETRO/10\n"
                    + "WHERE HODOMETRO > 999999";
            stmt.executeUpdate(sql);

            sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET HODOMETRO_ANTERIOR = HODOMETRO_ANTERIOR/10\n"
                    + "WHERE HODOMETRO_ANTERIOR > 999999";
            stmt.executeUpdate(sql);

            sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET HODOMETRO = HODOMETRO/10\n"
                    + "WHERE HODOMETRO > 999999";
            stmt.executeUpdate(sql);

            sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET HODOMETRO_ANTERIOR = HODOMETRO_ANTERIOR/10\n"
                    + "WHERE HODOMETRO_ANTERIOR > 999999";
            stmt.executeUpdate(sql);

            sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET KMMEDIO = (HODOMETRO-HODOMETRO_ANTERIOR)/VOLUME\n"
                    + "WHERE (HODOMETRO > 0 AND HODOMETRO_ANTERIOR > 0) AND (HODOMETRO>HODOMETRO_ANTERIOR) AND KMMEDIO <> (HODOMETRO-HODOMETRO_ANTERIOR)/VOLUME";

            stmt.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

    public static boolean zerarMediaOdo() {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET KMMEDIO = 0\n"
                    + "WHERE (HODOMETRO > 0 AND HODOMETRO_ANTERIOR > 0) AND (HODOMETRO_ANTERIOR>HODOMETRO)";

            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

    public static boolean zerarMediaHor() {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE ABASTECIMENTOS  \n"
                    + "SET TEMPOMEDIO = 0\n"
                    + "WHERE (HORIMETRO > 0 AND HORIMETRO_ANTERIOR > 0) AND (HORIMETRO_ANTERIOR>HORIMETRO)";

            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }

    public static boolean zerarEstoque() {
        Connection conn = GlobalParameter.getConn();
        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM MOVESTOQUE";
            stmt.executeUpdate(sql);

            sql = "UPDATE SALDOESTOQUE SET SALDO = 0";
            stmt.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return false;
        }
    }
}
