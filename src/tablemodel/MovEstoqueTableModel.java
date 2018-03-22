package tablemodel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.MovEstoque;
import util.FormatacaoDados;

public class MovEstoqueTableModel extends AbstractTableModel {

    private static List<MovEstoque> linhas;
    private static String[] cColunas = new String[]{"Data/Hora", "Tipo", "Quantidade", "Observação"};

    public MovEstoqueTableModel() {
        linhas = new ArrayList<>();
    }

    public MovEstoqueTableModel(List<MovEstoque> movEstoque) {
        linhas = new ArrayList<>(movEstoque);
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
                return Timestamp.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("MovEstoqueTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        MovEstoque movEstoque = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return movEstoque.getDataHora();
            case 1:
                if (movEstoque.getTipoMovimento().equals("E")) {
                    return "Entrada de Estoque";
                } else if (movEstoque.getTipoMovimento().equals("S")) {
                    return "Saída de Estoque";
                }
            case 2:
                return FormatacaoDados.DoubleFormat(movEstoque.getQuantidade());
            case 3:
                return movEstoque.getObservacao();
            default:
                throw new IndexOutOfBoundsException("MovEstoqueTableModel - getValueAt - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public MovEstoque getMovEstoques(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addListaMovEstoques(List<MovEstoque> movEstoques) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(movEstoques);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void addMovEstoque(MovEstoque pMovEstoque) {
        Boolean existe = false;
        //verifica se ja nao existe o registro
        for (MovEstoque movEstoque : linhas) {
            if (movEstoque.getCombustivel().getSeqCombustivel().equals(pMovEstoque.getCombustivel().getSeqCombustivel())) {
                existe = true;
                break;
            } else {
                existe = false;
            }
        }
        if (!existe) {
            linhas.add(pMovEstoque);
            int ultimoIndice = getRowCount() - 1;
            fireTableRowsInserted(ultimoIndice, ultimoIndice);
        }
    }
}
