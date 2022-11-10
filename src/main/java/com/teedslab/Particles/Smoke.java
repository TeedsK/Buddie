package com.teedslab.Particles;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import com.teedslab.Character;
/**
 * This class represents a smoke effect
 * 
 * @author Theo Kremer
 */
public class Smoke extends Particle {

    int width, height;

    public Smoke() {
        this.width = 10;
        this.height = 10;
        style();
    }

    private void style() {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        setOpacity(255);
        setOpacityIterator(25);
        setBackground(new Color(255,255,255,0));
    }

    public void start(final Character character, final double[] velocities) {
        setVisible(true);
        Thread t = new Thread() {
            public void run() {

                int x = character.getX() + (character.getWidth() / 2) + getRandomNumberUsingNextInt(-5, 5);
                int y = character.getY() + (character.getHeight() / 2) + getRandomNumberUsingNextInt(-5, 5);


                Thread t2 = new Thread() {
                    public void run() {
                        fade(0);
                    }
                }; 

                t2.start();

                
                for(int i = 0; i < 100; i++) {

                    x -= velocities[0];
                    y -= velocities[1];
    
                    setLocation((int) x, (int) y);

                    try {
                        Thread.sleep(10);
                    } catch(Exception e) {}
                }
            }
        }; t.start();
    }
    
    private int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
