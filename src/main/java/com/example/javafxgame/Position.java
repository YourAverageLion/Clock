package com.example.javafxgame;

public class Position {
    int x,y;
    public Position (int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy){
        x+=dx;
        y+=dy;
    }

}
