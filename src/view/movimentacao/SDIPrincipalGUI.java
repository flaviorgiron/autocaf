package view.movimentacao;

import integracao.ExportarAbastecimentosGUI;
import view.consultas.SobreSistemaGUI;
import tablemodel.StatusBicosTableModel;
import tablemodel.AbastecimentosTableModel;
import render.ColorirCelula;
import java.awt.AWTException;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import model.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import trayicon.TyBallon;
import idioma.Idioma;
import util.*;
import view.cadastros.*;
import render.*;
import bd.*;
import ctr.*;
import java.util.Base64.Decoder;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import tablemodel.AbastecimentosMotoristaTableModel;
import view.consultas.*;

public class SDIPrincipalGUI extends javax.swing.JFrame {

    public static AbastecimentosTableModel abastecimentosTableModel;
    public static AbastecimentosMotoristaTableModel abastecimentosMotoristaTableModel;

    public static StatusBicosTableModel statusBicosTableModel;
    private Cbc cbc = null;
    private Cbc cbcStatus = null;
    public Thread realTimeConsultasCompanyTec = null;
    public Thread analisaFilaCompanyTec = null;
    public Thread consultaStatus = null;
    public Thread leituraTWC = null;
    public Thread atualizaGridAbastecimentos = null;
    private static Idioma i;
    private String controlaMotorista = "N";

