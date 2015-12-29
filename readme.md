# ComponentSwitcher

This library was created in order to be used with Java Swing applications.

The initial version only offers switching for applications that use JDesktopPane together with multiple JInternalFrames.
When pressing Ctrl+TAB or Ctrl+Shift+Tab, the user will be able to choose which internal frame to be activated.
This becomes more useful when many internal frames are used at once.

###Build
Run ```mvn package```

###Usage
All you have to do in order to use the switcher is register a container listener on the jDesktopPane you want to use
and initiate a SwitchDispatcher:

    private DesktopListener desktopListener = new DesktopListener();
    
    private JDesktopPane jDesktopPane = new JDesktopPane();
    jDesktopPane.addContainerListener(desktopListener);
    
    SwitchDispatcher<JInternalFrame> switchDispatcher
            = new SwitchDispatcher<>(new DesktopSwitcher(jDesktopPane), desktopListener);
    switchDispatcher.start();

###Future improvements
* Use switcher for JTabbedPanes
* Allow custom keystrokes
* Make switch dialog prettier
* Add tests