package ctr;

import util.GeraLog;
import util.JConfirmMessage;
import view.movimentacao.SDIPrincipalGUI;

public class AtualizaGridAbastecimentos implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(30000);
                if (SDIPrincipalGUI.jtfDataSistema != null) {
                    SDIPrincipalGUI.carregaAbastecimentos();
                        Thread.sleep(1000);
                        SDIPrincipalGUI.carregaAbastecimentos();
                        Thread.sleep(1000);
                        SDIPrincipalGUI.carregaAbastecimentos();
                        Thread.sleep(1000);
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
