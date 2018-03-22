package view.movimentacao;

import bd.*;
import ctr.Cbc;
import ctr.GlobalParameter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.net.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import util.GeraLog;
import util.TratamentoValores;

public class TerminalMovel1 extends javax.swing.JFrame {

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
        BICO_INATIVO, ESCREVE_MENU_MASTER, LIMPAR, PENDENCIA, LE_VERSAO, ESCREVE_VERSAO, ESCREVE_MENU, WRITEMEN1, ESCREVE_MSG_PRINCIPAL, CANCELED, CHECKED,
        ESCREVE_VEICULO, ESCREVE_ABASTECIMENTO, LIBERA_BICO, BICO_OCUPADO, BICO_INEXISTENTE, VEICULO, ODOMETRO, FRENTISTA, FRENTISTA_MASTER, MOTORISTA, SENHA,
        VEICULO_NAO_CADASTRADO, OPERADOR_NAO_CADASTRADO, VEICULO_NAO_DIA_SEMANA, VEICULO_INATIVO, OPERADOR_INATIVO,
        ODOMETRO_DEVE_SER_MAIOR, EMPRESA_INATIVA, FROTA_INATIVA, OPERADOR_INCOMPATIVEL_VEICULO, TIPO_COMBUSTIVEL_DIFERENTE, BICO_NAO_CADASTRADO,
        LIMITE_ATINGIDO, FRENTISTA_LOGADO, MOTORISTA_INATIVO, MOTORISTA_NAO_CADASTRADO, OPERADOR_NAO_MASTER;
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
        //PrintStream ps = null;
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
                //ps = new PrintStream(clientSocket.getOutputStream());  

