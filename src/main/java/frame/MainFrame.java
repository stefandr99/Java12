package frame;


import panel.ControlPanel;
import panel.DesignPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;

    /**
     * Constructorul clasei care da nume aplicatiei (numele din banda) si apeleaza functia init care creeaza tot frame-ul
     */
    public MainFrame() throws IOException {
        super("Dynamic designer");
        init();
    }

    /**
     * setDefaultCloseOperation(EXIT_ON_CLOSE); -- indica faptul ca se inchide aplicatia la apasarea exit-ului
     * Creez cele 2 panouri ale aplicatiei si apoi le adaug in fereastra in pozitiile corespunzatoare
     */
    private void init() throws IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controlPanel = new ControlPanel(this);
        designPanel = new DesignPanel(this);


        add(controlPanel, BorderLayout.NORTH);
        add(designPanel, BorderLayout.CENTER);

        pack();
    }

    /**
     * @return panoul de desen
     */
    public DesignPanel getDesignPanel() {
        return designPanel;
    }

    /**
     * @return panoul de configuratii
     */
    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public static void main(String[] args) throws IOException {
        new MainFrame().setVisible(true);
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public void setDesignPanel(DesignPanel designPanel) {
        this.designPanel = designPanel;
        this.designPanel.revalidate();
    }
}
