package com.teedslab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.teedslab.Clean.PressSpreadButton;

import java.awt.BorderLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class represents each of the car selections
 * @author Theo Kremer
 */
public class CarSelection extends PressSpreadButton {

    public static CarSelection selectedCar = null;
    private String car;

    /**
     * initializes and creates the button
     * 
     * @param car - the file location of the car png
     * @param background - the background color of the button
     * @param buttonColor - the main color of the button 
     * @param hoverColor - the color to change when hovering 
     * @param pressColor - the color to change when pressed 
     * @param roundness - the roundness of the button
     */
    public CarSelection(String car, Color background, Color buttonColor, Color hoverColor, Color pressColor, int roundness) {
        super( background, buttonColor, hoverColor, pressColor, roundness);
        
        this.car = car;

        setPreferredSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));
        setMinimumSize(new Dimension(50, 50));
        setPressIncrementAmount(50);
        setDisableHover(true);

        JLabel image = new JLabel();
        image.setHorizontalAlignment(JLabel.CENTER);

        Image p1 = Toolkit.getDefaultToolkit().getImage(car);
        Image p2 = p1.getScaledInstance(23,23, Image.SCALE_SMOOTH);
        ImageIcon p3 = new ImageIcon(p2);
        image.setIcon(p3);

        add(image, BorderLayout.CENTER);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(selectedCar != null && selectedCar != CarSelection.this) {
                    selectedCar.fadeOutAll();
                }
                selectedCar = CarSelection.this;
                selectedCar.HoverFadeIn();
            }
        });
    }

    /**
     * @return String - the file name / location of the car
     */
    public String getCar() {
        return car;
    }
}
