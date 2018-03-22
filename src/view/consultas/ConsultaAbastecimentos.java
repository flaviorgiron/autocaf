package view.consultas;

import bd.EmpresasDB;
import bd.FrotasDB;
import bd.VeiculosDB;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import javax.swing.JDialog;
import model.Empresa;
import model.Frota;
import model.Veiculo;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import render.EmpresaListRender;
import render.FrotaListRender;
import render.VeiculoListRender;
import util.CapturaTamanhoTela;
import ctr.GlobalParameter;
import javax.swing.ImageIcon;
import util.GeraLog;
import util.JConfirmMessage;

public class ConsultaAbastecimentos extends javax.swing.JDialog {

    private String controlaMotorista = "N";

    public ConsultaAbastecimentos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
            setIconImage(icon.getImage());

            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);
            if (properties.getProperty("sis_controla_motorista").trim() != null) {
                if (properties.getProperty("sis_controla_motorista").trim().toUpperCase().equals("S")) {
                    controlaMotorista = "S";
                } else {
                    controlaMotorista = "N";
                }
            } else {
                controlaMotorista = "N";
            }

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

            carregaComboEmpresas();
            carregaComboFrotas();
            carregaComboVeiculos();

            jComboBox1.setRenderer(new EmpresaListRender());
            jComboBox2.setRenderer(new FrotaListRender());
            jComboBox3.setRenderer(new VeiculoListRender());

            if (jComboBox1.getItemCount() == 2) {
                jComboBox1.setSelectedIndex(1);
            } else {
                jComboBox1.setSelectedIndex(0);
            }

            if (jComboBox2.getItemCount() == 2) {
                jComboBox2.setSelectedIndex(1);
            } else {
                jComboBox2.setSelectedIndex(0);
            }

            if (jComboBox3.getItemCount() == 2) {
                jComboBox3.setSelectedIndex(1);
            } else {
                jComboBox3.setSelectedIndex(0);
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
        }
    }

    private void carregaComboEmpresas() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Todas");
        Iterator iterator = EmpresasDB.buscaEmpresas(false).iterator();
        while (iterator.hasNext()) {
            Empresa e = (Empresa) iterator.next();
            jComboBox1.addItem(e);
        }
    }

    private void carregaComboFrotas() {
        Empresa e;
        Iterator iterator;
        if (jComboBox1.getSelectedIndex() > 0) {
            e = (Empresa) jComboBox1.getSelectedItem();
            iterator = FrotasDB.buscaFrotasEmpresa(true, e.getSeqEmpresa()).iterator();
        } else {
            iterator = FrotasDB.buscaFrotas(true).iterator();
        }
        jComboBox2.removeAllItems();
        jComboBox2.addItem("Todas");

        while (iterator.hasNext()) {
            Frota f = (Frota) iterator.next();
            jComboBox2.addItem(f);
        }
    }

    private void carregaComboVeiculos() {
        Frota f;
        Empresa e;
        Iterator iterator;
        if (jComboBox2.getSelectedIndex() > 0) {
            f = (Frota) jComboBox2.getSelectedItem();
            iterator = VeiculosDB.buscaVeiculosFrota(true, f.getSeqFrota()).iterator();
        } else if (jComboBox1.getSelectedIndex() > 0) {
            e = (Empresa) jComboBox1.getSelectedItem();
            iterator = VeiculosDB.buscaVeiculosEmpresa(true, e.getSeqEmpresa()).iterator();
        } else {
            iterator = VeiculosDB.buscaVeiculos(true).iterator();
        }
        jComboBox3.removeAllItems();
        jComboBox3.addItem("Todos");

        while (iterator.hasNext()) {
            Veiculo v = (Veiculo) iterator.next();
            jComboBox3.addItem(v);
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
        jplPeriodo1 = new javax.swing.JPanel();
        jlbDataInicial1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jlbDataInicial2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jlbDataInicial3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jbtFechar = new javax.swing.JButton();
        jbtVisualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jplTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloJanela.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloJanela.setText("Consulta de Abastecimentos");

        javax.swing.GroupLayout jplTituloLayout = new javax.swing.GroupLayout(jplTitulo);
        jplTitulo.setLayout(jplTituloLayout);
        jplTituloLayout.setHorizontalGroup(
            jplTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTituloJanela)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplTituloLayout.setVerticalGroup(
            jplTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTituloJanela)
                .addContainerGap())
        );

        jplPeriodo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlbDataInicial.setText("Data Inicial");

        jlbDataFinal.setText("Data Final");

        javax.swing.GroupLayout jplPeriodoLayout = new javax.swing.GroupLayout(jplPeriodo);
        jplPeriodo.setLayout(jplPeriodoLayout);
        jplPeriodoLayout.setHorizontalGroup(
            jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbDataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jlbDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jplPeriodoLayout.setVerticalGroup(
            jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoLayout.createSequentialGroup()
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplPeriodoLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jlbDataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplPeriodoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jdcDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jplPeriodo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlbDataInicial1.setText("Empresa");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jlbDataInicial2.setText("Frota");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jlbDataInicial3.setText("Veículo");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jplPeriodo1Layout = new javax.swing.GroupLayout(jplPeriodo1);
        jplPeriodo1.setLayout(jplPeriodo1Layout);
        jplPeriodo1Layout.setHorizontalGroup(
            jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addComponent(jlbDataInicial3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbDataInicial1)
                            .addComponent(jlbDataInicial2))
                        .addGap(18, 18, 18)
                        .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplPeriodo1Layout.setVerticalGroup(
            jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jbtFechar.setText("Fechar");
        jbtFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFecharActionPerformed(evt);
            }
        });

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
                .addGroup(jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplOpcoesLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jplPeriodo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jplOpcoesLayout.createSequentialGroup()
                    .addComponent(jplPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jplOpcoesLayout.setVerticalGroup(
            jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplOpcoesLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jplPeriodo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtVisualizar)
                    .addComponent(jbtFechar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jplOpcoesLayout.createSequentialGroup()
                    .addComponent(jplPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 144, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jplOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jplTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                if (controlaMotorista.equals("S")) {
                    fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/abastecimentos_motorista_1.jasper");
                } else {
                    fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/abastecimentos_1.jasper");
                }
            } else if (properties.getProperty("cbc_casas_decimais_volume").trim().equals("2")) {
                if (controlaMotorista.equals("S")) {
                    fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/abastecimentos_motorista_2.jasper");
                } else {
                    fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/abastecimentos_2.jasper");
                }
            } else if (properties.getProperty("cbc_casas_decimais_volume").trim().equals("3")) {
                if (controlaMotorista.equals("S")) {
                    fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/abastecimentos_motorista_3.jasper");
                } else {
                    fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/abastecimentos_3.jasper");
                }
            } else if (controlaMotorista.equals("S")) {
                fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/abastecimentos_motorista_2.jasper");
            } else {
                fileJasper = this.getClass().getClassLoader().getResourceAsStream("relatorios/abastecimentos_2.jasper");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dtInicial = sdf.format(jdcDataInicial.getDate()) + " 00:00:00";
            String dtFinal = sdf.format(jdcDataFinal.getDate()) + " 23:59:59";

            parameters.put("DATA_INICIAL", dtInicial);
            parameters.put("DATA_FINAL", dtFinal);

            if (jComboBox1.getSelectedIndex() > 0) {
                parameters.put("EMPRESA_INICIAL", ((Empresa) jComboBox1.getSelectedItem()).getSeqEmpresa());
                parameters.put("EMPRESA_FINAL", ((Empresa) jComboBox1.getSelectedItem()).getSeqEmpresa());
            } else {
                parameters.put("EMPRESA_INICIAL", 0);
                parameters.put("EMPRESA_FINAL", 999999999);
            }
            if (jComboBox2.getSelectedIndex() > 0) {
                parameters.put("FROTA_INICIAL", ((Frota) jComboBox2.getSelectedItem()).getSeqFrota());
                parameters.put("FROTA_FINAL", ((Frota) jComboBox2.getSelectedItem()).getSeqFrota());
            } else {
                parameters.put("FROTA_INICIAL", 0);
                parameters.put("FROTA_FINAL", 999999999);
            }
            if (jComboBox3.getSelectedIndex() > 0) {
                parameters.put("VEICULO_INICIAL", ((Veiculo) jComboBox3.getSelectedItem()).getSeqVeiculo());
                parameters.put("VEICULO_FINAL", ((Veiculo) jComboBox3.getSelectedItem()).getSeqVeiculo());
            } else {
                parameters.put("VEICULO_INICIAL", 0);
                parameters.put("VEICULO_FINAL", 999999999);
            }

            if (GlobalParameter.getInstance().getOperador().getEmpresa() == null) {
                parameters.put("NOME_EMPRESA", "");
                parameters.put("ENDERECO_COMPLETO", "");
                parameters.put("OPERADOR", "");
            } else {
                parameters.put("NOME_EMPRESA", GlobalParameter.getInstance().getOperador().getEmpresa().getNomeEmpresa());
                parameters.put("ENDERECO_COMPLETO", GlobalParameter.getInstance().getOperador().getEmpresa().getEnderecoCompleto());
                parameters.put("OPERADOR", GlobalParameter.getInstance().getOperador().getNomeOperador());
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(fileJasper, parameters, conn);
            JasperViewer jrViewer = new JasperViewer(jasperPrint, true);
            viewer.getContentPane().add(jrViewer.getContentPane());
            viewer.setVisible(true);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
}//GEN-LAST:event_jbtVisualizarActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        carregaComboFrotas();
        carregaComboVeiculos();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        carregaComboVeiculos();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JButton jbtFechar;
    private javax.swing.JButton jbtVisualizar;
    private com.toedter.calendar.JDateChooser jdcDataFinal;
    private com.toedter.calendar.JDateChooser jdcDataInicial;
    private javax.swing.JLabel jlbDataFinal;
    private javax.swing.JLabel jlbDataInicial;
    private javax.swing.JLabel jlbDataInicial1;
    private javax.swing.JLabel jlbDataInicial2;
    private javax.swing.JLabel jlbDataInicial3;
    private javax.swing.JLabel jlbTituloJanela;
    private javax.swing.JPanel jplOpcoes;
    private javax.swing.JPanel jplPeriodo;
    private javax.swing.JPanel jplPeriodo1;
    private javax.swing.JPanel jplTitulo;
    // End of variables declaration//GEN-END:variables
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            ConsultaAbastecimentos dialog = new ConsultaAbastecimentos(new javax.swing.JFrame(), true);
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
