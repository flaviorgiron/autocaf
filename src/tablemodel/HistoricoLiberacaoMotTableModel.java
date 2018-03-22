package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.*;

public class HistoricoLiberacaoMotTableModel extends AbstractTableModel {

    private static List<HistoricoLiberacao> linhas;
    private static String[] cColunas = new String[]{"Data/Hora", "Operador", "Estação de Abastecimento", "Veículo", "Odômetro/Horímetro", "Motorista"};

    public HistoricoLiberacaoMotTableModel() {
        linhas = new ArrayList<>();
    }

    public HistoricoLiberacaoMotTableModel(List<HistoricoLiberacao> listaHistoricos) {
        linhas = new ArrayList<>(listaHistoricos);
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
                return String.class;//data/hora
            case 1:
                return String.class;//operador
            case 2:
                return String.class;//bico
            case 3:
                return String.class;//veiculo
            case 4:
                return String.class;//odometro
            case 5:
                return String.class;//motorista
            default:
                throw new IndexOutOfBoundsException("HistoricoLiberacao - GetColumnClass - Indice da Coluna fora dos limites");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            HistoricoLiberacao historico = linhas.get(rowIndex);

            switch (columnIndex) {
                case 0:

                    return historico.getDataHora();
                case 1:
                    if (historico.getOperador() == null) {
                        return "";
                    } else {
                        return historico.getOperador().getNomeOperador();
                    }
                case 2:
                    if (historico.getBico() == null) {
                        return "";
                    } else {
                        return historico.getBico().getNomeBico();
                    }
                case 3:
                    if (historico.getVeiculo() == null) {
                        return "";
                    } else {
                        return historico.getVeiculo().getNomeVeiculo();
                    }
                case 4:
                    return historico.getHodometro();
                case 5://odometro/horimetro
                    if (historico.getMotorista() == null) {
                        return "";
                    } else {
                        return historico.getMotorista().getNomeMotorista();
                    }
                default:
                    throw new IndexOutOfBoundsException("HistoricoLiberacao - getValueAt - Indice da Coluna fora dos limites");
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public HistoricoLiberacao getHistoricos(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public void addListaHistorico(List<HistoricoLiberacao> historicos) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(historicos);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void addHistorico(HistoricoLiberacao pHistorico) {
        Boolean existe = false;
        //verifica se ja nao existe o registro
        for (HistoricoLiberacao historico : linhas) {
            if (historico.getDataHora().equals(pHistorico.getDataHora())) {
                existe = true;
                break;
            } else {
                existe = false;
            }
        }
        if (!existe) {
            linhas.add(pHistorico);
            int ultimoIndice = getRowCount() - 1;
            fireTableRowsInserted(ultimoIndice, ultimoIndice);
        }
    }
}
