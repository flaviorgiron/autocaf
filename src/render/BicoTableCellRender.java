package render;

import javax.swing.table.DefaultTableCellRenderer;
import model.Bico;

public class BicoTableCellRender extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object value) {
        if (value instanceof Bico) {
            Bico b = (Bico) value;
            setText(b.getNomeBico());
        } else {
            super.setValue(value);
        }
    }
}
