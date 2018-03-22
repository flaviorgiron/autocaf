package view.cadastros;

import tablemodel.BicosTableModel;
import ctr.GlobalParameter;
import idioma.Idioma;
import java.awt.EventQueue;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.TableColumnModel;
import model.*;
import render.*;
import util.*;
import bd.*;
import java.io.FileInputStream;
import java.util.Properties;

public class BicosGUI extends JPanel {

    private BicosTableModel bicosTableModel;
    private static Idioma i;

    public BicosGUI() {
        try {
            i = GlobalParameter.getIdioma();
            initComponents();

            carregaGrid();
            carregaComboCombustiveis();
            jComboBox1.setRenderer(new CombustivelListRender());
            identField.setDocument(new FixedLengthUpperDocument(16));
            cbcField.setDocument(new FixedLengthUpperDocument(2));
            tfTWC.setDocument(new FixedLengthUpperDocument(2));
            nomebicoField.setDocument(new FixedLengthUpperDocument(100));
            fieldPorta.setDocument(new OnlyNumberFieldDocument(8));
            fieldCasas.setDocument(new OnlyNumberFieldDocument(1));
            encerranteField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#" + FormatacaoDados.DoubleFormat(0).replace(",", ".")))));
            jbtRecarregarActionPerformed(null);
            seq.setVisible(false);

            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);

            if (!properties.getProperty("cbc_conecta_twc").trim().toUpperCase().equals("S")) {
                idbicoLabel5.setVisible(false);
                tfTWC.setVisible(false);
            }
            masterTable.setAutoCreateRowSorter(true);

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
        }
    }

    public BicosTableModel getModelBico() {
        if (bicosTableModel == null) {
            bicosTableModel = (BicosTableModel) masterTable.getModel();
        }
        return bicosTableModel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        idbicoLabel = new javax.swing.JLabel();
        nomebicoLabel = new javax.swing.JLabel();
        idcombustivelLabel = new javax.swing.JLabel();
        identField = new javax.swing.JTextField();
        nomebicoField = new javax.swing.JTextField();
        jbtSalvar = new javax.swing.JButton();
        jbtRecarregar = new javax.swing.JButton();
        jbtNovo = new javax.swing.JButton();
        jbtExcluir = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jplTitulo2 = new javax.swing.JPanel();
        jlbTituloCadastroExame2 = new javax.swing.JLabel();
        ckAtivo = new javax.swing.JCheckBox();
        cbcField = new javax.swing.JTextField();
        idbicoLabel1 = new javax.swing.JLabel();
        seq = new javax.swing.JTextField();
        nomebicoLabel1 = new javax.swing.JLabel();
        encerranteField = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        chkComboio = new javax.swing.JCheckBox();
        idbicoLabel2 = new javax.swing.JLabel();
        fieldConexao = new javax.swing.JTextField();
        idbicoLabel3 = new javax.swing.JLabel();
        fieldPorta = new javax.swing.JTextField();
        idbicoLabel4 = new javax.swing.JLabel();
        fieldCasas = new javax.swing.JTextField();
        ckExterno = new javax.swing.JCheckBox();
        idbicoLabel5 = new javax.swing.JLabel();
        tfTWC = new javax.swing.JTextField();

        FormListener formListener = new FormListener();

        masterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        masterTable.addMouseListener(formListener);
        masterScrollPane.setViewportView(masterTable);

        idbicoLabel.setLabelFor(identField);
        idbicoLabel.setText(i.getProperty("cadbico5"));

        nomebicoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nomebicoLabel.setText("Nome:");

        idcombustivelLabel.setText("Combustível:");

        jbtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jbtSalvar.setText(i.getProperty("sis9"));
        jbtSalvar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtSalvar.setMaximumSize(new java.awt.Dimension(89, 25));
        jbtSalvar.setMinimumSize(new java.awt.Dimension(89, 25));
        jbtSalvar.addActionListener(formListener);

        jbtRecarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/recarregar.png"))); // NOI18N
        jbtRecarregar.setText(i.getProperty("sis8"));
        jbtRecarregar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtRecarregar.addActionListener(formListener);

        jbtNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo.png"))); // NOI18N
        jbtNovo.setText(i.getProperty("sis6"));
        jbtNovo.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtNovo.setMaximumSize(new java.awt.Dimension(89, 25));
        jbtNovo.setMinimumSize(new java.awt.Dimension(89, 25));
        jbtNovo.addActionListener(formListener);

        jbtExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        jbtExcluir.setText(i.getProperty("sis7"));
        jbtExcluir.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtExcluir.setMaximumSize(new java.awt.Dimension(89, 25));
        jbtExcluir.setMinimumSize(new java.awt.Dimension(89, 25));
        jbtExcluir.addActionListener(formListener);

        jplTitulo2.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloCadastroExame2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloCadastroExame2.setText(i.getProperty("princ7"));

        javax.swing.GroupLayout jplTitulo2Layout = new javax.swing.GroupLayout(jplTitulo2);
        jplTitulo2.setLayout(jplTitulo2Layout);
        jplTitulo2Layout.setHorizontalGroup(
            jplTitulo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplTitulo2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTituloCadastroExame2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplTitulo2Layout.setVerticalGroup(
            jplTitulo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplTitulo2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTituloCadastroExame2)
                .addContainerGap())
        );

        ckAtivo.setSelected(true);
        ckAtivo.setText(i.getProperty("cadbico10"));

        idbicoLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        idbicoLabel1.setText("CBC");

        seq.setText("jTextField1");

        nomebicoLabel1.setText(i.getProperty("cadbico9"));

        encerranteField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chkComboio.setText(i.getProperty("cadbico11"));
        chkComboio.addMouseListener(formListener);

        idbicoLabel2.setText(i.getProperty("cadbico12"));

        idbicoLabel3.setText(i.getProperty("cadbico13"));

        idbicoLabel4.setText(i.getProperty("cadbico14"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idbicoLabel3)
                    .addComponent(idbicoLabel2)
                    .addComponent(idbicoLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fieldCasas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldPorta, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(fieldConexao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chkComboio, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(167, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idbicoLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idbicoLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldCasas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idbicoLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chkComboio)
                    .addContainerGap(87, Short.MAX_VALUE)))
        );

        ckExterno.setSelected(true);
        ckExterno.setText("Externo");
        ckExterno.addMouseListener(formListener);

        idbicoLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        idbicoLabel5.setText("Terminal TWC");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idcombustivelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nomebicoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(155, 155, 155))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idbicoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomebicoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nomebicoField)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(idbicoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbcField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(idbicoLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfTWC, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 178, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(identField)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ckExterno))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jbtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbtRecarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(ckAtivo)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(encerranteField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(103, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jplTitulo2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(identField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idbicoLabel)
                    .addComponent(ckExterno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idbicoLabel1)
                    .addComponent(cbcField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idbicoLabel5)
                    .addComponent(tfTWC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomebicoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomebicoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcombustivelLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encerranteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomebicoLabel1)
                    .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckAtivo)
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtRecarregar)
                    .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.MouseListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jbtSalvar) {
                BicosGUI.this.jbtSalvarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtRecarregar) {
                BicosGUI.this.jbtRecarregarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtNovo) {
                BicosGUI.this.jbtNovoActionPerformed(evt);
            }
            else if (evt.getSource() == jbtExcluir) {
                BicosGUI.this.jbtExcluirActionPerformed(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == masterTable) {
                BicosGUI.this.masterTableMouseClicked(evt);
            }
            else if (evt.getSource() == chkComboio) {
                BicosGUI.this.chkComboioMouseClicked(evt);
            }
            else if (evt.getSource() == ckExterno) {
                BicosGUI.this.ckExternoMouseClicked(evt);
            }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == ckExterno) {
                BicosGUI.this.ckExternoMouseReleased(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("unchecked")
    private void jbtRecarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRecarregarActionPerformed
        try {
            carregaGrid();
            limpaCampos();
            disableCampos();
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        } finally {
            jbtNovo.setEnabled(true);
            jbtSalvar.setEnabled(false);
            jbtExcluir.setEnabled(false);
        }
    }//GEN-LAST:event_jbtRecarregarActionPerformed

    private void jbtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExcluirActionPerformed
        int option = JConfirmMessage.showOptionDialog(i.getProperty("sis1"), i.getProperty("sis12"));
        if (option == 0) {
            try {
                if (seq.getText().equals("")) {
                    JConfirmMessage.showMessageDialog(i.getProperty("sis13"), i.getProperty("sis1"));
                    return;
                }
                BicosDB.excluirBico(Integer.parseInt(seq.getText()));
                jbtRecarregar.doClick();
            } catch (Exception e) {
                GeraLog g = new GeraLog();
                g.gravaErro(e);
                g.close();
                JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
            }
        }
    }//GEN-LAST:event_jbtExcluirActionPerformed

    private void jbtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNovoActionPerformed
        try {
            enableCampos();
            limpaCampos();
            identField.requestFocus();
            jbtSalvar.setEnabled(true);
            jbtNovo.setEnabled(false);
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        } finally {
            jbtSalvar.setEnabled(true);
            jbtNovo.setEnabled(false);
            jbtExcluir.setEnabled(false);
        }
     }//GEN-LAST:event_jbtNovoActionPerformed

    private boolean verificaCamposObrigatorios() {
        try {
            if (identField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, i.getProperty("cadbico1"));
                identField.requestFocus();
                return false;
            }
            if (cbcField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, i.getProperty("cadbico2"));
                cbcField.requestFocus();
                return false;
            }
            if (nomebicoField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, i.getProperty("cadbico3"));
                nomebicoField.requestFocus();
                return false;
            }

            if (jComboBox1.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, i.getProperty("cadbico4"));
                jComboBox1.requestFocus();
                return false;
            }

            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);

            if (properties.getProperty("cbc_conecta_twc").trim().toUpperCase().equals("S")) {
                if (tfTWC.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Terminal TWC não informado");
                    jComboBox1.requestFocus();
                    return false;
                }
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            //e.printStackTrace();
        }
        return true;
    }

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        try {
            boolean bicoJaCadastradoCBC = false;
            boolean bicoJaCadastradoTWC = false;

            String statusAtual;
            if (verificaCamposObrigatorios()) {
                Bico b = new Bico();

                if (ckAtivo.isSelected()) {
                    statusAtual = "A";
                } else {
                    statusAtual = "I";
                }

                if (!seq.getText().trim().equals("")) {
                    b.setSeqBico(Integer.valueOf(seq.getText().trim()));
                    if (!ckExterno.isSelected()) {
                        bicoJaCadastradoCBC = BicosDB.buscaBicoJaCadastradoCBC(cbcField.getText(), Integer.valueOf(seq.getText().trim()), statusAtual);
                    }

                    if (tfTWC.isVisible()) {
                        bicoJaCadastradoTWC = BicosDB.buscaBicoJaCadastradoTWC(tfTWC.getText(), Integer.valueOf(seq.getText().trim()), statusAtual);
                    }

                } else {
                    if (!ckExterno.isSelected()) {
                        bicoJaCadastradoCBC = BicosDB.buscaBicoJaCadastradoCBC(cbcField.getText(), 0, statusAtual);
                    }
                    if (tfTWC.isVisible()) {
                        bicoJaCadastradoTWC = BicosDB.buscaBicoJaCadastradoTWC(tfTWC.getText(), 0, statusAtual);
                    }
                }

                if (bicoJaCadastradoCBC) {
                    JOptionPane.showMessageDialog(null, "Bico já cadastrado com este código CBC.");
                    cbcField.requestFocus();
                    return;
                }

                if (bicoJaCadastradoTWC) {
                    JOptionPane.showMessageDialog(null, "Bico já cadastrado com este código de Terminal TWC.");
                    tfTWC.requestFocus();
                    return;
                }

                if (!identField.getText().trim().equals("")) {
                    b.setIdentBico(identField.getText().trim());
                }
                b.setIdCBC(cbcField.getText());
                b.setNomeBico(nomebicoField.getText());
                if (tfTWC.isVisible()) {
                    b.setIdTWC(Integer.valueOf(tfTWC.getText()));
                } else {
                    b.setIdTWC(0);
                }
                b.setCombustivel((Combustivel) jComboBox1.getModel().getSelectedItem());
                if (ckAtivo.isSelected()) {
                    b.setSituacao("A");
                } else {
                    b.setSituacao("I");
                }

                if (ckExterno.isSelected()) {
                    b.setExterno("S");
                } else {
                    b.setExterno("N");
                }

                if (encerranteField.getText().trim().equals("")) {
                    b.setEncerrante(0.0);
                    //encerranteField.setText("0,00");
                } else {
                    b.setEncerrante(Double.parseDouble(encerranteField.getText().replace(",", ".")));
                }

                if (fieldPorta.getText().trim().equals("")) {
                    fieldPorta.setText("0");
                }
                if (fieldCasas.getText().trim().equals("")) {
                    fieldCasas.setText("0");
                }
                if (chkComboio.isSelected()) {
                    b.setComboio("S");
                    b.setConexao(fieldConexao.getText());
                    b.setPorta(Integer.parseInt(fieldPorta.getText()));
                    b.setCasasDecimais(Integer.parseInt(fieldCasas.getText()));
                } else {
                    b.setComboio("N");
                    b.setConexao("");
                    b.setPorta(0);
                    b.setCasasDecimais(0);
                }

                if (BicosDB.insertOrUpdateBico(b)) {
                    jbtRecarregar.doClick();
                    jbtSalvar.setEnabled(false);
                    jbtSalvar.setEnabled(false);
                    jbtExcluir.setEnabled(false);
                    jbtNovo.setEnabled(true);
                }
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    private void masterTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterTableMouseClicked
        if (evt.getClickCount() == 2) {
            int selected = masterTable.getSelectedRow();
            Object cf = masterTable.getValueAt(selected, 0);
            try {
                Bico bico = BicosDB.buscaBico(Integer.parseInt(cf.toString()));
                if (bico != null) {
                    mostrar(bico);
                    enableCampos();
                    jbtExcluir.setEnabled(true);
                    jbtSalvar.setEnabled(true);
                }
            } catch (Exception e) {
                GeraLog g = new GeraLog();
                g.gravaErro(e);
                g.close();
                JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
            } finally {
                jbtExcluir.setEnabled(true);
                jbtSalvar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_masterTableMouseClicked

    private void chkComboioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkComboioMouseClicked
        if (chkComboio.isSelected()) {
            fieldConexao.setEnabled(true);
            fieldPorta.setEnabled(true);
            fieldCasas.setEnabled(true);
        } else {
            fieldConexao.setEnabled(false);
            fieldPorta.setEnabled(false);
            fieldCasas.setEnabled(false);
        }
    }//GEN-LAST:event_chkComboioMouseClicked

    private void ckExternoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckExternoMouseClicked

    }//GEN-LAST:event_ckExternoMouseClicked

    private void ckExternoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckExternoMouseReleased
        if (ckExterno.isSelected()) {
            cbcField.setEnabled(false);
            cbcField.setText("99");
            tfTWC.setVisible(false);
            idbicoLabel5.setVisible(false);
            tfTWC.setText("0");
            chkComboio.setEnabled(false);
            fieldConexao.setText("");
            fieldPorta.setText("");
            fieldCasas.setText("");
            encerranteField.setText("0,00");
            encerranteField.setEnabled(false);
            chkComboio.setSelected(false);
        } else {
            cbcField.setEnabled(true);

            tfTWC.setVisible(true);
            idbicoLabel5.setVisible(true);

            chkComboio.setEnabled(true);
            encerranteField.setEnabled(true);
            cbcField.setText("");
            tfTWC.setText("");
        }
    }//GEN-LAST:event_ckExternoMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cbcField;
    private javax.swing.JCheckBox chkComboio;
    private javax.swing.JCheckBox ckAtivo;
    private javax.swing.JCheckBox ckExterno;
    private javax.swing.JFormattedTextField encerranteField;
    private javax.swing.JTextField fieldCasas;
    private javax.swing.JTextField fieldConexao;
    private javax.swing.JTextField fieldPorta;
    private javax.swing.JLabel idbicoLabel;
    private javax.swing.JLabel idbicoLabel1;
    private javax.swing.JLabel idbicoLabel2;
    private javax.swing.JLabel idbicoLabel3;
    private javax.swing.JLabel idbicoLabel4;
    private javax.swing.JLabel idbicoLabel5;
    private javax.swing.JLabel idcombustivelLabel;
    private javax.swing.JTextField identField;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtExcluir;
    private javax.swing.JButton jbtNovo;
    private javax.swing.JButton jbtRecarregar;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JLabel jlbTituloCadastroExame2;
    private javax.swing.JPanel jplTitulo2;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JTextField nomebicoField;
    private javax.swing.JLabel nomebicoLabel;
    private javax.swing.JLabel nomebicoLabel1;
    private javax.swing.JTextField seq;
    private javax.swing.JTextField tfTWC;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new BicosGUI());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

    private void carregaGrid() {
        try {
            masterTable.setModel(new BicosTableModel());
            masterTable.setAutoCreateRowSorter(false);
            masterTable.setRowHeight(20);
            TableColumnModel columnModel = masterTable.getColumnModel();
            CombustivelTableCellRender combustivelRender = new CombustivelTableCellRender();
            columnModel.getColumn(2).setPreferredWidth(190);
            columnModel.getColumn(3).setCellRenderer(combustivelRender);

            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);

            getModelBico().limpar();
            getModelBico().addListaBicos(BicosDB.buscaBicos(true));
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }

    private void carregaComboCombustiveis() {
        jComboBox1.removeAllItems();
        Iterator iterator = CombustiveisDB.buscaCombustiveis(false).iterator();
        while (iterator.hasNext()) {
            Combustivel c = (Combustivel) iterator.next();
            jComboBox1.addItem(c);
        }
    }

    private void enableCampos() {
        identField.setEnabled(true);
        nomebicoField.setEnabled(true);
        tfTWC.setEnabled(true);
        jComboBox1.setEnabled(true);
        ckAtivo.setEnabled(true);
        ckExterno.setEnabled(true);
        cbcField.setEnabled(true);
        tfTWC.setEnabled(true);
        encerranteField.setEnabled(true);
        chkComboio.setEnabled(true);

        if (ckExterno.isSelected()) {
            cbcField.setEnabled(false);
            
            tfTWC.setVisible(false);
            idbicoLabel5.setVisible(false);
            
            tfTWC.setText("0");
            cbcField.setText("99");
            chkComboio.setEnabled(false);
            fieldConexao.setText("");
            fieldPorta.setText("");
            fieldCasas.setText("");
            encerranteField.setText("0,00");
            encerranteField.setEnabled(false);
        }

    }

    private void limpaCampos() {
        seq.setText("");
        identField.setText("");
        nomebicoField.setText("");
        tfTWC.setText("");
        cbcField.setText("");
        ckAtivo.setSelected(true);
        ckExterno.setSelected(false);
        if (jComboBox1.getItemCount() == 1) {
            jComboBox1.setSelectedIndex(0);
        } else {
            jComboBox1.setSelectedIndex(-1);
        }

        encerranteField.setText("");

        chkComboio.setSelected(false);
        fieldConexao.setText("");
        fieldPorta.setText("");
        fieldCasas.setText("");
    }

    private void disableCampos() {
        identField.setEnabled(false);
        cbcField.setEnabled(false);
        tfTWC.setEnabled(false);
        nomebicoField.setEnabled(false);
        tfTWC.setEnabled(false);
        jComboBox1.setEnabled(false);
        ckAtivo.setEnabled(false);
        ckExterno.setEnabled(false);
        encerranteField.setEnabled(false);

        chkComboio.setEnabled(false);
        fieldConexao.setEnabled(false);
        fieldPorta.setEnabled(false);
        fieldCasas.setEnabled(false);
    }

    public void mostrar(Bico bico) {
        seq.setText(bico.getSeqBico().toString());
        identField.setText(bico.getIdentBico());
        nomebicoField.setText(bico.getNomeBico());
        tfTWC.setText(bico.getIdTWC().toString());
        cbcField.setText(bico.getIdCBC());
        if (bico.getSituacao().equals("A")) {
            ckAtivo.setSelected(true);
        } else {
            ckAtivo.setSelected(false);
        }

        if (bico.getExterno().equals("S")) {
            ckExterno.setSelected(true);
        } else {
            ckExterno.setSelected(false);
        }

        encerranteField.setText(FormatacaoDados.DoubleFormat(bico.getEncerrante()));

        jComboBox1.getModel().setSelectedItem(bico.getCombustivel());
        for (int i = 0; i < jComboBox1.getItemCount(); i++) {
            if (((Combustivel) jComboBox1.getItemAt(i)).getSeqCombustivel().equals(bico.getCombustivel().getSeqCombustivel())) {
                jComboBox1.setSelectedIndex(i);
            }
        }

        if (bico.getComboio().equals("S")) {
            chkComboio.setSelected(true);
            fieldConexao.setEnabled(true);
            fieldPorta.setEnabled(true);
            fieldCasas.setEnabled(true);
            fieldConexao.setText(bico.getConexao());
            fieldPorta.setText(bico.getPorta().toString());
            fieldCasas.setText(bico.getCasasDecimais().toString());

        } else {
            chkComboio.setSelected(false);
            fieldConexao.setText("");
            fieldPorta.setText("");
            fieldCasas.setText("");
            fieldConexao.setEnabled(false);
            fieldPorta.setEnabled(false);
            fieldCasas.setEnabled(false);
        }

        if (bico.getExterno().equals("S")) {
            cbcField.setEnabled(false);
            cbcField.setText("99");
            
            tfTWC.setVisible(false);
            idbicoLabel5.setVisible(false);
            tfTWC.setText("0");
            
            chkComboio.setEnabled(false);
            fieldConexao.setText("");
            fieldPorta.setText("");
            fieldCasas.setText("");
            encerranteField.setText("0,00");
            encerranteField.setEnabled(false);
        }
        identField.requestFocus();
    }
}
