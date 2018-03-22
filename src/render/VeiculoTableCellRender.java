package render;

import javax.swing.table.DefaultTableCellRenderer;
import model.Veiculo;

public class VeiculoTableCellRender extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object value) {
        if (value instanceof Veiculo) {
            Veiculo v = (Veiculo) value;
            setText(v.getNomeVeiculo());
        } else {
            super.setValue(value);
        }
    }
}
