package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.Frota;
import model.Veiculo;

public class VeiculosTableModel extends AbstractTableModel {

    private static List<Veiculo> linhas;
    private static String[] cColunas = new String[]{"Seq", "Identificador", "Nome", "Frota", "Situação"};

    public VeiculosTableModel() {
        linhas = new ArrayList<>();
    }

    public VeiculosTableModel(List<Veiculo> listaVeiculos) {
        linhas = new ArrayList<>(listaVeiculos);
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
                return Integer.class; // código
            case 1:
                return String.class;//IDENT
            case 2:
                return String.class;//nome
            case 3:
                return String.class;//frota
            case 4:
                return String.class;//situação
            default:
                throw new IndexOutOfBoundsException("FrotasTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Veiculo veiculo = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return veiculo.getSeqVeiculo();
            case 1:
                if (veiculo.getIdentVeiculo() == null) {
                    return "";
                } else {
                    return veiculo.getIdentVeiculo();
                }
            case 2:
                return veiculo.getNomeVeiculo();
            case 3:
                if (veiculo.getFrota() == null) {
                    return null;
                } else {
                    return veiculo.getFrota().getNomeFrota();
                }
            case 4:
                if (veiculo.getSituacao().equals("A")) {
                    return "ATIVO";
                } else {
                    return "INATIVO";
                }
            default:
                throw new IndexOutOfBoundsException("FrotasTableModel - getValueAt - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Veiculo getVeiculos(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addListaVeiculos(List<Veiculo> veiculos) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(veiculos);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void addVeiculo(Veiculo pVeiculo) {
        Boolean existe = false;
        //verifica se ja nao existe o registro
        for (Veiculo veiculo : linhas) {
            if (veiculo.getNomeVeiculo().equals(pVeiculo.getNomeVeiculo())) {
                existe = true;
                break;
            } else {
                existe = false;
            }
        }
        if (!existe) {
            linhas.add(pVeiculo);
            int ultimoIndice = getRowCount() - 1;
            fireTableRowsInserted(ultimoIndice, ultimoIndice);
        }
    }
}
