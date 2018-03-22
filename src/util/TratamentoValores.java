package util;

import java.util.GregorianCalendar;

public class TratamentoValores {

    /**
     * Método para comparar as datas e retornar o numero de dias de diferença
     * entre elas
     *
     * Compare two date and return the difference between them in days.
     *
     * @param dataLow The lowest date
     * @param dataHigh The highest date
     *
     * @return int
     */
    public static int dataDiff(java.util.Date dataLow, java.util.Date dataHigh) {
        GregorianCalendar startTime = new GregorianCalendar();
        GregorianCalendar endTime = new GregorianCalendar();
        GregorianCalendar curTime = new GregorianCalendar();
        GregorianCalendar baseTime = new GregorianCalendar();
        startTime.setTime(dataLow);
        endTime.setTime(dataHigh);
        int dif_multiplier = 1;
        // Verifica a ordem de inicio das datas
        if (dataLow.compareTo(dataHigh) < 0) {
            baseTime.setTime(dataHigh);
            curTime.setTime(dataLow);
            dif_multiplier = 1;
        } else {
            baseTime.setTime(dataLow);
            curTime.setTime(dataHigh);
            dif_multiplier = -1;
        }
        int result_years = 0;
        int result_months = 0;
        int result_days = 0;
        // Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import acumulando
        // no total de dias. Ja leva em consideracao ano bissesto
        while (curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR)
                || curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)) {
            int max_day = curTime.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            result_months += max_day;
            curTime.add(GregorianCalendar.MONTH, 1);
        }
        // Marca que é um saldo negativo ou positivo
        result_months = result_months * dif_multiplier;
        // Retirna a diferenca de dias do total dos meses
        result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime.get(GregorianCalendar.DAY_OF_MONTH));
        return result_years + result_months + result_days;
    }

    public static String preencheCom(Object pLinha_a_preencher, String letra, int tamanho, int direcao, boolean trim) {
        String textoPreencher = null;
        StringBuilder sb = null;

        if (pLinha_a_preencher == null) {
            textoPreencher = "";
        } else {
            //if (pLinha_a_preencher instanceof Double) {
            //    textoPreencher = String.valueOf(pLinha_a_preencher);//.replace(".", "").replace(",", "");
            //} else if (pLinha_a_preencher instanceof Time) {
            //    textoPreencher = String.valueOf(pLinha_a_preencher).replace(":", "");
            //} else if (pLinha_a_preencher instanceof Date) {
            //    textoPreencher = FormatacaoDados.getFormataData((Date) pLinha_a_preencher, "ddMMyyyyHHmmss");
            //} else {
            textoPreencher = String.valueOf(pLinha_a_preencher);
            //}

            if (trim) {
                textoPreencher.toString().trim();
            }

            if (textoPreencher == null || textoPreencher.trim().equals("")) {
                textoPreencher = "";
            }
            while (textoPreencher.contains(" ")) {
                textoPreencher = textoPreencher.replaceAll(" ", " ").trim();
            }
            //Retira caracteres estranhos
            textoPreencher = textoPreencher.replaceAll("[./-]", "");
            sb = new StringBuilder(textoPreencher);
            if (direcao == 1) { //a Esquerda
                for (int i = sb.length(); i < tamanho; i++) {
                    sb.insert(0, letra);
                }
            } else if (direcao == 2) {//a Direita
                for (int i = sb.length(); i < tamanho; i++) {
                    sb.append(letra);
                }
            }

        }
        if (sb != null) {
            return sb.toString();
        } else {
            return "";
        }

    }

    //Parâmetros:  
    /**
     * 1 - Valor a arredondar. 2 - Quantidade de casas depois da vírgula. 3 -
     * Arredondar para cima ou para baixo? Para cima = 0 (ceil) Para baixo = 1
     * ou qualquer outro inteiro (floor)
     *
     */
    public static Double arredondar(double valor, int casas, int ceilOrFloor) {
        Double arredondado = valor;
        arredondado *= (Math.pow(10, casas));
        if (ceilOrFloor == 0) {
            arredondado = Math.ceil(arredondado);
        } else {
            arredondado = Math.floor(arredondado);
        }
        arredondado /= (Math.pow(10, casas));
        return arredondado;
    }
}
