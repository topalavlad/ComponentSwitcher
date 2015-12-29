package org.swing.enhance.keystroke;

import org.swing.enhance.switching.SwitchableComponentsListener;
import org.swing.enhance.switching.Switcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SwitchDispatcher<T> implements KeyEventDispatcher {

    private static final KeyStroke nextStroke = KeyStroke.getKeyStroke("ctrl TAB");
    private static final KeyStroke previousStroke = KeyStroke.getKeyStroke("ctrl shift TAB");

    private final Switcher<T> switcher;
    private final SwitchableComponentsListener<T> desktopListener;

    public SwitchDispatcher(Switcher<T> switcher, SwitchableComponentsListener<T> switchableComponentsListener) {
        this.switcher = switcher;
        this.desktopListener = switchableComponentsListener;
    }

    public void start() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
    }

    public void stop() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
    }

    public boolean dispatchKeyEvent(KeyEvent e) {
        KeyStroke keyStrokeForEvent = KeyStroke.getKeyStrokeForEvent(e);
        boolean next = nextStroke.equals(keyStrokeForEvent);
        boolean previous = previousStroke.equals(keyStrokeForEvent);
        boolean dismiss = e.getKeyCode() == KeyEvent.VK_CONTROL && !e.isControlDown();

        if (next) {
            switcher.next(desktopListener.getSwitchableComponentList());
        } else if (previous) {
            switcher.previous(desktopListener.getSwitchableComponentList());
        } else if (dismiss) {
            switcher.dismiss();
        }
        return next || previous || dismiss;
    }
}
