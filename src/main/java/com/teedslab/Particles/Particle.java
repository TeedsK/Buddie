package com.teedslab.Particles;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class represents a particle
 * 
 * @author Theo Kremer
 */
public class Particle extends JPanel {

    int opacityIterator;
    int opacity;

    public Particle() {
        this.opacityIterator = 10;
        this.opacity = 255;
        setVisible(true);
    }

    public boolean fade(int target) {
        while(opacity != target) {
            
            opacity = updateOpacity(target > opacity);

            int r = getBackground().getRed();
            int g = getBackground().getGreen();
            int b = getBackground().getBlue();

            setBackground(new Color(r,g,b,opacity));

            repaint();
            try {
                Thread.sleep(15);
            } catch(Exception e) {}
        }
        return true;
    }

    private int updateOpacity(boolean addition) {
        if(addition)
            return (opacity + opacityIterator <= 1.0) ? opacity + opacityIterator : 1;
        return (opacity - opacityIterator > 0) ?  opacity - opacityIterator : 0;
    }

    public void setOpacityIterator(int iterator) {
        this.opacityIterator = iterator;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }
}
