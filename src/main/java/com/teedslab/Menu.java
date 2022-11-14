package com.teedslab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;

public class Menu extends JFrame {
    
    JPanel background;

    public Menu() {
        super("Buddie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(300,500));
        setUndecorated(true);
        setPreferredSize(new Dimension(300,500));

        background = new JPanel(); 
        background.setLayout(new BorderLayout());
        background.add(getTitleSection(), BorderLayout.NORTH);
        background.setBackground(new Color(27, 30, 39));
        background.add(getChoiceSection(), BorderLayout.CENTER);

        add(background);
        
        pack();
        
        setVisible(true);
    }

    private JPanel getTitleSection() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(0, 60));
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Buddie");
        title.setForeground(new Color(255, 175, 219));
        title.setFont(new Font("Serif", Font.BOLD, 18));
        title.setHorizontalAlignment(JLabel.CENTER);

        panel.add(title, BorderLayout.CENTER);

        return panel;
    }

    private JPanel getChoiceSection() {
        JPanel panel = new JPanel();
        CarSelection[] cars = new CarSelection[4];
        
        panel.add(Box.createHorizontalGlue());

        for(int i = 0; i < 4; i++) {
            CarSelection car = new CarSelection("src/main/java/com/teedslab/Characters/Cars/car" + (i + 1) + ".png", new Color(27, 30, 39), new Color(44, 46, 61), new Color(55, 58, 75), new Color(95, 98, 123), 15);
            car.setPreferredSize(new Dimension(50,50));
            car.setMaximumSize(new Dimension(50,50));
            car.setMinimumSize(new Dimension(50,50));
            panel.add(car);
        }

        

        return panel;
    }


    
}
