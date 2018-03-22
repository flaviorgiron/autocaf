package ctr;

import util.GeraLog;
import util.JConfirmMessage;
import view.movimentacao.SDIPrincipalGUI;

public class RealTimeComboio implements Runnable {

    Cbc cbc = GlobalParameter.getInstance().getCbcComboio();

    @Override
    public void run() {
        int i = 0;
        try {
            while (true) {
                //i = i + 1;
                if (!cbc.realTimeComboio().equals("")) {
                    if (SDIPrincipalGUI.jtfDataSistema != null) {
                        SDIPrincipalGUI.carregaAbastecimentos();
                        Thread.sleep(500);
                        SDIPrincipalGUI.carregaAbastecimentos();
                        Thread.sleep(500);
                        SDIPrincipalGUI.carregaAbastecimentos();
                        Thread.sleep(500);
                    }
                    cbc.avancaRegistroComboio();
                    i = 0;
                } else {
                    i = i + 1;
                    //System.out.println("" + i);
                    if (i > 10) {
                        JConfirmMessage.showMessageDialog("Fim da Sincronização.", "");
                        GlobalParameter.setComboioSinc(null);
                        break;
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
