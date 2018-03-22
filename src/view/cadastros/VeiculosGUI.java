package view.cadastros;

import bd.VeiculosDB;
import bd.CombustiveisDB;
import bd.FrotasDB;
import ctr.GlobalParameter;
import idioma.Idioma;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.TableColumnModel;
import model.Veiculo;
import model.Combustivel;
import model.Frota;
import render.CombustivelListRender;
import render.FrotaListRender;
import render.FrotaTableCellRender;
import tablemodel.VeiculosTableModel;
import util.FixedLengthUpperDocument;
import util.FormatacaoDados;
import util.GeraLog;
import util.JConfirmMessage;
import util.OnlyNumberFieldDocument;

public class VeiculosGUI extends JPanel {

    private VeiculosTableModel veiculosTableModel;
    private static Idioma i;

    public VeiculosGUI() {
        i = GlobalParameter.getIdioma();
        initComponents();
        carregaGrid();
        carregaComboCombustiveis();
        carregaComboFrotas();
        jComboBox1.setRenderer(new CombustivelListRender());
        jComboBox2.setRenderer(new FrotaListRender());
        identField.setDocument(new FixedLengthUpperDocument(40));

        inicialField.setDocument(new OnlyNumberFieldDocument(6));
        fieldMarcador.setDocument(new OnlyNumberFieldDocument(6));

        nomeveiculoField.setDocument(new FixedLengthUpperDocument(100));

        limiteField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#" + FormatacaoDados.DoubleFormat(0).replace(",", ".")))));

        jbtRecarregarActionPerformed(null);
        seq.setVisible(false);
        fieldMarcador.setEnabled(false);

        masterTable.setAutoCreateRowSorter(true);
    }

