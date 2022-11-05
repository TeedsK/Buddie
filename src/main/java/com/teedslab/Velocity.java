package com.teedslab;


/**
 * This class represents the motion and velocity 
 * 
 * @author Theo Kremer
 */
public class Velocity {
    
    private int maxSpeed;
    private double acceleration;
    private double friction;

    private double[] speeds;
    private double[] deceleration;

    /**
     * Initializes the global variables
     */
    public Velocity() {
        maxSpeed = 10;
        acceleration = 0.25;
        friction = 0.1;
        speeds = new double[] {0, 0};
        deceleration = new double[] {0, 0};
    }

    /**
     * Gets the velocity given which one
     * i = 0 -> the x cordinate
     * i = 1 -> the y cordinate
     * 
     * @param i - the velocity of the cordinate to get
     * @return double - the velocity
     */
    private double getVelocity(int i) {
        if(speeds[i] > maxSpeed)
            speeds[i] = maxSpeed;
        if(speeds[i] < -maxSpeed)
            speeds[i] = -maxSpeed;
        return speeds[i];
    }

    /**
     * @return double - the X velocity
     */
    public double getVelocityX() {
        return getVelocity(0);
    }

    /**
     * @return double - the Y velocity
     */
    public double getVelocityY() {
        return getVelocity(1);
    }
    
    /**
     * Updates the speed based on the acceleration
     * checks if needs to change direction / descelerate
     * 
     * @param current - the current cordinate points
     * @param wanted - the desired cordinate points
     */
    public void updateAcceleration(double[] current, double[] wanted) {
        //loops through each cordinate point (x and y)
        for(int i = 0; i <= 1; i++) {

            //if we need to decelerate in the
            if(deceleration[i] != 0) {
                speeds[i] *= 1 - (friction);
                if(Math.abs(speeds[i]) < 0.5) {
                    speeds[i] = 0;
                    deceleration[i] = 0;
                }
            }
            
    
            if(wanted[i] > current[i]) {
                if(deceleration[i] == 0 && speeds[i] < 0) 
                    deceleration[i] = 1;
                if(i == 0)
                    speeds[i] += getCos(wanted[0], wanted[1], current[0], current[1]) * acceleration;
                else
                    speeds[i] += getSin(wanted[0], wanted[1], current[0], current[1]) * acceleration;
            } else {
                if(deceleration[i] == 0 && speeds[i] > 0) 
                    deceleration[i] = -1;
                if(i == 0)
                    speeds[i] -= getCos(wanted[0], wanted[1], current[0], current[1]) * acceleration;
                else
                    speeds[i] -= getSin(wanted[0], wanted[1], current[0], current[1]) * acceleration;
            }
    
            if(Double.isNaN(speeds[i])) 
                speeds[i] = 0;
        }
        
        
    }
    
    public void updateAcceleration(double x, double y, double wantedX, double wantedY) {
        updateAcceleration(new double[] {x, y}, new double[] {wantedX, wantedY});
    }

    public double getCos(double x1, double y1, double x2, double y2) {
        
        double y = Math.abs((Math.abs(y1) - Math.abs(y2)));
        double x = Math.abs((Math.abs(x1) - Math.abs(x2)));

        return Math.cos(Math.atan(y / x));
    }

    public double getSin(double x1, double y1, double x2, double y2) {

        double y = Math.abs((Math.abs(y1) - Math.abs(y2)));
        double x = Math.abs((Math.abs(x1) - Math.abs(x2)));

        return Math.sin(Math.atan(y / x));
    }

    /**
     * the 
     * 
     * @param x1 - the first X cordinate
     * @param y1 - the first Y cordinate
     * @param x2 - the second X cordinate
     * @param y2 - the second Y cordinate
     * @return
     */
    public boolean inRange(double x1, double y1, double x2, double y2) {
        
        return Math.abs(Math.abs(x1) - Math.abs(x2)) < 10 && Math.abs(Math.abs(y1) - Math.abs(y2)) < 10;
    }

}
