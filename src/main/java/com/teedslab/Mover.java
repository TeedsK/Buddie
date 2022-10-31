package com.teedslab;

import java.awt.Point;

/**
 * This class represents as the move function for the frame
 * 
 * @author Theo Kremer
 */
public class Mover {

    private int currentX, currentY;
    private int wantedX, wantedY;

    /**
     * 
     * @param wantedX
     * @param wantedY
     */
    public Mover(int wantedX, int wantedY) {
        this.wantedX = wantedX;
        this.wantedY = wantedY;
    }
    
    public void move(MainFrame frame) {
        Point location = frame.getLocation();
        int x = (int) location.getX();
        int y = (int) location.getY();
        
    }
}
