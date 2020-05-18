package panel;

import frame.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.RenderedImage;
import java.beans.*;
import java.io.*;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JLabel formsLabel;
    JTextField textField;
    JButton submitButton;
    JButton saveButton;
    JButton loadButton;

    public ControlPanel(MainFrame frame) throws IOException {
        this.frame = frame;
        init();
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    /**
     * Aici va fi control panelul pentru inserarea butoanelor si a celorlalte elemente grafice
     */
    private void init() throws IOException {
        formsLabel = new JLabel("Write here:");
        textField = new JTextField(30);
        submitButton = new JButton("Submit");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        add(formsLabel);
        add(textField);
        add(submitButton);
        add(saveButton);
        add(loadButton);

        submitButton.addActionListener(this::write);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("test.xml");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                assert fos != null;
                XMLEncoder encoder = new XMLEncoder(fos);
                encoder.setExceptionListener(new ExceptionListener() {
                    public void exceptionThrown(Exception e) {
                        System.out.println("Exception! :" + e.toString());
                    }
                });
                encoder.writeObject(frame.getDesignPanel());
                encoder.close();
                try {
                    fos.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream("test.xml");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                XMLDecoder decoder = new XMLDecoder(fis);
                DesignPanel decodedPanel = (DesignPanel) decoder.readObject();
                decoder.close();
                try {
                    assert fis != null;
                    fis.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                for(Component c : decodedPanel.getComponents())
                    frame.getDesignPanel().add(c);
                frame.getDesignPanel().revalidate();
            }
        });

    }

    public void write(ActionEvent e) {
        String text = frame.getControlPanel().textField.getText();
        if(text.compareTo("JButton") == 0) {
            JButton response = new JButton("Click me !");

            response.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BeanInfo beanInfo = null;
                    try {
                        beanInfo = Introspector.getBeanInfo(e.getSource().getClass());
                        BeanDescriptor bd = beanInfo.getBeanDescriptor();

                        String[][] dataResponse = {{bd.getDisplayName(), bd.getShortDescription(), bd.getName()}};
                        String[] column={"DisplayName","ShortDescription","Name"};
                        JTable jt = new JTable(dataResponse,column);

                        frame.getDesignPanel().add(jt);
                        frame.getDesignPanel().revalidate();
                    } catch (IntrospectionException introspectionException) {
                        introspectionException.printStackTrace();
                    }
                }
            });
            frame.getDesignPanel().add(response);

        }
        else if(text.compareTo("JLabel") == 0) {
            JLabel response = new JLabel("A valid label :)");

            response.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.getControlPanel().getInfo(e);
                }

                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}

            });
            frame.getDesignPanel().add(response);
        }
        else if(text.compareTo("JTextField") == 0) {
            JTextField response = new JTextField(20);

            response.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.getControlPanel().getInfo(e);
                }

                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}

            });
            frame.getDesignPanel().add(response);
        }
        else if(text.compareTo("JSpinner") == 0) {
            JSpinner response = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
            response.setValue(1);

            response.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.getControlPanel().getInfo(e);
                }

                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}

            });
            frame.getDesignPanel().add(response);
        }
        else if(text.compareTo("JComboBox") == 0) {
            String[] a = {"Stefan", "Dragoi", "Java"};
            JComboBox response = new JComboBox(a);

            response.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.getControlPanel().getInfo(e);
                }

                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}

            });
            frame.getDesignPanel().add(response);
        }
        frame.getDesignPanel().revalidate();
        textField.setText("");

    }

    public void getInfo(MouseEvent e) {
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(e.getSource().getClass());
            BeanDescriptor bd = beanInfo.getBeanDescriptor();

            String[][] dataResponse = {{bd.getDisplayName(), bd.getShortDescription(), bd.getName()}};
            String[] column={"DisplayName","ShortDescription","Name"};
            JTable jt = new JTable(dataResponse,column);

            frame.getDesignPanel().add(jt);
            frame.getDesignPanel().revalidate();
        } catch (IntrospectionException introspectionException) {
            introspectionException.printStackTrace();
        }
    }

}
