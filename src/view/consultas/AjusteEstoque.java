package view.consultas;

import java.util.Iterator;
import render.*;
import util.*;
import model.*;
import bd.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AjusteEstoque extends javax.swing.JDialog {

    public AjusteEstoque(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
        setIconImage(icon.getImage());

        carregaComboCombustiveis();

        jComboBox1.setRenderer(new CombustivelListRender());

        jTextField3.setVisible(false);
        jlbDataInicial3.setVisible(false);
        jTextField2.setText("0,00");
    }

    private void carregaComboCombustiveis() {
        jComboBox1.removeAllItems();
        //jComboBox1.addItem("Selecione");
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
        jlbDataInicial = new javax.swing.JLabel();
        jlbDataFinal = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jlbDataInicial1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jlbDataInicial2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jlbDataInicial4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jbtVisualizar = new javax.swing.JButton();
        jbtFechar = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jlbDataInicial3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajuste de Estoque");

        jplTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloJanela.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloJanela.setText("Ajuste de Estoque");

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

        jlbDataInicial.setText("Tipo Movimentação");

        jlbDataFinal.setText("Combustível");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Entrada de Estoque", "Saída de Estoque" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jlbDataInicial1.setText("Estoque Atual");

        jTextField1.setEditable(false);

        jlbDataInicial2.setText("Quantidade");

        jlbDataInicial4.setText("Observação");

        javax.swing.GroupLayout jplPeriodoLayout = new javax.swing.GroupLayout(jplPeriodo);
        jplPeriodo.setLayout(jplPeriodoLayout);
        jplPeriodoLayout.setHorizontalGroup(
            jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplPeriodoLayout.createSequentialGroup()
                        .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplPeriodoLayout.createSequentialGroup()
                                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbDataInicial1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jplPeriodoLayout.createSequentialGroup()
                                .addComponent(jlbDataInicial2)
                                .addGap(48, 48, 48)
                                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jplPeriodoLayout.createSequentialGroup()
                        .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplPeriodoLayout.createSequentialGroup()
                                .addComponent(jlbDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlbDataInicial4))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jplPeriodoLayout.setVerticalGroup(
            jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDataInicial2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbtVisualizar.setText("Lançar");
        jbtVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVisualizarActionPerformed(evt);
            }
        });

        jbtFechar.setText("Fechar");
        jbtFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFecharActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);

        jlbDataInicial3.setText("Estoque Final");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jplPeriodo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jplTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlbDataInicial3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtVisualizar)
                    .addComponent(jbtFechar)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDataInicial3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVisualizarActionPerformed
        if (jComboBox1.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Combustível não informado");
            jComboBox1.requestFocus();
            return;
        }
        if (jTextField2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Quantidade não informada");
            jTextField2.requestFocus();
            return;
        }

        try {
            int y = JConfirmMessage.showOptionDialog("", "Confirma o lançamento?");
            if (y == JOptionPane.YES_OPTION) {
                boolean res = false;
                res = EstoqueDB.gravaSaldoEstoque((jComboBox4.getSelectedItem().toString().equals("Entrada de Estoque")) ? "E" : "S", (((Combustivel) jComboBox1.getSelectedItem()).getSeqCombustivel()), Double.parseDouble(jTextField2.getText().replace(",", ".")), jTextField4.getText().trim());
                if (res) {
                    JConfirmMessage.showMessageDialog("Ajuste de estoque efetuado com sucesso.", "");
                    limpaTela();
                } else {
                    JConfirmMessage.showMessageDialog("Um problema ocorreu para o lançamento. Tente novamente.", "");
                }
            }

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "");
        }
    }//GEN-LAST:event_jbtVisualizarActionPerformed

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        dispose();
    }//GEN-LAST:event_jbtFecharActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (jComboBox1.getItemCount() >= 1) {
            jTextField1.setText(FormatacaoDados.DoubleFormat(EstoqueDB.buscaSaldoEstoqueCombustivel(((Combustivel) jComboBox1.getSelectedItem()).getSeqCombustivel())));
        } else {
            jTextField1.setText("0,00");
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JButton jbtFechar;
    private javax.swing.JButton jbtVisualizar;
    private javax.swing.JLabel jlbDataFinal;
    private javax.swing.JLabel jlbDataInicial;
    private javax.swing.JLabel jlbDataInicial1;
    private javax.swing.JLabel jlbDataInicial2;
    private javax.swing.JLabel jlbDataInicial3;
    private javax.swing.JLabel jlbDataInicial4;
    private javax.swing.JLabel jlbTituloJanela;
    private javax.swing.JPanel jplPeriodo;
    private javax.swing.JPanel jplTitulo;
    // End of variables declaration//GEN-END:variables
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            AjusteEstoque dialog = new AjusteEstoque(new javax.swing.JFrame(), true);
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

    private void limpaTela() {
        carregaComboCombustiveis();
        jTextField2.setText("0,00");
        jComboBox4.setSelectedIndex(0);
    }
}
