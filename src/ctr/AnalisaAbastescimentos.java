package ctr;

import java.io.FileInputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import model.*;
import util.*;
import view.movimentacao.*;
import bd.*;
import cupom.ImprimirCupom;

public class AnalisaAbastescimentos implements Runnable {

    @Override
    public void run() {
        String mensagem = "";
        try {

            Double encerranteAnterior;
            String abastecDireto;
            Bico b;
            Veiculo v;
            Operador o;
            Motorista m;
            Long anterior, marcador;

            Integer casasDecimais;
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);
            casasDecimais = Integer.parseInt(properties.getProperty("cbc_casas_decimais_volume").trim());
            //String dadosTela;

            while (true) {
                Thread.sleep(500);
                if ((!GlobalParameter.getInstance().getFilaCompanyTec().estaVazia())) {
                    //System.out.println("Tamanho Fila--> " + GlobalParameter.getInstance().getFilaCompanyTec().tamanho());
                    mensagem = (String) GlobalParameter.getInstance().getFilaCompanyTec().remova();
                    //mensagem = "a240000009400000000199620003A00390417184902160003600000149450000014745401000000004444B3CF6CA3137895B5B3CF6CA31378DEEC00B6";
                    //System.out.println(mensagem);
                    if (mensagem != null) {
                        if ((mensagem.substring(85, 101).equals("0000000000000000")) || (mensagem.substring(85, 101).equals("FFFFFFFFFFFFFFFF"))) {
                            abastecDireto = "N";
                            HistoricoDB.insertOrUpdate(new Historico(mensagem, "N"));
                        } else {
                            abastecDireto = "S";
                            HistoricoDB.insertOrUpdate(new Historico(mensagem, "S"));
                        }

                        if (!properties.getProperty("sis_inicializacao").trim().equals("1")) {
                            SDIPrincipalGUI.atualizaLeituras(mensagem);
                        }

                        Abastecimento a = new Abastecimento();
                        Integer dia = Integer.parseInt(mensagem.substring(35, 37));
                        Integer hora = Integer.parseInt(mensagem.substring(37, 39));
                        Integer minuto = Integer.parseInt(mensagem.substring(39, 41));

                        a.setStringCBC(mensagem);

                        a.setIdent1(mensagem.substring(85, 101));
                        a.setIdent2(mensagem.substring(101, 117));

                        if (abastecDireto.equals("N")) {
                            if (mensagem.substring(33, 35).equals("04") && (GlobalParameter.getDadosTelaBico04() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico04().getBico();
                                v = GlobalParameter.getDadosTelaBico04().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico04().getOperador();
                                m = GlobalParameter.getDadosTelaBico04().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico04().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("44") && (GlobalParameter.getDadosTelaBico44() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico44().getBico();
                                v = GlobalParameter.getDadosTelaBico44().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico44().getOperador();
                                m = GlobalParameter.getDadosTelaBico44().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico44().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("05") && (GlobalParameter.getDadosTelaBico05() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico05().getBico();
                                v = GlobalParameter.getDadosTelaBico05().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico05().getOperador();
                                m = GlobalParameter.getDadosTelaBico05().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico05().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("45") && (GlobalParameter.getDadosTelaBico45() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico45().getBico();
                                v = GlobalParameter.getDadosTelaBico45().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico45().getOperador();
                                m = GlobalParameter.getDadosTelaBico45().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico45().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("08") && (GlobalParameter.getDadosTelaBico08() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico08().getBico();
                                v = GlobalParameter.getDadosTelaBico08().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico08().getOperador();
                                m = GlobalParameter.getDadosTelaBico08().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico08().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("48") && (GlobalParameter.getDadosTelaBico48() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico48().getBico();
                                v = GlobalParameter.getDadosTelaBico48().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico48().getOperador();
                                m = GlobalParameter.getDadosTelaBico48().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico48().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("09") && (GlobalParameter.getDadosTelaBico09() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico09().getBico();
                                v = GlobalParameter.getDadosTelaBico09().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico09().getOperador();
                                m = GlobalParameter.getDadosTelaBico09().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico09().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("49") && (GlobalParameter.getDadosTelaBico49() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico49().getBico();
                                v = GlobalParameter.getDadosTelaBico49().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico49().getOperador();
                                m = GlobalParameter.getDadosTelaBico49().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico49().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("0C") && (GlobalParameter.getDadosTelaBico0C() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico0C().getBico();
                                v = GlobalParameter.getDadosTelaBico0C().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico0C().getOperador();
                                m = GlobalParameter.getDadosTelaBico0C().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico0C().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("4C") && (GlobalParameter.getDadosTelaBico4C() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico4C().getBico();
                                v = GlobalParameter.getDadosTelaBico4C().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico4C().getOperador();
                                m = GlobalParameter.getDadosTelaBico4C().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico4C().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("0D") && (GlobalParameter.getDadosTelaBico0D() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico0D().getBico();
                                v = GlobalParameter.getDadosTelaBico0D().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico0D().getOperador();
                                m = GlobalParameter.getDadosTelaBico0D().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico0D().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("4D") && (GlobalParameter.getDadosTelaBico4D() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico4D().getBico();
                                v = GlobalParameter.getDadosTelaBico4D().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico4D().getOperador();
                                m = GlobalParameter.getDadosTelaBico4D().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico4D().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("10") && (GlobalParameter.getDadosTelaBico10() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico10().getBico();
                                v = GlobalParameter.getDadosTelaBico10().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico10().getOperador();
                                m = GlobalParameter.getDadosTelaBico10().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico10().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("50") && (GlobalParameter.getDadosTelaBico50() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico50().getBico();
                                v = GlobalParameter.getDadosTelaBico50().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico50().getOperador();
                                m = GlobalParameter.getDadosTelaBico50().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico50().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("11") && (GlobalParameter.getDadosTelaBico11() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico11().getBico();
                                v = GlobalParameter.getDadosTelaBico11().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico11().getOperador();
                                m = GlobalParameter.getDadosTelaBico11().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico11().getHodometro();
                            } else if (mensagem.substring(33, 35).equals("51") && (GlobalParameter.getDadosTelaBico51() != null)) {// || mensagem.substring(22, 24).equals("05")) {
                                b = GlobalParameter.getDadosTelaBico51().getBico();
                                v = GlobalParameter.getDadosTelaBico51().getVeiculo();
                                o = GlobalParameter.getDadosTelaBico51().getOperador();
                                m = GlobalParameter.getDadosTelaBico51().getMotorista();
                                marcador = GlobalParameter.getDadosTelaBico51().getHodometro();
                            } else {//abastecimento sem os dados da tela
                                b = null;
                                v = null;
                                o = null;
                                m = null;
                                marcador = Long.parseLong(mensagem.substring(77, 85));
                                //encerranteAnterior = 0.0;
                            }
                        } else {
                            b = BicosDB.buscaBicoCBC(mensagem.substring(33, 35));
                            o = OperadoresDB.buscaIdentOperador(mensagem.substring(85, 101));
                            v = VeiculosDB.buscaIdentVeiculo(mensagem.substring(101, 117));
                            m = null;
                            marcador = Long.parseLong(mensagem.substring(77, 85));
                            if ((v == null) || (marcador == 0)) {
                                if (b.getIdCBC().equals("04")) {

                                    if (GlobalParameter.getLeituraTWC_04() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_04().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_04().getMotorista());

                                        if (GlobalParameter.getLeituraTWC_04().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_04().setOdometro("0");
                                        }

                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_04().getOdometro());
                                        GlobalParameter.setLeituraTWC_04(null);
                                    }
                                } else if (b.getIdCBC().equals("44")) {
                                    if (GlobalParameter.getLeituraTWC_44() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_44().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_44().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_44().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_44().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_44().getOdometro());
                                        GlobalParameter.setLeituraTWC_44(null);
                                    }
                                } else if (b.getIdCBC().equals("05")) {
                                    if (GlobalParameter.getLeituraTWC_05() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_05().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_05().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_05().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_05().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_05().getOdometro());
                                        GlobalParameter.setLeituraTWC_05(null);
                                    }
                                } else if (b.getIdCBC().equals("45")) {
                                    if (GlobalParameter.getLeituraTWC_45() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_45().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_45().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_45().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_45().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_45().getOdometro());
                                        GlobalParameter.setLeituraTWC_45(null);
                                    }

                                } else if (b.getIdCBC().equals("08")) {
                                    if (GlobalParameter.getLeituraTWC_08() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_08().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_08().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_08().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_08().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_08().getOdometro());
                                        GlobalParameter.setLeituraTWC_08(null);
                                    }
                                } else if (b.getIdCBC().equals("48")) {
                                    if (GlobalParameter.getLeituraTWC_48() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_48().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_48().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_48().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_48().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_48().getOdometro());
                                        GlobalParameter.setLeituraTWC_48(null);
                                    }
                                } else if (b.getIdCBC().equals("09")) {
                                    if (GlobalParameter.getLeituraTWC_09() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_09().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_09().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_09().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_09().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_09().getOdometro());
                                        GlobalParameter.setLeituraTWC_09(null);
                                    }
                                } else if (b.getIdCBC().equals("49")) {
                                    if (GlobalParameter.getLeituraTWC_49() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_49().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_49().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_49().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_49().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_49().getOdometro());
                                        GlobalParameter.setLeituraTWC_49(null);
                                    }
                                } else if (b.getIdCBC().equals("0C")) {
                                    if (GlobalParameter.getLeituraTWC_0C() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_0C().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_0C().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_0C().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_0C().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_0C().getOdometro());
                                        GlobalParameter.setLeituraTWC_0C(null);
                                    }
                                } else if (b.getIdCBC().equals("4C")) {
                                    if (GlobalParameter.getLeituraTWC_4C() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_4C().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_4C().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_4C().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_4C().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_4C().getOdometro());
                                        GlobalParameter.setLeituraTWC_4C(null);
                                    }
                                } else if (b.getIdCBC().equals("0D")) {
                                    if (GlobalParameter.getLeituraTWC_0D() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_0D().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_0D().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_0D().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_0D().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_0D().getOdometro());
                                        GlobalParameter.setLeituraTWC_0D(null);
                                    }
                                } else if (b.getIdCBC().equals("4D")) {
                                    if (GlobalParameter.getLeituraTWC_4D() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_4D().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_4D().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_4D().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_4D().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_4D().getOdometro());
                                        GlobalParameter.setLeituraTWC_4D(null);
                                    }
                                } else if (b.getIdCBC().equals("10")) {
                                    if (GlobalParameter.getLeituraTWC_10() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_10().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_10().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_10().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_10().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_10().getOdometro());
                                        GlobalParameter.setLeituraTWC_10(null);
                                    }
                                } else if (b.getIdCBC().equals("50")) {
                                    if (GlobalParameter.getLeituraTWC_50() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_50().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_50().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_50().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_50().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_50().getOdometro());
                                        GlobalParameter.setLeituraTWC_50(null);
                                    }
                                } else if (b.getIdCBC().equals("11")) {
                                    if (GlobalParameter.getLeituraTWC_11() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_11().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_11().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_11().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_11().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_11().getOdometro());
                                        GlobalParameter.setLeituraTWC_11(null);
                                    }
                                } else if (b.getIdCBC().equals("51")) {
                                    if (GlobalParameter.getLeituraTWC_51() != null) {
                                        v = VeiculosDB.buscaIdentVeiculo(GlobalParameter.getLeituraTWC_51().getVeiculo());
                                        m = MotoristasDB.buscaIdentMotorista(GlobalParameter.getLeituraTWC_51().getMotorista());
                                        if (GlobalParameter.getLeituraTWC_51().getOdometro().equals("")) {
                                            GlobalParameter.getLeituraTWC_51().setOdometro("0");
                                        }
                                        marcador = Long.valueOf(GlobalParameter.getLeituraTWC_51().getOdometro());
                                        GlobalParameter.setLeituraTWC_51(null);
                                    }
                                }
                            }
                            //marcador = Long.parseLong(mensagem.substring(77, 85));
                        }

                        a.setOperador(o);
                        a.setVeiculo(v);
                        a.setBico(b);
                        a.setHodometro(marcador);
                        a.setHorimetro(marcador);
                        a.setMotorista(m);

                        Integer mes = Integer.parseInt(mensagem.substring(41, 43));
                        Integer ano = Integer.parseInt(mensagem.substring(43, 45)) + 100;
                        Date dT = new Date(ano, mes - 1, dia, hora, minuto);
                        a.setDataHora(new Timestamp(dT.getTime()));
                        a.setTempoAbastecimento(hexaToTime(mensagem.substring(29, 33)));

                        switch (casasDecimais) {
                            case 1:
                                a.setVolume(Double.parseDouble(mensagem.substring(17, 22) + "." + mensagem.substring(22, 23)));
                                break;
                            case 2:
                                a.setVolume(Double.parseDouble(mensagem.substring(17, 21) + "." + mensagem.substring(21, 23)));
                                break;
                            case 3:
                                a.setVolume(Double.parseDouble(mensagem.substring(17, 20) + "." + mensagem.substring(20, 23)));
                                break;
                            default:
                                a.setVolume(Double.parseDouble(mensagem.substring(17, 21) + "." + mensagem.substring(21, 23)));
                                break;
                        }

                        a.setPrecoUnitario(Double.parseDouble(mensagem.substring(23, 24) + "." + mensagem.substring(24, 27)));
                        a.setNumeroAbastecimento(Integer.parseInt(mensagem.substring(45, 51)));
                        a.setEncerranteLitros(Double.parseDouble(mensagem.substring(51, 59) + "." + mensagem.substring(59, 61)));

                        //// a.setDataHora(new Timestamp(new Date().getTime()));
                        if (v != null) {
                            anterior = VeiculosDB.buscaHodometroHorimetroAnterior(v, a.getDataHora());
                        } else {
                            anterior = 0L;
                        }

                        encerranteAnterior = Double.parseDouble(mensagem.substring(61, 69) + "." + mensagem.substring(69, 71));

                        a.setEncerranteAnterior(encerranteAnterior);
                        a.setTotalPagar(a.getPrecoUnitario() * a.getVolume());

                        if (v != null) {
                            //switch (a.getVeiculo().getTipo()) {
                            if (a.getVeiculo().getTipo().equals("O")) {
                                //case "O":
                                a.setHodometroAnterior(anterior);
                                if ((anterior == 0) || ((marcador - anterior) / a.getVolume() < 0)) {
                                } else if (a.getVolume() == 0) {
                                    a.setKmMedio(0.0);
                                } else {
                                    a.setKmMedio((marcador - anterior) / a.getVolume());
                                }
                                //break
                            } else {
                                //case "H":
                                a.setHorimetroAnterior(anterior);
                                if ((anterior == 0) || (a.getVolume() / (marcador - anterior) < 0)) {
                                } else if (a.getVolume() == 0) {
                                    a.setTempoMedio(0.0);
                                } else {
                                    a.setTempoMedio(a.getVolume() / (marcador - anterior));
                                }
                                //}break;
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

                        boolean bAbastec = AbastecimentosDB.salvaAbastecimentoSimpl(a);
                        if (bAbastec) {
                            ImprimirCupom.imprimirCupom(a, 0);
                        }
                        if (v != null) {
                            VeiculosDB.atualizaMarcadorVeiculo(a.getVeiculo().getSeqVeiculo(), marcador, a.getVeiculo().getTipo());
                        }

                        //if (abastecDireto.equals("N")) {
                        if (v != null) {
                            FrotasDB.atualizaLimiteDisponivel(a.getVeiculo().getFrota().getSeqFrota(), a.getVolume());
                            VeiculosDB.atualizaLimiteRestante(a.getVeiculo().getSeqVeiculo(), a.getVolume());
                        }

                        //}
                        //System.out.println("Mensagem--> " + mensagem.substring(33, 35));
                        limpaGlobalDadosTela(mensagem.substring(33, 35));
                    }
                }
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(mensagem, "String Abastecimento");
        }
    }

    private void limpaGlobalDadosTela(String idCBC) {
        try {
            if (idCBC.equals("04") && (GlobalParameter.getDadosTelaBico04() != null)) {
                GlobalParameter.setDadosTelaBico04(null);
            } else if (idCBC.equals("44") && (GlobalParameter.getDadosTelaBico44() != null)) {
                GlobalParameter.setDadosTelaBico44(null);
            } else if (idCBC.equals("05") && (GlobalParameter.getDadosTelaBico05() != null)) {
                GlobalParameter.setDadosTelaBico05(null);
            } else if (idCBC.equals("45") && (GlobalParameter.getDadosTelaBico45() != null)) {
                GlobalParameter.setDadosTelaBico45(null);
            } else if (idCBC.equals("08") && (GlobalParameter.getDadosTelaBico08() != null)) {
                GlobalParameter.setDadosTelaBico08(null);
            } else if (idCBC.equals("48") && (GlobalParameter.getDadosTelaBico48() != null)) {
                GlobalParameter.setDadosTelaBico48(null);
            } else if (idCBC.equals("09") && (GlobalParameter.getDadosTelaBico09() != null)) {
                GlobalParameter.setDadosTelaBico09(null);
            } else if (idCBC.equals("49") && (GlobalParameter.getDadosTelaBico49() != null)) {
                GlobalParameter.setDadosTelaBico49(null);
            } else if (idCBC.equals("0C") && (GlobalParameter.getDadosTelaBico0C() != null)) {
                GlobalParameter.setDadosTelaBico0C(null);
            } else if (idCBC.equals("4C") && (GlobalParameter.getDadosTelaBico4C() != null)) {
                GlobalParameter.setDadosTelaBico4C(null);
            } else if (idCBC.equals("0D") && (GlobalParameter.getDadosTelaBico0D() != null)) {
                GlobalParameter.setDadosTelaBico0D(null);
            } else if (idCBC.equals("4D") && (GlobalParameter.getDadosTelaBico4D() != null)) {
                GlobalParameter.setDadosTelaBico4D(null);
            } else if (idCBC.equals("10") && (GlobalParameter.getDadosTelaBico10() != null)) {
                GlobalParameter.setDadosTelaBico10(null);
            } else if (idCBC.equals("50") && (GlobalParameter.getDadosTelaBico50() != null)) {
                GlobalParameter.setDadosTelaBico50(null);
            } else if (idCBC.equals("11") && (GlobalParameter.getDadosTelaBico11() != null)) {
                GlobalParameter.setDadosTelaBico11(null);
            } else if (idCBC.equals("51") && (GlobalParameter.getDadosTelaBico51() != null)) {
                GlobalParameter.setDadosTelaBico51(null);
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
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

//    public static double roundToDecimals(double d, int c) {
//        int temp = (int) ((d * Math.pow(10, c)));
//        return (((double) temp) / Math.pow(10, c));
//    }
}
