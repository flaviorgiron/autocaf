package view.movimentacao;

import bd.BicosDB;
import ctr.AnalisaComboio;
import ctr.Cbc;
import ctr.RealTimeComboio;
import model.Bico;
import render.BicoListRender;
import ctr.GlobalParameter;
import javax.swing.ImageIcon;
import util.GeraLog;
import util.JConfirmMessage;

public class SincronizaComboio extends javax.swing.JDialog {

    private static Cbc cbc;
    public Thread realTimeComboio = null;
    public Thread analisaComboio = null;

    public SincronizaComboio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            carregaComboComboios();

            jComboBox1.setRenderer(new BicoListRender());

            if (jComboBox1.getItemCount() == 2) {
                jComboBox1.setSelectedIndex(1);
            } else {
                jComboBox1.setSelectedIndex(0);
            }

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
        setIconImage(icon.getImage());
    }

    private void carregaComboComboios() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Selecione");
        for (Bico b : BicosDB.buscaComboios(false)) {
            jComboBox1.addItem(b);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplTitulo = new javax.swing.JPanel();
        jlbTituloJanela = new javax.swing.JLabel();
        jplOpcoes = new javax.swing.JPanel();
        jplPeriodo1 = new javax.swing.JPanel();
        jlbDataInicial1 = new javax.swing.JLabel();
        jlbDataInicial2 = new javax.swing.JLabel();
        jlbDataInicial3 = new javax.swing.JLabel();
        jlbDataInicial4 = new javax.swing.JLabel();
        fieldConexao = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        fieldPorta = new javax.swing.JTextField();
        fieldCasas = new javax.swing.JTextField();
        jbtVisualizar = new javax.swing.JButton();
        jbtFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jplTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloJanela.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloJanela.setText("Sincronização de Comboio");

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

        javax.swing.GroupLayout jplOpcoesLayout = new javax.swing.GroupLayout(jplOpcoes);
        jplOpcoes.setLayout(jplOpcoesLayout);
        jplOpcoesLayout.setHorizontalGroup(
            jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jplOpcoesLayout.setVerticalGroup(
            jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 159, Short.MAX_VALUE)
        );

        jplPeriodo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlbDataInicial1.setText("Comboio");

        jlbDataInicial2.setText("Conexão");

        jlbDataInicial3.setText("Porta");

        jlbDataInicial4.setText("Casas Decimais");

        fieldConexao.setEnabled(false);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        fieldPorta.setEnabled(false);

        fieldCasas.setEnabled(false);

        javax.swing.GroupLayout jplPeriodo1Layout = new javax.swing.GroupLayout(jplPeriodo1);
        jplPeriodo1.setLayout(jplPeriodo1Layout);
        jplPeriodo1Layout.setHorizontalGroup(
            jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addComponent(jlbDataInicial4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldCasas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbDataInicial2)
                            .addComponent(jlbDataInicial3)
                            .addComponent(jlbDataInicial1))
                        .addGap(34, 34, 34)
                        .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplPeriodo1Layout.setVerticalGroup(
            jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDataInicial1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDataInicial2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDataInicial3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldCasas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDataInicial4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbtVisualizar.setText("Sincronizar");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jplTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jplPeriodo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jplOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplPeriodo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtVisualizar)
                    .addComponent(jbtFechar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        dispose();
}//GEN-LAST:event_jbtFecharActionPerformed

    private void jbtVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVisualizarActionPerformed
        try {
            if (GlobalParameter.getComboioSinc() != null) {
                JConfirmMessage.showMessageDialog("Aguarde. Uma sincronização está em andamento.", "Atenção");
                return;
            }
            if (jComboBox1.getSelectedIndex() <= 0) {
                JConfirmMessage.showMessageDialog("Nenhum comboio selecionado.", "Atenção");
                return;
            }
            iniciaConexaoComboio();
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
}//GEN-LAST:event_jbtVisualizarActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (jComboBox1.getSelectedIndex() > 0) {
            Bico b = (Bico) jComboBox1.getModel().getSelectedItem();
            fieldConexao.setText(b.getConexao());
            fieldPorta.setText(b.getPorta().toString());
            fieldCasas.setText(b.getCasasDecimais().toString());
        } else {
            fieldConexao.setText("");
            fieldPorta.setText("");
            fieldCasas.setText("");
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fieldCasas;
    private javax.swing.JTextField fieldConexao;
    private javax.swing.JTextField fieldPorta;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JButton jbtFechar;
    private javax.swing.JButton jbtVisualizar;
    private javax.swing.JLabel jlbDataInicial1;
    private javax.swing.JLabel jlbDataInicial2;
    private javax.swing.JLabel jlbDataInicial3;
    private javax.swing.JLabel jlbDataInicial4;
    private javax.swing.JLabel jlbTituloJanela;
    private javax.swing.JPanel jplOpcoes;
    private javax.swing.JPanel jplPeriodo1;
    private javax.swing.JPanel jplTitulo;
    // End of variables declaration//GEN-END:variables
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SincronizaComboio dialog = new SincronizaComboio(new javax.swing.JFrame(), true);
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

    private void iniciaConexaoComboio() {
        try {
            cbc = GlobalParameter.getInstance().getCbcComboio();

            if (!cbc.isConectadoComboio()) {
                cbc.conectarDispositivoComboio(fieldConexao.getText(), Integer.valueOf(fieldPorta.getText()));
            }

            if (cbc.isConectadoComboio()) {
                GlobalParameter.setComboioSinc((Bico) jComboBox1.getModel().getSelectedItem());
                realTimeComboio = new Thread(new RealTimeComboio(), "RealTimeComboio");
                realTimeComboio.start();

                analisaComboio = new Thread(new AnalisaComboio(), "AnalisaComboio");
                analisaComboio.start();
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
            e.printStackTrace();
        }
    }
}
