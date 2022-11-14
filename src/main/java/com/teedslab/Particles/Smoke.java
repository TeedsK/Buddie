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
        setVisible(false);
        setBackground(new Color(200,200,200,255));
    }

    public void start(final Character character, final double[] velocities) {
        

        Thread t = new Thread() {
            public void run() {

                int x = character.getX() + (character.getWidth() / 2) + getRandomNumberUsingNextInt(-5, 5);
                int y = character.getY() + (character.getHeight() / 2) + getRandomNumberUsingNextInt(-5, 5);

                setLocation((int) x, (int) y);

                setVisible(true);
               
                Thread t2 = new Thread() {
                    public void run() {
                        if(fade(0))
                            Smoke.this.getParent().remove(Smoke.this);
                    }
                }; 

                t2.start();

                while(getBackground().getAlpha() > 0) {
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