                is_connected = true;
                return true;
            } catch (Exception e) {
                is_connected = false;
                return false;
            }
        }

        public boolean Write(String cmd) {
            try {
                //ps.println(cmd);
                ps = new OutputStreamWriter(clientSocket.getOutputStream(), "ISO-8859-1");
                ps.write(cmd, 0, cmd.length());
                ps.flush();
                //System.out.println(cmd);
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
            SmCmd = ETERMINALCMDS.LE_VERSAO;
            SmBackCmd = ETERMINALCMDS.PENDENCIA;
        }

        public void limpaCampos() {//deve-se limpar apenas o que liberou
            bicoTW.setText("");
            tfVeiculo.setText("");
            tfOdometroo.setText("");
            tfFrentista.setText("");
            tfAnterior.setText("");
            tfTipoCombustivel.setText("");
            tfAnterior.setText("");
            tfLimite.setText("");
            tfBIcoIdent.setText("");
            tfMenu.setText("");
//            bicoCaf1.setText("");
//            tfVeiculo1.setText("");
//            tfOdometroo1.setText("");
//            tfFrentista1.setText("");
//            tfAnterior1.setText("");
//            tfTipoCombustivel1.setText("");
//            tfAnterior1.setText("");
            tfIdentMotorista.setText("");

        }

        private Cbc cbcStatus = null;

        public void Task() {
            this.cbcStatus = GlobalParameter.getInstance().getCbcStatus();

            switch (Sm) {
                case TX:
                    Sm = ETERMINAL.RX;
                    String Numlogic = Hrs.Ajuste(Integer.toString(Hrs.NlogicTwc), '0', 'L', 2);
                    switch (SmCmd) {
                        case PENDENCIA: //OK
                            Hrs.Put("2C" + Numlogic);
                            //SmCmd = TerminalMovel1.ETERMINALCMDS.FRENTISTA_LOGADO;
                            break;
                        case FRENTISTA_LOGADO:
//                            //Hrs.Put("2E" + Numlogic + "0E84Frentista|D1730|T1Aproxime o cartão|D2450|T1do Frentista.|D2070|C");
                            Hrs.Put("2E" + Numlogic + "0E85|L");
                            //SmBackCmd = SmCmd;
                            SmCmd = TerminalMovel1.ETERMINALCMDS.PENDENCIA;
                            break;
                        case ESCREVE_MSG_PRINCIPAL: //OK
                            Hrs.Put("2B" + Numlogic + "0E2C|D2720|T2AutoCaf|R|D1850|T1Gestão de Frotas|R|D1570|T1www.autocaf.com.br|ES020");
                            SmCmd = TerminalMovel1.ETERMINALCMDS.CHECKED;
                            break;
                        case ESCREVE_MENU://OK                            
                            //Hrs.Put("2E" + Numlogic + "0E22Abastecimentos|D2530|T1Aproxime o cartão|D2450|T1 do bico.|D2070|C");

                            if (!tipoLeituraBico.getText().trim().equals("T")) {
                                Hrs.Put("2E" + Numlogic + "0E22AutoCaf|D0530|T2Informe o Bico.|D4055|T2|EN02087");
                                //Hrs.Put("2E" + Numlogic + "0E21AutoCaf-Master777|D0530|T2Informe o Bico.|D4055|T2|EN02087");
                            } else {
                                Hrs.Put("2E" + Numlogic + "0E22AutoCaf|D0520|T2Aproxime a Tag |D2550|T2do Bico.|D2070|C");
                                //Hrs.Put("2E" + Numlogic + "0E21AutoCaf-Master|D0520|T2Aproxime a Tag |D2550|T2do Bico.|D2070|C");
                            }
                            SmCmd = TerminalMovel1.ETERMINALCMDS.ESCREVE_MENU_MASTER;
                            break;
                        case ESCREVE_MENU_MASTER://OK                            
                            if (!tipoLeituraBico.getText().trim().equals("T")) {
                                Hrs.Put("2E" + Numlogic + "0E21AutoCaf-Master|D0530|T2Informe o Bico.|D4055|T2|EN02087");
                            } else {
                                Hrs.Put("2E" + Numlogic + "0E21AutoCaf-Master|D0520|T2Aproxime a Tag |D2550|T2do Bico.|D2070|C");
                            }
                            SmCmd = TerminalMovel1.ETERMINALCMDS.ESCREVE_MSG_PRINCIPAL;
                            break;

                        case LE_VERSAO: //OK
                            Hrs.Put("2B" + Numlogic + "0E80Terminal|ML");
                            //SmCmd = TerminalMovel1.ETERMINALCMDS.CHECKED;
                            break;
                        case ESCREVE_VERSAO: //OK
                            Hrs.Put("2E" + Numlogic + "0E81|MG" + Hrs.Ajuste(TerminalMovel1.VERSION.Menu, ' ', 'R', 20 - TerminalMovel1.VERSION.Menu.length()));
                            SmCmd = TerminalMovel1.ETERMINALCMDS.ESCREVE_MENU;
                            break;
                        case VEICULO: //OK
                            //Hrs.Put("2E" + Numlogic + "0E82Veículo|D1730|T1Aproxime o cartão|D2450|T1 do veículo.|D2070|C");
                            Hrs.Put("2E" + Numlogic + "0E82AutoCaf|D0520|T2Aproxime a Tag |D1550|T2do Veículo.|D2170|C");
                            SmCmd = TerminalMovel1.ETERMINALCMDS.PENDENCIA;
                            break;
                        case ODOMETRO:
                            //Hrs.Put("2E" + Numlogic + "0E83Odômetro|D1730|T1Insira o odômetro|R|R|D2450|T1 do Veículo.|D3570|EN060");
                            Hrs.Put("2E" + Numlogic + "0E83AutoCaf|D0320|T2Digite o Odome-|D0350|T2tro do Veículo.|D2973|EN060");
                            SmCmd = TerminalMovel1.ETERMINALCMDS.PENDENCIA;
                            break;
                        case FRENTISTA:
                            //Hrs.Put("2E" + Numlogic + "0E84Frentista|D1730|T1Aproxime o cartão|D2450|T1do Frentista.|D2070|C");
                            Hrs.Put("2E" + Numlogic + "0E84AutoCaf|D0520|T2Aproxime a Tag |D0850|T2do Frentista|D2170|C");
                            SmCmd = TerminalMovel1.ETERMINALCMDS.PENDENCIA;
                            break;
                        case FRENTISTA_MASTER:
                            //Hrs.Put("2E" + Numlogic + "0E84Frentista|D1730|T1Aproxime o cartão|D2450|T1do Frentista.|D2070|C");
                            Hrs.Put("2E" + Numlogic + "0E77AutoCaf-Master|D0520|T2Aproxime a Tag |D0850|T2do Frentista|D2170|C");
                            SmCmd = TerminalMovel1.ETERMINALCMDS.PENDENCIA;
                            break;
                        case MOTORISTA:
                            if (!tipoLeituraMotorista.getText().trim().equals("T")) {
                                Hrs.Put("2E" + Numlogic + "0E86AutoCaf|D0820|T2Digite o|D0850|T2Motorista.|D2973|EN080");
                                //Hrs.Put("2E" + Numlogic + "0E86AutoCaf|D0530|T2Informe o Motorista.|D4055|T2|EN08087");
                            } else {
                                Hrs.Put("2E" + Numlogic + "0E86AutoCaf|D0520|T2Aproxime a Tag |D0850|T2do Motorista|D2170|C");
                            }

                            SmCmd = TerminalMovel1.ETERMINALCMDS.PENDENCIA;
                            break;
                        case CANCELED:
                            Hrs.Put("2E" + Numlogic + "0E90Terminal|D1535|T1Operação Cancelada|KN");
                            limpaCampos();
                            SmCmd = TerminalMovel1.ETERMINALCMDS.PENDENCIA;
                            break;
                        case CHECKED:
                            Hrs.Put("2E" + Numlogic + "0E91AutoCaf|D3008|T2SUCESSO|D1240|T1TERMINAL ATUALIZADO|KN");
                            SmCmd = TerminalMovel1.ETERMINALCMDS.PENDENCIA;
                            break;

                        case ESCREVE_VEICULO:
                            //switch (Menu) {
                            //case AUTOCAF:
                            if (!tfMenu.getText().equals("21")) {
                                Hrs.Put("26" + bicoTW.getText() + tfVeiculo.getText() + "1S" + tfLimite.getText() + "20V00000");
                            } else {
                                Hrs.Put("26" + bicoTW.getText() + "FFFFFFFFFFFFFFFF" + "1S" + tfLimite.getText() + "20V00000");
                            }
                            SmCmd = ETERMINALCMDS.LIBERA_BICO;
                            break;
                        case ESCREVE_ABASTECIMENTO:
                            switch (Menu) {
                                case AUTOCAF:
                                    //implantar limites
                                    Hrs.Put("26" + bicoTW.getText() + tfFrentista.getText() + "0N" + tfLimite.getText() + "20V00000");
                                    //SmCmd = TerminalMovel1.ETERMINALCMDS.ESCREVE_VEICULO;
                                    break;
                            }
                            //SmCmd = ETERMINALCMDS.PENDENCIA;
                            break;
                        case LIMITE_ATINGIDO:
                            Hrs.Put("2E" + Numlogic + "0E92Terminal|T1Erro!|R|R|T1Limite atingido para|R|R|T1frota ou veículo.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case TIPO_COMBUSTIVEL_DIFERENTE:
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3510|T2ERRO|D0540|T1COMBUSTIVEL DIFERENTE|D3060|T1DO VEÍCULO|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case VEICULO_NAO_DIA_SEMANA:
                            Hrs.Put("2E" + Numlogic + "0E92Terminal|T1Erro!|R|R|T1Veículo não autorizado|R|R|T1para abastecer nesse dia.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case VEICULO_INATIVO:
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3508|T2ERRO|D3040|T1VEÍCULO INATIVO.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case BICO_INATIVO:
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3508|T2ERRO|D3040|T1BICO INATIVO.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case OPERADOR_INATIVO:
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3508|T2ERRO|D3040|T1OPERADOR INATIVO.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case MOTORISTA_INATIVO:
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3508|T2ERRO|D3040|T1MOTORISTA INATIVO.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case EMPRESA_INATIVA:
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3508|T2ERRO|D3040|T1EMPRESA INATIVA.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case FROTA_INATIVA:
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3508|T2ERRO|3040|T1FROTA INATIVA.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case OPERADOR_INCOMPATIVEL_VEICULO:
                            Hrs.Put("2E" + Numlogic + "0E92Terminal|T1Erro!|R|R|T1Empresa do operador|R|R|T1incompatível com a|R|R|T1frota do veículo.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case ODOMETRO_DEVE_SER_MAIOR:
                            //Hrs.Put("2E" + Numlogic + "0E92Terminal|T1Erro!|R|R|T1Odômetro/Horímetro deve|R|R|T1ser maior que o do|R|R|T1último abastecimento|R|R|T1Anterior:" + tfAnterior.getText() + "|KN");
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3508|T2ERRO|D0540|T1ODOMETRO/HORÍMETRO DEVE|D0557|T1SER MAIOR QUE O ÚLTIMO|D0575|T1INFORMADO: " + tfAnterior.getText() + "|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case BICO_NAO_CADASTRADO://OK
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3508|T2ERRO|D1440|T1BICO NÃO CADASTRADO.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case VEICULO_NAO_CADASTRADO://OK
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3508|T2ERRO|D1040|T1VEÍCULO NÃO CADASTRADO.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case OPERADOR_NAO_MASTER://OK
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3508|T2ERRO|D1040|T1OPERADOR NÃO É MASTER.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case OPERADOR_NAO_CADASTRADO://OK
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3508|T2ERRO|D1040|T1OPERADOR NÃO CADASTRADO.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case MOTORISTA_NAO_CADASTRADO://OK
                            Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3508|T2ERRO|D0640|T1MOTORISTA NÃO CADASTRADO.|KN");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case LIBERA_BICO://OK
                            try {
                                Bico b = BicosDB.buscaIdentBico(tfBIcoIdent.getText().trim());
                                Operador o = OperadoresDB.buscaIdentOperador(tfFrentista.getText().trim());
                                Veiculo v = VeiculosDB.buscaIdentVeiculo(tfVeiculo.getText().trim());
                                Motorista m = MotoristasDB.buscaIdentMotorista(tfIdentMotorista.getText().trim());
                                Long marcadorAtual;
                                if (!tfOdometroo.getText().trim().equals("")) {
                                    marcadorAtual = Long.parseLong(tfOdometroo.getText().trim());
                                } else {
                                    marcadorAtual = 0L;
                                }
                                DadosTelaBico dadosTelaBico = new DadosTelaBico(v, b, o, marcadorAtual, m);

                                cbcStatus.liberaUmAbastecimento(b.getIdCBC());

                                Thread.sleep(1200);

                                Hrs.Put("2E" + Numlogic + "0E92AutoCaf|D3008|T2SUCESSO|D3040|T1BICO LIBERADO.|KN");
                                SmCmd = ETERMINALCMDS.PENDENCIA;

                                //instanciar apenas o que liberou
                                LeituraTWC leituraTWC = new LeituraTWC();
                                leituraTWC.setBico(bicoTW.getText());
                                leituraTWC.setFrentista(tfFrentista.getText());
                                leituraTWC.setOdometro(tfOdometroo.getText());
                                leituraTWC.setOdometroAnterior(tfAnterior.getText());
                                leituraTWC.setTipoCombustivel(tfTipoCombustivel.getText());
                                leituraTWC.setVeiculo(tfVeiculo.getText());

                                if (!tfControlaMotorista.getText().equals("")) {
                                    if (tfControlaMotorista.getText().toUpperCase().equals("S")) {
                                        leituraTWC.setMotorista(tfIdentMotorista.getText());
                                    } else {
                                        leituraTWC.setMotorista("");
                                    }
                                } else {
                                    leituraTWC.setMotorista("");
                                }

                                if (b.getIdCBC().equals("04")) {
                                    GlobalParameter.setLeituraTWC_04(leituraTWC);
                                } else if (b.getIdCBC().equals("44")) {
                                    GlobalParameter.setLeituraTWC_44(leituraTWC);
                                } else if (b.getIdCBC().equals("05")) {
                                    GlobalParameter.setLeituraTWC_05(leituraTWC);
                                } else if (b.getIdCBC().equals("45")) {
                                    GlobalParameter.setLeituraTWC_45(leituraTWC);
                                } else if (b.getIdCBC().equals("08")) {
                                    GlobalParameter.setLeituraTWC_08(leituraTWC);
                                } else if (b.getIdCBC().equals("48")) {
                                    GlobalParameter.setLeituraTWC_48(leituraTWC);
                                } else if (b.getIdCBC().equals("09")) {
                                    GlobalParameter.setLeituraTWC_09(leituraTWC);
                                } else if (b.getIdCBC().equals("49")) {
                                    GlobalParameter.setLeituraTWC_49(leituraTWC);
                                } else if (b.getIdCBC().equals("0C")) {
                                    GlobalParameter.setLeituraTWC_0C(leituraTWC);
                                } else if (b.getIdCBC().equals("4C")) {
                                    GlobalParameter.setLeituraTWC_4C(leituraTWC);
                                } else if (b.getIdCBC().equals("0D")) {
                                    GlobalParameter.setLeituraTWC_0D(leituraTWC);
                                } else if (b.getIdCBC().equals("4D")) {
                                    GlobalParameter.setLeituraTWC_4D(leituraTWC);
                                } else if (b.getIdCBC().equals("10")) {
                                    GlobalParameter.setLeituraTWC_10(leituraTWC);
                                } else if (b.getIdCBC().equals("50")) {
                                    GlobalParameter.setLeituraTWC_50(leituraTWC);
                                } else if (b.getIdCBC().equals("11")) {
                                    GlobalParameter.setLeituraTWC_11(leituraTWC);
                                } else if (b.getIdCBC().equals("51")) {
                                    GlobalParameter.setLeituraTWC_51(leituraTWC);
                                }
                                HistoricoDB.insertHistoricoDadosTela(b.getSeqBico(), v != null ? v.getSeqVeiculo() : null, o.getSeqOperador(), dadosTelaBico.getHodometro(), m != null ? m.getSeqMotorista() : null);

                                limpaCampos();
                            } catch (Exception e) {
                                GeraLog g = new GeraLog();
                                g.gravaErro(e);
                                g.close();
                                break;
                            }
                            break;

                        case BICO_OCUPADO:
                            Hrs.Put("2E" + Numlogic + "0E93AutoCaf|D3508|T2ERRO|D3040|T1BICO OCUPADO.|KC");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
                            break;
                        case BICO_INEXISTENTE:
                            Hrs.Put("2E" + Numlogic + "0E94AutoCaf|D3508|T2ERRO|D2540|T1BICO INEXISTENTE.|KC");
                            SmCmd = ETERMINALCMDS.PENDENCIA;
                            limpaCampos();
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
                        if (!index.equals("")) {
                            if (index.equals("85")) {
                                String terminal = str.substring(2, 4);
                                if (terminal.equals("01")) {
                                    if (!str.substring(9, len).equals("0000000000000000")) { //tem frentista logado
                                        tfFrentista.setText(str.substring(9, len));
                                        //Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        Operador o = OperadoresDB.buscaIdentOperador(tfFrentista.getText());
                                        Veiculo v = VeiculosDB.buscaIdentVeiculo(tfVeiculo.getText());
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        if (o == null) {
                                            Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                            SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_NAO_CADASTRADO;
                                        } else if ((o.getSituacao().equals("I"))) {
                                            Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                            SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_INATIVO;
                                        } else if (!v.getFrota().getEmpresa().getSeqEmpresa().equals(o.getEmpresa().getSeqEmpresa())) {
                                            Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                            SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_INCOMPATIVEL_VEICULO;
                                        } else {
                                            Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                            if (true) { //????????
                                                SmCmd = TerminalMovel1.ETERMINALCMDS.ESCREVE_ABASTECIMENTO;
                                            }
                                        }
                                        Erro = 0;
                                    } else {
                                        tfFrentista.setText("0000000000000000");
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.FRENTISTA;
                                        Erro = 0;
                                    }
                                }
                            } else if (index.equals("77")) {
                                String terminal = str.substring(2, 4);
                                if (terminal.equals("01")) {
                                    //if (!str.substring(9, len).equals("0000000000000000")) { //tem frentista logado
                                    tfFrentista.setText(str.substring(14, len));
                                    //Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                    Operador o = OperadoresDB.buscaIdentOperador(tfFrentista.getText());
                                    Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                    //Veiculo v = VeiculosDB.buscaIdentVeiculo(tfVeiculo.getText());
                                    if (o == null) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_NAO_CADASTRADO;
                                    } else if ((o.getSituacao().equals("I"))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_INATIVO;
                                    } else if ((o.getIsMaster().equals("N")) && (tfMenu.getText().equals("21"))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_NAO_MASTER;
                                        //} else if (!v.getFrota().getEmpresa().getSeqEmpresa().equals(o.getEmpresa().getSeqEmpresa())) {
                                        //    Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        //    SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_INCOMPATIVEL_VEICULO;
                                    } else if ((o.getIsMaster().equals("N")) && (tfMenu.getText().equals("21"))) {

                                    } else {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        if (true) { //????????
                                            SmCmd = TerminalMovel1.ETERMINALCMDS.ESCREVE_ABASTECIMENTO;
                                        }
                                    }
                                    Erro = 0;
                                    //} else {
                                    //    tfFrentista.setText("0000000000000000");
                                    //    SmCmd = TerminalMovel1.ETERMINALCMDS.FRENTISTA;
                                    //    Erro = 0;
                                    //}
                                }
                            } else if (index.equals("22") || index.equals("2A")) { // bico
                                String aux = "", terminal = "";
                                tfMenu.setText("22");
                                if (!tipoLeituraBico.getText().trim().equals("T")) {
                                    aux = str.substring(14, len);
                                    terminal = str.substring(2, 4);
                                } else {
                                    aux = str.substring(14, len);
                                    terminal = str.substring(2, 4);
                                }
                                if (terminal.equals("01")) {
                                    //Integer numero = Integer.parseInt(aux);
                                    Bico b = BicosDB.buscaIdentBico(aux);
                                    if (b == null) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.BICO_NAO_CADASTRADO;
                                    } else if ((b.getSituacao().equals("I"))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.BICO_INATIVO;
                                    } else {
                                        bicoTW.setText(TratamentoValores.preencheCom(b.getIdTWC().toString(), "0", 2, 1, true));
                                        tfBIcoIdent.setText(aux);
                                        tfTipoCombustivel.setText(b.getCombustivel().getSeqCombustivel().toString());
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.VEICULO;
                                    }
                                }
                            } else if (index.equals("21")) { // bico
                                String aux = "", terminal = "";
                                tfMenu.setText("21");
                                if (!tipoLeituraBico.getText().trim().equals("T")) {
                                    aux = str.substring(14, len);
                                    terminal = str.substring(2, 4);
                                } else {
                                    aux = str.substring(14, len);
                                    terminal = str.substring(2, 4);
                                }
                                if (terminal.equals("01")) {
                                    //Integer numero = Integer.parseInt(aux);
                                    Bico b = BicosDB.buscaIdentBico(aux);
                                    if (b == null) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.BICO_NAO_CADASTRADO;
                                    } else if ((b.getSituacao().equals("I"))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.BICO_INATIVO;
                                    } else {
                                        bicoTW.setText(TratamentoValores.preencheCom(b.getIdTWC().toString(), "0", 2, 1, true));
                                        tfBIcoIdent.setText(aux);
                                        tfTipoCombustivel.setText(b.getCombustivel().getSeqCombustivel().toString());
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        //SmCmd = TerminalMovel1.ETERMINALCMDS.VEICULO;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.FRENTISTA_MASTER;
                                    }
                                }
                            } else if (index.equals("80")) {
                                SmBackCmd = SmCmd;
                                versao = str.substring(9, len);
                                if (!versao.equals(Hrs.Ajuste(TerminalMovel1.VERSION.Menu, ' ', 'R', 20 - TerminalMovel1.VERSION.Menu.length()))) {
                                    SmCmd = TerminalMovel1.ETERMINALCMDS.ESCREVE_VERSAO;
                                } else {
                                    SmCmd = TerminalMovel1.ETERMINALCMDS.CHECKED;
                                }
                            } else if (index.equals("82")) {
                                //veiculo
                                String terminal = str.substring(2, 4);
                                if (terminal.equals("01")) {
                                    tfVeiculo.setText(str.substring(14, len));

                                    Veiculo v = VeiculosDB.buscaIdentVeiculo(str.substring(14, len));
                                    Integer diaSemana = new GregorianCalendar().get(GregorianCalendar.DAY_OF_WEEK);

                                    if (v == null) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.VEICULO_NAO_CADASTRADO;
                                    } else if ((v.getSituacao().equals("I"))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.VEICULO_INATIVO;
                                    } else if (v.getFrota().getEmpresa().getSituacao().equals("I")) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.EMPRESA_INATIVA;
                                    } else if (v.getFrota().getSituacao().equals("I")) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.FROTA_INATIVA;
                                    } else if ((v.getSegunda().equals("N") && (diaSemana == 2))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.VEICULO_NAO_DIA_SEMANA;
                                    } else if ((v.getTerca().equals("N") && (diaSemana == 3))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.VEICULO_NAO_DIA_SEMANA;
                                    } else if ((v.getQuarta().equals("N") && (diaSemana == 4))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.VEICULO_NAO_DIA_SEMANA;
                                    } else if ((v.getQuinta().equals("N") && (diaSemana == 5))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.VEICULO_NAO_DIA_SEMANA;
                                    } else if ((v.getSexta().equals("N") && (diaSemana == 6))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.VEICULO_NAO_DIA_SEMANA;
                                    } else if ((v.getSabado().equals("N") && (diaSemana == 7))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.VEICULO_NAO_DIA_SEMANA;
                                    } else if ((v.getDomingo().equals("N") && (diaSemana == 1))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.VEICULO_NAO_DIA_SEMANA;
                                    } else if (!v.getCombustivel().getSeqCombustivel().toString().equals(tfTipoCombustivel.getText())) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.TIPO_COMBUSTIVEL_DIFERENTE;
                                    } else {
                                        String tipoControlePreset = tfPreset.getText();
                                        Bico b = BicosDB.buscaIdentBico(tfBIcoIdent.getText());
                                        //no caso de negativo
                                        if (v.getFrota().getDisponivel() < 0) {
                                            v.getFrota().setDisponivel(0.0);
                                        }
                                        //limite por veiculo ou frota ou semanal
                                        if ((v.getLimite() > 0) || (v.getFrota().getLimite() > 0) || (v.getLimiteSemanal() > 0)) {
                                            Double valor = 0.0;
                                            //possui limite somente por veiculo
                                            if ((v.getLimite() > 0) && (v.getFrota().getLimite() <= 0)) {
                                                //limite do veiculo
                                                if (tipoControlePreset.equals("L")) {
                                                    valor = TratamentoValores.arredondar(v.getLimite(), 2, 0);
                                                } else {
                                                    valor = TratamentoValores.arredondar(v.getLimite() * PrecosDB.buscaUltimoPreco(b).getPreco(), 2, 0);
                                                }//$$$$
                                            }
                                            //possui limite somente por frota
                                            if ((v.getLimite() <= 0) && (v.getFrota().getLimite() > 0)) {
                                                //limite da frota
                                                if (tipoControlePreset.equals("L")) {
                                                    valor = TratamentoValores.arredondar(v.getFrota().getDisponivel(), 2, 0);
                                                    //$$$$
                                                } else {
                                                    valor = TratamentoValores.arredondar(v.getFrota().getDisponivel() * PrecosDB.buscaUltimoPreco(b).getPreco(), 2, 0);
                                                }
                                            }
                                            //possui os dois limites, utiliza o menor
                                            if ((v.getLimite() > 0) && (v.getFrota().getLimite() > 0)) {
                                                //limite da frota é menor que do veículo
                                                if ((v.getFrota().getDisponivel() <= v.getLimite()) && (v.getFrota().getDisponivel() > 0)) {
                                                    if (tipoControlePreset.equals("L")) {
                                                        valor = TratamentoValores.arredondar(v.getFrota().getDisponivel(), 2, 0);
                                                    } else {//$$$$$
                                                        valor = TratamentoValores.arredondar(v.getFrota().getDisponivel() * PrecosDB.buscaUltimoPreco(b).getPreco(), 2, 0);
                                                    }//limite do veiculo 
                                                } else if (tipoControlePreset.equals("L")) {
                                                    valor = TratamentoValores.arredondar(v.getLimite(), 2, 0);
                                                } else {//$$$$$
                                                    valor = TratamentoValores.arredondar(v.getLimite() * PrecosDB.buscaUltimoPreco(b).getPreco(), 2, 0);
                                                }
                                            }

                                            if (valor < 0.0) {
                                                valor = 0.0;
                                            }
                                            //tem limite semanal e não tem por frota nem veiculo
                                            if ((v.getLimiteSemanal() > 0) && (valor <= 0)) {
                                                valor = TratamentoValores.arredondar(v.getRestanteSemanal(), 2, 0);
                                            }
                                            //tem limite semanal e ja acabou..
                                            if ((v.getLimiteSemanal() > 0) && (v.getRestanteSemanal() <= valor)) {
                                                valor = TratamentoValores.arredondar(v.getRestanteSemanal(), 2, 0);
                                            }

                                            String sValor = valor.toString();
                                            //Ajusta casa decimal. Bug quando valor redondo
                                            if ((sValor.length() == 3) && (sValor.substring(1, 3).equals(".0"))) {
                                                sValor = sValor.replace(".0", "00");
                                            } else if ((sValor.length() == 4) && (sValor.substring(2, 4).equals(".0"))) {
                                                sValor = sValor.replace(".0", "00");
                                            } else if ((sValor.length() == 5) && (sValor.substring(3, 5).equals(".0"))) {
                                                sValor = sValor.replace(".0", "00");
                                            } else if ((sValor.length() == 6) && (sValor.substring(4, 6).equals(".0"))) {
                                                sValor = sValor.replace(".0", "00");
                                            } else {
                                                sValor = sValor.replace(",", "").replace(".", "");
                                            }

                                            if (Integer.valueOf(sValor) <= 0) {
                                                Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                                SmCmd = TerminalMovel1.ETERMINALCMDS.LIMITE_ATINGIDO;
                                            } else {
                                                String preset = TratamentoValores.preencheCom(sValor, "0", 6, 1, true);
                                                tfLimite.setText(preset);
                                                Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                                SmCmd = TerminalMovel1.ETERMINALCMDS.ODOMETRO;
                                            }
                                        } else {
                                            Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                            SmCmd = TerminalMovel1.ETERMINALCMDS.ODOMETRO;
                                        }
                                    }
                                }
                            } else if (index.equals("83")) {
                                //odometro
                                String terminal = str.substring(2, 4);
                                if (terminal.equals("01")) {
                                    tfOdometroo.setText(str.substring(14, len));
                                    Veiculo v = VeiculosDB.buscaIdentVeiculo(tfVeiculo.getText());
                                    Long odoAnterior = VeiculosDB.buscaHodometroHorimetroAnterior(v, new Timestamp(new Date().getTime()));
                                    tfAnterior.setText(odoAnterior.toString());
                                    if (Long.parseLong(tfOdometroo.getText().trim()) <= odoAnterior) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.ODOMETRO_DEVE_SER_MAIOR;
                                    } else { //if (tfFrentista.getText().equals("0000000000000000")) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;

                                        if (!tfControlaMotorista.getText().equals("")) {
                                            if (tfControlaMotorista.getText().toUpperCase().equals("S")) {
                                                SmCmd = TerminalMovel1.ETERMINALCMDS.MOTORISTA;
                                            } else {
                                                SmCmd = TerminalMovel1.ETERMINALCMDS.FRENTISTA_LOGADO;
                                            }
                                        } else {
                                            SmCmd = TerminalMovel1.ETERMINALCMDS.FRENTISTA_LOGADO;
                                        }

//                                    } else {
//                                        Operador o = OperadoresDB.buscaIdentOperador(tfFrentista.getText());
//                                        if (o == null) {
//                                            Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
//                                            SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_NAO_CADASTRADO;
//                                        } else if ((o.getSituacao().equals("I"))) {
//                                            Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
//                                            SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_INATIVO;
//                                        } else if (!v.getFrota().getEmpresa().getSeqEmpresa().equals(o.getEmpresa().getSeqEmpresa())) {
//                                            Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
//                                            SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_INCOMPATIVEL_VEICULO;
//                                        } else {
//                                            Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
//                                            if (true) { //????????
//                                                SmCmd = TerminalMovel1.ETERMINALCMDS.ESCREVE_ABASTECIMENTO;
//                                            }
//                                        }
                                    }
                                }
                            } else if (index.equals("84")) {
                                //frentista
                                String terminal = str.substring(2, 4);
                                if (terminal.equals("01")) {
                                    tfFrentista.setText(str.substring(14, len));
                                    Operador o = OperadoresDB.buscaIdentOperador(str.substring(14, len));
                                    Veiculo v = VeiculosDB.buscaIdentVeiculo(tfVeiculo.getText());
                                    Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                    if (o == null) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_NAO_CADASTRADO;
                                    } else if ((o.getSituacao().equals("I"))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_INATIVO;
                                    } else if (!v.getFrota().getEmpresa().getSeqEmpresa().equals(o.getEmpresa().getSeqEmpresa())) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.OPERADOR_INCOMPATIVEL_VEICULO;
                                    } else {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        if (true) { //????????
                                            SmCmd = TerminalMovel1.ETERMINALCMDS.ESCREVE_ABASTECIMENTO;
                                        }
                                    }
                                }
                            } else if (index.equals("86")) {
                                //motorista
                                String terminal = str.substring(2, 4);
                                String aux;
                                if (terminal.equals("01")) {

                                    if (!tipoLeituraMotorista.getText().trim().equals("T")) {
                                        aux = str.substring(14, len);
                                    } else {
                                        aux = str.substring(14, len);
                                    }

                                    tfIdentMotorista.setText(aux);
                                    Motorista m = MotoristasDB.buscaIdentMotorista(aux);
                                    //Veiculo v = VeiculosDB.buscaIdentVeiculo(tfVeiculo.getText());
                                    if (m == null) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.MOTORISTA_NAO_CADASTRADO;
                                    } else if ((m.getSituacao().equals("I"))) {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.MOTORISTA_INATIVO;
                                    } else {
                                        Menu = TerminalMovel1.MENU_TERMINAL.AUTOCAF;
                                        SmCmd = TerminalMovel1.ETERMINALCMDS.FRENTISTA_LOGADO;
                                    }
                                }
                            } else {
                                Erro++;
                            }
                            Erro = 0;
                            //SmCmd = SmBackCmd;
                        }
                    } else {
                        SmCmd = ETERMINALCMDS.CANCELED;
                    }
                } else {
                    Erro = 0;
                    SmCmd = SmBackCmd;
                }
            } else if (cmd.equals("2F")) {
                SmCmd = ETERMINALCMDS.FRENTISTA_LOGADO;
            } else if ((cmd.equals("26")) && (SmCmd.name().equals("ESCREVE_VEICULO"))) {
                String valid = str.substring(2, 4);
                char erro = valid.charAt(1);
                if (valid.equals("00")) {
                    SmCmd = ETERMINALCMDS.LIBERA_BICO;
                } else if (erro == '7') {
                    SmCmd = ETERMINALCMDS.BICO_INEXISTENTE;
                } else if (erro == '8') {
                    SmCmd = ETERMINALCMDS.BICO_OCUPADO;
                }
            } else if (cmd.equals("26")) {
                String valid = str.substring(2, 4);
                char erro = valid.charAt(1);
                if ((valid.equals("00")) && (SmCmd.name().equals("LIBERA_BICO"))) {
                    SmCmd = ETERMINALCMDS.LIBERA_BICO;
                } else if (valid.equals("00")) {
                    SmCmd = ETERMINALCMDS.ESCREVE_VEICULO;
                } else if (erro == '7') {
                    SmCmd = ETERMINALCMDS.BICO_INEXISTENTE;
                } else if (erro == '8') {
                    SmCmd = ETERMINALCMDS.BICO_OCUPADO;
                }
            } else if (cmd.equals("2B") || cmd.equals("2E")) {
                String indexaux = str.substring(2, 4);
                if (indexaux.equals("E1") || indexaux.equals("E5")) {
                    SmCmd = ETERMINALCMDS.LIMPAR;
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

//        public void Inc_Port() {
//            int idx = jComboBox3.getSelectedIndex();
//            if (idx < 3) {
//                jComboBox3.setSelectedIndex(idx + 1);
//            } else {
//                jComboBox3.setSelectedIndex(0);
//            }
//        }
    }

    public TerminalMovel1() {
        initComponents();

        Hrs = new Horus(jTable1);

        try {
            Properties properties = new Properties();
            FileInputStream fis;
            fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);
            jTextField1.setText(properties.getProperty("cbc_host").trim());
            tfPreset.setText(properties.getProperty("cbc_tipocontrole_preset").trim());

            if (properties.getProperty("twc_tipo_leitura_bico").trim().toUpperCase().equals("")) {
                tipoLeituraBico.setText("T");
            } else if (properties.getProperty("twc_tipo_leitura_bico").trim().toUpperCase().equals("T")) {
                tipoLeituraBico.setText("T");
            } else {
                tipoLeituraBico.setText("D");
            }
//        jButton5.setText("Limpar");
//        jButton6.setText("Parar");
            jComboBox3.removeAllItems();
            jComboBox3.addItem(properties.getProperty("cbc_porta_twc").trim());

            if (properties.getProperty("sis_controla_motorista").trim() != null) {
                if (properties.getProperty("sis_controla_motorista").trim().toUpperCase().equals("S")) {
                    tfControlaMotorista.setText("S");
                } else {
                    tfControlaMotorista.setText("N");
                }
            } else {
                tfControlaMotorista.setText("N");
            }

            if (properties.getProperty("twc_tipo_leitura_motorista").trim().toUpperCase().equals("")) {
                tipoLeituraMotorista.setText("T");
            } else if (properties.getProperty("twc_tipo_leitura_motorista").trim().toUpperCase().equals("T")) {
                tipoLeituraMotorista.setText("T");
            } else {
                tipoLeituraMotorista.setText("D");
            }
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
        lbBico = new javax.swing.JLabel();
        bicoTW = new javax.swing.JTextField();
        lbVeiculo = new javax.swing.JLabel();
        tfVeiculo = new javax.swing.JTextField();
        lbVeiculo2 = new javax.swing.JLabel();
        tfOdometroo = new javax.swing.JTextField();
        lbVeiculo1 = new javax.swing.JLabel();
        tfFrentista = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        tfAnterior = new javax.swing.JTextField();
        lbVeiculo3 = new javax.swing.JLabel();
        lbBico2 = new javax.swing.JLabel();
        lbVeiculo16 = new javax.swing.JLabel();
        tfTipoCombustivel = new javax.swing.JTextField();
        bicoCaf4 = new javax.swing.JTextField();
        tfPreset = new javax.swing.JTextField();
        tfLimite = new javax.swing.JTextField();
        lbVeiculo18 = new javax.swing.JLabel();
        tfBIcoIdent = new javax.swing.JTextField();
        tipoLeituraBico = new javax.swing.JTextField();
        tfControlaMotorista = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbVeiculo19 = new javax.swing.JLabel();
        tfIdentMotorista = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tipoLeituraMotorista = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfMenu = new javax.swing.JTextField();

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 80, 20));

        jButton1.setText("Iniciar Teste");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lbBico.setText("Bico TWC");
        getContentPane().add(lbBico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 44, 70, 10));
        getContentPane().add(bicoTW, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 60, -1));

        lbVeiculo.setText("Identificador Veículo");
        getContentPane().add(lbVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, -1));
        getContentPane().add(tfVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 150, -1));

        lbVeiculo2.setText("Limite");
        getContentPane().add(lbVeiculo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 110, -1));
        getContentPane().add(tfOdometroo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 150, -1));

        lbVeiculo1.setText("Identificador Frentista");
        getContentPane().add(lbVeiculo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 130, -1));
        getContentPane().add(tfFrentista, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 150, -1));

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
        getContentPane().add(tfAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 150, -1));

        lbVeiculo3.setText("Odômetro/Horímetro");
        getContentPane().add(lbVeiculo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 110, -1));

        lbBico2.setText("Tipo Preset");
        getContentPane().add(lbBico2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 60, 20));

        lbVeiculo16.setText("Tipo Combustivel");
        getContentPane().add(lbVeiculo16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 90, -1));
        getContentPane().add(tfTipoCombustivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 80, -1));
        getContentPane().add(bicoCaf4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 60, -1));
        getContentPane().add(tfPreset, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 110, 20));
        getContentPane().add(tfLimite, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 150, -1));

        lbVeiculo18.setText("Odômetro Anterior");
        getContentPane().add(lbVeiculo18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 110, -1));
        getContentPane().add(tfBIcoIdent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 150, -1));
        getContentPane().add(tipoLeituraBico, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 70, -1));
        getContentPane().add(tfControlaMotorista, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 110, -1));

        jLabel1.setText("Tipo Leitura Bico - Digitado/Tag");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1, -1));

        jLabel2.setText("Identificador Bico");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, 20));

        lbVeiculo19.setText("Identificador Motorista");
        getContentPane().add(lbVeiculo19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 140, -1));
        getContentPane().add(tfIdentMotorista, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 150, -1));

        jLabel3.setText("Controla Motorista");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        jLabel4.setText("Tipo Leitura Motorista - Digitado/Tag");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, -1));
        getContentPane().add(tipoLeituraMotorista, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 70, -1));

        jLabel5.setText("Menu");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, -1, -1));
        getContentPane().add(tfMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 70, -1));

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
        Hrs.ConnectionSm = TerminalMovel1.ECONNECTION.CONNECT;
        TerminalMovel1.VERSION.Menu = "2.0";
        for (int count = 0; count < 2; count++) {
            Hrs.TWC[count].SmCmd = TerminalMovel1.ETERMINALCMDS.LE_VERSAO;
            Hrs.TWC[count].SmCmd = TerminalMovel1.ETERMINALCMDS.ESCREVE_MENU;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TerminalMovel1().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bicoCaf4;
    private javax.swing.JTextField bicoTW;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbBico;
    private javax.swing.JLabel lbBico2;
    private javax.swing.JLabel lbVeiculo;
    private javax.swing.JLabel lbVeiculo1;
    private javax.swing.JLabel lbVeiculo16;
    private javax.swing.JLabel lbVeiculo18;
    private javax.swing.JLabel lbVeiculo19;
    private javax.swing.JLabel lbVeiculo2;
    private javax.swing.JLabel lbVeiculo3;
    private javax.swing.JTextField tfAnterior;
    private javax.swing.JTextField tfBIcoIdent;
    private javax.swing.JTextField tfControlaMotorista;
    private javax.swing.JTextField tfFrentista;
    private javax.swing.JTextField tfIdentMotorista;
    private javax.swing.JTextField tfLimite;
    private javax.swing.JTextField tfMenu;
    private javax.swing.JTextField tfOdometroo;
    private javax.swing.JTextField tfPreset;
    private javax.swing.JTextField tfTipoCombustivel;
    private javax.swing.JTextField tfVeiculo;
    private javax.swing.JTextField tipoLeituraBico;
    private javax.swing.JTextField tipoLeituraMotorista;
    // End of variables declaration//GEN-END:variables
}
