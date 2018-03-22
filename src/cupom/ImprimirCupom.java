package cupom;

import ctr.GlobalParameter;
import java.io.FileInputStream;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Properties;
import model.Abastecimento;
import util.GeraLog;

public class ImprimirCupom {

//    public static void main(String[] args) {
//        imprimirCupom(new Abastecimento());
//    }
    public static String removerAcentos(String str) {
        try {
            if (str != null) {
                if (!str.equals("")) {
                    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                } else {
                    return "";
                }
            } else {
                return "";
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return "";
        }
    }

    public static boolean imprimirCupom(Abastecimento a, Integer origem) {
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            //FileInputStream fis = new FileInputStream("D:\\Projetos\\AutoCAF\\" + "config.properties");

            properties.load(fis);
            if (properties.getProperty("sis_imprime_cupom").trim() != null) {
                if (properties.getProperty("sis_imprime_cupom").trim().toUpperCase().equals("S")) {
                    if (properties.getProperty("sis_porta_impressora_cupom").trim() != null) {
                        int iRetorno;
                        Integer qtdeVias = 1;
                        if (origem == 0) { // 0 = automatico //1 = reimprimir
                            if (properties.getProperty("sis_cupom_qtdevias").trim() != null) {
                                qtdeVias = Integer.parseInt(properties.getProperty("sis_cupom_qtdevias").trim());
                                if (qtdeVias == 0) {
                                    qtdeVias = 1;
                                }
                            }
                        }
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

                        for (int i = 1; i <= qtdeVias; i++) {
                            BematechNFiscal cupom = BematechNFiscal.Instance;

                            cupom.ConfiguraModeloImpressora(5);

                            iRetorno = cupom.IniciaPorta(properties.getProperty("sis_porta_impressora_cupom").trim());

                            if (iRetorno != 0) {
                                if (a.getVeiculo() != null) {
                                    cupom.FormataTX("Empresa: " + removerAcentos(a.getVeiculo().getFrota().getEmpresa().getNomeEmpresa()) + "\r\n\r\n", 2, 0, 0, 0, 1);
                                } else {
                                    cupom.FormataTX("Empresa: \r\n\r\n", 2, 0, 0, 0, 1);
                                }

                                cupom.FormataTX("Data: " + formato.format(a.getDataHora()), 2, 0, 0, 0, 0);
                                cupom.FormataTX("  Hora: " + formatoHora.format(a.getDataHora()) + "\r\n\r\n", 2, 0, 0, 0, 0);
                                cupom.FormataTX("Recibo: " + a.getNumeroAbastecimento().toString() + "\r\n", 2, 0, 0, 0, 0);

                                if (a.getBico() != null) {
                                    cupom.FormataTX("Bico: " + removerAcentos(a.getBico().getNomeBico()) + "\r\n", 2, 0, 0, 0, 0);
                                } else {
                                    cupom.FormataTX("Bico: \r\n", 2, 0, 0, 0, 0);
                                }

                                if (a.getBico() != null) {
                                    cupom.FormataTX("Produto: " + removerAcentos(a.getBico().getCombustivel().getNomeCombustivel()) + "\r\n", 2, 0, 0, 0, 0);
                                } else {
                                    cupom.FormataTX("Produto: \r\n", 2, 0, 0, 0, 0);
                                }

                                cupom.FormataTX("Quantidade: " + a.getVolume().toString().replace(".", ",") + " Lts\r\n", 2, 0, 0, 0, 0);
                                if (a.getVeiculo() != null) {
                                    cupom.FormataTX("Veiculo: " + removerAcentos(a.getVeiculo().getNomeVeiculo()) + "\r\n", 2, 0, 0, 0, 0);
                                } else {
                                    cupom.FormataTX("Veiculo: \r\n", 2, 0, 0, 0, 0);
                                }
                                if (a.getVeiculo() != null) {
                                    if (a.getVeiculo().getTipo() != null) {
                                        if (a.getVeiculo().getTipo().equals("O")) {
                                            cupom.FormataTX("Odometro: " + a.getHodometro() + "\r\n", 2, 0, 0, 0, 0);
                                        } else {
                                            cupom.FormataTX("Horimetro: " + a.getHodometro() + "\r\n", 2, 0, 0, 0, 0);
                                        }
                                    } else {
                                        cupom.FormataTX("Odometro/Horimetro:\r\n", 2, 0, 0, 0, 0);
                                    }
                                } else {
                                    cupom.FormataTX("Odometro/Horimetro: \r\n", 2, 0, 0, 0, 0);
                                }

                                if (a.getVeiculo() != null) {
                                    cupom.FormataTX("Frota: " + removerAcentos(a.getVeiculo().getFrota().getNomeFrota()) + "\r\n", 2, 0, 0, 0, 0);
                                } else {
                                    cupom.FormataTX("Frota: \r\n", 2, 0, 0, 0, 0);
                                }

                                if (a.getOperador() != null) {
                                    cupom.FormataTX("Operador: " + removerAcentos(a.getOperador().getNomeOperador()) + "\r\n", 2, 0, 0, 0, 0);
                                } else {
                                    cupom.FormataTX("Operador: \r\n", 2, 0, 0, 0, 0);
                                }

                                if (properties.getProperty("sis_controla_motorista").trim() != null) {
                                    if (properties.getProperty("sis_controla_motorista").trim().toUpperCase().equals("S")) {
                                        cupom.FormataTX("Assinatura: __________________________________\r\n", 2, 0, 0, 0, 0);
                                        if (a.getMotorista() != null) {
                                            cupom.FormataTX("Motorista: " + removerAcentos(a.getMotorista().getNomeMotorista()) + "\r\n", 2, 0, 0, 0, 0);
                                        } else {
                                            cupom.FormataTX("Motorista: \r\n", 2, 0, 0, 0, 0);
                                        }
                                    }
                                }
                                cupom.FormataTX("\r\nAUTOCAF: www.autocaf.com.br", 2, 1, 0, 0, 0);

                                cupom.FormataTX("\r\n\r\n", 2, 0, 0, 0, 0);
                                cupom.AcionaGuilhotina(0);
                                //cupom.FormataTX("" + (char) 27 + (char) 109, 2, 0, 0, 0, 0);

                                cupom.FechaPorta();

                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            return false;
        }
    }
}
