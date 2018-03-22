package tablemodel;

import bd.EstoqueDB;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.Combustivel;
import util.FormatacaoDados;

public class EstoqueAtualTableModel extends AbstractTableModel {

    private static List<Combustivel> linhas;
    private static String[] cColunas = new String[]{"Combustível", "Saldo"};

    public EstoqueAtualTableModel() {
        linhas = new ArrayList<>();
    }

    public EstoqueAtualTableModel(List<Combustivel> listaCombustiveis) {
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
                return String.class; // código
            case 1:
                return String.class;//ident
            default:
                throw new IndexOutOfBoundsException("EstoqueAtualTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Combustivel c = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return c.getNomeCombustivel();
            case 1:
                return FormatacaoDados.DoubleFormat(EstoqueDB.buscaSaldoEstoqueCombustivel(c.getSeqCombustivel()));
            default:
                throw new IndexOutOfBoundsException("EstoqueAtualTableModel - getValueAt - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Combustivel getCombustiveis(int indiceLinha) {
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
        for (Combustivel c : linhas) {
            if (c.getSeqCombustivel().equals(pCombustivel.getSeqCombustivel())) {
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
