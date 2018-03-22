package ctr;

import bd.ConexaoFirebird;
import idioma.Idioma;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import model.*;
import util.GeraLog;
import util.JConfirmMessage;

public final class GlobalParameter {

    private static GlobalParameter instance = null;
    private static Cbc cbc;
    private static Cbc cbcStatus;
    private static Fila filaCompanyTec;

    //Canal 01 - Endereço 01
    private static LeituraTWC leituraTWC_04;
    private static LeituraTWC leituraTWC_44;
    //Canal 01 - Endereço 02
    private static LeituraTWC leituraTWC_05;
    private static LeituraTWC leituraTWC_45;

    //Canal 02 - Endereço 01
    private static LeituraTWC leituraTWC_08;
    private static LeituraTWC leituraTWC_48;
    //Canal 02 - Endereço 02
    private static LeituraTWC leituraTWC_09;
    private static LeituraTWC leituraTWC_49;

    //Canal 03 - Endereço 01
    private static LeituraTWC leituraTWC_0C;
    private static LeituraTWC leituraTWC_4C;
    //Canal 03 - Endereço 02
    private static LeituraTWC leituraTWC_0D;
    private static LeituraTWC leituraTWC_4D;

    //Canal 04 - Endereço 01
    private static LeituraTWC leituraTWC_10;
    private static LeituraTWC leituraTWC_50;
    //Canal 04 - Endereço 02
    private static LeituraTWC leituraTWC_11;
    private static LeituraTWC leituraTWC_51;

    private static Cbc cbcComboio;
    private static Fila filaComboio;

    private static String caminhoAplicacao;
    private static Idioma i;

    private static String statusBico04 = "F";
    private static String statusBico44 = "F";

    private static String statusBico05 = "F";
    private static String statusBico45 = "F";

    private static String statusBico08 = "F";
    private static String statusBico48 = "F";

    private static String statusBico09 = "F";
    private static String statusBico49 = "F";

    private static String statusBico0C = "F";
    private static String statusBico4C = "F";

    private static String statusBico0D = "F";
    private static String statusBico4D = "F";

    private static String statusBico10 = "F";
    private static String statusBico50 = "F";

    private static String statusBico11 = "F";
    private static String statusBico51 = "F";

    //Canal 01 - Endereço 01
    private static DadosTelaBico dadosTelaBico04;
    private static DadosTelaBico dadosTelaBico44;
    //Canal 01 - Endereço 02
    private static DadosTelaBico dadosTelaBico05;
    private static DadosTelaBico dadosTelaBico45;

    //Canal 02 - Endereço 01
    private static DadosTelaBico dadosTelaBico08;
    private static DadosTelaBico dadosTelaBico48;
    //Canal 02 - Endereço 02
    private static DadosTelaBico dadosTelaBico09;
    private static DadosTelaBico dadosTelaBico49;

    //Canal 03 - Endereço 01
    private static DadosTelaBico dadosTelaBico0C;
    private static DadosTelaBico dadosTelaBico4C;
    //Canal 03 - Endereço 02
    private static DadosTelaBico dadosTelaBico0D;
    private static DadosTelaBico dadosTelaBico4D;

    //Canal 04 - Endereço 01
    private static DadosTelaBico dadosTelaBico10;
    private static DadosTelaBico dadosTelaBico50;
    //Canal 04 - Endereço 02
    private static DadosTelaBico dadosTelaBico11;
    private static DadosTelaBico dadosTelaBico51;

    private static Connection conn;
    private static Operador operador;
    private static Bico comboioSinc;

