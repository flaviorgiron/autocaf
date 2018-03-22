package util;

import ctr.GlobalParameter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

public class FormatacaoDados {

    /**
     * @param data
     * @param formato
     * @return String
     */
    public static String getFormataData(Date data, String formato) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        return dateFormat.format(data);
    }

    /**
     * valor double a ser formatado
     *
     * @param valor
     * @return String
     */
    public static String DoubleFormat(double valor) {
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);
            switch (properties.getProperty("cbc_casas_decimais_volume").trim()) {
                case "1":
                    return String.format("%.1f", valor);
                case "2":
                    return String.format("%.2f", valor);
                case "3":
                    return String.format("%.3f", valor);
                default:
                    return String.format("%.2f", valor);
            }
        } catch (IOException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return "";
        }
    }

    public static String getDiaSemanaDescritivo(Date data, Boolean abreviado) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(data);
        String[] diaSemana = new String[7];
        if (abreviado) {
            diaSemana = new String[]{"dom", "seg",
                "ter", "qua", "qui", "sex", "sab"};
        } else {
            diaSemana = new String[]{"Domingo", "Segunda-Feira",
                "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"};
        }

        return diaSemana[calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1];//devido ao array começar do zero
    }
}
