package view.consultas;

import bd.BicosDB;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import javax.swing.JDialog;
import model.Bico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import render.BicoListRender;
import util.CapturaTamanhoTela;
import ctr.GlobalParameter;
import javax.swing.ImageIcon;
import util.GeraLog;
import util.JConfirmMessage;

public class ConsultaEncerrantes extends javax.swing.JDialog {

    public ConsultaEncerrantes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
        setIconImage(icon.getImage());

        GregorianCalendar calendarioInicial = new GregorianCalendar();
        GregorianCalendar calendarioFinal = new GregorianCalendar();
        int dia;
        int mes = calendarioInicial.get(GregorianCalendar.MONTH) + 1;
        int ano = calendarioInicial.get(GregorianCalendar.YEAR);

        //Verifica Meses com 30 Dias
        if ((mes == 4) || (mes == 6) || (mes == 9) || (mes == 11)) {
            dia = 30;
        } else {
            dia = 31;
        }
        //Verifica Ano Bixesto
        if (mes == 2) {
            if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
                dia = 29;
            } else {
                dia = 28;
            }
        }
        // Senão = meses com 31 dias

        calendarioInicial.set(ano, mes - 1, 1);
        calendarioFinal.set(ano, mes - 1, dia);

        jdcDataInicial.setCalendar(calendarioInicial);
        jdcDataFinal.setCalendar(calendarioFinal);

        jComboBox1.setRenderer(new BicoListRender());
        carregaComboBicos();

        if (jComboBox1.getItemCount() == 2) {
            jComboBox1.setSelectedIndex(1);
        } else {
            jComboBox1.setSelectedIndex(0);
        }
    }

    private void carregaComboBicos() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Selecione");
        Iterator iterator = BicosDB.buscaBicosInternos(false).iterator();
        while (iterator.hasNext()) {
            Bico b = (Bico) iterator.next();
            jComboBox1.addItem(b);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplTitulo = new javax.swing.JPanel();
        jlbTituloJanela = new javax.swing.JLabel();
        jplOpcoes = new javax.swing.JPanel();
        jplPeriodo = new javax.swing.JPanel();
        jdcDataInicial = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        jlbDataInicial = new javax.swing.JLabel();
        jdcDataFinal = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        jlbDataFinal = new javax.swing.JLabel();
        jbtFechar = new javax.swing.JButton();
        jplPeriodo1 = new javax.swing.JPanel();
        jlbDataInicial1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jbtVisualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jplTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloJanela.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloJanela.setText("Consulta de Encerrantes");

        javax.swing.GroupLayout jplTituloLayout = new javax.swing.GroupLayout(jplTitulo);
        jplTitulo.setLayout(jplTituloLayout);
        jplTituloLayout.setHorizontalGroup(
            jplTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTituloJanela)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jplTituloLayout.setVerticalGroup(
            jplTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTituloJanela)
                .addContainerGap())
        );

        getContentPane().add(jplTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jplPeriodo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlbDataInicial.setText("Data Inicial");

        jlbDataFinal.setText("Data Final");

        javax.swing.GroupLayout jplPeriodoLayout = new javax.swing.GroupLayout(jplPeriodo);
        jplPeriodo.setLayout(jplPeriodoLayout);
        jplPeriodoLayout.setHorizontalGroup(
            jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplPeriodoLayout.createSequentialGroup()
                        .addComponent(jlbDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jplPeriodoLayout.createSequentialGroup()
                        .addComponent(jlbDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jplPeriodoLayout.setVerticalGroup(
            jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbDataInicial)
                    .addComponent(jdcDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDataFinal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbtFechar.setText("Fechar");
        jbtFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFecharActionPerformed(evt);
            }
        });

        jplPeriodo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlbDataInicial1.setText("Estação de Abastecimento");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jplPeriodo1Layout = new javax.swing.GroupLayout(jplPeriodo1);
        jplPeriodo1.setLayout(jplPeriodo1Layout);
        jplPeriodo1Layout.setHorizontalGroup(
            jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbDataInicial1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jplPeriodo1Layout.setVerticalGroup(
            jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlbDataInicial1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jbtVisualizar.setText("Visualizar");
        jbtVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplOpcoesLayout = new javax.swing.GroupLayout(jplOpcoes);
        jplOpcoes.setLayout(jplOpcoesLayout);
        jplOpcoesLayout.setHorizontalGroup(
            jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplOpcoesLayout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addGroup(jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jplOpcoesLayout.createSequentialGroup()
                        .addComponent(jbtVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jplPeriodo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jplOpcoesLayout.createSequentialGroup()
                    .addComponent(jplPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jplOpcoesLayout.setVerticalGroup(
            jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplOpcoesLayout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(jplPeriodo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtVisualizar)
                    .addComponent(jbtFechar))
                .addContainerGap())
            .addGroup(jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jplOpcoesLayout.createSequentialGroup()
                    .addComponent(jplPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 116, Short.MAX_VALUE)))
        );

        getContentPane().add(jplOpcoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 54, 280, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        dispose();
}//GEN-LAST:event_jbtFecharActionPerformed

    private void jbtVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVisualizarActionPerformed
        if (jdcDataInicial.getDate().after(jdcDataFinal.getDate())) {
            JConfirmMessage.showMessageDialog("Data inicial maior que data final.", "Atenção");
            return;
        }

        if (jComboBox1.getSelectedIndex() <= 0) {
            JConfirmMessage.showMessageDialog("Nenhuma estação de abastecimento selecionada.", "Atenção");
            return;
        }

        try {
            GlobalParameter.getInstance();
            Connection conn = GlobalParameter.getConn();
            JDialog viewer = new JDialog(new javax.swing.JFrame(), "Visualização do Relatório", true);
            viewer.setSize(CapturaTamanhoTela.getWidthMonitor(), CapturaTamanhoTela.getHeightMonitor());
            viewer.setLocationRelativeTo(null);

            Map parameters = new HashMap();
            InputStream fileJasper;

            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);
            if (properties.getProperty("cbc_casas_decimais_volume").trim().equals("1")) {
                fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/conf_1.jasper");
            } else if (properties.getProperty("cbc_casas_decimais_volume").trim().equals("2")) {
                fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/conf_2.jasper");
            } else if (properties.getProperty("cbc_casas_decimais_volume").trim().equals("3")) {
                fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/conf_3.jasper");
            } else {
                fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/conf_2.jasper");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dtInicial = sdf.format(jdcDataInicial.getDate()) + " 00:00:00";
            String dtFinal = sdf.format(jdcDataFinal.getDate()) + " 23:59:59";

            if (GlobalParameter.getInstance().getOperador().getEmpresa() == null) {
                parameters.put("NOME_EMPRESA", "");
                parameters.put("ENDERECO_COMPLETO", "");
                parameters.put("OPERADOR", "");
            } else {
                parameters.put("NOME_EMPRESA", GlobalParameter.getInstance().getOperador().getEmpresa().getNomeEmpresa());
                parameters.put("ENDERECO_COMPLETO", GlobalParameter.getInstance().getOperador().getEmpresa().getEnderecoCompleto());
                parameters.put("OPERADOR", GlobalParameter.getInstance().getOperador().getNomeOperador());
            }

            parameters.put("DT_INICIAL", dtInicial);
            parameters.put("DT_FINAL", dtFinal);

            if (jComboBox1.getSelectedIndex() > 0) {
                parameters.put("BICO_INICIAL", ((Bico) jComboBox1.getSelectedItem()).getSeqBico());
                parameters.put("BICO_FINAL", ((Bico) jComboBox1.getSelectedItem()).getSeqBico());
            } else {
                parameters.put("BICO_INICIAL", 0);
                parameters.put("BICO_FINAL", 999999999);
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(fileJasper, parameters, conn);
            JasperViewer jrViewer = new JasperViewer(jasperPrint, true);
            viewer.getContentPane().add(jrViewer.getContentPane());
            viewer.setVisible(true);
        } catch (HeadlessException | IOException | JRException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
}//GEN-LAST:event_jbtVisualizarActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JButton jbtFechar;
    private javax.swing.JButton jbtVisualizar;
    private com.toedter.calendar.JDateChooser jdcDataFinal;
    private com.toedter.calendar.JDateChooser jdcDataInicial;
    private javax.swing.JLabel jlbDataFinal;
    private javax.swing.JLabel jlbDataInicial;
    private javax.swing.JLabel jlbDataInicial1;
    private javax.swing.JLabel jlbTituloJanela;
    private javax.swing.JPanel jplOpcoes;
    private javax.swing.JPanel jplPeriodo;
    private javax.swing.JPanel jplPeriodo1;
    private javax.swing.JPanel jplTitulo;
    // End of variables declaration//GEN-END:variables
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            ConsultaEncerrantes dialog = new ConsultaEncerrantes(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                    //dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            });
            dialog.setVisible(true);
        });
    }
}
