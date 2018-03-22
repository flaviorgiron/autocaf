package render;

import javax.swing.table.DefaultTableCellRenderer;
import model.Frota;

public class FrotaTableCellRender extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object value) {
        if (value instanceof Frota) {
            Frota f = (Frota) value;
            setText(f.getNomeFrota());
        } else {
            super.setValue(value);
        }
    }
}
