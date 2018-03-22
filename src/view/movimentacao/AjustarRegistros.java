package view.movimentacao;

import bd.AjustarRegistrosDB;
import javax.swing.ImageIcon;
import util.GeraLog;
import util.JConfirmMessage;

public class AjustarRegistros extends javax.swing.JDialog {

    public AjustarRegistros(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
        setIconImage(icon.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplTitulo = new javax.swing.JPanel();
        jlbTituloJanela = new javax.swing.JLabel();
        jplPeriodo1 = new javax.swing.JPanel();
        jlbDataInicial1 = new javax.swing.JLabel();
        btAjustaOdometro = new javax.swing.JButton();
        jlbDataInicial2 = new javax.swing.JLabel();
        btZerarMediaOdometro = new javax.swing.JButton();
        jlbDataInicial3 = new javax.swing.JLabel();
        jlbDataInicial4 = new javax.swing.JLabel();
        btAjustaOdometro1 = new javax.swing.JButton();
        btZerarMediaOdometro1 = new javax.swing.JButton();
        jlbDataInicial5 = new javax.swing.JLabel();
        btZerarMediaOdometro2 = new javax.swing.JButton();
        btZerarMediaOdometro3 = new javax.swing.JButton();
        btZerarMediaOdometro4 = new javax.swing.JButton();
        jlbDataInicial6 = new javax.swing.JLabel();
        jlbDataInicial7 = new javax.swing.JLabel();
        jbtFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajustes de registros");

        jplTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloJanela.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloJanela.setText("Ajustes de registros");

        javax.swing.GroupLayout jplTituloLayout = new javax.swing.GroupLayout(jplTitulo);
        jplTitulo.setLayout(jplTituloLayout);
        jplTituloLayout.setHorizontalGroup(
            jplTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTituloJanela, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplTituloLayout.setVerticalGroup(
            jplTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTituloJanela)
                .addContainerGap())
        );

        jplPeriodo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlbDataInicial1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlbDataInicial1.setText("Ajustar odômetro com mais de 6 casas decimais e recalcular médias.");

        btAjustaOdometro.setText("Executar");
        btAjustaOdometro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAjustaOdometroActionPerformed(evt);
            }
        });

        jlbDataInicial2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlbDataInicial2.setText("Zerar médias de abastecimentos com odômetro anterior maior que o atual");

        btZerarMediaOdometro.setText("Executar");
        btZerarMediaOdometro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btZerarMediaOdometroActionPerformed(evt);
            }
        });

        jlbDataInicial3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlbDataInicial3.setText("Ajustar horímetro com mais de 6 casas decimais e recalcular médias.");

        jlbDataInicial4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlbDataInicial4.setText("Zerar médias de abastecimentos com horímetro anterior maior que o atual");

        btAjustaOdometro1.setText("Executar");
        btAjustaOdometro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAjustaOdometro1ActionPerformed(evt);
            }
        });

        btZerarMediaOdometro1.setText("Executar");
        btZerarMediaOdometro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btZerarMediaOdometro1ActionPerformed(evt);
            }
        });

        jlbDataInicial5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlbDataInicial5.setText("Zerar Estoque");

        btZerarMediaOdometro2.setText("Executar");
        btZerarMediaOdometro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btZerarMediaOdometro2ActionPerformed(evt);
            }
        });

        btZerarMediaOdometro3.setText("Executar");
        btZerarMediaOdometro3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btZerarMediaOdometro3ActionPerformed(evt);
            }
        });

        btZerarMediaOdometro4.setText("Executar");
        btZerarMediaOdometro4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btZerarMediaOdometro4ActionPerformed(evt);
            }
        });

        jlbDataInicial6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlbDataInicial6.setText("Recalcular médias dos odômetros");

        jlbDataInicial7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlbDataInicial7.setText("Recalcular médias dos horímetros");

        javax.swing.GroupLayout jplPeriodo1Layout = new javax.swing.GroupLayout(jplPeriodo1);
        jplPeriodo1.setLayout(jplPeriodo1Layout);
        jplPeriodo1Layout.setHorizontalGroup(
            jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addComponent(btAjustaOdometro, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbDataInicial1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(99, 99, 99))
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addComponent(btZerarMediaOdometro, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbDataInicial2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(105, 105, 105))
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addComponent(btAjustaOdometro1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbDataInicial3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(99, 99, 99))
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addComponent(btZerarMediaOdometro1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbDataInicial4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(99, 99, 99))
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addComponent(btZerarMediaOdometro2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbDataInicial5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(99, 99, 99))
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                                .addComponent(btZerarMediaOdometro3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbDataInicial6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                                .addComponent(btZerarMediaOdometro4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbDataInicial7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(99, 99, 99))))
        );
        jplPeriodo1Layout.setVerticalGroup(
            jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAjustaOdometro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btZerarMediaOdometro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAjustaOdometro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btZerarMediaOdometro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btZerarMediaOdometro2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btZerarMediaOdometro3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbDataInicial6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btZerarMediaOdometro4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbDataInicial7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jbtFechar.setText("Fechar");
        jbtFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jplPeriodo1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jplTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplPeriodo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtFechar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        dispose();
}//GEN-LAST:event_jbtFecharActionPerformed

    private void btAjustaOdometroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAjustaOdometroActionPerformed
        try {
            if (AjustarRegistrosDB.ajustarOdometros()) {
                JConfirmMessage.showMessageDialog("Ajuste executado com sucesso.", "");
            } else {
                JConfirmMessage.showMessageDialog("Falha ao executar o ajuste. Detalhes no arquivo de log.", "");
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            //JConfirmMessage.showMessageDialog(i.getProperty("lic4"), i.getProperty("sis1"));
        }
}//GEN-LAST:event_btAjustaOdometroActionPerformed

    private void btZerarMediaOdometroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btZerarMediaOdometroActionPerformed
        try {
            if (AjustarRegistrosDB.zerarMediaOdo()) {
                JConfirmMessage.showMessageDialog("Ajuste executado com sucesso.", "");
            } else {
                JConfirmMessage.showMessageDialog("Falha ao executar o ajuste. Detalhes no arquivo de log.", "");
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            //JConfirmMessage.showMessageDialog(i.getProperty("lic4"), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_btZerarMediaOdometroActionPerformed

    private void btAjustaOdometro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAjustaOdometro1ActionPerformed
        try {
            if (AjustarRegistrosDB.ajustarHorimetros()) {
                JConfirmMessage.showMessageDialog("Ajuste executado com sucesso.", "");
            } else {
                JConfirmMessage.showMessageDialog("Falha ao executar o ajuste. Detalhes no arquivo de log.", "");
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            //JConfirmMessage.showMessageDialog(i.getProperty("lic4"), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_btAjustaOdometro1ActionPerformed

    private void btZerarMediaOdometro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btZerarMediaOdometro1ActionPerformed
        try {
            if (AjustarRegistrosDB.zerarMediaHor()) {
                JConfirmMessage.showMessageDialog("Ajuste executado com sucesso.", "");
            } else {
                JConfirmMessage.showMessageDialog("Falha ao executar o ajuste. Detalhes no arquivo de log.", "");
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            //JConfirmMessage.showMessageDialog(i.getProperty("lic4"), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_btZerarMediaOdometro1ActionPerformed

    private void btZerarMediaOdometro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btZerarMediaOdometro2ActionPerformed
        try {
            if (AjustarRegistrosDB.zerarEstoque()) {
                JConfirmMessage.showMessageDialog("Ajuste executado com sucesso.", "");
            } else {
                JConfirmMessage.showMessageDialog("Falha ao executar o ajuste. Detalhes no arquivo de log.", "");
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            //JConfirmMessage.showMessageDialog(i.getProperty("lic4"), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_btZerarMediaOdometro2ActionPerformed

    private void btZerarMediaOdometro3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btZerarMediaOdometro3ActionPerformed
        try {
            if (AjustarRegistrosDB.recalcularMediaOdometros()) {
                JConfirmMessage.showMessageDialog("Ajuste executado com sucesso.", "");
            } else {
                JConfirmMessage.showMessageDialog("Falha ao executar o ajuste. Detalhes no arquivo de log.", "");
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            //JConfirmMessage.showMessageDialog(i.getProperty("lic4"), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_btZerarMediaOdometro3ActionPerformed

    private void btZerarMediaOdometro4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btZerarMediaOdometro4ActionPerformed
        try {
            if (AjustarRegistrosDB.recalcularMediaHorimetros()) {
                JConfirmMessage.showMessageDialog("Ajuste executado com sucesso.", "");
            } else {
                JConfirmMessage.showMessageDialog("Falha ao executar o ajuste. Detalhes no arquivo de log.", "");
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            //JConfirmMessage.showMessageDialog(i.getProperty("lic4"), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_btZerarMediaOdometro4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAjustaOdometro;
    private javax.swing.JButton btAjustaOdometro1;
    private javax.swing.JButton btZerarMediaOdometro;
    private javax.swing.JButton btZerarMediaOdometro1;
    private javax.swing.JButton btZerarMediaOdometro2;
    private javax.swing.JButton btZerarMediaOdometro3;
    private javax.swing.JButton btZerarMediaOdometro4;
    private javax.swing.JButton jbtFechar;
    private javax.swing.JLabel jlbDataInicial1;
    private javax.swing.JLabel jlbDataInicial2;
    private javax.swing.JLabel jlbDataInicial3;
    private javax.swing.JLabel jlbDataInicial4;
    private javax.swing.JLabel jlbDataInicial5;
    private javax.swing.JLabel jlbDataInicial6;
    private javax.swing.JLabel jlbDataInicial7;
    private javax.swing.JLabel jlbTituloJanela;
    private javax.swing.JPanel jplPeriodo1;
    private javax.swing.JPanel jplTitulo;
    // End of variables declaration//GEN-END:variables
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            AjustarRegistros dialog = new AjustarRegistros(new javax.swing.JFrame(), true);
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
