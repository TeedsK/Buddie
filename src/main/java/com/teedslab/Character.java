package com.teedslab;

import java.awt.image.BufferedImage;
import java.io.File;

import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.MouseInfo;

/**
 * This class represents the character person
 * 
 * @author Theo Kremer
 */
public class Character extends JPanel {

    //the width and height of the character
    int imgWidth, imgHeight;
    MainFrame frame;
    Image img;

    /**
     * @param frame - the frame holding the character
     */
    public Character(MainFrame frame, String location) {
        this.frame = frame;
        this.imgWidth = 50;
        this.imgHeight = 50;
        setSize(60, 60);
        setPreferredSize(new Dimension(60, 60));
        setOpaque(false);
        try {
            BufferedImage img = ImageIO.read(new File(location));
            this.img = img.getScaledInstance(imgWidth - 10, imgHeight - 10, Image.SCALE_SMOOTH);
        } catch(Exception e) {}
    }

    /**
     * paints the character
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        double rotation = getRotation();

        int width = getWidth() - 1;
        int height = getHeight() - 1;

        int x  = (width - this.imgWidth) / 2;
        int y  = (height - this.imgHeight) / 2;

        g2d.rotate(Math.toRadians(rotation), width / 2, height / 2);
        g2d.drawImage(img, x, y, this);
        g2d.dispose();
    }

    /**
     * @return double - the degree to rotate the character towards the mouse
     */
    private double getRotation() {
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();

        int x = (int) frame.getLocation().getX() + (frame.getWidth() / 2);
        int y = (int) frame.getLocation().getY() + (frame.getHeight() / 2);

        int deltaX = mousePoint.x - x;
        int deltaY = mousePoint.y - y;

        double rotation = -Math.atan2(deltaX, deltaY);

        return Math.toDegrees(rotation) + 180;
    }
}
