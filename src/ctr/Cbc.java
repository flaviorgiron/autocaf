package ctr;

import idioma.*;
import java.io.*;
import java.net.*;
import util.*;

public class Cbc {

    private Socket socket;
    private Socket socketComboio;

    private BufferedReader bin;
    private BufferedReader binComboio;

    private BufferedWriter bout;
    private BufferedWriter boutComboio;

    private PrintWriter pout;
    private PrintWriter poutComboio;

    private Fila filaCompanyTec = null;
    private Fila filaComboio = null;
    private String retornoAnteriorRead = "", retornoAnteriorAbastec = "";

    //OK
    Cbc() throws IOException {
        this.socket = new Socket();
        this.socketComboio = new Socket();
    }

    //OK
    public void write(String st) throws IOException {
        try {
            this.pout.println(st);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
        }
    }

    //OK
    public void writeComboio(String st) throws IOException {
        this.poutComboio.println(st);
    }

//    public void readBicos() {
//        try {
//            char buf[] = new char[100];
//            while (!bin.ready()) {
//            }
//            bin.read(buf);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //OK
    public void escreveComando(String comando, int timeout) throws InterruptedException, IOException {
        //String rta = "";

        write(comando);
        long fim = System.currentTimeMillis() + timeout;
        while (!this.bin.ready()) {
        }
        while (fim > System.currentTimeMillis()) {
            //Thread.sleep(timeout);
//            char chRec = (char) this.bin.read();
//            rta = rta + chRec;
//            if (chRec == '(') {
//                rta = "(";
//            }
//            if (chRec == ')') {
//                break;
//            }
        }
//        return rta;
    }

    public void escreveComandoTeste(String comando, int timeout) throws InterruptedException, IOException {
        //String rta = "";

        write(comando);
        long fim = System.currentTimeMillis() + timeout;
        //while (!this.bin.ready()) {
        //}
        while (fim > System.currentTimeMillis()) {
            //Thread.sleep(timeout);
//            char chRec = (char) this.bin.read();
//            rta = rta + chRec;
//            if (chRec == '(') {
//                rta = "(";
//            }
//            if (chRec == ')') {
//                break;
//            }
        }
//        return rta;
    }

    //OK
    public void escreveComandoComboio(String comando, int timeout) throws InterruptedException, IOException {
        //String rta = "";

        writeComboio(comando);
        long fim = System.currentTimeMillis() + timeout;
        //while (!this.binComboio.ready()) {
        //}
        while (fim > System.currentTimeMillis()) {
            //Thread.sleep(timeout);
//            char chRec = (char) this.bin.read();
//            rta = rta + chRec;
//            if (chRec == '(') {
//                rta = "(";
//            }
//            if (chRec == ')') {
//                break;
//            }
        }
//        return rta;
    }

    public String escreveLeComandoStatus(String comando, int timeout) throws InterruptedException, IOException {
        String rta = "";
        String retorno = "";

        write(comando);
        long fim = System.currentTimeMillis() + timeout;
        while (!this.bin.ready()) {
        }
        while (fim > System.currentTimeMillis()) {
            //Thread.sleep(timeout);
            char chRec = (char) this.bin.read();
            rta = rta + chRec;
            if (chRec == '(') {
                rta = "(";
            }
            if (chRec == ')') {
                break;
            }
        }
        //System.out.println(rta);
        retorno = rta.replace("(0)", "").replace("(", "").replace(")", "").trim();
        if (!retorno.equals("")) {
            if (retorno.substring(0, 1).equals("S")) {
                return retorno;
            }
        }
        return "";
    }

    //OK
    public String buscaAbastecimentoComboio(String comando, int timeout) throws InterruptedException, IOException {
        String retorno = "";

        writeComboio(comando);
        long fim = System.currentTimeMillis() + timeout;
        while (!this.binComboio.ready()) {
        }
        while (fim > System.currentTimeMillis()) {
            //Thread.sleep(timeout);
            char chRec = (char) this.binComboio.read();
            retorno = retorno + chRec;
            if (chRec == '(') {
                retorno = "(";
            }
            if (chRec == ')') {
                break;
            }
        }

        retorno = retorno.replace("(0)", "").replace("(", "").replace(")", "").trim();

        if (!retorno.equals("")) {
            if (retorno.substring(0, 1).equals("a")) {
                if (retorno.trim().length() >= 50) {
                    if (retornoAnteriorAbastec.equals("")) {
                        filaComboio.insira(retorno);
                        //System.out.println(retorno);
                    } else if (!retornoAnteriorAbastec.equals(retorno)) {
                        filaComboio.insira(retorno);
                        //System.out.println(retorno);
                    }
                    retornoAnteriorAbastec = retorno;
                }
                return retorno;
            } else {
                return "";
            }
        } else {
            return "";

        }
    }

