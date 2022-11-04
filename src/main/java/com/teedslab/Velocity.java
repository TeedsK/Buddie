package com.teedslab;

import java.awt.MouseInfo;
import java.awt.Point;
/**
 * This class represents the motion and velocity 
 * 
 * @author Theo Kremer
 */
public class Velocity {
    
    private MainFrame frame;
    private int maxSpeed;

    private double acceleration;

    private double friction;

    private double[] speeds;
    private double[] deceleration;

    public Velocity(MainFrame frame) {

        this.frame = frame;
        maxSpeed = 10;
        acceleration = 0.25;
        friction = 0.1;
        speeds = new double[] {0, 0};
        deceleration = new double[] {0, 0};
    }

    
    private double getVelocity(int i) {
        if(speeds[i] > maxSpeed)
            speeds[i] = maxSpeed;
        if(speeds[i] < -maxSpeed)
            speeds[i] = -maxSpeed;
        return speeds[i];
    }


    /**
     * 
     * @param frameLocation
     * @param mouseLocation
     * @param i
     */
    public void updateAcceleration(double frameLocation, double mouseLocation, int i) {
        if(deceleration[i] > 0) {
            speeds[i] *= 1 - (friction);
            if(speeds[i] > -0.1) {
                speeds[i] = 0;
                deceleration[i] = 0;
            }
        } else if(deceleration[i] < 0) {
            speeds[i] *= 1 - (friction);
            if(speeds[i] < 0.1) {
                speeds[i] = 0;
                deceleration[i] = 0;
            }
        }

        if(mouseLocation > frameLocation) {
            if(deceleration[i] == 0 && speeds[i] < 0) 
                deceleration[i] = 1;
            if(i == 0)
                speeds[i] += getCos() * acceleration;
            else
                speeds[i] += getSin() * acceleration;
        } else {
            if(deceleration[i] == 0 && speeds[i] > 0) 
                deceleration[i] = -1;
            if(i == 0)
                speeds[i] -= getCos() * acceleration;
            else
                speeds[i] -= getSin() * acceleration;
        }

        if(Double.isNaN(speeds[i])) 
            speeds[i] = 0;
    }

    public double[] getVelocity() {
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        
        updateAcceleration(frame.getLocation().getX(), mouse.getX(), 0);
        updateAcceleration(frame.getLocation().getY(), mouse.getY(), 1);

        return new double[] {getVelocity(0),getVelocity(1)};
    }

    private double getCos() {
        Point mouse = MouseInfo.getPointerInfo().getLocation();

        double y = Math.abs((Math.abs(mouse.getY()) - Math.abs(frame.getLocation().getY())));
        double x = Math.abs((Math.abs(mouse.getX()) - Math.abs(frame.getLocation().getX())));

        return Math.cos(Math.atan(y / x));
    }

    private double getSin() {
        Point mouse = MouseInfo.getPointerInfo().getLocation();

        double y = Math.abs((Math.abs(mouse.getY()) - Math.abs(frame.getLocation().getY())));
        double x = Math.abs((Math.abs(mouse.getX()) - Math.abs(frame.getLocation().getX())));

        return Math.sin(Math.atan(y / x));
    }

}
