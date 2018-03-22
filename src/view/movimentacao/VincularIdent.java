package view.movimentacao;

import bd.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import model.*;
import render.*;
import util.*;
import tablemodel.VincularIdentTableModel;

public class VincularIdent extends javax.swing.JDialog {

    private VincularIdentTableModel vincularIdentTableModel;

    public VincularIdentTableModel getModelAbastec() {
        if (vincularIdentTableModel == null) {
            vincularIdentTableModel = (VincularIdentTableModel) masterTable.getModel();
        }
        return vincularIdentTableModel;
    }

    public VincularIdent(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jLabel1.setVisible(false);
        seq.setVisible(false);

        jdcDataInicial.setDate(new Date());
        jdcDataFinal.setDate(new Date());

        carregaComboBicos();
        carregaComboOperadores();
        carregaVeiculos();

        comboVeiculo.setRenderer(new VeiculoListRender());
        comboBico.setRenderer(new BicoListRender());
        comboOperador.setRenderer(new OperadorListRender());

        desabilitaCampos();

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
        setIconImage(icon.getImage());
    }

    private void carregaComboOperadores() {
        comboOperador.removeAllItems();
        //comboOperador.addItem("Selecione");
        Iterator iterator = OperadoresDB.buscaTodosOperadores(false).iterator();
        while (iterator.hasNext()) {
            Operador o = (Operador) iterator.next();
            comboOperador.addItem(o);
        }
    }

    private void carregaComboBicos() {
        comboBico.removeAllItems();
        //comboBico.addItem("Selecione");
        Iterator iterator = BicosDB.buscaBicosInternos(false).iterator();
        while (iterator.hasNext()) {
            Bico b = (Bico) iterator.next();
            comboBico.addItem(b);
        }
    }