    public VeiculosTableModel getModelVeiculo() {
        if (veiculosTableModel == null) {
            veiculosTableModel = (VeiculosTableModel) masterTable.getModel();
        }
        return veiculosTableModel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        idbicoLabel = new javax.swing.JLabel();
        nomebicoLabel = new javax.swing.JLabel();
        idcombustivelLabel = new javax.swing.JLabel();
        identField = new javax.swing.JTextField();
        nomeveiculoField = new javax.swing.JTextField();
        jbtSalvar = new javax.swing.JButton();
        jbtRecarregar = new javax.swing.JButton();
        jbtNovo = new javax.swing.JButton();
        jbtExcluir = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jplTitulo2 = new javax.swing.JPanel();
        jlbTituloCadastroExame2 = new javax.swing.JLabel();
        idcombustivelLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        rdHodometro = new javax.swing.JRadioButton();
        rdHorimetro = new javax.swing.JRadioButton();
        nomebicoLabel1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        inicialField = new javax.swing.JTextField();
        idcombustivelLabel4 = new javax.swing.JLabel();
        fieldMarcador = new javax.swing.JTextField();
        ckAtivo = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        limiteField = new javax.swing.JFormattedTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        idcombustivelLabel5 = new javax.swing.JLabel();
        nomebicoLabel2 = new javax.swing.JLabel();
        idcombustivelLabel2 = new javax.swing.JLabel();
        nomebicoLabel3 = new javax.swing.JLabel();
        limiteField1 = new javax.swing.JFormattedTextField();
        idcombustivelLabel3 = new javax.swing.JLabel();
        nomebicoLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        idcombustivelLabel6 = new javax.swing.JLabel();
        limiteField2 = new javax.swing.JFormattedTextField();
        idcombustivelLabel7 = new javax.swing.JLabel();
        seq = new javax.swing.JTextField();
        lbQtdeRegistros = new javax.swing.JLabel();

        FormListener formListener = new FormListener();

        masterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        masterTable.addMouseListener(formListener);
        masterScrollPane.setViewportView(masterTable);

        idbicoLabel.setText("Ident.");

        nomebicoLabel.setText("Nome");

        idcombustivelLabel.setText("Combustível");

        nomeveiculoField.addKeyListener(formListener);

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
        jlbTituloCadastroExame2.setText("Cadastro de Veículos");

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

        idcombustivelLabel1.setText("Frota");

        buttonGroup1.add(rdHodometro);
        rdHodometro.setSelected(true);
        rdHodometro.setText("Odômetro");

        buttonGroup1.add(rdHorimetro);
        rdHorimetro.setText("Horímetro");

        nomebicoLabel1.setText("Tipo");

        jLabel1.setText("Odômetro/Horímetro Inicial");

        idcombustivelLabel4.setText("Odômetro/Horímetro Atual");

        ckAtivo.setSelected(true);
        ckAtivo.setText("Ativo");
        ckAtivo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        ckAtivo.addActionListener(formListener);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        limiteField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Seg");

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("Ter");
        jCheckBox2.addActionListener(formListener);

        jCheckBox3.setSelected(true);
        jCheckBox3.setText("Qua");
        jCheckBox3.addActionListener(formListener);

        jCheckBox4.setSelected(true);
        jCheckBox4.setText("Qui");
        jCheckBox4.addActionListener(formListener);

        jCheckBox5.setSelected(true);
        jCheckBox5.setText("Sex");
        jCheckBox5.addActionListener(formListener);

        jCheckBox6.setSelected(true);
        jCheckBox6.setText("Sab");
        jCheckBox6.addActionListener(formListener);

        jCheckBox7.setSelected(true);
        jCheckBox7.setText("Dom");
        jCheckBox7.addActionListener(formListener);

        idcombustivelLabel5.setText("Dias permitidos");

        nomebicoLabel2.setText("Limite por Abastecimento");

        idcombustivelLabel2.setText("Litros");

        nomebicoLabel3.setText("Limite Semanal");

        limiteField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        idcombustivelLabel3.setText("Litros");

        nomebicoLabel4.setText("Renovação Automática");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" }));

        idcombustivelLabel6.setText("Disponível na Semana");

        limiteField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        idcombustivelLabel7.setText("Litros");

        seq.setText("jTextField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nomebicoLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(idcombustivelLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox4))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nomebicoLabel2)
                                        .addComponent(nomebicoLabel3))
                                    .addGap(9, 9, 9)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(limiteField1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(idcombustivelLabel3))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(limiteField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(idcombustivelLabel2))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(idcombustivelLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(limiteField2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(idcombustivelLabel7)
                                    .addGap(1, 1, 1))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox6)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox7)
                    .addComponent(idcombustivelLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(limiteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomebicoLabel2)
                    .addComponent(idcombustivelLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomebicoLabel3)
                    .addComponent(limiteField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcombustivelLabel3)
                    .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idcombustivelLabel6)
                    .addComponent(limiteField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcombustivelLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomebicoLabel4))
                .addGap(25, 25, 25))
        );

        lbQtdeRegistros.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbQtdeRegistros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbQtdeRegistros.setText("Quantidade de Registros: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jplTitulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(masterScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(idbicoLabel)
                                        .addComponent(nomebicoLabel))
                                    .addGap(42, 42, 42)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(identField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbQtdeRegistros))
                                        .addComponent(nomeveiculoField)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(nomebicoLabel1)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(idcombustivelLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(4, 4, 4)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(inicialField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(fieldMarcador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(idcombustivelLabel)
                                                        .addComponent(idcombustivelLabel1))
                                                    .addGap(13, 13, 13)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(rdHodometro)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(rdHorimetro)))))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(ckAtivo)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtRecarregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(identField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idbicoLabel)
                    .addComponent(lbQtdeRegistros))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeveiculoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomebicoLabel))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomebicoLabel1)
                            .addComponent(rdHodometro)
                            .addComponent(rdHorimetro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(inicialField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldMarcador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idcombustivelLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idcombustivelLabel))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idcombustivelLabel1)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckAtivo))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtRecarregar)
                    .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.KeyListener, java.awt.event.MouseListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jbtSalvar) {
                VeiculosGUI.this.jbtSalvarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtRecarregar) {
                VeiculosGUI.this.jbtRecarregarActionPerformed(evt);
            }
            else if (evt.getSource() == jbtNovo) {
                VeiculosGUI.this.jbtNovoActionPerformed(evt);
            }
            else if (evt.getSource() == jbtExcluir) {
                VeiculosGUI.this.jbtExcluirActionPerformed(evt);
            }
            else if (evt.getSource() == ckAtivo) {
                VeiculosGUI.this.ckAtivoActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBox2) {
                VeiculosGUI.this.jCheckBox2ActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBox3) {
                VeiculosGUI.this.jCheckBox3ActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBox4) {
                VeiculosGUI.this.jCheckBox4ActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBox5) {
                VeiculosGUI.this.jCheckBox5ActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBox6) {
                VeiculosGUI.this.jCheckBox6ActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBox7) {
                VeiculosGUI.this.jCheckBox7ActionPerformed(evt);
            }
        }

        public void keyPressed(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == nomeveiculoField) {
                VeiculosGUI.this.nomeveiculoFieldKeyPressed(evt);
            }
        }

        public void keyReleased(java.awt.event.KeyEvent evt) {
        }

        public void keyTyped(java.awt.event.KeyEvent evt) {
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == masterTable) {
                VeiculosGUI.this.masterTableMouseClicked(evt);
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
                VeiculosDB.excluirVeiculo(Integer.parseInt(seq.getText()));
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

        if (nomeveiculoField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome não informado");
            nomeveiculoField.requestFocus();
            return false;
        }
        if (jComboBox1.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Combustível não informado");
            jComboBox1.requestFocus();
            return false;
        }
        if (jComboBox2.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Frota não informada");
            jComboBox2.requestFocus();
            return false;
        }
        return true;
    }

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        try {
            if (verificaCamposObrigatorios()) {
                Veiculo v = new Veiculo();
                if (!seq.getText().trim().equals("")) {
                    v.setSeqVeiculo(Integer.valueOf(seq.getText().trim()));
                }

                if (!identField.getText().trim().equals("")) {
                    v.setIdentVeiculo(identField.getText().trim());
                } else {
                    v.setIdentVeiculo("");
                }
                if (rdHodometro.isSelected()) {
                    v.setTipo("O");
                } else {
                    v.setTipo("H");
                }
                v.setNomeVeiculo(nomeveiculoField.getText());
                v.setCombustivel((Combustivel) jComboBox1.getModel().getSelectedItem());
                v.setFrota((Frota) jComboBox2.getModel().getSelectedItem());
                if (ckAtivo.isSelected()) {
                    v.setSituacao("A");
                } else {
                    v.setSituacao("I");
                }
                if (limiteField.getText().trim().equals("")) {
                    v.setLimite(0.0);
                } else {
                    v.setLimite(Double.parseDouble(limiteField.getText().replace(",", ".")));
                }

                /////////
                if (limiteField1.getText().trim().equals("")) {
                    v.setLimiteSemanal(0.0);
                } else {
                    v.setLimiteSemanal(Double.parseDouble(limiteField1.getText().replace(",", ".")));
                }

                if (limiteField2.getText().trim().equals("")) {
                    v.setRestanteSemanal(0.0);
                } else {
                    v.setRestanteSemanal(Double.parseDouble(limiteField2.getText().replace(",", ".")));
                }

                String diaRen = jComboBox3.getSelectedItem().toString();
                if (diaRen.equals("Selecione")) {
                    v.setDiaRenovacao("Selecione");
                } else {
                    v.setDiaRenovacao(diaRen);
                }

                if (inicialField.getText().trim().equals("")) {
                    v.setValorInicial(0L);
                } else {
                    v.setValorInicial(Long.parseLong(inicialField.getText()));
                }

                if (jCheckBox1.isSelected()) {
                    v.setSegunda("S");
                } else {
                    v.setSegunda("N");
                }

                if (jCheckBox2.isSelected()) {
                    v.setTerca("S");
                } else {
                    v.setTerca("N");
                }

                if (jCheckBox3.isSelected()) {
                    v.setQuarta("S");
                } else {
                    v.setQuarta("N");
                }

                if (jCheckBox4.isSelected()) {
                    v.setQuinta("S");
                } else {
                    v.setQuinta("N");
                }

                if (jCheckBox5.isSelected()) {
                    v.setSexta("S");
                } else {
                    v.setSexta("N");
                }

                if (jCheckBox6.isSelected()) {
                    v.setSabado("S");
                } else {
                    v.setSabado("N");
                }

                if (jCheckBox7.isSelected()) {
                    v.setDomingo("S");
                } else {
                    v.setDomingo("N");
                }

                if (VeiculosDB.insertOrUpdateVeiculo(v)) {
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
                Veiculo veiculo = VeiculosDB.buscaVeiculo(Integer.parseInt(cf.toString()));
                if (veiculo != null) {
                    mostrar(veiculo);
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

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void ckAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckAtivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckAtivoActionPerformed

    private void nomeveiculoFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeveiculoFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            carregaGridPesquisa(nomeveiculoField.getText().trim());
        }
    }//GEN-LAST:event_nomeveiculoFieldKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox ckAtivo;
    private javax.swing.JTextField fieldMarcador;
    private javax.swing.JLabel idbicoLabel;
    private javax.swing.JLabel idcombustivelLabel;
    private javax.swing.JLabel idcombustivelLabel1;
    private javax.swing.JLabel idcombustivelLabel2;
    private javax.swing.JLabel idcombustivelLabel3;
    private javax.swing.JLabel idcombustivelLabel4;
    private javax.swing.JLabel idcombustivelLabel5;
    private javax.swing.JLabel idcombustivelLabel6;
    private javax.swing.JLabel idcombustivelLabel7;
    private javax.swing.JTextField identField;
    private javax.swing.JTextField inicialField;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtExcluir;
    private javax.swing.JButton jbtNovo;
    private javax.swing.JButton jbtRecarregar;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JLabel jlbTituloCadastroExame2;
    private javax.swing.JPanel jplTitulo2;
    private javax.swing.JLabel lbQtdeRegistros;
    private javax.swing.JFormattedTextField limiteField;
    private javax.swing.JFormattedTextField limiteField1;
    private javax.swing.JFormattedTextField limiteField2;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JLabel nomebicoLabel;
    private javax.swing.JLabel nomebicoLabel1;
    private javax.swing.JLabel nomebicoLabel2;
    private javax.swing.JLabel nomebicoLabel3;
    private javax.swing.JLabel nomebicoLabel4;
    private javax.swing.JTextField nomeveiculoField;
    private javax.swing.JRadioButton rdHodometro;
    private javax.swing.JRadioButton rdHorimetro;
    private javax.swing.JTextField seq;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new VeiculosGUI());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

    private void carregaGrid() {
        try {
            masterTable.setModel(new VeiculosTableModel());
            masterTable.setAutoCreateRowSorter(false);
            masterTable.setRowHeight(20);
            TableColumnModel columnModel = masterTable.getColumnModel();
            FrotaTableCellRender frotaRender = new FrotaTableCellRender();
            columnModel.getColumn(2).setPreferredWidth(190);
            columnModel.getColumn(3).setCellRenderer(frotaRender);

            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);

            getModelVeiculo().limpar();
            getModelVeiculo().addListaVeiculos(VeiculosDB.buscaVeiculos(true));
            lbQtdeRegistros.setText("Quantidade de Registros: " + masterTable.getRowCount());

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }

    private void carregaGridPesquisa(String nome) {
        try {
            masterTable.setModel(new VeiculosTableModel());
            masterTable.setAutoCreateRowSorter(false);
            masterTable.setRowHeight(20);
            TableColumnModel columnModel = masterTable.getColumnModel();
            FrotaTableCellRender frotaRender = new FrotaTableCellRender();
            columnModel.getColumn(2).setPreferredWidth(190);
            columnModel.getColumn(3).setCellRenderer(frotaRender);

            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);

            getModelVeiculo().limpar();
            getModelVeiculo().addListaVeiculos(VeiculosDB.buscaVeiculosNome(true, nome));
            lbQtdeRegistros.setText("Quantidade de Registros: " + masterTable.getRowCount());
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
    }

    private void carregaComboCombustiveis() {
        jComboBox1.removeAllItems();
        Iterator iterator = CombustiveisDB.buscaCombustiveis(false).iterator();
        while (iterator.hasNext()) {
            Combustivel c = (Combustivel) iterator.next();
            jComboBox1.addItem(c);
        }
    }

    private void carregaComboFrotas() {
        jComboBox2.removeAllItems();
        Iterator iterator = FrotasDB.buscaFrotas(false).iterator();
        while (iterator.hasNext()) {
            Frota f = (Frota) iterator.next();
            jComboBox2.addItem(f);
        }
    }

    private void enableCampos() {
        identField.setEnabled(true);
        nomeveiculoField.setEnabled(true);
        limiteField.setEnabled(true);
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);
        ckAtivo.setEnabled(true);
        rdHodometro.setEnabled(true);
        rdHorimetro.setEnabled(true);
        inicialField.setEnabled(true);

        jCheckBox1.setEnabled(true);
        jCheckBox2.setEnabled(true);
        jCheckBox3.setEnabled(true);
        jCheckBox4.setEnabled(true);
        jCheckBox5.setEnabled(true);
        jCheckBox6.setEnabled(true);
        jCheckBox7.setEnabled(true);
        limiteField1.setEnabled(true);
        limiteField2.setEnabled(true);
        jComboBox3.setEnabled(true);

    }

    private void limpaCampos() {

        limiteField1.setText("0,0");
        limiteField2.setText("0,0");
        jComboBox3.setSelectedIndex(0);

        seq.setText("");
        fieldMarcador.setText("0");
        identField.setText("");
        nomeveiculoField.setText("");
        limiteField.setText("");
        ckAtivo.setSelected(true);
        if (jComboBox1.getItemCount() == 1) {
            jComboBox1.setSelectedIndex(0);
        } else {
            jComboBox1.setSelectedIndex(-1);
        }
        if (jComboBox2.getItemCount() == 1) {
            jComboBox2.setSelectedIndex(0);
        } else {
            jComboBox2.setSelectedIndex(-1);
        }
        rdHodometro.setSelected(true);
        rdHorimetro.setSelected(false);
        inicialField.setText("");

        jCheckBox1.setSelected(true);
        jCheckBox2.setSelected(true);
        jCheckBox3.setSelected(true);
        jCheckBox4.setSelected(true);
        jCheckBox5.setSelected(true);
        jCheckBox6.setSelected(true);
        jCheckBox7.setSelected(true);
    }

    private void disableCampos() {
        identField.setEnabled(false);
        //nomeveiculoField.setEnabled(false);
        limiteField.setEnabled(false);
        jComboBox1.setEnabled(false);
        jComboBox2.setEnabled(false);
        ckAtivo.setEnabled(false);
        rdHodometro.setEnabled(false);
        rdHorimetro.setEnabled(false);
        inicialField.setEnabled(false);

        jCheckBox1.setEnabled(false);
        jCheckBox2.setEnabled(false);
        jCheckBox3.setEnabled(false);
        jCheckBox4.setEnabled(false);
        jCheckBox5.setEnabled(false);
        jCheckBox6.setEnabled(false);
        jCheckBox7.setEnabled(false);

        limiteField1.setEnabled(false);
        limiteField2.setEnabled(false);
        jComboBox3.setEnabled(false);
    }

    public void mostrar(Veiculo veiculo) {
        seq.setText(veiculo.getSeqVeiculo().toString());
        identField.setText(veiculo.getIdentVeiculo());
        nomeveiculoField.setText(veiculo.getNomeVeiculo());
        //limiteField.setText(veiculo.getLimite().toString().replace(".", ","));

        limiteField.setText(FormatacaoDados.DoubleFormat(veiculo.getLimite()));
        inicialField.setText(veiculo.getValorInicial().toString().replace(".", ","));

        limiteField1.setText(veiculo.getLimiteSemanal().toString().replace(".", ","));
        limiteField2.setText(veiculo.getRestanteSemanal().toString().replace(".", ","));
        String diaRen = veiculo.getDiaRenovacao();

        switch (diaRen) {
            case "Selecione":
                jComboBox3.setSelectedIndex(0);
                break;
            case "Domingo":
                jComboBox3.setSelectedIndex(1);
                break;
            case "Segunda":
                jComboBox3.setSelectedIndex(2);
                break;
            case "Terça":
                jComboBox3.setSelectedIndex(3);
                break;
            case "Quarta":
                jComboBox3.setSelectedIndex(4);
                break;
            case "Quinta":
                jComboBox3.setSelectedIndex(5);
                break;
            case "Sexta":
                jComboBox3.setSelectedIndex(6);
                break;
            case "Sábado":
                jComboBox3.setSelectedIndex(7);
                break;
            default:
                jComboBox3.setSelectedIndex(0);
                break;
        }

        if (veiculo.getSituacao().equals("A")) {
            ckAtivo.setSelected(true);
        } else {
            ckAtivo.setSelected(false);
        }

        jComboBox1.getModel().setSelectedItem(veiculo.getCombustivel());
        for (int i = 0; i < jComboBox1.getItemCount(); i++) {
            if (((Combustivel) jComboBox1.getItemAt(i)).getSeqCombustivel().equals(veiculo.getCombustivel().getSeqCombustivel())) {
                jComboBox1.setSelectedIndex(i);
            }
        }

        jComboBox2.getModel().setSelectedItem(veiculo.getCombustivel());
        for (int i = 0; i < jComboBox2.getItemCount(); i++) {
            if (((Frota) jComboBox2.getItemAt(i)).getSeqFrota().equals(veiculo.getFrota().getSeqFrota())) {
                jComboBox2.setSelectedIndex(i);
            }
        }

        if (veiculo.getTipo().equals("O")) {
            rdHodometro.setSelected(true);
            rdHorimetro.setSelected(false);
            fieldMarcador.setText(veiculo.getHodometroAtual().toString().replace(".", ","));
        } else {
            rdHodometro.setSelected(false);
            rdHorimetro.setSelected(true);
            fieldMarcador.setText(veiculo.getHorimetroAtual().toString().replace(".", ","));
        }

        if (veiculo.getSegunda().equals("S")) {
            jCheckBox1.setSelected(true);
        } else {
            jCheckBox1.setSelected(false);
        }

        if (veiculo.getTerca().equals("S")) {
            jCheckBox2.setSelected(true);
        } else {
            jCheckBox2.setSelected(false);
        }

        if (veiculo.getQuarta().equals("S")) {
            jCheckBox3.setSelected(true);
        } else {
            jCheckBox3.setSelected(false);
        }

        if (veiculo.getQuinta().equals("S")) {
            jCheckBox4.setSelected(true);
        } else {
            jCheckBox4.setSelected(false);
        }

        if (veiculo.getSexta().equals("S")) {
            jCheckBox5.setSelected(true);
        } else {
            jCheckBox5.setSelected(false);
        }

        if (veiculo.getSabado().equals("S")) {
            jCheckBox6.setSelected(true);
        } else {
            jCheckBox6.setSelected(false);
        }

        if (veiculo.getDomingo().equals("S")) {
            jCheckBox7.setSelected(true);
        } else {
            jCheckBox7.setSelected(false);
        }

        identField.requestFocus();
    }
}
