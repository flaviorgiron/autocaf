package view.movimentacao;

import ctr.GlobalParameter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.net.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.reflect.Method;
import java.util.Properties;
import util.GeraLog;

public class InformacoesCBC extends javax.swing.JFrame {

    public static enum ETCP {
        TX, RX;
    }

    public static enum ETWC {
        STATUS, LOOP;
    }

    public static enum ETWCSTATUS {
        TX, RX;
    }

    public static enum ETERMINAL {
        TX, RX;
    }

    public static enum ETERMINALCMDS {
        PENDENCIA, LE_INFO;
    }

    public static enum MENU_TERMINAL {
        AUTOCAF;
    }

    public enum ECONNECTION {
        CONNECT, DISCONNECT, RECONNECT, WAIT;
    }

    public static enum PROTHORUS {
        STX, INIT, GETLEN, GETDATA, GETCHECK;
    }

    public static enum CODTCP {
        isTX, isRX, isTOUT, isNda
    }

    public static class VERSION {

        public static String Menu = "2.0";
    }

    public static enum TOUT {
        CONNECT(10), TX(500), RX(100), RXStatusTwc(1000), TXStatusTwc(500), RXPendencyTWC(10);

        public final int val;

        TOUT(int value) {
            this.val = value;
        }
    }

    public Horus Hrs;

    public class Reminder {

        Timer timer;
        int time;
        Object clas;
        Method func;
        Object[] arg;

        public Reminder(int miliseconds, Object o, Method m, Object[] args) {
            time = miliseconds;
            clas = o;
            func = m;
            arg = args;
            timer = new Timer();
            timer.schedule(new RemindTask(), miliseconds * 1);
        }

        class RemindTask extends TimerTask {

            public void run() {
                try {
                    func.invoke(clas, arg);
                } catch (Exception e) {
                    int a;
                    a = 10;
                }
                timer.cancel();
                new Reminder(time, clas, func, arg); //reinicia timer
            }
        }
    }

    public class Tick {

        public int Count;

        public Tick() {
            Count = 0;
        }

        public int GetTick() {
            return Count;
        }

