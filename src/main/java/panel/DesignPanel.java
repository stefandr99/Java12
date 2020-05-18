package panel;

import frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

public class DesignPanel extends JPanel {
    MainFrame frame;
    final static int W = 800, H = 600;

    BufferedImage image;
    Graphics2D graphics;

    public DesignPanel () {}

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
     * Afisarea panelului de desenat
     */
    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());


    }
}
