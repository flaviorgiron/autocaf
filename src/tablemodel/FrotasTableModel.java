package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.Frota;
import model.Empresa;

public class FrotasTableModel extends AbstractTableModel {

    private static List<Frota> linhas;
    private static String[] cColunas = new String[]{"Seq", "Identificador", "Nome", "Empresa", "Situação"};

    public FrotasTableModel() {
        linhas = new ArrayList<>();
    }

    public FrotasTableModel(List<Frota> listaFrotas) {
        linhas = new ArrayList<>(listaFrotas);
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
                return Empresa.class;//combustivel
            case 4:
                return String.class;//situação
            default:
                throw new IndexOutOfBoundsException("FrotasTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Frota frota = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return frota.getSeqFrota();
            case 1:
                return frota.getIdentFrota();
            case 2:
                return frota.getNomeFrota();
            case 3:
                if (frota.getEmpresa() == null) {
                    return null;
                } else {
                    return frota.getEmpresa();
                }
            case 4:
                if (frota.getSituacao().equals("A")) {
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

    public Frota getFrotas(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addListaFrotas(List<Frota> frotas) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(frotas);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void addFrota(Frota pFrota) {
        Boolean existe = false;
        //verifica se ja nao existe o registro
        for (Frota frota : linhas) {
            if (frota.getSeqFrota().equals(pFrota.getSeqFrota())) {
                existe = true;
                break;
            } else {
                existe = false;
            }
        }
        if (!existe) {
            linhas.add(pFrota);
            int ultimoIndice = getRowCount() - 1;
            fireTableRowsInserted(ultimoIndice, ultimoIndice);
        }
    }
}
