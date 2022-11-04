package com.teedslab;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.MouseInfo;
import java.awt.Point;

/**
 * This class represents the main containing frame for the application
 * 
 * @author Theo Kremer
 */
public class MainFrame extends JFrame {
    
    private JPanel background;
    private int width, height;
    private Dimension resolution;
    private Velocity velocity;

    /**
     * initializes the global objects
     */
    public MainFrame() {
        super("Buddie");

        width = 100;
        height = 100;
        // mover = new Mover(0, 0);
        velocity = new Velocity(this);

        resolution = Toolkit.getDefaultToolkit().getScreenSize();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(width,height));

        background = new JPanel();
        background.setBackground(new Color(102, 95, 255));
        
        pack();
        setVisible(true);
    }

    /**
     * initializes the objects with a given height and width
     * 
     * @param width - the width of the frame
     * @param height - the height of the frame
     */
    public MainFrame(int width, int height) {
        this();

        this.width = width;
        this.height = height;
        
        setSize(new Dimension(width,height));
    }

    public void moveToCursor() {
        while(true) {
            try {
                Thread.sleep(15);
            } catch(Exception e) {}
            
            
            double[] velocities = velocity.getVelocity();

            double xo = this.getLocation().getX() + velocities[0];
            double yo = this.getLocation().getY() + velocities[1];
            

            this.setLocation((int) xo, (int) yo);
        }
        
    
    }

    public void moveToRandom() {
        int x = (int) (Math.random() * resolution.getWidth());
        int y = (int) (Math.random() * resolution.getHeight());
        // mover.move(this, x, y);
    }

    private boolean inRange(double x1, double y1, double x2, double y2) {
        if(Math.abs(Math.abs(x1) - Math.abs(x2)) < 10 && Math.abs(Math.abs(y1) - Math.abs(y2)) < 10) {
            return true;
        }
        return false;
    }

    public void moveTo(int x, int y) {
        
    }
}
