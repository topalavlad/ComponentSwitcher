package org.swing.enhance.switching;

import java.util.List;

public interface SwitchableComponentsListener<T> {
    /**
     * Get a list of the components that the user can switch through.
     * Ideally, this would be sorted by the last focused order.
     *
     * @return - list of switchable components of type T
     */
    List<T> getSwitchableComponentList();
}
