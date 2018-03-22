package render;

import javax.swing.table.DefaultTableCellRenderer;
import model.Empresa;

public class EmpresaTableCellRender extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object value) {
        if (value instanceof Empresa) {
            Empresa e = (Empresa) value;
            setText(e.getNomeEmpresa());
        } else {
            super.setValue(value);
        }
    }
}