    private void carregaVeiculos() {
        comboVeiculo.removeAllItems();
        //comboVeiculo.addItem("Selecione");
        Iterator iterator = VeiculosDB.buscaVeiculos(false).iterator();
        while (iterator.hasNext()) {
            Veiculo v = (Veiculo) iterator.next();
            comboVeiculo.addItem(v);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplTitulo = new javax.swing.JPanel();
        jlbTituloJanela = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jplPeriodo = new javax.swing.JPanel();
        jdcDataInicial = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        jlbDataInicial = new javax.swing.JLabel();
        jdcDataFinal = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        jlbDataFinal = new javax.swing.JLabel();
        jbtVisualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        seq = new javax.swing.JTextField();
        idcombustivelLabel4 = new javax.swing.JLabel();
        dataField = new org.jdesktop.swingx.JXDatePicker();
        idcombustivelLabel = new javax.swing.JLabel();
        comboBico = new javax.swing.JComboBox();
        idcombustivelLabel2 = new javax.swing.JLabel();
        comboVeiculo = new javax.swing.JComboBox();
        idcombustivelLabel3 = new javax.swing.JLabel();
        volumeField = new DecimalFormattedField(DecimalFormattedField.REAL);
        idcombustivelLabel6 = new javax.swing.JLabel();
        horaField = new TimeFormattedField(TimeFormattedField.HORA);
        idcombustivelLabel5 = new javax.swing.JLabel();
        numeroAbastec = new javax.swing.JTextField();
        idcombustivelLabel1 = new javax.swing.JLabel();
        comboOperador = new javax.swing.JComboBox();
        jbtSalvar = new javax.swing.JButton();
        idcombustivelLabel9 = new javax.swing.JLabel();
        idcombustivelLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vincular Identificadores");

        jplTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloJanela.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloJanela.setText("Vincular Identificadores");

        javax.swing.GroupLayout jplTituloLayout = new javax.swing.GroupLayout(jplTitulo);
        jplTitulo.setLayout(jplTituloLayout);
        jplTituloLayout.setHorizontalGroup(
            jplTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTituloJanela)
                .addContainerGap(383, Short.MAX_VALUE))
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

        jbtVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisa.png"))); // NOI18N
        jbtVisualizar.setText("Pesquisar");
        jbtVisualizar.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jbtVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplPeriodoLayout = new javax.swing.GroupLayout(jplPeriodo);
        jplPeriodo.setLayout(jplPeriodoLayout);
        jplPeriodoLayout.setHorizontalGroup(
            jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbDataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jplPeriodoLayout.createSequentialGroup()
                        .addComponent(jdcDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplPeriodoLayout.setVerticalGroup(
            jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtVisualizar)
                    .addGroup(jplPeriodoLayout.createSequentialGroup()
                        .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbDataInicial))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbDataFinal)
                            .addComponent(jdcDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        masterTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(masterTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Duplo clique para selecionar e alterar o registro.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 232, Short.MAX_VALUE))
                    .addComponent(jplPeriodo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pesquisa", jPanel1);

        idcombustivelLabel4.setText("Data");

        idcombustivelLabel.setText("Estação de Abastecimento");

        idcombustivelLabel2.setText("Veículo");

        comboVeiculo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboVeiculoItemStateChanged(evt);
            }
        });

        idcombustivelLabel3.setText("Volume");

        volumeField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        volumeField.setText("0");

        idcombustivelLabel6.setText("Hora");

        horaField.setText("00:00");

        idcombustivelLabel5.setText("Número");

        numeroAbastec.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        numeroAbastec.setText("0");

        idcombustivelLabel1.setText("Operador");

        jbtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        jbtSalvar.setText("Salvar");
        jbtSalvar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtSalvar.setMaximumSize(new java.awt.Dimension(89, 25));
        jbtSalvar.setMinimumSize(new java.awt.Dimension(89, 25));
        jbtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalvarActionPerformed(evt);
            }
        });

        idcombustivelLabel9.setText("Identificador 1");

        idcombustivelLabel10.setText("Identificador 2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(comboBico, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idcombustivelLabel)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(idcombustivelLabel9)
                                        .addGap(97, 97, 97)
                                        .addComponent(idcombustivelLabel1))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(idcombustivelLabel4)
                                                .addGap(89, 89, 89)
                                                .addComponent(idcombustivelLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(dataField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(horaField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(idcombustivelLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(numeroAbastec, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(idcombustivelLabel3)
                                            .addComponent(volumeField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 122, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboOperador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idcombustivelLabel10)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboVeiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(idcombustivelLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idcombustivelLabel6)
                            .addComponent(idcombustivelLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dataField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(horaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(idcombustivelLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numeroAbastec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(idcombustivelLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(volumeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idcombustivelLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(idcombustivelLabel9)
                                    .addComponent(idcombustivelLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(comboOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(idcombustivelLabel10)
                        .addGap(3, 3, 3)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(idcombustivelLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(260, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Abastecimento", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jplTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVisualizarActionPerformed
        if (jdcDataInicial.getDate() == null) {
            JConfirmMessage.showMessageDialog("Data inicial não informada.", "Atenção");
            return;
        }
        if (jdcDataFinal.getDate() == null) {
            JConfirmMessage.showMessageDialog("Data final não informada.", "Atenção");
            return;
        }
        if (jdcDataInicial.getDate().after(jdcDataFinal.getDate())) {
            JConfirmMessage.showMessageDialog("Data inicial maior que data final.", "Atenção");
            return;
        }
        try {
            carregaGrid();
        } catch (Exception e) {
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
}//GEN-LAST:event_jbtVisualizarActionPerformed

    private void carregaGrid() {
        try {
            masterTable.setModel(new VincularIdentTableModel());
            masterTable.setAutoCreateRowSorter(false);
            masterTable.setRowHeight(20);
            TableColumnModel columnModel = masterTable.getColumnModel();
            BicoTableCellRender bicoRender = new BicoTableCellRender();
            TimestampTableRender timestampRenderer = new TimestampTableRender();

            columnModel.getColumn(1).setCellRenderer(timestampRenderer);
            columnModel.getColumn(2).setCellRenderer(bicoRender);

            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);

            columnModel.getColumn(2).setPreferredWidth(200);

            getModelAbastec().limpar();

            getModelAbastec().addListaAbastecimentos(AbastecimentosDB.buscaAbastecimento(jdcDataInicial.getDate(), jdcDataFinal.getDate()));
            if (getModelAbastec().getRowCount() > 0) {
                jLabel1.setVisible(true);
            } else {
                jLabel1.setVisible(false);
            }

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }
    private void masterTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterTableMouseClicked
        if (evt.getClickCount() == 2) {
            int selected = masterTable.getSelectedRow();
            Object cf = masterTable.getValueAt(selected, 0);
            if (cf != null) {
                Abastecimento a = AbastecimentosDB.buscaAbastecimento(Integer.parseInt(cf.toString()));
                jTabbedPane1.setSelectedIndex(1);
                mostrar(a);
            }
        }
    }//GEN-LAST:event_masterTableMouseClicked

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

        numeroAbastec.setText(abastec.getNumeroAbastecimento().toString());
        volumeField.setText(FormatacaoDados.DoubleFormat(abastec.getVolume()));

        comboBico.getModel().setSelectedItem(abastec.getBico());
        for (int i = 0; i < comboBico.getItemCount(); i++) {
            if (((Bico) comboBico.getItemAt(i)).getSeqBico().equals(abastec.getBico().getSeqBico())) {
                comboBico.setSelectedIndex(i);
            }
        }

        comboVeiculo.getModel().setSelectedItem(abastec.getVeiculo());
        for (int i = 0; i < comboVeiculo.getItemCount(); i++) {
            if (((Veiculo) comboVeiculo.getItemAt(i)).getSeqVeiculo().equals(abastec.getVeiculo().getSeqVeiculo())) {
                comboVeiculo.setSelectedIndex(i);
            }
        }

        comboOperador.getModel().setSelectedItem(abastec.getOperador());
        for (int i = 0; i < comboOperador.getItemCount(); i++) {
            if (((Operador) comboOperador.getItemAt(i)).getSeqOperador().equals(abastec.getOperador().getSeqOperador())) {
                comboOperador.setSelectedIndex(i);
            }
        }

        jTextField1.setText(abastec.getIdent1());
        jTextField2.setText(abastec.getIdent2());
    }

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        if (seq.getText().trim().equals("")) {
            return;
        }
        try {
            if (verificaCamposObrigatorios()) {
                int option = JConfirmMessage.showOptionDialog("Atençãos", "Confirma o vínculo dos identificadores com os cadastros informados?");
                if (option == 0) {

                    Veiculo v = (Veiculo) comboVeiculo.getModel().getSelectedItem();
                    Operador o = (Operador) comboOperador.getModel().getSelectedItem();
                    OperadoresDB.atualizaIdent(o, jTextField1.getText().trim());
                    VeiculosDB.atualizaIdent(v, jTextField2.getText().trim());
                    AbastecimentosDB.atualizaIdentOperador(o, jTextField1.getText().trim());
                    AbastecimentosDB.atualizaIdentVeiculo(v, jTextField2.getText().trim());
                    jTabbedPane1.setSelectedIndex(0);
                    jbtVisualizar.doClick();
                }
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    private void comboVeiculoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboVeiculoItemStateChanged

    }//GEN-LAST:event_comboVeiculoItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboBico;
    private javax.swing.JComboBox comboOperador;
    private javax.swing.JComboBox comboVeiculo;
    private org.jdesktop.swingx.JXDatePicker dataField;
    private javax.swing.JFormattedTextField horaField;
    private javax.swing.JLabel idcombustivelLabel;
    private javax.swing.JLabel idcombustivelLabel1;
    private javax.swing.JLabel idcombustivelLabel10;
    private javax.swing.JLabel idcombustivelLabel2;
    private javax.swing.JLabel idcombustivelLabel3;
    private javax.swing.JLabel idcombustivelLabel4;
    private javax.swing.JLabel idcombustivelLabel5;
    private javax.swing.JLabel idcombustivelLabel6;
    private javax.swing.JLabel idcombustivelLabel9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JButton jbtVisualizar;
    private com.toedter.calendar.JDateChooser jdcDataFinal;
    private com.toedter.calendar.JDateChooser jdcDataInicial;
    private javax.swing.JLabel jlbDataFinal;
    private javax.swing.JLabel jlbDataInicial;
    private javax.swing.JLabel jlbTituloJanela;
    private javax.swing.JPanel jplPeriodo;
    private javax.swing.JPanel jplTitulo;
    private javax.swing.JTable masterTable;
    private javax.swing.JTextField numeroAbastec;
    private javax.swing.JTextField seq;
    private javax.swing.JTextField volumeField;
    // End of variables declaration//GEN-END:variables
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            VincularIdent dialog = new VincularIdent(new javax.swing.JFrame(), true);
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

    private void desabilitaCampos() {
        seq.setEnabled(false);
        numeroAbastec.setEnabled(false);
        dataField.setEnabled(false);
        horaField.setEnabled(false);
        volumeField.setEnabled(false);
        comboBico.setEnabled(false);
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
    }

    private boolean verificaCamposObrigatorios() {
        if (comboOperador.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(null, "Operador não informado");
            comboOperador.requestFocus();
            return false;
        }

        if (comboVeiculo.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(null, "Veículo não informado");
            comboVeiculo.requestFocus();
            return false;
        }

        return true;
    }
}
