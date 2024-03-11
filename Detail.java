package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Detail extends JPanel {
    JPanel lower;
    JPanel upper;
    JLabel GameName;
    int score;
    int highScore;
    JLabel scoreTracker;
    JLabel highScoreTracker;
    public Detail(){
        this.setLayout(new GridLayout(0, 1));
        upper = new JPanel();
        upper.setBackground(new Color(44, 66, 44));
        GameName = new JLabel("SNAKE2023");
        GameName.setForeground(Color.WHITE);
        upper.setLayout(new GridLayout(0, 3));
        upper.add(new JLabel(""));
        upper.add(GameName);   
        upper.add(new JLabel(""));

        lower = new JPanel();
        lower.setBackground(new Color(140, 50, 50));
        lower.setLayout(new GridLayout(0, 3));

        // this.setBackground(new Color(140, 50, 50));
        // this.setLayout(new GridLayout(0, 3));

        score = 0;
        highScore = 100;
        scoreTracker = new JLabel("Score: "+score);
        scoreTracker.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        highScoreTracker = new JLabel("High Score: "+highScore);
        highScoreTracker.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        scoreTracker.setForeground(Color.WHITE);
        highScoreTracker.setForeground(Color.white);
        Font f = new Font("serif", Font.BOLD, 30);
        scoreTracker.setFont(f);
        highScoreTracker.setFont(f);
        GameName.setFont(f);

        lower.add(scoreTracker);
        lower.add(new JLabel(""));
        lower.add(highScoreTracker);
        this.add(upper);
        this.add(lower);
    }

    public void setScore(int s){
        score = s;
        scoreTracker.setText("Score: "+score);
    }

}
