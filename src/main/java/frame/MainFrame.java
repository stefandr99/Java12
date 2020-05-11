package frame;


import panel.ControlPanel;
import panel.DesignPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;

    /**
     * Constructorul clasei care da nume aplicatiei (numele din banda) si apeleaza functia init care creeaza tot frame-ul
     */
    public MainFrame() {
        super("Dynamic designer");
        init();
    }

    /**
     * setDefaultCloseOperation(EXIT_ON_CLOSE); -- indica faptul ca se inchide aplicatia la apasarea exit-ului
     * Creez cele 2 panouri ale aplicatiei si apoi le adaug in fereastra in pozitiile corespunzatoare
     */
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        designPanel = new DesignPanel(this);
        controlPanel = new ControlPanel(this);

        add(designPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.NORTH);

        pack();
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
