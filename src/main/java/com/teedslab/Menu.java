package com.teedslab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.teedslab.Clean.PressSpreadButton;

import java.awt.BorderLayout;

/**
 * This class represents the main menu
 * 
 * @author Theo Kremer
 */
public class Menu extends JFrame {
    
    MainFrame gameFrame;
    JPanel background;
    Multiplier slippery;
    Multiplier speed;

    /**
     * initializes the global objects
     */
    public Menu() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(420,400));
        // setUndecorated(true);
        setPreferredSize(new Dimension(420,400));
        setMinimumSize(new Dimension(420,400));
        setLocationRelativeTo(null);

        background = new JPanel(); 
        background.setLayout(new BorderLayout());
        background.add(getTitleSection(), BorderLayout.NORTH);
        background.setBackground(new Color(27, 30, 39));
        background.add(getChoiceSection(), BorderLayout.CENTER);
        background.add(getControlButtons(), BorderLayout.SOUTH);

        add(background);
        
        pack();
        
        setVisible(true);
    }

    /**
     * @return JPanel - the title container panel 
     */
    private JPanel getTitleSection() {
        JPanel panel = new JPanel();
        
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(0, 60));
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Buddie");
        title.setForeground(new Color(255, 175, 219));
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setHorizontalAlignment(JLabel.CENTER);

        panel.add(title, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Get the panel of the choice selections
     * 
     * @return JPanel - the container panel
     */
    private JPanel getChoiceSection() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        for(int i = 0; i < 4; i++) {
            CarSelection car = new CarSelection("src/main/java/com/teedslab/Characters/Cars/car" + (i + 1) + ".png", new Color(27, 30, 39), new Color(44, 46, 61), new Color(95, 98, 123), new Color(95, 98, 123), 15);
            car.setPreferredSize(new Dimension(65,65));
            car.setMaximumSize(new Dimension(65,65));
            car.setMinimumSize(new Dimension(65,65));
            if(i == 0) {
                CarSelection.selectedCar = car;
                car.HoverFadeIn();
            }
            panel.add(car);
        }

        slippery = new Multiplier("Slippery Multiplier");
        speed = new Multiplier("Speed Multiplier");

        slippery.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        speed.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

        JPanel under = new JPanel();
        
        under.setLayout(new BorderLayout());
        under.setOpaque(false);
        under.add(slippery, BorderLayout.NORTH);
        under.setPreferredSize(new Dimension(420, 160));
        under.add(speed, BorderLayout.SOUTH);

        panel.add(under);

        return panel;
    }

    /**
     * @return JPanel - the very bottom container with the stop and start buttons
     */
    private JPanel getControlButtons() {

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());

        PressSpreadButton stop = new PressSpreadButton("stop", new Color(27, 30, 39), new Color(244, 81, 81), new Color(253, 114, 114), new Color(246, 64, 64), 15);
        PressSpreadButton start = new PressSpreadButton("start", new Color(27, 30, 39), new Color(80, 220, 123), new Color(119, 247, 158), new Color(48, 235, 105), 15);
        
        stop.setPreferredSize(new Dimension(100, 40));
        start.setPreferredSize(new Dimension(100, 40));

        stop.setPressIncrementAmount(50);
        start.setPressIncrementAmount(50);

        panel.add(stop, BorderLayout.WEST);
        panel.add(start, BorderLayout.EAST);

        stop.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(gameFrame != null) {
                    gameFrame.dispose();
                    gameFrame = null;
                }
            }
        });

        start.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(gameFrame == null) {
                    Thread t = new Thread() {
                        public void run() {
                            gameFrame = new MainFrame(slippery.getMultiplier(), speed.getMultiplier());
                            Character character = new Character(gameFrame, CarSelection.selectedCar.getCar());
                            gameFrame.setCharacter(character);
                            gameFrame.moveToCursor();
                        }
                    }; t.start();
                }
            }
        });
        
        return panel;
    }
    
}