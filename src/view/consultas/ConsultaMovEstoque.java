package view.consultas;

import tablemodel.MovEstoqueTableModel;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.swing.table.TableColumnModel;
import model.*;
import render.*;
import util.*;
import bd.*;
import javax.swing.ImageIcon;

public class ConsultaMovEstoque extends javax.swing.JDialog {

    private MovEstoqueTableModel movEstoqueTableModel;

    public MovEstoqueTableModel getModelMovEstoqueTableModel() {
        if (movEstoqueTableModel == null) {
            movEstoqueTableModel = (MovEstoqueTableModel) masterTable.getModel();
        }
        return movEstoqueTableModel;
    }

    public ConsultaMovEstoque(java.awt.Frame parent, boolean modal) {
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

        //jdcDataInicial.setCalendar(calendarioInicial);
        //jdcDataFinal.setCalendar(calendarioFinal);
        jdcDataInicial.setDate(new Date());
        jdcDataFinal.setDate(new Date());

        jComboBox1.setRenderer(new CombustivelListRender());

        carregaComboCombustiveis();

        masterTable.setModel(new MovEstoqueTableModel());
        masterTable.setAutoCreateRowSorter(false);

        TableColumnModel columnModel = masterTable.getColumnModel();
        columnModel.getColumn(3).setPreferredWidth(300);
    }

    private void carregaComboCombustiveis() {
        jComboBox1.removeAllItems();
        Iterator iterator = CombustiveisDB.buscaCombustiveis(false).iterator();
        while (iterator.hasNext()) {
            Combustivel c = (Combustivel) iterator.next();
            jComboBox1.addItem(c);
        }
        if (jComboBox1.getItemCount() >= 1) {
            //FormatacaoDados.DoubleFormat(EstoqueDB.buscaSaldoEstoqueCombustivel(((Combustivel) jComboBox1.getSelectedItem()).getSeqCombustivel()));
            jTextField1.setText(FormatacaoDados.DoubleFormat(EstoqueDB.buscaSaldoEstoqueCombustivel(((Combustivel) jComboBox1.getSelectedItem()).getSeqCombustivel())));
        } else {
            jTextField1.setText("0,00");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplTitulo = new javax.swing.JPanel();
        jlbTituloJanela = new javax.swing.JLabel();
        jplPeriodo = new javax.swing.JPanel();
        jdcDataInicial = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        jlbDataInicial = new javax.swing.JLabel();
        jdcDataFinal = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        jlbDataFinal = new javax.swing.JLabel();
        jlbDataInicial1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jbtVisualizar = new javax.swing.JButton();
        jlbDataInicial2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jplTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloJanela.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloJanela.setText("Consulta Movimentação de Estoque");

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

        jlbDataInicial1.setText("Combustível");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jbtVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisa.png"))); // NOI18N
        jbtVisualizar.setText("Pesquisar");
        jbtVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVisualizarActionPerformed(evt);
            }
        });

        jlbDataInicial2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbDataInicial2.setForeground(new java.awt.Color(51, 51, 255));
        jlbDataInicial2.setText("Saldo Atual:");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jplPeriodoLayout = new javax.swing.GroupLayout(jplPeriodo);
        jplPeriodo.setLayout(jplPeriodoLayout);
        jplPeriodoLayout.setHorizontalGroup(
            jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplPeriodoLayout.createSequentialGroup()
                        .addComponent(jlbDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jlbDataFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jplPeriodoLayout.createSequentialGroup()
                        .addComponent(jlbDataInicial1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplPeriodoLayout.createSequentialGroup()
                                .addComponent(jlbDataInicial2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtVisualizar))
                            .addComponent(jComboBox1, 0, 493, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jplPeriodoLayout.setVerticalGroup(
            jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlbDataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlbDataFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jdcDataInicial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtVisualizar)
                    .addComponent(jlbDataInicial2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        masterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(masterTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jplTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jplPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }//GEN-LAST:event_jbtVisualizarActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (jComboBox1.getItemCount() >= 1) {
            jTextField1.setText(FormatacaoDados.DoubleFormat(EstoqueDB.buscaSaldoEstoqueCombustivel(((Combustivel) jComboBox1.getSelectedItem()).getSeqCombustivel())));
        } else {
            jTextField1.setText("0,00");
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void carregaGrid() {
        try {
            masterTable.setModel(new MovEstoqueTableModel());
            masterTable.setAutoCreateRowSorter(false);
            masterTable.setRowHeight(20);
            TableColumnModel columnModel = masterTable.getColumnModel();

            TimestampTableRender timestampRenderer = new TimestampTableRender();
            columnModel.getColumn(0).setCellRenderer(timestampRenderer);
            columnModel.getColumn(3).setPreferredWidth(300);

            //columnModel.getColumn(1).setMaxWidth(0);
            //columnModel.getColumn(1).setMinWidth(0);
            getModelMovEstoqueTableModel().limpar();

            getModelMovEstoqueTableModel().addListaMovEstoques(EstoqueDB.buscaMovimentacoes(jdcDataInicial.getDate(), jdcDataFinal.getDate(), (Combustivel) jComboBox1.getSelectedItem()));
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbtVisualizar;
    private com.toedter.calendar.JDateChooser jdcDataFinal;
    private com.toedter.calendar.JDateChooser jdcDataInicial;
    private javax.swing.JLabel jlbDataFinal;
    private javax.swing.JLabel jlbDataInicial;
    private javax.swing.JLabel jlbDataInicial1;
    private javax.swing.JLabel jlbDataInicial2;
    private javax.swing.JLabel jlbTituloJanela;
    private javax.swing.JPanel jplPeriodo;
    private javax.swing.JPanel jplTitulo;
    private javax.swing.JTable masterTable;
    // End of variables declaration//GEN-END:variables
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            ConsultaMovEstoque dialog = new ConsultaMovEstoque(new javax.swing.JFrame(), true);
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
