package ctr;

import java.sql.Connection;
import java.sql.Statement;
import util.GeraLog;

public class AtualizaLimiteSemanal {

    private static Integer diaSemana;
    private static String sql;

    public static void atualizaLimite() {
        try {
            GlobalParameter.getInstance();
            Connection con = GlobalParameter.getConn();
            Statement stmt = con.createStatement();
            diaSemana = GlobalParameter.getDiaSemana();

            switch (diaSemana) {
                case 0:
                    sql = "UPDATE VEICULOS SET"
                            + " PROXRENAUTO = dateadd (7 day to current_date),"
                            + " RESTANTESEMANAL = LIMITESEMANAL"
                            + " WHERE DIARENOVACAO = 'Sábado' AND PROXRENAUTO <= current_date;";
                    break;
                case 1:
                    sql = "UPDATE VEICULOS SET"
                            + " PROXRENAUTO = dateadd (7 day to current_date),"
                            + " RESTANTESEMANAL = LIMITESEMANAL"
                            + " WHERE DIARENOVACAO = 'Domingo' AND PROXRENAUTO <= current_date;";
                    break;
                case 2:
                    sql = "UPDATE VEICULOS SET"
                            + " PROXRENAUTO = dateadd (7 day to current_date),"
                            + " RESTANTESEMANAL = LIMITESEMANAL"
                            + " WHERE DIARENOVACAO = 'Segunda' AND PROXRENAUTO <= current_date;";
                    break;
                case 3:
                    sql = "UPDATE VEICULOS SET"
                            + " PROXRENAUTO = dateadd (7 day to current_date),"
                            + " RESTANTESEMANAL = LIMITESEMANAL"
                            + " WHERE DIARENOVACAO = 'Terça' AND PROXRENAUTO <= current_date;";
                    break;
                case 4:
                    sql = "UPDATE VEICULOS SET"
                            + " PROXRENAUTO = dateadd (7 day to current_date),"
                            + " RESTANTESEMANAL = LIMITESEMANAL"
                            + " WHERE DIARENOVACAO = 'Quarta' AND PROXRENAUTO <= current_date;";
                    break;
                case 5:
                    sql = "UPDATE VEICULOS SET"
                            + " PROXRENAUTO = dateadd (7 day to current_date),"
                            + " RESTANTESEMANAL = LIMITESEMANAL"
                            + " WHERE DIARENOVACAO = 'Quinta' AND PROXRENAUTO <= current_date;";
                    break;
                case 6:
                    sql = "UPDATE VEICULOS SET"
                            + " PROXRENAUTO = dateadd (7 day to current_date),"
                            + " RESTANTESEMANAL = LIMITESEMANAL"
                            + " WHERE DIARENOVACAO = 'Sexta' AND PROXRENAUTO <= current_date;";
                    break;
                default:
                    sql = "";
                    break;
            }
            if (!sql.equals("")) {
                stmt.execute(sql);
            }

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }
}
