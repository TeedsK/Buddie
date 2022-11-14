package com.teedslab;

import java.awt.Color;
import java.awt.Dimension;

import com.teedslab.PressSpreadButton.PressSpreadButton;

/**
 * This class represents each of the car selections
 */
public class CarSelection extends PressSpreadButton {

    public CarSelection(String car, Color background, Color buttonColor, Color hoverColor, Color pressColor, int roundness) {
        super("", background, buttonColor, hoverColor, pressColor, roundness);
        setImage(car);
        setPreferredSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));
        setMinimumSize(new Dimension(50, 50));
    }

    
}
