package view.cadastros;

import bd.EmpresasDB;
import ctr.GlobalParameter;
import idioma.Idioma;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.TableColumnModel;
import model.Empresa;
import tablemodel.EmpresasTableModel;
import util.*;

public class EmpresasGUI extends JPanel {

    private EmpresasTableModel empresasTableModel;
    private static Idioma i;

    public EmpresasGUI() {
        i = GlobalParameter.getIdioma();
        initComponents();
        carregaGrid();
        identField.setDocument(new FixedLengthUpperDocument(2));
        nomeempresaField.setDocument(new FixedLengthUpperDocument(100));
        enderecoField.setDocument(new FixedLengthUpperDocument(255));
        jbtRecarregarActionPerformed(null);
        seq.setVisible(false);

        masterTable.setAutoCreateRowSorter(true);
    }

    public EmpresasTableModel getModelCombust() {
        if (empresasTableModel == null) {
            empresasTableModel = (EmpresasTableModel) masterTable.getModel();
        }
        return empresasTableModel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        idcombustivelLabel = new javax.swing.JLabel();
        nomecombustivelLabel = new javax.swing.JLabel();
        identField = new javax.swing.JTextField();
        nomeempresaField = new javax.swing.JTextField();
        jbtSalvar = new javax.swing.JButton();
        jbtRecarregar = new javax.swing.JButton();
        jbtNovo = new javax.swing.JButton();
        jbtExcluir = new javax.swing.JButton();
        jplTitulo2 = new javax.swing.JPanel();
        jlbTituloCadastroExame2 = new javax.swing.JLabel();
        ckAtivo = new javax.swing.JCheckBox();
        seq = new javax.swing.JTextField();
        nomecombustivelLabel1 = new javax.swing.JLabel();
        enderecoField = new javax.swing.JTextField();

        FormListener formListener = new FormListener();

        masterTable.addMouseListener(formListener);
        masterScrollPane.setViewportView(masterTable);
        if (masterTable.getColumnModel().getColumnCount() > 0) {
            masterTable.getColumnModel().getColumn(0).setResizable(false);
            masterTable.getColumnModel().getColumn(1).setResizable(false);
        }

        idcombustivelLabel.setText(i.getProperty("cadempresa4"));

        nomecombustivelLabel.setText(i.getProperty("cadempresa5"));

        jbtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jbtSalvar.setText(i.getProperty("sis9"));
        jbtSalvar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtSalvar.addActionListener(formListener);

        jbtRecarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/recarregar.png"))); // NOI18N
        jbtRecarregar.setText(i.getProperty("sis8"));
        jbtRecarregar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtRecarregar.addActionListener(formListener);

        jbtNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo.png"))); // NOI18N
        jbtNovo.setText(i.getProperty("sis6"));
        jbtNovo.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtNovo.addActionListener(formListener);

        jbtExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        jbtExcluir.setText(i.getProperty("sis7"));
        jbtExcluir.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtExcluir.addActionListener(formListener);

        jplTitulo2.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloCadastroExame2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloCadastroExame2.setText(i.getProperty("princ5"));

        javax.swing.GroupLayout jplTitulo2Layout = new javax.swing.GroupLayout(jplTitulo2);
        jplTitulo2.setLayout(jplTitulo2Layout);
        jplTitulo2Layout.setHorizontalGroup(
            jplTitulo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplTitulo2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTituloCadastroExame2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jplTitulo2Layout.setVerticalGroup(
            jplTitulo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplTitulo2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTituloCadastroExame2)
                .addContainerGap())
        );

        ckAtivo.setSelected(true);
        ckAtivo.setText(i.getProperty("cadempresa6"));
        ckAtivo.setMargin(new java.awt.Insets(0, 0, 0, 0));

        seq.setText("jTextField1");

