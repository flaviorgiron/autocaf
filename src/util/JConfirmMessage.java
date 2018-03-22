package util;

import ctr.GlobalParameter;
import idioma.Idioma;
import javax.swing.JOptionPane;

public class JConfirmMessage {

    private static Idioma i;

    public JConfirmMessage() {
    }

    public static int showOptionDialog(String title, String message) {
        i = GlobalParameter.getIdioma();
        String[] textMessages = {i.getProperty("sis14"), i.getProperty("sis15")};
        return JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, textMessages, null);
    }

    public static int showOptionDialog(String title, String message, boolean cancel) {
        i = GlobalParameter.getIdioma();
        String[] textMessages = {i.getProperty("sis14"), i.getProperty("sis15"), i.getProperty("sis16")};
        return JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, textMessages, null);
    }

    public static void showMessageDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, 1);
    }
}
