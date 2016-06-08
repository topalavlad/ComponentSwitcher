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
        initList();
        setContentPane(list);
        pack();
    }

    private void initList() {
        list.setCellRenderer(new JInternalFrameCellRenderer());
        list.setSelectedIndex(0);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void removeSelectionInterval(int index0, int index1) {}

            @Override
            public void addSelectionInterval(int index0, int index1) {
                super.setSelectionInterval(index0, index1);
            }
        });
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
        list.setSelectedIndex(indexToSelect > -1 ? indexToSelect : 0);
    }

    public T getSelected() {
        return list.getSelectedValue();
    }

    public void setSwitchableComponents(List<T> switchableComponents) {
        T oldSelection = list.getSelectedValue();
        list.setListData(new Vector<>(switchableComponents));
        list.setSelectedValue(oldSelection, false);
    }

    public JList<T> getList() {
        return list;
    }
}
