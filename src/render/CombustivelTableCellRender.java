package render;

import javax.swing.table.DefaultTableCellRenderer;
import model.Combustivel;

public class CombustivelTableCellRender extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object value) {
        if (value instanceof Combustivel) {
            Combustivel c = (Combustivel) value;
            setText(c.getNomeCombustivel());
        } else {
            super.setValue(value);
        }
    }
}
