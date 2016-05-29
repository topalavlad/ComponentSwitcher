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
    private final boolean titleOptional;
    private final InternalFrameAdapter frameActivatedListener = new InternalFrameAdapter() {
        @Override
        public void internalFrameActivated(InternalFrameEvent e) {
            JInternalFrame frame = e.getInternalFrame();
            frames.remove(frame);
            frames.add(0, frame);
        }
    };

    public DesktopListener() {
        this(false);
    }

    public DesktopListener(boolean titleOptional) {
        this.titleOptional = titleOptional;
    }

    @Override
    public void componentAdded(final ContainerEvent e) {
        if (e.getChild() instanceof JInternalFrame) {
            final JInternalFrame frame = (JInternalFrame) e.getChild();
            if (isTitleOptional() || frame.getTitle() != null && !frame.getTitle().trim().isEmpty()) {
                frames.add(frame);
                frame.addInternalFrameListener(frameActivatedListener);
            }
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

    private boolean isTitleOptional() {
        return titleOptional;
    }

    public List<JInternalFrame> getSwitchableComponentList() {
        return frames;
    }
}
