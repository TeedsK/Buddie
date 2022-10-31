package com.teedslab;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;


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
        this();

        this.width = width;
        this.height = height;
        
        setSize(new Dimension(width,height));
    }

    public void moveToRandom() {
        int x = (int) (Math.random() * resolution.getWidth());
        int y = (int) (Math.random() * resolution.getHeight());


    }

    public void moveTo(int x, int y) {
        
    }
}
