package com.teedslab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.teedslab.Clean.PressSpreadButton;

/**
 * This class represents a multiplier option
 * 
 * @author Theo Kremer
 */
public class Multiplier extends JPanel {
    
    double multiplier;
    PressSpreadButton selected;

    /**
     * 
     * @param title - the title of this section
     */
    public Multiplier(String title) {
        this.multiplier = 1.0;
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel label = new JLabel(title);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(new Color(255, 175, 219));
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));

        add(label, BorderLayout.NORTH);

        addButtons();
    }

    public double getMultiplier() {
        return multiplier;
    }

    /**
     * Adds the multiplier buttons
     */
    private void addButtons() {

        JPanel panel = new JPanel();
        panel.setOpaque(false);

        final double[] options = {0.25, 0.5, 0.75, 1, 2, 3, 4};

        for(int i = 0; i < options.length; i++) {
            final PressSpreadButton button = new PressSpreadButton("" + options[i], new Color(27, 30, 39), new Color(44, 46, 61), new Color(95, 98, 123), new Color(95, 98, 123), 15);
            
            button.setFontSize(11);
            button.setPreferredSize(new Dimension(40, 40));
            button.setPressIncrementAmount(50);
            button.setDisableHover(true);
            if(options[i] == 1) {
                button.HoverFadeIn();
                selected = button;
            }

            final int position = i;

            button.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if(multiplier != options[position]) {
                        multiplier = options[position];
                        selected.fadeOutAll();
                        selected = button;
                        button.HoverFadeIn();
                    }
                }
            });
            

            panel.add(button);

        }

        add(panel, BorderLayout.CENTER);

    }

}
