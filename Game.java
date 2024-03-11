package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JFrame implements ActionListener {
    Field fieldGame;
    JButton start;
    public Game(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        doEverything();
        this.setVisible(true);
        fieldGame.startGameLoop();
        
    }

    public void doEverything(){
        addGame();
        addToSouth();
        this.pack();
    }

    public void addToSouth(){
        start = new JButton("Start");
        JButton exit = new JButton("Exit Game");
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                System.exit(0);
            }
        });

        start.addActionListener(this);
        JPanel lowerPart = new JPanel(new GridLayout(0, 2));
        lowerPart.add(start);
        lowerPart.add(exit);
        this.add(lowerPart, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent ev){
        this.setVisible(false);
        this.getContentPane().removeAll();
        doEverything();
        this.revalidate();
        this.repaint();
        this.setVisible(true);
        fieldGame.startGameLoop();
    }

    public void addGame(){
        fieldGame = new Field();
        fieldGame.addKeyListener(fieldGame);
        fieldGame.setFocusable(true);
        this.add(fieldGame, BorderLayout.CENTER);
        this.add(fieldGame.scoreBoard, BorderLayout.NORTH);
    }

    public static void main(String[] args){
        Game snake = new Game();
    }

    
    
}
