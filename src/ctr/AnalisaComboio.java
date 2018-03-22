package ctr;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import model.*;
import util.*;
import bd.*;

public class AnalisaComboio implements Runnable {

    @Override
    public void run() {
        try {
            String mensagem;

            Veiculo v;
            Operador o;

            Long anterior, marcador = 0L;
            Double encerranteAnterior = 0.0;

            Bico b = GlobalParameter.getComboioSinc();
            Integer casasDecimais = b.getCasasDecimais(); //-->>>>>>>>>>>>>
            while (true) {
                Thread.sleep(800);
                if ((!GlobalParameter.getInstance().getFilaComboio().estaVazia())) {// || (true)) {//tirarrrrrrr
                    mensagem = (String) GlobalParameter.getInstance().getFilaComboio().remova();
                    //mensagem = "a240000009400000000199620003A00390417184902160003600000149450000014745401000000004444B3CF6CA3137895B5B3CF6CA31378DEEC00B6";
                    if (mensagem != null) {
                        //System.out.println(mensagem);
                        v = VeiculosDB.buscaIdentVeiculo(mensagem.substring(101, 117));
                        o = OperadoresDB.buscaIdentOperador(mensagem.substring(85, 101));
                        marcador = Long.parseLong(mensagem.substring(77, 85));

                        Abastecimento a = new Abastecimento();
                        Integer dia = Integer.parseInt(mensagem.substring(35, 37));
                        Integer hora = Integer.parseInt(mensagem.substring(37, 39));
                        Integer minuto = Integer.parseInt(mensagem.substring(39, 41));

                        a.setStringCBC(mensagem);

                        a.setIdent1(mensagem.substring(85, 101));
                        a.setIdent2(mensagem.substring(101, 117));

                        a.setOperador(o);
                        a.setVeiculo(v);
                        a.setBico(b);

                        Integer mes = Integer.parseInt(mensagem.substring(41, 43));
                        Integer ano = Integer.parseInt(mensagem.substring(43, 45)) + 100;
                        Date dT = new Date(ano, mes - 1, dia, hora, minuto);
                        a.setDataHora(new Timestamp(dT.getTime()));
                        a.setTempoAbastecimento(hexaToTime(mensagem.substring(29, 33)));

                        if (casasDecimais == 1) {
                            a.setVolume(Double.parseDouble(mensagem.substring(17, 22) + "." + mensagem.substring(22, 23)));
                        } else if (casasDecimais == 2) {
                            a.setVolume(Double.parseDouble(mensagem.substring(17, 21) + "." + mensagem.substring(21, 23)));
                        } else if (casasDecimais == 3) {
                            a.setVolume(Double.parseDouble(mensagem.substring(17, 20) + "." + mensagem.substring(20, 23)));
                        } else {
                            a.setVolume(Double.parseDouble(mensagem.substring(17, 21) + "." + mensagem.substring(21, 23)));
                        }

                        a.setPrecoUnitario(Double.parseDouble(mensagem.substring(23, 24) + "." + mensagem.substring(24, 27)));
                        a.setNumeroAbastecimento(Integer.parseInt(mensagem.substring(45, 51)));

                        //trabalhar com casas decimais???
                        a.setEncerranteLitros(Double.parseDouble(mensagem.substring(51, 59) + "." + mensagem.substring(59, 61)));
                        //trabalhar com casas decimais???        

                        if (v != null) {
                            anterior = VeiculosDB.buscaHodometroHorimetroAnterior(v, a.getDataHora());
                        } else {
                            anterior = 0L;
                        }

                        encerranteAnterior = Double.parseDouble(mensagem.substring(61, 69) + "." + mensagem.substring(69, 71));
                        a.setEncerranteAnterior(encerranteAnterior);

                        a.setTotalPagar(a.getPrecoUnitario() * a.getVolume());

                        if (v != null) {
                            switch (v.getTipo()) {
                                case "O":
                                    a.setHodometroAnterior(anterior);
                                    if ((anterior == 0) || ((marcador - anterior) / a.getVolume() < 0)) {
                                    } else if (a.getVolume() == 0) {
                                        a.setKmMedio(0.0);
                                    } else {
                                        a.setKmMedio((marcador - anterior) / a.getVolume());
                                    }
                                    break;
                                case "H":
                                    a.setHorimetroAnterior(anterior);
                                    if ((anterior == 0) || (a.getVolume() / (marcador - anterior) < 0)) {
                                    } else if (a.getVolume() == 0) {
                                        a.setTempoMedio(0.0);
                                    } else {
                                        a.setTempoMedio(a.getVolume() / (marcador - anterior));
                                    }
                                    break;
                            }
                        } else {
                            a.setHodometroAnterior(0L);
                            a.setKmMedio(0.0);
                            a.setHorimetroAnterior(0L);
                            a.setTempoMedio(0.0);
                        }

                        // para licenca
                        Base64.Encoder e = Base64.getEncoder();
                        Base64.Decoder de = Base64.getDecoder();

                        Date d = new Date();
                        d.setHours(00);
                        d.setMinutes(00);
                        d.setSeconds(01);
                        Long l = d.getTime();

                        Licenca lic = LicencaDB.buscaLicenca();
                        String s1 = new String(de.decode(lic.getUltimoAcesso()));
                        Long dataUltimoAcesso = Long.parseLong(s1);

                        if (dataUltimoAcesso < l) {
                            LicencaDB.atualizaUltimoAcesso(e.encodeToString(l.toString().getBytes()));
                        }

                        AbastecimentosDB.salvaAbastecimento(b, v, o, marcador, a);
                        // verificar se quando não contrala limite de frota mesmo assim é atualiza o campo de quantidade disponivel
//                      FrotasDB.atualizaLimiteDisponivel(v.getFrota().getSeqFrota(), a.getVolume());
                        if (v != null) {
                            VeiculosDB.atualizaMarcadorVeiculo(v.getSeqVeiculo(), marcador, v.getTipo());
                        }

                        HistoricoDB.insertOrUpdate(new Historico(mensagem, "S"), b.getSeqBico(), b.getNomeBico(), a.getVolume(), a.getTempoAbastecimento(), a.getDataHora(), a.getNumeroAbastecimento(), a.getEncerranteLitros());
                    }
                }
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }

    public Time hexaToTime(String timeHex) {
        try {
            long elapsedTime = Long.parseLong(timeHex, 16);
            String format = String.format("%%0%dd", 2);
            String seconds = String.format(format, elapsedTime % 60);
            String minutes = String.format(format, (elapsedTime % 3600) / 60);
            String hours = String.format(format, elapsedTime / 3600);
            DateFormat formato = new SimpleDateFormat("HH:mm:ss");
            String dataString = hours + ":" + minutes + ":" + seconds;
            return new java.sql.Time(formato.parse(dataString).getTime());
        } catch (NumberFormatException | ParseException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public static double roundToDecimals(double d, int c) {
        try {
            int temp = (int) ((d * Math.pow(10, c)));
            return (((double) temp) / Math.pow(10, c));
        } catch (Exception e) {
            e.printStackTrace();
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return 0;
        }
    }
}
