package com.teedslab;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class test extends javax.swing.JPanel {

    private BufferedImage img;
    private Point mousePoint;
    public static void main(String[] args) {
        new test();
    }
    /**
     * Creates new form RotatePane
     */
    public test() {

        try {
            // img = ImageIO.read(getClass().getResource("/Characters/Cars/car1.png"));
            img = ImageIO.read(new File("src/main/java/com/teedslab/Characters/Cars/car1.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {

                mousePoint = e.getPoint();

                repaint();

            }

        });

    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(img.getWidth(), img.getHeight());

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        double rotation = 0f;

        int width = getWidth() - 1;
        int height = getHeight() - 1;

        if (mousePoint != null) {

            int x = width / 2;
            int y = height / 2;

            int deltaX = mousePoint.x - x;
            int deltaY = mousePoint.y - y;

            rotation = -Math.atan2(deltaX, deltaY);

            rotation = Math.toDegrees(rotation) + 180;

        }

        int x  = (width - img.getWidth()) / 2;
        int y  = (height - img.getHeight()) / 2;

        g2d.rotate(Math.toRadians(rotation), width / 2, height / 2);
        g2d.drawImage(img, x, y, this);

        x = width / 2;
        y = height / 2;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.RED);
        g2d.drawLine(x, y, x, y - height / 4);
        g2d.dispose();

    }
}