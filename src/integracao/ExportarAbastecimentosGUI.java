package integracao;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import bd.*;
import javax.swing.ImageIcon;
import model.*;
import render.*;
import util.*;
import view.cadastros.SelecionaDiretorioGui;

public class ExportarAbastecimentosGUI extends javax.swing.JDialog {

    // private static Idioma i;
    public ExportarAbastecimentosGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //i = GlobalParameter.getIdioma();
        initComponents();
        try {
            jLabel3.setVisible(false);
            jButton2.setVisible(false);

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
            carregaComboTipos();

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

            ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
            setIconImage(icon.getImage());
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jplTitulo = new javax.swing.JPanel();
        jlbTituloJanela = new javax.swing.JLabel();
        jbtExportar = new javax.swing.JButton();
        jbtFechar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jlbCaminho = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jplPeriodo = new javax.swing.JPanel();
        jdcDataInicial = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        jlbDataInicial = new javax.swing.JLabel();
        jdcDataFinal = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        jlbDataFinal = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jplPeriodo1 = new javax.swing.JPanel();
        jlbDataInicial1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jlbDataInicial2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jlbDataInicial3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jlbDataInicial4 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Exportar Abastecimentos");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Exportação para abastecimentos identificados (estação/operador/veículo)");

        jplTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloJanela.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloJanela.setText("Exportar Abastecimentos");

