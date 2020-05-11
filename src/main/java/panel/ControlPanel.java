package panel;

import frame.MainFrame;

import javax.swing.*;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * Aici va fi control panelul pentru inserarea butoanelor si a celorlalte elemente grafice
     */
    private void init() {
        sidesLabel = new JLabel("Number of sides:");
    }

}
