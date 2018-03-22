package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.Combustivel;

public class CombustiveisTableModel extends AbstractTableModel {

    private static List<Combustivel> linhas;
    private static String[] cColunas = new String[]{"Seq", "Identificador", "Nome", "Situação"};

    public CombustiveisTableModel() {
        linhas = new ArrayList<>();
    }

    public CombustiveisTableModel(List<Combustivel> listaCombustiveis) {
        linhas = new ArrayList<>(listaCombustiveis);
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
                return String.class;//ident
            case 2:
                return String.class;//nome
            case 3:
                return String.class;//situacao
            default:
                throw new IndexOutOfBoundsException("CombustiveisTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Combustivel combustivel = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return combustivel.getSeqCombustivel();
            case 1:
                return combustivel.getIdentCombustivel();
            case 2:
                return combustivel.getNomeCombustivel();
            case 3:
                if (combustivel.getSituacao().equals("A")) {
                    return "ATIVO";
                } else {
                    return "INATIVO";
                }
            default:
                throw new IndexOutOfBoundsException("CombustiveisTableModel - getValueAt - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Combustivel getCombustivels(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addListaCombustiveis(List<Combustivel> combustiveis) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(combustiveis);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void addCombustivel(Combustivel pCombustivel) {
        Boolean existe = false;
        //verifica se ja nao existe o registro
        for (Combustivel combustivel : linhas) {
            if (combustivel.getNomeCombustivel().equals(pCombustivel.getNomeCombustivel())) {
                existe = true;
                break;
            } else {
                existe = false;
            }
        }
        if (!existe) {
            linhas.add(pCombustivel);
            int ultimoIndice = getRowCount() - 1;
            fireTableRowsInserted(ultimoIndice, ultimoIndice);
        }
    }
}