        public boolean TickCompare(int val) {
            if (val - GetTick() <= 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public class Tcp {

        String Ip;
        int Port;
        ETCP Sm;
        Tick Tout;
        boolean is_connected, is_busy, PckgRecept;
        OutputStreamWriter ps = null;
        Socket clientSocket = null;
        int TimetoTx, TimetoRx;
        String BuffTx, BuffRx;

        public Tcp(String ip, int Porta) {
            Ip = ip;
            Sm = ETCP.TX;
            Port = Porta;
            TimetoTx = 0;
            TimetoRx = 0;
            Tout = new Tick();
            BuffTx = null;
            BuffRx = null;
        }

        public CODTCP Task() {
            Tout.Count++;
            switch (Sm) {
                case TX:
                    if (Tout.TickCompare(TimetoTx)) {
                        TimetoTx = Tout.GetTick() + TOUT.TX.val;
                        TimetoRx = Tout.GetTick() + TOUT.RX.val;
                        if (is_connected && BuffTx != null && is_busy) {

                            if (Write(BuffTx)) {
                                is_busy = false;
                                Sm = ETCP.RX;
                                return CODTCP.isTX;
                            }
                        }
                    }
                    break;
                case RX:
                    if (!Tout.TickCompare(TimetoRx)) {
                        BuffRx = Read();
                        if (BuffRx != null) {
                            PckgRecept = true;
                            Sm = ETCP.TX;
                            return CODTCP.isRX;
                        }
                    } else {
                        Sm = ETCP.TX;
                        return CODTCP.isTOUT;
                    }
                    break;
            }
            return CODTCP.isNda;
        }

        public boolean Connect() {
            try {
                clientSocket = new Socket(Ip, Port);
                is_connected = true;
                return true;
            } catch (Exception e) {
                is_connected = false;
                return false;
            }
        }

        public boolean Write(String cmd) {
            try {
                ps = new OutputStreamWriter(clientSocket.getOutputStream(), "ISO-8859-1");
                ps.write(cmd, 0, cmd.length());
                ps.flush();
                is_connected = true;
                return true;
            } catch (Exception e) {
                is_connected = false;
                return false;
            }
        }

        public String Read() {
            try {
                if (clientSocket != null) {
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    return entrada.readLine();
                } else {
                    return "Erro";
                }
            } catch (Exception e) {
                return "Erro";
            }
        }

        public boolean Close() {
            try {
                clientSocket.close();
                is_connected = false;
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public class Grade {

        JTable Tab;
        DefaultTableModel Modelo;
        boolean On;

        public Grade(JTable Tabela, DefaultTableModel model) {
            On = true;
            Modelo = model;
            Tab = Tabela;
            Tab.setModel(Modelo);
        }

        public void Write(String Idx, String Cmd1, String Cmd2) {
            if (On) {
                Modelo.addRow(new String[]{Idx, Cmd1, Cmd2});
                Tab.setModel(Modelo);
                Tab.clearSelection();
                Tab.changeSelection(Tab.getRowCount() - 1, 0, false, false);
            }
        }
    }

    public class HrsProt {

        boolean PckgOk;
        int RxCount = 0, RxLenCount = 0, RxDataCount = 0, RxCkCount = 0, RxLen;
        byte RxData;
        int RxChecksumAcc;
        String data, RxCheckSum;
        char RxLenAcc[];
        PROTHORUS Sm;

        public HrsProt() {
            Sm = PROTHORUS.STX;
            RxLen = 0;
            RxLenCount = 0;
        }

        public String Parser(String Buff) {
            data = "";
            RxCheckSum = "";
            RxCount = 0;
            int len = Buff.length();
            while (RxCount < len) {
                RxData = (byte) Buff.charAt(RxCount);
                RxCount++;
                switch (Sm) {
                    case STX:
                        if (RxData == '>') {
                            Sm = PROTHORUS.INIT;
                        }
                        break;
                    case INIT:
                        if (RxData == '!') {
                            Sm = PROTHORUS.GETLEN;
                            RxChecksumAcc = RxData;
                            RxLenAcc = new char[4];
                        }
                        break;
                    case GETLEN:
                        RxChecksumAcc += RxData;
                        RxLenAcc[RxLenCount++] = (char) RxData;
                        if (RxLenCount == 4) {
                            String aux = new String(RxLenAcc);
                            RxLen = Integer.parseInt(aux, 16);
                            RxLenCount = 0;
                            if (RxLen != 0) {
                                Sm = PROTHORUS.GETDATA;
                            } else {
                                return "";
                            }
                        }
                        break;
                    case GETDATA:
                        data += Character.toString((char) RxData);
                        RxChecksumAcc += RxData;
                        RxDataCount++;
                        if (RxDataCount == RxLen) {
                            Sm = PROTHORUS.GETCHECK;
                            RxDataCount = 0;
                        }
                        break;
                    case GETCHECK:
                        RxCheckSum += Character.toString((char) RxData);
                        RxCkCount++;
                        if (RxCkCount == 2) {
                            String RxChecksumAccStr = Integer.toString(RxChecksumAcc, 16);
                            int RxChecksumAccStrLen = RxChecksumAccStr.length();
                            RxChecksumAccStr = RxChecksumAccStr.substring(RxChecksumAccStrLen - 2, RxChecksumAccStrLen);
                            if (RxCheckSum.equals(RxChecksumAccStr.toUpperCase())) {
                                Sm = PROTHORUS.STX;
                                RxCkCount = 0;
                                return data;
                            }
                        }
                        break;
                }
            }
            return "";
        }
    }

    public class Terminal {

        public ETERMINAL Sm;
        public ETERMINALCMDS SmCmd;
        public ETERMINALCMDS SmBackCmd;
        public MENU_TERMINAL Menu;
        public int Erro;
        public TOUT Tout;

        public Terminal() {
            Sm = ETERMINAL.TX;
            SmCmd = ETERMINALCMDS.LE_INFO;
            SmBackCmd = ETERMINALCMDS.PENDENCIA;
        }

        public void Task() {

            switch (Sm) {
                case TX:
                    Sm = ETERMINAL.RX;
                    String Numlogic = Hrs.Ajuste(Integer.toString(Hrs.NlogicTwc), '0', 'L', 2);
                    switch (SmCmd) {
                        case PENDENCIA: //OK
                            Hrs.Put("2C" + Numlogic);
                            break;
                        case LE_INFO: //OK
                            Hrs.Put("12");
                            //SmCmd = InformacoesCBC.ETERMINALCMDS.CHECKED;
                            break;
                    }
                    break;
                case RX:
                    if (Hrs.Prot.PckgOk) // recebeu dados ?
                    {
                        Parser(Hrs.DataRx);
                        Hrs.indexTwc++;
                        Sm = ETERMINAL.TX;
                    }
                    break;
            }
        }

        public void Parser(String str) {
            int len = str.length();
            String cmd = str.substring(0, 2);
            String escape = "", index = "", versao = "";
            if (len > 7) {
                escape = str.substring(9, 10);
                index = str.substring(6, 8);
            }
            //System.out.println(cmd);
            if (cmd.equals("2C")) {
                //System.out.println(str);
                if (Erro < Tout.RXPendencyTWC.val) {
                    if (!escape.equals("G")) {
                    } else if (index.equals("22") || index.equals("2A")) { // bico
                    } else if (index.equals("86")) {
                    } else {
                        Erro++;
                    }
                    Erro = 0;
                    //SmCmd = SmBackCmd;
                }
                Erro = 0;
                SmCmd = SmBackCmd;
            } else if ((cmd.equals(
                    "26")) && (SmCmd.name().equals("ESCREVE_VEICULO"))) {
                String valid = str.substring(2, 4);
                char erro = valid.charAt(1);
                if (valid.equals("00")) {
                    //SmCmd = ETERMINALCMDS.LIBERA_BICO;
                } else if (erro == '7') {
                    //SmCmd = ETERMINALCMDS.BICO_INEXISTENTE;
                } else if (erro == '8') {
                    //SmCmd = ETERMINALCMDS.BICO_OCUPADO;
                }
            } else if (cmd.equals(
                    "26")) {
                String valid = str.substring(2, 4);
                char erro = valid.charAt(1);
                if ((valid.equals("00")) && (SmCmd.name().equals("LIBERA_BICO"))) {
                    //SmCmd = ETERMINALCMDS.LIBERA_BICO;
                } else if (valid.equals("00")) {
                    //SmCmd = ETERMINALCMDS.ESCREVE_VEICULO;
                } else if (erro == '7') {
                    //SmCmd = ETERMINALCMDS.BICO_INEXISTENTE;
                } else if (erro == '8') {
                    //SmCmd = ETERMINALCMDS.BICO_OCUPADO;
                }
            } else if (cmd.equals(
                    "2B") || cmd.equals("2E")) {
                String indexaux = str.substring(2, 4);
                if (indexaux.equals("E1") || indexaux.equals("E5")) {
                    //SmCmd = ETERMINALCMDS.LIMPAR;
                }
                Erro = 0;
            }

        }

    }

    public class Horus {

        public Terminal TWC[] = new Terminal[99];
        public Grade Terminal;
        public Tcp Connection;
        public Tick Tout;
        public HrsProt Prot;
        public ECONNECTION ConnectionSm;
        public ETWC TerminalSm;
        public ETWCSTATUS TerminalStatusSm;
        public JTable Tab;
        int TimetoConnection;

        public int TimetoTxStatusTwc, TimetoRxStatusTwc, QtdTwc, indexTwc, NlogicTwc, ListTwc[];

        boolean is_Reconnecting;
        byte TxChecksumAcc;
        char TxData[];
        int TxLen;
        String arr, DataRx;

        public Horus(JTable tabela) {
            String colunas[] = {"Idx", "Cmd1", "Cmd2"};
            DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
            Terminal = new Grade(tabela, modelo);

            for (int count = 0; count < 99; count++) {
                TWC[count] = new Terminal();
            }

            Tout = new Tick();
            ListTwc = new int[99];
            Prot = new HrsProt();
            TimetoConnection = 0;
            is_Reconnecting = false;
            ConnectionSm = ECONNECTION.WAIT;
            TerminalSm = ETWC.STATUS;
            TerminalStatusSm = ETWCSTATUS.TX;
            Method func1;

            try {
                func1 = Horus.class.getMethod("Task", new Class[]{});
            } catch (Exception e) {
                func1 = null;
            }
            if (func1 != null) {
                new Reminder(TimetoConnection, this, func1, new Object[]{});
            }
        }

        public void Task() {
            ConnectionTask();
            CommunicationTask();
            TerminalTask();
            Tout.Count++;
        }

        public void TerminalTask() {
            if (Connection.is_connected) {
                switch (TerminalSm) {
                    case STATUS:
                        QtdTwc = SendStatusTwc();
                        if (QtdTwc != 0) {
                            indexTwc = 0;
                            TerminalSm = ETWC.LOOP;
                        }
                        break;
                    case LOOP:
                        if (indexTwc < QtdTwc) {
                            NlogicTwc = ListTwc[indexTwc];
                            if (NlogicTwc == 0) {
                                NlogicTwc = 1;
                            }
                            TWC[NlogicTwc].Task();
                        } else {
                            TerminalSm = ETWC.STATUS;
                        }
                        break;
                }
            }
        }

        public void ConnectionTask() {
            switch (ConnectionSm) {
                case CONNECT:
                    if (Tout.TickCompare(TimetoConnection)) {
                        TimetoConnection = TimetoConnection + TOUT.CONNECT.val;
                        Connection = new Tcp(jTextField1.getText(), Integer.parseInt((String) jComboBox3.getSelectedItem()));

                        if (Connection.Connect()) {
                            //Terminal.Write("Info", "Conectado", jTextField1.getText() + "/" + (String) jComboBox3.getSelectedItem());
                            is_Reconnecting = false;
                            ConnectionSm = ECONNECTION.WAIT;
                        } else {
                            //Terminal.Write("Info", "Não Conectou", jTextField1.getText() + "/" + (String) jComboBox3.getSelectedItem());
                            if (is_Reconnecting) {
                                ConnectionSm = ECONNECTION.DISCONNECT;
                            }
                            //Inc_Port();
                            Connection.Close();
                        }
                    }
                    break;
                case RECONNECT:
                    is_Reconnecting = true;
                    ConnectionSm = ECONNECTION.DISCONNECT;
                    break;
                case DISCONNECT:
                    Connection.Close();
                    //Terminal.Write("Info", "Desconectado", jTextField1.getText() + "/" + (String) jComboBox3.getSelectedItem());
                    if (is_Reconnecting) {
                        ConnectionSm = ECONNECTION.CONNECT;
                        TimetoConnection = Tout.GetTick();
                    } else {
                        ConnectionSm = ECONNECTION.WAIT;
                    }
                    break;
                case WAIT:

                    break;
            }
        }

        public void CommunicationTask() {
            if (Connection != null) {
                if (Connection.is_connected) {
                    switch (Connection.Task()) {
                        case isTX:
                            //Terminal.Write("Tx", Connection.BuffTx.substring(6, 8), Connection.BuffTx);
                            break;
                        case isRX:
                            /* .: espera a validação do pacote :. */
                            //Terminal.Write("Rx", Connection.BuffRx.substring(6,8), Connection.BuffRx);
                            break;
                        case isTOUT:
                            //Terminal.Write("Info", "Timeout", "Tempo superior a:  " + Integer.toString(TOUT.RX.val));
                            break;
                        case isNda:
                            break;
                    }
                }

                if (Connection.PckgRecept) //possui dados a tratar ...
                {
                    Connection.PckgRecept = false;
                    DataRx = Prot.Parser(Connection.BuffRx);
                    if (DataRx != "") {
                        //Terminal.Write("Rx", Connection.BuffRx.substring(6, 8), Connection.BuffRx);
                        Prot.PckgOk = true;
                    } else {
                        //Terminal.Write("Info", "Erro na verificação do Pacote", Connection.BuffRx);
                    }
                }
            }
        }

        public int SendStatusTwc() {
            switch (TerminalStatusSm) {
                case TX:
                    if (Tout.TickCompare(TimetoTxStatusTwc)) {
                        TimetoRxStatusTwc = Tout.GetTick() + TOUT.RXStatusTwc.val;
                        Put("2F");
                        TerminalStatusSm = ETWCSTATUS.RX;
                    }
                    return 0;
                case RX:
                    if (!Tout.TickCompare(TimetoRxStatusTwc)) //tem dados para tratar
                    {
                        if (Prot.PckgOk) {
                            String cmd = DataRx.substring(0, 2);
                            if (cmd.equals("2F")) {
                                int len = DataRx.length();
                                if (len > 2) {
                                    QtdTwc = ExtractNumTwc(DataRx, len);
                                    return QtdTwc;
                                }
                                Prot.PckgOk = false; //dados ja coletados
                                TerminalStatusSm = ETWCSTATUS.TX;
                                return 0;
                            }
                            return 0;
                        }
                    } else {
                        TerminalStatusSm = ETWCSTATUS.TX;
                        TimetoTxStatusTwc = Tout.GetTick() + TOUT.TXStatusTwc.val;
                    }
                    return 0;
            }
            return 0;
        }

        public int ExtractNumTwc(String str, int len) {
            int N_term = ((len - 2) / 2);
            int aux;
            for (int count = 0; count < N_term; count++) {
                aux = (count * 2) + 2;
                String straux = str.substring(aux, aux + 2);
                ListTwc[count] = Integer.parseInt(straux);
            }
            return N_term;
        }

        public byte SomaCh(String str) {
            byte acc = 0;
            for (int i = 0; i < str.length(); i++) {
                acc += str.charAt(i);
            }
            return acc;
        }

        public String Ajuste(String Txt, char ch, char side, int NewLen) //string indexa do zero
        {
            int count = 0, dif, len = Txt.length();
            char acc[] = new char[NewLen];

            dif = NewLen - len;
            if (dif > 0) {
                for (int count_aux = 0; count < NewLen; count++) {
                    if (side == 'L') {
                        if (count < dif) {
                            acc[count] = ch;
                        } else {
                            acc[count] = Txt.charAt(count_aux++);
                        }
                    } else if (side == 'R') {
                        if (count < len) {
                            acc[count] = Txt.charAt(count_aux++);
                        } else {
                            acc[count] = ch;
                        }
                    } else {
                        break;
                    }
                }
                //acc[count] = '\0';
                Txt = new String(acc);
            }
            return Txt;
        }

        private void PutBegin() {
            TxLen = 0;
            TxChecksumAcc = '?';
            Connection.BuffTx = "";
            Connection.BuffTx += '>';
            Connection.BuffTx += '?';
            TxLen = 2;
        }

        private void PutData(String cmd) {
            int len = cmd.length();
            char ch;
            TxData = new char[len];
            for (int count = 0; count < len; count++) {
                ch = cmd.charAt(count);
                TxData[count] += ch;
                TxChecksumAcc += ch;
            }
            TxLen += len;
        }

        private void PutFlush() {
            String hex = Integer.toHexString(TxLen - 2).toUpperCase();
            String len = Ajuste(hex, '0', 'L', 4); // não conta os marcadores nem o próprio campo.
            TxChecksumAcc += SomaCh(len);
            TxLen += 4;
            Connection.BuffTx += len;

            String data = new String(TxData);
            Connection.BuffTx += data;

            String ck = String.format("%02X", TxChecksumAcc);
            Connection.BuffTx += ck;
            TxLen += 2;
            Connection.is_busy = true; // terminou de montar o pacote.
        }

        public void Put(String cmd) {
            if (Connection.is_connected) {
                PutBegin();
                PutData(cmd);
                PutFlush();
                Hrs.Prot.PckgOk = false;
            }
        }
    }

    public InformacoesCBC() {
        initComponents();

        Hrs = new Horus(jTable1);

        try {

            File f = new File(".lock");
            f.createNewFile();
            //GlobalParameter.getInstance();
            GlobalParameter.setCaminhoAplicacao(f.getAbsolutePath().replace(".lock", ""));

            Properties properties = new Properties();
            FileInputStream fis;
            fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);
            jTextField1.setText(properties.getProperty("cbc_host").trim());

            jComboBox3.removeAllItems();
            jComboBox3.addItem(properties.getProperty("cbc_porta").trim());
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
        }
        jButton1ActionPerformed(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 390, 90));

        jButton1.setText("Iniciar Teste");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 89, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 190, -1));

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Hrs.ConnectionSm = InformacoesCBC.ECONNECTION.CONNECT;
        InformacoesCBC.VERSION.Menu = "2.0";
        for (int count = 0; count < 1; count++) {
            //Hrs.Put("12");
            Hrs.TWC[count].SmCmd = InformacoesCBC.ETERMINALCMDS.LE_INFO;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacoesCBC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
