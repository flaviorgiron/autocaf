package render;

import javax.swing.table.DefaultTableCellRenderer;
import model.Operador;

public class OperadorTableCellRender extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object value) {
        if (value instanceof Operador) {
            Operador o = (Operador) value;
            setText(o.getNomeOperador());
        } else {
            super.setValue(value);
        }
    }
}
