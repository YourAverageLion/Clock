package com.example.javafxgame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Button {
    int x,y,w,h;
    Color color = Color.RED;

    public Button(int x, int y, int w, int h, Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }


    public boolean inside (int mousex, int mousey){
        boolean x_bound = mousex>=x && mousex<=x+w;
        boolean y_bound = mousey>=y && mousey<=y+h;
        return x_bound && y_bound;
    }

    public void render (GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x,y,w,h);
    }

}
