package view.cadastros;

import bd.MotoristasDB;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.TableColumnModel;
import model.Motorista;
import tablemodel.MotoristasTableModel;
import util.FixedLengthUpperDocument;
import util.GeraLog;
import util.JConfirmMessage;
import util.TratamentoValores;

public class MotoristasGUI extends JPanel {

    private MotoristasTableModel motoristasTableModel;

    public MotoristasGUI() {
        initComponents();
        //carregaGrid();
        identField.setDocument(new FixedLengthUpperDocument(16));
        nomeMotoristaField.setDocument(new FixedLengthUpperDocument(100));
        jbtRecarregarActionPerformed(null);
        seq.setVisible(false);

        masterTable.setAutoCreateRowSorter(true);
    }

    public MotoristasTableModel getModelCombust() {
        if (motoristasTableModel == null) {
            motoristasTableModel = (MotoristasTableModel) masterTable.getModel();
        }
        return motoristasTableModel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        idcombustivelLabel = new javax.swing.JLabel();
        nomecombustivelLabel = new javax.swing.JLabel();
        identField = new javax.swing.JTextField();
        nomeMotoristaField = new javax.swing.JTextField();
        jbtSalvar = new javax.swing.JButton();
        jbtRecarregar = new javax.swing.JButton();
        jbtNovo = new javax.swing.JButton();
        jbtExcluir = new javax.swing.JButton();
        jplTitulo2 = new javax.swing.JPanel();
        jlbTituloCadastroExame2 = new javax.swing.JLabel();
        ckAtivo = new javax.swing.JCheckBox();
        seq = new javax.swing.JTextField();

        FormListener formListener = new FormListener();

        masterTable.addMouseListener(formListener);
        masterScrollPane.setViewportView(masterTable);
        if (masterTable.getColumnModel().getColumnCount() > 0) {
            masterTable.getColumnModel().getColumn(0).setResizable(false);
            masterTable.getColumnModel().getColumn(1).setResizable(false);
        }

        idcombustivelLabel.setText("Ident.");

        nomecombustivelLabel.setText("Nome");

        jbtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jbtSalvar.setText("Salvar");
        jbtSalvar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtSalvar.addActionListener(formListener);

        jbtRecarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/recarregar.png"))); // NOI18N
        jbtRecarregar.setText("Recarregar");
        jbtRecarregar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtRecarregar.addActionListener(formListener);

        jbtNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo.png"))); // NOI18N
        jbtNovo.setText("Novo");
        jbtNovo.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtNovo.addActionListener(formListener);

        jbtExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        jbtExcluir.setText("Excluir");
        jbtExcluir.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtExcluir.addActionListener(formListener);

        jplTitulo2.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloCadastroExame2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloCadastroExame2.setText("Cadastro de Motoristas");

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

        ckAtivo.setSelected(true);
        ckAtivo.setText("Ativo");

        seq.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idcombustivelLabel)
                            .addComponent(nomecombustivelLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtRecarregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ckAtivo)
                            .addComponent(nomeMotoristaField, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(identField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jplTitulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idcombustivelLabel)
                    .addComponent(identField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seq))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomecombustivelLabel)
                    .addComponent(nomeMotoristaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckAtivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSalvar)
                    .addComponent(jbtRecarregar)
                    .addComponent(jbtExcluir)
                    .addComponent(jbtNovo))
                .addContainerGap())
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.MouseListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jbtSalvar) {
                MotoristasGUI.this.jbtSalvarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtRecarregar) {
                MotoristasGUI.this.jbtRecarregarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtNovo) {
                MotoristasGUI.this.jbtNovoActionPerformed(evt);
            }
            else if (evt.getSource() == jbtExcluir) {
                MotoristasGUI.this.jbtExcluirActionPerformed(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == masterTable) {
                MotoristasGUI.this.masterTableMouseClicked(evt);
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
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        } finally {
            jbtNovo.setEnabled(true);
            jbtSalvar.setEnabled(false);
            jbtExcluir.setEnabled(false);
        }
    }//GEN-LAST:event_jbtRecarregarActionPerformed

    private void jbtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExcluirActionPerformed
        int option = JConfirmMessage.showOptionDialog("Atenção", "Deseja realmente excluir o registro?");
        if (option == 0) {
            try {
                if (seq.getText().equals("")) {
                    JConfirmMessage.showMessageDialog("Selecione um registro para excluir !!!", "Atenção");
                    return;
                }
                MotoristasDB.excluirMotorista(Integer.parseInt(seq.getText()));
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
    private boolean verificaCamposObrigatorios() {
        if (identField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Identificador não informado");
            identField.requestFocus();
            return false;
        } else {
            identField.setText(TratamentoValores.preencheCom(identField.getText().trim(), "0", 8, 1, true));
        }
        if (nomeMotoristaField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome não informado");
            nomeMotoristaField.requestFocus();
            return false;
        }

        return true;
    }

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        try {
            if (verificaCamposObrigatorios()) {
                Motorista m = new Motorista();
                if (!seq.getText().trim().equals("")) {
                    m.setSeqMotorista(Integer.valueOf(seq.getText().trim()));
                }

                if (!identField.getText().trim().equals("")) {
                    m.setIdentMotorista(identField.getText().trim());
                }
                m.setNomeMotorista(nomeMotoristaField.getText());

                if (ckAtivo.isSelected()) {
                    m.setSituacao("A");
                } else {
                    m.setSituacao("I");
                }

                if (MotoristasDB.insertOrUpdate(m)) {
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
                Motorista m = MotoristasDB.buscaMotorista(Integer.parseInt(cf.toString()));
                if (m != null) {
                    mostrar(m);
                    enableCampos();
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
    private void carregaGrid() {
        try {
            masterTable.setModel(new MotoristasTableModel());
            masterTable.setAutoCreateRowSorter(false);
            masterTable.setRowHeight(20);
            TableColumnModel columnModel = masterTable.getColumnModel();
            columnModel.getColumn(2).setPreferredWidth(200);

            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);

            getModelCombust().limpar();
            getModelCombust().addListaMotoristas(MotoristasDB.buscaMotoristas(true));
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }

    private void enableCampos() {
        nomeMotoristaField.setEnabled(true);
        identField.setEnabled(true);
        ckAtivo.setEnabled(true);
    }

    private void limpaCampos() {
        seq.setText("");
        identField.setText("");
        nomeMotoristaField.setText("");
        ckAtivo.setSelected(true);
    }

    private void disableCampos() {
        identField.setEnabled(false);
        nomeMotoristaField.setEnabled(false);
        ckAtivo.setEnabled(false);
    }

    public void mostrar(Motorista m) {
        seq.setText(m.getSeqMotorista().toString());
        identField.setText(m.getIdentMotorista());
        nomeMotoristaField.setText(m.getNomeMotorista());
        if (m.getSituacao().equals("A")) {
            ckAtivo.setSelected(true);
        } else {
            ckAtivo.setSelected(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ckAtivo;
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
    private javax.swing.JTextField nomeMotoristaField;
    private javax.swing.JLabel nomecombustivelLabel;
    private javax.swing.JTextField seq;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new MotoristasGUI());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

}
