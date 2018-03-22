package view.movimentacao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import model.*;
import render.*;
import util.*;
import bd.*;
import ctr.GlobalParameter;
import java.io.FileInputStream;
import java.util.Properties;
import tablemodel.MarcadoresInconsistentesTableModel;

public class MarcadoresInconsistentesGUI extends javax.swing.JDialog {

    private MarcadoresInconsistentesTableModel alterarAbastecTableModel;
    private String paramMotorista = "N";

    public MarcadoresInconsistentesTableModel getModelAbastec() {
        if (alterarAbastecTableModel == null) {
            alterarAbastecTableModel = (MarcadoresInconsistentesTableModel) masterTable.getModel();
        }
        return alterarAbastecTableModel;
    }

    public MarcadoresInconsistentesGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
        setIconImage(icon.getImage());

        jLabel1.setVisible(false);
        seq.setVisible(false);

        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(GlobalParameter.getCaminhoAplicacao() + "config.properties");
            properties.load(fis);

            if (properties.getProperty("sis_controla_motorista").trim() != null) {
                if (properties.getProperty("sis_controla_motorista").trim().toUpperCase().equals("S")) {
                    paramMotorista = "S";
                    comboMotorista.setVisible(true);
                    idcombustivelLabel9.setVisible(true);
                } else {
                    paramMotorista = "N";
                    comboMotorista.setVisible(false);
                    idcombustivelLabel9.setVisible(false);
                }
            } else {
                paramMotorista = "N";
                comboMotorista.setVisible(false);
                idcombustivelLabel9.setVisible(false);
            }

            marcadorAntField.setDocument(new OnlyNumberFieldDocument(6));
            marcadorAtualField.setDocument(new OnlyNumberFieldDocument(6));
//
//            carregaComboEmpresas();
//            carregaComboFrotas();
//            carregaComboVeiculos();
            carregaComboMotoristas();

            comboEmpresa.setRenderer(new EmpresaListRender());
            comboFrota.setRenderer(new FrotaListRender());
            comboVeiculo.setRenderer(new VeiculoListRender());
            comboBico.setRenderer(new BicoListRender());
            comboOperador.setRenderer(new OperadorListRender());
            comboMotorista.setRenderer(new MotoristaListRender());

            desabilitaCampos();
            carregaComboTipos();
            marcadorAtualField.setDocument(new OnlyNumberFieldDocument(14));
            jbtVisualizar.doClick();
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
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

