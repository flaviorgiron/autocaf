package ctr;

import util.GeraLog;
import util.JConfirmMessage;
import view.movimentacao.SDIPrincipalGUI;

public class ConsultaStatusBico implements Runnable {

    Cbc cbcStatus = GlobalParameter.getInstance().getCbcStatus();

    @Override
    public void run() {
        try {
            String retorno = "";
            while (true) {
                if (cbcStatus.isConectado()) {
                    Thread.sleep(1000);
                    retorno = cbcStatus.realTimeStatusBicos();
                    //System.out.println(retorno);
                    if (!retorno.equals("")) {
                        GlobalParameter.setStatusBico04(retorno.substring(1, 2));
                        GlobalParameter.setStatusBico44(retorno.substring(2, 3));

                        GlobalParameter.setStatusBico05(retorno.substring(2, 3));//05//06
                        GlobalParameter.setStatusBico45(retorno.substring(6, 7));

                        GlobalParameter.setStatusBico08(retorno.substring(9, 10));
                        GlobalParameter.setStatusBico48(retorno.substring(10, 11));

                        GlobalParameter.setStatusBico09(retorno.substring(6, 7)); //13//14
                        GlobalParameter.setStatusBico49(retorno.substring(14, 15));

                        GlobalParameter.setStatusBico0C(retorno.substring(17, 18));
                        GlobalParameter.setStatusBico4C(retorno.substring(18, 19));

                        GlobalParameter.setStatusBico0D(retorno.substring(21, 22));
                        GlobalParameter.setStatusBico4D(retorno.substring(22, 23));

                        GlobalParameter.setStatusBico10(retorno.substring(25, 26));
                        GlobalParameter.setStatusBico50(retorno.substring(26, 27));

                        GlobalParameter.setStatusBico11(retorno.substring(29, 30));
                        GlobalParameter.setStatusBico51(retorno.substring(30, 31));

                        if (SDIPrincipalGUI.jtfDataSistema != null) {
                            SDIPrincipalGUI.atualizaStatusBicos();
                        }
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
}