    //OK
    public String buscaAbastecimento(String comando, int timeout) throws InterruptedException, IOException {
        String retorno = "";

        write(comando);
        long fim = System.currentTimeMillis() + timeout;
        while (!this.bin.ready()) {
        }
        while (fim > System.currentTimeMillis()) {
            //Thread.sleep(timeout);
            char chRec = (char) this.bin.read();
            retorno = retorno + chRec;
            if (chRec == '(') {
                retorno = "(";
            }
            if (chRec == ')') {
                break;
            }
        }

        retorno = retorno.replace("(0)", "").replace("(", "").replace(")", "").trim();

        if (!retorno.equals("")) {
            //System.out.println(retorno);
            if (retorno.substring(0, 1).equals("a")) {
                if (retorno.trim().length() >= 10) {
                    if (retornoAnteriorAbastec.equals("")) {
                        filaCompanyTec.insira(retorno);
                        //System.out.println(retorno);
                    } else if (!retornoAnteriorAbastec.equals(retorno)) {
                        filaCompanyTec.insira(retorno);
                        //System.out.println(retorno);
                    }
                    retornoAnteriorAbastec = retorno;
                }
                return retorno;
            } else {
                return "";
            }
        } else {
            return "";

        }
    }

    //    public boolean lerAbastec() {
    //        try {
    //            String retorno;
    //            char buf[] = new char[125];
    //            while (!bin.ready()) {
    //            }
    //            bin.read(buf);
    //
    //            retorno = new String(buf).replace("(0)", "").replace("(", "").replace(")", "").trim();
    //
    //            if (retorno.trim().length() >= 50) {
    //                if (retornoAnteriorAbastec.equals("")) {
    //                    filaCompanyTec.insira(retorno);
    //                    //System.out.println(retorno);
    //                } else if (!retornoAnteriorAbastec.equals(retorno)) {
    //                    filaCompanyTec.insira(retorno);
    //                    //System.out.println(retorno);
    //                }
    //                retornoAnteriorAbastec = retorno;
    //
    //                return true;
    //            } else {
    //                return false;
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            return false;
    //        }
    //    }
    //OK
    public void conectarDispositivo(String st, int pPorta) {
        Idioma i = GlobalParameter.getIdioma();
        try {

            this.socket = new Socket(st, pPorta);
            this.bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bout = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.pout = new PrintWriter(socket.getOutputStream(), true);
            this.filaCompanyTec = GlobalParameter.getInstance().getFilaCompanyTec();
            //this.filaComboio = GlobalParameter.getInstance().getFilaComboio();
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    public void conectarDispositivoComboio(String st, int pPorta) {
        Idioma i = GlobalParameter.getIdioma();
        try {

            this.socketComboio = new Socket(st, pPorta);
            this.binComboio = new BufferedReader(new InputStreamReader(socketComboio.getInputStream()));
            this.boutComboio = new BufferedWriter(new OutputStreamWriter(socketComboio.getOutputStream()));
            this.poutComboio = new PrintWriter(socketComboio.getOutputStream(), true);
            //this.filaCompanyTec = GlobalParameter.getInstance().getFilaCompanyTec();
            this.filaComboio = GlobalParameter.getInstance().getFilaComboio();
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(i.getProperty("sis3"), i.getProperty("sis1"));
            e.printStackTrace();
        }
    }

    //OK
    public void desconetarDispositivo() {
        Idioma i = GlobalParameter.getIdioma();
        try {
            if (socket.isConnected()) {
                pout.flush();
                //s.shutdownInput();
                //s.shutdownOutput();
                bout.close();
                bin.close();
                socket.close();
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(i.getProperty("sis4"), i.getProperty("sis1"));
        }
    }

    //OK
    public boolean isConectado() {
        return socket.isConnected();
    }

    //OK
    public boolean isConectadoComboio() {
        return socketComboio.isConnected();
    }

    //OK
    private String addChecksum(String st) {
        Idioma i = GlobalParameter.getIdioma();
        try {
            int ac = 0;
            String check;
            for (int a = 0; a < st.length(); a++) {
                ac = ac + st.codePointAt(a);
            }
            check = Integer.toHexString(ac);
            check = check.toUpperCase();
            if (check.length() < 2) {
                check = "0" + check;
            } else if (check.length() > 2) {
                check = (String) check.subSequence(check.length() - 2, check.length());
            }
            return "(" + st + check + ")";
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(i.getProperty("sis5"), i.getProperty("sis1"));
            return "";
        }
    }

    //OK
    public void alteraPreco(String bico, String preco) throws InterruptedException, IOException {
        try {
            String comando = addChecksum("&U" + bico + "00" + preco);
            escreveComando(comando, 1000);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    //OK
    public void ajusteRelogio(String dia, String hora, String minuto) {
        try {
            String comando = addChecksum("&H" + dia + hora + minuto);
            escreveComando(comando, 500);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    //OK
    public void ajusteRelogioExtendido(String dia, String mes, String ano, String hora, String minuto, String segundo, String semana) {
        try {
            String comando = addChecksum("&KW1" + ano + mes + dia + semana + hora + minuto + segundo);
            escreveComando(comando, 500);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    //OK
    public void bloqueiaBico(String bico) {
        try {
            String comando = addChecksum("&M" + bico + "B");
            escreveComando(comando, 1500);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //e.printStackTrace();
        }
    }

    //OK
//    public void liberaBico(String bico) {
//        try {
//            String comando = addChecksum("&M" + bico + "L");
//            escreveComando(comando, 1000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //OK
    public void liberaUmAbastecimento(String bico) {
        try {
            String comando = addChecksum("&M" + bico + "A");
            //System.out.println(comando);
            escreveComando(comando, 1000);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    //OK
//    public void pararAbastecimento(String bico) throws InterruptedException, IOException {
//        try {
//            String comando = addChecksum("&M" + bico + "P");
//            escreveComando(comando, 1000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void verAbastecimentoAndamento() throws InterruptedException, IOException {
//        try {
//            String comando = "(&V)";
//            write(comando);
//            //------>>>>>>read();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //OK
    public String realTimeStatusBicos() throws InterruptedException, IOException {
        try {
            String comando = "(&S)";
            return escreveLeComandoStatus(comando, 1000);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            return "";
        }
    }

    //OK
    public void avancaRegistro() {
        try {
            String comando = "(&I)";
            escreveComandoTeste(comando, 1000);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    //OK
    public void avancaRegistroComboio() {
        try {
            String comando = "(&I)";
            escreveComandoComboio(comando, 1000);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    public String realTimeComboio() {
        try {
            Thread.sleep(1500);
            String comando = addChecksum("&A2");
            return buscaAbastecimentoComboio(comando, 2000);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            return "";
        }
    }

    //OK
    public String consultaAbastecimento() {
        try {
            String comando = addChecksum("&A2");
            return buscaAbastecimento(comando, 1500);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            return "";
        }
    }

    //OK
    public void determinarValorAbastecimento(String bico, String valor) throws InterruptedException, IOException {
        try {
            if (valor.trim().length() == 6) {
                String comando = addChecksum("&P" + bico + valor);
                escreveComando(comando, 1000);
            } else {
                //System.out.println("Valor com quantidade menor de 6 caracteres");
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    //OK
//    public void leituraTotaisEncerramentesValor(String bico) throws InterruptedException, IOException {
//        try {
//            String comando = addChecksum("&T" + bico + "$");
//            escreveComando(comando, 2000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //OK
//    public void leituraTotaisEncerramentesLitros(String bico) throws InterruptedException, IOException {
//        try {
//            String comando = addChecksum("&T" + bico + "L");
//            escreveComando(comando, 2000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //OK
//    public void leituraTotaisExtendidoLitros(String bico) throws InterruptedException, IOException {
//        try {
//            String comando = addChecksum("&T" + bico + "I");
//            escreveComando(comando, 2000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //OK
//    public void limpezaMemoriaIdentificadores() throws InterruptedException, IOException {
//        try {
//            String comando = addChecksum("&F");
//            escreveComando(comando, 2000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
