package render;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import model.Combustivel;

public class CombustivelListRender extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Combustivel) {
            Combustivel c = (Combustivel) value;
            setText(c.getNomeCombustivel());
        }
        return this;
    }
}
