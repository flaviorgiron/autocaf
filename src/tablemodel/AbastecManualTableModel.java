package tablemodel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.Abastecimento;
import model.Bico;
import model.Empresa;
import model.Frota;
import model.Operador;
import model.Veiculo;
import util.FormatacaoDados;

public class AbastecManualTableModel extends AbstractTableModel {

    private static List<Abastecimento> linhas;
    private static String[] cColunas = new String[]{"Seq", "Data/Hora", "Estação de Abastecimento", "Volume", "Veículo", "Frota", "Empresa", "Operador"};

    public AbastecManualTableModel() {
        linhas = new ArrayList<>();
    }

    public AbastecManualTableModel(List<Abastecimento> listaAbastecimentos) {
        linhas = new ArrayList<>(listaAbastecimentos);
    }

    @Override
    public int getColumnCount() {
        return cColunas.length;
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return cColunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return Integer.class; // seq
            case 1:
                return Timestamp.class; // data/hora
            case 2:
                return Bico.class;//bico
            case 3:
                return String.class;//volume
            case 4:
                return Veiculo.class;//veiculo
            case 5:
                return Frota.class;//frota
            case 6:
                return Empresa.class;//empresa
            case 7:
                return Operador.class;//operador
            default:
                throw new IndexOutOfBoundsException("AbastecManualTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Abastecimento abastecimento = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return abastecimento.getIdAbastecimento();
            case 1:
                return abastecimento.getDataHora();
            case 2:
                if (abastecimento.getBico() == null) {
                    return null;
                } else {
                    return abastecimento.getBico();
                }
            case 3:
                return FormatacaoDados.DoubleFormat(abastecimento.getVolume());
            case 4:
                if (abastecimento.getVeiculo() == null) {
                    return null;
                } else {
                    return abastecimento.getVeiculo();
                }
            case 5:
                if (abastecimento.getVeiculo() == null) {
                    return null;
                } else {
                    return abastecimento.getVeiculo().getFrota();
                }
            case 6:
                if (abastecimento.getVeiculo() == null) {
                    return null;
                } else {
                    return abastecimento.getVeiculo().getFrota().getEmpresa();
                }
            case 7:
                if (abastecimento.getOperador() == null) {
                    return null;
                } else {
                    return abastecimento.getOperador();
                }
            default:
                throw new IndexOutOfBoundsException("AbastecManualTableModel - getValueAt - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Abastecimento getAbastecimentos(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addListaAbastecimentos(List<Abastecimento> abastecimentos) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(abastecimentos);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void addAbastecimento(Abastecimento pAbastecimento) {
        Boolean existe = false;
        //verifica se ja nao existe o registro
        for (Abastecimento abastecimento : linhas) {
            if (abastecimento.getNumeroAbastecimento().equals(pAbastecimento.getNumeroAbastecimento())) {
                existe = true;
                break;
            } else {
                existe = false;
            }
        }
        if (!existe) {
            linhas.add(pAbastecimento);
            int ultimoIndice = getRowCount() - 1;
            fireTableRowsInserted(ultimoIndice, ultimoIndice);
        }
    }
}
