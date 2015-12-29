package org.swing.enhance.desktop;


import javax.swing.*;
import java.awt.*;

public class JInternalFrameCellRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setText(((JInternalFrame) value).getTitle());
        return c;
    }
}
