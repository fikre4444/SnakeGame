package SnakeGame;

public class Apple {
    int size;
    int x, y;
    int[] locs;
    public Apple(int s, boolean big){
        // based on size assigns a location which is done by choosing from a list of locations which is generated in the form of a grid of 16 units 
        //so that the snake and the apple coincide in location and not become half half contact
        locs = new int[30];
        for(int i = 0; i<30; i++){
            locs[i] = i*16;
        }
        size = s;
        if(big)
            size = 75;
        x = locs[generateInt(0, 29)];
        y = locs[generateInt(0, 29)];
    }

    public int generateInt(int low, int high){
        double d = Math.random()*(high - low + 1);
        int result = low + (int)d;
        return result;
    }
}
