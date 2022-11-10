package com.teedslab;

/**
 * This class represents the starter for the application
 * 
 * @author Theo Kremer
 */
public class App 
{
    public static void main( String[] args )
    {
        MainFrame frame = new MainFrame();
        // while(true) {
        //     try {
                // Thread.sleep(1000);
            // } catch(Exception e) {}
            Character character = new Character(frame);
            
            frame.setCharacter(character);
        // }
        frame.moveToCursor();
        
    }
}




