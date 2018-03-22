package ctr;

import java.util.Objects;
import util.GeraLog;
import util.JConfirmMessage;
import view.movimentacao.SDIPrincipalGUI;

public class ConsultaAbastecimento implements Runnable {

    Cbc cbc = GlobalParameter.getInstance().getCbc();
    private static Integer diaAnterior;
    private static Integer diaSemana;

    @Override
    public void run() {
        try {
            diaAnterior = 18;

            while (true) {
                diaSemana = GlobalParameter.getDiaSemana();
                if (!Objects.equals(diaAnterior, diaSemana)) {
                    diaAnterior = diaSemana;
                    AtualizaLimiteSemanal.atualizaLimite();
                }
                if (cbc.isConectado()) {
                    Thread.sleep(1000);
                    if (SDIPrincipalGUI.jtfDataSistema != null) {
                        SDIPrincipalGUI.jtfDataSistema.setText(GlobalParameter.getInstance().getDataSistema());
                    }
                    if (!cbc.consultaAbastecimento().equals("")) {
                        if (SDIPrincipalGUI.jtfDataSistema != null) {
                            SDIPrincipalGUI.carregaAbastecimentos();
                            Thread.sleep(500);
                            SDIPrincipalGUI.carregaAbastecimentos();
                            Thread.sleep(500);
                            SDIPrincipalGUI.carregaAbastecimentos();
                            Thread.sleep(500);
                            SDIPrincipalGUI.carregaAbastecimentos();
                            Thread.sleep(500);
                            SDIPrincipalGUI.carregaAbastecimentos();
                            Thread.sleep(500);
                            SDIPrincipalGUI.carregaAbastecimentos();
                            Thread.sleep(500);
                            SDIPrincipalGUI.carregaAbastecimentos();
                            Thread.sleep(500);
                        }
                        cbc.avancaRegistro();
                        Thread.sleep(500);
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
