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

    /**
     * initializes the global objects
     */
    public MainFrame() {
        super("Buddie");

        width = 100;
        height = 100;

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
        this(); //re-calls the constructor 

        this.width = width;
        this.height = height;
        
        setSize(new Dimension(width,height));
    }

    /**
     * sets the frame to follow the mouse
     */
    public void moveToCursor() {

        MouseFollower velocity = new MouseFollower(this);

        while(true) {

            //refresh rate - 15 ms
            try {
                Thread.sleep(15);
            } catch(Exception e) {}
            
            //gets the x, y, velocities
            double[] velocities = velocity.getVelocity();

            //adds the velocities to the current cordinates
            int x = (int) (this.getLocation().getX() + velocities[0]);
            int y = (int) (this.getLocation().getY() + velocities[1]);
            
            //updates the current location
            this.setLocation(x, y);
        }

    }

    /**
     * 
     */
    public void moveToRandom() {

        Velocity velocity = new Velocity();

        int wantedX = (int) (Math.random() * resolution.getWidth());
        int wantedY = (int) (Math.random() * resolution.getHeight());
        
        int x = (int) this.getLocation().getX();
        int y = (int) this.getLocation().getY();

        // velocity.move(this, x, y);
        while(velocity.inRange(wantedX, wantedY, x, y)) {
            
            try {
                Thread.sleep(15);
            } catch(Exception e) {}

            //adds the velocities to the current cordinates
            x = (int) (this.getLocation().getX() + velocity.getVelocityX());
            y = (int) (this.getLocation().getY() + velocity.getVelocityY());
            
            //updates the current location
            this.setLocation(x, y);
        }
    }

    

    public void moveTo(int x, int y) {
        
    }
}
