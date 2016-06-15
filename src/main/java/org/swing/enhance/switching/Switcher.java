package org.swing.enhance.switching;

import javax.swing.*;
import java.util.List;

/**
 * This class will do the actual switching based on the lists of components it receives.
 *
 * @param <T> - The type of the components that can be switched. {@link javax.swing.JInternalFrame} initially but
 *            will be extended for tabs and other components.
 */
public interface Switcher<T> {
    /**
     * Trigger this to select the previous component in the list
     *
     * @param switchableComponentList - list of all the switchable components
     */
    void previous(List<T> switchableComponentList);

    /**
     * Trigger this to select the next component in the list
     *
     * @param switchableComponentList - list of all the switchable components
     */
    void next(List<T> switchableComponentList);

    /**
     * This method will hide the dialog that shows all the switchable components if it exists.
     */
    void dismiss();

    /**
     * Returns the desktop pane on which the switcher is working
     */
    JDesktopPane getDesktop();

    /**
     * Checks whether the switcher component currently has focus
     * @return - true if the switch dialog is open and has focus; false otherwise
     */
    boolean hasFocus();
}