        javax.swing.GroupLayout jplTituloLayout = new javax.swing.GroupLayout(jplTitulo);
        jplTitulo.setLayout(jplTituloLayout);
        jplTituloLayout.setHorizontalGroup(
            jplTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTituloJanela)
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jplTituloLayout.setVerticalGroup(
            jplTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTituloJanela)
                .addContainerGap())
        );

        jbtExportar.setText("Exportar");
        jbtExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExportarActionPerformed(evt);
            }
        });

        jbtFechar.setText("Fechar");
        jbtFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFecharActionPerformed(evt);
            }
        });

        jButton1.setText("Selecionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jlbCaminho.setText("C:\\AUTOCAF\\EXPORT");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Caminho do Arquivo:");

        jplPeriodo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlbDataInicial.setText("Data Inicial");

        jlbDataFinal.setText("Data Final");

        jCheckBox1.setText("Separar por ponto e virgula (;)");

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
                    .addGroup(jplPeriodoLayout.createSequentialGroup()
                        .addComponent(jdcDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1))
                    .addComponent(jdcDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jdcDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        jlbDataInicial4.setText("Tipo");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jplPeriodo1Layout = new javax.swing.GroupLayout(jplPeriodo1);
        jplPeriodo1.setLayout(jplPeriodo1Layout);
        jplPeriodo1Layout.setHorizontalGroup(
            jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplPeriodo1Layout.createSequentialGroup()
                        .addComponent(jlbDataInicial3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplPeriodo1Layout.createSequentialGroup()
                        .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbDataInicial1)
                            .addComponent(jlbDataInicial2))
                        .addGap(18, 18, 18)
                        .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addComponent(jlbDataInicial4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("MANUAL DE ORIENTAÇÃO DO LEIAUTE DE EXPORTAÇÃO :");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/download.png"))); // NOI18N
        jButton2.setText("Download");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(38, 38, 38)
                        .addComponent(jbtExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jplTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jlbCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1))
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jplPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jplPeriodo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jlbCaminho))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplPeriodo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtExportar)
                    .addComponent(jbtFechar)
                    .addComponent(jButton2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean gravarTXT(String caminhoCompleto, String texto) {
        try {
            FileWriter x = new FileWriter(caminhoCompleto, false);
            texto += "\n\r"; // criando nova linha e recuo no arquivo              
            x.write(texto); // armazena o texto no objeto x, que aponta para o arquivo             
            x.close(); // cria o arquivo   
            return true;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            return false;
        }
    }

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        dispose();
}//GEN-LAST:event_jbtFecharActionPerformed

    private void jbtExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExportarActionPerformed
        try {
            File f = new File(jlbCaminho.getText());
            if (!f.exists()) {
                f.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");

            String conteudo = preparaConteudo(jCheckBox1.isSelected());
            String nomeArquivo = sdf.format(new Date());
            if (gravarTXT(f.getAbsolutePath() + "\\" + nomeArquivo + ".txt", conteudo)) {
                JConfirmMessage.showMessageDialog("Exportação realizada com sucesso.", "");
                Runtime.getRuntime().exec("explorer " + f.getAbsolutePath());
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
}//GEN-LAST:event_jbtExportarActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        carregaComboFrotas();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        carregaComboVeiculos();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String caminhoAtual = jlbCaminho.getText();
            SelecionaDiretorioGui s = new SelecionaDiretorioGui(null, true, caminhoAtual);
            s.setLocationRelativeTo(null);
            s.setVisible(true);
            jlbCaminho.setText(s.getCaminho());
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void carregaComboEmpresas() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Todas");
        for (Empresa e : EmpresasDB.buscaEmpresas(false)) {
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

    private void carregaComboTipos() {
        jComboBox4.removeAllItems();
        jComboBox4.addItem("Odômetro/Horímetro");
        jComboBox4.addItem("Odômetro");
        jComboBox4.addItem("Horímetro");
        jComboBox4.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbtExportar;
    private javax.swing.JButton jbtFechar;
    private com.toedter.calendar.JDateChooser jdcDataFinal;
    private com.toedter.calendar.JDateChooser jdcDataInicial;
    private javax.swing.JLabel jlbCaminho;
    private javax.swing.JLabel jlbDataFinal;
    private javax.swing.JLabel jlbDataInicial;
    private javax.swing.JLabel jlbDataInicial1;
    private javax.swing.JLabel jlbDataInicial2;
    private javax.swing.JLabel jlbDataInicial3;
    private javax.swing.JLabel jlbDataInicial4;
    private javax.swing.JLabel jlbTituloJanela;
    private javax.swing.JPanel jplPeriodo;
    private javax.swing.JPanel jplPeriodo1;
    private javax.swing.JPanel jplTitulo;
    // End of variables declaration//GEN-END:variables
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            ExportarAbastecimentosGUI dialog = new ExportarAbastecimentosGUI(new javax.swing.JFrame(), true);
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

    private String preparaConteudo(boolean separaVirgula) {
        try {
            String conteudo = "";
            Date dtInicial = jdcDataInicial.getDate();
            Date dtFinal = jdcDataFinal.getDate();

            Integer seqEmpresaInicial = 0;
            Integer seqEmpresaFinal = 999999999;

            Integer seqFrotaInicial = 0;
            Integer seqFrotaFinal = 999999999;

            Integer seqVeiculoInicial = 0;
            Integer seqVeiculoFinal = 999999999;

            String tipo1 = "H";
            String tipo2 = "H";

            if (jComboBox1.getSelectedIndex() > 0) {
                seqEmpresaInicial = ((Empresa) jComboBox1.getSelectedItem()).getSeqEmpresa();
                seqEmpresaFinal = ((Empresa) jComboBox1.getSelectedItem()).getSeqEmpresa();
            }
            if (jComboBox2.getSelectedIndex() > 0) {
                seqFrotaInicial = ((Frota) jComboBox2.getSelectedItem()).getSeqFrota();
                seqEmpresaFinal = ((Frota) jComboBox2.getSelectedItem()).getSeqFrota();
            }
            if (jComboBox3.getSelectedIndex() > 0) {
                seqVeiculoInicial = ((Veiculo) jComboBox3.getSelectedItem()).getSeqVeiculo();
                seqVeiculoFinal = ((Veiculo) jComboBox3.getSelectedItem()).getSeqVeiculo();
            }
            if (jComboBox4.getSelectedIndex() == 0) { //todos
                tipo1 = "O";
                tipo2 = "H";
            } else if (jComboBox4.getSelectedIndex() == 1) {
                tipo1 = "O";
                tipo2 = "O";
            }

            Iterator iterator = AbastecimentosDB.buscaAbastExport(dtInicial, dtFinal, seqEmpresaInicial, seqEmpresaFinal, seqFrotaInicial, seqFrotaFinal, seqVeiculoInicial, seqVeiculoFinal, tipo1, tipo2).iterator();
            int cont = 0;
            while (iterator.hasNext()) {
                Abastecimento a = (Abastecimento) iterator.next();

                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                SimpleDateFormat stf = new SimpleDateFormat("mmss");
                SimpleDateFormat stf2 = new SimpleDateFormat("HHmm");
                String dataAbastec = sdf.format(a.getDataHora());
                String horaAbastec = stf2.format(a.getDataHora());
                String tempoAbastec = stf.format(a.getTempoAbastecimento());

                Long marcador = a.getVeiculo().getTipo().equals("O") ? a.getHodometro() : a.getHorimetro();
                Long marcadorAnterior = a.getVeiculo().getTipo().equals("O") ? a.getHodometroAnterior() : a.getHorimetroAnterior();
                int locVirgula = a.getVolume().toString().indexOf(".");
                int casasDecimais = (a.getVolume().toString().length() - 1) - locVirgula;

                String linha = "";
                if (!separaVirgula) {
                    linha
                            = //01	REGISTRO	Número do abastecimento CBC	N	04
                            TratamentoValores.preencheCom(a.getNumeroAbastecimento(), "0", 4, 1, true)
                            //02	IDENT_BICO	Código identificador do bico	C	02
                            + TratamentoValores.preencheCom(a.getBico().getIdentBico(), " ", 16, 1, true)
                            //03	IDENT_COMBUST	Código identificador do combustível	C	02
                            + TratamentoValores.preencheCom(a.getBico().getCombustivel().getIdentCombustivel(), " ", 2, 1, true)
                            //04	IDENT_TANQUE	Código identificador do Tanque de combustível	C	02
                            + TratamentoValores.preencheCom(" ", " ", 2, 1, true)
                            //05	TOT_PAGAR	Quantidade total a pagar (2 casas decimais)	N	06
                            + TratamentoValores.preencheCom(String.format("%.2f", a.getTotalPagar()).replace(",", ""), "0", 6, 1, true)
                            //06	VOLUME	Volume abastecido em litros	N	06
                            + TratamentoValores.preencheCom(a.getVolume(), "0", 6, 1, true).replace(".", "")
                            //07	PRECO_UNITARIO	Preço por litro (3 casas decimais)	N	04
                            + TratamentoValores.preencheCom(String.format("%.3f", a.getPrecoUnitario()).replace(",", ""), "0", 4, 2, true)
                            //08	TEMPO_ABASTEC	Tempo de duração do abastecimento (mmss)	N	04
                            + TratamentoValores.preencheCom(tempoAbastec, "", 4, 1, true)
                            //09	DATA	Data do abastecimento	N	08
                            + TratamentoValores.preencheCom(dataAbastec, "0", 8, 1, true)
                            //10	HORA	Hora do abastecimento	N	04
                            + TratamentoValores.preencheCom(horaAbastec, "0", 4, 1, true)
                            //11	ENCERRANTE INICIAL	Encerrante do bico (2 casas decimais)	N	10
                            + TratamentoValores.preencheCom(String.format("%.2f", a.getEncerranteAnterior()).replace(",", ""), "0", 10, 1, true)
                            //12	ENCERRANTE FINAL	Encerrante do bico (2 casas decimais)		10
                            + TratamentoValores.preencheCom(String.format("%.2f", a.getEncerranteLitros()).replace(",", ""), "0", 10, 1, true)
                            //13	IDENT_OPERADOR	Código identificador do operador	C	16
                            + TratamentoValores.preencheCom(a.getOperador().getIdentOperador(), " ", 16, 1, true)
                            //14	IDENT_VEICULO	Código identificador do veículo	C	16
                            + TratamentoValores.preencheCom(a.getVeiculo().getIdentVeiculo(), " ", 16, 1, true)
                            //15	MARCADOR ANTERIOR
                            + TratamentoValores.preencheCom(marcadorAnterior, " ", 7, 1, true)
                            //16	MARCADOR	Odômetro ou Horímetro na realização do abastecimento	N	06
                            + TratamentoValores.preencheCom(marcador, " ", 7, 1, true)
                            //17	TIPO	Tipo de controle do veículo O = Odômetro H = Horímetro	C	01
                            + TratamentoValores.preencheCom(a.getVeiculo().getTipo(), " ", 1, 1, true)
                            //18	IDENT_EMPRESA	Código identificador da empresa	C	2
                            + TratamentoValores.preencheCom(a.getVeiculo().getFrota().getEmpresa().getIdentEmpresa(), " ", 2, 1, true)
                            //19	IDENT_FROTA	Código identificador da frota	C	2
                            + TratamentoValores.preencheCom(a.getVeiculo().getFrota().getIdentFrota(), " ", 2, 1, true)
                            //20	CASAS_DEC	Quantidade de casas decimais do volume	N	01
                            + TratamentoValores.preencheCom(casasDecimais, "0", 1, 1, true)
                            //21	IDENT_MOTORISTA
                            + TratamentoValores.preencheCom(((a.getMotorista() == null) ? "" : a.getMotorista().getIdentMotorista()), " ", 16, 1, true)
                            //22        KM MEDIO
                            + TratamentoValores.preencheCom(String.valueOf(String.format("%.2f", a.getKmMedio()).replace(",", "")), "0", 6, 1, true)
                            //23        TEMPO MEDIO
                            + TratamentoValores.preencheCom(String.valueOf(String.format("%.2f", a.getTempoMedio()).replace(",", "")), "0", 6, 1, true)
                            //24        TIPO ABASTEC
                            + TratamentoValores.preencheCom(String.valueOf(a.getTipoAbastec()), " ", 1, 1, true)
                            //25        IDENTIFICADOR 1
                            + TratamentoValores.preencheCom(((a.getIdent1() == null) ? " " : String.valueOf(a.getIdent1())), " ", 16, 1, true)
                            //26        IDENTIFICADOR 2
                            + TratamentoValores.preencheCom(((a.getIdent2() == null) ? " " : String.valueOf(a.getIdent2())), " ", 16, 1, true)
                            //27        STRING CBC
                            + TratamentoValores.preencheCom(((a.getStringCBC() == null) ? " " : String.valueOf(a.getStringCBC())), " ", 150, 1, true);

                } else {
                    linha
                            = //01	REGISTRO	Número do abastecimento CBC	N	04
                            String.valueOf(a.getNumeroAbastecimento())
                            //02	IDENT_BICO	Código identificador do bico	C	02
                            + ";" + String.valueOf(a.getBico().getIdentBico())
                            //03	IDENT_COMBUST	Código identificador do combustível	C	02
                            + ";" + String.valueOf(a.getBico().getCombustivel().getIdentCombustivel())
                            //04	IDENT_TANQUE	Código identificador do Tanque de combustível	C	02
                            + ";" + String.valueOf(" ")
                            //05	TOT_PAGAR	Quantidade total a pagar (2 casas decimais)	N	06
                            + ";" + String.valueOf(String.format("%.2f", a.getTotalPagar()).replace(",", ""))
                            //06	VOLUME	Volume abastecido em litros	N	06
                            + ";" + String.valueOf(a.getVolume()).replace(".", "")
                            //07	PRECO_UNITARIO	Preço por litro (3 casas decimais)	N	04
                            + ";" + String.valueOf(String.format("%.3f", a.getPrecoUnitario()).replace(",", ""))
                            //08	TEMPO_ABASTEC	Tempo de duração do abastecimento (mmss)	N	04
                            + ";" + String.valueOf(tempoAbastec)
                            //09	DATA	Data do abastecimento	N	08
                            + ";" + String.valueOf(dataAbastec)
                            //10	HORA	Hora do abastecimento	N	04
                            + ";" + String.valueOf(horaAbastec)
                            //11	ENCERRANTE INICIAL	Encerrante do bico (2 casas decimais)	N	10
                            + ";" + String.valueOf(String.format("%.2f", a.getEncerranteAnterior()).replace(",", ""))
                            //12	ENCERRANTE FINAL	Encerrante do bico (2 casas decimais)		10
                            + ";" + String.valueOf(String.format("%.2f", a.getEncerranteLitros()).replace(",", ""))
                            //13	IDENT_OPERADOR	Código identificador do operador	C	16
                            + ";" + String.valueOf(a.getOperador().getIdentOperador())
                            //14	IDENT_VEICULO	Código identificador do veículo	C	16
                            + ";" + String.valueOf(a.getVeiculo().getIdentVeiculo())
                            //15	MARCADOR	Odômetro ou Horímetro na realização do abastecimento	N	06
                            + ";" + String.valueOf(marcadorAnterior)
                            //16	MARCADOR ANTERIOR
                            + ";" + String.valueOf(marcador)
                            //17	TIPO	Tipo de controle do veículo O = Odômetro H = Horímetro	C	01
                            + ";" + String.valueOf(a.getVeiculo().getTipo())
                            //18	IDENT_EMPRESA	Código identificador da empresa	C	2
                            + ";" + String.valueOf(a.getVeiculo().getFrota().getEmpresa().getIdentEmpresa())
                            //19	IDENT_FROTA	Código identificador da frota	C	2
                            + ";" + String.valueOf(a.getVeiculo().getFrota().getIdentFrota())
                            //20	CASAS_DEC	Quantidade de casas decimais do volume	N	01
                            + ";" + String.valueOf(casasDecimais)
                            //21	IDENT_MOTORISTA
                            + ";" + ((a.getMotorista() == null) ? " " : a.getMotorista().getIdentMotorista())
                            //22        KM MEDIO
                            + ";" + String.valueOf(String.format("%.2f", a.getKmMedio()).replace(",", ""))
                            //23        TEMPO MEDIO
                            + ";" + String.valueOf(String.format("%.2f", a.getTempoMedio()).replace(",", ""))
                            //24        TIPO ABASTEC
                            + ";" + String.valueOf(a.getTipoAbastec())
                            //25        IDENTIFICADOR 1
                            + ";" + ((a.getIdent1() == null) ? " " : String.valueOf(a.getIdent1()))
                            //26        IDENTIFICADOR 2
                            + ";" + ((a.getIdent2() == null) ? " " : String.valueOf(a.getIdent2()))
                            //27        STRING CBC
                            + ";" + ((a.getStringCBC() == null) ? " " : String.valueOf(a.getStringCBC()));
                }
                if (cont > 0) {
                    conteudo += System.getProperty("line.separator") + linha;
                } else {
                    conteudo += linha;
                }
                cont++;
            }
            return conteudo;
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            return "";
        }
    }
}
