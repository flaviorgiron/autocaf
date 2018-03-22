package util;


import java.awt.Dimension;
import java.awt.Toolkit;

public class CapturaTamanhoTela {

    public static int getWidthMonitor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = screenSize.width;
        return x;// - 100;
    }

    public static int getHeightMonitor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int y = screenSize.height;
        return y;// - 100;
    }
}
