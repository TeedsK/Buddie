package com.teedslab;

import java.awt.MouseInfo;
import java.awt.Point;

/**
 * This class represents a version of the Velocity class
 * this makes the frame follow the mouse changing velocity along the way
 * 
 * @author Theo Kremer
 */
public class MouseFollower extends Velocity {

    MainFrame frame;

    /**
     * @param frame - the frame to move
     */
    public MouseFollower(MainFrame frame) {
        super();
        this.frame = frame;

    }
    
    /**
     * calculates the current velocities of the object 
     * to be added to the position
     * 
     * @return double[] - the velocities in an array of cordinates [x, y]
     */
    public double[] getVelocity() {
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        
        updateAcceleration(frame.getLocation().getX(), frame.getLocation().getY(), mouse.getX(), mouse.getY());
        
        return new double[] {getVelocityX(),getVelocityY()};
    }    
}
