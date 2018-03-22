package view.consultas;

import ctr.GlobalParameter;
import idioma.Idioma;
import javax.swing.ImageIcon;

public class NotasVersaoGUI extends javax.swing.JDialog {

    private static Idioma i;

    public NotasVersaoGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        i = GlobalParameter.getIdioma();
        initComponents();

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
        setIconImage(icon.getImage());

        jTextArea1.setCaretPosition(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplTitulo = new javax.swing.JPanel();
        jlbTituloJanela = new javax.swing.JLabel();
        jbtFechar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jplTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloJanela.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloJanela.setText(i.getProperty("princ42"));

        javax.swing.GroupLayout jplTituloLayout = new javax.swing.GroupLayout(jplTitulo);
        jplTitulo.setLayout(jplTituloLayout);
        jplTituloLayout.setHorizontalGroup(
            jplTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTituloJanela, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplTituloLayout.setVerticalGroup(
            jplTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTituloJanela)
                .addContainerGap())
        );

        jbtFechar.setText(i.getProperty("sis10"));
        jbtFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFecharActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("V5.1\n* Adicionado atalho para acesso a tela de ajuste de estoque.\n\nV5.0\n* Adicionado campo de valor nos relatórios de abastecimentos e consumo médio.\n* Nova tela restrita para ajustes de registros.\n* Nova tela restrita para excluir abastecimentos.\n* Nova tela restrita para consulta de abastecimentos com odômetros e horímetros atuais menores que os anteriores.\n\nV4.2\n* Implementado regra para não permitir abastecimentos no TWC para bicos inativos.\n* Implementado tela de histórico de liberações no menu Abastecimentos.\n\nV4.1\n* Implementado parâmetro para informar a quantidade de vias para impressão de cupom.\n\nV4.0\n* Implementado e adequado as rotinas do sistema para permitir o controle de motoristas nos abastecimentos.\n* Implementado rotina para impressão de cupom não fiscal (Impressora Bematech MP-4200 TH).\n* Realizado melhoria em todos os cadastros para permitir ordenação das colunas com apenas o clique nelas.\n* Melhoria do cadastro de operador. Incluído novo campo \"Master\".\n* Implementado novo menu TWC para operadores master. (não requer identificação de veículo e odômetro/horímetro).\n\nV3.1\n* Implementado novo parâmetro para permitir que a leitura do bico no TWC possa ser através de leitura por TAG ou por código do sistema.\n\nV3.0\n* Implementado novo parâmetro para acesso ao sistema sem necessidade de login.\n* Inserido regra no cadastro de operador para bloquear o cadastro de identificadores em duplicidade.\n\nV2.9\n* Inserido rotina para registro de abastecimento externo.\n* Inserido integração com terminal móvel TWC.\n* Inserido pesquisa por nome (ENTER) no cadastro de veículo.\n\nV2.8\n* Inserido controle de estoque para os combustíveis.\n* Inserido rotina para sincronização de comboios.\n\nV2.7\n* Limitado Odômetros e Horímetros a apenas 6 caracteres\n\nV2.6\n* Implementado controle de licença.\n\nV2.5\n* Adicionado relatório para exibir odômetro/horímetro atual dos veículos.\n* Novo campo no cadastro de veículo para exibir odômetro/horímetro atual.\n* Nova tela para atualização do banco de dados (restrito).\n* Corrigido problema ao limitar abastecimento quando não exitir quantidade liberada para frota.\n\nV2.4\n* Correção do limite de abastecimento de frota para não permitir abastecimento quando limite disponível acabar.\n* Ajustes na tela de configuração ao informar o valor por litro com quantidade menor de 4 caracteres.\n* Melhorias de layout.\n* Inserido tela para visualização nas notas de Versão/Release.\n\nV2.3\n* Ajustes para funcionamento no Linux.\n\nV2.2\n* Tela para lançamento manual de abastecimento.\n* Não permitir que um operador abasteça veiculo de outra empresa.\n* Não permitir cadastro de operador com mesmo login.\n* Nos relatórios exibir o nome do operador que emitiu o relatório.\n* Relatório de média por veiculo exibir a média geral.\n* Dados da Empresa nos relatórios.\n* Configuração de casas decimais da bomba.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jplTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jbtFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        dispose();
}//GEN-LAST:event_jbtFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jbtFechar;
    private javax.swing.JLabel jlbTituloJanela;
    private javax.swing.JPanel jplTitulo;
    // End of variables declaration//GEN-END:variables
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            NotasVersaoGUI dialog = new NotasVersaoGUI(new javax.swing.JFrame(), true);
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
