package view.movimentacao;

import tablemodel.AbastecManualTableModel;
import java.awt.EventQueue;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.TableColumnModel;
import model.*;
import render.*;
import util.*;
import bd.*;
import ctr.GlobalParameter;
import java.io.FileInputStream;
import java.util.Properties;

public class AbastecimentoExterno extends JPanel {

    private AbastecManualTableModel abastecimentosTableModel;
    private String paramMotorista = "N";

    public AbastecimentoExterno() {
        initComponents();
        carregaGrid();
        carregaComboBicos();
        carregaComboVeiculos();
        carregaComboOperadores();
        carregaComboMotoristas();
        comboBico.setRenderer(new BicoListRender());
        comboVeiculo.setRenderer(new VeiculoListRender());
        comboOperador.setRenderer(new OperadorListRender());
        comboMotorista.setRenderer(new MotoristaListRender());
//        identField.setDocument(new FixedLengthUpperDocument(40));
//        nomebicoField.setDocument(new FixedLengthUpperDocument(100));
        jbtRecarregarActionPerformed(null);
        seq.setVisible(false);
        marcadorField.setDocument(new OnlyNumberFieldDocument(6));
        //numeroAbastec.setDocument(new OnlyNumberFieldDocument(20));
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);

            if (properties.getProperty("sis_controla_motorista").trim() != null) {
                if (properties.getProperty("sis_controla_motorista").trim().toUpperCase().equals("S")) {
                    paramMotorista = "S";
                    comboMotorista.setVisible(true);
                    idcombustivelLabel7.setVisible(true);
                } else {
                    paramMotorista = "N";
                    comboMotorista.setVisible(false);
                    idcombustivelLabel7.setVisible(false);
                }
            } else {
                paramMotorista = "N";
                comboMotorista.setVisible(false);
                idcombustivelLabel7.setVisible(false);
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    public AbastecManualTableModel getModelAbastec() {
        if (abastecimentosTableModel == null) {
            abastecimentosTableModel = (AbastecManualTableModel) masterTable.getModel();
        }
        return abastecimentosTableModel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        idcombustivelLabel = new javax.swing.JLabel();
        volumeField = new DecimalFormattedField(DecimalFormattedField.REAL);
        jbtSalvar = new javax.swing.JButton();
        jbtRecarregar = new javax.swing.JButton();
        jbtNovo = new javax.swing.JButton();
        jbtExcluir = new javax.swing.JButton();
        comboBico = new javax.swing.JComboBox();
        jplTitulo2 = new javax.swing.JPanel();
        jlbTituloCadastroExame2 = new javax.swing.JLabel();
        seq = new javax.swing.JTextField();
        idcombustivelLabel1 = new javax.swing.JLabel();
        comboVeiculo = new javax.swing.JComboBox();
        idcombustivelLabel2 = new javax.swing.JLabel();
        comboOperador = new javax.swing.JComboBox();
        idcombustivelLabel3 = new javax.swing.JLabel();
        idcombustivelLabel4 = new javax.swing.JLabel();
        dataField = new org.jdesktop.swingx.JXDatePicker();
        idcombustivelLabel6 = new javax.swing.JLabel();
        horaField = new TimeFormattedField(TimeFormattedField.HORA);
        nomebicoLabel2 = new javax.swing.JLabel();
        marcadorField = new javax.swing.JTextField();
        idcombustivelLabel5 = new javax.swing.JLabel();
        fieldPrecoUnitario = new DecimalFormattedField(DecimalFormattedField.CASASDEC3);
        idcombustivelLabel7 = new javax.swing.JLabel();
        comboMotorista = new javax.swing.JComboBox();

        FormListener formListener = new FormListener();

        masterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        masterTable.addMouseListener(formListener);
        masterScrollPane.setViewportView(masterTable);

        idcombustivelLabel.setText("Estação de Abastecimento");

        jbtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jbtSalvar.setText("Salvar");
        jbtSalvar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtSalvar.setMaximumSize(new java.awt.Dimension(89, 25));
        jbtSalvar.setMinimumSize(new java.awt.Dimension(89, 25));
        jbtSalvar.addActionListener(formListener);

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

        jplTitulo2.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloCadastroExame2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloCadastroExame2.setText("Registro de Abastecimentos Externos");

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

        seq.setText("jTextField1");

        idcombustivelLabel1.setText("Operador");

        idcombustivelLabel2.setText("Veículo");

        idcombustivelLabel3.setText("Volume");

        idcombustivelLabel4.setText("Data");

        idcombustivelLabel6.setText("Hora");

        nomebicoLabel2.setText("Odômetro/Horímetro Atual");

        idcombustivelLabel5.setText("Preço Unitário");

        idcombustivelLabel7.setText("Motorista");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jplTitulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(masterScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtRecarregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(idcombustivelLabel4)
                                        .addGap(89, 89, 89)
                                        .addComponent(idcombustivelLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dataField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(horaField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(comboBico, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(idcombustivelLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(idcombustivelLabel))
                                            .addComponent(comboVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(marcadorField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(nomebicoLabel2)
                                            .addComponent(idcombustivelLabel1)
                                            .addComponent(comboOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(idcombustivelLabel3)
                                                    .addComponent(volumeField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(idcombustivelLabel5)
                                                    .addComponent(fieldPrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idcombustivelLabel7)
                                    .addComponent(comboMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(idcombustivelLabel6)
                                            .addComponent(idcombustivelLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(dataField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(horaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(idcombustivelLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(idcombustivelLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idcombustivelLabel)
                            .addComponent(idcombustivelLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(volumeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idcombustivelLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldPrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idcombustivelLabel2)
                    .addComponent(nomebicoLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(marcadorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtRecarregar)
                    .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.MouseListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jbtSalvar) {
                AbastecimentoExterno.this.jbtSalvarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtRecarregar) {
                AbastecimentoExterno.this.jbtRecarregarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtNovo) {
                AbastecimentoExterno.this.jbtNovoActionPerformed(evt);
            }
            else if (evt.getSource() == jbtExcluir) {
                AbastecimentoExterno.this.jbtExcluirActionPerformed(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == masterTable) {
                AbastecimentoExterno.this.masterTableMouseClicked(evt);
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
                AbastecimentosDB.excluirAbastecimento(Integer.parseInt(seq.getText()));
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
            //numeroAbastec.requestFocus();
            dataField.setDate(new Date());
            Integer hora, minuto;
            String sHora, sMinuto;
            hora = new Date().getHours();
            minuto = new Date().getMinutes();
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
            String sData = sHora + ":" + sMinuto;
            horaField.setText(sData);
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
        if (dataField.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Data não informada");
            dataField.requestFocus();
            return false;
        }

        if ((horaField.getText().trim().isEmpty()) || (horaField.getText().trim().equals("00:00"))) {
            JOptionPane.showMessageDialog(null, "Hora não informada");
            horaField.requestFocus();
            return false;
        }

//        if (numeroAbastec.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Número do abastecimento não informado");
//            numeroAbastec.requestFocus();
//            return false;
//        }
        if (comboOperador.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Operador não informado");
            comboOperador.requestFocus();
            return false;
        }

        if (paramMotorista.equals("S")) {
            if (comboMotorista.getSelectedIndex() ==0) {
                JOptionPane.showMessageDialog(null, "Motorista não informado");
                comboMotorista.requestFocus();
                return false;
            }
        }

        if (comboBico.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Estação de abastecimento não informada");
            comboBico.requestFocus();
            return false;
        }

        if ((volumeField.getText().trim().isEmpty()) || (volumeField.getText().trim().equals(FormatacaoDados.DoubleFormat(0)))) {
            JOptionPane.showMessageDialog(null, "Volume não informado");
            volumeField.requestFocus();
            return false;
        }
        if ((fieldPrecoUnitario.getText().trim().isEmpty()) || (fieldPrecoUnitario.getText().trim().equals("0,000"))) {
            JOptionPane.showMessageDialog(null, "Preço Unitário não informado");
            fieldPrecoUnitario.requestFocus();
            return false;
        }
//        if ((encerranteField.getText().trim().isEmpty()) || (encerranteField.getText().trim().equals(FormatacaoDados.DoubleFormat(0)))) {
//            JOptionPane.showMessageDialog(null, "Encerrante não informado");
//            encerranteField.requestFocus();
//            return false;
//        }
        if (comboVeiculo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Veículo não informado");
            comboVeiculo.requestFocus();
            return false;
        }

        if (marcadorField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Odômetro/Horímetro não informado");
            marcadorField.requestFocus();
            return false;
        }

        return true;
    }

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        try {
            if (verificaCamposObrigatorios()) {
                Abastecimento a = new Abastecimento();
                Double encerranteAnterior = 0.0;
                if (!seq.getText().trim().equals("")) {
                    a.setIdAbastecimento(Integer.valueOf(seq.getText().trim()));
                }
                a.setBico((Bico) comboBico.getModel().getSelectedItem());
                a.setVeiculo((Veiculo) comboVeiculo.getModel().getSelectedItem());
                a.setOperador((Operador) comboOperador.getModel().getSelectedItem());
                if (paramMotorista.equals("S")) {
                    a.setMotorista((Motorista) comboMotorista.getModel().getSelectedItem());
                } else {
                    a.setMotorista(null);
                }
                //a.setNumeroAbastecimento(Integer.parseInt(numeroAbastec.getText()));
                a.setStringCBC("");
                a.setVolume(Double.valueOf(volumeField.getText().replace(",", ".")));
                a.setPrecoUnitario(Double.valueOf(fieldPrecoUnitario.getText().replace(",", ".")));
                //a.setEncerranteLitros(Double.valueOf(encerranteField.getText().replace(",", ".")));
                Timestamp t = new Timestamp(dataField.getDate().getTime());
                t.setHours(Integer.parseInt(horaField.getText().substring(0, 2)));
                t.setMinutes(Integer.parseInt(horaField.getText().substring(3, 5)));
                a.setDataHora(t);
                Long anterior;
                anterior = VeiculosDB.buscaHodometroHorimetroAnterior(a.getVeiculo(), a.getDataHora());
                encerranteAnterior = AbastecimentosDB.buscaEncerranteAnterior((Bico) comboBico.getModel().getSelectedItem(), t);
                //Preco p = PrecosDB.buscaUltimoPreco((Bico) comboBico.getModel().getSelectedItem());
                a.setPrecoUnitario(Double.valueOf(fieldPrecoUnitario.getText().replace(",", ".")));
                a.setTotalPagar(a.getPrecoUnitario() * Double.valueOf(volumeField.getText().replace(",", ".")));

                if (marcadorField.getText().trim().equals("")) {
                    marcadorField.setText("0");
                }
                a.setEncerranteAnterior(encerranteAnterior);
                switch (a.getVeiculo().getTipo()) {
                    case "O":
                        a.setHodometro(Long.valueOf(marcadorField.getText().replace(",", ".")));
                        a.setHodometroAnterior(anterior);
                        if ((anterior == 0) || ((Long.valueOf(marcadorField.getText()) - anterior) / a.getVolume() < 0)) {
                        } else if (a.getVolume() == 0) {
                            a.setKmMedio(0.0);
                        } else {
                            a.setKmMedio((Long.valueOf(marcadorField.getText()) - anterior) / a.getVolume());
                        }
                        break;
                    case "H":
                        a.setHorimetro(Long.valueOf(marcadorField.getText().replace(",", ".")));
                        a.setHorimetroAnterior(anterior);
                        if ((anterior == 0) || ((Long.valueOf(marcadorField.getText()) - anterior) / a.getVolume() < 0)) {
                        } else if (a.getVolume() == 0) {
                            a.setTempoMedio(0.0);
                        } else {
                            a.setTempoMedio((Long.valueOf(marcadorField.getText()) - anterior) / a.getVolume());
                        }
                        break;
                }

                if (AbastecimentosDB.insertOrUpdateAbastecimentoManual(a, false, "E")) {
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
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção!!!");
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    private void masterTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterTableMouseClicked
        if (evt.getClickCount() == 2) {
            int selected = masterTable.getSelectedRow();
            Object cf = masterTable.getValueAt(selected, 0);
            try {
                Abastecimento abastec = AbastecimentosDB.buscaAbastecimento(Integer.parseInt(cf.toString()));
                if (abastec != null) {
                    mostrar(abastec);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboBico;
    private javax.swing.JComboBox comboMotorista;
    private javax.swing.JComboBox comboOperador;
    private javax.swing.JComboBox comboVeiculo;
    private org.jdesktop.swingx.JXDatePicker dataField;
    private javax.swing.JTextField fieldPrecoUnitario;
    private javax.swing.JFormattedTextField horaField;
    private javax.swing.JLabel idcombustivelLabel;
    private javax.swing.JLabel idcombustivelLabel1;
    private javax.swing.JLabel idcombustivelLabel2;
    private javax.swing.JLabel idcombustivelLabel3;
    private javax.swing.JLabel idcombustivelLabel4;
    private javax.swing.JLabel idcombustivelLabel5;
    private javax.swing.JLabel idcombustivelLabel6;
    private javax.swing.JLabel idcombustivelLabel7;
    private javax.swing.JButton jbtExcluir;
    private javax.swing.JButton jbtNovo;
    private javax.swing.JButton jbtRecarregar;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JLabel jlbTituloCadastroExame2;
    private javax.swing.JPanel jplTitulo2;
    private javax.swing.JTextField marcadorField;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JLabel nomebicoLabel2;
    private javax.swing.JTextField seq;
    private javax.swing.JTextField volumeField;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new AbastecimentoExterno());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

    private void carregaGrid() {
        try {
            masterTable.setModel(new AbastecManualTableModel());
            masterTable.setAutoCreateRowSorter(false);
            masterTable.setRowHeight(20);
            TableColumnModel columnModel = masterTable.getColumnModel();
            BicoTableCellRender bicoRender = new BicoTableCellRender();
            VeiculoTableCellRender veiculoRender = new VeiculoTableCellRender();
            OperadorTableCellRender operadorRender = new OperadorTableCellRender();

            FrotaTableCellRender frotaRender = new FrotaTableCellRender();
            EmpresaTableCellRender empresaRender = new EmpresaTableCellRender();
            TimestampTableRender timestampRenderer = new TimestampTableRender();
            columnModel.getColumn(1).setCellRenderer(timestampRenderer);
            columnModel.getColumn(2).setCellRenderer(bicoRender);
            columnModel.getColumn(4).setCellRenderer(veiculoRender);
            columnModel.getColumn(5).setCellRenderer(frotaRender);
            columnModel.getColumn(6).setCellRenderer(empresaRender);
            columnModel.getColumn(7).setCellRenderer(operadorRender);

            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);

            getModelAbastec().limpar();
            getModelAbastec().addListaAbastecimentos(AbastecimentosDB.buscaAbastecimentosManual("E"));
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }

    private void enableCampos() {
        dataField.setEnabled(true);
        horaField.setEnabled(true);
        //numeroAbastec.setEnabled(true);
        volumeField.setEnabled(true);
        fieldPrecoUnitario.setEnabled(true);
        //encerranteField.setEnabled(true);
        marcadorField.setEnabled(true);
        comboBico.setEnabled(true);
        comboVeiculo.setEnabled(true);
        comboOperador.setEnabled(true);
        comboMotorista.setEnabled(true);
    }

    private void limpaCampos() {
        seq.setText("");
        dataField.setDate(null);
        horaField.setText("");
        //numeroAbastec.setText("");
        volumeField.setText(FormatacaoDados.DoubleFormat(0));
        fieldPrecoUnitario.setText("0,000");
        //encerranteField.setText(FormatacaoDados.DoubleFormat(0));
        marcadorField.setText("");
        comboBico.setSelectedIndex(0);
        comboVeiculo.setSelectedIndex(0);
        comboOperador.setSelectedIndex(0);
        comboMotorista.setSelectedIndex(0);

        if (comboBico.getItemCount() == 2) {
            comboBico.setSelectedIndex(1);
        } else {
            comboBico.setSelectedIndex(0);
        }

        if (comboVeiculo.getItemCount() == 2) {
            comboVeiculo.setSelectedIndex(1);
        } else {
            comboVeiculo.setSelectedIndex(0);
        }
        if (comboOperador.getItemCount() == 2) {
            comboOperador.setSelectedIndex(1);
        } else {
            comboOperador.setSelectedIndex(0);
        }
        if (comboMotorista.getItemCount() == 2) {
            comboMotorista.setSelectedIndex(1);
        } else {
            comboMotorista.setSelectedIndex(0);
        }
    }

    private void disableCampos() {
        dataField.setEnabled(false);
        horaField.setEnabled(false);
        //numeroAbastec.setEnabled(false);
        volumeField.setEnabled(false);
        fieldPrecoUnitario.setEnabled(false);
        //encerranteField.setEnabled(false);
        marcadorField.setEnabled(false);
        comboBico.setEnabled(false);
        comboVeiculo.setEnabled(false);
        comboOperador.setEnabled(false);
        comboMotorista.setEnabled(false);
    }

    public void mostrar(Abastecimento abastec) {
        seq.setText(abastec.getIdAbastecimento().toString());

        dataField.setDate(new Date(abastec.getDataHora().getTime()));
        Timestamp sTime = abastec.getDataHora();

        Integer hora, minuto;
        String sHora, sMinuto;

        hora = sTime.getHours();
        minuto = sTime.getMinutes();

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

        horaField.setText(sHora + ":" + sMinuto);

        //numeroAbastec.setText(abastec.getNumeroAbastecimento().toString());
        //volumeField.setText(abastec.getVolume().toString().replace(".", ","));
        //encerranteField.setText(abastec.getEncerranteLitros().toString().replace(".", ","));
        volumeField.setText(FormatacaoDados.DoubleFormat(abastec.getVolume()));
        fieldPrecoUnitario.setText(String.format("%.3f", abastec.getPrecoUnitario()));
        //encerranteField.setText(FormatacaoDados.DoubleFormat(abastec.getEncerranteLitros()));

        if (abastec.getVeiculo().getTipo().equals("O")) {
            marcadorField.setText(abastec.getHodometro().toString());
        } else {
            marcadorField.setText(abastec.getHorimetro().toString());
        }

        comboBico.getModel().setSelectedItem(abastec.getBico());
        for (int i = 1; i < comboBico.getItemCount(); i++) {
            if (((Bico) comboBico.getItemAt(i)).getSeqBico().equals(abastec.getBico().getSeqBico())) {
                comboBico.setSelectedIndex(i);
            }
        }

        comboVeiculo.getModel().setSelectedItem(abastec.getVeiculo());
        for (int i = 1; i < comboVeiculo.getItemCount(); i++) {
            if (((Veiculo) comboVeiculo.getItemAt(i)).getSeqVeiculo().equals(abastec.getVeiculo().getSeqVeiculo())) {
                comboVeiculo.setSelectedIndex(i);
            }
        }

        comboOperador.getModel().setSelectedItem(abastec.getOperador());
        for (int i = 1; i < comboOperador.getItemCount(); i++) {
            if (((Operador) comboOperador.getItemAt(i)).getSeqOperador().equals(abastec.getOperador().getSeqOperador())) {
                comboOperador.setSelectedIndex(i);
            }
        }

        if (paramMotorista.equals("S")) {
            comboMotorista.getModel().setSelectedItem(abastec.getMotorista());
            for (int i = 1; i < comboMotorista.getItemCount(); i++) {
                if (((Motorista) comboMotorista.getItemAt(i)).getSeqMotorista().equals(abastec.getMotorista().getSeqMotorista())) {
                    comboMotorista.setSelectedIndex(i);
                }
            }
        }

    }

    private void carregaComboVeiculos() {
        comboVeiculo.removeAllItems();
        comboVeiculo.addItem("Selecione");
        Iterator iterator = VeiculosDB.buscaVeiculos(false).iterator();
        while (iterator.hasNext()) {
            Veiculo v = (Veiculo) iterator.next();
            comboVeiculo.addItem(v);
        }
    }

    private void carregaComboOperadores() {
        comboOperador.removeAllItems();
        comboOperador.addItem("Selecione");
        Iterator iterator = OperadoresDB.buscaTodosOperadores(false).iterator();
        while (iterator.hasNext()) {
            Operador o = (Operador) iterator.next();
            comboOperador.addItem(o);
        }
    }

    private void carregaComboBicos() {
        comboBico.removeAllItems();
        comboBico.addItem("Selecione");
        Iterator iterator = BicosDB.buscaBicosExternos(false).iterator();
        while (iterator.hasNext()) {
            Bico b = (Bico) iterator.next();
            comboBico.addItem(b);
        }
    }

    private void carregaComboMotoristas() {
        comboMotorista.removeAllItems();
        comboMotorista.addItem("Selecione");
        Iterator iterator = MotoristasDB.buscaMotoristas(false).iterator();
        while (iterator.hasNext()) {
            Motorista m = (Motorista) iterator.next();
            comboMotorista.addItem(m);
        }
    }
}
