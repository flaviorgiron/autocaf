package view.movimentacao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import model.*;
import render.*;
import tablemodel.*;
import util.*;
import bd.*;
import ctr.GlobalParameter;
import idioma.Idioma;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

public class AlterarAbastecGUI extends javax.swing.JDialog {

    private AlterarAbastecTableModel alterarAbastecTableModel;
    private String paramMotorista = "N";
    private static Idioma i;

    public AlterarAbastecTableModel getModelAbastec() {
        if (alterarAbastecTableModel == null) {
            alterarAbastecTableModel = (AlterarAbastecTableModel) masterTable.getModel();
        }
        return alterarAbastecTableModel;
    }

    public AlterarAbastecGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        i = GlobalParameter.getIdioma();
        initComponents();

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/abastec.png"));
        setIconImage(icon.getImage());

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

            jLabel1.setVisible(false);
            seq.setVisible(false);

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

            carregaComboEmpresas();
            carregaComboFrotas();
            carregaComboVeiculos();

            carregaComboBicos();
            carregaComboOperadores();
            carregaVeiculos();

            carregaComboMotoristas();

            jComboBox1.setRenderer(new EmpresaListRender());
            jComboBox2.setRenderer(new FrotaListRender());
            jComboBox3.setRenderer(new VeiculoListRender());

            comboEmpresa.setRenderer(new EmpresaListRender());
            comboFrota.setRenderer(new FrotaListRender());
            comboVeiculo.setRenderer(new VeiculoListRender());
            comboBico.setRenderer(new BicoListRender());
            comboOperador.setRenderer(new OperadorListRender());
            comboMotorista.setRenderer(new MotoristaListRender());

            //if (jComboBox1.getItemCount() == 2) {
            //    jComboBox1.setSelectedIndex(1);
            //} else {
            jComboBox1.setSelectedIndex(0);
            //}

            //if (jComboBox2.getItemCount() == 2) {
            //    jComboBox2.setSelectedIndex(1);
            //} else {
            jComboBox2.setSelectedIndex(0);
            //}

            //if (jComboBox3.getItemCount() == 2) {
            //    jComboBox3.setSelectedIndex(1);
            //} else {
            jComboBox3.setSelectedIndex(0);
            //}

            if (comboMotorista.getItemCount() == 2) {
                comboMotorista.setSelectedIndex(1);
            } else {
                comboMotorista.setSelectedIndex(0);
            }

            desabilitaCampos();
            carregaComboTipos();

            marcadorAntField.setDocument(new OnlyNumberFieldDocument(6));
            marcadorAtualField.setDocument(new OnlyNumberFieldDocument(6));

            mediaField.setEnabled(false);
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

    private void carregaComboTipos() {
        comboTipo.removeAllItems();
        comboTipo.addItem("Odômetro");
        comboTipo.addItem("Horímetro");
    }

    private void carregaComboEmpresas() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Todas");

