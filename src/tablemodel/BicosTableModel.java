package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.Bico;
import model.Combustivel;

public class BicosTableModel extends AbstractTableModel {

    private static List<Bico> linhas;
    private static String[] cColunas = new String[]{"Seq", "Identificador", "Nome", "Combustível", "Situação"};

    public BicosTableModel() {
        linhas = new ArrayList<>();
    }

    public BicosTableModel(List<Bico> listaBicos) {
        linhas = new ArrayList<>(listaBicos);
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
                return String.class;//combustivel
            case 4:
                return String.class;//situação
            default:
                throw new IndexOutOfBoundsException("BicosTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Bico bico = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return bico.getSeqBico();
            case 1:
                return bico.getIdentBico();
            case 2:
                return bico.getNomeBico();
            case 3:
                if (bico.getCombustivel() == null) {
                    return "";
                } else {
                    return bico.getCombustivel().getNomeCombustivel();
                }
            case 4:
                if (bico.getSituacao().equals("A")) {
                    return "ATIVO";
                } else {
                    return "INATIVO";
                }
            default:
                throw new IndexOutOfBoundsException("BicosTableModel - getValueAt - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Bico getBicos(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addListaBicos(List<Bico> bicos) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(bicos);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void addBico(Bico pBico) {
        Boolean existe = false;
        //verifica se ja nao existe o registro
        for (Bico bico : linhas) {
            if (bico.getSeqBico().equals(pBico.getSeqBico())) {
                existe = true;
                break;
            } else {
                existe = false;
            }
        }
        if (!existe) {
            linhas.add(pBico);
            int ultimoIndice = getRowCount() - 1;
            fireTableRowsInserted(ultimoIndice, ultimoIndice);
        }
    }
}