    private void carregaComboTipos() {
        comboTipo.removeAllItems();
        comboTipo.addItem("Odômetro");
        comboTipo.addItem("Horímetro");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplTitulo = new javax.swing.JPanel();
        jlbTituloJanela = new javax.swing.JLabel();
        jplOpcoes = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jbtVisualizar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        seq = new javax.swing.JTextField();
        idcombustivelLabel4 = new javax.swing.JLabel();
        dataField = new org.jdesktop.swingx.JXDatePicker();
        idcombustivelLabel = new javax.swing.JLabel();
        comboBico = new javax.swing.JComboBox();
        idcombustivelLabel2 = new javax.swing.JLabel();
        comboVeiculo = new javax.swing.JComboBox();
        nomebicoLabel2 = new javax.swing.JLabel();
        marcadorAntField = new javax.swing.JTextField();
        idcombustivelLabel3 = new javax.swing.JLabel();
        volumeField = new DecimalFormattedField(DecimalFormattedField.REAL);
        idcombustivelLabel6 = new javax.swing.JLabel();
        horaField = new TimeFormattedField(TimeFormattedField.HORA);
        idcombustivelLabel5 = new javax.swing.JLabel();
        numeroAbastec = new javax.swing.JTextField();
        idcombustivelLabel1 = new javax.swing.JLabel();
        comboOperador = new javax.swing.JComboBox();
        nomebicoLabel1 = new javax.swing.JLabel();
        encerranteField = new DecimalFormattedField(DecimalFormattedField.REAL);
        jbtSalvar = new javax.swing.JButton();
        nomebicoLabel3 = new javax.swing.JLabel();
        marcadorAtualField = new javax.swing.JTextField();
        idcombustivelLabel7 = new javax.swing.JLabel();
        comboFrota = new javax.swing.JComboBox();
        comboEmpresa = new javax.swing.JComboBox();
        idcombustivelLabel8 = new javax.swing.JLabel();
        nomebicoLabel4 = new javax.swing.JLabel();
        mediaField = new javax.swing.JFormattedTextField();
        jlbDataInicial4 = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox();
        idcombustivelLabel9 = new javax.swing.JLabel();
        comboMotorista = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de odômetros e horímetros inconsistentes");

        jplTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloJanela.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloJanela.setText("Consulta de odômetros e horímetros inconsistentes");

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

        masterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        masterTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(masterTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Duplo clique para selecionar e alterar o registro.");

        jbtVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisa.png"))); // NOI18N
        jbtVisualizar.setText("Pesquisar");
        jbtVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtVisualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtVisualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pesquisa", jPanel1);

        idcombustivelLabel4.setText("Data");

        idcombustivelLabel.setText("Estação de Abastecimento");

        idcombustivelLabel2.setText("Veículo");

        nomebicoLabel2.setText("Odômetro/Horímetro Anterior");

        marcadorAntField.setText("0");

        idcombustivelLabel3.setText("Volume");

        volumeField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        volumeField.setText("0");

        idcombustivelLabel6.setText("Hora");

        horaField.setText("00:00");

        idcombustivelLabel5.setText("Número");

        numeroAbastec.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        numeroAbastec.setText("0");

        idcombustivelLabel1.setText("Operador");

        nomebicoLabel1.setText("Encerrante");

        encerranteField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        encerranteField.setText("0");

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

        nomebicoLabel3.setText("Odômetro/Horímetro Atual");

        marcadorAtualField.setText("0");
        marcadorAtualField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                marcadorAtualFieldFocusLost(evt);
            }
        });

        idcombustivelLabel7.setText("Frota");

        idcombustivelLabel8.setText("Empresa");

        nomebicoLabel4.setText("Média");

        mediaField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        mediaField.setText("0");

        jlbDataInicial4.setText("Tipo");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        idcombustivelLabel9.setText("Motorista");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addGap(37, 37, 37)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(idcombustivelLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(numeroAbastec)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomebicoLabel2)
                                    .addComponent(marcadorAntField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomebicoLabel3)
                                    .addComponent(marcadorAtualField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomebicoLabel4)
                                    .addComponent(mediaField, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(comboBico, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(volumeField))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(idcombustivelLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(176, 176, 176)
                                        .addComponent(idcombustivelLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(idcombustivelLabel)
                                        .addGap(91, 91, 91)
                                        .addComponent(idcombustivelLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(comboVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboFrota, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(idcombustivelLabel1)
                            .addComponent(nomebicoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(encerranteField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idcombustivelLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboEmpresa, 0, 262, Short.MAX_VALUE)
                            .addComponent(comboOperador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbDataInicial4)
                            .addComponent(comboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(55, 55, 55))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idcombustivelLabel9)
                            .addComponent(comboMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(idcombustivelLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idcombustivelLabel)
                            .addComponent(idcombustivelLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(volumeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(nomebicoLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(encerranteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idcombustivelLabel2)
                            .addComponent(idcombustivelLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboFrota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(idcombustivelLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(nomebicoLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(marcadorAntField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(nomebicoLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(marcadorAtualField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mediaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomebicoLabel4)
                            .addComponent(jlbDataInicial4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)))
                .addGap(7, 7, 7)
                .addComponent(idcombustivelLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(130, 130, 130))
        );

        jTabbedPane1.addTab("Abastecimento", jPanel2);

        javax.swing.GroupLayout jplOpcoesLayout = new javax.swing.GroupLayout(jplOpcoes);
        jplOpcoes.setLayout(jplOpcoesLayout);
        jplOpcoesLayout.setHorizontalGroup(
            jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplOpcoesLayout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jplOpcoesLayout.setVerticalGroup(
            jplOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplOpcoesLayout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jplOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jplTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jplTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVisualizarActionPerformed
        try {
            carregaGrid();
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
}//GEN-LAST:event_jbtVisualizarActionPerformed

    private void carregaGrid() {
        try {
            masterTable.setModel(new MarcadoresInconsistentesTableModel());
            masterTable.setAutoCreateRowSorter(false);
            masterTable.setRowHeight(20);
            TableColumnModel columnModel = masterTable.getColumnModel();
            BicoTableCellRender bicoRender = new BicoTableCellRender();
            VeiculoTableCellRender veiculoRender = new VeiculoTableCellRender();

            TimestampTableRender timestampRenderer = new TimestampTableRender();

            columnModel.getColumn(1).setCellRenderer(timestampRenderer);
            columnModel.getColumn(2).setCellRenderer(bicoRender);

            columnModel.getColumn(6).setCellRenderer(veiculoRender);

            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);

            columnModel.getColumn(1).setPreferredWidth(120);

            getModelAbastec().limpar();

            getModelAbastec().addListaAbastecimentos(AbastecimentosDB.buscaAbastecimentoInconsistente());
            if (getModelAbastec().getRowCount() > 0) {
                jLabel1.setVisible(true);
            } else {
                jLabel1.setVisible(false);
            }

        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
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
        try {
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
            //volumeField.setText(abastec.getVolume().toString().replace(".", ","));
            //encerranteField.setText(abastec.getEncerranteLitros().toString().replace(".", ","));

            volumeField.setText(FormatacaoDados.DoubleFormat(abastec.getVolume()));
            encerranteField.setText(FormatacaoDados.DoubleFormat(abastec.getEncerranteLitros()));

            if (abastec.getVeiculo() != null) {
                if (abastec.getVeiculo().getTipo() != null) {
                    if (abastec.getVeiculo().getTipo().equals("O")) {
                        comboTipo.setSelectedIndex(0);
                    } else {//Horímetro
                        comboTipo.setSelectedIndex(1);
                    }
                } else {
                    comboTipo.setSelectedIndex(-1);
                }
            } else {
                comboTipo.setSelectedIndex(-1);
            }
            if (abastec.getVeiculo() != null) {
                if (abastec.getVeiculo().getTipo() != null) {
                    if (abastec.getVeiculo().getTipo().equals("O")) {
                        marcadorAtualField.setText(abastec.getHodometro().toString());
                        marcadorAntField.setText(abastec.getHodometroAnterior().toString());
                        mediaField.setText(FormatacaoDados.DoubleFormat(abastec.getKmMedio()));
                    } else {
                        marcadorAtualField.setText(abastec.getHorimetro().toString());
                        marcadorAntField.setText(abastec.getHorimetroAnterior().toString());
                        mediaField.setText(FormatacaoDados.DoubleFormat(abastec.getTempoMedio()));
                    }
                } else {
                    marcadorAtualField.setText(abastec.getHorimetro().toString());
                    marcadorAntField.setText(abastec.getHorimetroAnterior().toString());
                    mediaField.setText(FormatacaoDados.DoubleFormat(abastec.getTempoMedio()));
                }
            }

            comboBico.getModel().setSelectedItem(abastec.getBico());
            for (int i = 0; i < comboBico.getItemCount(); i++) {
                if (((Bico) comboBico.getItemAt(i)).getSeqBico().equals(abastec.getBico().getSeqBico())) {
                    comboBico.setSelectedIndex(i);
                }
            }

            if (abastec.getVeiculo() != null) {
                if (abastec.getVeiculo().getTipo() != null) {
                    comboVeiculo.getModel().setSelectedItem(abastec.getVeiculo());
                    for (int i = 0; i < comboVeiculo.getItemCount(); i++) {
                        if (((Veiculo) comboVeiculo.getItemAt(i)).getSeqVeiculo().equals(abastec.getVeiculo().getSeqVeiculo())) {
                            comboVeiculo.setSelectedIndex(i);
                        }
                    }
                } else {
                    comboVeiculo.setSelectedIndex(-1);
                }
            }

            if (abastec.getVeiculo() != null) {
                if (abastec.getVeiculo().getTipo() != null) {
                    comboFrota.getModel().setSelectedItem(abastec.getVeiculo().getFrota());
                    for (int i = 1; i < comboFrota.getItemCount(); i++) {
                        if (((Frota) comboFrota.getItemAt(i)).getSeqFrota() == (abastec.getVeiculo().getFrota().getSeqFrota())) {
                            comboFrota.setSelectedIndex(i);
                        }
                    }
                } else {
                    comboFrota.setSelectedIndex(-1);
                }
            }

            if (abastec.getVeiculo() != null) {
                if (abastec.getVeiculo().getTipo() != null) {
                    comboEmpresa.getModel().setSelectedItem(abastec.getVeiculo().getFrota().getEmpresa());
                    for (int i = 1; i < comboEmpresa.getItemCount(); i++) {
                        if (((Empresa) comboEmpresa.getItemAt(i)).getSeqEmpresa() == (abastec.getVeiculo().getFrota().getEmpresa().getSeqEmpresa())) {
                            comboEmpresa.setSelectedIndex(i);
                        }
                    }
                } else {
                    comboEmpresa.setSelectedIndex(-1);
                }
            }

            comboOperador.getModel().setSelectedItem(abastec.getOperador());
            for (int i = 0; i < comboOperador.getItemCount(); i++) {
                if (((Operador) comboOperador.getItemAt(i)).getSeqOperador().equals(abastec.getOperador().getSeqOperador())) {
                    comboOperador.setSelectedIndex(i);
                }
            }

            if (abastec.getMotorista().getSeqMotorista() > 0) {
                comboMotorista.getModel().setSelectedItem(abastec.getMotorista());
                for (int i = 1; i < comboMotorista.getItemCount(); i++) {
                    if (((Motorista) comboMotorista.getItemAt(i)).getSeqMotorista().equals(abastec.getMotorista().getSeqMotorista())) {
                        comboMotorista.setSelectedIndex(i);
                    }
                }
            } else {
                comboMotorista.setSelectedIndex(-1);
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        if (seq.getText().trim().equals("")) {
            return;
        }
        try {
            Integer idAbastec = Integer.parseInt(seq.getText());

            String tipoVeiculo;
            if (comboTipo.getSelectedIndex() == 0) {
                tipoVeiculo = "O";
            } else {
                tipoVeiculo = "H";
            }

            if (verificaCamposObrigatorios()) {
                Double media = Double.parseDouble(mediaField.getText().replace(",", "."));
                Long marcadorAtual = Long.parseLong(marcadorAtualField.getText());
                Long marcadorAnterior = Long.parseLong(marcadorAntField.getText());
                if (AbastecimentosDB.atualizaMarcador(idAbastec, marcadorAnterior, marcadorAtual, media, tipoVeiculo)) {
                    jTabbedPane1.setSelectedIndex(0);
                    jbtVisualizar.doClick();
                }
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção!!!");
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    private void marcadorAtualFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_marcadorAtualFieldFocusLost
        if (marcadorAtualField.getText().trim().equals("")) {
            marcadorAtualField.setText("0");
        }

        if (marcadorAntField.getText().equals("")) {
            marcadorAntField.setText("0");
        }
        Long anterior = Long.parseLong(marcadorAntField.getText());
        Long atual = Long.parseLong(marcadorAtualField.getText());
        Long diferenca = atual - anterior;
        Double volume = Double.parseDouble(volumeField.getText().replace(",", "."));
        if (volume > 0) {
            Double media = diferenca / volume;
            mediaField.setText(FormatacaoDados.DoubleFormat(media).replace(".", ","));
        } else {
            mediaField.setText("0,00");
        }

    }//GEN-LAST:event_marcadorAtualFieldFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboBico;
    private javax.swing.JComboBox comboEmpresa;
    private javax.swing.JComboBox comboFrota;
    private javax.swing.JComboBox comboMotorista;
    private javax.swing.JComboBox comboOperador;
    private javax.swing.JComboBox comboTipo;
    private javax.swing.JComboBox comboVeiculo;
    private org.jdesktop.swingx.JXDatePicker dataField;
    private javax.swing.JTextField encerranteField;
    private javax.swing.JFormattedTextField horaField;
    private javax.swing.JLabel idcombustivelLabel;
    private javax.swing.JLabel idcombustivelLabel1;
    private javax.swing.JLabel idcombustivelLabel2;
    private javax.swing.JLabel idcombustivelLabel3;
    private javax.swing.JLabel idcombustivelLabel4;
    private javax.swing.JLabel idcombustivelLabel5;
    private javax.swing.JLabel idcombustivelLabel6;
    private javax.swing.JLabel idcombustivelLabel7;
    private javax.swing.JLabel idcombustivelLabel8;
    private javax.swing.JLabel idcombustivelLabel9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JButton jbtVisualizar;
    private javax.swing.JLabel jlbDataInicial4;
    private javax.swing.JLabel jlbTituloJanela;
    private javax.swing.JPanel jplOpcoes;
    private javax.swing.JPanel jplTitulo;
    private javax.swing.JTextField marcadorAntField;
    private javax.swing.JTextField marcadorAtualField;
    private javax.swing.JTable masterTable;
    private javax.swing.JFormattedTextField mediaField;
    private javax.swing.JLabel nomebicoLabel1;
    private javax.swing.JLabel nomebicoLabel2;
    private javax.swing.JLabel nomebicoLabel3;
    private javax.swing.JLabel nomebicoLabel4;
    private javax.swing.JTextField numeroAbastec;
    private javax.swing.JTextField seq;
    private javax.swing.JTextField volumeField;
    // End of variables declaration//GEN-END:variables
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            MarcadoresInconsistentesGUI dialog = new MarcadoresInconsistentesGUI(new javax.swing.JFrame(), true);
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
        dataField.setEnabled(false);
        horaField.setEnabled(false);
        numeroAbastec.setEnabled(false);
        volumeField.setEnabled(false);
        encerranteField.setEnabled(false);
        //marcadorAntField.setEnabled(false);
        //mediaField.setEnabled(false);
        comboFrota.setEnabled(false);
        comboEmpresa.setEnabled(false);
        comboBico.setEnabled(false);
        comboVeiculo.setEnabled(false);
        comboOperador.setEnabled(false);
        comboTipo.setEnabled(false);
        comboMotorista.setEnabled(false);
    }

    private boolean verificaCamposObrigatorios() {
        if (marcadorAtualField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Odômetro/Horímetro Atual não informado");
            marcadorAtualField.requestFocus();
            return false;
        }
        Long anterior = Long.parseLong(marcadorAntField.getText());
        Long atual = Long.parseLong(marcadorAtualField.getText());
        if (atual <= anterior) {
            JOptionPane.showMessageDialog(null, "Odômetro/Horímetro Atual deve ser maior que o anterior.");
            marcadorAtualField.requestFocus();
            return false;
        }
        return true;
    }
}