        nomecombustivelLabel1.setText(i.getProperty("cadempresa7"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nomecombustivelLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(nomecombustivelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(idcombustivelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(identField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ckAtivo)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jbtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtRecarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(enderecoField)
                                    .addComponent(nomeempresaField))
                                .addGap(0, 10, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jplTitulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addComponent(idcombustivelLabel)
                    .addComponent(identField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomecombustivelLabel)
                    .addComponent(nomeempresaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enderecoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomecombustivelLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckAtivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSalvar)
                    .addComponent(jbtRecarregar)
                    .addComponent(jbtExcluir)
                    .addComponent(jbtNovo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.MouseListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jbtSalvar) {
                EmpresasGUI.this.jbtSalvarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtRecarregar) {
                EmpresasGUI.this.jbtRecarregarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtNovo) {
                EmpresasGUI.this.jbtNovoActionPerformed(evt);
            }
            else if (evt.getSource() == jbtExcluir) {
                EmpresasGUI.this.jbtExcluirActionPerformed(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == masterTable) {
                EmpresasGUI.this.masterTableMouseClicked(evt);
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
                EmpresasDB.excluirEmpresa(Integer.parseInt(seq.getText()));
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
        if (identField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, i.getProperty("cadempresa1"));
            identField.requestFocus();
            return false;
        }
        if (nomeempresaField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, i.getProperty("cadempresa2"));
            nomeempresaField.requestFocus();
            return false;
        }
        if (enderecoField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, i.getProperty("cadempresa3"));
            enderecoField.requestFocus();
            return false;
        }

        return true;
    }

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        try {
            if (verificaCamposObrigatorios()) {
                Empresa c = new Empresa();
                if (!seq.getText().trim().equals("")) {
                    c.setSeqEmpresa(Integer.valueOf(seq.getText().trim()));
                }

                if (!identField.getText().trim().equals("")) {
                    c.setIdentEmpresa(identField.getText().trim());
                }

                c.setNomeEmpresa(nomeempresaField.getText().trim());
                c.setEnderecoCompleto(enderecoField.getText().trim());

                if (ckAtivo.isSelected()) {
                    c.setSituacao("A");
                } else {
                    c.setSituacao("I");
                }

                if (EmpresasDB.insertOrUpdate(c)) {
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
                Empresa c = EmpresasDB.buscaEmpresa(Integer.parseInt(cf.toString()));
                if (c != null) {
                    mostrar(c);
                    enableCampos();
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
    private void carregaGrid() {
        try {
            masterTable.setModel(new EmpresasTableModel());
            masterTable.setAutoCreateRowSorter(false);
            masterTable.setRowHeight(20);
            TableColumnModel columnModel = masterTable.getColumnModel();
            columnModel.getColumn(2).setPreferredWidth(300);

            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);

            getModelCombust().limpar();
            getModelCombust().addListaEmpresas(EmpresasDB.buscaEmpresas(true));
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }

    private void enableCampos() {
        identField.setEnabled(true);
        nomeempresaField.setEnabled(true);
        enderecoField.setEnabled(true);
        ckAtivo.setEnabled(true);
    }

    private void limpaCampos() {
        seq.setText("");
        identField.setText("");
        nomeempresaField.setText("");
        enderecoField.setText("");
        ckAtivo.setSelected(true);
    }

    private void disableCampos() {
        identField.setEnabled(false);
        nomeempresaField.setEnabled(false);
        ckAtivo.setEnabled(false);
        enderecoField.setEnabled(false);
    }

    public void mostrar(Empresa c) {
        seq.setText(c.getSeqEmpresa().toString());
        identField.setText(c.getIdentEmpresa());
        nomeempresaField.setText(c.getNomeEmpresa());
        enderecoField.setText(c.getEnderecoCompleto());
        enderecoField.setCaretPosition(0);
        if (c.getSituacao().equals("A")) {
            ckAtivo.setSelected(true);
        } else {
            ckAtivo.setSelected(false);
        }
        identField.requestFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ckAtivo;
    private javax.swing.JTextField enderecoField;
    private javax.swing.JLabel idcombustivelLabel;
    private javax.swing.JTextField identField;
    private javax.swing.JButton jbtExcluir;
    private javax.swing.JButton jbtNovo;
    private javax.swing.JButton jbtRecarregar;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JLabel jlbTituloCadastroExame2;
    private javax.swing.JPanel jplTitulo2;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JLabel nomecombustivelLabel;
    private javax.swing.JLabel nomecombustivelLabel1;
    private javax.swing.JTextField nomeempresaField;
    private javax.swing.JTextField seq;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new EmpresasGUI());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

}
