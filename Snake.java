package SnakeGame;

import java.util.*;
import SnakeGame.Square.Directions;

public class Snake {
    int length;  // holds the length or number of squares in the snake
    LinkedList<Square> snakeLink; // holds the object squares in a linked list might change later
    int speed; // speed a single scalar that we use to multiply with direction;

    public Snake(int l, int sp){
        length = l;
        snakeLink = constructSnake(l);
        speed = sp;

    }

    public LinkedList<Square> constructSnake(int length){
        LinkedList<Square> snakeLin = new LinkedList<>();
        for(int i = 0; i<length; i++){
            int width = 15;
            int locationx = ((length-(1+i))*width) + (length-i);
            Square s;
            if(i == 0)
                s = new Square(width, locationx, 0, Directions.RIGHT, true);
            else s = new Square(width, locationx, 0, Directions.RIGHT, false);
            snakeLin.add(s);
        }
        showDetail(snakeLin);
        return snakeLin;
        
    }

    public void appendSnake(){
        int width = 15;
        Square slast = snakeLink.getLast();
        Square newSquare = new Square(width, slast.lastx, slast.lasty, slast.direction, false);
        snakeLink.add(newSquare);
        length++;
    }

    public void moveSnake(){
        for(int i = 0; i<snakeLink.size(); i++){
            if(i == 0){
                snakeLink.get(0).move(speed);
            }else{
                Square s = snakeLink.get(i-1);
                snakeLink.get(i).follow(s);
            }
        }
    }

    public LinkedList<Square> getSnakeLink(){
        return snakeLink;
    }

    public void showDirection(){
        System.out.println("current direction is "+this.snakeLink.get(0).direction);
    }

    public void changeDirection(Directions d){
        this.snakeLink.get(0).direction = d;
    }



    public void showDetail(LinkedList<Square> sn){
        for(int i=0; i<sn.size(); i++){
            Square s = sn.get(i);
            System.out.println((i+1)+":");
            System.out.println(s.toString());
            System.out.println();
        }
    }

}
