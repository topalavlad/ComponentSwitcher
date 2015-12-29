package org.swing.enhance.desktop;

import org.swing.enhance.switching.SwitchableComponentsListener;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.util.ArrayList;
import java.util.List;

public class DesktopListener extends ContainerAdapter implements SwitchableComponentsListener<JInternalFrame> {

    private final List<JInternalFrame> frames = new ArrayList<>();
    private final InternalFrameAdapter frameActivatedListener = new InternalFrameAdapter() {
        @Override
        public void internalFrameActivated(InternalFrameEvent e) {
            JInternalFrame frame = e.getInternalFrame();
            frames.remove(frame);
            frames.add(0, frame);
        }
    };

    @Override
    public void componentAdded(final ContainerEvent e) {
        if (e.getChild() instanceof JInternalFrame) {
            final JInternalFrame frame = (JInternalFrame) e.getChild();
            frames.add(frame);
            frame.addInternalFrameListener(frameActivatedListener);
        }
    }

    @Override
    public void componentRemoved(final ContainerEvent e) {
        if (e.getChild() instanceof JInternalFrame) {
            JInternalFrame frame = (JInternalFrame) e.getChild();
            frames.remove(frame);
            frame.removeInternalFrameListener(frameActivatedListener);
        }
    }

    public List<JInternalFrame> getSwitchableComponentList() {
        return frames;
    }
}