        comboEmpresa.removeAllItems();
        comboEmpresa.addItem("Selecione");
        Iterator iterator = EmpresasDB.buscaEmpresas(false).iterator();
        while (iterator.hasNext()) {
            Empresa e = (Empresa) iterator.next();
            jComboBox1.addItem(e);
            comboEmpresa.addItem(e);
        }
    }

    private void carregaComboFrotas() {
        Empresa e;
        Iterator iterator;
        if (jComboBox1.getSelectedIndex() > 0) {
            e = (Empresa) jComboBox1.getSelectedItem();
            iterator = FrotasDB.buscaFrotasEmpresa(true, e.getSeqEmpresa()).iterator();
        } else {
            iterator = FrotasDB.buscaFrotas(true).iterator();
        }
        jComboBox2.removeAllItems();
        jComboBox2.addItem("Todas");

        comboFrota.removeAllItems();
        comboFrota.addItem("Selecione");

        while (iterator.hasNext()) {
            Frota f = (Frota) iterator.next();
            jComboBox2.addItem(f);
            comboFrota.addItem(f);
        }
    }

    private void carregaComboVeiculos() {
        Frota f;
        Empresa e;
        Iterator iterator;
        if (jComboBox2.getSelectedIndex() > 0) {
            f = (Frota) jComboBox2.getSelectedItem();
            iterator = VeiculosDB.buscaVeiculosFrota(true, f.getSeqFrota()).iterator();
        } else if (jComboBox1.getSelectedIndex() > 0) {
            e = (Empresa) jComboBox1.getSelectedItem();
            iterator = VeiculosDB.buscaVeiculosEmpresa(true, e.getSeqEmpresa()).iterator();
        } else {
            iterator = VeiculosDB.buscaVeiculos(true).iterator();
        }
        jComboBox3.removeAllItems();
        jComboBox3.addItem("Todos");

        while (iterator.hasNext()) {
            Veiculo v = (Veiculo) iterator.next();
            jComboBox3.addItem(v);
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
        jplOpcoes = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jplPeriodo = new javax.swing.JPanel();
        jdcDataInicial = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        jlbDataInicial = new javax.swing.JLabel();
        jdcDataFinal = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        jlbDataFinal = new javax.swing.JLabel();
        jplPeriodo1 = new javax.swing.JPanel();
        jlbDataInicial1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jlbDataInicial2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jlbDataInicial3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jbtVisualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
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
        nomebicoLabel5 = new javax.swing.JLabel();
        encerranteAntField = new DecimalFormattedField(DecimalFormattedField.REAL);
        idcombustivelLabel9 = new javax.swing.JLabel();
        comboMotorista = new javax.swing.JComboBox();
        jbtSalvar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar/Excluir Abastecimento");

        jplTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jlbTituloJanela.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jlbTituloJanela.setText("Modificar / Excluir Abastecimento");

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

        javax.swing.GroupLayout jplPeriodoLayout = new javax.swing.GroupLayout(jplPeriodo);
        jplPeriodo.setLayout(jplPeriodoLayout);
        jplPeriodoLayout.setHorizontalGroup(
            jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdcDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlbDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdcDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(360, 360, 360))
        );
        jplPeriodoLayout.setVerticalGroup(
            jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jplPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbDataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbDataFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdcDataInicial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdcDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jplPeriodo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlbDataInicial1.setText("Empresa");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jlbDataInicial2.setText("Frota");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jlbDataInicial3.setText("Veículo");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbtVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisa.png"))); // NOI18N
        jbtVisualizar.setText("Pesquisar");
        jbtVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVisualizarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Duplo clique para selecionar e alterar o registro.");

        javax.swing.GroupLayout jplPeriodo1Layout = new javax.swing.GroupLayout(jplPeriodo1);
        jplPeriodo1.setLayout(jplPeriodo1Layout);
        jplPeriodo1Layout.setHorizontalGroup(
            jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addComponent(jlbDataInicial3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbDataInicial1)
                            .addComponent(jlbDataInicial2))
                        .addGap(18, 18, 18)
                        .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtVisualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplPeriodo1Layout.setVerticalGroup(
            jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodo1Layout.createSequentialGroup()
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jlbDataInicial1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataInicial2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addGroup(jplPeriodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbDataInicial3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtVisualizar))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jplPeriodo1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jplPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jplPeriodo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplPeriodo1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
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

        nomebicoLabel2.setText("Odômetro/Horímetro Anterior");

        marcadorAntField.setText("0");
        marcadorAntField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                marcadorAntFieldFocusLost(evt);
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

        nomebicoLabel1.setText("Encerrante Atual");

        encerranteField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        encerranteField.setText("0");

        jbtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar.png"))); // NOI18N
        jbtSalvar.setText("Alterar");
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
        mediaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mediaFieldFocusGained(evt);
            }
        });

        jlbDataInicial4.setText("Tipo");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        nomebicoLabel5.setText("Encerrante Anterior");

        encerranteAntField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        encerranteAntField.setText("0");

        idcombustivelLabel9.setText("Motorista");

        jbtSalvar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        jbtSalvar1.setText("Excluir");
        jbtSalvar1.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jbtSalvar1.setMaximumSize(new java.awt.Dimension(89, 25));
        jbtSalvar1.setMinimumSize(new java.awt.Dimension(89, 25));
        jbtSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalvar1ActionPerformed(evt);
            }
        });

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
                                    .addComponent(comboBico, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idcombustivelLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idcombustivelLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(volumeField)))
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
                                    .addComponent(mediaField)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(comboVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboFrota, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(idcombustivelLabel)
                                        .addGap(88, 88, 88)
                                        .addComponent(idcombustivelLabel3)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(idcombustivelLabel1)
                            .addComponent(idcombustivelLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboEmpresa, 0, 262, Short.MAX_VALUE)
                            .addComponent(comboOperador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbDataInicial4)
                            .addComponent(comboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(encerranteAntField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomebicoLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(encerranteField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomebicoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(55, 55, 55))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idcombustivelLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(comboMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 370, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(encerranteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(nomebicoLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(encerranteAntField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(10, 10, 10)
                .addComponent(idcombustivelLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(131, 131, 131))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplOpcoesLayout.createSequentialGroup()
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
            e.printStackTrace();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção");
        }
}//GEN-LAST:event_jbtVisualizarActionPerformed

    private void carregaGrid() {
        try {
            masterTable.setModel(new AlterarAbastecTableModel());
            masterTable.setAutoCreateRowSorter(false);
            masterTable.setRowHeight(20);
            TableColumnModel columnModel = masterTable.getColumnModel();
            BicoTableCellRender bicoRender = new BicoTableCellRender();
            VeiculoTableCellRender veiculoRender = new VeiculoTableCellRender();
            OperadorTableCellRender operadorRender = new OperadorTableCellRender();

            FrotaTableCellRender frotaRender = new FrotaTableCellRender();
            EmpresaTableCellRender empresaRender = new EmpresaTableCellRender();

            TimestampTableRender timestampRenderer = new TimestampTableRender();
            TimeTableRender timeRenderer = new TimeTableRender();

            columnModel.getColumn(1).setCellRenderer(timestampRenderer);
            columnModel.getColumn(2).setCellRenderer(bicoRender);
            columnModel.getColumn(4).setCellRenderer(timeRenderer);

            columnModel.getColumn(5).setCellRenderer(veiculoRender);
            columnModel.getColumn(6).setCellRenderer(frotaRender);
            columnModel.getColumn(7).setCellRenderer(empresaRender);
            columnModel.getColumn(8).setCellRenderer(operadorRender);

            columnModel.getColumn(0).setMinWidth(0);
            columnModel.getColumn(0).setMaxWidth(0);

            columnModel.getColumn(1).setPreferredWidth(120);

            getModelAbastec().limpar();

            Empresa e;
            Veiculo v;
            Frota f;

            if (jComboBox1.getSelectedIndex() <= 0) {
                e = null;
            } else {
                e = (Empresa) jComboBox1.getSelectedItem();
            }
            if (jComboBox3.getSelectedIndex() <= 0) {
                v = null;
            } else {
                v = (Veiculo) jComboBox3.getSelectedItem();
            }

            if (jComboBox2.getSelectedIndex() <= 0) {
                f = null;
            } else {
                f = (Frota) jComboBox2.getSelectedItem();
            }

            getModelAbastec().addListaAbastecimentos(AbastecimentosDB.buscaAbastecimento(jdcDataInicial.getDate(), jdcDataFinal.getDate(), v, f, e));
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
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        carregaComboFrotas();
        carregaComboVeiculos();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        carregaComboVeiculos();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

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
            encerranteAntField.setText(FormatacaoDados.DoubleFormat(abastec.getEncerranteAnterior()));

            if (abastec.getVeiculo().getTipo() != null) {
                if (abastec.getVeiculo().getTipo().equals("O")) {
                    comboTipo.setSelectedIndex(0);
                } else {//Horímetro
                    comboTipo.setSelectedIndex(1);
                }
            } else {
                comboTipo.setSelectedIndex(-1);
            }

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
                marcadorAtualField.setText("0");
                marcadorAntField.setText("0");
                mediaField.setText("0");
            }

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

            comboFrota.getModel().setSelectedItem(abastec.getVeiculo().getFrota());
            for (int i = 1; i < comboFrota.getItemCount(); i++) {
                if (((Frota) comboFrota.getItemAt(i)).getSeqFrota().equals(abastec.getVeiculo().getFrota().getSeqFrota())) {
                    comboFrota.setSelectedIndex(i);
                }
            }

            comboEmpresa.getModel().setSelectedItem(abastec.getVeiculo().getFrota().getEmpresa());
            for (int i = 1; i < comboEmpresa.getItemCount(); i++) {
                if (((Empresa) comboEmpresa.getItemAt(i)).getSeqEmpresa().equals(abastec.getVeiculo().getFrota().getEmpresa().getSeqEmpresa())) {
                    comboEmpresa.setSelectedIndex(i);
                }
            }

            comboOperador.getModel().setSelectedItem(abastec.getOperador());
            for (int i = 0; i < comboOperador.getItemCount(); i++) {
                if (((Operador) comboOperador.getItemAt(i)).getSeqOperador().equals(abastec.getOperador().getSeqOperador())) {
                    comboOperador.setSelectedIndex(i);
                }
            }

            comboMotorista.getModel().setSelectedItem(abastec.getMotorista());
            for (int i = 1; i < comboMotorista.getItemCount(); i++) {
                if (((Motorista) comboMotorista.getItemAt(i)).getSeqMotorista().equals(abastec.getMotorista().getSeqMotorista())) {
                    comboMotorista.setSelectedIndex(i);
                }
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

        if (comboVeiculo.getSelectedIndex() <= -1) {
            JOptionPane.showMessageDialog(null, "Veículo não informado.");
            comboVeiculo.requestFocus();
            return;
        }

        if (paramMotorista.equals("S")) {
            if (comboMotorista.getSelectedIndex() <= -1) {
                JOptionPane.showMessageDialog(null, "Motorista não informado.");
                comboMotorista.requestFocus();
                return;
            }
        }

        try {
            Abastecimento a = new Abastecimento();
            Integer idAbastec = Integer.parseInt(seq.getText());
            a.setIdAbastecimento(idAbastec);

            //Double encerranteAnterior = 0.0;
            if (!seq.getText().trim().equals("")) {
                a.setIdAbastecimento(Integer.valueOf(seq.getText().trim()));
            }
            a.setBico((Bico) comboBico.getModel().getSelectedItem());
            a.setVeiculo((Veiculo) comboVeiculo.getModel().getSelectedItem());
            a.setOperador((Operador) comboOperador.getModel().getSelectedItem());

            a.setNumeroAbastecimento(Integer.parseInt(numeroAbastec.getText()));

            //frota
            //empresa
            //numero
            //data
            //hora
            //tipo
            a.setVolume(Double.valueOf(volumeField.getText().replace(",", ".")));
            a.setEncerranteLitros(Double.valueOf(encerranteField.getText().replace(",", ".")));
            a.setEncerranteAnterior(Double.valueOf(encerranteAntField.getText().replace(",", ".")));

            Timestamp t = new Timestamp(dataField.getDate().getTime());
            t.setHours(Integer.parseInt(horaField.getText().substring(0, 2)));
            t.setMinutes(Integer.parseInt(horaField.getText().substring(3, 5)));
            a.setDataHora(t);

            Preco p = PrecosDB.buscaUltimoPreco((Bico) comboBico.getModel().getSelectedItem());
            a.setPrecoUnitario(p.getPreco());
            a.setTotalPagar(p.getPreco() * Double.valueOf(volumeField.getText().replace(",", ".")));

            if (marcadorAtualField.getText().trim().equals("")) {
                marcadorAtualField.setText("0");
            }
            if (marcadorAntField.getText().trim().equals("")) {
                marcadorAntField.setText("0");
            }
            if (mediaField.getText().trim().equals("")) {
                mediaField.setText("0");
            }

            //Double media = Double.parseDouble(mediaField.getText().replace(",", "."));
            Long marcadorAtual = Long.parseLong(marcadorAtualField.getText());
            Long anterior = Long.parseLong(marcadorAntField.getText());

            //String tipoVeiculo;
            if (comboTipo.getSelectedIndex() == 0) {
                //tipoVeiculo = "O";
                a.setKmMedio(Double.parseDouble(mediaField.getText().replace(",", ".")));
                a.setHodometroAnterior(anterior);
                a.setHodometro(marcadorAtual);
            } else {
                //tipoVeiculo = "H";
                a.setTempoMedio(Double.parseDouble(mediaField.getText().replace(",", ".")));
                a.setHorimetroAnterior(anterior);
                a.setHorimetro(marcadorAtual);
            }

            if (!paramMotorista.equals("S")) {
                a.setMotorista(null);
            }

            if (verificaCamposObrigatorios()) {
                if (paramMotorista.equals("S")) {
                    a.setMotorista((Motorista) comboMotorista.getModel().getSelectedItem());
                }
                if (AbastecimentosDB.editarAbastec(a)) {
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
        calcularMedia();
    }//GEN-LAST:event_marcadorAtualFieldFocusLost

    private void comboVeiculoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboVeiculoItemStateChanged
        if (comboVeiculo.getSelectedIndex() != 0) {
            Veiculo v = (Veiculo) comboVeiculo.getModel().getSelectedItem();
            comboFrota.setSelectedItem(v.getFrota());
            comboEmpresa.setSelectedItem(v.getFrota().getEmpresa());
            if (v.getTipo() != null) {
                if (v.getTipo().equals("O")) {
                    comboTipo.setSelectedIndex(0);
                } else {
                    comboTipo.setSelectedIndex(1);
                }
            } else {
                comboTipo.setSelectedIndex(-1);
            }
        }
    }//GEN-LAST:event_comboVeiculoItemStateChanged

    private void marcadorAntFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_marcadorAntFieldFocusLost
        calcularMedia();
    }//GEN-LAST:event_marcadorAntFieldFocusLost

    private void mediaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mediaFieldFocusGained
        calcularMedia();
    }//GEN-LAST:event_mediaFieldFocusGained

    private void jbtSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvar1ActionPerformed
        if (seq.getText().trim().equals("")) {
            return;
        }
        try {
            int option = JConfirmMessage.showOptionDialog(i.getProperty("sis1"), i.getProperty("sis12"));
            if (option == 0) {
                try {
                    if (seq.getText().equals("")) {
                        JConfirmMessage.showMessageDialog(i.getProperty("sis13"), i.getProperty("sis1"));
                        return;
                    }
                    AbastecimentosDB.excluirAbastecimento(Integer.parseInt(seq.getText()));
                    JConfirmMessage.showMessageDialog("Registro excluído com sucesso.", i.getProperty("sis1"));
                    jTabbedPane1.setSelectedIndex(0);
                    jbtVisualizar.doClick();
                } catch (Exception e) {
                    GeraLog g = new GeraLog();
                    g.gravaErro(e);
                    g.close();
                    JConfirmMessage.showMessageDialog(e.getMessage(), i.getProperty("sis1"));
                }
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
            JConfirmMessage.showMessageDialog(e.getMessage(), "Atenção!!!");
        }
    }//GEN-LAST:event_jbtSalvar1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboBico;
    private javax.swing.JComboBox comboEmpresa;
    private javax.swing.JComboBox comboFrota;
    private javax.swing.JComboBox comboMotorista;
    private javax.swing.JComboBox comboOperador;
    private javax.swing.JComboBox comboTipo;
    private javax.swing.JComboBox comboVeiculo;
    private org.jdesktop.swingx.JXDatePicker dataField;
    private javax.swing.JTextField encerranteAntField;
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
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JButton jbtSalvar1;
    private javax.swing.JButton jbtVisualizar;
    private com.toedter.calendar.JDateChooser jdcDataFinal;
    private com.toedter.calendar.JDateChooser jdcDataInicial;
    private javax.swing.JLabel jlbDataFinal;
    private javax.swing.JLabel jlbDataInicial;
    private javax.swing.JLabel jlbDataInicial1;
    private javax.swing.JLabel jlbDataInicial2;
    private javax.swing.JLabel jlbDataInicial3;
    private javax.swing.JLabel jlbDataInicial4;
    private javax.swing.JLabel jlbTituloJanela;
    private javax.swing.JPanel jplOpcoes;
    private javax.swing.JPanel jplPeriodo;
    private javax.swing.JPanel jplPeriodo1;
    private javax.swing.JPanel jplTitulo;
    private javax.swing.JTextField marcadorAntField;
    private javax.swing.JTextField marcadorAtualField;
    private javax.swing.JTable masterTable;
    private javax.swing.JFormattedTextField mediaField;
    private javax.swing.JLabel nomebicoLabel1;
    private javax.swing.JLabel nomebicoLabel2;
    private javax.swing.JLabel nomebicoLabel3;
    private javax.swing.JLabel nomebicoLabel4;
    private javax.swing.JLabel nomebicoLabel5;
    private javax.swing.JTextField numeroAbastec;
    private javax.swing.JTextField seq;
    private javax.swing.JTextField volumeField;
    // End of variables declaration//GEN-END:variables
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            AlterarAbastecGUI dialog = new AlterarAbastecGUI(new javax.swing.JFrame(), true);
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
//        volumeField.setEnabled(false);
//        encerranteField.setEnabled(false);
//        marcadorAntField.setEnabled(false);
//        mediaField.setEnabled(false);
        comboFrota.setEnabled(false);
        comboEmpresa.setEnabled(false);
//        comboBico.setEnabled(false);
//        comboVeiculo.setEnabled(false);
//        comboOperador.setEnabled(false);
        comboTipo.setEnabled(false);
        //comboMotorista.setEnabled(false);
    }

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

        if (numeroAbastec.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Número do abastecimento não informado");
            numeroAbastec.requestFocus();
            return false;
        }

        if (comboOperador.getSelectedIndex() <= -1) {
            JOptionPane.showMessageDialog(null, "Operador não informado");
            comboOperador.requestFocus();
            return false;
        }

        if (comboBico.getSelectedIndex() < -1) {
            JOptionPane.showMessageDialog(null, "Estação de abastecimento não informado");
            comboBico.requestFocus();
            return false;
        }

        if ((volumeField.getText().trim().isEmpty()) || (volumeField.getText().trim().equals(FormatacaoDados.DoubleFormat(0)))) {
            JOptionPane.showMessageDialog(null, "Volume não informado");
            volumeField.requestFocus();
            return false;
        }

        if ((encerranteAntField.getText().trim().isEmpty()) || (encerranteAntField.getText().trim().equals(FormatacaoDados.DoubleFormat(0)))) {
            JOptionPane.showMessageDialog(null, "Encerrante Anterior não informado");
            encerranteAntField.requestFocus();
            return false;
        }

        if ((encerranteField.getText().trim().isEmpty()) || (encerranteField.getText().trim().equals(FormatacaoDados.DoubleFormat(0)))) {
            JOptionPane.showMessageDialog(null, "Encerrante não informado");
            encerranteField.requestFocus();
            return false;
        }

        if (comboVeiculo.getSelectedIndex() <= -1) {
            JOptionPane.showMessageDialog(null, "Veículo não informado");
            comboVeiculo.requestFocus();
            return false;
        }

        if (marcadorAntField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Odômetro/Horímetro Anterior não informado");
            marcadorAntField.requestFocus();
            return false;
        }

        if (marcadorAtualField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Odômetro/Horímetro Atual não informado");
            marcadorAtualField.requestFocus();
            return false;
        }
        if (paramMotorista.equals("S")) {
            if (comboMotorista.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(null, "Motorista não informado");
                comboMotorista.requestFocus();
                return false;
            }
        }

        return true;
    }

    private void calcularMedia() {
        try {
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
                if (comboTipo.getSelectedIndex() == 0) { //odometro
                    if (diferenca > 0) {
                        Double media = diferenca / volume;
                        BigDecimal bd = new BigDecimal(media).setScale(2, RoundingMode.HALF_EVEN);
                        mediaField.setText(FormatacaoDados.DoubleFormat(bd.doubleValue()).replace(".", ","));
                    } else {
                        mediaField.setText("0,00");
                    }
                } else if (diferenca > 0) { //horimetro
                    Double media = volume / diferenca;
                    BigDecimal bd = new BigDecimal(media).setScale(2, RoundingMode.HALF_EVEN);
                    mediaField.setText(FormatacaoDados.DoubleFormat(bd.doubleValue()).replace(".", ","));
                } else {
                    mediaField.setText("0,00");
                }
            } else {
                mediaField.setText("0,00");
            }
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
        }
    }
}
