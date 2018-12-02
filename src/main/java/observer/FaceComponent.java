package observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class FaceComponent extends JPanel {

    private final static int WINDOW_SIZE = 300;

    private final Face face;

    private FaceComponent() {
        face = new Face(WINDOW_SIZE, WINDOW_SIZE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                face.mouseClicked(e);
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                face.mouseMoved(e);
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                face.mouseMoved(e);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        face.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WINDOW_SIZE, WINDOW_SIZE);
    }

    private static void createAndShowGUI() {
        JComponent face = new FaceComponent();

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.CYAN);
        contentPane.add(face);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(contentPane);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(FaceComponent::createAndShowGUI);
    }
}