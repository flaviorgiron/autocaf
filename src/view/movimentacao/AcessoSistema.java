package view.movimentacao;

import bd.LicencaDB;
import bd.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import model.*;
import ctr.GlobalParameter;
import idioma.Idioma;
import javax.swing.ImageIcon;
import util.*;

public class AcessoSistema extends javax.swing.JFrame {

    private static Idioma i;

    public AcessoSistema() {
        try {
            i = GlobalParameter.getIdioma();
            initComponents();
            this.getRootPane().setDefaultButton(jbtAcessoSistema);
            //data atual
            Date d = new Date();
            Long dataAtual = d.getTime();

            //data da licença
            Licenca lic = LicencaDB.buscaLicenca();
            Decoder de = Base64.getDecoder();
            String s1 = new String(de.decode(lic.getLicenca()));
            Long dataLic = Long.parseLong(s1);
            Date dtLic = new Date(dataLic);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            jlbLicenca.setText("" + formato.format(dtLic));

            //data ultimo acesso
            String s2 = new String(de.decode(lic.getUltimoAcesso()));
            Long dataUltimoAcesso = Long.parseLong(s2);

            int diasRest = TratamentoValores.dataDiff(d, dtLic);

            //verifica se a data atual é maior que a licenca
            if (dataAtual > dataLic) {
                jlbAvisoLoginSenha.setText(i.getProperty("lic1"));
                //"A licença de uso do sistema está vencida.");
                jbtAcessoSistema.setEnabled(false);
                jtfLoginUsuario.setEnabled(false);
                jtfPasswordUsuario.setEnabled(false);
                lbDias.setText("0 dia");
                //return;
                //olha se alterou data do sistema
            } else if (dataAtual < dataUltimoAcesso) {
                jlbAvisoLoginSenha.setText(i.getProperty("lic2"));
                jbtAcessoSistema.setEnabled(false);
                jtfLoginUsuario.setEnabled(false);
                jtfPasswordUsuario.setEnabled(false);
                lbDias.setText("0 dia");
                //return;
            } else {
                //olha parametro para exibir os dias restantes
                lbDias.setText("0 dia");
                //return;
            }
            if (diasRest > 1) {
                lbDias.setText(diasRest + " dias");
            } else {
                lbDias.setText(diasRest + " dia");
            }

            //comentar
            //jtfLoginUsuario.setText("SUPERADMIN");
            //jtfPasswordUsuario.setText("SUPERADMIN");
            //jbtAcessoSistema.doClick();
            //URL url = this.getClass().getResource("/imagens/abastec.png");
            //Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
            //this.setIconImage(icon);
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
            setIconImage(icon.getImage());
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplDadosAcesso = new javax.swing.JPanel();
        jlbLoginUsuario = new javax.swing.JLabel();
        jtfLoginUsuario = new javax.swing.JTextField();
        jlbPasswordUsuario = new javax.swing.JLabel();
        jtfPasswordUsuario = new javax.swing.JPasswordField();
        jbtAcessoSistema = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jlbPasswordUsuario1 = new javax.swing.JLabel();
        jlbLicenca = new javax.swing.JLabel();
        jlbPasswordUsuario2 = new javax.swing.JLabel();
        lbDias = new javax.swing.JLabel();
        jplRodapeBranco = new javax.swing.JPanel();
        jlbAvisoLoginSenha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(i.getProperty("login3"));
        setResizable(false);

        jplDadosAcesso.setBorder(javax.swing.BorderFactory.createTitledBorder(i.getProperty("login4")));
        jplDadosAcesso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jlbLoginUsuario.setText(i.getProperty("login5"));

        jtfLoginUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfLoginUsuarioKeyPressed(evt);
            }
        });

        jlbPasswordUsuario.setText(i.getProperty("login6"));

        jtfPasswordUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfPasswordUsuarioKeyPressed(evt);
            }
        });

        jbtAcessoSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/login.png"))); // NOI18N
        jbtAcessoSistema.setToolTipText(i.getProperty("login9"));
        jbtAcessoSistema.setFocusable(false);
        jbtAcessoSistema.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtAcessoSistema.setIconTextGap(0);
        jbtAcessoSistema.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtAcessoSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAcessoSistemaActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/licenca.png"))); // NOI18N
        jButton1.setToolTipText(i.getProperty("lic3"));
        jButton1.setContentAreaFilled(false);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jlbPasswordUsuario1.setText(i.getProperty("login7"));

        jlbLicenca.setText("xx/xx/xxxx");

        jlbPasswordUsuario2.setText(i.getProperty("login8"));

        lbDias.setText("x dias");

        javax.swing.GroupLayout jplDadosAcessoLayout = new javax.swing.GroupLayout(jplDadosAcesso);
        jplDadosAcesso.setLayout(jplDadosAcessoLayout);
        jplDadosAcessoLayout.setHorizontalGroup(
            jplDadosAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplDadosAcessoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplDadosAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfPasswordUsuario)
                    .addComponent(jtfLoginUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(jlbPasswordUsuario)
                    .addComponent(jlbLoginUsuario)
                    .addGroup(jplDadosAcessoLayout.createSequentialGroup()
                        .addGroup(jplDadosAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jplDadosAcessoLayout.createSequentialGroup()
                                .addComponent(jlbPasswordUsuario2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbDias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jplDadosAcessoLayout.createSequentialGroup()
                                .addComponent(jlbPasswordUsuario1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbLicenca, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jbtAcessoSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplDadosAcessoLayout.setVerticalGroup(
            jplDadosAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplDadosAcessoLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jlbLoginUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfLoginUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbPasswordUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfPasswordUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplDadosAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplDadosAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbPasswordUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlbLicenca))
                    .addComponent(jButton1))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jplDadosAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbPasswordUsuario2)
                    .addComponent(lbDias))
                .addGap(56, 56, 56))
            .addGroup(jplDadosAcessoLayout.createSequentialGroup()
                .addComponent(jbtAcessoSistema)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jplRodapeBranco.setBackground(new java.awt.Color(255, 255, 255));

        jlbAvisoLoginSenha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbAvisoLoginSenha.setForeground(new java.awt.Color(255, 0, 0));
        jlbAvisoLoginSenha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jplRodapeBrancoLayout = new javax.swing.GroupLayout(jplRodapeBranco);
        jplRodapeBranco.setLayout(jplRodapeBrancoLayout);
        jplRodapeBrancoLayout.setHorizontalGroup(
            jplRodapeBrancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplRodapeBrancoLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jlbAvisoLoginSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jplRodapeBrancoLayout.setVerticalGroup(
            jplRodapeBrancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbAvisoLoginSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplDadosAcesso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jplRodapeBranco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplDadosAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jplRodapeBranco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtAcessoSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAcessoSistemaActionPerformed
        try {
            if (jtfLoginUsuario.getText().trim().equals("")) {
                return;
            }
            if (jtfPasswordUsuario.getText().trim().equals("")) {
                return;
            }
            Operador operador = OperadoresDB.consultaLoginSenha(jtfLoginUsuario.getText().toUpperCase(), jtfPasswordUsuario.getText().toUpperCase());
            if (operador != null) {
                if (operador.getSituacao().equals("A")) {
                    GlobalParameter.getInstance().setOperador(operador);
                    SDIPrincipalGUI principal = new SDIPrincipalGUI();
                    principal.setTitle("AutoCAF - " + i.getProperty("sis2"));
                    principal.setLocationRelativeTo(null);
                    principal.setVisible(true);
                    this.dispose();

                } else {
                    jlbAvisoLoginSenha.setText(i.getProperty("login1"));
                    jtfLoginUsuario.requestFocus();
                }
            } else {
                jlbAvisoLoginSenha.setText(i.getProperty("login2"));
                jtfLoginUsuario.requestFocus();
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
            e.printStackTrace();
        }
}//GEN-LAST:event_jbtAcessoSistemaActionPerformed

    private void jtfLoginUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfLoginUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtfPasswordUsuario.requestFocus();
        }
    }//GEN-LAST:event_jtfLoginUsuarioKeyPressed

    private void jtfPasswordUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPasswordUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jbtAcessoSistema.doClick();
        }
    }//GEN-LAST:event_jtfPasswordUsuarioKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            AtualizarLicenca li = new AtualizarLicenca(null, true);
            li.setTitle(i.getProperty("lic3"));
            li.setLocationRelativeTo(null);
            li.setVisible(true);
            
            Date d = new Date();
            //data da licença
            Licenca lic = LicencaDB.buscaLicenca();
            Decoder de = Base64.getDecoder();
            String s1 = new String(de.decode(lic.getLicenca()));
            String s2 = new String(de.decode(lic.getUltimoAcesso()));
            Long dataLic = Long.parseLong(s1);
            Date dtLic = new Date(dataLic);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            jlbLicenca.setText("" + formato.format(dtLic));
            Long dataUltimoAcesso = Long.parseLong(s2);

            //verifica se a data atual é maior que a licenca
            if (d.getTime() > dataLic) {
                jlbAvisoLoginSenha.setText(i.getProperty("lic1"));
                jbtAcessoSistema.setEnabled(false);
                jtfLoginUsuario.setEnabled(false);
                jtfPasswordUsuario.setEnabled(false);
                lbDias.setText("0 dia");
                //return;
                //olha se alterou data do sistema
            } else if (d.getTime() < dataUltimoAcesso) {
                jlbAvisoLoginSenha.setText(i.getProperty("lic2"));
                jbtAcessoSistema.setEnabled(false);
                jtfLoginUsuario.setEnabled(false);
                jtfPasswordUsuario.setEnabled(false);
                lbDias.setText("0 dia");
                //return;
            } else {
                //olha parametro para exibir os dias restantes
                lbDias.setText("0 dia");
                //return;
            }

            int diasRest = TratamentoValores.dataDiff(d, dtLic);

            if (diasRest > 1) {
                lbDias.setText(diasRest + " dias");
            } else {
                lbDias.setText(diasRest + " dia");
            }

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

//    public static void main(final String args[]) {
//        java.awt.EventQueue.invokeLater(() -> {
//            UIManager.put("ToolTip.background", Color.white);
//            //System.out.println(new File(".").getAbsolutePath());
//            AcessoSistema form = new AcessoSistema();
//            form.setTitle(i.getProperty("login3"));
//            form.setVisible(true);
//            form.setLocationRelativeTo(null);
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jbtAcessoSistema;
    private javax.swing.JLabel jlbAvisoLoginSenha;
    private javax.swing.JLabel jlbLicenca;
    private javax.swing.JLabel jlbLoginUsuario;
    private javax.swing.JLabel jlbPasswordUsuario;
    private javax.swing.JLabel jlbPasswordUsuario1;
    private javax.swing.JLabel jlbPasswordUsuario2;
    private javax.swing.JPanel jplDadosAcesso;
    private javax.swing.JPanel jplRodapeBranco;
    private javax.swing.JTextField jtfLoginUsuario;
    private javax.swing.JPasswordField jtfPasswordUsuario;
    private javax.swing.JLabel lbDias;
    // End of variables declaration//GEN-END:variables

}
