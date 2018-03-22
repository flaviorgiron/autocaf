package render;

import javax.swing.table.DefaultTableCellRenderer;

public class VolumeTableCellRender extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object value) {
        if (value instanceof Double) {
            String sDouble = value.toString();
            setText(sDouble.replace(".", ","));
        } else {
            super.setValue(value);
        }
    }
}