    public SDIPrincipalGUI() throws AWTException, IOException {
        try {
            i = GlobalParameter.getIdioma();
            this.setExtendedState(MAXIMIZED_BOTH);
            initComponents();

            jButton1.setVisible(false);

            ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
            setIconImage(icon.getImage());

            //data atual
            Date d = new Date();
            Long dataAtual = d.getTime();

            //data da licença
            Licenca lic = LicencaDB.buscaLicenca();
            Decoder de = Base64.getDecoder();
            String s1 = new String(de.decode(lic.getLicenca()));
            Long dataLic = Long.parseLong(s1);

            //data ultimo acesso
            //String s2 = new String(de.decode(lic.getUltimoAcesso()));
            //Long dataUltimoAcesso = Long.parseLong(s2);
            //atualizaLeituras();
            jLabel7.setText("");
            carregaAbastecimentos();

            jMenu4.setVisible(false);

            jtfDataSistema.setText(GlobalParameter.getInstance().getDataSistema());
            if (GlobalParameter.getInstance().getOperador().getNomeOperador() != null) {
                jtfNomeUsuario.setText(GlobalParameter.getInstance().getOperador().getNomeOperador());
                jtfNomeUsuario.setCaretPosition(0);
                //se for super admin/ habilita menu restrito
                if (GlobalParameter.getInstance().getOperador().getIsSuperUsuario().equals("S")) {
                    jMenu4.setVisible(true);
                }
            } else {
                jtfNomeUsuario.setText("");
            }

            carregaBallon();

            //verifica se a data atual é maior que a licenca
            if (dataAtual > dataLic) {
                JConfirmMessage.showMessageDialog("A licença de uso do sistema está vencida.", "");
                jMenuItem1.setEnabled(false);
                jLabel6.setEnabled(false);
                jMenuItem28.setEnabled(false);
                jMenuItem39.setEnabled(false);
                jLabel9.setEnabled(false);
                jMenuItem29.setEnabled(false);
                System.exit(0);
            } else {
                iniciaConexaoCompanyTec();
                carregaStatusBicos();
            }

            GeraLog ge = new GeraLog();
            ge.gravaMensagensLog(new Throwable("Validou Licença"));
            ge.close();

            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);

            if (properties.getProperty("sis_controla_motorista").trim() != null) {
                if (properties.getProperty("sis_controla_motorista").trim().toUpperCase().equals("S")) {
                    jMenuItem31.setVisible(true); // cadastro
                    jMenuItem33.setVisible(true); // relatorio
                    controlaMotorista = "S";
                } else {
                    jMenuItem31.setVisible(false);
                    jMenuItem33.setVisible(false);
                    controlaMotorista = "N";
                }
            } else {
                jMenuItem31.setVisible(false);
                jMenuItem33.setVisible(false);
                controlaMotorista = "N";
            }

            //em construção
            jMenuItem16.setVisible(false); // fabricantes
            jMenuItem38.setVisible(false); // importacao
            jMenuItem17.setVisible(false); // modificar odometro -->>>>> ficou inutil...remover
            jMenuItem37.setVisible(false); // excluir abastec -->>>>> ficou inutil...remover
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
        }
    }

    public static AbastecimentosTableModel getModelAbastec() {
        if (abastecimentosTableModel == null) {
            abastecimentosTableModel = (AbastecimentosTableModel) jtbAbastecimentos.getModel();
        }
        return abastecimentosTableModel;
    }

    public static AbastecimentosMotoristaTableModel getModelAbastecMotorista() {
        if (abastecimentosMotoristaTableModel == null) {
            abastecimentosMotoristaTableModel = (AbastecimentosMotoristaTableModel) jtbAbastecimentos.getModel();
        }
        return abastecimentosMotoristaTableModel;
    }

    public static StatusBicosTableModel getModelStatusBicos() {
        if (statusBicosTableModel == null) {
            statusBicosTableModel = (StatusBicosTableModel) tbStatusBicos.getModel();
        }
        return statusBicosTableModel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfNomeUsuario = new javax.swing.JTextField();
        jtfDataSistema = new javax.swing.JTextField();
        jlbDataSistema = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbAbastecimentos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaLeituras = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        tfStringExam = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        tfBico = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfData = new javax.swing.JTextField();
        tfTempo = new javax.swing.JTextField();
        tfVolume = new javax.swing.JTextField();
        tfPreco = new javax.swing.JTextField();
        tfnumAbastec = new javax.swing.JTextField();
        tfIdent1 = new javax.swing.JTextField();
        tfIdent2 = new javax.swing.JTextField();
        tfEncerrante = new javax.swing.JTextField();
        tfStatus = new javax.swing.JTextField();
        tfEncerranteAnt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbStatusBicos = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem37 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("AutoCAF - " + i.getProperty("sis2"));
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowIconified(java.awt.event.WindowEvent evt) {
                minimizarJanela(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sair.png"))); // NOI18N
        jLabel1.setToolTipText(i.getProperty("princ52"));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bico.png"))); // NOI18N
        jLabel3.setToolTipText(i.getProperty("princ26"));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/veiculo.png"))); // NOI18N
        jLabel4.setToolTipText(i.getProperty("princ28"));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/operador.png"))); // NOI18N
        jLabel5.setToolTipText(i.getProperty("princ13"));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/abastec.png"))); // NOI18N
        jLabel6.setToolTipText("Abastecimento");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/frota.png"))); // NOI18N
        jLabel8.setToolTipText(i.getProperty("princ15"));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sync_comboio.png"))); // NOI18N
        jLabel9.setToolTipText("Sincronizar Comboio");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/export.png"))); // NOI18N
        jLabel10.setToolTipText("Exportação");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel10MousePressed(evt);
            }
        });

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logo_menu.png"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/estoque.png"))); // NOI18N
        jLabel24.setToolTipText("Ajuste de Estoque");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel24MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(241, 240, 240));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setLabelFor(jtfNomeUsuario);
        jLabel2.setText(i.getProperty("princ17"));
        jLabel2.setFocusable(false);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jtfNomeUsuario.setEditable(false);
        jtfNomeUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jtfNomeUsuario.setToolTipText("");

        jtfDataSistema.setEditable(false);
        jtfDataSistema.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jtfDataSistema.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfDataSistema.setToolTipText("");

        jlbDataSistema.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbDataSistema.setForeground(new java.awt.Color(204, 0, 0));
        jlbDataSistema.setLabelFor(jtfNomeUsuario);
        jlbDataSistema.setText(i.getProperty("princ19"));
        jlbDataSistema.setFocusable(false);
        jlbDataSistema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbDataSistema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfDataSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDataSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDataSistema)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtbAbastecimentos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbAbastecimentos);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/recarregar.png"))); // NOI18N
        jButton1.setText(i.getProperty("princ21"));
        jButton1.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        jTabbedPane1.addTab(i.getProperty("princ22"), jPanel4);

        jtaLeituras.setColumns(20);
        jtaLeituras.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jtaLeituras.setRows(5);
        jScrollPane2.setViewportView(jtaLeituras);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(i.getProperty("princ20"), jPanel5);

        jLabel11.setText("String CBC para Examinar:");

        jButton2.setText("Examinar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tfBico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBicoActionPerformed(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Bico CBC");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Data");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Tempo Abastec.");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Volume");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Preco Unit.");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Num. Abastec.");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Identificador 1");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Identificador 2");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Encerrante");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Encerrante Anterior");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Status");

        jLabel25.setText("(00=Ok)");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfEncerranteAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEncerrante, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdent2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdent1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfnumAbastec, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfData, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfBico, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfStringExam, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tfStringExam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfBico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tfnumAbastec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tfIdent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfIdent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEncerrante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEncerranteAnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Examinar String CBC", jPanel6);

        tbStatusBicos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbStatusBicos);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Status Bicos", jPanel7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1);

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuBar1.setMaximumSize(new java.awt.Dimension(2000, 30));
        jMenuBar1.setMinimumSize(new java.awt.Dimension(0, 30));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(277, 30));

        jMenu3.setText("Cadastros");
        jMenu3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jMenu3.setMargin(new java.awt.Insets(0, 10, 0, 10));

        jMenuItem11.setText(i.getProperty("princ24"));
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuItem8.setText(i.getProperty("princ25"));
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem4.setText(i.getProperty("princ26"));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem6.setText(i.getProperty("princ27"));
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem5.setText(i.getProperty("princ28"));
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem10.setText(i.getProperty("princ29"));
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuItem31.setText("Motoristas");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem31);

        jMenuItem16.setText(i.getProperty("princ31"));
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem16);

        jMenuItem23.setText(i.getProperty("princ53"));
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem23);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Relatórios");
        jMenu2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jMenu2.setMargin(new java.awt.Insets(0, 0, 0, 10));

        jMenuItem9.setText(i.getProperty("princ33"));
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem12.setText(i.getProperty("princ34"));
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem15.setText(i.getProperty("princ36"));
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);

        jMenuItem20.setText("Veículos");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem20);

        jMenuItem33.setText("Motoristas");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem33);

        jMenuItem13.setText(i.getProperty("princ35"));
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("Abastecimentos");
        jMenu6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jMenu6.setMargin(new java.awt.Insets(0, 0, 0, 10));

        jMenuItem28.setText("Liberar Abastecimento");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem28);

        jMenuItem39.setText("Alterar Preço");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem39);

        jMenuItem34.setText("Histórico de Liberações");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem34);

        jMenuItem32.setText("Reimprimir Cupom");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem32);

        jMenuItem7.setText("Abastecimento Externo");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem7);

        jMenuItem29.setText("Sincronizar Comboio");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem29);

        jMenuBar1.add(jMenu6);

        jMenu5.setText("Controle de Estoque");
        jMenu5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jMenu5.setMargin(new java.awt.Insets(0, 0, 0, 10));

        jMenuItem26.setText("Ajuste de Estoque");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem26);

        jMenuItem27.setText("Consulta de Saldo");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem27);

        jMenuItem30.setText("Consulta Movimentação");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem30);

        jMenuBar1.add(jMenu5);

        jMenu7.setText("Integração");
        jMenu7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jMenu7.setMargin(new java.awt.Insets(0, 0, 0, 10));

        jMenuItem19.setText("Exportar Abastecimentos");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem19);

        jMenuItem38.setText("Importar Abastecimentos");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem38);

        jMenuBar1.add(jMenu7);

        jMenu1.setText("Avançado");
        jMenu1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jMenu1.setMargin(new java.awt.Insets(0, 0, 0, 10));

        jMenuItem1.setText(i.getProperty("princ39"));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem25.setText(i.getProperty("princ54"));
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem25);

        jMenuItem21.setText(i.getProperty("princ43"));
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem21);

        jMenuItem18.setText(i.getProperty("princ42"));
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem18);

        jMenuItem3.setText(i.getProperty("princ44"));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem2.setText(i.getProperty("princ45"));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Restrito");
        jMenu4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jMenu4.setMargin(new java.awt.Insets(0, 0, 0, 10));

        jMenuItem35.setText("Ajuste de Registros");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem35);

        jMenuItem36.setText("Inconsistência Odômetro/Horímetro");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem36);

        jMenuItem22.setText("Modificar/Excluir Abastecimento");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem22);

        jMenuItem24.setText(i.getProperty("princ49"));
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem24);

        jMenuItem14.setText(i.getProperty("princ30"));
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem14);

        jMenuItem17.setText(i.getProperty("princ47"));
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem17);

        jMenuItem37.setText("Excluir Abastecimento");
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem37);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void minimizarJanela(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_minimizarJanela
        setVisible(false);
    }//GEN-LAST:event_minimizarJanela

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        finalizarSistema();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            atualizaUltimoAcesso();
            SobreSistemaGUI ss = new SobreSistemaGUI(null, true);
            ss.setLocationRelativeTo(null);
            ss.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        finalizarSistema();
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            atualizaUltimoAcesso();
            ConfiguracoesGUI c = new ConfiguracoesGUI(null, true);
            c.setLocationRelativeTo(null);
            c.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, i.getProperty("princ1"), true);
            JModal.setContentPane((Container) new BicosGUI());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        try {
            atualizaUltimoAcesso();
            ConsultaAbastecimentos c = new ConsultaAbastecimentos(null, true);
            c.setLocationRelativeTo(null);
            c.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, i.getProperty("princ2"), true);
            JModal.setContentPane((Container) new CombustiveisGUI());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, i.getProperty("princ3"), true);
            JModal.setContentPane((Container) new OperadoresGUI());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
            jButton1ActionPerformed(evt);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        carregaAbastecimentos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, i.getProperty("princ4"), true);
            JModal.setContentPane((Container) new FrotasGUI());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, i.getProperty("princ5"), true);
            JModal.setContentPane((Container) new EmpresasGUI());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, i.getProperty("princ6"), true);
            JModal.setContentPane((Container) new VeiculosGUI());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        try {
            atualizaUltimoAcesso();
            ConsultaMedia c = new ConsultaMedia(null, true);
            c.setLocationRelativeTo(null);
            c.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        finalizarSistema();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, i.getProperty("princ7"), true);
            JModal.setContentPane((Container) new BicosGUI());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, i.getProperty("princ6"), true);
            JModal.setContentPane((Container) new VeiculosGUI());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, i.getProperty("princ3"), true);
            JModal.setContentPane((Container) new OperadoresGUI());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        if (jLabel6.isEnabled()) {
            try {
                atualizaUltimoAcesso();
                AbastecimentoGUI p = new AbastecimentoGUI(null, true);
                p.setLocationRelativeTo(null);
                p.setVisible(true);
                jButton1ActionPerformed(null);
            } catch (Exception e) {
                GeraLog g = new GeraLog();
                g.gravaErro(e);
                g.close();
                JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
            }
        }
    }//GEN-LAST:event_jLabel6MousePressed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        try {
            atualizaUltimoAcesso();
            GlobalParameter.getInstance();
            Connection conn = GlobalParameter.getConn();
            JDialog viewer = new JDialog(new javax.swing.JFrame(), i.getProperty("princ8"), true);
            viewer.setSize(CapturaTamanhoTela.getWidthMonitor(), CapturaTamanhoTela.getHeightMonitor());
            viewer.setLocationRelativeTo(null);

            Map parameters = new HashMap();
            InputStream fileJasper;

            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);
            switch (properties.getProperty("cbc_casas_decimais_volume").trim()) {
                case "1":
                    fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/inconsistencia_1.jasper");
                    break;
                case "2":
                    fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/inconsistencia_2.jasper");
                    break;
                case "3":
                    fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/inconsistencia_3.jasper");
                    break;
                default:
                    fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/inconsistencia_2.jasper");
                    break;
            }

            if (GlobalParameter.getInstance().getOperador().getEmpresa() == null) {
                parameters.put("OPERADOR", "");
                parameters.put("NOME_EMPRESA", "");
                parameters.put("ENDERECO_COMPLETO", "");

            } else {
                parameters.put("OPERADOR", GlobalParameter.getInstance().getOperador().getNomeOperador());
                parameters.put("NOME_EMPRESA", GlobalParameter.getInstance().getOperador().getEmpresa().getNomeEmpresa());
                parameters.put("ENDERECO_COMPLETO", GlobalParameter.getInstance().getOperador().getEmpresa().getEnderecoCompleto());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(fileJasper, parameters, conn);
            JasperViewer jrViewer = new JasperViewer(jasperPrint, true);
            viewer.getContentPane().add(jrViewer.getContentPane());
            viewer.setVisible(true);
        } catch (HeadlessException | IOException | JRException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, i.getProperty("princ9"), true);
            JModal.setContentPane((Container) new AbastecimentoManual());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
            jButton1ActionPerformed(evt);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        try {
            atualizaUltimoAcesso();
            ConsultaEncerrantes c = new ConsultaEncerrantes(null, true);
            c.setLocationRelativeTo(null);
            c.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, i.getProperty("princ10"), true);
            JModal.setContentPane((Container) new FabricantesGUI());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
            jButton1ActionPerformed(evt);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, i.getProperty("princ4"), true);
            JModal.setContentPane((Container) new FrotasGUI());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jLabel8MousePressed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
