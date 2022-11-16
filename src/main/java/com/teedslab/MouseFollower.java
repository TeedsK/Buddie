package com.teedslab;

import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JComponent;

/**
 * This class represents a version of the Velocity class
 * this makes the frame follow the mouse changing velocity along the way
 * 
 * @author Theo Kremer
 */
public class MouseFollower extends Velocity {

    JComponent component;

    /**
     * @param frame - the frame to move
     */
    public MouseFollower(double slipperyX, double speedX, JComponent component) {
        super(slipperyX, speedX);
        this.component = component;
        this.setFriction(0.05);
    }
    
    /**
     * calculates the current velocities of the object 
     * to be added to the position
     * 
     * @return double[] - the velocities in an array of cordinates [x, y]
     */
    public double[] getVelocity() {
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        updateAcceleration(component.getLocationOnScreen().getX(), component.getLocationOnScreen().getY(), mouse.getX(), mouse.getY());
        
        return new double[] {getVelocityX(),getVelocityY()};
    }
}
