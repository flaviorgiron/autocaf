package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.Empresa;

public class EmpresasTableModel extends AbstractTableModel {

    private static List<Empresa> linhas;
    private static String[] cColunas = new String[]{"Seq", "Identificador", "Nome", "Situação"};

    public EmpresasTableModel() {
        linhas = new ArrayList<>();
    }

    public EmpresasTableModel(List<Empresa> listaEmpresas) {
        linhas = new ArrayList<>(listaEmpresas);
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
                throw new IndexOutOfBoundsException("EmpresasTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Empresa combustivel = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return combustivel.getSeqEmpresa();
            case 1:
                return combustivel.getIdentEmpresa();
            case 2:
                return combustivel.getNomeEmpresa();
            case 3:
                if (combustivel.getSituacao().equals("A")) {
                    return "ATIVO";
                } else {
                    return "INATIVO";
                }
            default:
                throw new IndexOutOfBoundsException("EmpresasTableModel - getValueAt - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Empresa getCombustivels(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addListaEmpresas(List<Empresa> empresas) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(empresas);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void addEmpresa(Empresa pEmpresa) {
        Boolean existe = false;
        //verifica se ja nao existe o registro
        for (Empresa combustivel : linhas) {
            if (combustivel.getSeqEmpresa().equals(pEmpresa.getSeqEmpresa())) {
                existe = true;
                break;
            } else {
                existe = false;
            }
        }
        if (!existe) {
            linhas.add(pEmpresa);
            int ultimoIndice = getRowCount() - 1;
            fireTableRowsInserted(ultimoIndice, ultimoIndice);
        }
    }
}
