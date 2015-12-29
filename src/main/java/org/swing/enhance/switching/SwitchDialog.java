package org.swing.enhance.switching;

import org.swing.enhance.desktop.JInternalFrameCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class SwitchDialog<T> extends JDialog {

    private final JList<T> list;

    public SwitchDialog(Window owner, List<T> titles) {
        super(owner);
        setUndecorated(true);
        list = new JList<>(new Vector<>(titles));
        list.setCellRenderer(new JInternalFrameCellRenderer());
        list.setSelectedIndex(0);
        setContentPane(list);
        pack();
    }

    public void selectNext() {
        int indexToSelect = list.getSelectedIndex() != list.getModel().getSize() - 1
                ? list.getSelectedIndex() + 1
                : 0;
        list.setSelectedIndex(indexToSelect);
    }

    public void selectPrevious() {
        int indexToSelect = list.getSelectedIndex() != 0
                ? list.getSelectedIndex() - 1
                : list.getModel().getSize() - 1;
        list.setSelectedIndex(indexToSelect);
    }

    public T getSelected() {
        return list.getSelectedValue();
    }
}
