package render;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import model.Bico;

public class BicoIdDescListRender extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Bico) {
            Bico b = (Bico) value;
            setText(b.getIdentBico() + " - "+ b.getNomeBico());
        }
        return this;
    }
}
