package com.teedslab;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.teedslab.Particles.Smoke;

import java.awt.Toolkit;

/**
 * This class represents the main containing frame for the application
 * 
 * @author Theo Kremer
 */
public class MainFrame extends JFrame {
    
    private JPanel background; // the background of the frame
    private int width, height; // the width and height of the frame
    private Dimension resolution; // the width and height of the screen
    private Character character; //the character 

    /**
     * initializes the global objects
     */
    public MainFrame() {
        super("Buddie");

        width = 200;
        height = 200;

        resolution = Toolkit.getDefaultToolkit().getScreenSize();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(width,height));
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));

        background = new JPanel();
        background.setOpaque(false);
        background.setSize(new Dimension(width,height));
        background.setPreferredSize(new Dimension(width,height));
        background.setBackground(new Color(102, 95, 255));
        background.setLayout(null);
        // background.setOpaque(false);
        
        add(background);
        
        pack();
        setLocationRelativeTo(null);
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
     * @return JPanel - the background to the frame
     */
    public JPanel getFrameBackground() {
        return background;
    }


    /**
     * @param character - the character of the frame
     */
    public void setCharacter(Character character) {
        this.character = character;
        int x = (background.getWidth() - character.getWidth()) / 2;
        int y = (background.getHeight() - character.getHeight()) / 2;
        character.setLocation(x, y);
        
        background.add(character);
        // background.setComponentZOrder(character, 0);

        background.repaint();
        background.revalidate();
    }


    /**
     * sets the frame to follow the mouse
     */
    public void moveToCursor() {

        MouseFollower velocity = new MouseFollower(character);

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
            
            for(int i = 0; i < 3; i++) {
                Smoke smoke = new Smoke();
                background.add(smoke);
                background.setComponentZOrder(smoke, 1);
                background.setComponentZOrder(character, 0);
                background.repaint();
                smoke.start(character, velocities);
            }
            
            // this.setLocation(-100, -100);
            this.setLocation(x, y);
        }

    }



    /**
     * moves the frame to a random location
     */
    public void moveToRandom() {

        Velocity velocity = new Velocity();

        int wantedX = (int) (Math.random() * resolution.getWidth()) - 10;
        int wantedY = (int) (Math.random() * resolution.getHeight()) - 10;
        
        int x = (int) this.getLocation().getX();
        int y = (int) this.getLocation().getY();

        // velocity.move(this, x, y);
        while(!velocity.inRange(wantedX, wantedY, x, y)) {
            
            try {
                Thread.sleep(15);
            } catch(Exception e) {}

            velocity.updateAcceleration(this.getLocation().getX(), this.getLocation().getY(), wantedX, wantedY);

            //adds the velocities to the current cordinates
            x = (int) (this.getLocation().getX() + velocity.getVelocityX());
            y = (int) (this.getLocation().getY() + velocity.getVelocityY());
            
            //updates the current location
            this.setLocation(x, y);
        }
    }

}
