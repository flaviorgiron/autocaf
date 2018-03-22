package view.movimentacao;

import ctr.GlobalParameter;
import bd.*;
import ctr.AnalisaAbastescimentos;
import ctr.Cbc;
import ctr.ConsultaAbastecimento;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import util.*;
import model.*;

public class AbastecimentoGUI extends javax.swing.JDialog {

    public Cbc cbc = null;

    private static String tipoControlePreset = "L";
    public Thread realTimeConsultasCompanyTec = null;
    public Thread analisaFilaCompanyTec = null;
    private String controlaMotorista = "N";

    public AbastecimentoGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);

            this.setSize(CapturaTamanhoTela.getWidthMonitor(), CapturaTamanhoTela.getHeightMonitor());
            tfBico.setText("");
            tfVeiculo.setText("");
            tfHodometro.setText("");
            tfOperador.setText("");
            lbBico.setText("");
            lbVeiculo.setText("");
            lbOperador.setText("");

            if (properties.getProperty("sis_controla_motorista").trim() != null) {
                if (properties.getProperty("sis_controla_motorista").trim().toUpperCase().equals("S")) {
                    lbMotorista.setVisible(true);
                    tfMotorista.setVisible(true);
                    tiMotorista.setVisible(true);
                    controlaMotorista = "S";
                } else {
                    lbMotorista.setVisible(false);
                    tfMotorista.setVisible(false);
                    tiMotorista.setVisible(false);
                }
            } else {
                lbMotorista.setVisible(false);
                tfMotorista.setVisible(false);
                tiMotorista.setVisible(false);
            }

            lbMotorista.setText("");
            tfMotorista.setText("");

            lbOdometroAnt.setText("--------------------");
            tfVeiculo.requestFocus();

            lbMsg.setText("");

            tfBico.setDocument(new FixedLengthUpperDocument(40));
            tfVeiculo.setDocument(new FixedLengthUpperDocument(40));

            tfMotorista.setDocument(new FixedLengthUpperDocument(40));

            tfOperador.setDocument(new FixedLengthUpperDocument(40));
            tfHodometro.setDocument(new OnlyNumberFieldDocument(6));

            //iniciaConexaoCompanyTec();
            //envia comando de bloqueio
            this.cbc = GlobalParameter.getInstance().getCbc();

            if ((!cbc.isConectado()) && (properties.getProperty("cbc_conecta_bomba").trim().toUpperCase().equals("S"))) {
                cbc.conectarDispositivo(properties.getProperty("cbc_host").trim(), Integer.valueOf(properties.getProperty("cbc_porta").trim()));

                realTimeConsultasCompanyTec = new Thread(new ConsultaAbastecimento(), "ConsultaAbastecimento");
                realTimeConsultasCompanyTec.start();

                analisaFilaCompanyTec = new Thread(new AnalisaAbastescimentos(), "AnalisaAbastescimentos");
                analisaFilaCompanyTec.start();

                for (Bico bico : BicosDB.buscaBicosInternos(false)) {
                    cbc.bloqueiaBico(bico.getIdCBC());
                    //Thread.sleep(500);
                }

            }

            if (!properties.getProperty("sis_bicopadrao").trim().equals("")) {
                tfBico.setText(properties.getProperty("sis_bicopadrao").trim());
                tfBicoFocusLost(null);
                tfVeiculo.requestFocus();
                tfVeiculo.requestFocus();
            }
            tipoControlePreset = properties.getProperty("cbc_tipocontrole_preset").trim();
            //senhaAtual.setVisible(false);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
        setIconImage(icon.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tiBico = new javax.swing.JLabel();
        tfBico = new javax.swing.JTextField();
        lbBico = new javax.swing.JLabel();
        tiVeiculo = new javax.swing.JLabel();
        tfVeiculo = new javax.swing.JTextField();
        lbOperador = new javax.swing.JLabel();
        tiHodometro = new javax.swing.JLabel();
        tfHodometro = new javax.swing.JTextField();
        tiOperador = new javax.swing.JLabel();
        tfOperador = new javax.swing.JTextField();
        lbVeiculo = new javax.swing.JLabel();
        btAbastecer = new javax.swing.JButton();
        lbHora = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbMsg = new javax.swing.JLabel();
        tiOperador1 = new javax.swing.JLabel();
        senhaInformada = new javax.swing.JPasswordField();
        lbOdometroAnt = new javax.swing.JLabel();
        tiHodometro1 = new javax.swing.JLabel();
        tiMotorista = new javax.swing.JLabel();
        tfMotorista = new javax.swing.JTextField();
        lbMotorista = new javax.swing.JLabel();

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tiBico.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tiBico.setText("Estação de Abastecimento");

        tfBico.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        tfBico.setText("tfBico");
        tfBico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfBicoFocusLost(evt);
            }
        });
        tfBico.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tfBicoPropertyChange(evt);
            }
        });
        tfBico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfBicoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBicoKeyReleased(evt);
            }
        });

        lbBico.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        lbBico.setText("lbBico");

        tiVeiculo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tiVeiculo.setText("Veículo");

        tfVeiculo.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        tfVeiculo.setText("tfVeiculo");
        tfVeiculo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfVeiculoFocusLost(evt);
            }
        });
        tfVeiculo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfVeiculoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfVeiculoKeyReleased(evt);
            }
        });

        lbOperador.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        lbOperador.setText("lbOperador");

        tiHodometro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tiHodometro.setText("Odômetro Anterior");

        tfHodometro.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        tfHodometro.setText("tfHodometro");
        tfHodometro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfHodometroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfHodometroKeyReleased(evt);
            }
        });

        tiOperador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tiOperador.setText("Operador");

        tfOperador.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        tfOperador.setText("tfOperador");
        tfOperador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfOperadorFocusLost(evt);
            }
        });
        tfOperador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfOperadorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfOperadorKeyReleased(evt);
            }
        });

        lbVeiculo.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        lbVeiculo.setText("lbVeiculo");

        btAbastecer.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btAbastecer.setText("Abastecer (Pressione tecla enter)");
        btAbastecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAbastecerActionPerformed(evt);
            }
        });
        btAbastecer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btAbastecerKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btAbastecerKeyReleased(evt);
            }
        });

        lbHora.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbMsg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbMsg.setForeground(new java.awt.Color(255, 0, 0));
        lbMsg.setText("jLabel1");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(lbMsg, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 758, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(lbMsg, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tiOperador1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tiOperador1.setText("Senha");

        senhaInformada.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        senhaInformada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                senhaInformadaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                senhaInformadaKeyReleased(evt);
            }
        });

        lbOdometroAnt.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        lbOdometroAnt.setText("lbOdometroAnt");

        tiHodometro1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tiHodometro1.setText("Odômetro Atual");

        tiMotorista.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tiMotorista.setText("Motorista");

        tfMotorista.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        tfMotorista.setText("tfMotorista");
        tfMotorista.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfMotoristaFocusLost(evt);
            }
        });
        tfMotorista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfMotoristaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfMotoristaKeyReleased(evt);
            }
        });

        lbMotorista.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        lbMotorista.setText("lbMotorista");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lbHora, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10))
            .add(layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(lbOdometroAnt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 326, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(tiHodometro))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(tiHodometro1)
                            .add(tfHodometro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 326, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createSequentialGroup()
                        .add(tfVeiculo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 326, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbVeiculo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 452, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(tiMotorista)
                    .add(layout.createSequentialGroup()
                        .add(tfMotorista, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 326, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbMotorista, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 452, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(tfOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 326, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lbOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 452, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(tiOperador)
                    .add(tiOperador1)
                    .add(senhaInformada, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 326, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(tiVeiculo))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(tiBico)
                            .add(layout.createSequentialGroup()
                                .add(tfBico, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 326, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lbBico, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 452, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(btAbastecer, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lbHora, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(tiBico)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(6, 6, 6)
                                .add(lbBico))
                            .add(tfBico, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tiVeiculo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(lbVeiculo))
                    .add(tfVeiculo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tiHodometro)
                    .add(tiHodometro1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(lbOdometroAnt))
                    .add(tfHodometro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tiMotorista, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(lbMotorista))
                    .add(tfMotorista, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(tiOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(lbOperador))
                    .add(tfOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tiOperador1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(senhaInformada, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(btAbastecer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tfOperador.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void tfBicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfBicoFocusLost
//        if (tfBico.getText().trim().length() == 1) {
//            tfBico.setText("0" + tfBico.getText().trim());
//        }
        if (!tfBico.getText().trim().equals("")) {
            //System.out.println("Focus Lost --> " + tfBico.getText().trim());
            Bico b = BicosDB.buscaIdentBico(tfBico.getText().trim());
            if (b != null) {
                if (b.getNomeBico() != null) {
                    lbBico.setText(b.getNomeBico());
                    verificarTipoCombustivel();
                } else {
                    lbBico.setText("Bico não encontrado");
                }
            } else {
                lbBico.setText("Bico não encontrado");
            }
        }
    }//GEN-LAST:event_tfBicoFocusLost

    private void tfVeiculoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfVeiculoFocusLost

        if (!tfVeiculo.getText().trim().equals("")) {
            Veiculo v = VeiculosDB.buscaIdentVeiculo(tfVeiculo.getText().trim());
            if (v != null) {
                lbVeiculo.setText(v.getNomeVeiculo());
                // O = Horímetro, H - Hodômetro
                if (v.getTipo().trim().equals("H")) {
                    tiHodometro.setText("Horímetro Anterior");
                    tiHodometro1.setText("Horímetro Atual");
                } else {
                    tiHodometro.setText("Odômetro Anterior");
                    tiHodometro1.setText("Odômetro Atual");
                }
                lbOdometroAnt.setText(VeiculosDB.buscaHodometroHorimetroAnterior(v, new Timestamp(new Date().getTime())).toString());
            } else {
                lbVeiculo.setText("Veículo não encontrado");
                tiHodometro.setText("Odômetro Anterior");
                tiHodometro1.setText("Odômetro Atual");
                lbOdometroAnt.setText("--------------------");
            }
            verificarTipoCombustivel();
        }
    }//GEN-LAST:event_tfVeiculoFocusLost

    public void limpaTela() {
        tfBico.setText("");
        lbBico.setText("");
        tfVeiculo.setText("");
        lbVeiculo.setText("");
        tiHodometro.setText("Odômetro Anterior");
        tiHodometro1.setText("Odômetro Atual");
        tfHodometro.setText("");
        tfOperador.setText("");
        lbOperador.setText("");
        lbOdometroAnt.setText("--------------------");
        lbMsg.setText("");
        senhaInformada.setText("");
        tfMotorista.setText("");
        lbMotorista.setText("");
        //carrega bico padrao

        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);
            if (!"".equals(properties.getProperty("sis_bicopadrao").trim())) {
                tfBico.setText(properties.getProperty("sis_bicopadrao").trim());
                tfBicoFocusLost(null);
                tfVeiculo.requestFocus();
            } else {
                tfBico.requestFocus();
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }

    }

    private void tfOperadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfOperadorFocusLost

        if (!tfOperador.getText().trim().equals("")) {
            Operador o = OperadoresDB.buscaIdentOperador(tfOperador.getText().trim());
            if (o != null) {
                if (o.getNomeOperador() != null) {
                    lbOperador.setText(o.getNomeOperador());
                    //senhaAtual.setText(o.getSenha());
                } else {
                    lbOperador.setText("Operador não encontrado");
                }
            }
        }
    }//GEN-LAST:event_tfOperadorFocusLost

    private void tfBicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBicoKeyPressed
        if ((evt.getKeyCode() == 40) || (evt.getKeyCode() == 10)) {
            tfVeiculo.requestFocus();
        }
    }//GEN-LAST:event_tfBicoKeyPressed

    private void tfVeiculoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfVeiculoKeyPressed
        if ((evt.getKeyCode() == 38)) {//cima
            tfBico.requestFocus();
        }
        if ((evt.getKeyCode() == 40) || (evt.getKeyCode() == 10)) {
            tfHodometro.requestFocus();
        }

    }//GEN-LAST:event_tfVeiculoKeyPressed

    private void tfHodometroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHodometroKeyPressed
        if ((evt.getKeyCode() == 38)) {//cima
            tfVeiculo.requestFocus();
        }
        if ((evt.getKeyCode() == 40) || (evt.getKeyCode() == 10)) {
            tfMotorista.requestFocus();
        }

    }//GEN-LAST:event_tfHodometroKeyPressed

    private void tfOperadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfOperadorKeyPressed
        if ((evt.getKeyCode() == 38)) {//cima
            tfMotorista.requestFocus();
        }
        if ((evt.getKeyCode() == 40) || (evt.getKeyCode() == 10)) {
            senhaInformada.requestFocus();
        }

    }//GEN-LAST:event_tfOperadorKeyPressed

    private void btAbastecerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btAbastecerKeyPressed
        if ((evt.getKeyCode() == 38)) {//cima
            senhaInformada.requestFocus();
        }

    }//GEN-LAST:event_btAbastecerKeyPressed

    private void btAbastecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAbastecerActionPerformed
        try {
            Date d = new Date();
            //data da licença
            Licenca lic = LicencaDB.buscaLicenca();
            Decoder de = Base64.getDecoder();
            String s1 = new String(de.decode(lic.getLicenca()));
            String s2 = new String(de.decode(lic.getUltimoAcesso()));
            Long dataLic = Long.parseLong(s1);
            Long dataUltimoAcesso = Long.parseLong(s2);

            //verifica se a data atual é maior que a licenca
            if (d.getTime() > dataLic) {
                lbMsg.setText("A licença de uso do sistema está vencida.");
                btAbastecer.setEnabled(false);
                return;
                //olha se alterou data do sistema
            } else if (d.getTime() < dataUltimoAcesso) {
                lbMsg.setText("Falha de violação de acesso.");
                btAbastecer.setEnabled(false);
                return;
            } else {
                //olha parametro para exibir os dias restantes
                //return;
            }

            if ((tfBico.getText().trim().equals("")) || (lbBico.getText().equals("Bico não encontrado"))) {
                return;
            }
            if ((tfVeiculo.getText().trim().equals("")) || (lbVeiculo.getText().equals("Veículo não encontrado"))) {
                return;
            }
            if (controlaMotorista != null) {
                if (controlaMotorista.toUpperCase().equals("S")) {
                    if ((tfMotorista.getText().trim().equals("")) || (lbMotorista.getText().equals("Motorista não encontrado"))) {
                        return;
                    }
                }
            }
            if (tfHodometro.getText().trim().equals("")) {
                return;
            }
            if ((tfOperador.getText().trim().equals("")) || (lbOperador.getText().equals("Operador não encontrado"))) {
                return;
            }

            Bico b = null;
            b = BicosDB.buscaIdentBico(tfBico.getText().trim());

//            if (abastecAndamento(b.getIdCBC())) {
//                //System.out.println(b.getIdentBico() + "Em andamento" + b.getIdCBC());
//                lbMsg.setText("Aguarde. Bico possui um abastecimento em andamento");
//                return;
//            }
            Veiculo v = VeiculosDB.buscaIdentVeiculo(tfVeiculo.getText().trim());
            Motorista m;
            if (controlaMotorista != null) {
                if (controlaMotorista.toUpperCase().equals("S")) {
                    m = MotoristasDB.buscaIdentMotorista(tfMotorista.getText().trim());
                } else {
                    m = null;
                }
            } else {
                m = null;
            }
            Operador o = OperadoresDB.buscaIdentOperador(tfOperador.getText().trim());
            Integer diaSemana = new GregorianCalendar().get(GregorianCalendar.DAY_OF_WEEK);

            if (b == null) {
                //System.out.println(b.getIdentBico() + "Em andamento" + b.getIdCBC());
                lbMsg.setText("Bico não cadastrado");
                return;
            }

            if (v == null) {
                //System.out.println(b.getIdentBico() + "Em andamento" + b.getIdCBC());
                lbMsg.setText("Veículo não cadastrado");
                return;
            }

            if (o == null) {
                //System.out.println(b.getIdentBico() + "Em andamento" + b.getIdCBC());
                lbMsg.setText("Operador não cadastrado");
                return;
            }

            if ((v.getSegunda().equals("N") && (diaSemana == 2))) {
                lbMsg.setText("Veículo não autorizado para abastecimento neste dia.");
                tfVeiculo.requestFocus();
                return;
            }
            if ((v.getTerca().equals("N") && (diaSemana == 3))) {
                lbMsg.setText("Veículo não autorizado para abastecimento neste dia.");
                tfVeiculo.requestFocus();
                return;
            }
            if ((v.getQuarta().equals("N") && (diaSemana == 4))) {
                lbMsg.setText("Veículo não autorizado para abastecimento neste dia.");
                tfVeiculo.requestFocus();
                return;
            }

            if ((v.getQuinta().equals("N") && (diaSemana == 5))) {
                lbMsg.setText("Veículo não autorizado para abastecimento neste dia.");
                tfVeiculo.requestFocus();
                return;
            }

            if ((v.getSexta().equals("N") && (diaSemana == 6))) {
                lbMsg.setText("Veículo não autorizado para abastecimento neste dia.");
                tfVeiculo.requestFocus();
                return;
            }

            if ((v.getSabado().equals("N") && (diaSemana == 7))) {
                lbMsg.setText("Veículo não autorizado para abastecimento neste dia.");
                tfVeiculo.requestFocus();
                return;
            }

            if ((v.getDomingo().equals("N") && (diaSemana == 1))) {
                lbMsg.setText("Veículo não autorizado para abastecimento neste dia.");
                tfVeiculo.requestFocus();
                return;
            }

            if (!v.getFrota().getEmpresa().getSeqEmpresa().equals(o.getEmpresa().getSeqEmpresa())) {
                lbMsg.setText("Empresa do operador incompatível com a frota do veículo.");
                tfOperador.requestFocus();
                return;
            }
            if (o.getSituacao().equals("I")) {
                lbMsg.setText("Operador inativo.");
                tfOperador.requestFocus();
                return;
            }

            if (b.getSituacao().equals("I")) {
                lbMsg.setText("Bico inativo.");
                tfBico.requestFocus();
                return;
            }

            if (v.getSituacao().equals("I")) {
                lbMsg.setText("Veículo inativo.");
                tfVeiculo.requestFocus();
                return;
            }
            if (v.getFrota().getSituacao().equals("I")) {
                lbMsg.setText("Frota inativa.");
                return;
            }
            if (v.getFrota().getEmpresa().getSituacao().equals("I")) {
                lbMsg.setText("Empresa inativa.");
                return;
            }
            if (controlaMotorista != null) {
                if (controlaMotorista.toUpperCase().equals("S")) {
                    if (m.getSituacao().equals("I")) {
                        lbMsg.setText("Motorista inativo.");
                        tfMotorista.requestFocus();
                        return;
                    }
                }
            }

            if (!o.getSenha().toUpperCase().equals(senhaInformada.getText().toUpperCase().trim())) {
                lbMsg.setText("Senha incorreta.");
                senhaInformada.requestFocus();
                return;
            }

            //verifica se o combustivel do veiculo é igual a bomba
            if ((v.getCombustivel() != null) && (b.getCombustivel() != null)) {
                if (!v.getCombustivel().getSeqCombustivel().equals(b.getCombustivel().getSeqCombustivel())) {
                    lbMsg.setText("Combustível diferente ao do veículo");
                    return;
                } else {
                    lbMsg.setText("");
                    //return;
                }
            } else {
                lbMsg.setText("");
            }

            if (Long.parseLong(tfHodometro.getText().trim()) <= VeiculosDB.buscaHodometroHorimetroAnterior(v, new Timestamp(new Date().getTime()))) {
                lbMsg.setText("Odômetro/Horímetro deve ser maior que o do último abastecimento");
                return;
            }
            DadosTelaBico dadosTelaBico = new DadosTelaBico(v, b, o, Long.parseLong(tfHodometro.getText().trim()), m);

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
                    lbMsg.setText("Limite atingido para frota ou veículo no bico " + b.getNomeBico());
                    return;
                }

                //System.out.println(sValor);
                cbc.determinarValorAbastecimento(b.getIdCBC(), TratamentoValores.preencheCom(sValor, "0", 6, 1, true));
                Thread.sleep(1000);
            }

            if (b.getIdCBC().equals("04")) {
                GlobalParameter.setDadosTelaBico04(dadosTelaBico);
            } else if (b.getIdCBC().equals("44")) {
                GlobalParameter.setDadosTelaBico44(dadosTelaBico);
            } else if (b.getIdCBC().equals("05")) {
                GlobalParameter.setDadosTelaBico05(dadosTelaBico);
            } else if (b.getIdCBC().equals("45")) {
                GlobalParameter.setDadosTelaBico45(dadosTelaBico);
            } else if (b.getIdCBC().equals("08")) {
                GlobalParameter.setDadosTelaBico08(dadosTelaBico);
            } else if (b.getIdCBC().equals("48")) {
                GlobalParameter.setDadosTelaBico48(dadosTelaBico);
            } else if (b.getIdCBC().equals("09")) {
                GlobalParameter.setDadosTelaBico09(dadosTelaBico);
            } else if (b.getIdCBC().equals("49")) {
                GlobalParameter.setDadosTelaBico49(dadosTelaBico);
            } else if (b.getIdCBC().equals("0C")) {
                GlobalParameter.setDadosTelaBico0C(dadosTelaBico);
            } else if (b.getIdCBC().equals("4C")) {
                GlobalParameter.setDadosTelaBico4C(dadosTelaBico);
            } else if (b.getIdCBC().equals("0D")) {
                GlobalParameter.setDadosTelaBico0D(dadosTelaBico);
            } else if (b.getIdCBC().equals("4D")) {
                GlobalParameter.setDadosTelaBico4D(dadosTelaBico);
            } else if (b.getIdCBC().equals("10")) {
                GlobalParameter.setDadosTelaBico10(dadosTelaBico);
            } else if (b.getIdCBC().equals("50")) {
                GlobalParameter.setDadosTelaBico50(dadosTelaBico);
            } else if (b.getIdCBC().equals("11")) {
                GlobalParameter.setDadosTelaBico11(dadosTelaBico);
            } else if (b.getIdCBC().equals("51")) {
                GlobalParameter.setDadosTelaBico51(dadosTelaBico);
            }

            //System.out.println("Dados Tela" + dadosTelaBico.getBico().getIdCBC());
            cbc.liberaUmAbastecimento(b.getIdCBC());
            HistoricoDB.insertHistoricoDadosTela(b.getSeqBico(), v.getSeqVeiculo(), o.getSeqOperador(), dadosTelaBico.getHodometro(), m != null ? m.getSeqMotorista() : null);
            limpaTela();

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }//GEN-LAST:event_btAbastecerActionPerformed

    private void senhaInformadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaInformadaKeyPressed
//        if (evt.getKeyCode() == 10) {
//            btAbastecerActionPerformed(null);
//        }

        if ((evt.getKeyCode() == 40) || (evt.getKeyCode() == 10)) {
            btAbastecer.requestFocus();
            btAbastecerActionPerformed(null);
        }

        if ((evt.getKeyCode() == 38)) {//cima
            tfOperador.requestFocus();
        }

//        if (evt.getKeyCode() == 106) {//cima
//            senhaInformada.setText(senhaInformada.getText().trim().replace("*", ""));
//            tfOperador.requestFocus();
//        }
    }//GEN-LAST:event_senhaInformadaKeyPressed

    private void tfVeiculoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfVeiculoKeyReleased
        if (evt.getKeyCode() == 106) {//baixo
            tfVeiculo.setText(tfVeiculo.getText().trim().replace("*", ""));
            tfBico.requestFocus();
        }
    }//GEN-LAST:event_tfVeiculoKeyReleased

    private void tfHodometroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHodometroKeyReleased
        if (evt.getKeyCode() == 106) {//baixo
            tfHodometro.setText(tfHodometro.getText().trim().replace("*", ""));
            tfVeiculo.requestFocus();
        }
    }//GEN-LAST:event_tfHodometroKeyReleased

    private void tfOperadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfOperadorKeyReleased
        if (evt.getKeyCode() == 106) {//baixo
            tfOperador.setText(tfOperador.getText().trim().replace("*", ""));
            tfHodometro.requestFocus();
        }
    }//GEN-LAST:event_tfOperadorKeyReleased

    private void senhaInformadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaInformadaKeyReleased
        if (evt.getKeyCode() == 106) {//baixo
            senhaInformada.setText(senhaInformada.getText().trim().replace("*", ""));
            tfOperador.requestFocus();
        }
    }//GEN-LAST:event_senhaInformadaKeyReleased

    private void btAbastecerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btAbastecerKeyReleased
        if (evt.getKeyCode() == 106) {//baixo
            senhaInformada.requestFocus();
        }
    }//GEN-LAST:event_btAbastecerKeyReleased

    private void tfBicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBicoKeyReleased
        if (evt.getKeyCode() == 106) {//baixo
            tfBico.setText(tfBico.getText().trim().replace("*", ""));
            tfBico.requestFocus();
        }
    }//GEN-LAST:event_tfBicoKeyReleased

    private void tfBicoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tfBicoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tfBicoPropertyChange

    private void tfMotoristaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfMotoristaFocusLost
        if (controlaMotorista != null) {
            if (controlaMotorista.toUpperCase().equals("S")) {
                if (!tfMotorista.getText().trim().equals("")) {
                    //System.out.println("Focus Lost --> " + tfBico.getText().trim());
                    if (tfMotorista.getText().trim().length() <= 8) {
                        tfMotorista.setText(TratamentoValores.preencheCom(tfMotorista.getText().trim(), "0", 8, 1, true));
                    }

                    Motorista m = MotoristasDB.buscaIdentMotorista(tfMotorista.getText().trim());
                    if (m != null) {
                        if (m.getNomeMotorista() != null) {
                            lbMotorista.setText(m.getNomeMotorista());
                            //verificarTipoCombustivel();
                        } else {
                            lbMotorista.setText("Motorista não encontrado");
                        }
                    } else {
                        lbMotorista.setText("Motorista não encontrado");
                    }
                }
            }
        }
    }//GEN-LAST:event_tfMotoristaFocusLost

    private void tfMotoristaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfMotoristaKeyPressed
        if ((evt.getKeyCode() == 38)) {//cima
            tfHodometro.requestFocus();
        }
        if ((evt.getKeyCode() == 40) || (evt.getKeyCode() == 10)) {
            tfOperador.requestFocus();
        }
    }//GEN-LAST:event_tfMotoristaKeyPressed

    private void tfMotoristaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfMotoristaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMotoristaKeyReleased

    public void verificarTipoCombustivel() {
        if (tfBico.getText().trim().equals("")) {
            return;
        }

        if (tfVeiculo.getText().trim().equals("")) {
            return;
        }
        Veiculo v = VeiculosDB.buscaIdentVeiculo(tfVeiculo.getText().trim());
        Bico b = BicosDB.buscaIdentBico(tfBico.getText().trim());

        //verifica se o combustivel do veiculo é igual a bomba
        if (v != null) {
            if (b != null) {
                if ((v.getCombustivel() != null) && (b.getCombustivel() != null)) {
                    if (!v.getCombustivel().getSeqCombustivel().equals(b.getCombustivel().getSeqCombustivel())) {
                        lbMsg.setText("Combustível diferente ao do veículo");
                    } else {
                        lbMsg.setText("");
                    }
                } else {
                    lbMsg.setText("");
                }
            }
        } else {
            lbMsg.setText("");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            AbastecimentoGUI dialog = new AbastecimentoGUI(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    //GlobalParameter.getInstance().getCbc().desconetarDispositivo();
                    System.exit(0);
                    //dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            });
            dialog.setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAbastecer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbBico;
    private javax.swing.JLabel lbHora;
    private javax.swing.JLabel lbMotorista;
    private javax.swing.JLabel lbMsg;
    private javax.swing.JLabel lbOdometroAnt;
    private javax.swing.JLabel lbOperador;
    private javax.swing.JLabel lbVeiculo;
    private javax.swing.JPasswordField senhaInformada;
    private javax.swing.JTextField tfBico;
    private javax.swing.JTextField tfHodometro;
    private javax.swing.JTextField tfMotorista;
    private javax.swing.JTextField tfOperador;
    private javax.swing.JTextField tfVeiculo;
    private javax.swing.JLabel tiBico;
    private javax.swing.JLabel tiHodometro;
    private javax.swing.JLabel tiHodometro1;
    private javax.swing.JLabel tiMotorista;
    private javax.swing.JLabel tiOperador;
    private javax.swing.JLabel tiOperador1;
    private javax.swing.JLabel tiVeiculo;
    // End of variables declaration//GEN-END:variables

    private boolean abastecAndamento(String idCBC) {
        Boolean retorno = false;
        if (idCBC.equals("04") && (GlobalParameter.getDadosTelaBico04() != null)) {
            retorno = true;
        } else if (idCBC.equals("44") && (GlobalParameter.getDadosTelaBico44() != null)) {
            retorno = true;
        } else if (idCBC.equals("05") && (GlobalParameter.getDadosTelaBico05() != null)) {
            retorno = true;
        } else if (idCBC.equals("45") && (GlobalParameter.getDadosTelaBico45() != null)) {
            retorno = true;
        } else if (idCBC.equals("08") && (GlobalParameter.getDadosTelaBico08() != null)) {
            retorno = true;
        } else if (idCBC.equals("48") && (GlobalParameter.getDadosTelaBico48() != null)) {
            retorno = true;
        } else if (idCBC.equals("09") && (GlobalParameter.getDadosTelaBico09() != null)) {
            retorno = true;
        } else if (idCBC.equals("49") && (GlobalParameter.getDadosTelaBico49() != null)) {
            retorno = true;
        } else if (idCBC.equals("0C") && (GlobalParameter.getDadosTelaBico0C() != null)) {
            retorno = true;
        } else if (idCBC.equals("4C") && (GlobalParameter.getDadosTelaBico4C() != null)) {
            retorno = true;
        } else if (idCBC.equals("0D") && (GlobalParameter.getDadosTelaBico0D() != null)) {
            retorno = true;
        } else if (idCBC.equals("4D") && (GlobalParameter.getDadosTelaBico4D() != null)) {
            retorno = true;
        } else if (idCBC.equals("10") && (GlobalParameter.getDadosTelaBico10() != null)) {
            retorno = true;
        } else if (idCBC.equals("50") && (GlobalParameter.getDadosTelaBico50() != null)) {
            retorno = true;
        } else if (idCBC.equals("11") && (GlobalParameter.getDadosTelaBico11() != null)) {
            retorno = true;
        } else if (idCBC.equals("51") && (GlobalParameter.getDadosTelaBico51() != null)) {
            retorno = true;
        }
        return retorno;
    }
}
