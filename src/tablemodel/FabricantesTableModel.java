package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.Fabricante;

public class FabricantesTableModel extends AbstractTableModel {

    private static List<Fabricante> linhas;
    private static String[] cColunas = new String[]{"Seq", "Identificador", "Nome", "Situação"};

    public FabricantesTableModel() {
        linhas = new ArrayList<>();
    }

    public FabricantesTableModel(List<Fabricante> listaFabricantes) {
        linhas = new ArrayList<>(listaFabricantes);
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
                throw new IndexOutOfBoundsException("FabricantesTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Fabricante f = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return f.getSeqFabricante();
            case 1:
                return f.getIdentFabricante();
            case 2:
                return f.getNomeFabricante();
            case 3:
                if (f.getSituacao().equals("A")) {
                    return "ATIVO";
                } else {
                    return "INATIVO";
                }
            default:
                throw new IndexOutOfBoundsException("FabricantesTableModel - getValueAt - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Fabricante getFabricantes(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addListaFabricantes(List<Fabricante> fabricantes) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(fabricantes);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void addFabricante(Fabricante pFabricantes) {
        Boolean existe = false;
        //verifica se ja nao existe o registro
        for (Fabricante f : linhas) {
            if (f.getNomeFabricante().equals(pFabricantes.getNomeFabricante())) {
                existe = true;
                break;
            } else {
                existe = false;
            }
        }
        if (!existe) {
            linhas.add(pFabricantes);
            int ultimoIndice = getRowCount() - 1;
            fireTableRowsInserted(ultimoIndice, ultimoIndice);
        }
    }
}
