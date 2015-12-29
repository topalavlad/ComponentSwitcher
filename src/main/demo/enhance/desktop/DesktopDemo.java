package enhance.desktop;

import org.swing.enhance.desktop.DesktopListener;
import org.swing.enhance.desktop.DesktopSwitcher;
import org.swing.enhance.keystroke.SwitchDispatcher;

import javax.swing.*;
import java.awt.*;

public class DesktopDemo {

    private JDesktopPane jDesktopPane;

    public static void main(String[] args) {
        DesktopDemo desktopDemo = new DesktopDemo();
        desktopDemo.init();
    }

    private void init() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DesktopListener desktopListener = new DesktopListener();

                jDesktopPane = new JDesktopPane();
                jDesktopPane.addContainerListener(desktopListener);

                initComponents();

                SwitchDispatcher<JInternalFrame> switchDispatcher
                        = new SwitchDispatcher<>(new DesktopSwitcher(jDesktopPane), desktopListener);
                switchDispatcher.start();
            }
        });
    }

    private void initComponents() {
        JFrame jFrame = new JFrame();

        JInternalFrame jInternalFrame1 = new JInternalFrame("Frame1");
        jInternalFrame1.add(new JButton("Test2"));

        JInternalFrame jInternalFrame2 = new JInternalFrame("Frame2");
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Tab1", new JPanel());
        tabs.addTab("Tab2", new JPanel());
        tabs.addTab("Tab3", new JPanel());
        jInternalFrame2.add(tabs);

        JInternalFrame jInternalFrame3 = new JInternalFrame("Frame3");
        jInternalFrame3.add(new JButton("Test"));

        jDesktopPane.add(jInternalFrame1);
        jDesktopPane.add(jInternalFrame2);
        jDesktopPane.add(jInternalFrame3);

        resetInternalFrame(jInternalFrame1);
        resetInternalFrame(jInternalFrame2);
        resetInternalFrame(jInternalFrame3);


        jFrame.setContentPane(jDesktopPane);
        jFrame.setVisible(true);
        jFrame.setSize(new Dimension(600, 600));
    }

    private void resetInternalFrame(JInternalFrame jInternalFrame) {
        jInternalFrame.setVisible(true);
        jInternalFrame.pack();
        jInternalFrame.setLocation(0, 0);
        jInternalFrame.setSize(300, 300);
    }
}
