package tablemodel;

import ctr.GlobalParameter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import model.Bico;

public class StatusBicosTableModel extends AbstractTableModel {

    private static List<Bico> linhas;
    private static String[] cColunas = new String[]{"", "Estação de Abastecimento", "Status"};

    public StatusBicosTableModel() {
        linhas = new ArrayList<>();
    }

    public StatusBicosTableModel(List<Bico> listaBicos) {
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
                return String.class;//nome
            case 1:
                return Bico.class;//nome
            case 2:
                return String.class;//status
            default:
                throw new IndexOutOfBoundsException("StatusTableModel - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Bico b = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return b.getIdCBC();
            case 1:
                return b.getNomeBico();
            case 2:
                if (b.getIdCBC().equals("04")) {
                    return (GlobalParameter.getStatusBico04());
                } else if (b.getIdCBC().equals("44")) {
                    return (GlobalParameter.getStatusBico44());
                } else if (b.getIdCBC().equals("05")) {
                    return (GlobalParameter.getStatusBico05());
                } else if (b.getIdCBC().equals("45")) {
                    return (GlobalParameter.getStatusBico45());
                } else if (b.getIdCBC().equals("08")) {
                    return (GlobalParameter.getStatusBico08());
                } else if (b.getIdCBC().equals("48")) {
                    return (GlobalParameter.getStatusBico48());
                } else if (b.getIdCBC().equals("09")) {
                    return (GlobalParameter.getStatusBico09());
                } else if (b.getIdCBC().equals("0C")) {
                    return (GlobalParameter.getStatusBico0C());
                } else if (b.getIdCBC().equals("4C")) {
                    return (GlobalParameter.getStatusBico4C());
                } else if (b.getIdCBC().equals("4D")) {
                    return (GlobalParameter.getStatusBico4D());
                } else if (b.getIdCBC().equals("0D")) {
                    return (GlobalParameter.getStatusBico0D());
                } else if (b.getIdCBC().equals("10")) {
                    return (GlobalParameter.getStatusBico10());
                } else if (b.getIdCBC().equals("50")) {
                    return (GlobalParameter.getStatusBico50());
                } else if (b.getIdCBC().equals("11")) {
                    return (GlobalParameter.getStatusBico11());
                } else if (b.getIdCBC().equals("51")) {
                    return (GlobalParameter.getStatusBico51());
                }
            default:
                throw new IndexOutOfBoundsException("StatusBicosTableModel - getValueAt - Indice da Coluna fora dos limites");
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

    public void atualizar() {
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
            if (bico.getIdentBico().equals(pBico.getIdentBico())) {
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

//    private String retornaStatus(String statusGlobal) {
//        if (statusGlobal.equals("A")) {
//            return "ABASTECENDO";
//        } else if (statusGlobal.equals("B")) {
//            return "BLOQUEADA";
//        } else if (statusGlobal.equals("P")) {
//            return "PRONTO";
//        } else if (statusGlobal.equals("L")) {
//            return "LIBERADA";
//        } else if (statusGlobal.equals("C")) {
//            return "CONCLUIU";
//        } else if (statusGlobal.equals("E")) {
//            return "ESPERA";
//        } else {
//            return "FALHA";
//        }
//    }
}
