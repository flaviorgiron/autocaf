package view.cadastros;

import bd.FrotasDB;
import bd.EmpresasDB;
import java.awt.EventQueue;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.TableColumnModel;
import model.Frota;
import model.Empresa;
import render.EmpresaTableCellRender;
import render.EmpresaListRender;
import tablemodel.FrotasTableModel;
import util.FixedLengthUpperDocument;
import util.FormatacaoDados;
import util.GeraLog;
import util.JConfirmMessage;

public class FrotasGUI extends JPanel {

    private FrotasTableModel frotasTableModel;

    public FrotasGUI() {
        initComponents();
        carregaGrid();
        carregaComboEmpresas();
        jComboBox1.setRenderer(new EmpresaListRender());
        identField.setDocument(new FixedLengthUpperDocument(2));
        nomefrotaField.setDocument(new FixedLengthUpperDocument(100));
        seq.setVisible(false);

        limiteField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#" + FormatacaoDados.DoubleFormat(0).replace(",", ".")))));
        disponivelField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#" + FormatacaoDados.DoubleFormat(0).replace(",", ".")))));

        jbtRecarregarActionPerformed(null);
        identField.requestFocus();

        masterTable.setAutoCreateRowSorter(true);
    }

    public FrotasTableModel getModelFrota() {
        if (frotasTableModel == null) {
            frotasTableModel = (FrotasTableModel) masterTable.getModel();
        }
        return frotasTableModel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplTitulo2 = new javax.swing.JPanel();
        jlbTituloCadastroExame2 = new javax.swing.JLabel();
        jbtRecarregar = new javax.swing.JButton();
        jbtNovo = new javax.swing.JButton();
        jbtExcluir = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        idbicoLabel = new javax.swing.JLabel();
        nomebicoLabel = new javax.swing.JLabel();
        ckAtivo = new javax.swing.JCheckBox();
        idcombustivelLabel = new javax.swing.JLabel();
        identField = new javax.swing.JTextField();
        nomefrotaField = new javax.swing.JTextField();
        jbtSalvar = new javax.swing.JButton();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        nomebicoLabel3 = new javax.swing.JLabel();
        limiteField = new javax.swing.JFormattedTextField();
        idcombustivelLabel3 = new javax.swing.JLabel();
        nomebicoLabel4 = new javax.swing.JLabel();
        disponivelField = new javax.swing.JFormattedTextField();
        idcombustivelLabel4 = new javax.swing.JLabel();
        seq = new javax.swing.JTextField();

        FormListener formListener = new FormListener();

        jplTitulo2.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloCadastroExame2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloCadastroExame2.setText("Cadastro de Frotas");

        javax.swing.GroupLayout jplTitulo2Layout = new javax.swing.GroupLayout(jplTitulo2);
        jplTitulo2.setLayout(jplTitulo2Layout);
        jplTitulo2Layout.setHorizontalGroup(
            jplTitulo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplTitulo2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTituloCadastroExame2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplTitulo2Layout.setVerticalGroup(
            jplTitulo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplTitulo2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTituloCadastroExame2)
                .addContainerGap())
        );

        jbtRecarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/recarregar.png"))); // NOI18N
        jbtRecarregar.setText("Recarregar");
        jbtRecarregar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtRecarregar.addActionListener(formListener);

        jbtNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo.png"))); // NOI18N
        jbtNovo.setText("Novo");
        jbtNovo.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtNovo.setMaximumSize(new java.awt.Dimension(89, 25));
        jbtNovo.setMinimumSize(new java.awt.Dimension(89, 25));
        jbtNovo.addActionListener(formListener);

        jbtExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        jbtExcluir.setText("Excluir");
        jbtExcluir.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtExcluir.setMaximumSize(new java.awt.Dimension(89, 25));
        jbtExcluir.setMinimumSize(new java.awt.Dimension(89, 25));
        jbtExcluir.addActionListener(formListener);

        idbicoLabel.setText("Ident.");

        nomebicoLabel.setText("Nome");

        ckAtivo.setSelected(true);
        ckAtivo.setText("Ativo");

        idcombustivelLabel.setText("Empresa");

        jbtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jbtSalvar.setText("Salvar");
        jbtSalvar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtSalvar.setMaximumSize(new java.awt.Dimension(89, 25));
        jbtSalvar.setMinimumSize(new java.awt.Dimension(89, 25));
        jbtSalvar.addActionListener(formListener);

        masterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        masterTable.addMouseListener(formListener);
        masterScrollPane.setViewportView(masterTable);

        nomebicoLabel3.setText("Limite p/ Abastecimento");

        limiteField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        idcombustivelLabel3.setText("Litros");

        nomebicoLabel4.setText("Quantidade Disponível");

        disponivelField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        idcombustivelLabel4.setText("Litros");

        seq.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jplTitulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(idbicoLabel)
                                        .addComponent(nomebicoLabel)
                                        .addComponent(idcombustivelLabel))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(identField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(58, 58, 58)
                                            .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jbtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jbtRecarregar)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(nomefrotaField, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ckAtivo))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomebicoLabel3)
                                    .addComponent(nomebicoLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(limiteField, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idcombustivelLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(disponivelField, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idcombustivelLabel4)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idbicoLabel)
                    .addComponent(identField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomebicoLabel)
                    .addComponent(nomefrotaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idcombustivelLabel)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomebicoLabel3)
                    .addComponent(idcombustivelLabel3)
                    .addComponent(limiteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomebicoLabel4)
                    .addComponent(disponivelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcombustivelLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckAtivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtRecarregar)
                    .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.MouseListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jbtRecarregar) {
                FrotasGUI.this.jbtRecarregarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtNovo) {
                FrotasGUI.this.jbtNovoActionPerformed(evt);
            }
            else if (evt.getSource() == jbtExcluir) {
                FrotasGUI.this.jbtExcluirActionPerformed(evt);
            }
            else if (evt.getSource() == jbtSalvar) {
                FrotasGUI.this.jbtSalvarActionPerformed(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == masterTable) {
                FrotasGUI.this.masterTableMouseClicked(evt);
            }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    private void jbtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExcluirActionPerformed
        int option = JConfirmMessage.showOptionDialog("Atenção", "Deseja realmente excluir o registro?");
        if (option == 0) {
            try {
                if (seq.getText().equals("")) {
                    JConfirmMessage.showMessageDialog("Selecione um registro para excluir !!!", "Atenção");
                    return;
                }
                FrotasDB.excluirFrota(Integer.parseInt(seq.getText()));
                jbtRecarregar.doClick();
            } catch (Exception e) {
                GeraLog g = new GeraLog();
                g.gravaErro(e);
                g.close();
                JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
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
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        } finally {
            jbtSalvar.setEnabled(true);
            jbtNovo.setEnabled(false);
            jbtExcluir.setEnabled(false);
        }
    }//GEN-LAST:event_jbtNovoActionPerformed

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
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        } finally {
            jbtNovo.setEnabled(true);
            jbtSalvar.setEnabled(false);
            jbtExcluir.setEnabled(false);
        }
    }//GEN-LAST:event_jbtRecarregarActionPerformed

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        try {
            if (verificaCamposObrigatorios()) {
                Frota f = new Frota();
                if (!seq.getText().trim().equals("")) {
                    f.setSeqFrota(Integer.valueOf(seq.getText().trim()));
                }

                if (!identField.getText().trim().equals("")) {
                    f.setIdentFrota(identField.getText().trim());
                }

                f.setNomeFrota(nomefrotaField.getText());
                f.setEmpresa((Empresa) jComboBox1.getModel().getSelectedItem());
                if (ckAtivo.isSelected()) {
                    f.setSituacao("A");
                } else {
                    f.setSituacao("I");
                }
                if (limiteField.getText().trim().equals("")) {
                    f.setLimite(0.0);
                    disponivelField.setText("0,00");
                } else {
                    f.setLimite(Double.parseDouble(limiteField.getText().replace(",", ".")));
                }

                if (disponivelField.getText().trim().equals("")) {
                    f.setDisponivel(0.0);
                } else {
                    f.setDisponivel(Double.parseDouble(disponivelField.getText().replace(",", ".")));
                }

                if (FrotasDB.insertOrUpdateFrota(f)) {
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
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    private void masterTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterTableMouseClicked
        if (evt.getClickCount() == 2) {
            int selected = masterTable.getSelectedRow();
            Object cf = masterTable.getValueAt(selected, 0);
            try {
                Frota frota = FrotasDB.buscaFrota(Integer.parseInt(cf.toString()));
                if (frota != null) {
                    mostrar(frota);
                    enableCampos();
                    jbtExcluir.setEnabled(true);
                    jbtSalvar.setEnabled(true);
                }
            } catch (Exception e) {
                GeraLog g = new GeraLog();
                g.gravaErro(e);
                g.close();
                JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            } finally {
                jbtExcluir.setEnabled(true);
                jbtSalvar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_masterTableMouseClicked

    private boolean verificaCamposObrigatorios() {
        if (identField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Identificador não informado");
            identField.requestFocus();
            return false;
        }
        if (nomefrotaField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome não informado");
            nomefrotaField.requestFocus();
            return false;
        }
        if (jComboBox1.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Empresa não informada");
            jComboBox1.requestFocus();
            return false;
        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ckAtivo;
    private javax.swing.JFormattedTextField disponivelField;
    private javax.swing.JLabel idbicoLabel;
    private javax.swing.JLabel idcombustivelLabel;
    private javax.swing.JLabel idcombustivelLabel3;
    private javax.swing.JLabel idcombustivelLabel4;
    private javax.swing.JTextField identField;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JButton jbtExcluir;
    private javax.swing.JButton jbtNovo;
    private javax.swing.JButton jbtRecarregar;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JLabel jlbTituloCadastroExame2;
    private javax.swing.JPanel jplTitulo2;
    private javax.swing.JFormattedTextField limiteField;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JLabel nomebicoLabel;
    private javax.swing.JLabel nomebicoLabel3;
    private javax.swing.JLabel nomebicoLabel4;
    private javax.swing.JTextField nomefrotaField;
    private javax.swing.JTextField seq;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new FrotasGUI());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

    private void carregaGrid() {
        try {
            masterTable.setModel(new FrotasTableModel());
            masterTable.setAutoCreateRowSorter(false);
            masterTable.setRowHeight(20);
            TableColumnModel columnModel = masterTable.getColumnModel();
            EmpresaTableCellRender empresaRender = new EmpresaTableCellRender();
            columnModel.getColumn(2).setPreferredWidth(190);
            columnModel.getColumn(3).setCellRenderer(empresaRender);

            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);

            getModelFrota().limpar();
            getModelFrota().addListaFrotas(FrotasDB.buscaFrotas(true));
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }

    private void carregaComboEmpresas() {
        jComboBox1.removeAllItems();
        Iterator iterator = EmpresasDB.buscaEmpresas(false).iterator();
        while (iterator.hasNext()) {
            Empresa e = (Empresa) iterator.next();
            jComboBox1.addItem(e);
        }
    }

    private void enableCampos() {
        identField.setEnabled(true);
        nomefrotaField.setEnabled(true);
        jComboBox1.setEnabled(true);
        ckAtivo.setEnabled(true);
        limiteField.setEnabled(true);
        disponivelField.setEnabled(true);
    }

    private void limpaCampos() {
        seq.setText("");
        identField.setText("");
        nomefrotaField.setText("");
        limiteField.setText("");
        disponivelField.setText("");
        ckAtivo.setSelected(true);
        if (jComboBox1.getItemCount() == 1) {
            jComboBox1.setSelectedIndex(0);
        } else {
            jComboBox1.setSelectedIndex(-1);
        }
    }

    private void disableCampos() {
        identField.setEnabled(false);
        nomefrotaField.setEnabled(false);
        jComboBox1.setEnabled(false);
        ckAtivo.setEnabled(false);
        limiteField.setEnabled(false);
        disponivelField.setEnabled(false);
    }

    public void mostrar(Frota frota) {
        seq.setText(frota.getSeqFrota().toString());
        identField.setText(frota.getIdentFrota());
        nomefrotaField.setText(frota.getNomeFrota());
        limiteField.setText(FormatacaoDados.DoubleFormat(frota.getLimite()));
        //TratamentoValores.arredondar(v.getFrota().getDisponivel() * PrecosDB.buscaUltimoPreco(b).getPreco(), 2, 0);
        //disponivelField.setText(TratamentoValores.arredondar(frota.getDisponivel(), 2, 0).toString().replace(".", ","));

        disponivelField.setText(FormatacaoDados.DoubleFormat(frota.getDisponivel()));

        if (frota.getSituacao().equals("A")) {
            ckAtivo.setSelected(true);
        } else {
            ckAtivo.setSelected(false);
        }

        jComboBox1.getModel().setSelectedItem(frota.getEmpresa());
        for (int i = 0; i < jComboBox1.getItemCount(); i++) {
            if (((Empresa) jComboBox1.getItemAt(i)).getSeqEmpresa().equals(frota.getEmpresa().getSeqEmpresa())) {
                jComboBox1.setSelectedIndex(i);
            }
        }
        identField.requestFocus();
    }
}