    public static GlobalParameter getInstance() {
        try {
            if (instance == null) {
                instance = new GlobalParameter();
                cbc = new Cbc();
                cbcStatus = new Cbc();
                cbcComboio = new Cbc();

                filaCompanyTec = new Fila();
                filaComboio = new Fila();

                //statusBico01 = "";
                //statusBico02 = "";
                dadosTelaBico04 = null;
                dadosTelaBico44 = null;
                dadosTelaBico05 = null;
                dadosTelaBico45 = null;
                dadosTelaBico08 = null;
                dadosTelaBico48 = null;
                dadosTelaBico09 = null;
                dadosTelaBico49 = null;
                dadosTelaBico0C = null;
                dadosTelaBico4C = null;
                dadosTelaBico0D = null;
                dadosTelaBico4D = null;
                dadosTelaBico10 = null;
                dadosTelaBico50 = null;
                dadosTelaBico11 = null;
                dadosTelaBico51 = null;
                //i = "portugues";
                conn = new ConexaoFirebird().getConnection();
                operador = new Operador();
            }
            return instance;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static String getCaminhoAplicacao() {
        return caminhoAplicacao;
    }

    public static Integer getDiaSemana() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return calendar.get(GregorianCalendar.DAY_OF_WEEK);
    }

    public static void setCaminhoAplicacao(String aCaminhoAplicacao) {
        caminhoAplicacao = aCaminhoAplicacao;
    }

    public static Bico getComboioSinc() {
        return comboioSinc;
    }

    public static void setComboioSinc(Bico aComboioSinc) {
        comboioSinc = aComboioSinc;
    }

    public static void setIdioma(String idioma) {
        i = new Idioma(idioma);
    }

    public static Idioma getIdioma() {
        if (i != null) {
            return i;
        } else {
            return new Idioma("portugues");
        }
    }

    public static String getStatusBico04() {
        return statusBico04;
    }

    public static void setStatusBico04(String aStatusBico04) {
        statusBico04 = aStatusBico04;
    }

    public static String getStatusBico44() {
        return statusBico44;
    }

    public static void setStatusBico44(String aStatusBico44) {
        statusBico44 = aStatusBico44;
    }

    public static String getStatusBico05() {
        return statusBico05;
    }

    public static void setStatusBico05(String aStatusBico05) {
        statusBico05 = aStatusBico05;
    }

    public static String getStatusBico45() {
        return statusBico45;
    }

    public static void setStatusBico45(String aStatusBico45) {
        statusBico45 = aStatusBico45;
    }

    public static String getStatusBico08() {
        return statusBico08;
    }

    public static void setStatusBico08(String aStatusBico08) {
        statusBico08 = aStatusBico08;
    }

    public static String getStatusBico48() {
        return statusBico48;
    }

    public static void setStatusBico48(String aStatusBico48) {
        statusBico48 = aStatusBico48;
    }

    public static String getStatusBico09() {
        return statusBico09;
    }

    public static void setStatusBico09(String aStatusBico09) {
        statusBico09 = aStatusBico09;
    }

    public static String getStatusBico49() {
        return statusBico49;
    }

    public static void setStatusBico49(String aStatusBico49) {
        statusBico49 = aStatusBico49;
    }

    public static String getStatusBico0C() {
        return statusBico0C;
    }

    public static void setStatusBico0C(String aStatusBico0C) {
        statusBico0C = aStatusBico0C;
    }

    public static String getStatusBico4C() {
        return statusBico4C;
    }

    public static void setStatusBico4C(String aStatusBico4C) {
        statusBico4C = aStatusBico4C;
    }

    public static String getStatusBico0D() {
        return statusBico0D;
    }

    public static void setStatusBico0D(String aStatusBico0D) {
        statusBico0D = aStatusBico0D;
    }

    public static String getStatusBico4D() {
        return statusBico4D;
    }

    public static void setStatusBico4D(String aStatusBico4D) {
        statusBico4D = aStatusBico4D;
    }

    public static String getStatusBico10() {
        return statusBico10;
    }

    public static void setStatusBico10(String aStatusBico10) {
        statusBico10 = aStatusBico10;
    }

    public static String getStatusBico50() {
        return statusBico50;
    }

    public static void setStatusBico50(String aStatusBico50) {
        statusBico50 = aStatusBico50;
    }

    public static String getStatusBico11() {
        return statusBico11;
    }

    public static void setStatusBico11(String aStatusBico11) {
        statusBico11 = aStatusBico11;
    }

    public static String getStatusBico51() {
        return statusBico51;
    }

    public static void setStatusBico51(String aStatusBico51) {
        statusBico51 = aStatusBico51;
    }

    public static LeituraTWC getLeituraTWC_04() {
        return leituraTWC_04;
    }

    public static void setLeituraTWC_04(LeituraTWC aLeituraTWC_04) {
        leituraTWC_04 = aLeituraTWC_04;
    }

    public static LeituraTWC getLeituraTWC_44() {
        return leituraTWC_44;
    }

    public static void setLeituraTWC_44(LeituraTWC aLeituraTWC_44) {
        leituraTWC_44 = aLeituraTWC_44;
    }

    public static LeituraTWC getLeituraTWC_05() {
        return leituraTWC_05;
    }

    public static void setLeituraTWC_05(LeituraTWC aLeituraTWC_05) {
        leituraTWC_05 = aLeituraTWC_05;
    }

    public static LeituraTWC getLeituraTWC_45() {
        return leituraTWC_45;
    }

    public static void setLeituraTWC_45(LeituraTWC aLeituraTWC_45) {
        leituraTWC_45 = aLeituraTWC_45;
    }

    public static LeituraTWC getLeituraTWC_08() {
        return leituraTWC_08;
    }

    public static void setLeituraTWC_08(LeituraTWC aLeituraTWC_08) {
        leituraTWC_08 = aLeituraTWC_08;
    }

    public static LeituraTWC getLeituraTWC_48() {
        return leituraTWC_48;
    }

    public static void setLeituraTWC_48(LeituraTWC aLeituraTWC_48) {
        leituraTWC_48 = aLeituraTWC_48;
    }

    public static LeituraTWC getLeituraTWC_09() {
        return leituraTWC_09;
    }

    public static void setLeituraTWC_09(LeituraTWC aLeituraTWC_09) {
        leituraTWC_09 = aLeituraTWC_09;
    }

    public static LeituraTWC getLeituraTWC_49() {
        return leituraTWC_49;
    }

    public static void setLeituraTWC_49(LeituraTWC aLeituraTWC_49) {
        leituraTWC_49 = aLeituraTWC_49;
    }

    public static LeituraTWC getLeituraTWC_0C() {
        return leituraTWC_0C;
    }

    public static void setLeituraTWC_0C(LeituraTWC aLeituraTWC_0C) {
        leituraTWC_0C = aLeituraTWC_0C;
    }

    public static LeituraTWC getLeituraTWC_4C() {
        return leituraTWC_4C;
    }

    public static void setLeituraTWC_4C(LeituraTWC aLeituraTWC_4C) {
        leituraTWC_4C = aLeituraTWC_4C;
    }

    public static LeituraTWC getLeituraTWC_0D() {
        return leituraTWC_0D;
    }

    public static void setLeituraTWC_0D(LeituraTWC aLeituraTWC_0D) {
        leituraTWC_0D = aLeituraTWC_0D;
    }

    public static LeituraTWC getLeituraTWC_4D() {
        return leituraTWC_4D;
    }

    public static void setLeituraTWC_4D(LeituraTWC aLeituraTWC_4D) {
        leituraTWC_4D = aLeituraTWC_4D;
    }

    public static LeituraTWC getLeituraTWC_10() {
        return leituraTWC_10;
    }

    public static void setLeituraTWC_10(LeituraTWC aLeituraTWC_10) {
        leituraTWC_10 = aLeituraTWC_10;
    }

    public static LeituraTWC getLeituraTWC_50() {
        return leituraTWC_50;
    }

    public static void setLeituraTWC_50(LeituraTWC aLeituraTWC_50) {
        leituraTWC_50 = aLeituraTWC_50;
    }

    public static LeituraTWC getLeituraTWC_11() {
        return leituraTWC_11;
    }

    public static void setLeituraTWC_11(LeituraTWC aLeituraTWC_11) {
        leituraTWC_11 = aLeituraTWC_11;
    }

    public static LeituraTWC getLeituraTWC_51() {
        return leituraTWC_51;
    }

    public static void setLeituraTWC_51(LeituraTWC aLeituraTWC_51) {
        leituraTWC_51 = aLeituraTWC_51;
    }

    public Cbc getCbcComboio() {
        return cbcComboio;
    }

    public Fila getFilaComboio() {
        return filaComboio;
    }

    private GlobalParameter() {
    }

    public Cbc getCbc() {
        return cbc;
    }

    public Cbc getCbcStatus() {
        return cbcStatus;
    }

    public String getDataSistema() {
        try {
            return DateFormat.getDateInstance().format(new Date());
        } catch (Exception e) {
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public Fila getFilaCompanyTec() {
        return filaCompanyTec;
    }

    public static DadosTelaBico getDadosTelaBico04() {
        return dadosTelaBico04;
    }

    public static void setDadosTelaBico04(DadosTelaBico aDadosTelaBico04) {
        dadosTelaBico04 = aDadosTelaBico04;
    }

    public static DadosTelaBico getDadosTelaBico44() {
        return dadosTelaBico44;
    }

    public static void setDadosTelaBico44(DadosTelaBico aDadosTelaBico44) {
        dadosTelaBico44 = aDadosTelaBico44;
    }

    public static DadosTelaBico getDadosTelaBico05() {
        return dadosTelaBico05;
    }

    public static void setDadosTelaBico05(DadosTelaBico aDadosTelaBico05) {
        dadosTelaBico05 = aDadosTelaBico05;
    }

    public static DadosTelaBico getDadosTelaBico45() {
        return dadosTelaBico45;
    }

    public static void setDadosTelaBico45(DadosTelaBico aDadosTelaBico45) {
        dadosTelaBico45 = aDadosTelaBico45;
    }

    public static DadosTelaBico getDadosTelaBico08() {
        return dadosTelaBico08;
    }

    public static void setDadosTelaBico08(DadosTelaBico aDadosTelaBico08) {
        dadosTelaBico08 = aDadosTelaBico08;
    }

    public static DadosTelaBico getDadosTelaBico48() {
        return dadosTelaBico48;
    }

    public static void setDadosTelaBico48(DadosTelaBico aDadosTelaBico48) {
        dadosTelaBico48 = aDadosTelaBico48;
    }

    public static DadosTelaBico getDadosTelaBico09() {
        return dadosTelaBico09;
    }

    public static void setDadosTelaBico09(DadosTelaBico aDadosTelaBico09) {
        dadosTelaBico09 = aDadosTelaBico09;
    }

    public static DadosTelaBico getDadosTelaBico49() {
        return dadosTelaBico49;
    }

    public static void setDadosTelaBico49(DadosTelaBico aDadosTelaBico49) {
        dadosTelaBico49 = aDadosTelaBico49;
    }

    public static DadosTelaBico getDadosTelaBico0C() {
        return dadosTelaBico0C;
    }

    public static void setDadosTelaBico0C(DadosTelaBico aDadosTelaBico0C) {
        dadosTelaBico0C = aDadosTelaBico0C;
    }

    public static DadosTelaBico getDadosTelaBico4C() {
        return dadosTelaBico4C;
    }

    public static void setDadosTelaBico4C(DadosTelaBico aDadosTelaBico4C) {
        dadosTelaBico4C = aDadosTelaBico4C;
    }

    public static DadosTelaBico getDadosTelaBico0D() {
        return dadosTelaBico0D;
    }

    public static void setDadosTelaBico0D(DadosTelaBico aDadosTelaBico0D) {
        dadosTelaBico0D = aDadosTelaBico0D;
    }

    public static DadosTelaBico getDadosTelaBico4D() {
        return dadosTelaBico4D;
    }

    public static void setDadosTelaBico4D(DadosTelaBico aDadosTelaBico4D) {
        dadosTelaBico4D = aDadosTelaBico4D;
    }

    public static DadosTelaBico getDadosTelaBico10() {
        return dadosTelaBico10;
    }

    public static void setDadosTelaBico10(DadosTelaBico aDadosTelaBico10) {
        dadosTelaBico10 = aDadosTelaBico10;
    }

    public static DadosTelaBico getDadosTelaBico50() {
        return dadosTelaBico50;
    }

    public static void setDadosTelaBico50(DadosTelaBico aDadosTelaBico50) {
        dadosTelaBico50 = aDadosTelaBico50;
    }

    public static DadosTelaBico getDadosTelaBico11() {
        return dadosTelaBico11;
    }

    public static void setDadosTelaBico11(DadosTelaBico aDadosTelaBico11) {
        dadosTelaBico11 = aDadosTelaBico11;
    }

    public static DadosTelaBico getDadosTelaBico51() {
        return dadosTelaBico51;
    }

    public static void setDadosTelaBico51(DadosTelaBico aDadosTelaBico51) {
        dadosTelaBico51 = aDadosTelaBico51;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador aOperador) {
        operador = aOperador;
    }
}
