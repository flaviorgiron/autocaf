package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.Motorista;

public class MotoristasTableModel extends AbstractTableModel {

    private static List<Motorista> linhas;
    private static String[] cColunas = new String[]{"Seq", "Identificador", "Nome", "Situação"};

    public MotoristasTableModel() {
        linhas = new ArrayList<>();
    }

    public MotoristasTableModel(List<Motorista> listaMotoristas) {
        linhas = new ArrayList<>(listaMotoristas);
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
                throw new IndexOutOfBoundsException("MotoristasTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Motorista m = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return m.getSeqMotorista();
            case 1:
                return m.getIdentMotorista();
            case 2:
                return m.getNomeMotorista();
            case 3:
                if (m.getSituacao().equals("A")) {
                    return "ATIVO";
                } else {
                    return "INATIVO";
                }
            default:
                throw new IndexOutOfBoundsException("MotoristasTableModel - getValueAt - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Motorista getMotoristas(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addListaMotoristas(List<Motorista> motoristas) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(motoristas);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void addMotorista(Motorista pMotorista) {
        Boolean existe = false;
        //verifica se ja nao existe o registro
        for (Motorista f : linhas) {
            if (f.getNomeMotorista().equals(pMotorista.getNomeMotorista())) {
                existe = true;
                break;
            } else {
                existe = false;
            }
        }
        if (!existe) {
            linhas.add(pMotorista);
            int ultimoIndice = getRowCount() - 1;
            fireTableRowsInserted(ultimoIndice, ultimoIndice);
        }
    }
}
