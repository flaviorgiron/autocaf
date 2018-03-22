package render;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableCellRenderer;

public class TimeTableRender extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object value) {
        Date d = null;
        if (value instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            if (value != null) {
                setText(sdf.format(value));
            } else {
                setText("00:00:00");
            }
        } else {
            super.setValue(value);
        }
    }
}
