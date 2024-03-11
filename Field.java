package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import SnakeGame.Square.Directions;

public class Field extends JPanel implements KeyListener, ActionListener{
    Snake snake;
    boolean playing;
    int waitSubtracter, wait;
    Apple apple;
    boolean buttonPressed;
    int score, highScore;
    JLabel JLScore;
    Detail scoreBoard;
    Thread t1;
    Runnable r1;
    public Field(){
        this.setPreferredSize(new Dimension(480, 480));
        this.setBackground(Color.BLACK);
        initializeStuff();

    }

    public void restartGame(){
        this.setPreferredSize(new Dimension(480, 480));
        this.setBackground(Color.BLACK);
        initializeStuff();
    }

    public void initializeStuff(){
        snake = new Snake(3, 16);
        apple = new Apple(16, false);
        playing = true;
        waitSubtracter = 0;
        wait = 200;
        buttonPressed = false;
        score = 0;
        JLScore = new JLabel("Score: "+score);
        scoreBoard = new Detail();
    }

    public void paint(Graphics g){ // draws the canvas and draws the snake
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        drawSnake(g2d);
        drawApple(g2d, apple);
    }

    public void drawSnake(Graphics2D g){ // gets the location of every snake segment and draws it
        try{
            g.setColor(Color.GREEN);
            LinkedList<Square> sn = this.snake.getSnakeLink();
            for(int i = 0; i<sn.size(); i++){
                int width = sn.get(i).width;
                int x = sn.get(i).x;
                int y = sn.get(i).y;
                g.fillRect(x, y, width, width);
            }
        }catch(Exception ex){}
    }
    
    public void drawApple(Graphics2D g, Apple apple){
        try{
            ImageIcon ii = new ImageIcon("SnakeGame/ra.png");
            int size = apple.size;
            int x = apple.x;
            int y = apple.y;
            Image res = getScaledImage(ii.getImage(), size, size);
            //Image rescaled = ii.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            g.drawImage(res, x, y, this);
        }catch(Exception ex){}
        
    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    public void snakeMove(){ //moves the snake and draws the panel
        this.snake.moveSnake();
        this.repaint();
    }

    public void checkCollision(){
        if(boundaryCollision())
            playing = false;
        if(collisionWithin()){
            playing = false;
        }  
         if(collisionWithApple()){
            apple = new Apple(16, false);
            snake.appendSnake();
            score++;
            if(score%5 == 0)
                waitSubtracter += 30;
            scoreBoard.setScore(score);
            this.repaint();
         }     
    }

    public boolean collisionWithApple(){
        Square head = this.snake.snakeLink.get(0);
        Directions d = head.direction;
        int xtoCheck = head.x, ytoCheck = head.y;
        if(d == Directions.UP){
            xtoCheck += (head.width/2);
        }else if (d == Directions.DOWN){
            xtoCheck += (head.width/2);
            ytoCheck += head.width;
        }else if(d == Directions.LEFT){
            ytoCheck += (head.width/2);
        }else if(d == Directions.RIGHT){
            ytoCheck += (head.width/2);
        }
        if((xtoCheck >= (apple.x) && xtoCheck < (apple.x+apple.size)) && (ytoCheck >= apple.y && ytoCheck < (apple.y+apple.size))){
            return true;
        }
        return false;
    }

    public boolean collisionWithin(){
        Square head = this.snake.snakeLink.get(0);
        Directions d = head.direction;
        int xtoCheck = head.x, ytoCheck = head.y; 
        Snake s = this.snake;    
        for(int i = 1; i<s.length; i++){
            Square sq = s.snakeLink.get(i);
            if(check(xtoCheck, ytoCheck, sq))
                return true;
        }
        return false;
        
    }

    public boolean check(int x, int y, Square s){
        if(x == s.x && y == s.y){
           return true; 
        }else if(x == (s.x + s.width) && y == s.y){
            return true;
        }else if(x == s.x && y == (s.y + s.width)){
            return true;
        }else if(x == (s.x + s.width) && y == (s.y + s.width)){
            return true;
        }
        return false;
    }

    public boolean boundaryCollision(){
        boolean collision = false;
        Square head = this.snake.snakeLink.get(0);
        int xtoCheck = head.x, ytoCheck = head.y;
        if(head.direction == Square.Directions.LEFT){
            if(xtoCheck < 0)
               collision = true; 
        }
        if(head.direction == Square.Directions.RIGHT){
            xtoCheck = head.x + head.width;
            if(xtoCheck > this.getWidth())
                collision = true;
        }
        if(head.direction == Square.Directions.UP){
            if(ytoCheck < 0)
                collision = true;
        }
        if(head.direction == Square.Directions.DOWN){
             ytoCheck = head.y + head.width;
             if(ytoCheck > this.getHeight())
                collision = true;
        }
        return collision;
    }

    public void startGameLoop(){
        r1 = ()->{
            try{
                System.out.println("entered the game loop");
                while(playing){
                    Thread.sleep(wait-waitSubtracter);
                    if(!buttonPressed){
                        this.snakeMove();
                    }  
                    else buttonPressed = false;
                    //this.snake.showDirection();
                    checkCollision();
                }
                // if(score > highscore){

                // }
            }catch(Exception ex){
                System.out.println("something is wrong in the thread sleeping.");
            }
            System.out.println("about to go out");
        };
        t1 = new Thread(r1);
        t1.start();

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            this.snake.changeDirection(Directions.UP);
            System.out.println("the pressed up");
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            this.snake.changeDirection(Directions.DOWN);
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.snake.changeDirection(Directions.RIGHT);
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            this.snake.changeDirection(Directions.LEFT);
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            playing = false;
            return;
        } else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("pressed enter");
            JFrame f = (JFrame)this.getParent().getParent().getParent().getParent();
            f.setVisible(false);
            f.getContentPane().removeAll();
            restartGame();
            f.add(this);
            f.revalidate();
            f.repaint();
            f.setVisible(true);
            this.startGameLoop();
            return;
        }
        this.snakeMove();
        buttonPressed = true;
    }

    public void actionPerformed(ActionEvent ev){
        snake = null;
        apple = null;
        this.repaint();
        snake = new Snake(3, 16);
        apple = new Apple(16, false);
        this.repaint();
        this.setFocusable(true);
        playing = true;
        startGameLoop();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Field ff = new Field();
        ff.addKeyListener(ff);
        ff.setFocusable(true); // so that the keys that I enter are on the JPanel noticed
        f.add(ff);
        f.add(ff.JLScore, BorderLayout.NORTH);
        f.pack();
        f.setVisible(true);
        ff.startGameLoop();

    }


}
