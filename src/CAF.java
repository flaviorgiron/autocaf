
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Properties;
import javax.swing.UIManager;
import ctr.GlobalParameter;
import util.GeraLog;
import util.JConfirmMessage;
import view.movimentacao.*;

public class CAF {

    public static void main(String args[]) throws Exception {
        try {
            File f = new File(".lock");
            f.createNewFile();
            //GlobalParameter.getInstance();
            GlobalParameter.setCaminhoAplicacao(f.getAbsolutePath().replace(".lock", ""));
            FileLock lock = new RandomAccessFile(f, "rw").getChannel().tryLock();
            if (lock != null) {

                Properties properties = new Properties();
                FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
                properties.load(fis);
                GlobalParameter.setIdioma(properties.getProperty("sis_idioma").trim());

                //inicialização modo simples
                if (properties.getProperty("sis_inicializacao").trim().equals("1")) {
                    AbastecimentoGUI.main(args);
                } else if (properties.getProperty("sis_inicializacao").trim().equals("3")) {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    SDIPrincipalGUI acesso = new SDIPrincipalGUI();
                    acesso.setLocationRelativeTo(null);
                    acesso.setVisible(true);
                } else {
                    ////UIManager.setLookAndFeel(new WindowsLookAndFeel());
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    AcessoSistema acesso = new AcessoSistema();
                    //SDIPrincipalGUI acesso = new SDIPrincipalGUI();
                    acesso.setLocationRelativeTo(null);
                    acesso.setVisible(true);
                }
            } else {
                JConfirmMessage.showMessageDialog("Programa já em execução.", "Atenção");
                System.exit(0);
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            System.exit(0);
        }
    }
}
