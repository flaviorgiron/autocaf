package render;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class ColorirCelula extends DefaultTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Font font = new Font("Arial", Font.BOLD, 11); 
        super.setFont(font);
        if (value.equals("A")) {
            super.setBackground(hex2Rgb("#F4FA58"));
            setValue("ABASTECENDO");
        } else if (value.equals("B")) {
            super.setBackground(hex2Rgb("#FE2E2E"));
            setValue("BLOQUEADA");
        } else if (value.equals("P")) {
            super.setBackground(hex2Rgb("#2E9AFE"));
            setValue("PRONTO");
        } else if (value.equals("L")) {
            super.setBackground(hex2Rgb("#2EFE64"));
            setValue("LIBERADA");
        } else if (value.equals("C")) {
            super.setBackground(hex2Rgb("#8A0808"));
            setValue("CONCLUIU");
        } else if (value.equals("E")) {
            super.setBackground(hex2Rgb("#FE9A2E"));
            setValue("ESPERA");
        } else {
            super.setBackground(hex2Rgb("#A4A4A4"));
            setValue("FALHA");
        }

        return this;
    }

    public static Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }
}
