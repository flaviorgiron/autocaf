package view.cadastros;

import bd.EmpresasDB;
import bd.OperadoresDB;
import java.awt.EventQueue;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import model.Empresa;
import model.Operador;
import render.EmpresaListRender;
import util.FixedLengthUpperDocument;
import ctr.GlobalParameter;
import tablemodel.OperadoresTableModel;
import util.GeraLog;
import util.JConfirmMessage;

public class OperadoresGUI extends JPanel {

    private OperadoresTableModel combustiveisTableModel;

    public OperadoresGUI() {
        initComponents();
        carregaGrid();
        carregaComboEmpresas();
        jComboBox1.setRenderer(new EmpresaListRender());

        identField.setDocument(new FixedLengthUpperDocument(16));
        nomeoperadorField.setDocument(new FixedLengthUpperDocument(100));
        loginField.setDocument(new FixedLengthUpperDocument(40));
        senhaField.setDocument(new FixedLengthUpperDocument(40));
        jbtRecarregarActionPerformed(null);
        seq.setVisible(false);

        masterTable.setAutoCreateRowSorter(true);
    }

    public OperadoresTableModel getModelCombust() {
        if (combustiveisTableModel == null) {
            combustiveisTableModel = (OperadoresTableModel) masterTable.getModel();
        }
        return combustiveisTableModel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        idcombustivelLabel = new javax.swing.JLabel();
        nomecombustivelLabel = new javax.swing.JLabel();
        identField = new javax.swing.JTextField();
        nomeoperadorField = new javax.swing.JTextField();
        jbtSalvar = new javax.swing.JButton();
        jbtRecarregar = new javax.swing.JButton();
        jbtNovo = new javax.swing.JButton();
        jbtExcluir = new javax.swing.JButton();
        jplTitulo2 = new javax.swing.JPanel();
        jlbTituloCadastroExame2 = new javax.swing.JLabel();
        idcombustivelLabel1 = new javax.swing.JLabel();
        loginField = new javax.swing.JTextField();
        idcombustivelLabel2 = new javax.swing.JLabel();
        senhaField = new javax.swing.JPasswordField();
        ckAdmin = new javax.swing.JCheckBox();
        seq = new javax.swing.JTextField();
        idcombustivelLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        ckAtivo = new javax.swing.JCheckBox();
        ckMaster = new javax.swing.JCheckBox();

        FormListener formListener = new FormListener();

        setMaximumSize(null);

        masterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        masterTable.addMouseListener(formListener);
        masterScrollPane.setViewportView(masterTable);

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
        jlbTituloCadastroExame2.setText("Cadastro de Operadores");

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

        idcombustivelLabel1.setText("Login");

        idcombustivelLabel2.setText("Senha");

        ckAdmin.setText("Administrador do sistema");
        ckAdmin.setMargin(new java.awt.Insets(0, 0, 0, 0));
        ckAdmin.addMouseListener(formListener);

        seq.setText("jTextField1");

        idcombustivelLabel3.setText("Empresa");

        ckAtivo.setSelected(true);
        ckAtivo.setText("Ativo");
        ckAtivo.addActionListener(formListener);

        ckMaster.setText("Master");
        ckMaster.setMargin(new java.awt.Insets(0, 0, 0, 0));
        ckMaster.addMouseListener(formListener);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addComponent(jplTitulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idcombustivelLabel)
                                    .addComponent(nomecombustivelLabel)
                                    .addComponent(idcombustivelLabel1)
                                    .addComponent(idcombustivelLabel2))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(senhaField, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                                        .addComponent(nomeoperadorField))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(identField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ckAdmin))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(idcombustivelLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jbtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtRecarregar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ckAtivo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ckMaster)))))
                        .addGap(0, 256, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(senhaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idcombustivelLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(identField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idcombustivelLabel)
                            .addComponent(ckAdmin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomeoperadorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomecombustivelLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idcombustivelLabel1)
                            .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcombustivelLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckAtivo)
                    .addComponent(ckMaster))
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
                OperadoresGUI.this.jbtSalvarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtRecarregar) {
                OperadoresGUI.this.jbtRecarregarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtNovo) {
                OperadoresGUI.this.jbtNovoActionPerformed(evt);
            }
            else if (evt.getSource() == jbtExcluir) {
                OperadoresGUI.this.jbtExcluirActionPerformed(evt);
            }
            else if (evt.getSource() == ckAtivo) {
                OperadoresGUI.this.ckAtivoActionPerformed(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == masterTable) {
                OperadoresGUI.this.masterTableMouseClicked(evt);
            }
            else if (evt.getSource() == ckAdmin) {
                OperadoresGUI.this.ckAdminMouseClicked(evt);
            }
            else if (evt.getSource() == ckMaster) {
                OperadoresGUI.this.ckMasterMouseClicked(evt);
            }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == ckAdmin) {
                OperadoresGUI.this.ckAdminMouseReleased(evt);
            }
            else if (evt.getSource() == ckMaster) {
                OperadoresGUI.this.ckMasterMouseReleased(evt);
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
                OperadoresDB.excluirOperador(Integer.parseInt(seq.getText()));
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
//        if (identField.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Identificador não informado");
//            identField.requestFocus();
//            return false;
//        }
        if (nomeoperadorField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome não informado");
            nomeoperadorField.requestFocus();
            return false;
        }
        if (ckAdmin.isSelected()) {
            if (loginField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Login não informado");
                loginField.requestFocus();
                return false;
            }
        }
        if (ckAdmin.isSelected()) {
            if (senhaField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Senha não informada");
                senhaField.requestFocus();
                return false;
            }
        }
        if (jComboBox1.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Empresa não informada");
            jComboBox1.requestFocus();
            return false;
        }
        Integer cod = 0;
        if (!seq.getText().trim().equals("")) {
            cod = Integer.valueOf(seq.getText().trim());
        }
        if (!loginField.getText().trim().equals("")) {
            if (OperadoresDB.consultaLogin(loginField.getText().trim(), cod) != null) {
                JOptionPane.showMessageDialog(null, "Operador já cadastrado (Login)");
                loginField.requestFocus();
                return false;
            }
        }
        return true;
    }

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        try {
            if (verificaCamposObrigatorios()) {
                Operador o = new Operador();
                if (!seq.getText().trim().equals("")) {
                    o.setSeqOperador(Integer.valueOf(seq.getText().trim()));
                    if (Integer.valueOf(seq.getText().trim()).equals(GlobalParameter.getInstance().getOperador().getSeqOperador())) {
                        GlobalParameter.getInstance().getOperador().setNomeOperador(nomeoperadorField.getText().trim());
                    }
                }

                if (!identField.getText().trim().equals("")) {
                    o.setIdentOperador(identField.getText().trim());
                } else {
                    o.setIdentOperador("");
                }

                o.setEmpresa((Empresa) jComboBox1.getModel().getSelectedItem());
                o.setNomeOperador(nomeoperadorField.getText().trim());
                o.setLogin(loginField.getText().trim());
                o.setSenha(senhaField.getText().trim());

                if (ckAdmin.isSelected()) {
                    o.setIsOperador("S");
                } else {
                    o.setIsOperador("N");
                }

                if (ckAtivo.isSelected()) {
                    o.setSituacao("A");
                } else {
                    o.setSituacao("I");
                }

                if (ckMaster.isSelected()) {
                    o.setIsMaster("S");
                } else {
                    o.setIsMaster("N");
                }

                if (OperadoresDB.insertOrUpdate(o)) {
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
                Operador c = OperadoresDB.buscaOperador(Integer.parseInt(cf.toString()));
                if (c != null) {
                    mostrar(c);
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

    private void ckAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckAtivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckAtivoActionPerformed

    private void ckAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckAdminMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ckAdminMouseClicked

    private void ckAdminMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckAdminMouseReleased
        if (ckAdmin.isSelected()) {
            loginField.setVisible(true);
        } else {
            loginField.setVisible(false);
        }
    }//GEN-LAST:event_ckAdminMouseReleased

    private void ckMasterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckMasterMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ckMasterMouseClicked

    private void ckMasterMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckMasterMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ckMasterMouseReleased
    private void carregaGrid() {
        try {
            masterTable.setModel(new OperadoresTableModel());
            masterTable.setAutoCreateRowSorter(false);
            masterTable.setRowHeight(20);
            TableColumnModel columnModel = masterTable.getColumnModel();
            columnModel.getColumn(2).setPreferredWidth(200);

            DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
            esquerda.setHorizontalAlignment(SwingConstants.LEFT);
            columnModel.getColumn(1).setCellRenderer(esquerda);
            columnModel.getColumn(2).setCellRenderer(esquerda);
            columnModel.getColumn(3).setCellRenderer(esquerda);
            columnModel.getColumn(4).setCellRenderer(esquerda);
            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);
            getModelCombust().limpar();
            getModelCombust().addListaOperadores(OperadoresDB.buscaTodosOperadores(true));

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }

    }

    private void enableCampos() {
        identField.setEnabled(true);
        nomeoperadorField.setEnabled(true);
        loginField.setEnabled(true);
        senhaField.setEnabled(true);
        ckAdmin.setEnabled(true);
        jComboBox1.setEnabled(true);
        ckAtivo.setEnabled(true);
        ckMaster.setEnabled(true);
    }

    private void limpaCampos() {
        seq.setText("");
        identField.setText("");
        nomeoperadorField.setText("");
        loginField.setText("");
        senhaField.setText("");
        ckAdmin.setSelected(false);
        ckAtivo.setSelected(true);
        ckMaster.setSelected(false);

        if (jComboBox1.getItemCount() == 1) {
            jComboBox1.setSelectedIndex(0);
        } else {
            jComboBox1.setSelectedIndex(-1);
        }
    }

    private void disableCampos() {
        identField.setEnabled(false);
        nomeoperadorField.setEnabled(false);
        loginField.setEnabled(false);
        senhaField.setEnabled(false);
        ckAdmin.setEnabled(false);
        jComboBox1.setEnabled(false);
        ckAtivo.setEnabled(false);
        ckMaster.setEnabled(false);
    }

    public void mostrar(Operador c) {
        seq.setText(c.getSeqOperador().toString());
        identField.setText(c.getIdentOperador());
        nomeoperadorField.setText(c.getNomeOperador());
        loginField.setText(c.getLogin());
        senhaField.setText(c.getSenha());
        if (c.getIsOperador().equals("S")) {
            ckAdmin.setSelected(true);
        } else {
            ckAdmin.setSelected(false);
        }

        if (c.getSituacao().equals("A")) {
            ckAtivo.setSelected(true);
        } else {
            ckAtivo.setSelected(false);
        }

        if (ckAdmin.isSelected()) {
            loginField.setVisible(true);
        } else {
            loginField.setVisible(false);
        }

        if (c.getIsMaster().equals("S")) {
            ckMaster.setSelected(true);
        } else {
            ckMaster.setSelected(false);
        }

        jComboBox1.getModel().setSelectedItem(c.getEmpresa());
        for (int i = 0; i < jComboBox1.getItemCount(); i++) {
            if (((Empresa) jComboBox1.getItemAt(i)).getSeqEmpresa().equals(c.getEmpresa().getSeqEmpresa())) {
                jComboBox1.setSelectedIndex(i);
            }
        }
        identField.requestFocus();
    }

    private void carregaComboEmpresas() {
        jComboBox1.removeAllItems();
        Iterator iterator = EmpresasDB.buscaEmpresas(false).iterator();
        while (iterator.hasNext()) {
            Empresa e = (Empresa) iterator.next();
            jComboBox1.addItem(e);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ckAdmin;
    private javax.swing.JCheckBox ckAtivo;
    private javax.swing.JCheckBox ckMaster;
    private javax.swing.JLabel idcombustivelLabel;
    private javax.swing.JLabel idcombustivelLabel1;
    private javax.swing.JLabel idcombustivelLabel2;
    private javax.swing.JLabel idcombustivelLabel3;
    private javax.swing.JTextField identField;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JButton jbtExcluir;
    private javax.swing.JButton jbtNovo;
    private javax.swing.JButton jbtRecarregar;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JLabel jlbTituloCadastroExame2;
    private javax.swing.JPanel jplTitulo2;
    private javax.swing.JTextField loginField;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JLabel nomecombustivelLabel;
    private javax.swing.JTextField nomeoperadorField;
    private javax.swing.JPasswordField senhaField;
    private javax.swing.JTextField seq;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new OperadoresGUI());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
