package panel;

import frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DesignPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image;
    Graphics2D graphics;

    public DesignPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    /**
     * Creem aplicatia si coloram fundalul panoului de desen cu alb
     */
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    /**
     * Afisam panelul de desenat
     */
    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }
}
