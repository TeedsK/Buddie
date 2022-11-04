package com.teedslab;

import java.awt.Point;

/**
 * This class represents as the move function for the frame
 * 
 * @author Theo Kremer
 */
public class Mover {

    private int wantedX, wantedY;

    /**
     * initializes a new Mover object
     * 
     * @param wantedX - the desired X value
     * @param wantedY - the desired Y value
     */
    public Mover(int wantedX, int wantedY) {
        this.wantedX = wantedX;
        this.wantedY = wantedY;
    }
    
    public void move(MainFrame frame, int x, int y) {
        this.wantedX = x;
        this.wantedY = y;
        move(frame);
    }

    /**
     * Moves the frame to the given position
     * 
     * @param frame - the frame to move
     * @param wantedX - the desired X position
     * @param wantedY - the desired Y position
     */
    public void move(final MainFrame frame) {

        Thread t = new Thread() {
            public void run() {

                Point location = frame.getLocation();
                int x = (int) location.getX();
                int y = (int) location.getY();

                int tempWantedX = wantedX;
                int tempWantedY = wantedY;

                while((tempWantedX == wantedX && tempWantedY == wantedY) && (!inRange(x, wantedX) || !inRange(y, wantedY))) {

                    //changes the value of X depending if its left or right
                    if(wantedX > x)
                        x += (wantedX - x) * (1 / 50.0);
                    else if(wantedX < x)
                        x -= (x - wantedX) * (1 / 50.0);
                    
                    //changes the value of Y depending on if its up or down
                    if(wantedY > y)
                        y += (wantedY - y) * (1 / 50.0);
                    else if(wantedY < y)
                        y -= (y - wantedY) * (1 / 50.0);
                    
                    //updates the location
                    frame.setLocation(x, y);

                    //repaints the frame
                    frame.repaint();
                    frame.revalidate();
                    
                    //sleeps for 1 second before repeating
                    try {
                        Thread.sleep(30);
                    } catch(Exception e) {}
                }

            }
        }; t.start();
        
    }

    private int getIteration(int p1, int p2) {
        int i = (int) ((p1 - p2) * (1 / 50.0));
        if(i > 20)
            return 20;
        if(i < 1) 
            return 1;
        return i;
    }

    /**
     * checks within the range
     * 
     * @param p1 - the first point
     * @param p2 - the second point
     * @return return if its with 2 of eachother
     */
    private boolean inRange(int p1, int p2) {
        System.out.println(p1 + " | " + p2 + " | " + (Math.abs(p1) - Math.abs(p2)));
        return Math.abs((Math.abs(p1) - Math.abs(p2))) <= 2;
    }
}