//        try {
//            atualizaUltimoAcesso();
//            ModificaOdometroGUI al = new ModificaOdometroGUI(null, true);
//            al.setLocationRelativeTo(null);
//            al.setVisible(true);
//        } catch (Exception e) {
//            GeraLog g = new GeraLog();
//            g.gravaErro(e);
//            g.close();
//            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
//        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        try {
            atualizaUltimoAcesso();
            ExportarAbastecimentosGUI e = new ExportarAbastecimentosGUI(null, true);
            e.setLocationRelativeTo(null);
            e.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        try {
            atualizaUltimoAcesso();
            AlterarAbastecGUI al = new AlterarAbastecGUI(null, true);
            al.setLocationRelativeTo(null);
            al.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        try {
            atualizaUltimoAcesso();
            ExecutaSQL ex = new ExecutaSQL(null, true);
            ex.setLocationRelativeTo(null);
            ex.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        try {
            atualizaUltimoAcesso();
            NotasVersaoGUI n = new NotasVersaoGUI(null, true);
            n.setLocationRelativeTo(null);
            n.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        try {
            atualizaUltimoAcesso();
            ConsultaMarcadores c = new ConsultaMarcadores(null, true);
            c.setLocationRelativeTo(null);
            c.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        try {
            atualizaUltimoAcesso();
            AtualizarLicenca li = new AtualizarLicenca(null, true);
            li.setLocationRelativeTo(null);
            li.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        if (jLabel9.isEnabled()) {
            try {
                atualizaUltimoAcesso();
                SincronizaComboio s = new SincronizaComboio(null, true);
                s.setLocationRelativeTo(null);
                s.setVisible(true);
            } catch (Exception e) {
                GeraLog g = new GeraLog();
                g.gravaErro(e);
                g.close();
                JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
            }
        }
    }//GEN-LAST:event_jLabel9MousePressed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        atualizaUltimoAcesso();
        AlterarIdioma a = new AlterarIdioma(null, true);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        i = GlobalParameter.getIdioma();
        atualizaIdioma();
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        atualizaUltimoAcesso();
        VincularIdent v = new VincularIdent(null, true);
        v.setLocationRelativeTo(null);
        v.setVisible(true);
        i = GlobalParameter.getIdioma();
        atualizaIdioma();
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        try {
            atualizaUltimoAcesso();
            AjusteEstoque a = new AjusteEstoque(null, true);
            a.setLocationRelativeTo(null);
            a.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        try {
            atualizaUltimoAcesso();
            EstoqueAtual e = new EstoqueAtual(null, true);
            e.setLocationRelativeTo(null);
            e.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        if (jLabel6.isEnabled()) {
            try {
                atualizaUltimoAcesso();
                AbastecimentoGUI p = new AbastecimentoGUI(null, true);
                p.setLocationRelativeTo(null);
                p.setVisible(true);
                jButton1ActionPerformed(null);
            } catch (Exception e) {
                GeraLog g = new GeraLog();
                g.gravaErro(e);
                g.close();
                JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
            }
        } else {
            JConfirmMessage.showMessageDialog("Falha ao conectar com dispositivo.", i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        try {
            atualizaUltimoAcesso();
            SincronizaComboio s = new SincronizaComboio(null, true);
            s.setLocationRelativeTo(null);
            s.setVisible(true);
            jButton1ActionPerformed(evt);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        try {
            atualizaUltimoAcesso();
            ConsultaMovEstoque c = new ConsultaMovEstoque(null, true);
            c.setLocationRelativeTo(null);
            c.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jLabel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MousePressed
        try {
            atualizaUltimoAcesso();
            ExportarAbastecimentosGUI e = new ExportarAbastecimentosGUI(null, true);
            e.setLocationRelativeTo(null);
            e.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jLabel10MousePressed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        atualizaUltimoAcesso();
        JDialog JModal = new JDialog(this, "Abastecimento Externo", true);
        JModal.setContentPane((Container) new AbastecimentoExterno());
        JModal.pack();
        JModal.setLocationRelativeTo(null);
        JModal.setVisible(true);
        jButton1ActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String mensagem = tfStringExam.getText().trim();

        String dia = mensagem.substring(35, 37);
        String mes = mensagem.substring(41, 43);
        String ano = "20" + mensagem.substring(43, 45);
        String hora = mensagem.substring(37, 39);
        String minuto = mensagem.substring(39, 41);

        String bicoCBC = mensagem.substring(33, 35);
        tfBico.setText(bicoCBC);

        String data = dia + "/" + mes + "/" + ano + " " + hora + ":" + minuto;
        tfData.setText(data);

        String tempoAbastec = hexaToTime(mensagem.substring(29, 33));
        tfTempo.setText(tempoAbastec);

        String volume = mensagem.substring(17, 23);
        tfVolume.setText(volume);

        String preco = mensagem.substring(23, 27);
        tfPreco.setText(preco);

        String ident1 = mensagem.substring(85, 101);
        tfIdent1.setText(ident1);

        String ident2 = mensagem.substring(101, 117);
        tfIdent2.setText(ident2);

        String numAbastec = mensagem.substring(45, 51);
        tfnumAbastec.setText(numAbastec);

        String encerrante = mensagem.substring(51, 61);
        tfEncerrante.setText(encerrante);

        String encerranteAnt = mensagem.substring(61, 71);
        tfEncerranteAnt.setText(encerranteAnt);

        //String totPagar = "";
        //tfTotPag
        String status = mensagem.substring(mensagem.length() - 4, mensagem.length() - 2);
        tfStatus.setText(status);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tfBicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfBicoActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        try {
            atualizaUltimoAcesso();
            JDialog JModal = new JDialog(this, "Cadastro de Motoristas", true);
            JModal.setContentPane((Container) new MotoristasGUI());
            JModal.pack();
            JModal.setLocationRelativeTo(null);
            JModal.setVisible(true);
            jButton1ActionPerformed(evt);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        try {
            atualizaUltimoAcesso();
            ReimprimirCupomGUI re = new ReimprimirCupomGUI(null, true);
            re.setLocationRelativeTo(null);
            re.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        try {
            atualizaUltimoAcesso();
            GlobalParameter.getInstance();
            Connection conn = GlobalParameter.getConn();
            JDialog viewer = new JDialog(new javax.swing.JFrame(), i.getProperty("princ8"), true);
            viewer.setSize(CapturaTamanhoTela.getWidthMonitor(), CapturaTamanhoTela.getHeightMonitor());
            viewer.setLocationRelativeTo(null);

            Map parameters = new HashMap();
            InputStream fileJasper;

            fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/motoristas.jasper");

            if (GlobalParameter.getInstance().getOperador().getEmpresa() == null) {
                parameters.put("OPERADOR", "");
                parameters.put("NOME_EMPRESA", "");
                parameters.put("ENDERECO_COMPLETO", "");

            } else {
                parameters.put("OPERADOR", GlobalParameter.getInstance().getOperador().getNomeOperador());
                parameters.put("NOME_EMPRESA", GlobalParameter.getInstance().getOperador().getEmpresa().getNomeEmpresa());
                parameters.put("ENDERECO_COMPLETO", GlobalParameter.getInstance().getOperador().getEmpresa().getEnderecoCompleto());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(fileJasper, parameters, conn);
            JasperViewer jrViewer = new JasperViewer(jasperPrint, true);
            viewer.getContentPane().add(jrViewer.getContentPane());
            viewer.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        try {
            //atualizaUltimoAcesso();
            HistoricoLiberacoesGUI al = new HistoricoLiberacoesGUI(null, true);
            al.setLocationRelativeTo(null);
            al.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        try {
            atualizaUltimoAcesso();
            AjustarRegistros al = new AjustarRegistros(null, true);
            al.setLocationRelativeTo(null);
            al.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        try {
            atualizaUltimoAcesso();
            MarcadoresInconsistentesGUI m = new MarcadoresInconsistentesGUI(null, true);
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
//        try {
//            atualizaUltimoAcesso();
//            ExcluirAbastecimentoGUI m = new ExcluirAbastecimentoGUI(null, true);
//            m.setLocationRelativeTo(null);
//            m.setVisible(true);
//            jButton1ActionPerformed(null);
//        } catch (Exception e) {
//            GeraLog g = new GeraLog();
//            g.gravaErro(e);
//            g.close();
//            //JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
//        }
    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        JConfirmMessage.showMessageDialog("Aguarde. Em breve.", i.getProperty("sis1"));
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        try {
            atualizaUltimoAcesso();
            AlterarPrecoGUI a = new AlterarPrecoGUI(null, true);
            a.setLocationRelativeTo(null);
            a.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void jLabel24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MousePressed
        try {
            atualizaUltimoAcesso();
            AjusteEstoque a = new AjusteEstoque(null, true);
            a.setLocationRelativeTo(null);
            a.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jLabel24MousePressed

    public String hexaToTime(String timeHex) {
        try {
            long elapsedTime = Long.parseLong(timeHex, 16);
            String format = String.format("%%0%dd", 2);
            String seconds = String.format(format, elapsedTime % 60);
            String minutes = String.format(format, (elapsedTime % 3600) / 60);
            String hours = String.format(format, elapsedTime / 3600);
            //DateFormat formato = new SimpleDateFormat("HH:mm:ss");
            String dataString = hours + ":" + minutes + ":" + seconds;
            return dataString;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return null;
        }
    }

    public void chamaRelatorio(InputStream caminho) {
        try {
            atualizaUltimoAcesso();
            GlobalParameter.getInstance();
            Connection conn = GlobalParameter.getConn();
            JDialog viewer = new JDialog(new javax.swing.JFrame(), i.getProperty("princ8"), true);
            viewer.setSize(CapturaTamanhoTela.getWidthMonitor(), CapturaTamanhoTela.getHeightMonitor());
            viewer.setLocationRelativeTo(null);
            Map parameters = new HashMap();

            JasperPrint jasperPrint = JasperFillManager.fillReport(caminho, parameters, conn);
            JasperViewer jrViewer = new JasperViewer(jasperPrint, true);
            viewer.getContentPane().add(jrViewer.getContentPane());
            viewer.setVisible(true);
        } catch (HeadlessException | JRException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }

    public static void atualizaUltimoAcesso() {
        jtfDataSistema.setText(GlobalParameter.getInstance().getDataSistema());

        Licenca lic = LicencaDB.buscaLicenca();
        if (lic != null) {
            Base64.Decoder de = Base64.getDecoder();
            String s1 = new String(de.decode(lic.getUltimoAcesso()));
            Long dataUltimoAcesso = Long.parseLong(s1);

            Base64.Encoder e = Base64.getEncoder();
            Date d = new Date();
            d.setHours(00);
            d.setMinutes(00);
            d.setSeconds(01);
            Long l = d.getTime();

            if (dataUltimoAcesso < l) {
                LicencaDB.atualizaUltimoAcesso(e.encodeToString(l.toString().getBytes()));
            }
        }
    }

    private void finalizarSistema() {
        try {
            atualizaUltimoAcesso();
            int y = JConfirmMessage.showOptionDialog(i.getProperty("sis1"), i.getProperty("princ11"));
            if (y == JOptionPane.YES_OPTION) {
                GlobalParameter.getInstance().getCbc().desconetarDispositivo();
                System.exit(0);
            }
        } catch (Exception e) {
            //GeraLog g = new GeraLog();
            //g.gravaErro(e);
            //g.close();
            //JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    public static javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jlbDataSistema;
    private static javax.swing.JTextArea jtaLeituras;
    public static javax.swing.JTable jtbAbastecimentos;
    public static javax.swing.JTextField jtfDataSistema;
    public static javax.swing.JTextField jtfNomeUsuario;
    public static javax.swing.JTable tbStatusBicos;
    private javax.swing.JTextField tfBico;
    private javax.swing.JTextField tfData;
    private javax.swing.JTextField tfEncerrante;
    private javax.swing.JTextField tfEncerranteAnt;
    private javax.swing.JTextField tfIdent1;
    private javax.swing.JTextField tfIdent2;
    private javax.swing.JTextField tfPreco;
    private javax.swing.JTextField tfStatus;
    private javax.swing.JTextField tfStringExam;
    private javax.swing.JTextField tfTempo;
    private javax.swing.JTextField tfVolume;
    private javax.swing.JTextField tfnumAbastec;
    // End of variables declaration//GEN-END:variables

    public static void carregaAbastecimentos() {
        try {
            atualizaUltimoAcesso();
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);

            if (properties.getProperty("sis_controla_motorista").trim() != null) {
                if (properties.getProperty("sis_controla_motorista").trim().toUpperCase().equals("S")) {
                    jtbAbastecimentos.setModel(new AbastecimentosMotoristaTableModel());
                } else {
                    jtbAbastecimentos.setModel(new AbastecimentosTableModel());
                }
            } else {
                jtbAbastecimentos.setModel(new AbastecimentosTableModel());
            }

            //jtbAbastecimentos.setModel(new AbastecimentosTableModel());
            jtbAbastecimentos.setAutoCreateRowSorter(false);
            jtbAbastecimentos.setRowHeight(20);

            TableColumnModel columnModel = jtbAbastecimentos.getColumnModel();
            TimestampTableRender timestampRenderer = new TimestampTableRender();
            //TimeTableRender timeRenderer = new TimeTableRender();

            EmpresaTableCellRender empresaRender = new EmpresaTableCellRender();
            FrotaTableCellRender frotaRender = new FrotaTableCellRender();
            BicoTableCellRender bicoRender = new BicoTableCellRender();
            VeiculoTableCellRender veiculoRender = new VeiculoTableCellRender();
            OperadorTableCellRender operadorRender = new OperadorTableCellRender();
            VolumeTableCellRender volumeRender = new VolumeTableCellRender();

            columnModel.getColumn(0).setCellRenderer(timestampRenderer);
            columnModel.getColumn(2).setCellRenderer(volumeRender);
            //columnModel.getColumn(3).setCellRenderer(timeRenderer);
            columnModel.getColumn(1).setCellRenderer(bicoRender);

            if (properties.getProperty("sis_controla_motorista").trim() != null) {
                if (properties.getProperty("sis_controla_motorista").trim().toUpperCase().equals("S")) {
                    columnModel.getColumn(4).setCellRenderer(veiculoRender);
                    columnModel.getColumn(6).setCellRenderer(frotaRender);
                    columnModel.getColumn(7).setCellRenderer(empresaRender);
                    columnModel.getColumn(8).setCellRenderer(operadorRender);
                } else {
                    columnModel.getColumn(3).setCellRenderer(veiculoRender);
                    columnModel.getColumn(5).setCellRenderer(frotaRender);
                    columnModel.getColumn(6).setCellRenderer(empresaRender);
                    columnModel.getColumn(7).setCellRenderer(operadorRender);
                }
            } else {
                columnModel.getColumn(3).setCellRenderer(veiculoRender);
                columnModel.getColumn(5).setCellRenderer(frotaRender);
                columnModel.getColumn(6).setCellRenderer(empresaRender);
                columnModel.getColumn(7).setCellRenderer(operadorRender);
            }

            if (properties.getProperty("sis_controla_motorista").trim() != null) {
                if (properties.getProperty("sis_controla_motorista").trim().toUpperCase().equals("S")) {
                    getModelAbastecMotorista().limpar();
                    getModelAbastecMotorista().addListaAbastecimentos(AbastecimentosDB.buscaAbastecimentos(Integer.parseInt(properties.getProperty("sis_qtd_historico_abastec").trim())));
                    jLabel7.setText(i.getProperty("princ50") + " " + getModelAbastecMotorista().getRowCount() + " " + i.getProperty("princ51"));
                } else {
                    getModelAbastec().limpar();
                    getModelAbastec().addListaAbastecimentos(AbastecimentosDB.buscaAbastecimentos(Integer.parseInt(properties.getProperty("sis_qtd_historico_abastec").trim())));
                    jLabel7.setText(i.getProperty("princ50") + " " + getModelAbastec().getRowCount() + " " + i.getProperty("princ51"));
                }
            } else {
                getModelAbastec().limpar();
                getModelAbastec().addListaAbastecimentos(AbastecimentosDB.buscaAbastecimentos(Integer.parseInt(properties.getProperty("sis_qtd_historico_abastec").trim())));
                jLabel7.setText(i.getProperty("princ50") + " " + getModelAbastec().getRowCount() + " " + i.getProperty("princ51"));
            }

            columnModel.getColumn(0).setPreferredWidth(70);
            columnModel.getColumn(1).setPreferredWidth(50);
            columnModel.getColumn(2).setPreferredWidth(10);
            //columnModel.getColumn(3).setPreferredWidth(10);
//        columnModel.getColumn(4).setPreferredWidth(200);
//        columnModel.getColumn(5).setPreferredWidth(200);

            jtfNomeUsuario.setText(GlobalParameter.getInstance().getOperador().getNomeOperador());
            jtfNomeUsuario.setCaretPosition(0);

        } catch (IOException | NumberFormatException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }

    private void carregaBallon() throws AWTException {
        try {
            TyBallon ball = new TyBallon();
            ball.setImageIcon("/imagens/icontray.png", "Icone do Tray");
            //ball.setUsePopMenu(true);
            TrayIcon trayIcon = ball.create("AutoCAF - " + i.getProperty("sis2"));

            SystemTray tray = SystemTray.getSystemTray();
            tray.add(trayIcon);
            trayIcon.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                        setVisible(true);
                        setState(JFrame.NORMAL);
                    }
                }
            });

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }

    private void iniciaConexaoCompanyTec() {
        try {
            this.cbc = GlobalParameter.getInstance().getCbc();
            this.cbcStatus = GlobalParameter.getInstance().getCbcStatus();
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);
            if ((!cbc.isConectado()) && (properties.getProperty("cbc_conecta_bomba").trim().toUpperCase().equals("S"))) {
                cbc.conectarDispositivo(properties.getProperty("cbc_host").trim(), Integer.valueOf(properties.getProperty("cbc_porta").trim()));

                GeraLog ger = new GeraLog();
                ger.gravaMensagensLog(new Throwable("Conectou na porta da cbc"));
                ger.close();

                Thread.sleep(2000);
                cbcStatus.conectarDispositivo(properties.getProperty("cbc_host").trim(), Integer.valueOf(properties.getProperty("cbc_porta_status_bico").trim()));

                GeraLog ge = new GeraLog();
                ge.gravaMensagensLog(new Throwable("Conectou na porta de status dos bicos"));
                ge.close();

            } else {
                //////////////////////////////jMenuItem7.setEnabled(false);
                jMenuItem1.setEnabled(false);
                jLabel6.setEnabled(false);
                jMenuItem28.setEnabled(false);
                jMenuItem39.setEnabled(false);

            }
            if (properties.getProperty("cbc_conecta_bomba").trim().toUpperCase().equals("S")) {
                if (cbc.isConectado()) {

                    //coloca bicos em modo de bloqueio
                    for (Bico bico : BicosDB.buscaBicosInternos(false)) {
                        cbcStatus.bloqueiaBico(bico.getIdCBC());
                        //try {
                        Thread.sleep(500);
                        GeraLog ger = new GeraLog();
                        ger.gravaMensagensLog(new Throwable("Bloqueou Bicos"));
                        ger.close();
                    }

                    //ajusta hora da CBC
                    GregorianCalendar calendario = new GregorianCalendar();
                    Integer dia = calendario.get(GregorianCalendar.DAY_OF_MONTH);
                    Integer mes = calendario.get(GregorianCalendar.MONTH) + 1;
                    Integer ano = calendario.get(GregorianCalendar.YEAR) - 2000;
                    Integer hora = calendario.get(GregorianCalendar.HOUR_OF_DAY);
                    Integer minuto = calendario.get(GregorianCalendar.MINUTE);
                    Integer segundo = calendario.get(GregorianCalendar.SECOND);
                    Integer semana = calendario.get(GregorianCalendar.DAY_OF_WEEK);

                    String sDia, sMes, sAno, sHora, sMinuto, sSegundo, sSemana;

                    if (dia.toString().length() == 1) {
                        sDia = "0" + dia.toString();
                    } else {
                        sDia = dia.toString();
                    }

                    if (mes.toString().length() == 1) {
                        sMes = "0" + mes.toString();
                    } else {
                        sMes = mes.toString();
                    }

                    if (ano.toString().length() == 1) {
                        sAno = "0" + ano.toString();
                    } else {
                        sAno = ano.toString();
                    }

                    if (hora.toString().length() == 1) {
                        sHora = "0" + hora.toString();
                    } else {
                        sHora = hora.toString();
                    }
                    if (minuto.toString().length() == 1) {
                        sMinuto = "0" + minuto.toString();
                    } else {
                        sMinuto = minuto.toString();
                    }

                    if (segundo.toString().length() == 1) {
                        sSegundo = "0" + segundo.toString();
                    } else {
                        sSegundo = segundo.toString();
                    }

                    if (semana.toString().length() == 1) {
                        sSemana = "0" + semana.toString();
                    } else {
                        sSemana = semana.toString();
                    }

                    cbcStatus.ajusteRelogioExtendido(sDia, sMes, sAno, sHora, sMinuto, sSegundo, sSemana);

                    GeraLog ge = new GeraLog();
                    ge.gravaMensagensLog(new Throwable("Ajustou relógio cbc"));
                    ge.close();

                    realTimeConsultasCompanyTec = new Thread(new ConsultaAbastecimento(), "ConsultaAbastecimento");
                    realTimeConsultasCompanyTec.start();

                    analisaFilaCompanyTec = new Thread(new AnalisaAbastescimentos(), "AnalisaAbastescimentos");
                    analisaFilaCompanyTec.start();

                    consultaStatus = new Thread(new ConsultaStatusBico(), "ConsultaStatus");
                    consultaStatus.start();

                    if (properties.getProperty("cbc_conecta_twc").trim().toUpperCase().equals("S")) {
                        leituraTWC = new Thread(new TerminalMovelThread(), "TerminalMovelThread");
                        leituraTWC.start();
                        GeraLog ger = new GeraLog();
                        ger.gravaMensagensLog(new Throwable("Conectou na porta do twc"));
                        ger.close();
                    }
                } else {
                    JConfirmMessage.showMessageDialog(i.getProperty("sis3"), i.getProperty("sis1"));
                    jMenuItem1.setEnabled(false);
                    jLabel6.setEnabled(false);
                    jMenuItem28.setEnabled(false);
                    jMenuItem39.setEnabled(false);
                }
            } else {
                jMenuItem1.setEnabled(false);
                jLabel6.setEnabled(false);
                jMenuItem28.setEnabled(false);
                jMenuItem39.setEnabled(false);
                //theared para atualizar a lista
                atualizaGridAbastecimentos = new Thread(new AtualizaGridAbastecimentos(), "AtualizaGridAbastecimentos");
                atualizaGridAbastecimentos.start();

            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
            jMenuItem1.setEnabled(false);
            jLabel6.setEnabled(false);
            jMenuItem28.setEnabled(false);
            jMenuItem39.setEnabled(false);
        }
    }

    public static void atualizaLeituras(String s) {
        try {
            if (jtaLeituras.getLineCount() > 30) {
                jtaLeituras.setText("");
            }
            String old = jtaLeituras.getText();
            jtaLeituras.setText("");
            jtaLeituras.append("--> " + s + "\n" + old);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
        }
    }

    private void atualizaIdioma() {
        setTitle("AutoCAF - " + i.getProperty("sis2"));
        jLabel1.setToolTipText(i.getProperty("princ52"));
        jLabel3.setToolTipText(i.getProperty("princ26"));
        jLabel4.setToolTipText(i.getProperty("princ28"));
        jLabel5.setToolTipText(i.getProperty("princ13"));
        jLabel6.setToolTipText(i.getProperty("princ14"));
        jLabel8.setToolTipText(i.getProperty("princ15"));
        jLabel9.setToolTipText(i.getProperty("princ16"));
        jLabel2.setText(i.getProperty("princ17"));
        jlbDataSistema.setText(i.getProperty("princ19"));
        jButton1.setText(i.getProperty("princ21"));
        jMenu3.setText(i.getProperty("princ23"));
        jMenuItem11.setText(i.getProperty("princ24"));
        jMenuItem8.setText(i.getProperty("princ25"));
        jMenuItem4.setText(i.getProperty("princ26"));
        jMenuItem6.setText(i.getProperty("princ27"));
        jMenuItem5.setText(i.getProperty("princ28"));
        jMenuItem10.setText(i.getProperty("princ29"));
        jMenuItem14.setText(i.getProperty("princ30"));
        jMenuItem16.setText(i.getProperty("princ31"));
        jMenuItem23.setText(i.getProperty("princ53"));
        jMenu2.setText(i.getProperty("princ32"));
        jMenuItem9.setText(i.getProperty("princ33"));
        jMenuItem12.setText(i.getProperty("princ34"));
        jMenuItem13.setText(i.getProperty("princ35"));
        jMenuItem15.setText(i.getProperty("princ36"));
        jMenuItem20.setText(i.getProperty("princ37"));
        jMenu1.setText(i.getProperty("princ38"));
        jMenuItem25.setText(i.getProperty("princ54"));
        jMenuItem1.setText(i.getProperty("princ39"));
        jMenuItem19.setText(i.getProperty("princ41"));
        jMenuItem18.setText(i.getProperty("princ42"));
        jMenuItem21.setText(i.getProperty("princ43"));
        jMenuItem3.setText(i.getProperty("princ44"));
        jMenuItem2.setText(i.getProperty("princ45"));
        jMenu4.setText(i.getProperty("princ46"));
        jMenuItem17.setText(i.getProperty("princ47"));
        jMenuItem22.setText(i.getProperty("princ48"));
        jMenuItem24.setText(i.getProperty("princ49"));

    }

    public static void atualizaStatusBicos() {
        try {
            String cbc = "";
            for (int linha = 0; linha < tbStatusBicos.getRowCount(); linha++) {
                cbc = (String) tbStatusBicos.getModel().getValueAt(linha, 0);
                if (cbc != null) {
                    if (cbc.equals("04")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico04(), linha, 2);
                    } else if (cbc.equals("44")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico44(), linha, 2);
                    } else if (cbc.equals("05")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico05(), linha, 2);
                    } else if (cbc.equals("45")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico45(), linha, 2);
                    } else if (cbc.equals("08")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico08(), linha, 2);
                    } else if (cbc.equals("48")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico48(), linha, 2);
                    } else if (cbc.equals("09")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico09(), linha, 2);
                    } else if (cbc.equals("0C")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico0C(), linha, 2);
                    } else if (cbc.equals("4C")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico4C(), linha, 2);
                    } else if (cbc.equals("4D")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico4D(), linha, 2);
                    } else if (cbc.equals("0D")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico0D(), linha, 2);
                    } else if (cbc.equals("10")) {
                        tbStatusBicos.getModel().setValueAt(GlobalParameter.getStatusBico10(), linha, 2);
                    } else if (cbc.equals("50")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico50(), linha, 2);
                    } else if (cbc.equals("11")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico11(), linha, 2);
                    } else if (cbc.equals("51")) {
                        getModelStatusBicos().setValueAt(GlobalParameter.getStatusBico51(), linha, 2);
                    }

                    tbStatusBicos.getColumnModel().getColumn(2).setCellRenderer(new ColorirCelula());
                    getModelStatusBicos().atualizar();
                }
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    private void carregaStatusBicos() {
        try {
            atualizaUltimoAcesso();
            tbStatusBicos.setModel(new StatusBicosTableModel());
            tbStatusBicos.setAutoCreateRowSorter(false);
            tbStatusBicos.setRowHeight(20);

            TableColumnModel columnModel = tbStatusBicos.getColumnModel();
            BicoTableCellRender bicoRender = new BicoTableCellRender();
            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);
            columnModel.getColumn(1).setCellRenderer(bicoRender);
            columnModel.getColumn(2).setPreferredWidth(600);

            getModelStatusBicos().limpar();
            getModelStatusBicos().addListaBicos(BicosDB.buscaBicosInternos(false));

            columnModel.getColumn(2).setCellRenderer(new ColorirCelula());

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }
}
