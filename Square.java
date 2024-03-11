package SnakeGame;

public class Square {
    enum Directions {RIGHT, LEFT, DOWN, UP};
    int width, x, y;
    Directions direction;
    int lastx, lasty;
    Directions lastDirection;
    boolean isHead;
    

    public Square(int w, int xloc, int yloc, Directions down, boolean head){
        width = w;
        x = xloc;
        y = yloc;
        direction = down;
        isHead = head;
        
    }

    public int[] getVelocityVector(Directions d){
        if(d == Directions.RIGHT)
            return new int[]{1, 0};
        else if(d == Directions.LEFT)
            return new int[]{-1, 0};
        else if(d == Directions.UP)
            return new int[]{0, -1};
        else if(d == Directions.DOWN)
            return new int[]{0, 1};
        return new int[2];
    }

    public void move(int speed){
        lastx = this.x;
        lasty = this.y;
        lastDirection = this.direction;
        int vector[] = this.getVelocityVector(this.direction);
        int moveByx = vector[0]*speed;
        int moveByy = vector[1]*speed;
        this.x = this.x + moveByx;
        this.y = this.y + moveByy;       
    }

    public void follow(Square leader){
        lastx = this.x;
        lasty = this.y;
        lastDirection = this.direction;
        this.x = leader.lastx;
        this.y = leader.lasty;
        this.direction = leader.lastDirection;
    }



    public String toString(){
        String detail = "";
        detail += "Width: "+this.width+"\nX: "+this.x+"\nY: "+this.y+"\nDirection: "+this.direction+"\nHead: "+isHead;
        detail += "\nlastx: "+this.lastx+"\nlasty: "+this.lasty;
        return detail;
    }



}
