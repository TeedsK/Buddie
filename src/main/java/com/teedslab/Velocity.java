package com.teedslab;


/**
 * This class represents the motion and velocity 
 * 
 * @author Theo Kremer
 */
public class Velocity {
    
    private double maxSpeed;
    private double acceleration;
    private double friction;

    private double[] speeds;
    private double[] deceleration;

    /**
     * Initializes the global variables
     */
    public Velocity(double slipperyX, double speedX) {
        maxSpeed = 20 * speedX;
        acceleration = 0.25 * speedX;
        friction = 0.1 / slipperyX;
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

            //if we need to decelerate
            if(deceleration[i] != 0) {
                speeds[i] *= 1 - (friction);
                if(Math.abs(speeds[i]) < 0.5) {
                    speeds[i] = 0;
                    deceleration[i] = 0;
                }
            }
            
            //if the wanted position is greater then the current, move to the right / down
            if(wanted[i] > current[i]) {

                //if it's current not already decelerating and switched acceleration location, start decelerating
                if(deceleration[i] == 0 && speeds[i] < 0) 
                    deceleration[i] = 1;

                //if its X cordinate, do cos
                if(i == 0)
                    speeds[i] += getCos(wanted[0], wanted[1], current[0], current[1]) * acceleration;
                
                //if its Y cordinate, do sin
                else
                    speeds[i] += getSin(wanted[0], wanted[1], current[0], current[1]) * acceleration;

            //otherwise the wanted position is less then the current, move to the left / up
            } else {

                //if it's current not already decelerating and switched acceleration location, start decelerating
                if(deceleration[i] == 0 && speeds[i] > 0) 
                    deceleration[i] = -1;
                
                //if its X cordinate, do cos
                if(i == 0)
                    speeds[i] -= getCos(wanted[0], wanted[1], current[0], current[1]) * acceleration;
        
                //if its Y cordinate, do sin
                else
                    speeds[i] -= getSin(wanted[0], wanted[1], current[0], current[1]) * acceleration;
            }
    
            if(Double.isNaN(speeds[i])) 
                speeds[i] = 0;
        }

    }
    
    /**
     * 
     * @param x - the current x cordinate
     * @param y - the current y cordinate
     * @param wantedX - the wanted x cordinate
     * @param wantedY - the wanted y cordinate
     */
    public void updateAcceleration(double x, double y, double wantedX, double wantedY) {
        updateAcceleration(new double[] {x, y}, new double[] {wantedX, wantedY});
    }

    /**
     * Gets the cos value of the triangle made from the three points
     * 
     * @param x1 - x cordinate of first point
     * @param y1 - y cordinate of first point
     * @param x2 - x cordinate of second point
     * @param y2 - y cordinate of second point
     * @return double - the value of the cos from triangle
     */
    public double getCos(double x1, double y1, double x2, double y2) {
        
        double y = Math.abs((Math.abs(y1) - Math.abs(y2)));
        double x = Math.abs((Math.abs(x1) - Math.abs(x2)));

        return Math.cos(Math.atan(y / x));
    }

    /**
     * Gets the sin value of the triangle made from the three points
     * 
     * @param x1 - x cordinate of first point
     * @param y1 - y cordinate of first point
     * @param x2 - x cordinate of second point
     * @param y2 - y cordinate of second point
     * @return double - the value of the sin from triangle
     */
    public double getSin(double x1, double y1, double x2, double y2) {

        double y = Math.abs((Math.abs(y1) - Math.abs(y2)));
        double x = Math.abs((Math.abs(x1) - Math.abs(x2)));

        return Math.sin(Math.atan(y / x));
    }

    /**
     * determines if two points are in range
     * 
     * @param x1 - the first X cordinate
     * @param y1 - the first Y cordinate
     * @param x2 - the second X cordinate
     * @param y2 - the second Y cordinate
     * @return boolean - the two points are within 10 units of eachother
     */
    public boolean inRange(double x1, double y1, double x2, double y2) {
        System.out.println(x1 +", " + y1 +" \t " +x2 + ", " + y2);
        return Math.abs(Math.abs(x1) - Math.abs(x2)) < 10 && Math.abs(Math.abs(y1) - Math.abs(y2)) < 10;
    }

    /**
     * sets the current friction count
     * 
     * @param friction - the amount of friction to feel 
     */
    public void setFriction(double friction) {
        this.friction = friction;
    }
}
