package cupom;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface BematechNFiscal extends Library {

    public BematechNFiscal Instance = (BematechNFiscal) Native.loadLibrary("mp2064", BematechNFiscal.class);

    public int ConfiguraModeloImpressora(int modelo) throws Exception;

    public int IniciaPorta(String porta) throws Exception;

    public int FechaPorta() throws Exception;

    public int FormataTX(String BufTras, int tipoletra, int italic, int sublin, int expand, int enfat) throws Exception;

    public int ImprimeBitmap(String bitmap, int orientacao) throws Exception;

    public int AcionaGuilhotina(int i);
}
