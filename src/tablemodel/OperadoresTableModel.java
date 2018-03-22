package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.Operador;

public class OperadoresTableModel extends AbstractTableModel {

    private static List<Operador> linhas;
    private static String[] cColunas = new String[]{"Seq", "Identificador", "Nome", "Login", "Situação"};

    public OperadoresTableModel() {
        linhas = new ArrayList<>();
    }

    public OperadoresTableModel(List<Operador> listaOperadores) {
        linhas = new ArrayList<>(listaOperadores);
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
                return Integer.class;//seq
            case 1:
                return Integer.class; // código
            case 2:
                return String.class;//nome
            case 3:
                return String.class;//login
            case 4:
                return String.class;//situação
            default:
                throw new IndexOutOfBoundsException("OperadoresTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Operador operador = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return operador.getSeqOperador();
            case 1:
                if (operador.getIdentOperador() == null) {
                    return "";
                } else {
                    return operador.getIdentOperador();
                }
            case 2:
                return operador.getNomeOperador();
            case 3:
                return operador.getLogin();
            case 4:
                return operador.getSituacao().equals("A") ? "ATIVO" : "INATIVO";
            default:
                throw new IndexOutOfBoundsException("OperadoresTableModel - getValueAt - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Operador getOperadores(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addListaOperadores(List<Operador> operadores) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(operadores);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void addOperador(Operador pOperador) {
        Boolean existe = false;
        //verifica se ja nao existe o registro
        for (Operador operador : linhas) {
            if (operador.getNomeOperador().equals(pOperador.getNomeOperador())) {
                existe = true;
                break;
            } else {
                existe = false;
            }
        }
        if (!existe) {
            linhas.add(pOperador);
            int ultimoIndice = getRowCount() - 1;
            fireTableRowsInserted(ultimoIndice, ultimoIndice);
        }
    }
}
